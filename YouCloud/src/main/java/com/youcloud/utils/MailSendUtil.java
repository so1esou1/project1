package com.youcloud.utils;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Random;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO 发送邮箱的工具包，主要在注册时才使用
 */
public class MailSendUtil implements Serializable {
    @Autowired
    JavaMailSender mailSender;


    public MailSendUtil(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    Logger logger = LoggerUtil.getInstance(MailSendUtil.class);

    /**
        @ClassName MailSendUtil.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 发送邮箱验证码的方法
    */
    @Async
    public String IdentifyCode(String userEmail){

        Random r = new Random( System.currentTimeMillis() );
        int idenCode = 10000 + r.nextInt(20000);
        logger.info("正在发送验证邮件，请稍后");

        //new MimeMessage();或:
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装~
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            //正文
            helper.setSubject("友云邮箱验证码");
            helper.setText("亲爱的用户你好，欢迎使用友云社区云盘，你的注册验证码是"
                    + idenCode + ",请不要将验证码告诉他人，祝您使用愉快!");

            //发送方和接收方
            helper.setFrom("w1401142897@163.com");
            helper.setTo(userEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
        return String.valueOf(idenCode);
    }
}
