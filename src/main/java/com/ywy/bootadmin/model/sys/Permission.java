package com.ywy.bootadmin.model.sys;

import com.ywy.bootadmin.model.BaseEntity;
import lombok.Data;

/**
 * 权限Entity
 *
 * @author: ywy
 * @date: 2020-03-19 22:25
 */
@Data
public class Permission extends BaseEntity {
    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 图标字体
     */
    private String iconfont;

    /**
     * 链接
     */
    private String url;

    /**
     * 类型（1：菜单；2：按钮）
     */
    private Integer type;

    /**
     * 资源标识
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;
}
