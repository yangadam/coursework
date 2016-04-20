package com.dedup4.storage.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class DedupCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DedupCoreApplication.class, args);
    }

}
