package com.itheima.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    // Exception类型的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView doMyException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exp", ex.getMessage());
        modelAndView.setViewName("exp");
        return modelAndView;
    }
}