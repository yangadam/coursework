package com.dedup4.storage.webapp;

import com.dedup4.storage.common.domain.FileRecipe;
import com.dedup4.storage.webapp.service.FileOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

import java.io.File;
import java.util.Arrays;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@SpringBootApplication
@EnableJms
public class WebappApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebappApplication.class);

    @Autowired
    private FileOperationService fileOperationService;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebappApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.debug(beanName);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @JmsListener(destination = "queue.pickfile.web")
    public void receiveFilePick(String text) {
        FileRecipe fileRecipe = fileOperationService.getByMd5(text);
        fileRecipe.setOnHdfs(true);
        fileOperationService.updateFileRecipe(fileRecipe);
        File fileToDelete = new File("/tmp/dedup/upload/" + text);
        fileToDelete.delete();
    }

}
