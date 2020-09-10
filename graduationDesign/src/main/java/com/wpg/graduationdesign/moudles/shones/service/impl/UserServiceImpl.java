package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.wpg.graduationdesign.moudles.shones.dao.UserDao;
import com.wpg.graduationdesign.moudles.shones.pojo.User;
import com.wpg.graduationdesign.moudles.shones.service.UserService;
import com.wpg.graduationdesign.utils.MD5Util;
import com.wpg.graduationdesign.utils.SendEmail;
import com.wpg.graduationdesign.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Result<User> loginByUser(User user) {
        //shiro验证
        Subject subject = SecurityUtils.getSubject();
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
        } catch (AuthorizationException e) {
            return new Result<User>(Result.Resultstatus.FAILD.status
                    , "此用户没有激活账户，请激活此账户！", user);
        }
        //登录成功
        Session session = subject.getSession();
        session.setAttribute("UserInfo", subject.getPrincipal());
        return new Result<>(Result.Resultstatus.SUCCESS.status, "登陆成功！", user);
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
            String content = "<a href='http://127.0.0.1/nxzm/activation/"
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
}
