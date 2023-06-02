package com.ywy.bootadmin.dao.sys;

import com.ywy.bootadmin.model.sys.Permission;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 权限Dao
 *
 * @author: ywy
 * @date: 2020-03-19 22:31
 */
@Mapper
public interface PermissionDao {
    /**
     * 查询所有权限信息
     * sql在mapper xml文件中
     * @param type
     * @return
     */
    List<Permission> list(Integer type);

    /**
     * 保存权限信息
     * @param permission
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_permission(parent_id, name, iconfont, url, type, permission, sort) values(#{parentId}, #{name}, #{iconfont}, #{url}, #{type}, #{permission}, #{sort})")
    Integer save(Permission permission);

    /**
     * 修改权限信息
     * sql在mapper xml文件中
     * @param permission
     * @return
     */
    Integer update(Permission permission);

    /**
     * 删除权限信息，通过外键级联删除role_permission
     * @param id
     * @return
     */
    @Delete("delete from sys_permission where id = #{id}")
    Integer delete(Integer id);

    /**
     * 根据id查询权限信息
     * @param id
     * @return
     */
    @Select("select * from sys_permission where id = #{id}")
    Permission getPermissionById(Integer id);

    /**
     * 根据父id查询权限信息
     * @param parentId
     * @return
     */
    @Select("select * from sys_permission where parent_id = #{parentId}")
    List<Permission> getPermissionByParentId(Integer parentId);

    /**
     * 根据用户id查询权限信息
     * sql在mapper xml文件中
     * @param userId
     * @param type
     * @return
     */
    List<Permission> getPermissionByUserId(@Param("userId") Integer userId, @Param("type") Integer type);
}
