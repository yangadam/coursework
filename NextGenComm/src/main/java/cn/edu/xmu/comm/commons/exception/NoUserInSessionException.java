package cn.edu.xmu.comm.commons.exception;

/**
 * 用户没有登录异常
 *
 * @author Mengmeng Yang
 * @version 12/29/2014 0029
 */
public class NoUserInSessionException extends Exception {

    public NoUserInSessionException() {
        super();
    }

    public NoUserInSessionException(String message) {
        super(message);
    }

}
