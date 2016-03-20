package com.dedup4.storage.util;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ActiveMQManagement manage the mbean on the activeMQ server.
 *
 * @author Yang Mengmeng Created on Feb 21, 2016
 */
public class ActiveMQManagement {

    private MBeanServerConnection connection;

    private BrokerViewMBean broker;

    public ActiveMQManagement() {
        MBeanServerConnectionFactoryBean factory = new MBeanServerConnectionFactoryBean();
        try {
            factory.setServiceUrl("service:jmx:rmi:///jndi/rmi://139.129.17.212:11099/jmxrmi");
            Map<String, String[]> environment = new HashMap<>();
            environment.put("jmx.remote.credentials", new String[]{"admin", "activemq"});
            factory.setEnvironmentMap(environment);
            factory.afterPropertiesSet();
            connection = factory.getObject();
            ObjectName name = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost");
            broker = MBeanServerInvocationHandler.newProxyInstance(connection, name, BrokerViewMBean.class, true);
        } catch (MalformedObjectNameException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Add a queue on activeMQ server.
     *
     * @param queueName queue name
     */
    public void addQueue(String queueName) {
        try {
            broker.addQueue(queueName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a topic on activeMQ server.
     *
     * @param topicName topic name
     */
    public void addTopic(String topicName) {
        try {
            broker.addTopic(topicName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Remove a queue on activeMQ server by name.
     *
     * @param queueName queue name
     */
    public void removeQueue(String queueName) {
        try {
            broker.removeQueue(queueName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Remove a topic on activeMQ server by name.
     *
     * @param topicName topic name
     */
    public void removeTopic(String topicName) {
        try {
            broker.removeTopic(topicName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Get all queues on activeMQ server.
     *
     * @return list of queue
     */
    public List<QueueViewMBean> getQueues() {
        List<QueueViewMBean> queues = new ArrayList<>();
        for (ObjectName queueName : broker.getQueues()) {
            QueueViewMBean viewMBean = MBeanServerInvocationHandler.newProxyInstance(connection, queueName,
                    QueueViewMBean.class, true);
            queues.add(viewMBean);
        }
        return queues;
    }

    /**
     * Get all topics on activeMQ server
     *
     * @return list of topic
     */
    public List<TopicViewMBean> getTopics() {
        List<TopicViewMBean> topics = new ArrayList<>();
        for (ObjectName topicName : broker.getTopics()) {
            TopicViewMBean viewMBean = MBeanServerInvocationHandler.newProxyInstance(connection, topicName,
                    TopicViewMBean.class, true);
            topics.add(viewMBean);
        }
        return topics;
    }

}
