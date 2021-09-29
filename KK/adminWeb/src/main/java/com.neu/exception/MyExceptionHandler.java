package com.neu.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ex.printStackTrace();
        //ajax请求
        if(request.getHeader("X-Requested-With").equals("XMLHttpRequest")){
            response.setContentType("application/json;charset=utf-8");
            try {
               PrintWriter printWriter =  response.getWriter();
               printWriter.print("{\"code\":500,\"msg\":\""+ex.getMessage()+"\"}");
               printWriter.flush();
            } catch (IOException e) {
               e.printStackTrace();
            }
            return null;
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error",ex.getMessage());
            modelAndView.setViewName("common/error");
            return modelAndView;
        }
    }
}
