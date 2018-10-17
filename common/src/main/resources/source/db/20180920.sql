/*
 Navicat Premium Data Transfer

 Source Server         : 610
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 60.213.233.210:3306
 Source Schema         : tianyrt_website

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 20/09/2018 17:07:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `phonenum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `inserttime` datetime(0) DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商城的用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES (1, 'admin', '123123', '闫树春', '17600200567', '2018-09-11 17:05:58');
INSERT INTO `mall_user` VALUES (2, 'admin', '123123', NULL, NULL, '2018-09-14 15:17:28');

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
-- Table structure for oa_mall_category
-- ----------------------------
DROP TABLE IF EXISTS `oa_mall_category`;
CREATE TABLE `oa_mall_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL COMMENT '父类id 当id为0的时候说明为根节点顶级',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别名称\n',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别的状态 0的时候废弃  1的时候使用',
  `sortorder` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序编号 展示顺序 当数字相同的时候 听天由命',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '最后一次操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_mall_category
-- ----------------------------
INSERT INTO `oa_mall_category` VALUES (1, 0, '智能家具', '1', '1', '2018-09-18 12:17:24', '2018-09-18 12:17:27');
INSERT INTO `oa_mall_category` VALUES (2, NULL, '智能出行', NULL, '1', '2018-09-19 11:18:14', '2018-09-20 14:49:56');
INSERT INTO `oa_mall_category` VALUES (12, 1, '扫地机器人', NULL, NULL, '2018-09-20 09:58:16', '2018-09-20 14:50:32');
INSERT INTO `oa_mall_category` VALUES (13, 1, '智能水缸', NULL, NULL, '2018-09-20 10:04:17', '2018-09-20 14:50:38');
INSERT INTO `oa_mall_category` VALUES (24, 1, '123', '1', NULL, '2018-09-20 14:39:10', '2018-09-20 14:39:10');
INSERT INTO `oa_mall_category` VALUES (25, 2, '12312', '1', NULL, '2018-09-20 14:41:50', '2018-09-20 14:41:50');
INSERT INTO `oa_mall_category` VALUES (26, 1, '12312', '1', NULL, '2018-09-20 14:48:57', '2018-09-20 14:48:57');

-- ----------------------------
-- Table structure for oa_mall_product
-- ----------------------------
DROP TABLE IF EXISTS `oa_mall_product`;
CREATE TABLE `oa_mall_product`  (
  `id` int(100) NOT NULL COMMENT '商品的id',
  `categoryid` int(11) DEFAULT NULL COMMENT '分类的信息  对应的是类别表的主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品的名称',
  `subtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小标题',
  `mainimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主图地址',
  `subimages` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '副图片地址',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商品的详情',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '商品的价格',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '1在售2下架3删除',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category`(`categoryid`) USING BTREE,
  CONSTRAINT `fk_category` FOREIGN KEY (`categoryid`) REFERENCES `oa_mall_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_mall_product
-- ----------------------------
INSERT INTO `oa_mall_product` VALUES (1, 1, '扫地机器人', '扫地机器人', NULL, '', '扫地机器人', 1000.00, 100, '1', '2018-09-18 12:16:30', '2018-09-18 12:16:43');

-- ----------------------------
-- Table structure for oa_sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_auth`;
CREATE TABLE `oa_sys_auth`  (
  `id` int(100) NOT NULL,
  `ownerid` int(100) NOT NULL COMMENT '拥有权限的id(角色或者是用户)',
  `ownername` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '又有角色的名称（角色名称或者是用户的名称）',
  `mbid` int(100) DEFAULT NULL COMMENT '按钮和菜单资源的权限',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型1-角色 2-用户',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '最后修改者的时间',
  `updateuser` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后的修改者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mbid`(`mbid`) USING BTREE,
  INDEX `fk_type`(`type`) USING BTREE,
  CONSTRAINT `fk_mbid` FOREIGN KEY (`mbid`) REFERENCES `oa_sys_mandb` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_type` FOREIGN KEY (`type`) REFERENCES `oa_auth_ownertype` (`typeid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_auth
-- ----------------------------
INSERT INTO `oa_sys_auth` VALUES (1, 1, 'admin', 1, '1', '2018-09-04 11:31:53', 'admin');
INSERT INTO `oa_sys_auth` VALUES (2, 1, 'yansc', 1, '2', '2018-09-04 11:56:38', 'amdin');
INSERT INTO `oa_sys_auth` VALUES (3, 1, 'admin', 2, '1', '2018-09-18 11:20:59', 'admin');
INSERT INTO `oa_sys_auth` VALUES (4, 1, 'admin', 3, '1', '2018-09-18 11:24:17', 'admin');
INSERT INTO `oa_sys_auth` VALUES (5, 1, 'admin', 4, '1', '2018-09-18 15:24:43', 'admin');
INSERT INTO `oa_sys_auth` VALUES (6, 1, 'admin', 5, '1', '2018-09-19 09:17:19', 'admin');

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

-- ----------------------------
-- Table structure for oa_sys_mandb
-- ----------------------------
DROP TABLE IF EXISTS `oa_sys_mandb`;
CREATE TABLE `oa_sys_mandb`  (
  `ID` int(65) NOT NULL AUTO_INCREMENT,
  `MENU_ID` int(65) DEFAULT NULL,
  `BUTTON_ID` int(65) DEFAULT NULL COMMENT 'button的id',
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BTNSHORTNAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'button的简称',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_menuid`(`MENU_ID`) USING BTREE,
  INDEX `fk_button`(`BUTTON_ID`) USING BTREE,
  INDEX `fk_menu_code`(`MENU_CODE`) USING BTREE,
  INDEX `fk_button_shortname`(`BTNSHORTNAME`) USING BTREE,
  CONSTRAINT `fk_button` FOREIGN KEY (`BUTTON_ID`) REFERENCES `oa_sys_button` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_button_shortname` FOREIGN KEY (`BTNSHORTNAME`) REFERENCES `oa_sys_button` (`SHORTNAME`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_menu` FOREIGN KEY (`MENU_ID`) REFERENCES `oa_sys_menu` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_code` FOREIGN KEY (`MENU_CODE`) REFERENCES `oa_sys_menu` (`MENU_CODE`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa菜单和按钮关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_mandb
-- ----------------------------
INSERT INTO `oa_sys_mandb` VALUES (1, 2, 1, 'usermsg', 'add');
INSERT INTO `oa_sys_mandb` VALUES (2, 6, 1, 'rolemsg', 'add');
INSERT INTO `oa_sys_mandb` VALUES (3, 6, 2, 'rolemsg', 'del');
INSERT INTO `oa_sys_mandb` VALUES (4, 4, 2, 'productmsg', 'del');
INSERT INTO `oa_sys_mandb` VALUES (5, 7, 1, 'categorymsg', 'search');

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
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_menu
-- ----------------------------
INSERT INTO `oa_sys_menu` VALUES (1, '#', '#', 'sysmsg', '系统管理', 'ROOT', NULL, '0', '2018-07-14 11:50:45', 'sysmsg');
INSERT INTO `oa_sys_menu` VALUES (2, 'toUserList.html', '#', 'usermsg', '用户管理', 'sysmsg', NULL, '1', '2018-07-14 15:55:44', 'usermsg');
INSERT INTO `oa_sys_menu` VALUES (3, '#', '#', 'mallmsg', '商城管理', 'ROOT', NULL, '0', '2018-09-17 13:50:53', 'mallmsg');
INSERT INTO `oa_sys_menu` VALUES (4, 'toProductList.html', '#', 'productmsg', '商品管理', 'mallmsg', NULL, '1', '2018-09-17 17:27:54', 'goodsmsg');
INSERT INTO `oa_sys_menu` VALUES (6, 'toRoleList.html', '#', 'rolemsg', '角色管理', 'sysmsg', NULL, '1', '2018-09-18 10:13:00', 'rolemsg');
INSERT INTO `oa_sys_menu` VALUES (7, 'toCategoryList.html', '#', 'categorymsg', '商品类别管理', 'mallmsg', NULL, '1', '2018-09-19 09:15:57', 'mallmsg');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_role
-- ----------------------------
INSERT INTO `oa_sys_role` VALUES (1, 'admin', '超级管理员', '2018-09-04 10:52:08', 'admin');

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
  CONSTRAINT `fk_roleid` FOREIGN KEY (`roleid`) REFERENCES `oa_sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
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
  `phonum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `sexcode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别编码',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `updateuser` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后操作人',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_sexcode`(`sexcode`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `fk_sexcode` FOREIGN KEY (`sexcode`) REFERENCES `oa_sys_sexcode` (`sexcode`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oa 用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_sys_user
-- ----------------------------
INSERT INTO `oa_sys_user` VALUES (1, 'admin', '3d4fa24c14a0f629e1d31dd8b37f3afd', '闫树春', '17600200567', '1', '2018-09-04 11:29:51', '1', '1');

-- ----------------------------
-- Table structure for sys_para
-- ----------------------------
DROP TABLE IF EXISTS `sys_para`;
CREATE TABLE `sys_para`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数名',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数值',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否生效',
  `updatetime` datetime(0) DEFAULT NULL COMMENT '插入或者修改时间',
  `insertuser` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_username`(`insertuser`) USING BTREE,
  CONSTRAINT `fk_username` FOREIGN KEY (`insertuser`) REFERENCES `oa_sys_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_para
-- ----------------------------
INSERT INTO `sys_para` VALUES (1, 'OANAME', '天元瑞特自动化管理系统', '1', '2018-09-18 17:06:50', 'admin');

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
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_t_log
-- ----------------------------


SET FOREIGN_KEY_CHECKS = 1;
