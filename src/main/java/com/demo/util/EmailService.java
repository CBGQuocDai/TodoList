package com.demo.util;

import com.demo.model.OTP;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static final String email= System.getenv("EMAIL");
    private static final String password= System.getenv("EMAIL_PASSWORD");
    private static final String host="smtp.gmail.com";
    public void sendTo(String to, OTP otp) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };
        // create mail
        Session session = Session.getDefaultInstance(props, auth);
        String msg= "Welcome to todolist, your otp is "+otp.getPassword();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Todo verify");
        message.setText(msg);
        // gửi mail
        Transport.send(message);
    }
}
