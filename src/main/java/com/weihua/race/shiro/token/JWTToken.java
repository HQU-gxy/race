package com.weihua.race.shiro.token;

import com.weihua.race.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description token
 * @Date 2018-04-09
 * @Time 16:54
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    private String username;

    public JWTToken(String token) {
        this.token = token;
        this.username = JWTUtil.getUsername(token);
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
