package com.weihua.race.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * <p>
 *     处理多Realm情况下shiro无法抛出自定义异常的情况
 * </p>
 * @author hqully
 * @date 2022-05-08 15:44
 * @version 1.0
 */
public class MultiRealmAuthenticator extends ModularRealmAuthenticator {

	private static final Logger log = LoggerFactory.getLogger(MultiRealmAuthenticator.class);


	@Override
	protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
		AuthenticationStrategy strategy = getAuthenticationStrategy();
		AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);
		if (log.isTraceEnabled()) {
			log.trace("Iterating through {} realms for PAM authentication", realms.size());
		}
		for (Realm realm : realms) {
			aggregate = strategy.beforeAttempt(realm, token, aggregate);
			if (realm.supports(token)) {
				log.trace("Attempting to authenticate token [{}] using realm [{}]", token, realm);
				AuthenticationInfo info = null;
				Throwable t = null;

				// 此行代码执行取消try catch，若异常直接抛出，其他同原方法
				info = realm.getAuthenticationInfo(token);
				aggregate = strategy.afterAttempt(realm, token, info, aggregate, t);
			} else {
				log.debug("Realm [{}] does not support token {}.  Skipping realm.", realm, token);
			}
		}
		aggregate = strategy.afterAllAttempts(token, aggregate);
		return aggregate;
	}

}
