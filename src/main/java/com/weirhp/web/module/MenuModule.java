package com.weirhp.web.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.weirhp.domain.common.Menu;
import com.weirhp.service.common.MenuService;

/**
 * 菜单相关操作
 * 
 * @author weirhp@gmail.com
 * 
 */
@IocBean
@Fail("json")
@At("/menu")
public class MenuModule {
    @Inject
    private MenuService menuService;
    
    @At("/list")
    @Ok("jsp:jsps.admin.menu.list")
    public void list(HttpServletRequest request, @Param("::page.") Pager pager, @Param("::menu.") Menu menu) {
        if (pager == null) {
            pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(10);
        }
        if (menu == null) {
            menu = new Menu();
        }
        List<Menu> list = menuService.findMenus(menu, pager);
        request.setAttribute("menus", list);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", menu);
    }
    
    @At("/toEdit")
    @Ok("jsp:jsps.admin.menu.editMenu")
    public void add(HttpServletRequest request, @Param("id") Long id) {
        if (id != null && !id.equals(0)) {
            Menu menu = menuService.fetchMenu(id);
            request.setAttribute("menu", menu);
        }
    }
    
    @At("/toEditByTree")
    @Ok("jsp:jsps.admin.menu.editMenuByTree")
    public void editMenu(HttpServletRequest request) {
    }
    
    @At("/toSetMenuForRole")
    @Ok("jsp:jsps.admin.menu.setMenuForRole")
    public void toSetMenuForRole(HttpServletRequest request,@Param("roleId") Long roleId) {
        request.setAttribute("roleId", roleId);
    }
    
    @SuppressWarnings("unchecked")
    @At("/saveByTree")
    @Ok("json")
    public Map<String, Object> saveMenuTree(HttpServletRequest request, @Param("menus") String menuStr,
            @Param("delMenus") String delMenusStr) {
        List<Map<String, Object>> menus = Json.fromJson(List.class, menuStr);
        Long[] delMenus = Json.fromJson(Long[].class, delMenusStr);
        boolean re = menuService.saveMenus(menus, delMenus);
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("success", re);
        return reMap;
    }
    
    @At("/saveRoleMenus")
    @Ok("json")
    public Map<String, Object> saveRoleMenus(HttpServletRequest request, @Param("menuIds") String menuIds,
            @Param("roleId") Long roleId) {
        Long[] mIds = Json.fromJson(Long[].class, menuIds);
        boolean re = menuService.saveRoleMenus(roleId, mIds);
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("success", re);
        return reMap;
    }
    
    @At
    @Ok("json")
    public List<Menu> menuJson() {
        List<Menu> list = menuService.findMenus();
        return list;
    }
    
    @At
    @Ok("json")
    public List<Menu> menuJsonForRole(HttpServletRequest request,@Param("roleId") Long roleId) {
        List<Menu> list = menuService.menusForRole(roleId);
        return list;
    }
    
    @At
    @Ok("json")
    public List<Menu> menuJsonForRoleShow(HttpServletRequest request,@Param("roleId") Long roleId) {
        List<Menu> list = menuService.menusForRoleShow(roleId);
        return list;
    }
}
