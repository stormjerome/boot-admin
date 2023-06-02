package com.ywy.bootadmin.dao.test;

import com.ywy.bootadmin.model.test.Test;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * Dao
 * @author ywy
 * @date
 */
@Mapper
public interface TestDao {
    /**
     * 查询数据个数
     * @param params
     * @return
     */
    int count(@Param("params") Map<String, Object> params);

    /**
     * 分页查询信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    List<Test> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("params") Map<String, Object> params);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Select("select * from test where id = #{id}")
    Test getById(Integer id);

    /**
     * 保存信息
     * @param test
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into test(field1, field2, field3, field4, field5) values(#{field1}, #{field2}, #{field3}, #{field4}, #{field5})")
    Integer save(Test test);

    /**
     * 修改信息
     * sql在mapper xml文件中
     * @param test
     * @return
     */
    Integer update(Test test);

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Delete("delete from test where id = #{id}")
    Integer delete(Integer id);
}
