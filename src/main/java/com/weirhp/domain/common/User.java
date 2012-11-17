package com.weirhp.domain.common;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

@Table("user")
public class User extends BaseObject {
    
    private static final long serialVersionUID = 343431L;
    
    public User() {
    }
    
    public User(String name, String passwd) {
        this.name = name;
        this.setPassword(passwd);
    }
    
    @Id
    private Long             id;
    
    @Name
    @Column
    private String           name;
    
    @Column("nick_name")
    private String           nickName;
    
    @Column
    private String password;
    
    private boolean          locked;
    
    private List<Role>       roles;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public boolean isSysAdmin() {
        if (this.roles != null) {
            for (int i = 0; i < roles.size(); i++) {
                if (roles.get(i) != null && "admin".equals(roles.get(i).getCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}
