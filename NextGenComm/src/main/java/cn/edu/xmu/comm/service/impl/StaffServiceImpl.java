package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
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
 * 员工管理模块Service实现
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Service
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

    //region Director Service

    //region DAO
    @Resource
    private DirectorDAO directorDAO;
    @Resource
    private StaffDAO staffDAO;
    //endregion

    //region Staff Service
    @Resource
    private CommunityDAO communityDAO;

    /**
     * 添加物业主任
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     * @param commName 小区名
     * @return 物业主任
     */
    @Override
    @Required(name = "admin")
    @Transactional(readOnly = false)
    public Director addDirector(String username, String password, String name, String phoneNumber,
                                String email, String commName) {
        Community community = communityDAO.getByName(commName);
        Director director = new Director(username, password, name, phoneNumber, email);
        community.assignDirector(director);
        SecurityUtils.encryptUser(director);
        directorDAO.persist(director);
        communityDAO.merge(community);
        return director;
    }
    //endregion

    /**
     * 获得所有物业主任
     *
     * @return 物业主任列表
     */
    @Override
    @Required(name = "admin")
    public List<Director> getAllDirectors() {
        return directorDAO.getAll();
    }

    /**
     * 添加工作人员
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话
     * @param email       邮箱
     * @param community   小区
     * @param type        类型
     * @return 员工
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public Staff addStaff(String username, String password, String name, String phoneNumber, String email, Community community, String type) {
        Staff staff = new Staff(username, password, name, phoneNumber, email, community, type);
        SecurityUtils.encryptUser(staff);
        staffDAO.persist(staff);
        communityDAO.merge(community);
        return staff;
    }

    /**
     * 获取所有员工
     *
     * @return 员工列表
     */
    @Override
    @Required(name = "director")
    public List<Staff> getAllStaff() {
        Community community = SessionUtils.getCommunity();
        return staffDAO.getAll(community);
    }
    //endregion

}
