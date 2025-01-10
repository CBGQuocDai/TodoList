package com.demo.servlet;

import com.demo.DAO.OTPDAO;
import com.demo.DAO.UserDAO;
import com.demo.model.OTP;
import com.demo.model.User;
import com.demo.util.EmailService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/forgot"})
public class ForgotPassword extends HttpServlet {
    private static final OTPDAO otpdao = new OTPDAO();
    private static final UserDAO userdao = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int vv = 1;
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        session.setAttribute("z_email", email);
        session.setAttribute("vv", vv);
        User user = userdao.getUserByEmail(email);
        if(user == null) {
            resp.sendRedirect("/TodoList_war_exploded/forgot?message=email isn't exist");
        }
        else{
            OTP otp = new OTP(email);
            otpdao.addOTP(otp);
            EmailService emailService = new EmailService();
            try {
                emailService.sendTo(email,otp);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("/TodoList_war_exploded/verify");
        }
    }
}
