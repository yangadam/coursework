package com.dedup4.storage.filepicker;

import com.dedup4.storage.common.util.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Configuration
public class BeanConfig {

    @Bean
    public MessageSender messageSender(JmsTemplate jmsTemplate) {
        return new MessageSender(jmsTemplate);
    }

}
