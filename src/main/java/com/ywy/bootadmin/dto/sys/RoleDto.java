package com.ywy.bootadmin.dto.sys;

import com.ywy.bootadmin.model.sys.Role;
import lombok.Data;
import java.util.List;

/**
 * 角色Dto
 *
 * @author ywy
 * @date 2020-03-26 16:13
 */
@Data
public class RoleDto extends Role {
    private List<Integer> permissionIds;
}
