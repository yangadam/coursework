package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.DirectorDAO;
import cn.edu.xmu.comm.dao.StaffDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.entity.Staff;
import cn.edu.xmu.comm.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Service
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

    //region DAO
    @Resource
    private DirectorDAO directorDAO;
    @Resource
    private StaffDAO staffDAO;
    @Resource
    private CommunityDAO communityDAO;
    //endregion

    /**
     * 添加物业主任
     *
     * @param username  用户名
     * @param password  密码
     * @param name      姓名
     * @param community 小区
     * @return 物业主任
     */
    @Override
    @Transactional(readOnly = false)
    public Director addDirector(String username, String password, String name, String phoneNumber, String email, Community community) {
        Director director = new Director(username, password, name, phoneNumber, email);
        community.assignDirector(director);
        SecurityUtils.encryptUser(director);
        directorDAO.persist(director);
        communityDAO.merge(community);
        return director;
    }

    /**
     * 获得所有物业主任
     *
     * @return 物业主任列表
     */
    @Override
    public List<Director> getAllDirectors() {
        return directorDAO.getAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Staff addStaff(String username, String password, String name, String phoneNumber, String email, Community community, String type) {
        Staff staff = new Staff(username, password, name, phoneNumber, email, community, type);
        SecurityUtils.encryptUser(staff);
        staffDAO.persist(staff);
        communityDAO.merge(community);
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        Community community = SessionUtils.getCommunity();
        return staffDAO.getAll(community);
    }

}
