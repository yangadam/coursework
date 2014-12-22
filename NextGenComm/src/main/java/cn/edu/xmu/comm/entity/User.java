package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import cn.edu.xmu.comm.commons.security.SecurityUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @GeneratedValue
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
    private Boolean locked = Boolean.FALSE;

    /**
     * 角色集合
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

    /**
     * 姓名
     */
    private String name;
    //endregion

    public String getType() {
        String type = getClass().getSimpleName().toLowerCase();
        return type == User.class.getSimpleName() ? "admin" : type;
    }

    public boolean checkPassword(String password) {
        return SecurityUtil.encrypt(password, getCredentialsSalt()).equals(this.password);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

}
