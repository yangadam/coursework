package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User extends DataEntity {

    //region Public Methods

    //region Instance Variables
    private Integer id;
    private String username;
    private String password;
    private String salt;
    //endregion

    //region Constructors
    private Boolean locked = Boolean.FALSE;
    private String name;
    //endregion

    //region Getters
    private String phoneNumber;
    private String email;
    @Transient
    private String classType;

    /**
     * 无参构造函数
     */
    User() {
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
    public User(String username, String password, String name, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * 获取小区 在子类中覆写
     *
     * @return null
     */
    public Community getCommunity() {
        return null;
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
     * 判断用户是否是系统管理员
     *
     * @return 是系统管理员返回true
     */
    public Boolean isAdmin() {
        classType = getClass().getSimpleName().toLowerCase();
        String adminType = User.class.getSimpleName().toLowerCase();
        return classType.equals(adminType);
    }
    //endregion

    /**
     * 验证密码
     *
     * @param password 原始密码
     * @return 验证通过返回true
     */
    public Boolean checkPassword(String password) {
        String encryptPwd = SecurityUtils.encrypt(password, getCredentialsSalt());
        return encryptPwd.equals(this.password);
    }

    /**
     * 获得用户主键
     *
     * @return 用户主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    //region Setters
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得用户名
     *
     * @return 用户名
     */
    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获得加密密码
     *
     * @return 加密密码
     */
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获得密码盐
     *
     * @return 密码盐
     */
    @Column(nullable = false)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    //endregion

    /**
     * 获得用户是否被锁定
     *
     * @return 用户是否被锁定
     */
    @Column(nullable = false)
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 获得姓名
     *
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得手机号码
     *
     * @return 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获得邮箱地址
     *
     * @return 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }
    //endregion

}
