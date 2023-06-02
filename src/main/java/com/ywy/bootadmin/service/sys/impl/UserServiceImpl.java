package com.ywy.bootadmin.service.sys.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.PageResult;
import com.ywy.bootadmin.common.rest.ResponseCode;
import com.ywy.bootadmin.dao.sys.RoleUserDao;
import com.ywy.bootadmin.dao.sys.UserDao;
import com.ywy.bootadmin.dto.sys.UserDto;
import com.ywy.bootadmin.model.sys.User;
import com.ywy.bootadmin.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户Service实现
 * @author ywy
 * @date 2020-03-18 10:00
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params) {
        Integer count = userDao.count(params);
        List<User> userList = userDao.listByPage(offset, limit, params);
        PageResult<User> result = new PageResult<>();
        result.setTotal(count);
        result.setDatas(userList);
        return new BaseResult(result);
    }

    @Override
    public BaseResult save(UserDto userDto) {
        // 查询用户名是否存在
        User user = userDao.getByUsername(userDto.getUsername());
        // 用户名已存在
        if (user != null) {
            return new BaseResult(ResponseCode.USERNAME_REPEAT);
        }

        // 查询手机号是否存在
        user = userDao.getByPhone(userDto.getTelephone());
        // 手机号已存在
        if (user != null) {
            return new BaseResult(ResponseCode.PHONE_REPEAT);
        }

        userDto.setStatus(1);
        // 密码加密
        String newPass = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userDto.setPassword(newPass);

        if (userDto.getRoleId() != null) {
            // 保存用户信息
            userDao.save(userDto);

            // 保存用户角色关系
            roleUserDao.save(userDto.getRoleId(), userDto.getId());

            return new BaseResult();
        }
        return new BaseResult(ResponseCode.SAVE_ERROR);
    }

    @Override
    public BaseResult update(UserDto userDto) {
        // 查询用户名是否存在
        User user = userDao.getByUsername(userDto.getUsername());
        // 用户名已存在
        if (user != null && !(user.getId().equals(userDto.getId()))) {
            return new BaseResult(ResponseCode.USERNAME_REPEAT);
        }

        // 查询手机号是否存在
        user = userDao.getByPhone(userDto.getTelephone());
        // 手机号已存在
        if (user != null && !(user.getId().equals(userDto.getId()))) {
            return new BaseResult(ResponseCode.PHONE_REPEAT);
        }


        if (userDto.getRoleId() != null) {
            // 修改用户信息
            userDao.update(userDto);

            // 查看用户是否有角色
            Integer roleId = roleUserDao.getRoleIdByUserId(userDto.getId());
            if (roleId != null) {
                // 修改角色用户关系
                roleUserDao.update(userDto.getRoleId(), userDto.getId());
            } else {
                // 保存角色用户关系
                roleUserDao.save(userDto.getRoleId(), userDto.getId());
            }

            return new BaseResult();
        }
        return new BaseResult(ResponseCode.UPDATE_ERROR);
    }

    @Override
    public BaseResult delete(Integer id) {
        // 删除用户信息，通过外键级联删除角色用户关系
        Integer count = userDao.delete(id);
        if (count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.DELETE_ERROR);
    }

    @Override
    public BaseResult changePassword(Integer id, String oldPassword, String newPassword) {
        User user = userDao.getById(id);
        if (user == null) {
            return new BaseResult(ResponseCode.USER_NON_EXISTENT);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            return new BaseResult(ResponseCode.USER_PASSWORD_ERROR);
        }

        String encodeNewPassword = bCryptPasswordEncoder.encode(newPassword);
        userDao.changePassword(id, encodeNewPassword);
        return new BaseResult();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getById(id);
    }
}
