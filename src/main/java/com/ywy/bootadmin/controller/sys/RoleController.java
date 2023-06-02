package com.ywy.bootadmin.controller.sys;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.DataResponse;
import com.ywy.bootadmin.common.rest.PageInfo;
import com.ywy.bootadmin.dto.sys.RoleDto;
import com.ywy.bootadmin.model.sys.Role;
import com.ywy.bootadmin.service.sys.RoleService;
import com.ywy.bootadmin.service.sys.RoleUserService;
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
 * 角色Controller
 *
 * @author ywy
 * @date 2020-03-20 12:38
 */
@Controller
@RequestMapping("/role")
@Slf4j
@Api(description = "角色相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    /**
     * 角色列表
     * @param pageInfo
     * @param role
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    @ApiOperation("分页查询角色列表")
    public DataResponse list(PageInfo pageInfo, Role role) {
        log.info("RoleController.list(), param: {}, {}", pageInfo, role);

        HashMap<String, Object> paramsMap = MapUtil.convertToMap(role);
        BaseResult baseResult = roleService.listByPage(pageInfo.getOffset(), pageInfo.getLimit(), paramsMap);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到添加角色页面
     * @return
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @ApiOperation("跳转到添加角色页面")
    public String toAdd() {
        return "/role/role-add";
    }

    /**
     * 添加角色信息
     * @param roleDto
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:add')")
    @ApiOperation("添加角色信息")
    public DataResponse add(RoleDto roleDto) {
        log.info("RoleController.add(), param: {}", roleDto);

        BaseResult baseResult = roleService.save(roleDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 跳转到修改角色页面
     * @return
     */
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation("跳转到修改角色页面")
    public String toEdit(Model model, Integer id) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "/role/role-edit";
    }

    /**
     * 修改角色信息
     * @param roleDto
     * @return
     */
    @PutMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation("修改角色信息")
    public DataResponse edit(RoleDto roleDto) {
        log.info("RoleController.edit(), param: {}", roleDto);

        BaseResult baseResult = roleService.update(roleDto);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:del')")
    @ApiOperation("删除角色信息")
    public DataResponse delete(Integer id) {
        log.info("RoleController.delete(), param: {}", id);

        BaseResult baseResult = roleService.delete(id);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 查询所有角色信息
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("查询所有角色信息")
    public DataResponse getAllRoles() {
        log.info("RoleController.getAllRoles()");

        BaseResult baseResult = roleService.getAllRoles();
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }

    /**
     * 根据用户id查询角色用户关系
     * @param userId
     * @return
     */
    @GetMapping("/getRoleUserByUserId")
    @ResponseBody
    @ApiOperation("根据用户id查询角色用户关系")
    public DataResponse getRoleUserByUserId(Integer userId) {
        log.info("RoleController.getRoleUserByUserId(), param: " + userId);

        BaseResult baseResult = roleUserService.getRoleIdByUserId(userId);
        if (baseResult.isSuccess()) {
            return DataResponse.success(baseResult.getData());
        }
        return DataResponse.failure(baseResult.getError());
    }
}
