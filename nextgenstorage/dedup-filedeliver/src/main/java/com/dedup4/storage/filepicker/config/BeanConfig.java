package com.dedup4.storage.filepicker.config;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.filepicker.util.SshHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Configuration
public class BeanConfig {

    @Value("$dedup.server.host")
    private String host;
    @Value("$dedup.server.port")
    private int port;
    @Value("$dedup.server.user")
    private String user;
    @Value("$dedup.server.pwd")
    private String password;

    @Bean
    public SshHelper sshHelper() {
        return new SshHelper(host, port, user, password);
    }

    @Bean
    public MessageSender messageSender(JmsTemplate jmsTemplate) {
        return new MessageSender(jmsTemplate);
    }

}
