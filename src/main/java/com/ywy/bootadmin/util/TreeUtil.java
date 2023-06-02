package com.ywy.bootadmin.util;

import com.ywy.bootadmin.dto.sys.Tree;
import com.ywy.bootadmin.model.sys.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * 树型工具
 *
 * @author ywy
 * @date 2020-03-27 10:14
 */
public class TreeUtil {
    /**
     * 将list结构转换为tree结构，根节点id为-1
     * @param permissionList
     * @return
     */
    public static List<Tree> convertTree(List<Permission> permissionList) {
        List<Tree> treeList = new ArrayList<>();
        convertTree(-1, permissionList, treeList);
        return treeList;
    }

    /**
     * 将list结构转换为tree结构
     * @param parentId
     * @param permissionList
     * @param treeList
     */
    private static void convertTree(Integer parentId, List<Permission> permissionList, List<Tree> treeList) {
        for (Permission permission : permissionList) {
            // 如果父节点为parentId则加入
            if (permission.getParentId().equals(parentId)) {
                Tree parent = new Tree(permission.getId(), permission.getName(), permission.getIconfont(), permission.getUrl(), permission.getParentId());
                treeList.add(parent);
                // 如果存在子节点则递归查找子节点
                if (permissionList.stream().filter(p -> p.getParentId().equals(permission.getId())).findAny().isPresent()) {
                    List<Tree> children = new ArrayList<>();
                    parent.setChildren(children);
                    convertTree(permission.getId(), permissionList, children);
                }
            }
        }
    }
}
