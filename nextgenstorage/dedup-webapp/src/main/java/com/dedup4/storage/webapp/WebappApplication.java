package com.dedup4.storage.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

import java.util.Arrays;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@SpringBootApplication
@EnableJms
public class WebappApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebappApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebappApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.debug(beanName);
        }
    }

    @JmsListener(destination = "queue.pickfile.web")
    public void receiveFilePick(String text) {
        LOGGER.info(text);
        // TODO delete file
        // TODO update database

    }

}
