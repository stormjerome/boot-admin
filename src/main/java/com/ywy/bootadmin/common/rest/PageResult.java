package com.ywy.bootadmin.common.rest;

import lombok.Data;
import java.util.List;

/**
 * 分页数据结果封装类
 *
 * @author ywy
 * @date 2020-03-19 15:26
 */
@Data
public class PageResult<T> {
    /**
     * 数据个数
     */
    private Integer total;

    /**
     * 数据
     */
    private List<T> datas;
}
