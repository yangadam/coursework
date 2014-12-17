package cn.edu.xmu.comm.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yummy on 2014/12/14.
 */
@Entity
public class Role {

    //region Instance Variables
    /**
     * 角色主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 用户集合
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, targetEntity = User.class)
    private Set<User> users = new HashSet<User>();

    /**
     * 权限集合
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Permission.class)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<Permission>();
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    //endregion

}
