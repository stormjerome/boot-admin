package com.ywy.bootadmin.service.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dto.sys.PermissionDto;
import com.ywy.bootadmin.model.sys.Permission;

/**
 * 权限Service
 *
 * @author ywy
 * @date 2020-03-25 11:39
 */
public interface PermissionService {
    /**
     * 查询所有权限信息
     * @return
     */
    BaseResult getAllPermissions();

    /**
     * 保存权限信息
     * @param permissionDto
     * @return
     */
    BaseResult save(PermissionDto permissionDto);

    /**
     * 修改权限信息
     * @param permissionDto
     * @return
     */
    BaseResult update(PermissionDto permissionDto);

    /**
     * 删除权限信息
     * @param id
     * @return
     */
    BaseResult delete(Integer id);

    /**
     * 查询权限tree结构信息
     * @param noButton
     * @return
     */
    BaseResult getPermissionsByTree(Boolean noButton);


    /**
     * 根据id查询权限信息
     * @param id
     * @return
     */
    Permission getPermissionById(Integer id);

    /**
     * 查询菜单信息
     * @param userId
     * @return
     */
    BaseResult getMenu(Integer userId);
}
