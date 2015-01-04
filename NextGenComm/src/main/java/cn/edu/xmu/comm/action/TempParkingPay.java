package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yiu-Wah WONG on 2015/1/3.
 */
@Controller
public class TempParkingPay extends ActionSupport {

    @Resource
    private CarService carService;

    private ParkBill parkBill;

    private Integer parkBillId;
    private String ownerName;
    private String ownerPhone;
    private Date carInTime;
    private Date carOutTime;
    private BigDecimal fee;
    private String license;

    public String execute() {

        parkBill = carService.getParkBill(parkBillId);
        ownerName = parkBill.getOwner().getName();
        ownerPhone = parkBill.getOwner().getPhoneNumber();
        carInTime = parkBill.getStartTime();
        carOutTime = parkBill.getEndTime();
        license = parkBill.getLicense();
        fee = parkBill.getFee();

        return SUCCESS;
    }

    public Integer getParkBillId() {
        return parkBillId;
    }

    public void setParkBillId(Integer parkBillId) {
        this.parkBillId = parkBillId;
    }

    public Date getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Date carInTime) {
        this.carInTime = carInTime;
    }

    public Date getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(Date carOutTime) {
        this.carOutTime = carOutTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
