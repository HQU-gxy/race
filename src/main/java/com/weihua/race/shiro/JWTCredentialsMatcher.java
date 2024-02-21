package com.weihua.race.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.weihua.race.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *     对JWTToken进行校验的CredentialsMatcher
 * </p>
 * @author liulingyu
 * @date 2022-05-08 15:43
 * @version 1.0
 */
public class JWTCredentialsMatcher implements CredentialsMatcher {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * JwtCredentialsMatcher只需验证JwtToken内容是否合法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

		String token = authenticationToken.getCredentials().toString();
		String username = authenticationToken.getPrincipal().toString();
		try {
			JWTUtil.verify(token, username);
			return true;
		} catch (JWTVerificationException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
