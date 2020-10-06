package cn.edu.xmu.comm.commons.utils;

import cn.edu.xmu.comm.commons.calc.impl.AreaManageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.impl.DateOverdueFineCalculator;
import cn.edu.xmu.comm.commons.calc.impl.FixGarbageFeeCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/7/2015 0007
 */
public class FeeTypeUtils {

    private static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("固定(元)", FixGarbageFeeCalculator.class.getSimpleName());
        map.put("按面积(元/平方米)", AreaManageFeeCalculator.class.getSimpleName());
        map.put("按滞纳天数(元)", DateOverdueFineCalculator.class.getSimpleName());
    }

    public static String getFeeType(String s) {
        return map.get(s);
    }

}
