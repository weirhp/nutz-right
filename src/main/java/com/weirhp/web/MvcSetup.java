package com.weirhp.web;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MvcSetup implements Setup {
    private static final Log log = Logs.get();
    
    public void init(NutConfig config) {
        log.debug("I start!!");
        // Dao dao = config.getIoc().get(Dao.class);
        //
        // // 自动建表
        // for (Class<?> klass :
        // Scans.me().scanPackage(User.class.getPackage().getName())) {
        // if (null != klass.getAnnotation(Table.class)) {
        // dao.create(klass, false);
        // log.debug("create table " + klass.getSimpleName());
        // }
        // }
        
    }
    
    @Override
    public void destroy(NutConfig config) {
        log.debug("I stop!!");
    }
    
}
