package com.weirhp.domain.common;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

@Table("role")
public class Role extends BaseObject{
    
    private static final long serialVersionUID = -44554333443783L;

    @Id
    private Long           id;
    
    @Name
    @Comment("角色编码")
    private String         code;
    
    @Column
    @Comment("角色称呼")
    private String         name;
    
    @Many(field = "roleId", key = "id", target = RoleMenu.class)
    private List<RoleMenu> roleMenus;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<RoleMenu> getRoleMenus() {
        return roleMenus;
    }
    
    public void setRoleMenus(List<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
