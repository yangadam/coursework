package com.dedup4.storage.filepicker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Configuration
@EnableScheduling
public class TestConfig {

    @Bean
    public TestTask testTask() {
        return new TestTask();
    }

}
