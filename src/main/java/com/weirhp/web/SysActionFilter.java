package com.weirhp.web;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.ServerRedirectView;

import com.weirhp.annotation.Allow;
import com.weirhp.domain.common.Role;
import com.weirhp.domain.common.User;
import com.weirhp.service.common.OperationService;
import com.weirhp.util.HReflects;
/**
 * 权限过滤器
 * @author weirhp@gmail.com
 *
 */
public class SysActionFilter implements ActionFilter {
    
    private static Map<Long, Object> roleOperations = new HashMap<Long, Object>();
    
    @Override
    public View match(ActionContext actionContext) {
        Ioc ioc = actionContext.getIoc();
        OperationService operationService = ioc.get(OperationService.class);
        HttpServletRequest request = actionContext.getRequest();
        Method actionMethod = actionContext.getMethod();
        User user = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
        if (!hasRight(user, actionMethod, operationService)) {
            if (user == null) {
                return view;
            } else {
                try {
                    //根据方法本来的返回数据类型进行错误view的展示
                    Ok ok = actionMethod.getAnnotation(Ok.class);
                    String okStr = ok.value();
                    if ("json".equals(okStr)) {
                        return new ServerRedirectView("/noright_json");
                    }
                } catch (Exception e) {
                    
                }
            }
            return new ServerRedirectView("/noright");
        }
        return null;
    }
    /**
     * 判断当前用户是否有权限访问该action
     * @param user
     * @param actionMethod
     * @param operationService
     * @return
     */
    private boolean hasRight(User user, Method actionMethod, OperationService operationService) {
        Allow allow = actionMethod.getAnnotation(Allow.class);
        boolean hasRight = false;
        if (allow != null && allow.value() == true) {
            hasRight = true;
        }
        if (user != null && user.isSysAdmin()) {
            hasRight = true;
        }
        if (!hasRight && user != null && !user.isSysAdmin()) {
            List<Role> roles = user.getRoles();
            if (roles != null && roles.size() > 0) {
                for (Role role : roles) {
                    if (!roleOperations.containsKey(role.getId())) {
                        initRoleOperations(role, operationService);
                    }
                    String mCode = HReflects.getMethodIdentity(actionMethod);
                    @SuppressWarnings("unchecked")
                    Map<String, Object> actions = (Map<String, Object>) roleOperations.get(role.getId());
                    if (actions != null && actions.containsKey(mCode)) {// 该角色分配了该权限
                        hasRight = true;
                        break;
                    }
                }
            }
        }
        return hasRight;
    }
    
    private synchronized void initRoleOperations(Role role, OperationService operationService) {
        if (!roleOperations.containsKey(role.getId())) {
            roleOperations.put(role.getId(), operationService.findMenuOperationForRole(role.getId()));
        }
    }
    
    private View view;
    
    public SysActionFilter() {
        view = new ServerRedirectView("/login.html");
    }
    
    public SysActionFilter(String veiw) {
        view = new ServerRedirectView(veiw);
    }
}
