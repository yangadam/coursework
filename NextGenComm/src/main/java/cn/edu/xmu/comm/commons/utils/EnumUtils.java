package cn.edu.xmu.comm.commons.utils;

import cn.edu.xmu.comm.entity.Staff;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 */
public class EnumUtils {

    public String[] getStaffType() {
        Staff.StaffType[] types = Staff.StaffType.values();
        String[] strings = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            strings[i] = types[i].getPosition();
        }
        return strings;
    }

    public String[] getGarbageFeeType() {
        return new String[]{"固定(元)"};
    }

    public String[] getManageFeeType() {
        return new String[]{"按面积(元/平方米)"};
    }

    public String[] getOverDueFeeType() {
        return new String[]{"按滞纳天数(元)"};
    }

}
