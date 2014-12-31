package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.DirectorDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
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

    @Resource
    private DirectorDAO directorDAO;

    @Resource
    private CommunityDAO communityDAO;

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
    public Director addDirector(String username, String password, String name, Community community) {
        Director director = new Director(username, password, name);
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

}
