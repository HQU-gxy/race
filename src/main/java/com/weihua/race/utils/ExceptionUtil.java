package com.weihua.race.utils;





import com.weihua.race.controller.ErrorController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 *     异常处理工具类
 * </p>
 * @author liulingyu
 * @date 2022-05-07 15:44
 * @version 1.0
 */
public class ExceptionUtil {

    public static final String FILTER_ERROR = "filterError";
    /**
     * {@link ErrorController#handleError(HttpServletRequest)}
     * 的映射路径
     */
    public static final String PATH = "/filter-error";

    /**
     * <p>
     * 处理拦截器中的异常，将其转发给controller层再抛出，
     * 让异常能被全局异常处理器处理
     * </p>
     *
     * @param request  request
     * @param response response
     * @param e        拦截器中的异常
     * @date 2022/5/7 15:45 <br>
     */
    public static void handleException(ServletRequest request, ServletResponse response , Exception e) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setAttribute(FILTER_ERROR, e);
        httpServletRequest.getRequestDispatcher(PATH).forward(request, response);
    }


}
