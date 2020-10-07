package com.dedup4.storage.common.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Yang Mengmeng Created on Mar 20, 2016.
 */
@Configuration
@ComponentScan(basePackages = "com.dedup4.storage.util")
public class ActiveMQConfig {

    @Bean
    public MessageSender messageSender(JmsTemplate jmsTemplate) {
        return new MessageSender(jmsTemplate);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("dedup4", "dedup4", "tcp://139.129.17.212:61616");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName("queue.default");
        return new JmsTemplate(connectionFactory());
    }

}
