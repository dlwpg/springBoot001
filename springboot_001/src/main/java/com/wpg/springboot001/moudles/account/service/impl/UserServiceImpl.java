package com.wpg.springboot001.moudles.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.config.ResourceConfigBean;
import com.wpg.springboot001.moudles.account.dao.RoleDao;
import com.wpg.springboot001.moudles.account.dao.UserDao;
import com.wpg.springboot001.moudles.account.dao.UserRoleDao;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.UserService;
import com.wpg.springboot001.utils.MD5Util;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;
    private Subject subject;

    @Override
    @Transactional
    public Result<User> insertUserByUser(User user) {
        if (userDao.selectUserByUserName(user.getUserName()) != null) {
            return new Result<User>(Result.Resultstatus.FAILD.status,
                    "username is alive");
        }
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUserByUser(user);

        //删除所有角色，在重新插入新的角色
        userRoleDao.deleteUserRole(user.getUserId());
        List<Role> list = user.getRoles();
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(item -> {
                userRoleDao.inserUserRole(user.getUserId(), item.getRoleId());
            });
        }
        return new Result<User>(Result.Resultstatus.SUCCESS.status,
                "insert user success!", user);
    }

    @Override
    @Transactional
    public Result<User> selectUserByUserName(User user) {

        subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName()
                , MD5Util.getMD5(user.getPassword()));

        //设置记住我
        usernamePasswordToken.setRememberMe(user.getRememberMe());

        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.Resultstatus.FAILD.status,
                    "username or password is error");
        }

//        User user1=userDao.selectUserByUserName(user.getUserName());
        Session session=subject.getSession();
        session.setAttribute("UserInfo", subject.getPrincipal());
        return new Result<User>(Result.Resultstatus.SUCCESS.status, "login success");

    }

    @Override
    @Transactional
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(Optional.ofNullable(userDao.getusersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User tem = userDao.selectUserByUserName(user.getUserName());
        if (tem != null && user.getUserId() != tem.getUserId()) {
            return new Result<>(Result.Resultstatus.FAILD.status, "this userName is repeat.");
        }
        userDao.updateUser(user);

        //修改权限
        userRoleDao.deleteUserRole(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.inserUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<>(Result.Resultstatus.SUCCESS.status, "update success", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(Integer userId) {
        //删除用户的时候同时删除其角色信息
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRole(userId);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "delete is success");
    }

    @Override
    @Transactional
    public User selectByUserId(int userId) {
        return userDao.selectByUserId(userId);
    }

    //查询所有role对象
    @Override
    @Transactional
    public List<Role> selectRoles() {
        return Optional.ofNullable(roleDao.selectRoles()).orElse(Collections.emptyList());
    }

    //上传照片

    @Override
    @Transactional
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            //存入upload文件夹
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Upload failed.");
        }

        System.err.println(relativePath);
        return new Result<String>(
                Result.Resultstatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.selectUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(Result.Resultstatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);
        subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        session.setAttribute("UserInfo", user);

        return new Result<User>(Result.Resultstatus.SUCCESS.status, "Edit success.", user);
    }

    @Override
    public void logout() {
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        Session session=subject.getSession();
        session.setAttribute("UserInfo",null);
    }
}
