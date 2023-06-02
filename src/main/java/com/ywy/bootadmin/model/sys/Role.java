package com.ywy.bootadmin.model.sys;

import com.ywy.bootadmin.model.BaseEntity;
import lombok.Data;

/**
 * 角色Entity
 * @author: ywy
 * @date: 2020-03-19 22:20
 */
@Data
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
