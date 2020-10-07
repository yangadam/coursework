package com.dedup4.storage.common.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.jms.JmsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
            messageSender.send("queue.test", "Test2");
            assertThat(outputCapture.toString(), containsString("Test1"));
            assertThat(outputCapture.toString(), containsString("Test2"));
        } catch (JmsException e) {
            fail("Fail to send message.");
        }
    }

}