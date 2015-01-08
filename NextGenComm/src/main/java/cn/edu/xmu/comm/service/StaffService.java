package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.entity.Staff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工管理模块Service接口
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface StaffService {
    /**
     * 添加物业主任
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话号码
     * @param email       邮箱
     * @param commName    小区名
     * @return 物业主任
     */
    @Required(name = "admin")
    @Transactional(readOnly = false)
    Director addDirector(String username, String password, String name, String phoneNumber,
                         String email, String commName);

    /**
     * 获得所有物业主任
     *
     * @return 物业主任列表
     */
    @Required(name = "admin")
    List<Director> getAllDirectors();

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
    @Required(name = "director")
    @Transactional(readOnly = false)
    Staff addStaff(String username, String password, String name, String phoneNumber, String email, Community community, String type);

    /**
     * 获取所有员工
     *
     * @return 员工列表
     */
    @Required(name = "director")
    List<Staff> getAllStaff();
}
