package cn.edu.xmu.comm.commons.exception;

/**
 * 没有权限异常
 *
 * @author Mengmeng Yang
 * @version 12/29/2014 0029
 */
public class NoPermissionException extends Exception {

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

}
