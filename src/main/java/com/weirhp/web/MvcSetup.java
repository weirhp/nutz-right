package com.weirhp.web;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;

import com.weirhp.domain.common.User;

public class MvcSetup implements Setup {
    private static final Log log = Logs.get();
    
    public void init(NutConfig config) {
        //初始化数据字典的静态类
        
        Dao dao = config.getIoc().get(Dao.class);
        
        // if (!dao.exists(UserInfo.class)) {
        // dao.create(UserInfo.class, false);
        // }
        // 自动建表
        for (Class<?> klass : Scans.me().scanPackage(User.class.getPackage().getName())) {
            if (null != klass.getAnnotation(Table.class)) {
                dao.create(klass, false);
                log.debug("create table " + klass.getSimpleName());
            }
        }
        
        // if (dao.count(User.class) == 0) {
        // log.debug("Create Admin user");
        // RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        // String salt = rng.nextBytes().toBase64();
        // String hashedPasswordBase64 = new Sha256Hash("123", salt,
        // 1024).toBase64();
        // dao.insert(Json.fromJson(User.class,
        // "{name:'admin',passwd:'"+hashedPasswordBase64+"',salt:'"+ salt
        // +"'}"));
        // }
        
    }
    
    @Override
    public void destroy(NutConfig config) {
        System.out.println("I stop!!");
    }
    
}
