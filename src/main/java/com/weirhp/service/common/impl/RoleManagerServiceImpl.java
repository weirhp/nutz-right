package com.weirhp.service.common.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.weirhp.domain.common.Operation;
import com.weirhp.domain.common.Role;
import com.weirhp.domain.common.RoleMenu;
import com.weirhp.domain.common.UserRole;
import com.weirhp.service.common.RoleManagerService;
import com.weirhp.util.HDBUtils;
import com.weirhp.util.HReflects;

@IocBean(name = "roleService", fields = { "dao" })
public class RoleManagerServiceImpl extends IdEntityService<Role> implements RoleManagerService {
    
    @Override
    public Map<String, Object> initOperation(Map<String, Method> methods) {
        Map<String, Operation> mapByMethod = new HashMap<String, Operation>();
        long updateCount = 0;
        long insertCount = 0;
        if (methods != null && methods.size() > 0) {
            for (Entry<String, Method> entry : methods.entrySet()) {
                String path = entry.getKey();
                Method method = entry.getValue();
                Operation opt = new Operation();
                opt.setClassName(method.getDeclaringClass().getName());
                opt.setPath(path);
                opt.setMethodName(method.getName());
                opt.setUniqueCode(HReflects.getMethodIdentity(method));
                // mapByPath.put(path, opt);
                mapByMethod.put(opt.getUniqueCode(), opt);
            }
        }
        List<Operation> listMethods = dao().query(Operation.class, null);
        Map<String, Operation> oldMethodMap = new HashMap<String, Operation>();
        if (listMethods != null && listMethods.size() > 0) {
            for (Operation opt : listMethods) {
                if (mapByMethod.containsKey(opt.getUniqueCode())) {
                    Operation nowOpt = mapByMethod.get(opt.getUniqueCode());
                    if (!nowOpt.getPath().equals(opt.getPath())) {
                        nowOpt.setId(opt.getId());
                        dao().update(nowOpt);// 更新旧的path
                        updateCount++;
                    }
                    oldMethodMap.put(opt.getUniqueCode(), opt);
                } else {
                    dao().delete(opt);//删除该operation
                    Sql sql = Sqls.create("delete from menu_operation where operation_id=@operationId");//删除相应的菜单对应的数据
                    sql.params().set("operationId", opt.getId());
                }
            }
            
        }
        // 追加新的操作 actions
        for (Entry<String, Operation> entry : mapByMethod.entrySet()) {
            String key = entry.getKey();
            Operation nowOpt = entry.getValue();
            if (!oldMethodMap.containsKey(key)) {
                dao().insert(nowOpt);
                insertCount++;
            }
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("updateCount", updateCount);
        msg.put("insertCount", insertCount);
        return msg;
    }
    
    @Override
    public Role save(Role role) {
        if (role.getId() == null || role.getId().longValue() == 0) {
            dao().insert(role);
        } else {
            dao().update(role);
        }
        return role;
    }
    
    @Override
    public boolean delete(Long id) {
        if (id != null && id.longValue() != 0) {
            dao().delete(Role.class, id);
        }
        return true;
    }
    
    @Override
    public List<Role> findRoles(Role role, Pager pager) {
        Sql sql = Sqls.queryEntity("select w.* from role w where name like @name");
        String likeName = HDBUtils.getLikeSqlForMysql(role.getName());
        sql.params().set("name", likeName);
        sql.setPager(pager);
        
        Sql sql2 = Sqls.fetchLong("select count(*) from menu w where name like @name");
        sql2.params().set("name", likeName);
        dao().execute(sql2);
        pager.setRecordCount(sql2.getInt());
        sql.setEntity(dao().getEntity(Role.class));
        dao().execute(sql);
        return sql.getList(Role.class);
    }
    
    @Override
    public boolean editRoleMenu(Role role) {
        if (role == null) {
            return false;
        }
        List<RoleMenu> roleMenus = role.getRoleMenus();
        Sql deleteSql = Sqls.create("delete from role_menu where role_id=@id");
        deleteSql.params().set("id", role.getId());
        dao().execute(deleteSql);
        if (roleMenus != null) {
            for (RoleMenu rm : roleMenus) {
                rm.setRoleId(role.getId());
                dao().insert(rm);
            }
        }
        return true;
    }
    
    @Override
    public Role fetchRole(Long id) {
        return dao().fetch(Role.class, id);
    }
    
    @Override
    public List<Role> findRolesForUserSelect(Long userId, Role role, Pager pager) {
        Sql sql = Sqls
                .queryEntity("select w.* from role w left join (select * from user_role where user_id=@userId) ur on ur.role_id = w.id  where ur.user_id is null and w.name like @name");
        String likeName = HDBUtils.getLikeSqlForMysql(role.getName());
        sql.params().set("name", likeName).set("userId", userId);
        sql.setPager(pager);
        
        Sql sql2 = Sqls
                .fetchLong("select w.* from role w left join (select * from user_role where user_id=@userId) ur on ur.role_id = w.id  where ur.user_id is null and w.name like @name");
        sql2.params().set("name", likeName).set("userId", userId);
        dao().execute(sql2);
        pager.setRecordCount(sql2.getInt());
        sql.setEntity(dao().getEntity(Role.class));
        dao().execute(sql);
        return sql.getList(Role.class);
    }
    
    @Override
    public List<Role> findRolesForUserSelected(Long userId, Role role, Pager pager) {
        Sql sql = Sqls
                .queryEntity("select w.* from role w inner join (select * from user_role where user_id=@userId) ur on ur.role_id = w.id  where w.name like @name");
        String likeName = HDBUtils.getLikeSqlForMysql(role.getName());
        sql.params().set("name", likeName).set("userId", userId);
        sql.setPager(pager);
        
        Sql sql2 = Sqls
                .fetchLong("select w.* from role w inner join (select * from user_role where user_id=@userId) ur on ur.role_id = w.id  where w.name like @name");
        sql2.params().set("name", likeName).set("userId", userId);
        dao().execute(sql2);
        pager.setRecordCount(sql2.getInt());
        sql.setEntity(dao().getEntity(Role.class));
        dao().execute(sql);
        return sql.getList(Role.class);
    }
    
    @Override
    public boolean saveUserSelectRoles(Long userId, long[] roleIds) {
        if (roleIds != null && roleIds.length > 0) {
            Sql sql = Sqls.create("insert into user_role(user_id,role_id) values(@userId,@roleId)");
            for (long roleId : roleIds) {
                int count = dao().count(UserRole.class, Cnd.where("userId", "=", userId).and("roleId", "=", roleId));
                if (count == 0) {
                    sql.params().set("userId", userId);
                    sql.params().set("roleId", roleId);
                    sql.addBatch();
                }
            }
            dao().execute(sql);
        }
        return true;
    }
    
    @Override
    public boolean deleteRoleFromUser(Long userId, Long roleId) {
        if (userId != null && roleId != null) {
            Sql sql = Sqls.create("delete from user_role where user_id=@userId and role_id=@roleId");
            sql.params().set("userId", userId).set("roleId", roleId);
            dao().execute(sql);
        }
        return true;
    }
    
}
