package com.dedup4.storage.util;

import com.dedup4.storage.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yang Mengmeng Created on Mar 12, 2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class MessageReceiverTest {

    @Test
    public void testOnMessage() throws Exception {
        Thread.sleep(60000);
    }
}