package com.ywy.bootadmin.dao.sys;

import com.ywy.bootadmin.model.sys.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * 用户Dao
 * @author ywy
 * @date 2020-03-18 10:00
 */
@Mapper
public interface UserDao {
    /**
     * 查询用户数据个数
     * @param params
     * @return
     */
    Integer count(@Param("params") Map<String, Object> params);

    /**
     * 分页查询用户信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    List<User> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("params") Map<String, Object> params);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from sys_user where id = #{id}")
    User getById(Integer id);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from sys_user where username = #{username}")
    User getByUsername(String username);

    /**
     * 根据手机号查询用户信息
     * @param telephone
     * @return
     */
    @Select("select * from sys_user where telephone = #{telephone}")
    User getByPhone(String telephone);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, head_img_url, telephone, email, birthday, sex, status) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status})")
    Integer save(User user);

    /**
     * 修改用户信息
     * sql在mapper xml文件中
     * @param user
     * @return
     */
    Integer update(User user);

    /**
     * 删除用户信息，通过外键级联删除role_user
     * @param id
     * @return
     */
    @Delete("delete from sys_user where id = #{id}")
    Integer delete(Integer id);

    /**
     * 修改用户密码
     * @param id
     * @param password
     * @return
     */
    @Update("update sys_user set password = #{password} where id = #{id}")
    Integer changePassword(@Param("id")Integer id, @Param("password")String password);
}
