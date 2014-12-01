package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.domain.EnergyRecord;
import cn.edu.xmu.comm.service.EnergyRecordService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yummy on 11/29/2014 0029.
 */
@Controller
public class EnergyRecordAction extends ActionSupport {


    @Resource
    private EnergyRecordService energyRecordService;

    private List<EnergyRecord> energyRecords;

    private EnergyRecord energyRecord;


    public String addEnergyRecord() {
        energyRecordService.addEnergyRecord(energyRecord);
        return SUCCESS;
    }

    public String listEnergyRecord() {
        energyRecords = energyRecordService.getAllEnergyRecords();
        return SUCCESS;
    }


    //========== Getters and setters ==========
    public List<EnergyRecord> getEnergyRecords() {
        return energyRecords;
    }

    public void setEnergyRecords(List<EnergyRecord> energyRecords) {
        this.energyRecords = energyRecords;
    }

    public EnergyRecord getEnergyRecord() {
        return energyRecord;
    }

    public void setEnergyRecord(EnergyRecord energyRecord) {
        this.energyRecord = energyRecord;
    }

}
