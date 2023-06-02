package com.ywy.bootadmin.dao.sys;

import com.ywy.bootadmin.model.sys.Role;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * 角色Dao
 *
 * @author: ywy
 * @date: 2020-03-19 22:32
 */
@Mapper
public interface RoleDao {
    /**
     * 查询角色数据个数
     * @param params
     * @return
     */
    Integer count(@Param("params") Map<String, Object> params);

    /**
     * 分页查询角色信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    List<Role> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("params") Map<String, Object> params);

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_role(name, description) values(#{name}, #{description})")
    Integer save(Role role);

    /**
     * 修改角色信息
     * sql在mapper xml文件中
     * @param role
     * @return
     */
    Integer update(Role role);

    /**
     * 删除角色信息，通过外键级联删除role_permission
     * @param id
     * @return
     */
    @Delete("delete from sys_role where id = #{id}")
    Integer delete(Integer id);

    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from sys_role")
    List<Role> list();

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Select("select * from sys_role where id = #{id}")
    Role getById(Integer id);
}
