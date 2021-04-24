package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.HttpResponse;
import com.wpg.graduationdesign.config.ResourceConfigBean;
import com.wpg.graduationdesign.moudles.shones.dao.RoleDao;
import com.wpg.graduationdesign.moudles.shones.dao.USerRoleDao;
import com.wpg.graduationdesign.moudles.shones.dao.UserDao;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.entity.UserRole;
import com.wpg.graduationdesign.moudles.shones.service.UserService;
import com.wpg.graduationdesign.utils.MD5Util;
import com.wpg.graduationdesign.utils.SendEmail;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private USerRoleDao uSerRoleDao;
    private Subject subject;


    @Override
    @Transactional
    public Result<User> loginByUser(User user) {
        //shiro验证
        subject = SecurityUtils.getSubject();
        UsernamePasswordToken up = new UsernamePasswordToken(
                user.getUserName(), MD5Util.getMD5(user.getPassWord()));
        up.setRememberMe(user.getRememberMe());
        try {
            subject.login(up);

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new Result<>(Result.Resultstatus.FAILD.status, "该账户不存在！");
        } catch (IncorrectCredentialsException e) {
            return new Result<>(Result.Resultstatus.FAILD.status, "密码错误！");
        } catch (LockedAccountException e) {
            return new Result<User>(Result.Resultstatus.FAILD.status
                    , "此用户没有激活账户，请前往邮箱激活此账户！", user);
        }
        //登录成功
        Session session = subject.getSession();
        session.setAttribute("UserInfo", subject.getPrincipal());
        System.out.println(subject.getPrincipal());
        return new Result<>(Result.Resultstatus.SUCCESS.status, "登录成功！", user);
    }

    @Override
    @Transactional
    public Result<User> registerUser(User user) {
        User selectUser = userDao.loginByUser(user.getUserName());
        if (selectUser != null) {
            return new Result<>(Result.Resultstatus.FAILD.status,
                    "请换个用户名,当前账户已存在！");
        }
        user.setPassWord(MD5Util.getMD5(user.getPassWord()));
        //未激活设置为0
        user.setStatus(0);
        userDao.registerUser(user);

        try {
            String content = "<a href='http://127.0.0.1/nxzm/user/activation/"
                    + user.getUserId() + "'>请点击激活</a>";
            SendEmail.sendMail(user.getEmail(), "这是一封激活邮件", content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new Result<>(Result.Resultstatus.SUCCESS.status, "注册成功！", user);
    }

    @Override
    @Transactional
    public void activeStatus(int userId) {
        userDao.activeStatus(userId);
    }

    //上传照片
    @Override
    @Transactional
    public Result<String> uploadUserImg(MultipartFile file, String userId) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Please select img.");
        }
        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() + "userimg/" +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux() + "userimg/"
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() + "userimg/" +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            //存入upload文件夹
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Upload failed.");
        }

//        System.err.println(relativePath);
//        return new Result<String>(
//                Result.Resultstatus.SUCCESS.status, "Upload success.", relativePath);
//        String path = null;
//        try {
//            path = ResourceUtils.getURL("classpath:").getPath() + "static/upload/userimg";
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            //判断文件是否为空
//            //创建文件对象
//            File ff = new File("" + path + "/" + file.getOriginalFilename() + "");
////                   判断服务器目录是否存在，不存在就创建目录
//            if (!ff.isDirectory()) {
//                ff.mkdirs();
//            }
//            file.transferTo(ff);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        //存虚拟路径到数据库
        userDao.updateImg(relativePath, userId);
        return new Result<String>(
                Result.Resultstatus.SUCCESS.status, "Upload success.", relativePath);

    }

    @Override
    @Transactional
    public Result<User> getImageByUserId(int userId) {
        User user = userDao.getImageByUserId(userId);
//        System.out.println(user);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "", user);
    }


    //修改其他信息
    @Override
    @Transactional
    public Result<User> updateProfile(User user) {
        User selectUser = userDao.loginByUser(user.getUserName());
        if (selectUser != null && user.getUserId() != selectUser.getUserId()) {
            return new Result<>(Result.Resultstatus.FAILD.status,
                    "请换个用户名,当前账户已存在！");
        }
        userDao.updateProfile(user);
        subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("UserInfo", user);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "", user);
    }

    //修改密码
    @Override
    @Transactional
    public Result<String> revisePassword(int userId, String newPassword) {
        String password = MD5Util.getMD5(newPassword);
        userDao.revisePassword(userId, password);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "修改成功");
    }

    //分页查询
    @Override
    @Transactional
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        PageInfo<User> userslist = new PageInfo<>(Optional.ofNullable(userDao.getusersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
        return userslist;
    }

    //获取所有权限
    @Override
    @Transactional
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
    }

    //获取用户所有信息
    @Override
    @Transactional
    public Result<User> getUserAndRoles(int userId) {
        User user = userDao.getUserAndRoles(userId);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "", user);
    }

    //重置密码
    @Override
    @Transactional
    public Result<String> resetPassword(int userId) {
        userDao.resetPassword(userId, MD5Util.getMD5("000000"));
        return new Result<>(Result.Resultstatus.SUCCESS.status, "重置密码成功");
    }

    ///删除用户
    @Override
    @Transactional
    public Result<String> deleteUser(int userId) {
        userDao.deleteUser(userId);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "删除用户成功");
    }

    //修改权限
    @Override
    @Transactional
    public Result<User> updateRole(int userId, List<UserRole> userRole) {
        //插入前删除所有权限
        uSerRoleDao.deleteAllUserRoleByUserId(userId);
        //重新分配权限

        if (userRole != null && !userRole.isEmpty()) {
            userRole.stream().forEach(item -> {
                System.out.println(item.getRoleId());
                uSerRoleDao.insertRole(userId, item.getRoleId());
            });
            return new Result<>(Result.Resultstatus.SUCCESS.status, "修改成功");
        } else {
            return new Result<>(Result.Resultstatus.FAILD.status, "无效修改");
        }
    }

    @Override
    public Result<User> checkOldPassword(User user) {
        User user1 = userDao.loginByUser(user.getUserName());
        if (user1.getPassWord().equals(MD5Util.getMD5(user.getPassWord()))){
            return new Result<>(Result.Resultstatus.SUCCESS.status,"原密码正确",user1);
        }
        else {
            return new Result<>(Result.Resultstatus.FAILD.status,"原密码不正确",user1);
        }
    }

    @Override
    public Result<User> updateSetting(User user) {
        User selectUser = userDao.loginByUser(user.getUserName());
        if (selectUser != null && user.getUserId() != selectUser.getUserId()) {
            return new Result<>(Result.Resultstatus.FAILD.status,
                    "请换个用户名,当前账户已存在！");
        }
        userDao.updateProfileQt(user);
//        userDao.loginByUser(user.getUserName());
        return new Result<>(Result.Resultstatus.SUCCESS.status,"修改成功",user);
    }
}
