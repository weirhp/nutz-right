package com.weirhp.domain.common;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

@Table("user_role")
public class UserRole extends BaseObject {
    private static final long serialVersionUID = 53453451L;
    @Id
    private Long              id;
    @Column("user_id")
    private Long              userId;
    @Column("role_id")
    private Long              roleId;
    
    private User              user;
    private Role              role;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
}
