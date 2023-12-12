package com.weihua.race.controller;

import com.weihua.race.domain.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * controller基类
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022/10/4 16:01
 */
@Slf4j
@RestController
public class BaseController {

    @GetMapping("/test")
    public String test(PageDTO queryDTO) {
        log.info(queryDTO.toString());
        return "test";


    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                log.info("=====initBinder=====的参数为:" + text);
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        });
    }

}
