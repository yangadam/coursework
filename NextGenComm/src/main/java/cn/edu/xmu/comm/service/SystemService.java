package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.PasswordIncorrectException;
import cn.edu.xmu.comm.commons.exception.UserNotFoundException;
import cn.edu.xmu.comm.entity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface SystemService {
    /**
     * 记住我登陆
     *
     * @param tokenStr token字符串，Cookie的value
     * @return 登录用户
     */
    User rememberMeLogin(String tokenStr);

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户
     * @throws cn.edu.xmu.comm.commons.exception.UserNotFoundException      没找到用户
     * @throws cn.edu.xmu.comm.commons.exception.PasswordIncorrectException 密码不正确
     * @see cn.edu.xmu.comm.commons.exception.UserNotFoundException
     * @see cn.edu.xmu.comm.commons.exception.PasswordIncorrectException
     */
    @Transactional(readOnly = false)
    User login(String username, String password)
            throws UserNotFoundException, PasswordIncorrectException;

    /**
     * 为用户生成用于记住我的临时密码，存入Cookie，Key="COMM",value=返回值
     *
     * @param user 用户
     * @return 临时密码
     */
    @Transactional(readOnly = false)
    String makeRememberMeToken(User user);

    /**
     * 用户登出
     *
     * @param user 用户
     */
    @Transactional(readOnly = false)
    void logout(User user);
}
