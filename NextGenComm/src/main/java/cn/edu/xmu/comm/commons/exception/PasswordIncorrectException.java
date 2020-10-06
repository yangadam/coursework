package cn.edu.xmu.comm.commons.exception;

/**
 * 密码错误异常
 *
 * @author Mengmeng Yang
 * @version 12/21/2014 0021
 */
public class PasswordIncorrectException extends Exception {

    public PasswordIncorrectException() {
        super();
    }

    public PasswordIncorrectException(String message) {
        super(message);
    }
}
