package com.weihua.race.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * <p>
 * 设置shiro权限管理器，让同一个接口能被指定的不同角色访问
 * </p>
 * @author liulingyu
 * @date 2022-05-07 16:22
 * @version 1.0
 */
public class AnyRolesAuthorizationFilter extends AuthorizationFilter {
    public AnyRolesAuthorizationFilter() {
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
        String[] rolesArray = (String[]) ((String[]) mappedValue);

        if (rolesArray == null || rolesArray.length == 0) {
            return false;
        }
        Set<String> roles = CollectionUtils.asSet(rolesArray);

        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }

        return false;
    }

}
