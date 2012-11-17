package com.weirhp.web.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.weirhp.domain.common.MenuOperation;
import com.weirhp.service.common.OperationService;

@IocBean
@At("/operation")
public class OperationModule {
    
    @Inject
    private OperationService operationService;
    
    @At
    @Ok("json")
    public List<Map<String,Object>> operationJson() {
        List<Map<String,Object>> list = operationService.findAllOperationForTree();
        return list;
    }
    
    @At
    @Ok("json")
    public List<MenuOperation> menuOperationsJson(HttpServletRequest request,@Param("menuId") Long menuId) {
        List<MenuOperation> list = operationService.findMenuOperation(menuId);
        return list;
    }
    
    @At("/saveMenuOperations")
    @Ok("json")
    public Map<String, Object> saveMenuOperations(HttpServletRequest request, @Param("optIds") String menuIds,
            @Param("menuId") Long menuId) {
        Long[] mIds = Json.fromJson(Long[].class, menuIds);
        boolean re = operationService.saveMenuOperations(menuId, mIds);
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("success", re);
        return reMap;
    }
    
    @At("/setOperationForMenu")
    @Ok("jsp:jsps.admin.menu.setOperationForMenu")
    public void toSetMenuOperation(HttpServletRequest request, @Param("menuId") String menuId){
        request.setAttribute("menuId", menuId);
    }
}
