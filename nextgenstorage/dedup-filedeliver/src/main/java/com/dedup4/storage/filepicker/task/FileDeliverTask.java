package com.dedup4.storage.filepicker.task;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.filepicker.util.SshHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Component
public class FileDeliverTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDeliverTask.class);

    @Autowired
    private SshHelper sshHelper;

    @Autowired
    private MessageSender messageSender;

    @Scheduled(cron = "30 * * * * ?")
    public void deliverFiles() {
        LOGGER.info("Start to deliver files.");
        File root = (File.listRoots())[0];
        String path = root.getAbsolutePath() + "tmp/dedup/upload";
        File folder = new File(path);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files != null ? files : new File[0]) {
                if (file.isFile()) {
                    sshHelper.upload(folder.toPath(), "/tmp/dedup/upload", file.getName());
                    // Send a message to deduplication server
                    messageSender.send("queue.filedeliver.dedup", file.getName());
                }
            }
        }
        LOGGER.info("Complete delivery.");
    }

}
