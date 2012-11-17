package com.weirhp.domain.common;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.weirhp.domain.BaseObject;

@Table("menu_operation")
public class MenuOperation extends BaseObject{
    
    private static final long serialVersionUID = 14444444L;
    @Id
    private Long id;
    @Column("menu_id")
    private Long menuId;
    @Column("operation_id")
    private Long operationId;
    
    private Menu menu;
    private Operation operation;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getOperationId() {
        return operationId;
    }
    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
}
