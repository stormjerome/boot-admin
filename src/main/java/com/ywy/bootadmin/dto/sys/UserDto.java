package com.ywy.bootadmin.dto.sys;

import com.ywy.bootadmin.model.sys.User;
import lombok.Data;

/**
 * 用户Dto
 *
 * @author ywy
 * @date 2020-03-20 17:10
 */
@Data
public class UserDto extends User {
    private Integer roleId;
}
