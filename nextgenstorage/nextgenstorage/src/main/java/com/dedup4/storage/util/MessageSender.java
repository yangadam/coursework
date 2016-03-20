package com.dedup4.storage.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * MessageSender send message to queue on the activeMQ server.
 *
 * @author Yang Mengmeng Created on Feb 25, 2016
 */
@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Send text to default destination
     *
     * @param text text message
     */
    public void send(String text) throws JmsException {
        jmsTemplate.send(new ActiveMQQueue("queue.default"), session -> session.createTextMessage(text));
    }

    /**
     * Send text message to a specified destination
     *
     * @param dest destination
     * @param text text message
     */
    public void send(Destination dest, final String text) throws JmsException {
        jmsTemplate.send(dest, session -> session.createTextMessage(text));
    }

}
