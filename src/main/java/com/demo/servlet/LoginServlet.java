package com.demo.servlet;

import com.demo.DAO.UserDAO;
import com.demo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
    private static final UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean checkbox = req.getParameter("remember_me") != null;
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(checkbox);
        User user = userDAO.getUserByEmail(email);
        if(user != null){
            if(user.getVerify()==0){
                resp.sendRedirect("/TodoList_war_exploded/login?error=account isn't exit");
            }
            else {
                if (user.getPassword().equals(password)) {
                    if (checkbox) {
                        Cookie cookie = new Cookie("email", String.valueOf(user.getEmail()));
                        cookie.setMaxAge(30 * 24 * 60 * 60);
                        resp.addCookie(cookie);
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("email", String.valueOf(user.getEmail()));
                    }
                    resp.sendRedirect("/TodoList_war_exploded/t");
                } else {
                    resp.sendRedirect("/TodoList_war_exploded/login?error=Username or password is incorrect");
                }
            }
        }
        else{
            resp.sendRedirect("/TodoList_war_exploded/login?error=Username or password is incorrect");
        }
    }
}
