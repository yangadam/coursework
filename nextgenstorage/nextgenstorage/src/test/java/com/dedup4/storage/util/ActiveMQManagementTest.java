package com.dedup4.storage.util;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on Feb 27, 2016
 *
 * @author Yang Mengmeng
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring-context-jms.xml" })
public class ActiveMQManagementTest {

    private ActiveMQManagement management;

    @Before
    public void setUp() {
        management = new ActiveMQManagement();
    }

    @Test
    public void testGetQueues() {
        List<QueueViewMBean> queues = management.getQueues();
        int size = queues.size();

        management.addQueue("queue.a");
        queues = management.getQueues();
        assertEquals(size + 1, queues.size());

        management.removeQueue("queue.a");
        queues = management.getQueues();
        assertEquals(size, queues.size());
    }

    @Test
    public void testGetTopics() {
        List<TopicViewMBean> topics = management.getTopics();
        int size = topics.size();

        management.addTopic("topic.a");
        topics = management.getTopics();
        assertEquals(size + 1, topics.size());

        management.removeTopic("topic.a");
        topics = management.getTopics();
        assertEquals(size, topics.size());
    }

}
