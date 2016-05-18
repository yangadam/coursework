package com.dedup4.storage.webapp.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Yang Mengmeng Created on May 18, 2016.
 */
public class MailSender {

    String sendFrom;

    String host;

    String username;

    String password;

    Properties properties;

    public MailSender() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
        Properties mailProperties = new Properties();
        try {
            mailProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendFrom = mailProperties.getProperty("address");
        host = mailProperties.getProperty("host");
        username = mailProperties.getProperty("username");
        password = mailProperties.getProperty("password");
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
    }

    /**
     * 发送邮件
     *
     * @param sendTo  收件人
     * @param subject 主题
     * @param context 邮件内容(建议为HTML格式)
     */
    public void sendMail(String sendTo, String subject, String context) throws MessagingException {

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //发件人邮件用户名、密码
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendFrom));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(sendTo));
        message.setSubject(subject);
        message.setContent(context, "text/html;charset = utf-8");
        Transport.send(message);

    }

}
