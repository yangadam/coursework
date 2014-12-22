package cn.edu.xmu.comm.commons.exception;

/**
 * 没有找到用户异常
 *
 * @author Mengmeng Yang
 * @version 12/21/2014
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}