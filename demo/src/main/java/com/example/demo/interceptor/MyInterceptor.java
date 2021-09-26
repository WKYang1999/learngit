package com.example.demo.interceptor;

import com.example.demo.utils.ConstUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor  implements HandlerInterceptor {

    //拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("使用了拦截器");
        return false;
//        HttpSession session = request.getSession();
//
//        Object user = session.getAttribute(ConstUtil.SESSION_KEY);
//
//        String requestURI = request.getRequestURI();
//
//        requestURI = requestURI.replace(request.getContextPath(),"");
//        System.out.println("session user:" + user);
//        System.out.println("requestURI:" + requestURI);
//        if(user == null){
//            System.out.println("user == null");
//            return true;
//        }else {
//            System.out.println("user != null");
//            if(requestURI.equals("/login")){
//                response.sendRedirect(request.getContextPath() + "/index");
//                return false;
//            }
//            return true;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
