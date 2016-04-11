package com.dedup4.storage.filepicker.task;

import com.dedup4.storage.common.util.MessageSender;
import com.dedup4.storage.filepicker.util.SshHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Component
public class FileDeliverTask {

//    @Autowired
//    private FsShell fsShell;

    @Autowired
    private SshHelper sshHelper;

    @Autowired
    private MessageSender messageSender;

    @Scheduled(cron = "0 */20 * * * ?")
    public void deliverFiles() {
        File folder = new File("/tmp/dedup/upload/");
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files != null ? files : new File[0]) {
                sshHelper.upload(file.getParentFile().toPath(), "/tmp/dedup/upload/", file.getName());
                //fsShell.moveFromLocal(file.getAbsolutePath(), "/users/upload/");
                messageSender.send("queue.pickfile.web", file.getName()); // Send a message to web server
                messageSender.send("queue.pickfile.dedup", file.getName()); // Send a message to deduplication server
            }
        }
    }

}
