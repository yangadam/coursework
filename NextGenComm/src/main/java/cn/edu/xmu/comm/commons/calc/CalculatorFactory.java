package cn.edu.xmu.comm.commons.calc;

import cn.edu.xmu.comm.commons.utils.SpringContextHolder;
import cn.edu.xmu.comm.commons.utils.StringUtils;

/**
 * 计算器工厂
 * Created by Roger on 2014/12/8 0008.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
public class CalculatorFactory {

    public static <T> T getCalculator(String className) {
        String beanName = StringUtils.lowerFirst(className);
        return SpringContextHolder.getBean(beanName);
    }

}
