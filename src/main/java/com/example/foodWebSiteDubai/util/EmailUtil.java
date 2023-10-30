package com.example.foodWebSiteDubai.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(String to, String uuid, Long userId) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMailMessage = new MimeMessageHelper(message, true);
        mimeMailMessage.setTo(to);
        mimeMailMessage.setSubject("Activation link");
        mimeMailMessage.setText("Please click the link below." +
                "<a href='\"http://localhost:2345/v1/user/activateUser?uuid=" + uuid + "&userId=" + userId + ">" + "Click Me" + "</a>");
        javaMailSender.send(message);
    }
}
