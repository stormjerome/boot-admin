package com.ywy.bootadmin.dao.sys;

import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 角色权限关系Dao
 *
 * @author ywy
 * @date 2020-03-20 17:35
 */
@Mapper
public interface RolePermissionDao {
    /**
     * 批量保存角色权限关系信息
     * @param roleId
     * @param permissionIds
     * @return
     */
    Integer batchSave(@Param("roleId")Integer roleId, @Param("permissionIds") List<Integer> permissionIds);

    /**
     * 删除角色权限关系信息
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    Integer delete(Integer roleId);

    /**
     * 根据角色id查询权限id
     * @param roleId
     * @return
     */
    @Select("select permission_id from sys_role_permission where role_id = #{roleId}")
    List<Integer> getPermissionIdsByRoleId(Integer roleId);

}
