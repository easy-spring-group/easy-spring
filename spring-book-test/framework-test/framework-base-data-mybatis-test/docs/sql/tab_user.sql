/*
 Navicat Premium Data Transfer

 Source Server         : local_docker
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : spring_book_test

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 12/05/2018 22:02:17 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` bigint(8) unsigned NOT NULL COMMENT 'Id',
  `name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `user_code` varchar(45) DEFAULT NULL COMMENT '用户编码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '伪删除标识字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tab_user`
-- ----------------------------
BEGIN;
INSERT INTO `tab_user` VALUES ('1428117721710592', 'test-update-get', null, '2018-12-05 06:18:13', '2018-12-05 07:03:32', '0'), ('1428117721710593', 'test-delete', null, '2018-12-05 06:26:19', '2018-12-05 06:26:19', '0'), ('1428117721710594', 'delete-data', null, '2018-12-05 06:27:47', '2018-12-05 06:27:47', '0'), ('1428117721710595', 'delete-data', null, '2018-12-05 06:28:09', '2018-12-05 06:28:09', '0'), ('1428117721710596', 'real-delete', null, '2018-12-05 06:30:05', '2018-12-05 06:30:05', '0'), ('1428117721710597', 'real-delete-data', null, '2018-12-05 06:31:17', '2018-12-05 06:31:17', '0'), ('1428117721710598', 'real-delete-data', null, '2018-12-05 06:31:41', '2018-12-05 06:31:45', '0'), ('1428117721710599', 'update-all-source', null, '2018-12-05 06:34:47', '2018-12-05 06:34:47', '0'), ('1428117721710600', 'update-selective-source', null, '2018-12-05 06:38:40', '2018-12-05 06:38:40', '0'), ('1428117721710601', 'test-get', null, '2018-12-05 07:04:02', '2018-12-05 07:04:02', '0'), ('1428117721710602', 'test-get-one', null, '2018-12-05 07:04:09', '2018-12-05 07:06:30', '0'), ('1428117721710603', 'test-get', null, '2018-12-05 07:08:48', '2018-12-05 07:08:48', '0'), ('1428117721710604', 'test-get-one-deleted', null, '2018-12-05 10:43:15', '2018-12-05 10:44:22', '1'), ('1428117721710605', 'test-get-one-deleted-2', null, '2018-12-05 10:44:09', '2018-12-05 10:44:16', '0'), ('1428117721710606', 'test-get-one-deleted-2', null, '2018-12-05 10:44:28', '2018-12-05 10:44:36', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
