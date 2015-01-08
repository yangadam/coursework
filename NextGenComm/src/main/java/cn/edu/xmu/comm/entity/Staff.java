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

    //region Public Methods

    //region Private Instance Variables
    private Community community;

    //endregion

    //region Constructors
    private StaffType staffType;

    /**
     * 无参构造函数
     */
    Staff() {
    }
    //endregion

    //region Getters

    /**
     * 构造函数
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话
     * @param email       邮箱
     * @param community   小区
     * @param type        类型
     */
    public Staff(String username, String password, String name, String phoneNumber, String email, Community community, String type) {
        super(username, password, name, phoneNumber, email);
        this.staffType = judgeType(type);
        this.community = community;
    }

    /**
     * 获得用户类型
     *
     * @return 用户类型
     */
    @Override
    public String getType() {
        return staffType.pageResult;
    }
    //endregion

    /**
     * 获得小区
     *
     * @return 小区
     */
    @Override
    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    public Community getCommunity() {
        return community;
    }

    //region Setters
    public void setCommunity(Community community) {
        this.community = community;
    }
    //endregion

    //region Inner Enum

    /**
     * 获得员工类型
     *
     * @return 员工类型
     */
    @Enumerated(EnumType.ORDINAL)
    public StaffType getStaffType() {
        return staffType;
    }
    //endregion

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    //region Private Methods
    private StaffType judgeType(String type) {
        StaffType[] types = StaffType.values();
        for (StaffType st : types) {
            if (st.getPosition().equals(type)) {
                return st;
            }
        }
        return null;
    }
    //endregion

    /**
     * 员工类型
     */
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
    //endregion

}
