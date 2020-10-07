package cn.edu.xmu.comm.commons.utils;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public class SessionUtils {

    /**
     * 获取Session中存入的用户
     *
     * @return 用户
     */
    public static User getUser() {
        return (User) get(Constants.USER);
    }

    /**
     * 获取Session中存入的小区
     *
     * @return 小区
     */
    public static Community getCommunity() {
        return (Community) get(Constants.COMMUNITY);
    }

    /**
     * 将用户存入Session
     *
     * @param user 用户
     */
    public static void putUser(User user) {
        put(Constants.USER, user);
    }

    /**
     * 将小区存入Session
     *
     * @param community 小区
     */
    public static void putCommunity(Community community) {
        put(Constants.COMMUNITY, community);
    }

    /**
     * 通过键获取Session中存入的对象
     *
     * @param key 键
     * @return 对象
     */
    private static Object get(String key) {
        return ActionContext.getContext().getSession().get(key);
    }

    /**
     * 将对象存入Session
     *
     * @param key   键
     * @param value 值
     */
    private static void put(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }

}
