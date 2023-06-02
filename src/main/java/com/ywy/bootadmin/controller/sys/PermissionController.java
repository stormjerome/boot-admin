package com.ywy.bootadmin.controller.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.DataResponse;
import com.ywy.bootadmin.dto.sys.PermissionDto;
import com.ywy.bootadmin.model.sys.Permission;
import com.ywy.bootadmin.service.sys.PermissionService;
import com.ywy.bootadmin.service.sys.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 权限Controller
 *
 * @author ywy
 * @date 2020-03-25 11:37
 */
@Controller
@RequestMapping("/permission")
@Slf4j
@Api(description = "权限相关接口")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 权限列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:permission:query')")
    @ApiOperation("查询权限列表")
    public DataResponse list() {
        log.info("PermissionController.list()");

        BaseResult baseResult = permissionService.getAllPermissions();
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到添加权限页面
     * @return
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:permission:add')")
    @ApiOperation("跳转到添加权限页面")
    public String toAdd() {
        return "/permission/permission-add";
    }

    /**
     * 添加权限信息
     * @param permissionDto
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:permission:add')")
    @ApiOperation("添加权限信息")
    public DataResponse add(PermissionDto permissionDto) {
        log.info("PermissionController.add(), param: {}", permissionDto);

        BaseResult baseResult = permissionService.save(permissionDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到修改权限页面
     * @return
     */
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:permission:edit')")
    @ApiOperation("跳转到修改权限页面")
    public String toEdit(Model model, Integer id) {
        Permission permission = permissionService.getPermissionById(id);
        model.addAttribute("permission", permission);
        return "/permission/permission-edit";
    }

    /**
     * 修改权限信息
     * @param permissionDto
     * @return
     */
    @PutMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:permission:edit')")
    @ApiOperation("修改权限信息")
    public DataResponse edit(PermissionDto permissionDto) {
        log.info("PermissionController.edit(), param: {}", permissionDto);

        BaseResult baseResult = permissionService.update(permissionDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 删除权限信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:permission:del')")
    @ApiOperation("删除权限信息")
    public DataResponse delete(Integer id) {
        log.info("PermissionController.delete(), param: {}", id);

        BaseResult baseResult = permissionService.delete(id);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 查询权限tree结构信息
     * @param noButton
     * @return
     */
    @GetMapping("/tree")
    @ResponseBody
    @ApiOperation("查询权限tree结构信息")
    public DataResponse tree(Boolean noButton) {
        log.info("PermissionController.tree()");

        BaseResult baseResult = permissionService.getPermissionsByTree(noButton);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 根据角色id查询角色权限关系
     * @param roleId
     * @return
     */
    @GetMapping("/getPermissionIdsByRoleId")
    @ResponseBody
    @ApiOperation("根据角色id查询角色权限关系")
    public DataResponse getPermissionIdsByRoleId(Integer roleId) {
        log.info("PermissionController.getPermissionIdsByRoleId(), param: " + roleId);

        BaseResult baseResult = rolePermissionService.getPermissionIdsByRoleId(roleId);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 查询菜单信息
     * @param userId
     * @return
     */
    @GetMapping("/menu")
    @ResponseBody
    @ApiOperation("查询菜单信息")
    public DataResponse menu(Integer userId) {
        log.info("PermissionController.userId()");

        BaseResult baseResult = permissionService.getMenu(userId);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }
}
