package com.ywy.bootadmin.common.rest;

import lombok.Getter;

/**
 * 状态信息枚举
 * @author ywy
 * @date 2020-03-19 14:27
 */
@Getter
public enum ResponseCode {
    // 公共信息
    SUCCESS(200, 0, "", "操作成功"),
    SYS_ERR(500, 1001 ,"SYS_ERROR", "系统繁忙，请稍候重试"),
    SAVE_ERROR(500, 1002 ,"SAVE_ERROR", "保存操作失败"),
    UPDATE_ERROR(500, 1003 ,"UPDATE_ERROR", "修改操作失败"),
    DELETE_ERROR(500, 1004 ,"DELETE_ERROR", "删除操作失败"),

    // 用户
    USERNAME_REPEAT(500, 2001 ,"USERNAME_REPEAT", "用户名已存在"),
    PHONE_REPEAT(500, 2002 ,"PHONE_REPEAT", "手机号已存在"),
    USER_NON_EXISTENT(500, 2003 ,"USER_NON_EXISTENT", "用户不存在"),
    USER_PASSWORD_ERROR(500, 2004 ,"USER_PASSWORD_ERROR", "用户密码错误"),

    // 角色
    USER_ROLE_NO_CLEAR(500, 3001, "USER_ROLE_NO_CLEAR", "该角色存在用户关联，无法删除"),

    // 权限
    PERMISSION_HAVE_CHILDREN(500, 4001, "PERMISSION_HAVE_CHILDREN", "该菜单存在子级，无法删除"),

    // 登录
    LOGIN_FAILURE(500, 5001, "LOGIN_FAILURE", "登录失败！"),
    LOGIN_LOCKED(500, 5002, "LOGIN_LOCKED", "账号被锁定，登录失败！"),
    LOGIN_BAD_CREDENTIALS(500, 5003, "LOGIN_BAD_CREDENTIALS", "账号或密码输入错误，登录失败！"),
    LOGIN_DISABLED(500, 5004, "LOGIN_DISABLED", "账号被禁用，登录失败！"),
    LOGIN_ACCOUNT_EXPIRED(500, 5005, "LOGIN_ACCOUNT_EXPIRED", "账号已过期，登录失败！"),
    LOGIN_CREDENTIALS_EXPIRED(500, 5006, "LOGIN_CREDENTIALS_EXPIRED", "密码已过期，登录失败！"),


    // 代码生成
    GEN_TABLE_NON_EXISTENT(500, 6001, "GEN_TABLE_NON_EXISTENT", "表不存在"),
    ;


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

    ResponseCode(Integer httpCode, Integer retCode, String retInfo, String retMsg) {
        this.httpCode = httpCode;
        this.retCode = retCode;
        this.retInfo = retInfo;
        this.retMsg = retMsg;
    }
}
