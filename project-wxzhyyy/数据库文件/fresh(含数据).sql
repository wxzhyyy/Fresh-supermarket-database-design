/*
Navicat MySQL Data Transfer

Source Server         : book
Source Server Version : 50514
Source Host           : localhost:3306
Source Database       : fresh8

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2020-07-14 23:19:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` varchar(20) NOT NULL,
  `admin_name` varchar(50) DEFAULT NULL,
  `admin_pwd` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cat_id` varchar(20) NOT NULL,
  `cat_name` varchar(50) DEFAULT NULL,
  `cat_describe` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '酒水', '就是酒水');
INSERT INTO `category` VALUES ('3', '水果', '西瓜啥的');

-- ----------------------------
-- Table structure for comd_purc_record
-- ----------------------------
DROP TABLE IF EXISTS `comd_purc_record`;
CREATE TABLE `comd_purc_record` (
  `purc_id` varchar(20) NOT NULL,
  `comd_id` varchar(20) NOT NULL,
  `purc_quantity` int(11) DEFAULT NULL,
  `purc_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`purc_id`,`comd_id`),
  KEY `FK_comd_purc_record2` (`comd_id`),
  CONSTRAINT `FK_comd_purc_record2` FOREIGN KEY (`comd_id`) REFERENCES `commodity` (`comd_id`),
  CONSTRAINT `FK_comd_purc_record` FOREIGN KEY (`purc_id`) REFERENCES `commodity_purchase` (`purc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comd_purc_record
-- ----------------------------

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `comd_id` varchar(20) NOT NULL,
  `cat_id` varchar(20) DEFAULT NULL,
  `promotion_id` varchar(50) DEFAULT NULL,
  `comd_name` varchar(50) DEFAULT NULL,
  `comd_price` float DEFAULT NULL,
  `comd_vip_price` float DEFAULT NULL,
  `comd_quantity` int(11) DEFAULT NULL,
  `comd_specification` varchar(1024) DEFAULT NULL,
  `comd_details` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`comd_id`),
  KEY `FK_classify` (`cat_id`),
  KEY `FK_comd_promotion` (`promotion_id`),
  CONSTRAINT `FK_comd_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`promotion_id`),
  CONSTRAINT `FK_classify` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '1', null, '啤酒', '25', '23', '200', null, null);
INSERT INTO `commodity` VALUES ('2', '1', null, '白酒', '30', '25', '200', null, null);
INSERT INTO `commodity` VALUES ('3', '3', null, '油', '15', '13', '200', null, null);
INSERT INTO `commodity` VALUES ('4', '3', null, '油盐', '10', '8', '200', null, null);

-- ----------------------------
-- Table structure for commodity_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `commodity_evaluation`;
CREATE TABLE `commodity_evaluation` (
  `comd_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `eval_content` varchar(1024) DEFAULT NULL,
  `eval_time` datetime DEFAULT NULL,
  `eval_star` int(11) DEFAULT NULL,
  `eval_pic` longblob,
  PRIMARY KEY (`comd_id`,`user_id`),
  KEY `FK_commodity_evaluation2` (`user_id`),
  CONSTRAINT `FK_commodity_evaluation2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_commodity_evaluation` FOREIGN KEY (`comd_id`) REFERENCES `commodity` (`comd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity_evaluation
-- ----------------------------
INSERT INTO `commodity_evaluation` VALUES ('1', '1', 'fffff', '2020-07-14 22:51:36', '4', null);
INSERT INTO `commodity_evaluation` VALUES ('2', '2', '很好', '2020-07-14 23:10:34', '6', null);

-- ----------------------------
-- Table structure for commodity_menu
-- ----------------------------
DROP TABLE IF EXISTS `commodity_menu`;
CREATE TABLE `commodity_menu` (
  `comd_id` varchar(20) NOT NULL,
  `menu_id` varchar(20) NOT NULL,
  `commodity_menu_describe` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`comd_id`,`menu_id`),
  KEY `FK_commodity_menu2` (`menu_id`),
  CONSTRAINT `FK_commodity_menu2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `FK_commodity_menu` FOREIGN KEY (`comd_id`) REFERENCES `commodity` (`comd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity_menu
-- ----------------------------

-- ----------------------------
-- Table structure for commodity_order
-- ----------------------------
DROP TABLE IF EXISTS `commodity_order`;
CREATE TABLE `commodity_order` (
  `order_id` varchar(20) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `original_price` float DEFAULT NULL,
  `actual_price` float DEFAULT NULL,
  `coupon_id` varchar(20) DEFAULT NULL,
  `required_delivery_time` datetime DEFAULT NULL,
  `addr_id` varchar(20) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_buy` (`user_id`),
  CONSTRAINT `FK_buy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity_order
-- ----------------------------
INSERT INTO `commodity_order` VALUES ('1', '1', '140', '135', '1', null, null, '下单');
INSERT INTO `commodity_order` VALUES ('2', '1', '95', '90', '1', null, '1', '下单');
INSERT INTO `commodity_order` VALUES ('3', '2', '140', '135', '1', null, '3', '下单');

-- ----------------------------
-- Table structure for commodity_purchase
-- ----------------------------
DROP TABLE IF EXISTS `commodity_purchase`;
CREATE TABLE `commodity_purchase` (
  `purc_id` varchar(20) NOT NULL,
  `admin_id` varchar(20) DEFAULT NULL,
  `comd_id` varchar(20) DEFAULT NULL,
  `purc_status` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`purc_id`),
  KEY `FK_work` (`admin_id`),
  CONSTRAINT `FK_work` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity_purchase
-- ----------------------------

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `coupon_id` varchar(20) NOT NULL,
  `coupon_content` varchar(1024) DEFAULT NULL,
  `coupon_fit_money` float DEFAULT NULL,
  `coupon_price` float DEFAULT NULL,
  `coupon_start_time` datetime DEFAULT NULL,
  `coupon_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('1', '满50减5', '50', '5', '2020-07-13 12:00:00', '2020-07-20 12:00:00');

-- ----------------------------
-- Table structure for disc_ass
-- ----------------------------
DROP TABLE IF EXISTS `disc_ass`;
CREATE TABLE `disc_ass` (
  `comd_id` varchar(20) NOT NULL,
  `disc_id` varchar(50) NOT NULL,
  `disc_start_time` datetime DEFAULT NULL,
  `disc_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`comd_id`,`disc_id`),
  KEY `FK_disc_ass2` (`disc_id`),
  CONSTRAINT `FK_disc_ass2` FOREIGN KEY (`disc_id`) REFERENCES `full_discount` (`disc_id`),
  CONSTRAINT `FK_disc_ass` FOREIGN KEY (`comd_id`) REFERENCES `commodity` (`comd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of disc_ass
-- ----------------------------

-- ----------------------------
-- Table structure for full_discount
-- ----------------------------
DROP TABLE IF EXISTS `full_discount`;
CREATE TABLE `full_discount` (
  `disc_id` varchar(50) NOT NULL,
  `disc_content` varchar(1024) DEFAULT NULL,
  `disc_fitnumber` int(11) DEFAULT NULL,
  `disc_discount` float DEFAULT NULL,
  `disc_start_time` datetime DEFAULT NULL,
  `disc_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`disc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of full_discount
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` varchar(20) NOT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_material` varchar(1024) DEFAULT NULL,
  `menu_step` varchar(1024) DEFAULT NULL,
  `menu_pic` longblob,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `order_id` varchar(20) NOT NULL,
  `comd_id` varchar(20) NOT NULL,
  `order_quantity` int(11) DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  `order_discount` float DEFAULT NULL,
  `disc_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`comd_id`),
  KEY `FK_order_details2` (`comd_id`),
  CONSTRAINT `FK_order_details2` FOREIGN KEY (`comd_id`) REFERENCES `commodity` (`comd_id`),
  CONSTRAINT `FK_order_details` FOREIGN KEY (`order_id`) REFERENCES `commodity_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES ('1', '1', '2', '25', '0', null);
INSERT INTO `order_details` VALUES ('1', '2', '3', '30', '0', null);
INSERT INTO `order_details` VALUES ('2', '1', '3', '25', '0', null);
INSERT INTO `order_details` VALUES ('2', '4', '2', '10', '0', null);
INSERT INTO `order_details` VALUES ('3', '2', '3', '30', '0', null);
INSERT INTO `order_details` VALUES ('3', '4', '5', '10', '0', null);

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `promotion_id` varchar(50) NOT NULL,
  `comd_id` varchar(200) DEFAULT NULL,
  `prom_price` float DEFAULT NULL,
  `prom_quantity` int(11) DEFAULT NULL,
  `prom_start_time` datetime DEFAULT NULL,
  `prom_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`promotion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES ('1', '1', '20', '200', '2020-07-13 12:00:00', '2020-07-20 12:00:00');

-- ----------------------------
-- Table structure for shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `shipping_address`;
CREATE TABLE `shipping_address` (
  `addr_id` varchar(20) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `province` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `cell` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `linkman` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`addr_id`),
  KEY `FK_uesr_addr` (`user_id`),
  CONSTRAINT `FK_uesr_addr` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping_address
-- ----------------------------
INSERT INTO `shipping_address` VALUES ('1', '1', '浙江', '杭州', '拱墅', '理四', '虾粥', '1267543323');
INSERT INTO `shipping_address` VALUES ('3', '2', '浙江', '杭州', '周', '撒旦', '小周', '1986256382');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_sex` char(1) DEFAULT NULL,
  `user_pwd` varchar(40) DEFAULT NULL,
  `user_vip` char(1) DEFAULT NULL,
  `user_vip_endtime` datetime DEFAULT NULL,
  `user_phone` char(11) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_city` varchar(50) DEFAULT NULL,
  `user_regtime` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user1', null, '1', null, null, null, null, null, '2020-07-14 20:52:07');
INSERT INTO `user` VALUES ('2', 'user2', null, '1', null, null, null, null, null, '2020-07-14 20:52:16');

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `user_id` varchar(20) NOT NULL,
  `coupon_id` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`coupon_id`),
  KEY `FK_user_coupon2` (`coupon_id`),
  CONSTRAINT `FK_user_coupon2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`),
  CONSTRAINT `FK_user_coupon` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
