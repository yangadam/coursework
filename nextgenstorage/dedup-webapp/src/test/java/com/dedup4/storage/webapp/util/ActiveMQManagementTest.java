package com.dedup4.storage.webapp.util;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created on Feb 27, 2016
 *
 * @author Yang Mengmeng
 */
@Ignore
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
        assertThat(queues.size(), is(equalTo(size + 1)));

        management.removeQueue("queue.a");
        queues = management.getQueues();
        assertThat(queues.size(), is(equalTo(size)));
    }

    @Test
    public void testGetTopics() {
        List<TopicViewMBean> topics = management.getTopics();
        int size = topics.size();

        management.addTopic("topic.a");
        topics = management.getTopics();
        assertThat(topics.size(), is(equalTo(size + 1)));

        management.removeTopic("topic.a");
        topics = management.getTopics();
        assertThat(topics.size(), is(equalTo(size)));
    }

}
