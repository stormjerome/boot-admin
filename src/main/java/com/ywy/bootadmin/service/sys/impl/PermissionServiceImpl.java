package com.ywy.bootadmin.service.sys.impl;

import com.ywy.bootadmin.common.rest.BaseResult;
import com.ywy.bootadmin.common.rest.ResponseCode;
import com.ywy.bootadmin.dao.sys.PermissionDao;
import com.ywy.bootadmin.dto.sys.PermissionDto;
import com.ywy.bootadmin.dto.sys.Tree;
import com.ywy.bootadmin.model.sys.Permission;
import com.ywy.bootadmin.service.sys.PermissionService;
import com.ywy.bootadmin.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 权限Service实现
 *
 * @author wx
 * @date 2020-03-25 11:42
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public BaseResult getAllPermissions() {
        List<Permission> permissionList = permissionDao.list(null);
        return new BaseResult(permissionList);
    }

    @Override
    public BaseResult save(PermissionDto permissionDto) {
        Integer count = permissionDao.save(permissionDto);
        if (count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.SAVE_ERROR);
    }

    @Override
    public BaseResult update(PermissionDto permissionDto) {
        Integer count = permissionDao.update(permissionDto);
        if (count > 0) {
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.UPDATE_ERROR);
    }

    @Override
    public BaseResult delete(Integer id) {
        // 查询是否存在子菜单，如果存在则不允许删除菜单
        // 可以同步删除父节点为id的菜单，如果只有两级菜单没有问题，但如果有两级以上菜单，会出现子菜单没有删除的情况
        List<Permission> permissionList = permissionDao.getPermissionByParentId(id);
        if (permissionList.size() <= 0) {
            // 删除权限信息，通过外键级联删除角色权限关系
            permissionDao.delete(id);
            return new BaseResult();
        }
        return new BaseResult(ResponseCode.PERMISSION_HAVE_CHILDREN);
    }

    @Override
    public BaseResult getPermissionsByTree(Boolean noButton) {
        Integer type = null;
        if (noButton) {
            type = 1;
        }
        List<Permission> permissionList = permissionDao.list(type);
        // 可以查询所有的，根据stream filter过滤type的值
//        permissionList = permissionList.stream().filter(p -> p.getType() == 1).collect(Collectors.toList());
        List<Tree> treeList = TreeUtil.convertTree(permissionList);
        return new BaseResult(treeList);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionDao.getPermissionById(id);
    }

    @Override
    public BaseResult getMenu(Integer userId) {
        List<Permission> permissionList = permissionDao.getPermissionByUserId(userId, 1);
        List<Tree> treeList = TreeUtil.convertTree(permissionList);
        return new BaseResult(treeList);
    }
}
