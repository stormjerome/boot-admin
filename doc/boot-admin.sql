/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 19/04/2020 16:15:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `iconfont` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标字体',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（1：菜单；2：按钮）',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源标识',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, -1, '系统管理', '&#xe6ae;', '', 1, '', 1, '2020-03-27 11:21:42', '2020-04-19 03:47:47');
INSERT INTO `sys_permission` VALUES (2, 1, '用户管理', '&#xe6b8;', '/router?pageName=user/user-list', 1, NULL, 101, '2020-03-27 11:20:51', '2020-04-19 03:48:19');
INSERT INTO `sys_permission` VALUES (3, 2, '查询', NULL, NULL, 2, 'sys:user:query', 10101, '2020-03-27 11:20:58', '2020-04-19 02:09:39');
INSERT INTO `sys_permission` VALUES (4, 2, '新增', NULL, NULL, 2, 'sys:user:add', 10102, '2020-03-27 11:21:03', '2020-04-19 02:09:43');
INSERT INTO `sys_permission` VALUES (5, 2, '删除', NULL, NULL, 2, 'sys:user:del', 10103, '2020-03-27 11:21:08', '2020-04-19 02:09:45');
INSERT INTO `sys_permission` VALUES (6, 2, '修改', NULL, NULL, 2, 'sys:user:edit', 10104, '2020-03-27 11:25:09', '2020-04-19 02:09:51');
INSERT INTO `sys_permission` VALUES (7, 1, '角色管理', '&#xe732;', '/router?pageName=role/role-list', 1, NULL, 102, '2020-03-27 11:22:05', '2020-04-19 03:49:42');
INSERT INTO `sys_permission` VALUES (8, 7, '查询', NULL, NULL, 2, 'sys:role:query', 10201, '2020-03-27 11:22:11', '2020-04-19 02:09:54');
INSERT INTO `sys_permission` VALUES (9, 7, '新增', NULL, NULL, 2, 'sys:role:add', 10202, '2020-03-27 11:22:15', '2020-04-19 02:09:58');
INSERT INTO `sys_permission` VALUES (10, 7, '删除', NULL, NULL, 2, 'sys:role:del', 10203, '2020-03-27 11:22:20', '2020-04-19 02:10:01');
INSERT INTO `sys_permission` VALUES (11, 7, '修改', NULL, NULL, 2, 'sys:role:edit', 10204, '2020-03-27 11:25:03', '2020-04-19 02:10:06');
INSERT INTO `sys_permission` VALUES (12, 1, '菜单管理', '&#xe71c;', '/router?pageName=permission/permission-list', 1, NULL, 103, '2020-03-27 11:21:50', '2020-04-19 04:06:31');
INSERT INTO `sys_permission` VALUES (13, 12, '查询', NULL, NULL, 2, 'sys:permission:query', 10301, '2020-03-27 11:21:54', '2020-04-19 02:10:09');
INSERT INTO `sys_permission` VALUES (14, 12, '新增', NULL, NULL, 2, 'sys:permission:add', 10302, '2020-03-27 11:21:59', '2020-04-19 02:10:15');
INSERT INTO `sys_permission` VALUES (15, 12, '删除', NULL, NULL, 2, 'sys:permission:del', 10303, '2020-03-27 11:22:02', '2020-04-19 02:10:20');
INSERT INTO `sys_permission` VALUES (16, 12, '修改', NULL, NULL, 2, 'sys:permission:edit', 10304, '2020-03-27 11:23:15', '2020-04-19 02:10:26');
INSERT INTO `sys_permission` VALUES (17, -1, '文件管理', '&#xe83c;', NULL, 1, NULL, 2, '2020-03-27 11:22:24', '2020-04-19 03:54:01');
INSERT INTO `sys_permission` VALUES (18, 17, '查询', NULL, NULL, 2, 'sys:file:query', 20101, '2020-03-27 11:22:26', '2020-04-19 02:10:42');
INSERT INTO `sys_permission` VALUES (19, 17, '删除', NULL, NULL, 2, 'sys:file:del', 20102, '2020-03-27 11:22:28', '2020-04-19 02:10:51');
INSERT INTO `sys_permission` VALUES (20, -1, '系统监控', '&#xe820;', NULL, 1, NULL, 3, '2020-03-27 14:52:15', '2020-04-19 03:54:42');
INSERT INTO `sys_permission` VALUES (21, 20, '数据源监控', '&#xe757;', '/druid/index.html', 1, NULL, 301, '2020-03-27 11:22:34', '2020-04-19 03:56:16');
INSERT INTO `sys_permission` VALUES (22, 20, '日志查询', '&#xe74e;', '', 1, 'sys:log:query', 302, '2020-03-27 11:22:57', '2020-04-19 16:11:21');
INSERT INTO `sys_permission` VALUES (23, -1, '开发工具', '&#xe71b;', NULL, 1, NULL, 4, '2020-03-27 14:53:38', '2020-04-19 04:06:35');
INSERT INTO `sys_permission` VALUES (24, 23, '接口swagger', '&#xe6d4;', '/swagger-ui.html', 1, '', 401, '2020-03-27 11:22:45', '2020-04-19 03:58:05');
INSERT INTO `sys_permission` VALUES (25, 23, '代码生成', '&#xe714;', '/router?pageName=generate/generate-list', 1, 'sys:generate:list', 402, '2020-03-27 11:22:52', '2020-04-19 04:03:27');
INSERT INTO `sys_permission` VALUES (26, -1, '个人中心', '&#xe70b;', NULL, 1, NULL, 5, '2020-03-27 14:57:14', '2020-04-19 03:56:32');
INSERT INTO `sys_permission` VALUES (27, 26, '修改密码', '&#xe82b;', '/router?pageName=user/user-password', 1, 'sys:user:password', 501, '2020-03-27 11:21:13', '2020-04-19 16:01:09');
INSERT INTO `sys_permission` VALUES (28, 23, '代码生成测试', NULL, '/router?pageName=test/test-list', 1, '', 403, '2020-04-18 23:44:06', '2020-04-19 04:18:21');
INSERT INTO `sys_permission` VALUES (29, 28, '查询', NULL, '', 2, 'test:query', 40301, '2020-04-19 00:00:11', '2020-04-19 04:18:21');
INSERT INTO `sys_permission` VALUES (30, 28, '新增', NULL, '', 2, 'test:add', 40302, '2020-04-19 00:47:22', '2020-04-19 04:18:21');
INSERT INTO `sys_permission` VALUES (31, 28, '删除', NULL, '', 2, 'test:del', 40303, '2020-04-19 00:48:46', '2020-04-19 04:18:21');
INSERT INTO `sys_permission` VALUES (32, 28, '修改', NULL, '', 2, 'test:edit', 40304, '2020-04-19 00:49:59', '2020-04-19 04:18:21');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员', '2020-03-20 13:33:57', NULL);
INSERT INTO `sys_role` VALUES (2, 'USER', '普通用户', '2020-03-20 13:34:39', NULL);
INSERT INTO `sys_role` VALUES (3, 'GUEST', '访问者', '2020-03-20 13:34:51', NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `fk_rp_permissionId`(`permission_id`) USING BTREE,
  CONSTRAINT `fk_rp_permissionId` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_rp_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限关系\r\n删除role数据时级联删除对应role_permission' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 27);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_ru_roleId`(`role_id`) USING BTREE,
  CONSTRAINT `fk_ru_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_ru_userId` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-用户关系\r\n删除user数据时级联删除对应role_user\r\n删除role数据时如果存在role_user则不允许删除' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1);
INSERT INTO `sys_role_user` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `head_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT 1 COMMENT '性别（0：女；1：男）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（0：禁用；1：正常；2：锁定）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$vNZomTuw3G/IUchfIvptS.TC5Rhd3.8BLb9p9sxmBzTNeb15bsSRS', '超级管理员', NULL, '18012345678', 'admin@qq.com', '1991-12-31', 1, 1, '2020-03-19 23:55:02', '2020-04-16 14:59:15');
INSERT INTO `sys_user` VALUES (2, 'user1', '$2a$10$izwxAeYjMmRM8ZVLHw9ld.UEuV382zrVit7TQGYXuhrLdza5Rg34e', '测试用户1', NULL, '18112345678', '123456@qq.com', '2020-04-08', 0, 1, '2020-03-19 23:55:14', '2020-04-09 15:03:26');
INSERT INTO `sys_user` VALUES (3, 'user2', '123456', '用户2', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:56:04');
INSERT INTO `sys_user` VALUES (4, 'user3', '123456', '用户3', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:12');
INSERT INTO `sys_user` VALUES (5, 'user4', '123456', '用户4', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:09');
INSERT INTO `sys_user` VALUES (6, 'user5', '123456', '用户5', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:07');
INSERT INTO `sys_user` VALUES (7, 'user6', '123456', '用户6', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:05');
INSERT INTO `sys_user` VALUES (8, 'user7', '123456', '用户7', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:03');
INSERT INTO `sys_user` VALUES (9, 'user8', '123456', '用户8', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-20 00:00:01');
INSERT INTO `sys_user` VALUES (10, 'user9', '123456', '用户9', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:53');
INSERT INTO `sys_user` VALUES (11, 'user10', '123456', '用户10', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:50');
INSERT INTO `sys_user` VALUES (12, 'user11', '123456', '用户11', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:21');
INSERT INTO `sys_user` VALUES (13, 'user12', '123456', '用户12', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:18');
INSERT INTO `sys_user` VALUES (14, 'user13', '123456', '用户13', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:17');
INSERT INTO `sys_user` VALUES (15, 'user14', '123456', '用户14', NULL, NULL, NULL, NULL, 1, 1, '2020-03-19 23:55:14', '2020-03-19 23:59:16');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `field1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段1',
  `field2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段2',
  `field3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段3',
  `field4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段4',
  `field5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段5',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (2, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (3, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (4, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (5, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (6, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (7, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (8, '测试数据', '测试数据', '测试数据', '测试数据', '测试数据');
INSERT INTO `test` VALUES (9, '测试数据', '测试数据', '测试数据1', '测试数据1', '测试数据1');
INSERT INTO `test` VALUES (10, '测试数据111', '测试数据111', '测试数据111', '测试数据111', '测试数据111');

SET FOREIGN_KEY_CHECKS = 1;
