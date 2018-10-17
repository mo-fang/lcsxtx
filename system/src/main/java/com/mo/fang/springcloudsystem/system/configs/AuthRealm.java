package com.mo.fang.springcloudsystem.system.configs;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.mo.fang.springcloudsystem.system.mapper.MenuAndButtonMapper;
import com.mo.fang.springcloudsystem.system.serviceI.MenuAndButtonService;
import com.mo.fang.springcloudsystem.system.serviceI.UserService;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuAndButtonService  menuAndButtonService;
    private SysUser user = new SysUser();

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        this.user.setUsername(username);
        SysUser user = userService.getUser(this.user);//数据库中获取用户的信息
        if (user ==null){
            throw new  UnknownAccountException("用户名为"+username+"的用户不存在于本系统");
        }
        Object principal = user.getUsername();
        String hashedCredentials = user.getPassword(); //密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal); // 盐:是不需要在配置文件中配置的；随机字符串或者ID作为盐 此处用用户的principal作为盐
        String realmName = getName();
        SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(user, hashedCredentials, credentialsSalt, realmName);
        return info;//放入shiro.调用CredentialsMatcher检验密码
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = (SysUser) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        if (user != null) {
            Set<String> perms = new HashSet<String>();
            perms = menuAndButtonService.getMbAuthPerms(user.getId());
            if (perms!=null) {
                // 权限加入AuthorizationInfo认证对象
                auth.setStringPermissions(perms);
            }
        }
        return auth;
    }
    public void removeUserAuthorizationInfoCache() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//        SimplePrincipalCollection pc = new SimplePrincipalCollection();
//        pc.add(sysUser, getName());
//        super.clearCachedAuthorizationInfo(pc);
    }


}