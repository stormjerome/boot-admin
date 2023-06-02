package com.ywy.bootadmin.dto.sys;

import lombok.Data;
import java.util.List;

/**
 * 树型结构
 *
 * @author ywy
 * @date 2020-03-28 10:35
 */
@Data
public class Tree {
    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标字体
     */
    private String iconfont;

    /**
     * 链接
     */
    private String url;

    /**
     * 父类id
     */
    private Integer pId;

    /**
     * 是否展开
     */
    private Boolean open = true;

    /**
     * 子节点
     */
    private List<Tree> children;

    public Tree() {
    }

    public Tree(Integer id, String name, String iconfont, String url, Integer pId) {
        this.id = id;
        this.name = name;
        this.iconfont = iconfont;
        this.url = url;
        this.pId = pId;
    }
}
