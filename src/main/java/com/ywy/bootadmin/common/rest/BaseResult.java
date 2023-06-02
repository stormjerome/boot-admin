package com.ywy.bootadmin.common.rest;

import lombok.Data;

/**
 * 中间数据结果封装类
 *
 * @author ywy
 * @date 2020-03-20 17:35
 */
@Data
public class BaseResult<T> {
    /**
     * 数据信息
     */
    protected T data;

    /**
     * 异常信息
     */
    protected ResponseCode error;

    public BaseResult() {
    }

    public BaseResult(T data) {
        this.data = data;
    }

    public BaseResult(ResponseCode error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return this.error == null;
    }
}
