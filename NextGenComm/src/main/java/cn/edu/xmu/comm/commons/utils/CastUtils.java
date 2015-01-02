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

    public static List<Integer[]> castToListIntegerArray(List list) {
        List<Integer[]> ret = new ArrayList<Integer[]>();
        for (Object aList : list) {
            Object[] objects = (Object[]) aList;
            Integer[] ints = new Integer[objects.length];
            for (int i = 0; i < objects.length; i++) {
                ints[i] = Integer.valueOf(objects[i].toString());
            }
            ret.add(ints);
        }
        return ret;
    }

}
