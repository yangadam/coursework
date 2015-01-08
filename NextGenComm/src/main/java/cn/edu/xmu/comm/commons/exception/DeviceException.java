package cn.edu.xmu.comm.commons.exception;

import java.util.Date;

/**
 * Created by Roger on 2014/12/31 0031.
 * @version 2014/12/31 0031
 */
public class DeviceException extends Exception {

    Date date;

    public DeviceException() {
        super();
    }

    public DeviceException(String message) {
        super(message);
        this.date = new Date();
    }

    public DeviceException(String message, Date date) {
        super(message);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
