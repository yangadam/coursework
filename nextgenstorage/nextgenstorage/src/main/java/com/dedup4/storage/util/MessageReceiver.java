package com.dedup4.storage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@Component
public class MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(destination = "queue.default")
    public void receiveDefault(String text) {
        LOGGER.info(text);
        // TODO
    }

}
