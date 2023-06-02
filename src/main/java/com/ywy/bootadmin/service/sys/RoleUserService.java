package com.ywy.bootadmin.service.sys;

import com.ywy.bootadmin.common.rest.BaseResult;

/**
 * 角色用户关系Service
 *
 * @author ywyw
 * @date 2020-03-21 11:26
 */
public interface RoleUserService {
    /**
     * 根据用户id查询角色id
     * @param userId
     * @return
     */
    BaseResult getRoleIdByUserId(Integer userId);
}
