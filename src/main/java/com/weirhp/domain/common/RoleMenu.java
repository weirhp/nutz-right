package com.weirhp.domain.common;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

/**
 * 角色对应的菜单
 * 
 * @author weirhp
 * 
 */
@Table("role_menu")
public class RoleMenu extends BaseObject {
    private static final long serialVersionUID = 1755423455435345534L;
    @Id
    private Long              id;
    @Column("role_id")
    private Long               roleId;
    @Column("menu_id")
    private Long               menuId;
    
    private Menu              menu;
    private Role              role;
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public Long getMenuId() {
        return menuId;
    }
    
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
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
