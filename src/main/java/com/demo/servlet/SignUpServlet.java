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

@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
    private static final UserDAO userDAO = new UserDAO();
    private static final OTPDAO otpdao = new OTPDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = userDAO.getUserByEmail(email);
        if(user!= null) {
            if(user.getVerify()==1) {
                resp.sendRedirect("/TodoList_war_exploded/signup?error=email is exit");
            }
            else {
                userDAO.updateUser(user);
                OTP otp = new OTP(email);
                otpdao.addOTP(otp);
                EmailService emailService = new EmailService();
                try {
                    emailService.sendTo(email,otp);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                session.setAttribute("z_email", email);
                resp.sendRedirect("/TodoList_war_exploded/verify");
            }
        }
        else{
            userDAO.addUser(new User(password,email,0));
            OTP otp = new OTP(email);
            otpdao.addOTP(otp);
            EmailService emailService = new EmailService();

            try {
                emailService.sendTo(email,otp);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("z_email", email);
            resp.sendRedirect("/TodoList_war_exploded/verify");
        }

    }
}
