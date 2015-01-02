package cn.edu.xmu.comm.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/2/2015 0002
 */
public class CastUtils {

    public static List<String[]> castToListStringArray(List list) {
        List<String[]> ret = new ArrayList<String[]>();
        for (Object aList : list) {
            Object[] objects = (Object[]) aList;
            String[] ints = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                ints[i] = String.valueOf(objects[i].toString());
            }
            ret.add(ints);
        }
        return ret;
    }

}
