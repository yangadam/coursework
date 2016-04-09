package com.dedup4.storage.filepicker;

import com.dedup4.storage.common.util.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Component
public class FilePickTask {

    @Autowired
    private FsShell fsShell;

    @Autowired
    private MessageSender messageSender;

    @Scheduled(cron = "0 */20 * * * ?")
    public void pickFiles() {
        File folder = new File("/tmp/dedup/upload/");
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files != null ? files : new File[0]) {
                fsShell.moveFromLocal(file.getAbsolutePath(), "/users/upload/");
                messageSender.send("queue.pickfile.web", file.getName()); // Send a message to web server
                messageSender.send("queue.pickfile.dedup", file.getName()); // Send a message to deduplication server
            }
        }
    }

}
