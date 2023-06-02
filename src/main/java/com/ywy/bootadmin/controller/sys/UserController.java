package com.ywy.bootadmin.controller.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.DataResponse;
import com.ywy.bootadmin.common.rest.PageInfo;
import com.ywy.bootadmin.dto.sys.UserDto;
import com.ywy.bootadmin.model.sys.User;
import com.ywy.bootadmin.service.sys.UserService;
import com.ywy.bootadmin.util.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

/**
 * 用户Controller
 * @author ywy
 * @date 2020-03-18 10:00
 */
@Controller
@RequestMapping("user")
@Slf4j
@Api(description = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表
     * @param pageInfo
     * @param user
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
    @ApiOperation("分页查询用户列表")
    public DataResponse list(PageInfo pageInfo, User user) {
        log.info("UserController.list(), param: {}, {}", pageInfo, user);

        HashMap<String, Object> paramsMap = MapUtil.convertToMap(user);
        BaseResult baseResult = userService.listByPage(pageInfo.getOffset(), pageInfo.getLimit(), paramsMap);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到添加用户页面
     * @return
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation("跳转到添加用户页面")
    public String toAdd() {
        return "/user/user-add";
    }

    /**
     * 添加用户信息
     * @param userDto
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation("添加用户信息")
    public DataResponse add(UserDto userDto) {
        log.info("UserController.add(), param: {}", userDto);

        BaseResult baseResult = userService.save(userDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到修改用户页面
     * @return
     */
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation("跳转到修改用户页面")
    public String toEdit(Model model, Integer id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user/user-edit";
    }

    /**
     * 修改用户信息
     * @param userDto
     * @return
     */
    @PutMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation("修改用户信息")
    public DataResponse edit(UserDto userDto) {
        log.info("UserController.edit(), param: {}", userDto);

        BaseResult baseResult = userService.update(userDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:del')")
    @ApiOperation("删除用户信息")
    public DataResponse delete(Integer id) {
        log.info("UserController.delete(), param: {}", id);

        BaseResult baseResult = userService.delete(id);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 修改用户密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PutMapping("/password")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation("修改用户密码")
    public DataResponse changePassword(Integer id, String oldPassword, String newPassword) {
        log.info("UserController.changePassword(), param: {},{},{}", id, oldPassword, newPassword);

        BaseResult baseResult = userService.changePassword(id, oldPassword, newPassword);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }
}
