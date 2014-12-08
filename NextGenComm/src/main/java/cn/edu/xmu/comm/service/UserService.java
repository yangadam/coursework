package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Roger on 2014/12/8 0008.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private OwnerDAO ownerDAO;


    public void addOwner(Owner owner) {
        ownerDAO.saveOrUpdate(owner);
    }

}
