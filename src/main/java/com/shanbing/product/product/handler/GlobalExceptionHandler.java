package com.shanbing.product.product.handler;

import com.shanbing.product.product.result.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value=Exception.class)
    public JsonResult allExceptionHandler(HttpServletRequest request, Exception exception){
        logger.error("异常错误",exception);
       return new JsonResult(-1,"服务器错误");
    }

}
