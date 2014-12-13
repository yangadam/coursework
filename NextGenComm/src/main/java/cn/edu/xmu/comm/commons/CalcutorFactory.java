package cn.edu.xmu.comm.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public class CalcutorFactory {

    static Map<String, IShareCalculator> shareCalcs;
    static Map<String, IManageFeeCalculator> manageFeeCalcs;
    static Map<String, IGarbageFeeCalculator> garbageFeeCalcs;


    static {
        shareCalcs = new HashMap<String, IShareCalculator>();
        manageFeeCalcs = new HashMap<String, IManageFeeCalculator>();
        garbageFeeCalcs = new HashMap<String, IGarbageFeeCalculator>();
    }

    public static IShareCalculator getShareCalc(String className) {
        if (shareCalcs.containsKey(className))
            return shareCalcs.get(className);
        IShareCalculator newInstance = null;
        try {
            newInstance = (IShareCalculator) Class.forName(className).newInstance();
            shareCalcs.put(className, newInstance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    public static IManageFeeCalculator getManageFeeCalc(String className) {
        if (manageFeeCalcs.containsKey(className))
            return manageFeeCalcs.get(className);
        IManageFeeCalculator newInstance = null;
        try {
            newInstance = (IManageFeeCalculator) Class.forName(className).newInstance();
            manageFeeCalcs.put(className, newInstance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    public static IGarbageFeeCalculator getGarbageFeeCalc(String className) {
        if (garbageFeeCalcs.containsKey(className))
            return garbageFeeCalcs.get(className);
        IGarbageFeeCalculator newInstance = null;
        try {
            newInstance = (IGarbageFeeCalculator) Class.forName(className).newInstance();
            garbageFeeCalcs.put(className, newInstance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

}
