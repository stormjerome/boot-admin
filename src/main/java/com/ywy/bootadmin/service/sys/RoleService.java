package com.ywy.bootadmin.service.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dto.sys.RoleDto;
import com.ywy.bootadmin.model.sys.Role;
import java.util.Map;

/**
 * 角色Service
 *
 * @author ywy
 * @date 2020-03-20 12:40
 */
public interface RoleService {
    /**
     *分页查询角色信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params);

    /**
     * 保存角色信息
     * @param roleDto
     * @return
     */
    BaseResult save(RoleDto roleDto);

    /**
     * 修改角色信息
     * @param roleDto
     * @return
     */
    BaseResult update(RoleDto roleDto);

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    BaseResult delete(Integer id);

    /**
     * 查询所有角色信息
     * @return
     */
    BaseResult getAllRoles();

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    Role getRoleById(Integer id);
}
