package com.dedup4.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.debug(beanName);
        }
    }

}
