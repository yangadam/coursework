package cn.edu.xmu.comm.action;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试Action
 *
 * @author Mengmeng Yang
 * @version 12/22/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context*.xml"})
public class ActionTest extends StrutsSpringTestCase {

    @Before
    @Override
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAction() {
        request.addParameter("community.name", "海韵");
        ActionProxy proxy = getActionProxy("/comm.do");
        String result = null;
        try {
            result = proxy.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("success", result);
    }

}
