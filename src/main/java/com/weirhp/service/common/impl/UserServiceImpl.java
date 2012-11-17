package com.weirhp.service.common.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import com.weirhp.domain.common.Role;
import com.weirhp.domain.common.User;
import com.weirhp.service.common.UserService;
import com.weirhp.util.HDBUtils;

@IocBean(name = "userService", fields = { "dao" })
public class UserServiceImpl extends IdEntityService<User> implements UserService {
    
    @Override
    public User login(String name, String password) {
        User loginUser = dao().fetch(User.class, Cnd.where("name", "=", name).and("password", "=", password));
        if (loginUser != null) {
            // 取出用户的角色
            Sql sql = Sqls
                    .queryEntity("select r.* from role r inner join user_role ur on ur.role_id=r.id where ur.user_id=@id");
            sql.params().set("id", loginUser.getId());
            sql.setEntity(dao().getEntity(Role.class));
            dao().execute(sql);
            List<Role> roles = sql.getList(Role.class);
            loginUser.setRoles(roles);
        }
        return loginUser;
    }
    
    @Override
    public List<User> findUsers(User user, Pager pager) {
        Sql sql = Sqls.queryEntity("select w.* from user w where name like @name");
        String likeName = HDBUtils.getLikeSqlForMysql(user.getName());
        sql.params().set("name", likeName);
        sql.setPager(pager);
        
        Sql sql2 = Sqls.fetchLong("select count(*) from user w where name like @name");
        sql2.params().set("name", likeName);
        dao().execute(sql2);
        pager.setRecordCount(sql2.getInt());
        sql.setEntity(dao().getEntity(User.class));
        dao().execute(sql);
        return sql.getList(User.class);
    }
    
    @Override
    public User fetchUser(Long id) {
        User user = dao().fetch(User.class, id);
        return user;
    }
    
    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId().longValue() == 0) {
            dao().insert(user);
        } else {
            Sql sql = Sqls.create("update user set nick_name=@nickName where id=@id");
            sql.params().set("nickName", user.getNickName()).set("id", user.getId());
            dao().execute(sql);
        }
        return user;
    }
    
    @Override
    public boolean updatePassword(User user) {
        boolean re = false;
        if (user != null && user.getId() != null && user.getId().longValue() != 0) {
            Sql sql = Sqls.create("update user set password=@password where id=@id");
            sql.params().set("password", user.getPassword()).set("id", user.getId());
            dao().execute(sql);
            re = true;
        }
        return re;
    }
    
    @Override
    public boolean delete(Long id) {
        Sql sql2 = Sqls.create("delete from user_role where user_id=@userId");
        sql2.params().set("userId", id);
        Sql sql3 = Sqls.create("delete from user where id=@userId");
        sql3.params().set("userId", id);
        dao().execute(sql2, sql3);
        return true;
    }
    
}
