package com.dedup4.storage.core;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.core.detection.Dedup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

import java.io.File;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableJms
public class DedupCoreApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DedupCoreApplication.class);

    @Autowired
    private MessageSender messageSender;

    public static void main(String[] args) {
        SpringApplication.run(DedupCoreApplication.class, args);
    }

    @JmsListener(destination = "queue.pickfile.dedup")
    public void receiveDefault(String text) {
        LOGGER.info(text);
        // TODO deduplicate file
        String path = (File.listRoots())[0].getAbsolutePath() + "tmp/dedup";
        Dedup dedup = new Dedup(path);
        try {
            dedup.operate(new File(path + "/upload/" + text));
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("", e);
        }
        messageSender.send("queue.dedup.web", text);
    }

}
