package com.ywy.bootadmin.common.rest;

import lombok.Data;

/**
 * 基本信息
 *
 * @author ywy
 * @date 2020-03-19 15:47
 */
@Data
public class BaseResponse {
    /**
     * http状态码
     */
    private Integer httpCode;

    /**
     * 状态码
     */
    private Integer retCode;

    /**
     * 简要信息
     */
    private String retInfo;

    /**
     * 详细信息
     */
    private String retMsg;

    public BaseResponse() {

    }

    public BaseResponse(ResponseCode responseCode) {
        this.httpCode = responseCode.getHttpCode();
        this.retCode = responseCode.getRetCode();
        this.retInfo = responseCode.getRetInfo();
        this.retMsg = responseCode.getRetMsg();
    }
}
