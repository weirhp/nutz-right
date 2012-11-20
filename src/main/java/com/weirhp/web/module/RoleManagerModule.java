package com.weirhp.web.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.weirhp.domain.common.Role;
import com.weirhp.service.common.RoleManagerService;
import com.weirhp.service.common.UserService;

/**
 * 角色相关的处理
 * @author weirhp@gmail.com
 *
 */
@At("/role_mgr")
@IocBean
public class RoleManagerModule {
    
    @Inject
    private RoleManagerService roleService;
    @Inject
    private UserService userService;
    
    @At("/list")
    @Ok("jsp:jsps.admin.role.list")
    public void list(HttpServletRequest request, @Param("::page.") Pager pager, @Param("::role.") Role role) {
        if (pager == null) {
            pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(10);
        }
        if (role == null) {
            role = new Role();
        }
        List<Role> list = roleService.findRoles(role, pager);
        request.setAttribute("roles", list);
        request.setAttribute("pager", pager);
        request.setAttribute("role", role);
    }
    
    @At("/listForUserSelect")
    @Ok("jsp:jsps.admin.role.listForUserSelect")
    public void listForUserSelect(HttpServletRequest request, @Param("::page.") Pager pager,
            @Param("::role.") Role role, @Param("userId") Long userId) {
        if (pager == null) {
            pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(10);
        }
        if (role == null) {
            role = new Role();
        }
        List<Role> list = roleService.findRolesForUserSelect(userId, role, pager);
        request.setAttribute("roles", list);
        request.setAttribute("pager", pager);
        request.setAttribute("role", role);
        request.setAttribute("userId", userId);
        request.setAttribute("selectUser", userService.fetchUser(userId));
    }
    
    @At("/listForUserSelected")
    @Ok("jsp:jsps.admin.role.listForUserSelected")
    public void listForUserSelected(HttpServletRequest request, @Param("::page.") Pager pager,
            @Param("::role.") Role role, @Param("userId") Long userId) {
        if (pager == null) {
            pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(10);
        }
        if (role == null) {
            role = new Role();
        }
        List<Role> list = roleService.findRolesForUserSelected(userId, role, pager);
        request.setAttribute("roles", list);
        request.setAttribute("pager", pager);
        request.setAttribute("role", role);
        request.setAttribute("userId", userId);
    }
    
    /**
     * 保存用户选择的角色
     * 
     * @param request
     * @param role
     * @param userId
     */
    @At("/saveUserSelectRoles")
    @Ok("json")
    public Map<String, Object> saveUserSelectRoles(HttpServletRequest request, @Param("roleIds") long[] roleIds,
            @Param("userId") Long userId) {
        boolean re = roleService.saveUserSelectRoles(userId, roleIds);
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("success", re);
        return reMap;
    }
    
    @At("/deleteRoleFromUser")
    @Ok("json")
    public Map<String, Object> deleteRoleFromUser(HttpServletRequest request, @Param("roleId") Long roleId,
            @Param("userId") Long userId) {
        boolean re = roleService.deleteRoleFromUser(userId, roleId);
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("success", re);
        return reMap;
    }
    
    @At("/toEdit")
    @Ok("jsp:jsps.admin.role.editRole")
    public void add(HttpServletRequest request, @Param("id") Long id) {
        if (id != null && !id.equals(0)) {
            Role role = roleService.fetchRole(id);
            request.setAttribute("role", role);
            request.setAttribute("message", "保存成功！");
        }
    }
    
    @At("/save")
    @Ok("jsp:jsps.admin.role.editRole")
    public void editRole(HttpServletRequest request, @Param("::role.") Role role) {
        if (role != null) {
            role = roleService.save(role);
            request.setAttribute("role", role);
        }
    }
    
    @At("/delete")
    @Ok("json")
    public Map<String, Object> delete(HttpServletRequest request, @Param("id") Long id) {
        Map<String, Object> json = new HashMap<String, Object>();
        if (id != null) {
            boolean re = roleService.delete(id);
            json.put("success", re);
        }
        return json;
    }
}
