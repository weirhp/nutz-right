package com.weirhp.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.weirhp.domain.common.Menu;
import com.weirhp.domain.common.User;
import com.weirhp.service.common.MenuService;
import com.weirhp.util.HDBUtils;

@IocBean(name = "menuService", fields = { "dao" })
public class MenuServiceImpl extends IdEntityService<Menu> implements MenuService {
    
    @Override
    public List<Menu> findMenus(Menu conMenu, Pager pager) {
        Sql sql = Sqls.queryEntity("select w.* from menu w where name like @name");
        String likeName = HDBUtils.getLikeSqlForMysql(conMenu.getName());
        sql.params().set("name", likeName);
        sql.setPager(pager);
        
        Sql sql2 = Sqls.fetchLong("select count(*) from menu w where name like @name");
        sql2.params().set("name", likeName);
        dao().execute(sql2);
        pager.setRecordCount(sql2.getInt());
        sql.setEntity(dao().getEntity(Menu.class));
        dao().execute(sql);
        return sql.getList(Menu.class);
    }
    
    @Override
    public Menu fetchMenu(Long id) {
        return dao().fetch(Menu.class, id);
    }
    
    @Override
    public List<Menu> findMenusForAdmin(User user) {
        if (user.isSysAdmin()) {
            return findMenusForShow();
        }
        // user = dao().fetchLinks(user, null);
        Sql sql = Sqls.queryEntity("select m.* from menu m inner join (" + " select distinct rm.* from user u "
                + "     inner join user_role ur on u.id = ur.user_id"
                + "     inner join role_menu rm on rm.role_id=ur.role_id"
                + "     where u.id = @id) rm2 on rm2.menu_id = m.id where m.show_menu=1 order by m.indexs");
        sql.params().set("id", user.getId());
        sql.setEntity(dao().getEntity(Menu.class));
        dao().execute(sql);
        return sql.getList(Menu.class);
    }
    
    @Override
    public List<Menu> findMenus() {
        return dao().query(Menu.class, Cnd.orderBy().asc("indexs"));
    }
    
