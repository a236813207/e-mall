/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : e_mall

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-02-04 22:25:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dic_ad`
-- ----------------------------
DROP TABLE IF EXISTS `dic_ad`;
CREATE TABLE `dic_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `ad_postion_id` bigint(20) NOT NULL COMMENT '广告位置id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `is_enabled` tinyint(1) NOT NULL COMMENT '是否可用',
  `link` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `name` varchar(32) NOT NULL COMMENT '广告名字',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `type` varchar(32) NOT NULL COMMENT '广告类型',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_ad
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_ad_postion`
-- ----------------------------
DROP TABLE IF EXISTS `dic_ad_postion`;
CREATE TABLE `dic_ad_postion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `height` tinyint(4) NOT NULL COMMENT '广告高度',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `name` varchar(32) NOT NULL COMMENT '广告位置名称',
  `width` tinyint(4) NOT NULL COMMENT '广告宽度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_ad_postion
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `dic_attribute`;
CREATE TABLE `dic_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `name` varchar(32) NOT NULL COMMENT '品牌名称',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转url',
  `value` varchar(255) DEFAULT NULL COMMENT '跳转url',
  `attribute_catgory_id` bigint(20) NOT NULL COMMENT '属性分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_attribute_category`
-- ----------------------------
DROP TABLE IF EXISTS `dic_attribute_category`;
CREATE TABLE `dic_attribute_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `name` varchar(32) NOT NULL COMMENT '属性名称',
  `value` varchar(255) NOT NULL COMMENT '属性值',
  `is_enabled` tinyint(1) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_attribute_category
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_brand`
-- ----------------------------
DROP TABLE IF EXISTS `dic_brand`;
CREATE TABLE `dic_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `name` varchar(32) NOT NULL COMMENT '品牌名称',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_category`
-- ----------------------------
DROP TABLE IF EXISTS `dic_category`;
CREATE TABLE `dic_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `name` varchar(32) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父类id',
  `parent_ids` varchar(512) NOT NULL COMMENT '父类id集合',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_category
-- ----------------------------

-- ----------------------------
-- Table structure for `e_order`
-- ----------------------------
DROP TABLE IF EXISTS `e_order`;
CREATE TABLE `e_order` (
  `id` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `city` varchar(32) NOT NULL COMMENT '市',
  `consignee` varchar(32) NOT NULL COMMENT '收件人',
  `deduct_coupon_money` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵扣金额',
  `deleted` bit(1) DEFAULT NULL,
  `district` varchar(32) NOT NULL COMMENT '区',
  `expire_time` datetime DEFAULT NULL COMMENT '支付过期时间',
  `express_no` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `freight` decimal(10,2) NOT NULL COMMENT '运费',
  `member_coupon_id` bigint(20) DEFAULT NULL COMMENT '会员优惠券id',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `member_name` varchar(32) NOT NULL COMMENT '会员名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `payment_status` tinyint(2) NOT NULL COMMENT '支付状态',
  `phone` varchar(32) NOT NULL COMMENT '电话',
  `province` varchar(32) NOT NULL COMMENT '省',
  `real_amount` decimal(10,2) NOT NULL COMMENT '订单实付金额',
  `status` tinyint(2) NOT NULL COMMENT '订单状态',
  `trade_no` varchar(128) NOT NULL COMMENT '交易流水号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_order
-- ----------------------------

-- ----------------------------
-- Table structure for `e_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `e_order_log`;
CREATE TABLE `e_order_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator` varchar(32) NOT NULL COMMENT '操作人',
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `status` tinyint(2) NOT NULL COMMENT '订单日志状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_order_log
-- ----------------------------

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `account_name` varchar(32) NOT NULL COMMENT '会员名称',
  `address` varchar(512) DEFAULT NULL COMMENT '地址',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '密码盐值',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `salt` varchar(128) NOT NULL COMMENT '密码盐值',
  `status` tinyint(2) NOT NULL COMMENT '状态',
  `wx_open_id` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `wx_union_id` varchar(128) DEFAULT NULL COMMENT '微信unionid',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for `member_address`
-- ----------------------------
DROP TABLE IF EXISTS `member_address`;
CREATE TABLE `member_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `city` varchar(32) NOT NULL COMMENT '市',
  `consignee` varchar(32) NOT NULL COMMENT '收件人',
  `district` varchar(32) NOT NULL COMMENT '区县',
  `is_default` tinyint(2) NOT NULL COMMENT '是否默认',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `phone` varchar(32) NOT NULL COMMENT '电话',
  `province` varchar(32) NOT NULL COMMENT '省',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_address
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `orders` tinyint(4) unsigned DEFAULT NULL COMMENT '排序号',
  `icon` varchar(64) NOT NULL COMMENT 'icon',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父级id',
  `parent_ids` varchar(1000) NOT NULL COMMENT '父级id集合',
  `url` varchar(64) NOT NULL COMMENT 'url',
  `permission` varchar(255) NOT NULL COMMENT '权限码',
  `type` varchar(32) NOT NULL COMMENT '资源类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(128) NOT NULL COMMENT '描述',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '修改时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `salt` varchar(128) NOT NULL COMMENT '密码盐值',
  `user_name` varchar(32) NOT NULL COMMENT '账户名',
  `status` tinyint(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2019-06-01 00:02:21', '2019-06-01 00:02:24', '5pEHMRcIxgrAiew1pDnPa0Vi54w=', 'B3F6AA73F805B184987A2AE5E37BB5A4', 'admin', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
