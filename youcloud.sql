/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_3306
 Source Server Type    : MySQL
 Source Server Version : 50142
 Source Host           : 127.0.0.1:3306
 Source Schema         : youcloud

 Target Server Type    : MySQL
 Target Server Version : 50142
 File Encoding         : 65001

 Date: 03/05/2021 09:39:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `balance` float(255, 0) NOT NULL COMMENT '账户余额',
  `account_user_id` int(11) NOT NULL COMMENT '账户所属用户ID',
  `payed` float(255, 0) NULL DEFAULT NULL COMMENT '已支付金额',
  PRIMARY KEY (`account_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle`  (
  `circle_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '朋友圈的id，唯一标识',
  `circle_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友圈的名字',
  `circle_password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进群的密码',
  `cap` int(255) NULL DEFAULT NULL COMMENT '朋友圈的容量',
  `cap_used` int(255) NULL DEFAULT NULL COMMENT '朋友圈已用内存',
  `cap_level` int(1) NULL DEFAULT NULL COMMENT '朋友圈的等级，共3级，容量从小到大，刚开始为1级',
  `circle_user_count` int(11) NULL DEFAULT NULL COMMENT '朋友圈人数',
  `found_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友圈创建者',
  `holder_count` int(255) NULL DEFAULT NULL COMMENT '拥有文件夹的数量',
  PRIMARY KEY (`circle_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1619426080 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for fileholder
-- ----------------------------
DROP TABLE IF EXISTS `fileholder`;
CREATE TABLE `fileholder`  (
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件夹的名字，唯一标识',
  `file_cap` int(255) NULL DEFAULT NULL COMMENT '文件夹已有容量',
  `file_circle_id` int(255) NULL DEFAULT NULL COMMENT '所属朋友圈的id',
  PRIMARY KEY (`file_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `pay_time` datetime NOT NULL COMMENT '支付时间',
  `pay_things` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付说明',
  `pay_user_id` int(11) NOT NULL COMMENT '支付者的ID',
  `pay_count` float(255, 0) NULL DEFAULT NULL COMMENT '支付金额',
  `pay_id` int(11) NOT NULL COMMENT '支付的订单ID',
  PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sources
-- ----------------------------
DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources`  (
  `source_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源的地址，唯一标识',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源的名称',
  `source_size` int(255) NULL DEFAULT NULL COMMENT '资源的大小',
  `source_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源的类型',
  `source_userid` int(10) NULL DEFAULT NULL COMMENT '资源上传者的id',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `source_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源所属文件夹的名字',
  `post_fix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源的后缀',
  PRIMARY KEY (`source_name`) USING BTREE,
  UNIQUE INDEX `name`(`source_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for talk
-- ----------------------------
DROP TABLE IF EXISTS `talk`;
CREATE TABLE `talk`  (
  `talk_user_id` int(255) NOT NULL COMMENT '发言者ID',
  `talk_user_nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发言者昵称',
  `talk_circle_id` int(255) NOT NULL COMMENT '发言者所在朋友圈的ID',
  `talk_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发言的内容',
  `talk_ID` int(11) NOT NULL COMMENT '发言内容的ID',
  `talk_time` date NOT NULL COMMENT '发言时间',
  PRIMARY KEY (`talk_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL COMMENT '用户的id，也是账号，唯一标识',
  `user_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的昵称，未修改的话是id',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的密码',
  `user_mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的邮箱',
  `user_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的地址',
  `user_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的联系方式',
  `user_profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像的地址',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份:普通用户、普通会员和大会员',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `pwd`(`user_password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userscircles
-- ----------------------------
DROP TABLE IF EXISTS `userscircles`;
CREATE TABLE `userscircles`  (
  `user_id` int(255) NULL DEFAULT NULL COMMENT '用户id',
  `circle_id` int(255) NULL DEFAULT NULL COMMENT '朋友圈id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
