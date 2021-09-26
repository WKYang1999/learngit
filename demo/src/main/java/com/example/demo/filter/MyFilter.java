package com.example.demo.filter;

import com.example.demo.utils.ConstUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MyFilter implements Filter {

    //过滤器
    //indexOf  返回字符串首次出现的位置，如果没有要检索的字符串，则返回-1
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if(request.getRequestURI().indexOf("/index") != -1 ||
            request.getRequestURI().indexOf("/asd") != -1 ||
            request.getRequestURI().indexOf("/online") != -1
            ){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            Object user = session.getAttribute(ConstUtil.SESSION_KEY);
            if(user == null){
                if(request.getRequestURI().indexOf("/login") != -1){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    wrapper.sendRedirect("/login");
                }
            }else{
                if(request.getRequestURI().indexOf("/login") != -1){
                    wrapper.sendRedirect("/index");
                }else{
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
