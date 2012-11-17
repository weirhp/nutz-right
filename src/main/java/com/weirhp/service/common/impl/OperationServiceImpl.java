package com.weirhp.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.weirhp.domain.common.MenuOperation;
import com.weirhp.domain.common.Operation;
import com.weirhp.service.common.OperationService;

@IocBean(name = "operationService", fields = { "dao" })
public class OperationServiceImpl extends IdEntityService<Operation> implements OperationService {
    
    @Override
    public List<Operation> findAllOperation() {
        List<Operation> opts = dao().query(Operation.class, Cnd.orderBy().asc("className").asc("methodName"));
        return opts;
    }
    
    @Override
    public List<Map<String, Object>> findAllOperationForTree() {
        List<Map<String, Object>> optList = new ArrayList<Map<String, Object>>();
        List<Operation> opts = findAllOperation();
        long classCount = 0;
        String classId = "";
        String className ="";
        for (Operation opt : opts) {
            if (!opt.getClassName().equals(className)) {
                className = opt.getClassName();
                Map<String, Object> obj = new HashMap<String, Object>();
                optList.add(obj);
                classId = "class_" + classCount;
                obj.put("id", classId);
                obj.put("name", opt.getClassName());
                obj.put("pId", 0);
                classCount++;
            }
            Map<String, Object> mobj = new HashMap<String, Object>();
            optList.add(mobj);
            mobj.put("id", opt.getId());
            mobj.put("name", opt.getMethodName());
            mobj.put("pId", classId);
            mobj.put("accessUrl", opt.getPath());
        }
        return optList;
    }
    
    @Override
    public List<MenuOperation> findMenuOperation(Long menuId) {
        List<MenuOperation> opts = dao().query(MenuOperation.class, Cnd.where("menuId", "=", menuId));
        return opts;
    }
    
    @Override
    public Map<String, Object> findMenuOperationForRole(Long roleId) {
        Sql sql = Sqls.queryEntity("select distinct opt.* from operation opt inner join menu_operation mo "
                + " on mo.operation_id = opt.id inner join role_menu rm on rm.menu_id=mo.menu_id"
                + " where rm.role_id = @roleId ");
        sql.params().set("roleId", roleId);
        sql.setEntity(dao().getEntity(Operation.class));
        dao().execute(sql);
        List<Operation> list = sql.getList(Operation.class);
        
        Map<String, Object> reMap = new HashMap<String, Object>();
        for (Operation opt : list) {
            reMap.put(opt.getUniqueCode(), list);
        }
        return reMap;
    }
    
    @Override
    public boolean saveMenuOperations(Long menuId, Long[] operationIds) {
        if (operationIds != null && operationIds.length > 0) {
            Sql delSql = Sqls.create("delete from menu_operation where menu_id=@menuId");
            delSql.params().set("menuId", menuId);
            dao().execute(delSql);
            Sql sql = Sqls.create("insert into menu_operation(menu_id,operation_id) values(@menuId,@operationId)");
            for (long operationId : operationIds) {
                int count = dao().count(MenuOperation.class,
                        Cnd.where("menuId", "=", menuId).and("operationId", "=", operationId));
                if (count == 0) {
                    sql.params().set("menuId", menuId);
                    sql.params().set("operationId", operationId);
                    sql.addBatch();
                }
            }
            dao().execute(sql);
        }
        return true;
    }
}
