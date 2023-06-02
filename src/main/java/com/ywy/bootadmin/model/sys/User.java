package com.ywy.bootadmin.model.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywy.bootadmin.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * 用户Entity
 * @author ywy
 * @date 2020-03-18 10:00
 */
@Data
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 性别（0：女；1：男）
     */
    private Integer sex;

    /**
     * 状态（0：禁用；1：正常；2：锁定）
     */
    private Integer status;
}
