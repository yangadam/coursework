package cn.edu.xmu.comm.commons.persistence;

import java.util.Comparator;

/**
 * Created by Roger on 2014/12/27 0027.
 */
public class IntegerComparator implements Comparator<Integer> {

    public int compare(Integer intA, Integer intB){
        // Compare intB to intA, rather than intA to intB.
        return intB.compareTo(intA);
    }

}
