package com.weirhp.service.common;

import java.util.List;

import org.nutz.dao.pager.Pager;

import com.weirhp.domain.common.User;

public interface UserService {
    public User login(String name,String password);

    public List<User> findUsers(User user, Pager pager);

    public User fetchUser(Long id);

    public User save(User role);

    public boolean delete(Long id);

    boolean updatePassword(User user);
}
