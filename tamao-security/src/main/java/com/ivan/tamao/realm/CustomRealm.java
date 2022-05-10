package com.ivan.tamao.realm;

import cn.hutool.core.util.StrUtil;
import com.ivan.tamao.entity.BaseLoginUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>自定义授权</p>
 *
 * @author ivan
 * @className CustomRealm
 * @since 2021/12/19 11:37
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Set<String> adminRoleNameSet = new HashSet<>();
    private static final Set<String> adminPermissionNameSet = new HashSet<>();
    private static final Set<String> userRoleNameSet = new HashSet<>();
    private static final Set<String> userPermissionNameSet = new HashSet<>();
    static {
        adminRoleNameSet.add("admin");
        userRoleNameSet.add("user");
        adminPermissionNameSet.add("user:insert");
        adminPermissionNameSet.add("user:update");
        adminPermissionNameSet.add("user:delete");
        adminPermissionNameSet.add("user:query");
        userPermissionNameSet.add("user:query");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        BaseLoginUser loginUser = (BaseLoginUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (StrUtil.equals(loginUser.getUsername(), "admin")) {
            authorizationInfo.addRoles(adminRoleNameSet);
            authorizationInfo.addStringPermissions(adminPermissionNameSet);
        }

        if (StrUtil.equals(loginUser.getUsername(), "user")) {
            authorizationInfo.addRoles(userRoleNameSet);
            authorizationInfo.addStringPermissions(userPermissionNameSet);
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String principal = (String) token.getPrincipal();

        if (StrUtil.isBlank(principal)) {
            throw new AuthenticationException("用户名不能为空");
        }

        if (principal.equals("admin"))
            return new SimpleAuthenticationInfo("admin", "123", CustomRealm.class.getName());
        else if (principal.equals("user"))
            return new SimpleAuthenticationInfo("user", "123", CustomRealm.class.getName());
        else
            return null;
    }
}
