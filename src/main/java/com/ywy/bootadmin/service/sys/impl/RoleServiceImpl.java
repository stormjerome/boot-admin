package com.ywy.bootadmin.service.sys.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.PageResult;
import com.ywy.bootadmin.common.rest.ResponseCode;
import com.ywy.bootadmin.dao.sys.RoleDao;
import com.ywy.bootadmin.dao.sys.RolePermissionDao;
import com.ywy.bootadmin.dao.sys.RoleUserDao;
import com.ywy.bootadmin.dto.sys.RoleDto;
import com.ywy.bootadmin.model.sys.Role;
import com.ywy.bootadmin.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现
 *
 * @author ywy
 * @date 2020-03-20 12:43
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public BaseResult listByPage(Integer offset, Integer limit, Map<String, Object> params) {
        Integer count = roleDao.count(params);
        List<Role> userList = roleDao.listByPage(offset, limit, params);
        PageResult<Role> result = new PageResult<>();
        result.setTotal(count);
        result.setDatas(userList);
        return new BaseResult(result);
    }

    @Override
    public BaseResult save(RoleDto roleDto) {
        // 保存角色信息
        roleDao.save(roleDto);

        // 保存角色权限关系
        List<Integer> permissionIds = roleDto.getPermissionIds();
        // 移除根节点id
        permissionIds.remove(Integer.valueOf(-1));

        if (!CollectionUtils.isEmpty(permissionIds)) {
            // 保存角色权限关系
            rolePermissionDao.batchSave(roleDto.getId(), permissionIds);
        }
        return new BaseResult();
    }

    @Override
    public BaseResult update(RoleDto roleDto) {
        // 修改角色信息
        roleDao.update(roleDto);

        // 删除该角色的所有权限
        rolePermissionDao.delete(roleDto.getId());

        List<Integer> permissionIds = roleDto.getPermissionIds();
        // 移除根节点id
        permissionIds.remove(Integer.valueOf(-1));

        if (!CollectionUtils.isEmpty(permissionIds)) {
            // 保存角色权限关系
            rolePermissionDao.batchSave(roleDto.getId(), permissionIds);
        }
        return new BaseResult();
    }

    @Override
    public BaseResult delete(Integer id) {
        // 查询是否存在角色用户关系，如果存在则不允许删除角色
        List<Integer> userIds = roleUserDao.getUserIdsByRoleId(id);
        if (userIds.size() <= 0) {
            // 删除角色信息，通过外键级联删除角色权限信息
            roleDao.delete(id);
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.USER_ROLE_NO_CLEAR);
    }

    @Override
    public BaseResult getAllRoles() {
        List<Role> roleList = roleDao.list();
        return new BaseResult(roleList);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDao.getById(id);
    }
}
