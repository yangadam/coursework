package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.PasswordIncorrectException;
import cn.edu.xmu.comm.commons.exception.UserNotFoundException;
import cn.edu.xmu.comm.commons.security.SecurityUtil;
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

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
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
        if (user.checkPassword(password)) {
            clearRememberMeToken(user.getId());
            throw new PasswordIncorrectException("密码不正确");
        }
        return user;
    }

    /**
     * 为用户生成用于记住我的临时密码
     *
     * @param user 用户
     * @return 临时密码
     */
    private String makeRememberMeToken(User user) {
        String tokenStr = SecurityUtil.generateSalt(36);
        Token token = new Token(user.getId(), tokenStr);
        return token.getToken();
    }

    private void clearRememberMeToken(Integer id) {

    }

}
