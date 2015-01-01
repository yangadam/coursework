package cn.edu.xmu.comm.commons.utils;

import cn.edu.xmu.comm.entity.Staff;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 */
public class StaffTypeUtils {

    public String[] getStaffType() {
        Staff.StaffType[] types = Staff.StaffType.values();
        String[] strings = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            strings[i] = types[i].getPosition();
        }
        return strings;
    }

}
