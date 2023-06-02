package com.ywy.bootadmin.model.generate;

import lombok.Data;

/**
 * 数据库表和Bean字段信息
 *
 * @author ywy
 * @date 2020-04-13 16:30
 */
@Data
public class TableField {
    /**
     * 表字段名称
     */
    private String columnName;

    /**
     * 表字段数据类型
     */
    private String dataType;

    /**
     * 表字段描述
     */
    private String columnComment;

    /**
     * 表字段默认值
     */
    private String columnDefault;

    /**
     * java字段名称
     */
    private String fieldName;

    /**
     * javaBean字段数据类型
     */
    private String fieldType;
}
