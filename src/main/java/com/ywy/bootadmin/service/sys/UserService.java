package com.ywy.bootadmin.service.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.dto.sys.UserDto;
import com.ywy.bootadmin.model.sys.User;
import java.util.Map;

/**
 * 用户Service
 * @author ywy
 * @date 2020-03-18 10:00
 */
public interface UserService {
    /**
     * 分页查询用户信息
     * @param offset
     * @param limit
     * @param params
     * @return
     */
    BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params);

    /**
     * 保存用户信息
     * @param userDto
     * @return
     */
    BaseResult save(UserDto userDto);

    /**
     * 修改用户信息
     * @param userDto
     * @return
     */
    BaseResult update(UserDto userDto);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    BaseResult delete(Integer id);

    /**
     * 修改用户密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    BaseResult changePassword(Integer id, String oldPassword, String newPassword);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
