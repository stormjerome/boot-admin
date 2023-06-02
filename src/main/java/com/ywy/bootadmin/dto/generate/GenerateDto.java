package com.ywy.bootadmin.dto.generate;

import lombok.Data;
import java.util.List;

/**
 * 代码生成Dto
 *
 * @author: ywy
 * @date: 2020-04-13 22:28
 */
@Data
public class GenerateDto {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 保存路径
     */
    private String path;

    /**
     * 分组包名
     */
    private String groupPackageName;

    /**
     * Bean包名
     */
    private String beanPackageName;

    /**
     * Bean类名
     */
    private String beanName;

    /**
     * Dao包名
     */
    private String daoPackageName;

    /**
     * Dao类名
     */
    private String daoName;

    /**
     * Service包名
     */
    private String servicePackageName;

    /**
     * Service类名
     */
    private String serviceName;

    /**
     * ServiceImpl包名
     */
    private String serviceImplPackageName;

    /**
     * ServiceImpl类名
     */
    private String serviceImplName;

    /**
     * Controller包名
     */
    private String controllerPackageName;

    /**
     * Controller类名
     */
    private String controllerName;

    /**
     * 表字段名称
     */
    private List<String> columnName;

    /**
     * 表字段描述
     */
    private List<String> columnComment;

    /**
     * java字段名称
     */
    private List<String> fieldName;

    /**
     * java字段类型
     */
    private List<String> fieldType;
}
