package com.weihua.race.shiro.filter;

import com.weihua.race.wrapper.AuthParamWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 仅用于将{@link HttpRequest}包装一层 {@link AuthParamWrapper}.<br>
 * 不做任何过滤和校验。
 * </p>
 *
 * @author hqully
 * @version 1.0
 */
@Slf4j
public class AuthParamFilter implements Filter{

    public AuthParamFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new AuthParamWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

}
