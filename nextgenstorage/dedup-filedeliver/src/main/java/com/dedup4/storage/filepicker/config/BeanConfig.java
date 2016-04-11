package com.dedup4.storage.filepicker.config;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.filepicker.util.SshHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Configuration
public class BeanConfig {

    @Bean
    public SshHelper sshHelper() {
        return new SshHelper("139.129.10.110", "YangMengmeng930626");
    }

    @Bean
    public MessageSender messageSender(JmsTemplate jmsTemplate) {
        return new MessageSender(jmsTemplate);
    }

}
