package com.demo.hibernate.controller.advice;


import com.demo.hibernate.exceptions.PrivateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SignControllerAdvice {

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map exceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1002);
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     */
    @ResponseBody
    @ExceptionHandler(value = PrivateException.class)
    public Map PrivateExceptionHandler(PrivateException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ex.code);
        map.put("msg", ex.msg);
        return map;
    }

}