package com.demo.servlet;

import com.demo.DAO.OTPDAO;
import com.demo.DAO.UserDAO;
import com.demo.model.OTP;
import com.demo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet {
    private static final OTPDAO otpDAO = new OTPDAO();
    private static final UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("/verify.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp_req = req.getParameter("otp");
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("z_email");
        OTP otp = otpDAO.getOTP(email);
        if( otp.getPassword().equals(otp_req)) {
            long time = System.currentTimeMillis();
            long expire = otp.getExpireAt().getTime();
            if(time<=expire) {
                userDAO.activeAccount(email);
                session.setAttribute("email", email);

                if( session.getAttribute("vv")==null) {

                    resp.sendRedirect("/TodoList_war_exploded/t");
                }
                else{
                    resp.sendRedirect("/TodoList_war_exploded/changepassword");
                }
            } else{
                resp.sendRedirect("/TodoList_war_exploded/verify?error= otp is expired");
            }
        }
        else{
            resp.sendRedirect("/TodoList_war_exploded/verify?error= otp is incorrect");
        }
    }
}
