package com.demo.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TaskFilter extends HttpServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        String emails = (String) session.getAttribute("email");
        String email = null;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("email")){
                    email=cookie.getValue();
                }
            }
        }

        if(email != null || emails != null){
            if(emails!= null) session.setAttribute("email",emails);
            else session.setAttribute("email",email);
            chain.doFilter(request, response);
        }
        else{
            res.sendRedirect("/TodoList_war_exploded/login");
        }
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
