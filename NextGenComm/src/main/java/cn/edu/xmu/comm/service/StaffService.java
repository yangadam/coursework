package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.entity.Staff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface StaffService {
    /**
     * 添加物业主任
     *
     * @param username  用户名
     * @param password  密码
     * @param name      姓名
     * @param community 小区
     * @return 物业主任
     */
    @Transactional(readOnly = false)
    Director addDirector(String username, String password, String name, Community community);

    /**
     * 获得所有物业主任
     *
     * @return 物业主任列表
     */
    List<Director> getAllDirectors();

    @Transactional(readOnly = false)
    Staff addStaff(String username, String password, String name, Community community, String type);

    List<Staff> getAllStaff();

}
