package com.example.foodWebSiteDubai.util;


import com.example.foodWebSiteDubai.entity.MailLinkType;
import com.example.foodWebSiteDubai.exception.CommonException;
import com.example.foodWebSiteDubai.repository.MailLinkTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailLinkTypeRepo mailLinkTypeRepo;

    public void sendMessage(String to, String uuid, Long userId, Long linkId) throws MessagingException {
        Optional<MailLinkType> mailLinkType = mailLinkTypeRepo.findById(linkId);
        if (mailLinkType.isEmpty()){
            throw new CommonException(HttpStatus.BAD_REQUEST,"Invalid Link type");
        }
        MailLinkType mailLinkType1 = mailLinkType.get();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMailMessage = new MimeMessageHelper(message, true);
        mimeMailMessage.setTo(to);
        mimeMailMessage.setSubject("Activation link");
        mimeMailMessage.setText(mailLinkType1.getLinkUrl());
//        mimeMailMessage.setText("Please click the link below." +
//                "<a href='\"http://localhost:2345/v1/user/activateUser?uuid=" + uuid + "&userId=" + userId + ">" + "Click Me" + "</a>");
        javaMailSender.send(message);
    }
}
