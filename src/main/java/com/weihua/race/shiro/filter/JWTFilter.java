package com.weihua.race.shiro.filter;

import com.weihua.race.shiro.token.JWTToken;
import com.weihua.race.utils.ExceptionUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     JWT过滤器，用于检验每一次请求的JWTToken
 * </p>
 * @author liulingyu
 * @date 2022-05-08 15:38
 * @version 1.0
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {




    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        if (this.isLoginRequest(request, response)){
            return false;
        }

        boolean allowed = false;
        try {
            // 检测Header里的 JWT token内容是否正确，尝试使用 token进行登录
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { // not found any token
            log.error("Not found any token");
        } catch (Exception e) {

            ExceptionUtil.handleException(request, response, e);

        }
        return allowed || super.isPermissive(mappedValue);

    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        return false;
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorization = httpServletRequest.getHeader("token");
        JWTToken token = new JWTToken(authorization);
        return token;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
                    + "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        Subject subject = getSubject(request, response);
        subject.login(token); // 交给 Shiro 去进行登录验证

        return onLoginSuccess(token, subject, request, response);
        /*try {

        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }*/
    }




    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 后置处理
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
        // 添加跨域支持
        this.fillCorsHeader(WebUtils.toHttp(request), WebUtils.toHttp(response));
    }

    protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                httpServletRequest.getHeader("Access-Control-Request-Headers"));
    }



}
