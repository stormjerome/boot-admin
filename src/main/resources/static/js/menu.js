/**
 * 获取选中的菜单id
 * @return {Array}
 */
function getCheckedMenuIds() {
    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = treeObj.getCheckedNodes(true);

    var length = nodes.length;
    var ids = [];
    for (var i = 0; i < length; i++) {
        var n = nodes[i];
        var id = n['id'];
        ids.push(id);
    }

    return ids;
}

/**
 * 设置ztree参数
 * @param isRadioType
 * @return
 */
function getSetting(isRadioType) {
    var setting = {
        check: {
            enable: true,
            chkboxType: {
                "Y": "ps",
                "N": "ps"
            }
        },
        async: {
            enable: true,
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {}
    };
    if (isRadioType) {
        setting.check = {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        }
    }
    return setting;
}

/**
 * 获取菜单树
 * @param noButton
 * @returns
 */
function getMenuTree(noButton) {
    var tree = {
        id: -1,
        name: "root",
        open: true
    };

    $.ajax({
        url: "/permission/tree",
        type: "GET",
        async: false,
        data: {
            noButton: noButton == undefined ? false : noButton
        },
        success: function (ret) {
            tree.children  = ret.data;

        }
    });
    return tree;
}

/**
 * 初始化菜单tree选中状态
 * @param ids
 */
function initMenuCheck(ids) {
    var length = ids.length;

    if (length > 0) {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");

        // 设置根节点选中
        var node = treeObj.getNodeByParam("id", -1, null);
        treeObj.checkNode(node, true, false);

        // 设置节点选中
        for (var i = 0; i < length; i++) {
            var node = treeObj.getNodeByParam("id", ids[i], null);
            treeObj.checkNode(node, true, false);
        }
    }
}

/**
 * 初始化单选菜单tree选中状态
 * @param id
 */
function initRadioCheck(id) {
    if (id != undefined) {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var node = treeObj.getNodeByParam("id", id, null);
        treeObj.checkNode(node, true, false);
    }
}