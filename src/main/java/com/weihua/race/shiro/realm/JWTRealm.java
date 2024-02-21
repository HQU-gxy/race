package com.weihua.race.shiro.realm;

import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.User;
import com.weihua.race.service.UserService;
import com.weihua.race.shiro.token.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * <p>
 *     对JWTToken进行检验，
 *     由JWTToken生成用户实体，
 *     对用户实体进行检验和授权
 * </p>
 * @author liulingyu
 * @date 2022-05-08 15:41
 * @version 1.0
 */
@Slf4j
public class JWTRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("JWTRealm 认证方法执行");
        JWTToken jwtToken = (JWTToken) token;
        String username = jwtToken.getPrincipal().toString();
        User user = userService.getUserByUsername(username);
        if (user == null){
            throw new ResultException(ResultEnum.USER_NAME_PWD_NULL);
        }
        return new SimpleAuthenticationInfo(user, username, getName());
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("JWTRealm 授权方法执行");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        info.addRole(currentUser.getRoleId());
        return info;
    }
}
