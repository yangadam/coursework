package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Clerk extends User {

    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    Clerk() {
    }

    public Clerk(String username, String password, String name, Community community) {
        super(username, password, name);
        this.community = community;
    }

    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
