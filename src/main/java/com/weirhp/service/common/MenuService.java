package com.weirhp.service.common;

import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.weirhp.domain.common.Menu;
import com.weirhp.domain.common.User;

public interface MenuService {
    
    List<Menu> findMenus(Menu conMenu, Pager pager);
    
    Menu fetchMenu(Long id);
    
    List<Menu> findMenusForAdmin(User user);
    
    List<Menu> findMenus();
    
    boolean saveMenus(List<Map<String, Object>> menus, Long[] delMenus);

    /**
     * 查询角色所拥有的菜单项
     * @param roleId
     * @return
     */
    List<Menu> menusForRole(Long roleId);

    boolean saveRoleMenus(Long roleId, Long[] mIds);

    /**
     * 用于显示用户可以操作的菜单
     * @param roleId
     * @return
     */
    List<Menu> menusForRoleShow(Long roleId);
}
