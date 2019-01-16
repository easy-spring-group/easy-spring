/*
 Navicat Premium Data Transfer

 Source Server         : local_docker
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : easy_spring_security_demo

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 01/16/2019 14:07:05 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(8) unsigned NOT NULL COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `open_id` varchar(45) DEFAULT NULL COMMENT '微信openid',
  `head_img_url` varchar(256) DEFAULT NULL COMMENT '微信头像连接地址',
  `telephone` varchar(32) NOT NULL COMMENT '联系电话',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户是否已锁定(默认值:0未锁定)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '伪删除标记字段',
  PRIMARY KEY (`id`) COMMENT '主键索引',
  UNIQUE KEY `username_UNIQUE` (`username`) COMMENT '用户名唯一索引',
  UNIQUE KEY `telephone_UNIQUE` (`telephone`) COMMENT '电话唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('123', 'admin', '$2a$10$v9RoC7VhLc41vbQ9/6rXpOLiEawXbVOUJnn/EulBHAW9YXTbkL3ce', null, null, '18866668888', null, '0', '2019-01-16 05:31:58', '2019-01-16 06:02:32', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
