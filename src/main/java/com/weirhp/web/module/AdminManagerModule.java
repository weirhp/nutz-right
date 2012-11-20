package com.weirhp.web.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.weirhp.annotation.Allow;
import com.weirhp.domain.common.Menu;
import com.weirhp.domain.common.User;
import com.weirhp.service.common.MenuService;
import com.weirhp.web.Constants;

/**
 * 管理员首页的处理
 * @author weirhp@gmail.com
 *
 */
@IocBean
@At("/manage")
public class AdminManagerModule {
    
    @Inject
    private MenuService menuService;
    
    @At
    @Ok("jsp:jsps.admin.index")
    public void index(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
        if (loginUser == null) {
            loginUser = new User();
            loginUser.setId(1L);
        }
        List<Menu> menus = menuService.findMenusForAdmin(loginUser);
        request.setAttribute("menus", Json.toJson(menus));
    }
    
    @Allow
    @At
    @Ok("jsp:jsps.login")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
    
}
