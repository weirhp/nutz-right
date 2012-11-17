
CREATE DATABASE IF NOT EXISTS `nutz_right_demo` ;
USE `nutz_right_demo`;

CREATE TABLE IF NOT EXISTS `menu` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL COMMENT '对应的访问链接',
  `parent_id` bigint(64) DEFAULT NULL COMMENT '父结点ID',
  `indexs` int(32) DEFAULT NULL COMMENT '排序字段',
  `show_menu` int(32) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `indexs` (`indexs`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `menu_operation` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(64) DEFAULT NULL,
  `operation_id` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `operation_id` (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `operation` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `unique_code` varchar(800) NOT NULL,
  `path` varchar(500) DEFAULT NULL,
  `class_name` varchar(500) DEFAULT NULL,
  `method_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `name` varchar(50) DEFAULT NULL COMMENT '角色称呼',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `role_menu` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `role_id` int(32) DEFAULT NULL,
  `menu_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) DEFAULT NULL,
  `role_id` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
