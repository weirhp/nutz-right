package com.weirhp.web.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.weirhp.domain.common.User;
import com.weirhp.service.common.UserService;
import com.weirhp.web.Constants;

@IocBean
@At("/user")
public class UserManagerModule {
    
    @Inject
    private UserService userService;
    
    @At("/list")
    @Ok("jsp:jsps.admin.user.list")
    public void list(HttpServletRequest request, @Param("::page.") Pager pager, @Param("::user.") User user) {
        if (pager == null) {
            pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(10);
        }
        if (user == null) {
            user = new User();
        }
        List<User> list = userService.findUsers(user, pager);
        request.setAttribute("users", list);
        request.setAttribute("pager", pager);
        request.setAttribute("user", user);
    }
    
    @At("/toEdit")
    @Ok("jsp:jsps.admin.user.edit")
    public void add(HttpServletRequest request, @Param("id") Long id) {
        if (id != null && !id.equals(0)) {
            User user = userService.fetchUser(id);
            request.setAttribute("user", user);
        }
    }
    
    @At("/save")
    @Ok("jsp:jsps.admin.user.edit")
    public void add(HttpServletRequest request, @Param("::user.") User user) {
        if (user != null) {
            user = userService.save(user);
            request.setAttribute("user", user);
            request.setAttribute("message", "保存成功！");
        }
    }
    
    @At("/delete")
    @Ok("json")
    public Map<String, Object> delete(HttpServletRequest request, @Param("id") Long id) {
        Map<String, Object> json = new HashMap<String, Object>();
        if (id != null) {
            boolean re = userService.delete(id);
            json.put("success", re);
        }
        return json;
    }
    
    @At("/toUpdatePassword")
    @Ok("jsp:jsps.admin.user.updatePassword")
    public void toSetPassword() {
    }
    
    @At("/updatePassword")
    @Ok("json")
    public Map<String, Object> updatePassword(HttpServletRequest request, @Param("oldPass") String oldPass,
            @Param("newPass") String newPass) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
        if (!Strings.isEmpty(oldPass) && !Strings.isEmpty(newPass)
                && user.getPassword().equals(oldPass)) {
            User newUser = new User("", newPass);
            newUser.setId(user.getId());
            userService.updatePassword(newUser);
            user.setPassword(newUser.getPassword());
        }
        map.put("success", true);
        return map;
    }
    
}
