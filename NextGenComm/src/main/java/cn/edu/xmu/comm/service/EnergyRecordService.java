package cn.edu.xmu.comm.service;


import cn.edu.xmu.comm.dao.EnergyRecordDAO;
import cn.edu.xmu.comm.domain.EnergyRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Yummy on 11/29/2014 0029.
 */
@Transactional
@Service("energyRecordService")
public class EnergyRecordService {

    @Resource
    private EnergyRecordDAO energyRecordDAO;

    public void addEnergyRecord(EnergyRecord energyRecord) {
        energyRecordDAO.addEnergyRecord(energyRecord);
    }

    public List<EnergyRecord> getAllEnergyRecords() {
        return energyRecordDAO.getAllEnergyRecord();
    }

}
