package com.weirhp.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.weirhp.annotation.Allow;
import com.weirhp.domain.common.User;
import com.weirhp.service.common.UserService;
import com.weirhp.web.Constants;

@IocBean
@Fail("json")
public class IndexModule {
    
    @Inject
    UserService userService;
    
    @Allow
    @At("/")
    @Ok("jsp:jsps.login")
    public void index() {
        // AtMap atMap = Mvcs.getAtMap();
        // Map<String,Method> map= atMap.getMethodMapping();
        // for(Entry<String, Method> entry:map.entrySet()){
        // System.out.println(entry.getKey() + ":" + entry.getValue() + "||" +
        // entry.getValue().getDeclaringClass());
        // }
        // return "";
    }
    
    @Allow
    @At("/login")
    @Ok("jsp:jsps.login")
    public void login() {
    }
    
    @Allow
    @At("/llogin")
    @Ok("json")
    public Map<String, Object> login(HttpServletRequest request, @Param("::user.") User user,
            @Param("randcode") String randcode) {
        Map<String, Object> re = new HashMap<String, Object>();
        re.put("success", false);
        User loginUser = userService.login(user.getName(), user.getPassword());
        if (loginUser != null) {
            request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, loginUser);
            re.put("success", true);
        } else {
            re.put("message", "用户名或者密码错误！");
        }
        request.getSession().removeAttribute(Constants.SESSION_VERIFY_CODE);
        return re;
    }
    
    @Allow
    @At("/noright")
    @Ok("jsp:jsps.error.noright")
    public void noRight() {
        
    }
    
    @Allow
    @At("/noright_json")
    @Ok("json")
    public Map<String, Object> noRightJson() {
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("msg", "你没有权限访问！");
        return map;
    }
}
