package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Staff extends User {

    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Enumerated(EnumType.ORDINAL)
    private StaffType staffType;

    Staff() {
    }

    public Staff(String username, String password, String name, String phoneNumber, String email, Community community, String type) {
        super(username, password, name, phoneNumber, email);
        this.staffType = judgeType(type);
        this.community = community;
    }

    @Override
    public String getType() {
        return staffType.pageResult;
    }

    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    private StaffType judgeType(String type) {
        StaffType[] types = StaffType.values();
        for (StaffType st : types) {
            if (st.getPosition().equals(type)) {
                return st;
            }
        }
        return null;
    }

    public enum StaffType {
        CLERK("文员", "clerk"), GUARD("保安", "guard"), CASHIER("出纳", "cashier");

        private String position;
        private String pageResult;

        private StaffType(String position, String pageResult) {
            this.position = position;
            this.pageResult = pageResult;
        }

        public String getPageResult() {
            return pageResult;
        }

        public String getPosition() {
            return position;
        }
    }

}
