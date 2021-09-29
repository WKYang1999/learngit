package com.neu.interceptor;

import com.neu.domain.Menu;
import com.neu.domain.User;
import com.neu.utils.ConstUtil;
import com.neu.vo.MenuVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        Object user = session.getAttribute(ConstUtil.SESSION_KEY);

        String requestURI = request.getRequestURI();

        requestURI = requestURI.replace(request.getContextPath(),"");

        String result = requestURI.substring(requestURI.length()-3);

        if(user==null){

            //判断是否来登录
            if(requestURI.equals("/login")){
                return true;
            }
            if(requestURI.equals("/register")){
                return true;
            }
            if(requestURI.equals("/users/register")){
                return true;
            }
            if(requestURI.equals("/index")){
                return true;
            }
            if(result.equals("/sp")){
                return true;
            }if(result.equals("/gk")){
                return true;
            }if(result.equals("/ss")){
                return true;
            }if(requestURI.equals("/search")){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }else{
            if(requestURI.equals("/login")){
                response.sendRedirect(request.getContextPath()+"/index");
                return false;
            }

            //判断是否有权限

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            //TODO
            //System.out.println(handlerMethod.getMethod().getName());
            //System.out.println(handlerMethod.getBean().getClass());

            //    http:............/users
                                       //UserController的list方法来处理
                                             //@RequestMapping("/users")  --->/users!list

            //判断是否需要权限才能访问的请求，如果不是，则直接放行
            RequestMapping requestMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
            if(requestMapping == null){
                return true;
            }

//            if(1==1){
//                return true;
//            }
            //判断是否拥有权限
            String accessToken = requestMapping.value()[0]+"!"+handlerMethod.getMethod().getName();
            System.out.println("accessToken:" + accessToken);
            List<MenuVO> menus  = (List<MenuVO>) request.getSession().getAttribute(ConstUtil.SESSION_MENU_KEY);
            System.out.println("menus:" + menus);
            for(MenuVO menu : menus){
                if(menu.getAccessToken().equals(accessToken)){
                    return true;
                }
            }

            request.getRequestDispatcher("/WEB-INF/jsp/common/forbidden.jsp").forward(request, response);
            return false;
        }
    }
}
