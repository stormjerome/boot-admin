package com.ywy.bootadmin.model.generate;

import lombok.Data;
import java.util.List;

/**
 * 数据库表对应的Bean信息
 *
 * @author ywy
 * @date 2020-04-13 17:42
 */
@Data
public class BeanInfo {
    /**
     * bean类名
     */
    private String beanName;

    /**
     * 字段信息
     */
    private List<TableField> fields;
}
