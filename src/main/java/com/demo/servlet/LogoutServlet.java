package com.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                Cookie ck = new Cookie("email", "");
                ck.setMaxAge(0);
                resp.addCookie(ck);
            }
        }
        HttpSession session = req.getSession();
        session.removeAttribute("email");
        resp.sendRedirect("http://localhost:8080/TodoList_war_exploded/login");
    }
}
