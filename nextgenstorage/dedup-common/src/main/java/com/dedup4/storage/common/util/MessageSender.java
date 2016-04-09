package com.dedup4.storage.common.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * MessageSender send message to queue on the activeMQ server.
 *
 * @author Yang Mengmeng Created on Feb 25, 2016
 */
public class MessageSender {

    private JmsTemplate jmsTemplate;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Send text message to a specified destination
     *
     * @param dest destination
     * @param text text message
     * @throws JmsException
     */
    public void send(String dest, String text) throws JmsException {
        jmsTemplate.send(new ActiveMQQueue(dest), session -> session.createTextMessage(text));
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
