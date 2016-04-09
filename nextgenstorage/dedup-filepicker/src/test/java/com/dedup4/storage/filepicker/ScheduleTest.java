package com.dedup4.storage.filepicker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestConfig.class)
public class ScheduleTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(10L);
        assertThat(outputCapture.toString(), containsString("schedule task executed"));
    }

}
