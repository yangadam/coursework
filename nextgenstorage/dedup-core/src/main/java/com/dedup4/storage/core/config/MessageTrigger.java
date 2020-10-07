package com.dedup4.storage.core.config;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.core.facade.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
@Component
public class MessageTrigger {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTrigger.class);

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private FileService fileService;

    @JmsListener(destination = "queue.filedeliver.dedup")
    public void receive(String text) {
        LOGGER.info(text);
        // TODO deduplicate file
        String path = (File.listRoots())[0].getAbsolutePath() + "tmp/dedup";
        fileService.dedupFile(new File(path + "/upload/" + text));
        messageSender.send("queue.dedup.web", text);
    }

}
