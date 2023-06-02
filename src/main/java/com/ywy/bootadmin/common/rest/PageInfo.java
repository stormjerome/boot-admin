package com.ywy.bootadmin.common.rest;

import lombok.Data;

/**
 * 分页学习
 *
 * @author: ywy
 * @date: 2020-03-19 23:29
 */
@Data
public class PageInfo {
    /**
     * 当前第几页
     */
    private Integer page;

    /**
     * 每页显示数量
     */
    private Integer limit;

    /**
     * 当前数据偏移量
     */
    private Integer offset;

    public Integer getOffset() {
        if (this.page == null || this.limit == null) {
            this.offset = 0;
        } else {
            this.offset = (this.page - 1) * this.limit;
        }
        return this.offset;
    }
}
