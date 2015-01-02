package cn.edu.xmu.comm.commons.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Roger on 2015/1/2 0002.
 *
 * Send E-mail in the web application
 */
public class MailUtils {

    String sendFrom;

    String host;

    String username;

    String password;

    Properties properties;

    /**
     * MailUtils构造函数
     * 读取mail.properties文件
     */
    public MailUtils() {
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
     * @param sendTo 收件人
     * @param subject 主题
     * @param context 邮件内容(建议为HTML格式)
     */
    public void sendMail(String sendTo, String subject, String context) {

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(sendFrom));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(sendTo));

            // Set Subject: 头部头字段
            message.setSubject(subject);

            // 设置消息体
            message.setContent(context, "text/html;charset = utf-8");

            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
