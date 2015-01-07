package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

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

    /**
     * 所属小区
     */
    @OneToOne(targetEntity = Community.class, mappedBy = "director")
    private Community community;

    Director() {
    }

    public Director(String username, String password, String name, String phoneNumber, String email) {
        super(username, password, name, phoneNumber, email);
    }

    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
