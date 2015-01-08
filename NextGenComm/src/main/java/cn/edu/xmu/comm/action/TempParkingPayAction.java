package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.ParkingBill;
import cn.edu.xmu.comm.service.ParkingService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yiu-Wah WONG on 2015/1/3.
 *
 * @version 2015/1/3
 */
@Controller
public class TempParkingPayAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Integer parkBillId;
    private String ownerName;
    private String ownerPhone;
    private Date carInTime;
    private Date carOutTime;
    private BigDecimal fee;
    private String license;

    @Override
    public String execute() {
        ParkingBill parkingBill = parkingService.finishParkBill(parkBillId);
        ownerName = parkingBill.getOwner().getName();
        ownerPhone = parkingBill.getOwner().getPhoneNumber();
        carInTime = parkingBill.getStartTime();
        carOutTime = parkingBill.getEndTime();
        license = parkingBill.getLicense();
        fee = parkingBill.getFee();
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
