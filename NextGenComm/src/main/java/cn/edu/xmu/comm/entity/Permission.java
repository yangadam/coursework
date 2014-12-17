package cn.edu.xmu.comm.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yummy on 2014/12/14.
 */
@Entity
public class Permission {

    //region Instance Variables
    /**
     * 权限主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean available = Boolean.FALSE;

    /**
     * @return
     */
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY, targetEntity = Role.class)
    private Set<Role> roles = new HashSet<Role>();
    //endregion

    //region Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    //endregion

}