    private List<Menu> findMenusForShow() {
        return dao().query(Menu.class, Cnd.where("show_Menu","=",1).asc("indexs"));
    }
    
    
    @Override
    public boolean saveMenus(final List<Map<String, Object>> menus, final Long[] delMenus) {
        if (menus != null && menus.size() > 0) {
            Trans.exec(java.sql.Connection.TRANSACTION_REPEATABLE_READ, new Atom() {
                public void run() {
                    // 删除
                    if (delMenus != null && delMenus.length > 0) {
                        Sql deleteMenuOpt = Sqls.create("delete from menu_operation where menu_id in($ids)");
                        Sql deleteRoleMenu = Sqls.create("delete from role_menu where menu_id in($ids)");
                        String ids = HDBUtils.join(delMenus, ",");
                        deleteMenuOpt.vars().set("ids", ids);
                        deleteRoleMenu.vars().set("ids", ids);
                        dao().execute(deleteMenuOpt);
                        dao().execute(deleteRoleMenu);
                        Sql deleteMenu = Sqls.create("delete from menu where id in($ids)");
                        deleteMenu.vars().set("ids", ids);
                        dao().execute(deleteMenu);
                    }
                    List<Menu> tmpMenus = covertMapToMenu(menus);
                    // 更新
                    Map<String, Long> menuIds = new HashMap<String, Long>();
                    for (int i = 0; i < tmpMenus.size(); i++) {
                        Menu menu = tmpMenus.get(i);
                        menu.setIndexs(i);
                        if (menu.getpId().matches("^\\d+?$")) {
                            menu.setParentId(Long.parseLong(menu.getpId()));
                        } else {
                            menu.setParentId(menuIds.get(menu.getpId()));
                        }
                        if (menu.getId() != null && menu.getId() > 0) {
                            dao().update(menu);
                        } else {
                            String tmpId = menu.getTmpId();
                            dao().insert(menu);
                            menuIds.put(tmpId, menu.getId());
                        }
                    }
                }
            });
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    private List<Menu> covertMapToMenu(List<Map<String, Object>> maps) {
        List<Menu> menus = new ArrayList<Menu>();
        for (Map<String, Object> map : maps) {
            Menu menu = new Menu();
            String id = String.valueOf(map.get("id"));
            String name = (String) map.get("name");
            String url = (String) map.get("url");
            String show = String.valueOf(map.get("show"));
            String pid = "0";
            if (show.matches("^\\d+?$")) {
                menu.setShowMenu(Integer.parseInt(show));
            }
            if (id.matches("^\\d+?$")) {
                menu.setId(Long.parseLong(id));
                menu.setParentId(0L);
            }
            menu.setTmpId(id);
            
            menu.setpId(pid);
            menu.setName(name);
            menu.setUrl(url);
            menus.add(menu);
            
            List<Map<String, Object>> children = (List<Map<String, Object>>) map.get("children");
            if (children != null) {
                for (Map<String, Object> map2 : children) {
                    covertMapToMenu(menus, map2, menu.getTmpId());
                }
            }
            
        }
        return menus;
    }
    
    @SuppressWarnings("unchecked")
    private void covertMapToMenu(List<Menu> menus, Map<String, Object> map, String pId) {
        Menu menu = new Menu();
        String id = String.valueOf(map.get("id"));
        String name = (String) map.get("name");
        String url = (String) map.get("url");
        String show = String.valueOf(map.get("show"));
        if (show.matches("^\\d+?$")) {
            menu.setShowMenu(Integer.parseInt(show));
        }
        if (id.matches("^\\d+?$")) {
            menu.setId(Long.parseLong(id));
            menu.setParentId(0L);
        }
        menu.setTmpId(id);
        menu.setName(name);
        menu.setUrl(url);
        menu.setpId(pId);
        menus.add(menu);
        List<Map<String, Object>> children = (List<Map<String, Object>>) map.get("children");
        if (children != null) {
            for (Map<String, Object> map2 : children) {
                covertMapToMenu(menus, map2, menu.getTmpId());
            }
        }
    }
    
    @Override
    public List<Menu> menusForRole(Long roleId) {
        if (roleId != null) {
            Sql sql = Sqls
                    .queryEntity("select m.* from  `menu` m inner join (select * from `role_menu` where role_id = @roleId) rm on m.id=rm.menu_id");
            sql.params().set("roleId", roleId);
            sql.setEntity(dao().getEntity(Menu.class));
            dao().execute(sql);
            return sql.getList(Menu.class);
        }
        return null;
    }
    
    /**
     * 保存项目对应的菜单项
     */
    @Override
    public boolean saveRoleMenus(final Long roleId, final Long[] mIds) {
        if (roleId != null) {
            Trans.exec(java.sql.Connection.TRANSACTION_REPEATABLE_READ, new Atom() {
                @Override
                public void run() {
                    Sql sql = Sqls.create("delete from role_menu where role_id = @roleId");
                    sql.params().set("roleId", roleId);
                    dao().execute(sql);
                    Sql insSql = Sqls.create("insert into role_menu(role_id,menu_id) values(@roleId,@menuId)");
                    for (Long mid : mIds) {
                        insSql.params().set("roleId", roleId).set("menuId", mid);
                        insSql.addBatch();
                    }
                    dao().execute(insSql);
                }
            });
        }
        return true;
    }

    @Override
    public List<Menu> menusForRoleShow(Long roleId) {
        if (roleId != null) {
            Sql sql = Sqls
                    .queryEntity("select m.* from  `menu` m inner join (select * from `role_menu` where role_id = @roleId) rm on m.id=rm.menu_id where m.show = 1");
            sql.params().set("roleId", roleId);
            sql.setEntity(dao().getEntity(Menu.class));
            dao().execute(sql);
            return sql.getList(Menu.class);
        }
        return null;
    }
    
}
