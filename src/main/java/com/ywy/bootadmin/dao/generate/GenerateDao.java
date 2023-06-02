package com.ywy.bootadmin.dao.generate;

import com.ywy.bootadmin.model.generate.TableField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 代码生成 Dao
 *
 * @author ywy
 * @date 2020-04-13 17:12
 */
@Mapper
public interface GenerateDao {
    /**
     * 查询数据库表字段信息
     * @param tableName
     * @return
     */
    @Select("select column_name, data_type, column_comment, column_default from information_schema.columns " +
            "where table_name=#{tableName} and table_schema = (select database())")
    List<TableField> getTableColumns(String tableName);
}
