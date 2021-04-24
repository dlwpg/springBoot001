package com.wpg.graduationdesign.config.shiro;

import com.wpg.graduationdesign.moudles.shones.dao.UserDao;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.entity.UserRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        User userAndRoles= userDao.getUserAndRoles(user.getUserId());
        List<UserRole> userRoles = userAndRoles.getUserRoles();
        if (userRoles != null && !userRoles.isEmpty()) {
            userRoles.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoles().get(0).getName());
            });
        }
        return simpleAuthorizationInfo;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        //还没有放入SimpleAuthenticationInfo所以此处是另一个方法调用的是获取用户名的接口
        //以后装入SimpleAuthenticationInfo后就是整个用户信息
        String name = (String) authenticationToken.getPrincipal();
        //通过用户名查询信息
        User user = userDao.loginByUser(name);
        if (user == null) {
            throw new UnknownAccountException("这个账户不存在！");
        }
        if (user.getStatus() == 0) {
            throw new LockedAccountException("未激活");
        }

        return new SimpleAuthenticationInfo(user, user.getPassWord(), getName());
    }
}
