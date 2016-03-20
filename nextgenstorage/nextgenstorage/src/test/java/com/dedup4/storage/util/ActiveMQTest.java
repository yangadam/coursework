package com.dedup4.storage.util;

import com.dedup4.storage.config.ActiveMQConfig;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.jms.JmsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ActiveMQConfig.class)
public class ActiveMQTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    private MessageSender messageSender;

    @Test
    public void testSendMessage() {
        try {
            messageSender.send(new ActiveMQQueue("queue.test"), "Test1");
            messageSender.send("Test2");
            assertTrue(outputCapture.toString().contains("Test1"));
            assertTrue(outputCapture.toString().contains("Test2"));
        } catch (JmsException e) {
            fail("Fail to send message.");
        }
    }

}