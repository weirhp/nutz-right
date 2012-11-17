package com.weirhp.service.common;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.weirhp.domain.common.Role;

public interface RoleManagerService {
    public Map<String,Object> initOperation(Map<String,Method> methods);
    public Role save(Role role);
    public boolean delete(Long id);
    public List<Role> findRoles(Role role, Pager pager);
    public boolean editRoleMenu(Role role);
    public Role fetchRole(Long id);
    
    /**
     * 供用户选择的角色列表
     * @param userId
     * @param role
     * @param pager
     * @return
     */
    public List<Role> findRolesForUserSelect(Long userId, Role role, Pager pager);
    
    /**
     * 查询用户已经选择的角色列表
     * @param userId
     * @param role
     * @param pager
     * @return
     */
    List<Role> findRolesForUserSelected(Long userId, Role role, Pager pager);
    
    /**
     * 保存用户选择的角色
     * @param userId
     * @param roleIds
     * @return
     */
    public boolean saveUserSelectRoles(Long userId, long[] roleIds);
    /**
     * 给用户删除一个角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean deleteRoleFromUser(Long userId, Long roleId);
}
