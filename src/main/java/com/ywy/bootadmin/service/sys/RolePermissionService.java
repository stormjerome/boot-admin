package com.ywy.bootadmin.service.sys;

import com.ywy.bootadmin.common.rest.BaseResult;

/**
 * 角色权限关系Service
 *
 * @author ywyw
 * @date 2020-03-21 11:26
 */
public interface RolePermissionService {
    /**
     * 根据角色id查询权限id
     * @param roleId
     * @return
     */
    BaseResult getPermissionIdsByRoleId(Integer roleId);
}
