package com.amaury.chatdoc.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Component
class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    JavaMailSender javaMailSender

    @Value('${spring.mail.username}')
    String sender
    @Value('${otpSubject}')
    String otpSubject

    @Override
    void sendEmail(String to, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage()
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true)
            mimeMessageHelper.setFrom(sender)
            mimeMessageHelper.setTo(to)
            mimeMessageHelper.setSubject(otpSubject)
            mimeMessageHelper.setText(body)
            javaMailSender.send(mimeMessage)
    }
}
