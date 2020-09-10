package com.wpg.graduationdesign.config.shiro;

import com.wpg.graduationdesign.moudles.shones.dao.UserDao;
import com.wpg.graduationdesign.moudles.shones.pojo.User;
import com.wpg.graduationdesign.vo.Result;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
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
//    @Autowired
//    private ResourceService resourceService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        User user = (User) principalCollection.getPrimaryPrincipal();
//        List<Role> listrole = user.getRoles();
//        if (listrole != null && !listrole.isEmpty()) {
//            listrole.stream().forEach(item -> {
//                simpleAuthorizationInfo.addRole(item.getRoleName());
//                List<Resource> resourceList = resourceService.getResourcesByRoleId(item.getRoleId());
//
//                if (!resourceList.isEmpty() && resourceList != null) {
//                    resourceList.stream().forEach(resource -> {
//                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
//                    });
//                }
//            });
//
//        }
//        return simpleAuthorizationInfo;
        return null;
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
            throw new AuthorizationException("未激活");
        }

        return new SimpleAuthenticationInfo(user, user.getPassWord(), getName());
    }
}
