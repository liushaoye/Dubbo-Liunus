/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : p2p

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 16/08/2018 09:46:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES (1, '刘德华', '138000000001', 'beijing', 'branky@163.com');
INSERT INTO `u_user` VALUES (2, 'nick', '139000000001', '北京大兴亦庄', 'fastly@163.com');
INSERT INTO `u_user` VALUES (4, '小丸子111', '17000018701', '北京市海淀区', '11222aaa@qq.com');
INSERT INTO `u_user` VALUES (5, '张学友', '100000001', '北京市海淀区', '287643434@qq.com');
INSERT INTO `u_user` VALUES (6, 'nick3222', '13800000001', '北京市海淀区', 'hnds@163.com');
INSERT INTO `u_user` VALUES (7, 'nick3', '13900000000', '北京海淀区中关村', 'quetyj@163.com');
INSERT INTO `u_user` VALUES (8, 'zhangsanfeng001', '13700000000', '13e24', '127490808@qq.com');
INSERT INTO `u_user` VALUES (9, 'zhangsanfeng001', '13700000000', '13e24', '127490808@qq.com');
INSERT INTO `u_user` VALUES (10, 'zhangsanfeng001', '13700000000', '13e24', '127490808@qq.com');
INSERT INTO `u_user` VALUES (12, '111', '111', '111', '111');
INSERT INTO `u_user` VALUES (13, '杨乃武', '135124512', '大法师大法', '123123123121231');
INSERT INTO `u_user` VALUES (14, '咪咪咪', '的说法', '阿斯顿飞', '大幅度');

SET FOREIGN_KEY_CHECKS = 1;
