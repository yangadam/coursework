package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.entity.Staff;
import cn.edu.xmu.comm.repository.CommunityRepository;
import cn.edu.xmu.comm.repository.DirectorRepository;
import cn.edu.xmu.comm.repository.StaffRepository;
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

    @Resource
    private DirectorRepository directorRepository;
    @Resource
    private StaffRepository staffRepository;
    @Resource
    private CommunityRepository communityRepository;

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
        Community community = communityRepository.findByName(commName);
        Director director = new Director(username, password, name, phoneNumber, email);
        community.assignDirector(director);
        SecurityUtils.encryptUser(director);
        directorRepository.save(director);
        communityRepository.save(community);
        return director;
    }

    /**
     * 获得所有物业主任
     *
     * @return 物业主任列表
     */
    @Override
    @Required(name = "admin")
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
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
        staffRepository.save(staff);
        communityRepository.save(community);
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
        return staffRepository.findByCommunity(community);
    }
}
