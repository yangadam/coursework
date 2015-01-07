package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"})
        }
)
public class User extends DataEntity {

    //region Instance Variables
    /**
     * 用户主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * 加密密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 密码盐
     */
    @Column(nullable = false)
    private String salt;

    /**
     * 用户是否被锁定
     */
    @Column(nullable = false)
    private Boolean locked = Boolean.FALSE;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 类型
     */
    @Transient
    private String classType;
    //endregion

    User() {
    }

    public User(String username, String password, String name, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * 判断用户的类型
     *
     * @return 用户类型
     */
    public String getType() {
        return isAdmin() ? "admin" : classType;
    }

    /**
     * 判断用户是否是管理员
     *
     * @return 判断结果
     */
    public Boolean isAdmin() {
        classType = getClass().getSimpleName().toLowerCase();
        String adminType = User.class.getSimpleName().toLowerCase();
        return classType.equals(adminType);
    }

    /**
     * 验证密码
     *
     * @param password 原始密码
     * @return 验证结果
     */
    public Boolean checkPassword(String password) {
        String encryptPwd = SecurityUtils.encrypt(password, getCredentialsSalt());
        return encryptPwd.equals(this.password);
    }

    public Community getCommunity() {
        return null;
    }

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion

}
