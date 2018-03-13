/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : pay

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2018-03-13 17:37:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `p_user`
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `p_id` int(11) NOT NULL auto_increment,
  `p_account` int(11) default NULL,
  `p_password` varchar(255) collate utf8_unicode_ci default NULL,
  `p_name` varchar(255) collate utf8_unicode_ci default NULL,
  `p_money` double default NULL,
  PRIMARY KEY  (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('1', '123', '123', '董金杰', '123');
INSERT INTO `p_user` VALUES ('2', '1234', '123', '周文和', '120');
