package com.ywy.bootadmin.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础Entity
 */
@Data
public class BaseEntity implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;
}
