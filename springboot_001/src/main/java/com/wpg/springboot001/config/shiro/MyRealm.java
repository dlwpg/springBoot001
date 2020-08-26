package com.wpg.springboot001.config.shiro;

import com.wpg.springboot001.moudles.account.dao.UserDao;
import com.wpg.springboot001.moudles.account.pojo.Resource;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.ResourceService;
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
    @Autowired
    private ResourceService resourceService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> listrole = user.getRoles();
        if (listrole != null && !listrole.isEmpty()) {
            listrole.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resourceList = resourceService.getResourcesByRoleId(item.getRoleId());

                if (!resourceList.isEmpty() && resourceList != null) {
                    resourceList.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
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
        User user = userDao.selectUserByUserName(name);
        if (user == null) {
            throw new UnknownAccountException("this account is null");
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
