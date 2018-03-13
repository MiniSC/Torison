/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : torison

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2018-03-13 17:37:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `UserID` int(11) NOT NULL default '0',
  `FriendID` int(11) default NULL,
  PRIMARY KEY  (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `nomal_user`
-- ----------------------------
DROP TABLE IF EXISTS `nomal_user`;
CREATE TABLE `nomal_user` (
  `userId` int(11) NOT NULL auto_increment,
  `userAccountNum` varchar(255) default NULL,
  `userPassword` varchar(255) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nomal_user
-- ----------------------------
INSERT INTO `nomal_user` VALUES ('1', '123', '53989483038990520775444686317057812967');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL auto_increment,
  `UserID` int(11) default NULL,
  `RouteID` int(11) default NULL,
  `Num` int(11) default NULL,
  `Status` varchar(255) default NULL,
  PRIMARY KEY  (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('7', '1', '3', '23', '01');
INSERT INTO `orders` VALUES ('9', '2', '3', '2', '01');
INSERT INTO `orders` VALUES ('10', '2', '3', '3', '01');

-- ----------------------------
-- Table structure for `route`
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `routeID` int(11) NOT NULL auto_increment,
  `routeName` varchar(255) default NULL,
  `routeFromAddress` varchar(255) default NULL,
  `routeEndAddress` varchar(255) default NULL,
  `routeNeedMoney` double default NULL,
  `routeIntroduce` varchar(255) default NULL,
  `routeFromId` varchar(255) default NULL,
  `routeMaxPersonNum` int(11) default NULL,
  `routeLastPersonNum` int(11) default NULL,
  `deposite` double default NULL,
  PRIMARY KEY  (`routeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES ('3', '金塘仙人山景区一日游', '杭州（东）', '舟山市金塘岛', '250', '这是一条去往金塘的路线', '1', '10', '5', null);

-- ----------------------------
-- Table structure for `route_collection`
-- ----------------------------
DROP TABLE IF EXISTS `route_collection`;
CREATE TABLE `route_collection` (
  `UserID` int(11) NOT NULL default '0',
  `routeID` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route_collection
-- ----------------------------
INSERT INTO `route_collection` VALUES ('1', '3');

-- ----------------------------
-- Table structure for `route_maker`
-- ----------------------------
DROP TABLE IF EXISTS `route_maker`;
CREATE TABLE `route_maker` (
  `UserID` int(11) NOT NULL auto_increment,
  `Pic1` varchar(255) default NULL,
  `Pic2` varchar(255) default NULL,
  `Introduce` varchar(255) default NULL,
  `Status` varchar(255) default NULL,
  PRIMARY KEY  (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route_maker
-- ----------------------------
INSERT INTO `route_maker` VALUES ('1', null, null, 'dsfasfdsaf', '2');
INSERT INTO `route_maker` VALUES ('2', null, null, 'dsfasfdsaf', null);

-- ----------------------------
-- Table structure for `route_pic`
-- ----------------------------
DROP TABLE IF EXISTS `route_pic`;
CREATE TABLE `route_pic` (
  `routeID` int(11) NOT NULL default '0',
  `routePic1` varchar(255) default NULL,
  `routePic2` varchar(255) default NULL,
  `routePic3` varchar(255) default NULL,
  PRIMARY KEY  (`routeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route_pic
-- ----------------------------
INSERT INTO `route_pic` VALUES ('2', null, null, null);
INSERT INTO `route_pic` VALUES ('3', 'a6ab9ed1-3c77-44ab-81b7-eb375d345078.jpg', 'd77b85f1-11f2-4ed4-a216-636829403552.jpg', 'ed1bbc69-0a52-4ec6-9b08-85e4731fca39.jpg');
INSERT INTO `route_pic` VALUES ('4', '723626dc-5fe1-4531-b165-4095a14129f7.jpg', '1ed1bd54-d0ab-467a-bf69-2da6d5684ce5.jpg', 'd0084a24-c8e4-4c16-9a86-7e4831928e91.jpg');
INSERT INTO `route_pic` VALUES ('5', 'e972dc65-1181-4e28-9e40-d95eabe442d9.jpg', 'ecd0d5cc-a299-4ece-877b-46e24c2f571f.jpg', 'd0abeb52-4c86-4645-870d-1bd1b2ca62a0.jpg');
INSERT INTO `route_pic` VALUES ('6', '1be89fbf-7ba2-4489-860c-7eca9c7ff3a9.jpg', '1a73528f-dc29-4e30-8563-70bf29e4aa9e.jpg', '6cbd5c71-4e9e-47c4-8284-023aa3ffdaf4.jpg');
INSERT INTO `route_pic` VALUES ('7', '2b17c24b-504c-44d8-82ee-18c0e46d411b.jpg', 'ced9ce2f-cee7-4387-918c-bd9555c49aea.jpg', '023fa493-63d0-4ffc-accd-e8e03ef779dc.jpg');
INSERT INTO `route_pic` VALUES ('8', '5946b0be-0793-4c11-a05f-5ec1a5f7bf5f.jpg', 'f35d1f02-2bf5-4614-8473-84d863909ab8.jpg', '2bf79fe9-2067-422c-b89e-aa3de92b8044.jpg');
INSERT INTO `route_pic` VALUES ('9', '18d6698a-0060-4ff7-81ec-e36d87c41190.jpg', '05cec5d1-51ea-43ea-9d9f-90361b68305b.jpg', '29c73d90-930d-4d11-a61d-6707fee83c11.jpg');
INSERT INTO `route_pic` VALUES ('10', '38ba1594-657e-4d3b-a73a-65b29f24bfbc.jpg', '983238fc-b3c6-4bc5-873a-af9b2c72de1c.jpg', '759c6342-063a-4285-aca6-1b4b0e7ab91f.jpg');
INSERT INTO `route_pic` VALUES ('11', '3f3a82fa-f5b5-4448-80a9-33c85a835e85.jpg', '7265e78d-a1a6-4485-9d5f-a06e1d300408.jpg', '579e6afc-a5f2-4432-ba82-2df117afffae.jpg');
INSERT INTO `route_pic` VALUES ('12', '46dcdaac-ea4a-4cc3-ba0d-69abbf3c2e1f.jpg', 'd2861110-f468-4dea-a705-036325157d08.jpg', '462eec32-7660-4f9d-be25-8f45d6fdd918.jpg');

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `a_id` int(11) NOT NULL auto_increment,
  `a_account` varchar(255) default NULL,
  `a_name` varchar(255) default NULL,
  `a_authentication` varchar(255) default NULL,
  `a_password` varchar(255) default NULL,
  PRIMARY KEY  (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', '11', '董金杰', '1', '11');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `account` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `birthday` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `rank` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `age` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '董金杰', '123', '2018-1-1', '浙江省舟山市定海区', '0', '222@qq.com', '2');
INSERT INTO `user` VALUES ('2', '1234', '周文和', '123', '2018-03-15', '清乐园1幢401-2', '0', '12313123@qq.com', '22');
