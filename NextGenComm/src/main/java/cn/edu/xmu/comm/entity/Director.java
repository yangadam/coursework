package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * 物业主任
 *
 * @author Mengmeng Yang
 * @version 12/30/2014 0030
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Director extends User {

    @OneToOne(targetEntity = Community.class, mappedBy = "director")
    private Community community;

    /**
     * 无参构造函数
     */
    Director() {
    }

    /**
     * 构造函数
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话
     * @param email       邮箱
     */
    public Director(String username, String password, String name, String phoneNumber, String email) {
        super(username, password, name, phoneNumber, email);
    }

    /**
     * 获得所属小区
     *
     * @return 所属小区
     */
    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

}
