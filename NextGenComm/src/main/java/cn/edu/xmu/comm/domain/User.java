package cn.edu.xmu.comm.domain;

import javax.persistence.*;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Column(nullable = false)
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
     * 姓名
     */
    private String name;
    //endregion

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

}
