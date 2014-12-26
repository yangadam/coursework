package cn.edu.xmu.comm.commons.utils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author Mengmeng Yang
 * @version 12/26/2014 0026
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 将首字母转化为小写
     *
     * @param str 字符串
     * @return 首字母小写的字符串
     */
    public static String lowerFirst(String str) {
        if (isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

}
