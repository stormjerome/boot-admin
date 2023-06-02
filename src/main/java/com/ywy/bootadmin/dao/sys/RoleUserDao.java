package com.ywy.bootadmin.dao.sys;

import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 角色用户关系Dao
 *
 * @author ywy
 * @date 2020-03-20 17:35
 */
@Mapper
public interface RoleUserDao {
    /**
     * 保存角色用户关系信息
     * @param roleId
     * @param userId
     * @return
     */
    @Insert("insert into sys_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    Integer save(@Param("roleId")Integer roleId, @Param("userId")Integer userId);

    /**
     * 修改角色用户关系信息
     * @param roleId
     * @param userId
     * @return
     */
    @Update("update sys_role_user set role_id = #{roleId} where user_id = #{userId}")
    Integer update(@Param("roleId")Integer roleId, @Param("userId")Integer userId);

    /**
     * 根据用户id查询角色id
     * @param userId
     * @return
     */
    @Select("select role_id from sys_role_user where user_id = #{userId}")
    Integer getRoleIdByUserId(Integer userId);

    /**
     * 根据角色id查询用户id
     * @param roleId
     * @return
     */
    @Select("select user_id from sys_role_user where role_id = #{roleId}")
    List<Integer> getUserIdsByRoleId(Integer roleId);
}
