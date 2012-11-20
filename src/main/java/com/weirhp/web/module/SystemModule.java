package com.weirhp.web.module;

import java.lang.reflect.Method;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.config.AtMap;

import com.weirhp.service.common.RoleManagerService;

/**
 * 系统设定，初始化等操作
 * @author weirhp
 */
@IocBean
@At("/system")
public class SystemModule {
    
    @Inject
    private RoleManagerService roleService;
    
    @At("/system_set")
    @Ok("jsp:jsps.admin.system.system_set")
    public void toSystemSet(){
        
    }
    
    /**
     * 初始化所有的action操作，保存到数据库
     * @return
     */
    @At("/init_operation")
    @Ok("json")
    public Map<String, Object> initOperation() {
        AtMap atMap = Mvcs.getAtMap();
        Map<String, Method> map = atMap.getMethodMapping();
        Map<String, Object> msg = roleService.initOperation(map);
        return msg;
    }
    
}
