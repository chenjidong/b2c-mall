/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : b2c-mall

Target Server Type    : MariaDB
Target Server Version : 50557
File Encoding         : 65001

Date: 2020-02-24 19:41:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b2c_admin
-- ----------------------------
DROP TABLE IF EXISTS `b2c_admin`;
CREATE TABLE `b2c_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `role_ids` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '0.冻结 1.激活',
  `last_login_ip` varchar(255) DEFAULT NULL,
  `gmt_last_login` datetime DEFAULT NULL,
  `gmt_update` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`(30)) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of b2c_admin
-- ----------------------------
INSERT INTO `b2c_admin` VALUES ('1', 'admin', 'e7492a74a978b0b549941b3c85af9390', '18584669549', '', '[1]', '1', '127.0.0.1', '2019-04-08 22:12:04', '2019-04-17 12:23:21', '2019-04-08 22:12:12');
INSERT INTO `b2c_admin` VALUES ('23', 'guest', 'e7492a74a978b0b549941b3c85af9390', '17132358876', null, '[12]', '1', '0.0.0.0', '1997-01-20 00:00:00', '2019-08-11 13:20:55', '2019-08-11 12:34:29');
INSERT INTO `b2c_admin` VALUES ('24', '123', '8a3379ae294935afa4195fa27600fd43', '123', null, '[17]', '1', '0.0.0.0', '1997-01-20 00:00:00', '2020-02-24 19:33:32', '2020-01-21 02:47:25');

-- ----------------------------
-- Table structure for b2c_role
-- ----------------------------
DROP TABLE IF EXISTS `b2c_role`;
CREATE TABLE `b2c_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) DEFAULT NULL,
  `desc` varchar(1023) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0.冻结 1.激活',
  `gmt_update` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of b2c_role
-- ----------------------------
INSERT INTO `b2c_role` VALUES ('1', '超级管理员', '所有模块的权限', '1', '2019-04-08 22:40:45', '2019-04-08 22:40:50');
INSERT INTO `b2c_role` VALUES ('12', '游客权限', '重要部分没有编辑权限', '1', '2019-08-11 12:32:16', '2019-08-11 12:32:16');
INSERT INTO `b2c_role` VALUES ('17', '233', '233', '1', '2020-02-15 01:51:14', '2020-02-15 01:51:14');

-- ----------------------------
-- Table structure for b2c_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `b2c_role_permission`;
CREATE TABLE `b2c_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission` varchar(63) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `gmt_update` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `label` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=522 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of b2c_role_permission
-- ----------------------------
INSERT INTO `b2c_role_permission` VALUES ('1', '1', '*', '0', '2019-01-01 00:00:00', '2019-01-01 00:00:00', '', null);
INSERT INTO `b2c_role_permission` VALUES ('2', '0', '*', '0', '2020-02-15 02:53:51', '2020-02-15 02:53:54', '超级管理权限', null);
INSERT INTO `b2c_role_permission` VALUES ('343', '0', 'admin:admin:update', '0', '2019-05-04 12:28:02', '2019-05-04 12:28:02', '管理员修改', '/admin/admin/create');
INSERT INTO `b2c_role_permission` VALUES ('465', '0', 'admin:role:permissionList', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '角色授权', '/admin/permission/create');
INSERT INTO `b2c_role_permission` VALUES ('466', '0', 'admin:permission:list', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '权限列表', '/admin/permission/list');
INSERT INTO `b2c_role_permission` VALUES ('467', '0', 'admin:role:update', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '角色修改', '/admin/role/create');
INSERT INTO `b2c_role_permission` VALUES ('468', '0', 'admin:role:list', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '角色列表', '/admin/role/list');
INSERT INTO `b2c_role_permission` VALUES ('469', '0', 'admin:role:delete', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '角色删除', '/admin/role/delete');
INSERT INTO `b2c_role_permission` VALUES ('470', '0', 'admin:role:create', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '角色新增', '/admin/admin/create');
INSERT INTO `b2c_role_permission` VALUES ('475', '0', 'admin:admin:update', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '管理员修改', '/admin/admin/create');
INSERT INTO `b2c_role_permission` VALUES ('476', '0', 'admin:admin:list', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '管理员列表', '/admin/admin/list');
INSERT INTO `b2c_role_permission` VALUES ('477', '0', 'admin:admin:delete', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '管理员删除', '/admin/admin/delete');
INSERT INTO `b2c_role_permission` VALUES ('478', '0', 'admin:admin:create', '0', '2019-08-11 12:40:28', '2019-08-11 12:40:28', '管理员新增', '/admin/admin/create');
INSERT INTO `b2c_role_permission` VALUES ('515', '0', 'admin:account:list', '0', '2020-02-22 20:00:08', '2020-02-22 20:00:12', '会员列表', '/admin/account/list');
INSERT INTO `b2c_role_permission` VALUES ('516', '0', 'admin:account:create', '0', '2020-02-22 20:00:42', '2020-02-22 20:00:46', '会员添加', '/admin/account/createByAdmin');
INSERT INTO `b2c_role_permission` VALUES ('517', '0', 'admin:account:update', '0', '2020-02-22 20:01:03', '2020-02-22 20:01:05', '会员修改', '/admin/account/createByAdmin');
INSERT INTO `b2c_role_permission` VALUES ('518', '0', 'admin:account:delete', '0', '2020-02-22 20:01:25', '2020-02-22 20:01:29', '会员删除', '/admin/account/delete');
INSERT INTO `b2c_role_permission` VALUES ('519', '0', 'admin:account:status', '0', '2020-02-24 18:07:34', '2020-02-24 18:07:37', '会员状态', '/admin/account/updateStatus');
INSERT INTO `b2c_role_permission` VALUES ('520', '17', 'admin:role:list', '0', '2020-02-24 19:32:43', '2020-02-24 19:32:43', '角色列表', null);
INSERT INTO `b2c_role_permission` VALUES ('521', '17', 'admin:admin:list', '0', '2020-02-24 19:32:43', '2020-02-24 19:32:43', '管理员列表', null);
