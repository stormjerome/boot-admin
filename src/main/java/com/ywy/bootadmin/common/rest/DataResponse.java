package com.ywy.bootadmin.common.rest;

import lombok.Data;

/**
 * 数据对象返回实体
 *
 * @author ywy
 * @date 2018/08/13
 */
@Data
public class DataResponse<T> extends BaseResponse {
    /**
     * 数据
     */
    private T data;

    public DataResponse() {

    }

    public DataResponse(ResponseCode responseCode) {
        super(responseCode);
    }

    public DataResponse(T data, ResponseCode responseCode) {
        super(responseCode);
        this.data = data;
    }

    public static DataResponse success() {
        return new DataResponse(ResponseCode.SUCCESS);
    }

    public static <T> DataResponse success(T data) {
        return new DataResponse(data, ResponseCode.SUCCESS);
    }

    public static DataResponse failure() {
        return new DataResponse(ResponseCode.SYS_ERR);
    }

    public static DataResponse failure(ResponseCode responseCode) {
        return new DataResponse(responseCode);
    }
}
