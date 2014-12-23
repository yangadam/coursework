package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.PasswordIncorrectException;
import cn.edu.xmu.comm.commons.exception.UserNotFoundException;
import cn.edu.xmu.comm.commons.security.SecurityUtil;
import cn.edu.xmu.comm.dao.TokenDAO;
import cn.edu.xmu.comm.dao.UserDAO;
import cn.edu.xmu.comm.entity.Token;
import cn.edu.xmu.comm.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 系统模块Service
 *
 * @author Mengmeng Yang
 * @version 2014-12-21
 */
@Service
@Transactional(readOnly = true)
public class SystemService {

    @Resource
    private UserDAO userDAO;

    @Resource
    private TokenDAO tokenDAO;


    /**
     * 记住我登陆
     *
     * @param tokenStr token字符串，Cookie的value
     * @return 登录用户
     */
    public User rememberMeLogin(String tokenStr) {
        Token token = tokenDAO.get(tokenStr);
        return userDAO.get(token.getId());
    }

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户
     * @throws UserNotFoundException      没找到用户
     * @throws PasswordIncorrectException 密码不正确
     * @see UserNotFoundException
     * @see PasswordIncorrectException
     */
    public User login(String username, String password)
            throws UserNotFoundException, PasswordIncorrectException {
        User user = userDAO.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户名不正确！");
        }
        if (Boolean.FALSE.equals(user.checkPassword(password))) {
            clearRememberMeToken(user.getId());
            throw new PasswordIncorrectException("密码不正确");
        }
        return user;
    }

    /**
     * 为用户生成用于记住我的临时密码，存入Cookie，Key="COMM",value=返回值
     *
     * @param user 用户
     * @return 临时密码
     */
    @Transactional(readOnly = false)
    public String makeRememberMeToken(User user) {
        String tokenStr = SecurityUtil.generateSalt(36);
        Token token = new Token(tokenStr, user.getId());
        tokenDAO.persist(token);
        return token.getToken();
    }

    /**
     * 清除临时密码
     */
    @Transactional(readOnly = false)
    private int clearRememberMeToken(Integer uid) {
        return tokenDAO.deleteByUid(uid);
    }

}
