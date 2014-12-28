package cn.edu.xmu.comm;

import cn.edu.xmu.comm.commons.persistence.IntegerComparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Roger on 2014/12/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class javaTest {
    @Test
    public void testTreeMap() {
        Map<Integer, BigDecimal> map = new TreeMap<Integer, BigDecimal>(new IntegerComparator());
        map.put(90, BigDecimal.valueOf(10));
        map.put(150, BigDecimal.valueOf(15));
        map.put(210, BigDecimal.valueOf(20));
        map.put(30, BigDecimal.valueOf(5));

        for(Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.print(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
