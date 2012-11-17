# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.28-log
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2012-11-17 14:13:52
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for nutz_right_
DROP DATABASE IF EXISTS `nutz_right`;
CREATE DATABASE IF NOT EXISTS `nutz_right` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nutz_right`;


# Dumping structure for table nutz_right.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL COMMENT '对应的访问链接',
  `parent_id` bigint(64) DEFAULT NULL COMMENT '父结点ID',
  `indexs` int(32) DEFAULT NULL COMMENT '排序字段',
  `show_menu` int(32) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `indexs` (`indexs`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.menu: ~6 rows (approximately)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `name`, `url`, `parent_id`, `indexs`, `show_menu`) VALUES
	(1, '系统管理', '', 0, 0, 1),
	(3, '角色管理', '/role_mgr/list', 1, 2, 1),
	(23, '菜单树管理', '/menu/toEditByTree', 1, 3, 1),
	(24, '系统初始化操作', '/system/system_set', 1, 1, 1),
	(25, '用户管理', '/user/list', 1, 4, 1),
	(26, '通用的项目', NULL, 0, 5, 0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


# Dumping structure for table nutz_right.menu_operation
DROP TABLE IF EXISTS `menu_operation`;
CREATE TABLE IF NOT EXISTS `menu_operation` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(64) DEFAULT NULL,
  `operation_id` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `operation_id` (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.menu_operation: ~32 rows (approximately)
/*!40000 ALTER TABLE `menu_operation` DISABLE KEYS */;
INSERT INTO `menu_operation` (`id`, `menu_id`, `operation_id`) VALUES
	(38, 26, 70),
	(39, 26, 68),
	(40, 26, 37),
	(41, 26, 42),
	(42, 26, 45),
	(43, 26, 69),
	(44, 26, 54),
	(45, 24, 64),
	(46, 24, 48),
	(47, 3, 52),
	(48, 3, 44),
	(49, 3, 41),
	(50, 3, 59),
	(51, 3, 36),
	(52, 3, 57),
	(53, 3, 58),
	(54, 3, 55),
	(55, 23, 62),
	(56, 23, 49),
	(57, 23, 63),
	(58, 23, 51),
	(59, 23, 60),
	(60, 23, 69),
	(61, 23, 61),
	(62, 23, 53),
	(63, 23, 43),
	(64, 25, 40),
	(65, 25, 47),
	(66, 25, 66),
	(67, 25, 39),
	(68, 25, 65),
	(69, 25, 50);
/*!40000 ALTER TABLE `menu_operation` ENABLE KEYS */;


# Dumping structure for table nutz_right.operation
DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `unique_code` varchar(800) NOT NULL,
  `path` varchar(500) DEFAULT NULL,
  `class_name` varchar(500) DEFAULT NULL,
  `method_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.operation: ~35 rows (approximately)
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` (`id`, `unique_code`, `path`, `class_name`, `method_name`) VALUES
	(36, 'com.weirhp.web.module.RoleManagerModule@list@javax.servlet.http.HttpServletRequest@org.nutz.dao.pager.Pager@com.weirhp.domain.common.Role', '/role_mgr/list', 'com.weirhp.web.module.RoleManagerModule', 'list'),
	(37, 'com.weirhp.web.module.IndexModule@index', '/', 'com.weirhp.web.module.IndexModule', 'index'),
	(38, 'com.weirhp.web.module.OperationModule@toSetMenuOperation@javax.servlet.http.HttpServletRequest@java.lang.String', '/operation/setOperationForMenu', 'com.weirhp.web.module.OperationModule', 'toSetMenuOperation'),
	(39, 'com.weirhp.web.module.UserManagerModule@list@javax.servlet.http.HttpServletRequest@org.nutz.dao.pager.Pager@com.weirhp.domain.common.User', '/user/list', 'com.weirhp.web.module.UserManagerModule', 'list'),
	(40, 'com.weirhp.web.module.UserManagerModule@add@javax.servlet.http.HttpServletRequest@java.lang.Long', '/user/toEdit', 'com.weirhp.web.module.UserManagerModule', 'add'),
	(41, 'com.weirhp.web.module.RoleManagerModule@deleteRoleFromUser@javax.servlet.http.HttpServletRequest@java.lang.Long@java.lang.Long', '/role_mgr/deleteRoleFromUser', 'com.weirhp.web.module.RoleManagerModule', 'deleteRoleFromUser'),
	(42, 'com.weirhp.web.module.IndexModule@login@javax.servlet.http.HttpServletRequest@com.weirhp.domain.common.User@java.lang.String', '/llogin', 'com.weirhp.web.module.IndexModule', 'login'),
	(43, 'com.weirhp.web.module.MenuModule@toSetMenuForRole@javax.servlet.http.HttpServletRequest@java.lang.Long', '/menu/toSetMenuForRole', 'com.weirhp.web.module.MenuModule', 'toSetMenuForRole'),
	(44, 'com.weirhp.web.module.RoleManagerModule@delete@javax.servlet.http.HttpServletRequest@java.lang.Long', '/role_mgr/delete', 'com.weirhp.web.module.RoleManagerModule', 'delete'),
	(45, 'com.weirhp.web.module.IndexModule@login', '/login', 'com.weirhp.web.module.IndexModule', 'login'),
	(46, 'com.weirhp.web.module.OperationModule@operationJson', '/operation/operationjson', 'com.weirhp.web.module.OperationModule', 'operationJson'),
	(47, 'com.weirhp.web.module.UserManagerModule@add@javax.servlet.http.HttpServletRequest@com.weirhp.domain.common.User', '/user/save', 'com.weirhp.web.module.UserManagerModule', 'add'),
	(48, 'com.weirhp.web.module.SystemModule@toSystemSet', '/system/system_set', 'com.weirhp.web.module.SystemModule', 'toSystemSet'),
	(49, 'com.weirhp.web.module.MenuModule@editMenu@javax.servlet.http.HttpServletRequest', '/menu/toEditByTree', 'com.weirhp.web.module.MenuModule', 'editMenu'),
	(50, 'com.weirhp.web.module.UserManagerModule@updatePassword@javax.servlet.http.HttpServletRequest@java.lang.String@java.lang.String', '/user/updatePassword', 'com.weirhp.web.module.UserManagerModule', 'updatePassword'),
	(51, 'com.weirhp.web.module.MenuModule@menuJson', '/menu/menujson', 'com.weirhp.web.module.MenuModule', 'menuJson'),
	(52, 'com.weirhp.web.module.RoleManagerModule@add@javax.servlet.http.HttpServletRequest@java.lang.Long', '/role_mgr/toEdit', 'com.weirhp.web.module.RoleManagerModule', 'add'),
	(53, 'com.weirhp.web.module.MenuModule@saveRoleMenus@javax.servlet.http.HttpServletRequest@java.lang.String@java.lang.Long', '/menu/saveRoleMenus', 'com.weirhp.web.module.MenuModule', 'saveRoleMenus'),
	(54, 'com.weirhp.web.module.MessageModule@getMessage@javax.servlet.http.HttpServletRequest@javax.servlet.http.HttpServletResponse', '/mvc/message/getmessage', 'com.weirhp.web.module.MessageModule', 'getMessage'),
	(55, 'com.weirhp.web.module.RoleManagerModule@saveUserSelectRoles@javax.servlet.http.HttpServletRequest@[J@java.lang.Long', '/role_mgr/saveUserSelectRoles', 'com.weirhp.web.module.RoleManagerModule', 'saveUserSelectRoles'),
	(56, 'com.weirhp.web.module.OperationModule@menuOperationsJson@javax.servlet.http.HttpServletRequest@java.lang.Long', '/operation/menuoperationsjson', 'com.weirhp.web.module.OperationModule', 'menuOperationsJson'),
	(57, 'com.weirhp.web.module.RoleManagerModule@listForUserSelect@javax.servlet.http.HttpServletRequest@org.nutz.dao.pager.Pager@com.weirhp.domain.common.Role@java.lang.Long', '/role_mgr/listForUserSelect', 'com.weirhp.web.module.RoleManagerModule', 'listForUserSelect'),
	(58, 'com.weirhp.web.module.RoleManagerModule@listForUserSelected@javax.servlet.http.HttpServletRequest@org.nutz.dao.pager.Pager@com.weirhp.domain.common.Role@java.lang.Long', '/role_mgr/listForUserSelected', 'com.weirhp.web.module.RoleManagerModule', 'listForUserSelected'),
	(59, 'com.weirhp.web.module.RoleManagerModule@editRole@javax.servlet.http.HttpServletRequest@com.weirhp.domain.common.Role', '/role_mgr/save', 'com.weirhp.web.module.RoleManagerModule', 'editRole'),
	(60, 'com.weirhp.web.module.MenuModule@menuJsonForRole@javax.servlet.http.HttpServletRequest@java.lang.Long', '/menu/menujsonforrole', 'com.weirhp.web.module.MenuModule', 'menuJsonForRole'),
	(61, 'com.weirhp.web.module.MenuModule@saveMenuTree@javax.servlet.http.HttpServletRequest@java.lang.String@java.lang.String', '/menu/saveByTree', 'com.weirhp.web.module.MenuModule', 'saveMenuTree'),
	(62, 'com.weirhp.web.module.MenuModule@add@javax.servlet.http.HttpServletRequest@java.lang.Long', '/menu/toEdit', 'com.weirhp.web.module.MenuModule', 'add'),
	(63, 'com.weirhp.web.module.MenuModule@list@javax.servlet.http.HttpServletRequest@org.nutz.dao.pager.Pager@com.weirhp.domain.common.Menu', '/menu/list', 'com.weirhp.web.module.MenuModule', 'list'),
	(64, 'com.weirhp.web.module.SystemModule@initOperation', '/system/init_operation', 'com.weirhp.web.module.SystemModule', 'initOperation'),
	(65, 'com.weirhp.web.module.UserManagerModule@toSetPassword', '/user/toUpdatePassword', 'com.weirhp.web.module.UserManagerModule', 'toSetPassword'),
	(66, 'com.weirhp.web.module.UserManagerModule@delete@javax.servlet.http.HttpServletRequest@java.lang.Long', '/user/delete', 'com.weirhp.web.module.UserManagerModule', 'delete'),
	(67, 'com.weirhp.web.module.OperationModule@saveMenuOperations@javax.servlet.http.HttpServletRequest@java.lang.String@java.lang.Long', '/operation/saveMenuOperations', 'com.weirhp.web.module.OperationModule', 'saveMenuOperations'),
	(68, 'com.weirhp.web.module.AdminManagerModule@logout@javax.servlet.http.HttpServletRequest', '/manage/logout', 'com.weirhp.web.module.AdminManagerModule', 'logout'),
	(69, 'com.weirhp.web.module.MenuModule@menuJsonForRoleShow@javax.servlet.http.HttpServletRequest@java.lang.Long', '/menu/menujsonforroleshow', 'com.weirhp.web.module.MenuModule', 'menuJsonForRoleShow'),
	(70, 'com.weirhp.web.module.AdminManagerModule@index@javax.servlet.http.HttpServletRequest', '/manage/index', 'com.weirhp.web.module.AdminManagerModule', 'index');
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;


# Dumping structure for table nutz_right.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `name` varchar(50) DEFAULT NULL COMMENT '角色称呼',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `code`, `name`) VALUES
	(1, 'admin', '系统超级管理员'),
	(2, 'user_manager', '用户管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


# Dumping structure for table nutz_right.role_menu
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE IF NOT EXISTS `role_menu` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `role_id` int(32) DEFAULT NULL,
  `menu_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.role_menu: ~3 rows (approximately)
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(1, 2, 1),
	(2, 2, 25),
	(3, 2, 26);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;


# Dumping structure for table nutz_right.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.user: ~4 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `nick_name`, `password`) VALUES
	(1, 'admin', '系统管理员', '1234'),
	(2, 'user1', 'user1', '1234'),
	(3, 'user2', 'user2', '1234'),
	(4, 'test', 'test', 'test');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


# Dumping structure for table nutz_right.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) DEFAULT NULL,
  `role_id` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table nutz_right.user_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1),
	(2, 2, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
