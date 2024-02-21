package com.weihua.race.controller;

import com.weihua.race.common.exception.ResultExceptionHandler;
import com.weihua.race.utils.ExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 处理filter中的异常Controller
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023/3/7 14:21
 */
@RestController
public class ErrorController {

    /**
     * 转发过滤器中的异常请求给 {@link ResultExceptionHandler}处理。
     * @param request 带有异常attribute的请求
     * @throws Throwable 过滤器中的异常
     */
    @RequestMapping(ExceptionUtil.PATH)
    public void handleError(HttpServletRequest request) throws Throwable {
        //获取请求中的异常并直接抛出，交由全局异常处理器处理
        Throwable filterError = (Throwable) request.getAttribute(ExceptionUtil.FILTER_ERROR);
        throw filterError;
    }
}
