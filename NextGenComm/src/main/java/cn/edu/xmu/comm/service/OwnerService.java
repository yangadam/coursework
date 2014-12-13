package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.domain.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Service
@Transactional
public class OwnerService {

    @Resource
    private OwnerDAO ownerDAO;

    /**
     * 添加业主
     *
     * @param owner
     */
    public void addOwner(Owner owner) {
        ownerDAO.saveOrUpdate(owner);
    }

    /**
     * 根据Id获得业主
     *
     * @param id
     * @return
     */
    public Owner getById(Integer id) {
        return (Owner) ownerDAO.currentSession().get(Owner.class, id);
    }

    /**
     * 获得所有业主
     *
     * @return
     */
    public List<Owner> getAll() {
        return ownerDAO.getAll();
    }

}
