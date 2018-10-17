/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : sxtx

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 17/10/2018 23:04:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oa_auth_ownertype
-- ----------------------------
DROP TABLE IF EXISTS `oa_auth_ownertype`;
CREATE TABLE `oa_auth_ownertype`  (
  `id` int(2) NOT NULL,
  `typeid` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `typename` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `typeid`(`typeid`) USING BTREE,
  INDEX `typeid_2`(`typeid`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限资源的类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_auth_ownertype
-- ----------------------------
INSERT INTO `oa_auth_ownertype` VALUES (1, '1', '角色');
INSERT INTO `oa_auth_ownertype` VALUES (2, '2', '用户');

-- ----------------------------
-- Table structure for oa_sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_auth`;
CREATE TABLE `oa_sys_auth`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `ownerid` int(100) NOT NULL COMMENT '拥有权限的id(角色或者是用户)',
  `ownername` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '又有角色的名称（角色名称或者是用户的名称）',
  `mbid` int(100) DEFAULT NULL COMMENT '按钮和菜单资源的权限',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型1-角色 2-用户',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '最后修改者的时间',
  `updateuser` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后的修改者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mbid`(`mbid`) USING BTREE,
  INDEX `fk_type`(`type`) USING BTREE,
  CONSTRAINT `fk_mbid` FOREIGN KEY (`mbid`) REFERENCES `oa_sys_mandb` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_type` FOREIGN KEY (`type`) REFERENCES `oa_auth_ownertype` (`typeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 361 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_auth
-- ----------------------------
INSERT INTO `oa_sys_auth` VALUES (326, 1, NULL, 1, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (327, 1, NULL, 2, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (328, 1, NULL, 3, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (329, 1, NULL, 102, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (330, 1, NULL, 4, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (331, 1, NULL, 5, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (332, 1, NULL, 151, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (333, 1, NULL, 152, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (334, 1, NULL, 153, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (335, 1, NULL, 129, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (336, 1, NULL, 130, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (337, 1, NULL, 131, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (338, 1, NULL, 132, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (339, 1, NULL, 135, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (340, 1, NULL, 136, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (341, 1, NULL, 137, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (342, 1, NULL, 138, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (343, 1, NULL, 139, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (344, 1, NULL, 140, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (345, 1, NULL, 141, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (346, 1, NULL, 142, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (347, 1, NULL, 143, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (348, 1, NULL, 144, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (349, 1, NULL, 145, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (350, 1, NULL, 146, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (351, 1, NULL, 147, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (352, 1, NULL, 148, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (353, 1, NULL, 149, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (354, 1, NULL, 150, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (355, 1, NULL, 104, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (356, 1, NULL, 106, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (357, 1, NULL, 107, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (358, 1, NULL, 108, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (359, 1, NULL, 134, '1', '2018-10-12 13:38:32', 'admin');
INSERT INTO `oa_sys_auth` VALUES (360, 1, NULL, 103, '1', '2018-10-12 13:38:32', 'admin');

-- ----------------------------
-- Table structure for oa_sys_button
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_button`;
CREATE TABLE `oa_sys_button`  (
  `ID` int(65) NOT NULL COMMENT '代码',
  `BUTTON_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮名称',
  `MARK` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮备注',
  `SHORTNAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮简称',
  `ICON` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮icon',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `SHORTNAME`(`SHORTNAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa按钮表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_button
-- ----------------------------
INSERT INTO `oa_sys_button` VALUES (1, '增加', 'add', 'add', 'add');
INSERT INTO `oa_sys_button` VALUES (2, '删除', 'del', 'del', 'del');
INSERT INTO `oa_sys_button` VALUES (3, '修改', 'edit', 'edit', 'edit');
INSERT INTO `oa_sys_button` VALUES (4, '查询', 'search', 'search', 'search');
INSERT INTO `oa_sys_button` VALUES (5, '赋权限', 'auth', 'auth', 'auth');
INSERT INTO `oa_sys_button` VALUES (7, '详情', 'detail', 'detail', 'detail');

-- ----------------------------
-- Table structure for oa_sys_department
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_department`;
CREATE TABLE `oa_sys_department`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `parentid` int(11) DEFAULT NULL COMMENT '父级部门的id',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `createname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_department
-- ----------------------------
INSERT INTO `oa_sys_department` VALUES (1, '研发部', NULL, '2018-09-30 09:16:28', 'admin', '2018-09-30 09:16:34');
INSERT INTO `oa_sys_department` VALUES (3, '事业发展部', NULL, '2018-09-30 15:47:03', NULL, NULL);
INSERT INTO `oa_sys_department` VALUES (4, '财务部', NULL, '2018-09-29 18:39:57', 'admin', NULL);
INSERT INTO `oa_sys_department` VALUES (5, '企管部', NULL, '2018-10-11 11:24:43', 'admin', NULL);
INSERT INTO `oa_sys_department` VALUES (6, '会计部', NULL, '2018-10-11 11:28:50', 'admin', NULL);

-- ----------------------------
-- Table structure for oa_sys_mandb
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_mandb`;
CREATE TABLE `oa_sys_mandb`  (
  `ID` int(65) NOT NULL AUTO_INCREMENT,
  `MENU_ID` int(65) DEFAULT NULL COMMENT 'menu的id',
  `BUTTON_ID` int(65) DEFAULT NULL COMMENT 'button的id',
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MENU_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'menu的名称',
  `BTNSHORTNAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BTN_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'button的名称',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_menuid`(`MENU_ID`) USING BTREE,
  INDEX `fk_button`(`BUTTON_ID`) USING BTREE,
  INDEX `fk_menu_code`(`MENU_NAME`) USING BTREE,
  INDEX `fk_button_shortname`(`BTN_NAME`) USING BTREE,
  CONSTRAINT `fk_button` FOREIGN KEY (`BUTTON_ID`) REFERENCES `oa_sys_button` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa菜单和按钮关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_mandb
-- ----------------------------
INSERT INTO `oa_sys_mandb` VALUES (1, 5, 1, 'menumsg', '菜单管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (2, 5, 4, 'menumsg', '菜单管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (3, 5, 3, 'menumsg', '菜单管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (4, 6, 5, 'rolemsg', '角色管理', 'auth', '授权');
INSERT INTO `oa_sys_mandb` VALUES (5, 6, 4, 'rolemsg', '角色管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (102, 5, 2, 'menumsg', '菜单管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (103, 7, 4, 'categorymsg', '商品类别管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (104, 4, 4, 'productmsg', '商品管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (106, 4, 1, 'productmsg', '商品管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (107, 4, 2, 'productmsg', '商品管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (108, 4, 3, 'productmsg', '商品管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (129, 9, 1, 'paramsg', '参数管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (130, 9, 2, 'paramsg', '参数管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (131, 9, 3, 'paramsg', '参数管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (132, 9, 4, 'paramsg', '参数管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (134, 4, 7, 'productmsg', '商品管理', 'detail', '详情');
INSERT INTO `oa_sys_mandb` VALUES (135, 11, 1, 'depmsg', '部门管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (136, 11, 2, 'depmsg', '部门管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (137, 11, 3, 'depmsg', '部门管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (138, 11, 4, 'depmsg', '部门管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (139, 11, 5, 'depmsg', '部门管理', 'auth', '赋权限');
INSERT INTO `oa_sys_mandb` VALUES (140, 12, 1, 'usermsg', '用户管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (141, 12, 2, 'usermsg', '用户管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (142, 12, 3, 'usermsg', '用户管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (143, 12, 4, 'usermsg', '用户管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (144, 12, 5, 'usermsg', '用户管理', 'auth', '赋权限');
INSERT INTO `oa_sys_mandb` VALUES (145, 12, 7, 'usermsg', '用户管理', 'detail', '详情');
INSERT INTO `oa_sys_mandb` VALUES (146, 13, 1, 'paramsg', '参数管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (147, 13, 2, 'paramsg', '参数管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (148, 13, 3, 'paramsg', '参数管理', 'edit', '修改');
INSERT INTO `oa_sys_mandb` VALUES (149, 13, 4, 'paramsg', '参数管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (150, 14, 4, 'solrmsg', 'solr管理', 'search', '查询');
INSERT INTO `oa_sys_mandb` VALUES (151, 6, 1, 'rolemsg', '角色管理', 'add', '增加');
INSERT INTO `oa_sys_mandb` VALUES (152, 6, 2, 'rolemsg', '角色管理', 'del', '删除');
INSERT INTO `oa_sys_mandb` VALUES (153, 6, 3, 'rolemsg', '角色管理', 'edit', '修改');

-- ----------------------------
-- Table structure for oa_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_menu`;
CREATE TABLE `oa_sys_menu`  (
  `ID` int(65) NOT NULL AUTO_INCREMENT,
  `MENU_URL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单的链接',
  `MENU_CLASS` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单编号',
  `MENU_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `PARENT_MENUCODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级菜单的编号',
  `SEQUENCE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  `MENU_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单的类型 1 父级菜单 2 子菜单',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建菜单的时间',
  `ICON` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `MENU_CODE`(`MENU_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_menu
-- ----------------------------
INSERT INTO `oa_sys_menu` VALUES (1, '#', '#', 'sysmsg', '系统管理', 'ROOT', NULL, '0', '2018-07-14 11:50:45', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (3, '#', '#', 'mallmsg', '商城管理', 'ROOT', NULL, '0', '2018-09-17 13:50:53', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (4, 'toProductList.html', '#', 'productmsg', '商品管理', 'mallmsg', NULL, '1', '2018-09-29 00:31:17', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (5, 'toMenuList.html', '#', 'menumsg', '菜单管理', 'sysmsg', NULL, '1', '2018-09-26 15:20:06', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (6, 'toRoleList.html', '#', 'rolemsg', '角色管理', 'sysmsg', NULL, '1', '2018-10-12 13:38:21', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (7, 'toCategoryList.html', '#', 'categorymsg', '商品类别管理', 'mallmsg', NULL, '1', '2018-09-26 15:41:05', '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (9, 'toParaList.html', '#', 'paramsg', '参数管理', 'sysmsg', NULL, '1', NULL, '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (11, 'toDepartment.html', '#', 'depmsg', '部门管理', 'sysmsg', NULL, '1', NULL, '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (12, 'toUserList.html', '#', 'usermsg', '用户管理', 'sysmsg', NULL, '1', NULL, '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (13, 'toParaList.html', '#', 'paramsg', '参数管理', 'sysmsg', NULL, '1', NULL, '&#xe620;');
INSERT INTO `oa_sys_menu` VALUES (14, 'toSolr.html', '#', 'solrmsg', 'solr管理', 'sysmsg', NULL, '1', '2018-10-06 18:51:06', '&#xe620;');

-- ----------------------------
-- Table structure for oa_sys_pozts
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_pozts`;
CREATE TABLE `oa_sys_pozts`  (
  `id` int(100) NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位名称',
  `departmentid` int(100) DEFAULT NULL COMMENT '所属部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa职能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_pozts
-- ----------------------------
INSERT INTO `oa_sys_pozts` VALUES (1, '研发', 1);

-- ----------------------------
-- Table structure for oa_sys_qualification
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_qualification`;
CREATE TABLE `oa_sys_qualification`  (
  `id` int(100) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa学历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_qualification
-- ----------------------------
INSERT INTO `oa_sys_qualification` VALUES (1, '本科');
INSERT INTO `oa_sys_qualification` VALUES (2, '专科');
INSERT INTO `oa_sys_qualification` VALUES (3, '高中');
INSERT INTO `oa_sys_qualification` VALUES (4, '大专');
INSERT INTO `oa_sys_qualification` VALUES (5, '中专');
INSERT INTO `oa_sys_qualification` VALUES (6, '小学');
INSERT INTO `oa_sys_qualification` VALUES (7, '其他');
INSERT INTO `oa_sys_qualification` VALUES (8, '研究生');
INSERT INTO `oa_sys_qualification` VALUES (9, '博士生');

-- ----------------------------
-- Table structure for oa_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_role`;
CREATE TABLE `oa_sys_role`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色代码',
  `NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `INSERTTIME` datetime(0) DEFAULT NULL COMMENT '插入时间',
  `INSERTUSERNAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '插入人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_role
-- ----------------------------
INSERT INTO `oa_sys_role` VALUES (1, 'admin', '超级管理员', '2018-09-27 12:57:56', 'admin');
INSERT INTO `oa_sys_role` VALUES (11, 'qiguan', '企管部门权限', '2018-10-12 00:00:00', 'admin');

-- ----------------------------
-- Table structure for oa_sys_sexcode
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_sexcode`;
CREATE TABLE `oa_sys_sexcode`  (
  `id` int(11) NOT NULL,
  `sexcode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别编码',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sexcode`(`sexcode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa性别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_sexcode
-- ----------------------------
INSERT INTO `oa_sys_sexcode` VALUES (1, '1', '男');
INSERT INTO `oa_sys_sexcode` VALUES (2, '2', '女');

-- ----------------------------
-- Table structure for oa_sys_shiro
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_shiro`;
CREATE TABLE `oa_sys_shiro`  (
  `id` int(10) NOT NULL COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称--路径',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保护的类型',
  `sequence` int(3) NOT NULL COMMENT ' 加载的顺序',
  `mark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa shiro保护表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_shiro
-- ----------------------------
INSERT INTO `oa_sys_shiro` VALUES (1, '/**', 'authc', 2, '非静态的其他的资源');
INSERT INTO `oa_sys_shiro` VALUES (2, '/signout.html', 'logout', 1, '登出');
INSERT INTO `oa_sys_shiro` VALUES (3, '/public/bus.js', 'anon', 1, '静态资源');
INSERT INTO `oa_sys_shiro` VALUES (4, '/public/**', 'anon', 1, '静态资源');
INSERT INTO `oa_sys_shiro` VALUES (5, '/public/**/**', 'anon', 1, '静态资源');
INSERT INTO `oa_sys_shiro` VALUES (6, '/pc/css/**', 'anon', 1, '静态资源');
INSERT INTO `oa_sys_shiro` VALUES (7, '/doLogin.html', 'anon', 1, '登录方法');

-- ----------------------------
-- Table structure for oa_sys_uandr
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_uandr`;
CREATE TABLE `oa_sys_uandr`  (
  `id` int(100) NOT NULL,
  `userid` int(100) NOT NULL COMMENT '用户id',
  `roleid` int(100) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_userid`(`userid`) USING BTREE,
  INDEX `fk_roleid`(`roleid`) USING BTREE,
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `oa_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa角色和用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_uandr
-- ----------------------------
INSERT INTO `oa_sys_uandr` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for oa_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_user`;
CREATE TABLE `oa_sys_user`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `departmentid` int(100) DEFAULT NULL COMMENT '部门id',
  `postid` int(100) DEFAULT NULL COMMENT '岗位id',
  `sfzh` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号码',
  `hiredate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入职时间',
  `birthday` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出生日期',
  `qualificationid` int(100) DEFAULT NULL COMMENT '学历',
  `havewife` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '婚否 2-离异 1-婚0-否',
  `phonum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `sexcode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别编码',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭住址',
  `sosname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '紧急联系人',
  `sosphonnum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '紧急人联系方式',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `updateuser` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后操作人',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_sexcode`(`sexcode`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `fk_sexcode` FOREIGN KEY (`sexcode`) REFERENCES `oa_sys_sexcode` (`sexcode`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa 用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_user
-- ----------------------------
INSERT INTO `oa_sys_user` VALUES (1, 'admin', '3d4fa24c14a0f629e1d31dd8b37f3afd', '闫树春', 1, 1, '371502199205067838', '2018-09-30', '1992-05-06', 1, '1', '17600200567', '1', '星美城市广场', '戴xx', '12711171980', '2018-09-04 11:29:51', '1', '1');
INSERT INTO `oa_sys_user` VALUES (2, 'gaojing', 'b0534a6f324dc688d7b15e375d5e7ffc', '高晶', 5, 1, '###', '2018-07-11', '1990-05-11', 1, '1', '16606353013', '2', '高新区', '##', '110', '2018-10-11 13:37:21', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_para
-- ----------------------------
DROP TABLE IF EXISTS `sys_para`;
CREATE TABLE `sys_para`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数名',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数值',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否生效1-生效 0-不生效',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '插入或者修改时间',
  `insertuser` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_username`(`insertuser`) USING BTREE,
  CONSTRAINT `fk_username` FOREIGN KEY (`insertuser`) REFERENCES `oa_sys_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_para
-- ----------------------------
INSERT INTO `sys_para` VALUES (1, 'SYSNAME', '天元瑞特', '1', '2018-09-18 17:06:50', 'admin', '系统的名称');
INSERT INTO `sys_para` VALUES (2, 'PRODUCTIMGPATH', 'E:\\\\file\\\\images\\\\product', '1', '2018-09-28 10:11:19', 'admin', '商品图片的存放位置');
INSERT INTO `sys_para` VALUES (3, 'DEF-PASSWORD', '123456', '1', '2018-10-04 22:12:57', 'admin', '默认密码');
INSERT INTO `sys_para` VALUES (4, 'CORE-NAME', 'tymall', '1', '2018-10-06 18:08:11', 'admin', 'solr的core名称');

-- ----------------------------
-- Table structure for website_t_log
-- ----------------------------
DROP TABLE IF EXISTS `website_t_log`;
CREATE TABLE `website_t_log`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `logIp` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问ip',
  `insertTime` datetime(0) DEFAULT NULL COMMENT '访问时间',
  `ipAddress` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip所在地址',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '来源',
  `browserInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器信息',
  `osInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作系统信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
