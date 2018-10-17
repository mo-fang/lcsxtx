SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `tianyruit`.`sys_user`;
DROP TABLE IF EXISTS `tianyruit`.`website_t_log`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '用户的id 登录账号',
  `username` varchar(10) NOT NULL COMMENT '用户姓名',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `sexcode` varchar(1) NOT NULL COMMENT '性别（1-男 2-女）',
  `sfzh` varchar(18) NOT NULL COMMENT '身份证号码',
  `phonenum` varchar(11) NOT NULL COMMENT '电话号码',
  `inserttime` datetime NOT NULL COMMENT '插入时间',
  `status` varchar(1) NOT NULL COMMENT '状态（1-正常 0-逻辑删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';
CREATE TABLE `website_t_log` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `logIp` varchar(16) DEFAULT NULL COMMENT '访问ip',
  `insertTime` datetime DEFAULT NULL COMMENT '访问时间',
  `ipAddress` varchar(128) DEFAULT NULL COMMENT 'ip所在地址',
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `browserInfo` varchar(255) DEFAULT NULL COMMENT '浏览器信息',
  `osInfo` varchar(255) DEFAULT NULL COMMENT '操作系统信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `tianyruit`.`sys_user` WRITE;
DELETE FROM `tianyruit`.`sys_user`;
INSERT INTO `tianyruit`.`sys_user` (`id`,`name`,`username`,`password`,`sexcode`,`sfzh`,`phonenum`,`inserttime`,`status`) VALUES (1, '闫树春', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '1', '2018-07-10 14:39:41', '1');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `tianyruit`.`website_t_log` WRITE;
DELETE FROM `tianyruit`.`website_t_log`;
UNLOCK TABLES;
COMMIT;
