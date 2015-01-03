package cn.edu.xmu.comm.commons.exception;

import cn.edu.xmu.comm.entity.Owner;

/**
 * Created by Roger on 2015/1/2 0002.
 *
 */
public class MailException extends Exception {

    Owner owner;

    public MailException() {
        super();
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Owner owner) {
        super(message);
        this.owner = owner;
    }
}
