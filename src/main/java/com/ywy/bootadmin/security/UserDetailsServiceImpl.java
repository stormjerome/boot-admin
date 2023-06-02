package com.ywy.bootadmin.security;

import com.ywy.bootadmin.dao.sys.PermissionDao;
import com.ywy.bootadmin.dao.sys.UserDao;
import com.ywy.bootadmin.model.sys.Permission;
import com.ywy.bootadmin.model.sys.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * UserDetailsService实现
 *
 * @author ywy
 * @date 2020-04-08 18:06
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }

        // 根据用户id查询权限
        List<Permission> permissionList = permissionDao.getPermissionByUserId(user.getId(), null);

        MyUserDetails userDetails = new MyUserDetails();
        BeanUtils.copyProperties(user, userDetails);
        userDetails.setPermissionList(permissionList);
        return userDetails;
    }
}
