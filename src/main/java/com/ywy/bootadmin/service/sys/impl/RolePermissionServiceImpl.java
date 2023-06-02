package com.ywy.bootadmin.service.sys.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dao.sys.RolePermissionDao;
import com.ywy.bootadmin.service.sys.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 角色权限关系Service实现
 *
 * @author ywy
 * @date 2020-03-21 11:26
 */
@Service

@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseResult getPermissionIdsByRoleId(Integer roleId) {
        List<Integer> permissionIds = rolePermissionDao.getPermissionIdsByRoleId(roleId);
        return new BaseResult(permissionIds);
    }
}
