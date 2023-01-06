package com.library.filter;

import com.library.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user == null){
                httpServletRequest.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
        }else{
            /**
             * 管理员模式拦截
             */
            //if(!"ray".equals(user.getUsername())){
                
               // httpServletRequest.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
            //}
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
