package com.ywy.bootadmin.service.sys.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dao.sys.RoleUserDao;
import com.ywy.bootadmin.service.sys.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色用户关系Service实现
 *
 * @author ywy
 * @date 2020-03-21 11:26
 */
@Service

@Transactional
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public BaseResult getRoleIdByUserId(Integer userId) {
        Integer roleId = roleUserDao.getRoleIdByUserId(userId);
        return new BaseResult(roleId);
    }
}
