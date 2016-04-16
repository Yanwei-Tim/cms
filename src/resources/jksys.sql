/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50144
Source Host           : localhost:3306
Source Database       : jksys

Target Server Type    : MYSQL
Target Server Version : 50144
File Encoding         : 65001

Date: 2012-12-04 18:56:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `depart` varchar(20) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `start_ip` varchar(20) DEFAULT NULL,
  `end_ip` varchar(20) DEFAULT NULL,
  `start_hour` int(11) DEFAULT NULL,
  `end_hour` int(11) DEFAULT NULL,
  `description` text,
  `remote_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', '222222', '男', '0571-88888888', '2010-07-04 13:52:36', '2012-07-01 10:42:52', '有效', '信息中心', '主任', '超级管理员', 'admin@admin.com', '192.168.1.1', '192.168.50.254', '9', '18', '管理员', null);
INSERT INTO `account` VALUES ('2', 'authadmin', '222222', '男', '0571-88888888', '2012-07-01 10:39:10', '2012-07-01 10:39:10', '有效', '信息中心', '主任', '授权管理员', '*@hzih.net', '192.168.1.1', '192.168.200.244', '7', '21', '一个授权管理员账号', null);
INSERT INTO `account` VALUES ('3', 'auditadmin', '222222', '男', '0571-88888888', '2012-03-14 12:33:26', '2012-07-01 10:42:38', '有效', '信息中心', '主任', '审计管理员', '*@hzih.net', '192.168.1.1', '192.168.200.226', '1', '22', '一个审计管理员账号', null);
INSERT INTO `account` VALUES ('4', 'configadmin', '222222', '男', '0571-88888888', '2012-07-01 10:40:55', '2012-07-01 10:40:55', '有效', '信息中心', '主任', '配置管理员', '*@hzih.net', '192.168.1.1', '192.168.200.244', '7', '21', '一个配置管理员账号', null);
INSERT INTO `account` VALUES ('5', 'test', '222222', '男', '', '2012-11-22 13:13:53', '2012-11-22 13:14:34', '有效', '', '', '测试员', '', '0.0.0.0', '255.255.255.255', '0', '24', '', null);

-- ----------------------------
-- Table structure for `account_role`
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `account_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FK410D034851BABF58` (`role_id`),
  KEY `FK410D0348BE9C187C` (`account_id`),
  CONSTRAINT `FK410D034851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348BE9C187C` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES ('1', '1');
INSERT INTO `account_role` VALUES ('2', '2');
INSERT INTO `account_role` VALUES ('3', '3');
INSERT INTO `account_role` VALUES ('4', '4');
INSERT INTO `account_role` VALUES ('5', '5');

-- ----------------------------
-- Table structure for `alert_rule`
-- ----------------------------
DROP TABLE IF EXISTS `alert_rule`;
CREATE TABLE `alert_rule` (
  `Id` bigint(20) NOT NULL DEFAULT '0',
  `cpu` double DEFAULT NULL,
  `memory` double DEFAULT NULL,
  `disk` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alert_rule
-- ----------------------------
INSERT INTO `alert_rule` VALUES ('3', '0', '0', '0');
INSERT INTO `alert_rule` VALUES ('5', '80', '90', '80');

-- ----------------------------
-- Table structure for `business_code`
-- ----------------------------
DROP TABLE IF EXISTS `business_code`;
CREATE TABLE `business_code` (
  `code` varchar(10) DEFAULT NULL COMMENT '编码',
  `code_desc` varchar(255) DEFAULT NULL COMMENT '业务类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_code
-- ----------------------------
INSERT INTO `business_code` VALUES ('01000', ' 国内安全保卫管理类');
INSERT INTO `business_code` VALUES ('02000', ' 经济犯罪侦查管理类');
INSERT INTO `business_code` VALUES ('02001', ' 经济犯罪案件信息管理类');
INSERT INTO `business_code` VALUES (' 03000', ' 治安管理类');
INSERT INTO `business_code` VALUES (' 03001', ' 暂住人口信息系统');
INSERT INTO `business_code` VALUES (' 03002', ' 租赁房屋信息系统');
INSERT INTO `business_code` VALUES (' 03003', ' 旅馆业信息系统');
INSERT INTO `business_code` VALUES (' 03004', ' 典当业信息系统');
INSERT INTO `business_code` VALUES (' 03005', ' 机动车修理业信息系统');
INSERT INTO `business_code` VALUES (' 03006', ' 废旧回收业信息系统');
INSERT INTO `business_code` VALUES (' 03007', ' 印章业信息系统');
INSERT INTO `business_code` VALUES (' 03008', ' 印刷业信息系统');
INSERT INTO `business_code` VALUES (' 03009', ' 民用爆炸物品及危险物品信息系统');
INSERT INTO `business_code` VALUES (' 03010', ' 接处警信息系统');
INSERT INTO `business_code` VALUES (' 04000', ' 边防管理类');
INSERT INTO `business_code` VALUES (' 05000', ' 刑事侦查管理类');
INSERT INTO `business_code` VALUES (' 06000', ' 出入境管理类');
INSERT INTO `business_code` VALUES (' 06001', ' 公民出国境管理信息系统');
INSERT INTO `business_code` VALUES (' 06002', ' 港澳居民来往内地通行证签发信息系统');
INSERT INTO `business_code` VALUES (' 07000', ' 消防管理类');
INSERT INTO `business_code` VALUES (' 07001', ' 消防安全重点单位信息系统');
INSERT INTO `business_code` VALUES (' 08000', ' 警卫管理类');
INSERT INTO `business_code` VALUES (' 10000', ' 铁道公安管理类');
INSERT INTO `business_code` VALUES (' 11000', ' 网络安全监察管理类');
INSERT INTO `business_code` VALUES (' 12000', ' 行动技术管理类');
INSERT INTO `business_code` VALUES (' 13000', ' 监所管理类');
INSERT INTO `business_code` VALUES (' 13001', ' 看守所管理信息系统');
INSERT INTO `business_code` VALUES (' 14000', ' 交通公安管理类');
INSERT INTO `business_code` VALUES (' 15000', ' 民航公安管理类');
INSERT INTO `business_code` VALUES (' 16000', ' 林业公安管理类');
INSERT INTO `business_code` VALUES (' 17000', ' 交通管理类');
INSERT INTO `business_code` VALUES (' 17001', ' 机动车登记管理信息系统');
INSERT INTO `business_code` VALUES (' 17002', ' 机动车网上查询系统');
INSERT INTO `business_code` VALUES (' 17003', ' 驾驶人网上查询系统');
INSERT INTO `business_code` VALUES (' 17004', ' 122事故接、报警管理信息系统');
INSERT INTO `business_code` VALUES (' 17005', ' 驾驶人违法信息系统');
INSERT INTO `business_code` VALUES (' 17006', ' 机动车/驾驶人信息查询系统');
INSERT INTO `business_code` VALUES (' 18000', ' 法制管理类');
INSERT INTO `business_code` VALUES (' 19000', ' 外事管理类');
INSERT INTO `business_code` VALUES (' 20000', ' 装备财务管理类');
INSERT INTO `business_code` VALUES (' 21000', ' 禁毒管理类');
INSERT INTO `business_code` VALUES (' 21001', ' 易制毒化学品管理信息系统');
INSERT INTO `business_code` VALUES (' 22000', ' 科技管理类');
INSERT INTO `business_code` VALUES (' 23000', ' 基础和综合管理类');
INSERT INTO `business_code` VALUES (' 23001', ' 综合查询信息系统');
INSERT INTO `business_code` VALUES (' 23002', ' 在逃人员信息资源库');
INSERT INTO `business_code` VALUES (' 23003', ' 出入境人员/证件信息资源库');
INSERT INTO `business_code` VALUES (' 24000', ' 海关公安管理类');
INSERT INTO `business_code` VALUES (' 26000', ' 反邪教管理类');
INSERT INTO `business_code` VALUES (' 27000', ' 反恐怖管理类');
INSERT INTO `business_code` VALUES (' 31000', ' 办公管理类（指挥中心管理类）');
INSERT INTO `business_code` VALUES (' 32000', ' 纪委监察管理类');
INSERT INTO `business_code` VALUES (' 34000', ' 督察管理类');
INSERT INTO `business_code` VALUES (' 36000', ' 人事管理类');
INSERT INTO `business_code` VALUES (' 39000', ' 离退休干部管理类');
INSERT INTO `business_code` VALUES (' 42000', ' 综合浏览信息类');
INSERT INTO `business_code` VALUES ('保留', ' 其他');

-- ----------------------------
-- Table structure for `business_day_report`
-- ----------------------------
DROP TABLE IF EXISTS `business_day_report`;
CREATE TABLE `business_day_report` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(255) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `report_day` int(11) DEFAULT NULL,
  `ext_dataflow` double DEFAULT NULL,
  `int_dataflow` double DEFAULT NULL,
  `ext_record_count` bigint(20) DEFAULT NULL,
  `int_record_count` bigint(20) DEFAULT NULL,
  `alert_count` bigint(20) DEFAULT NULL,
  `report_month` int(11) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_day_report
-- ----------------------------
INSERT INTO `business_day_report` VALUES ('1', 'bbb', '2012-11-22 00:00:00', '22', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('2', 'bsquery', '2012-11-22 00:00:00', '22', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('3', 'bbb', '2012-11-23 00:00:00', '23', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('4', 'bsquery', '2012-11-23 00:00:00', '23', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('5', 'bbb', '2012-11-26 00:00:00', '26', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('6', 'bsquery', '2012-11-26 00:00:00', '26', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('7', 'bbb', '2012-11-27 00:00:00', '27', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('8', 'bsquery', '2012-11-27 00:00:00', '27', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('9', 'bbb', '2012-11-28 00:00:00', '28', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('10', 'bsquery', '2012-11-28 00:00:00', '28', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('11', 'bbb', '2012-11-29 00:00:00', '29', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('12', 'bsquery', '2012-11-29 00:00:00', '29', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('13', 'bbb', '2012-11-30 00:00:00', '30', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('14', 'bsquery', '2012-11-30 00:00:00', '30', '0', '0', '0', '0', '0', '11', '2012');
INSERT INTO `business_day_report` VALUES ('15', 'bbb', '2012-12-03 00:00:00', '3', '0', '0', '0', '0', '0', '12', '2012');
INSERT INTO `business_day_report` VALUES ('16', 'bsquery', '2012-12-03 00:00:00', '3', '0', '0', '0', '0', '0', '12', '2012');

-- ----------------------------
-- Table structure for `business_except_alert`
-- ----------------------------
DROP TABLE IF EXISTS `business_except_alert`;
CREATE TABLE `business_except_alert` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(60) NOT NULL DEFAULT '' COMMENT '业务名',
  `alert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '报警时间',
  `except_code` varchar(10) DEFAULT NULL COMMENT '报警类型',
  `alert_info` varchar(255) DEFAULT NULL COMMENT '报警内容',
  `isread` char(1) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台异常事件报警';

-- ----------------------------
-- Records of business_except_alert
-- ----------------------------

-- ----------------------------
-- Table structure for `business_except_code`
-- ----------------------------
DROP TABLE IF EXISTS `business_except_code`;
CREATE TABLE `business_except_code` (
  `code` varchar(10) DEFAULT NULL COMMENT '业务异常代码',
  `code_desc` varchar(60) DEFAULT NULL COMMENT '业务异常描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务异常表';

-- ----------------------------
-- Records of business_except_code
-- ----------------------------
INSERT INTO `business_except_code` VALUES ('0001', ' 数据流量异常');
INSERT INTO `business_except_code` VALUES ('0002', ' 数据传输协议及端口异常');
INSERT INTO `business_except_code` VALUES ('0003', ' 数据包结构异常');
INSERT INTO `business_except_code` VALUES ('0004', ' 硬件设备运行情况异常');
INSERT INTO `business_except_code` VALUES ('0005', ' 异常硬件设备类型');
INSERT INTO `business_except_code` VALUES ('0006', ' 应用软件运行情况异常');
INSERT INTO `business_except_code` VALUES ('0007', ' 异常应用软件名称');
INSERT INTO `business_except_code` VALUES ('0008', ' 操作系统运行情况异常');
INSERT INTO `business_except_code` VALUES ('0009', ' 数据库运行情况异常');
INSERT INTO `business_except_code` VALUES ('保留', ' 其它');

-- ----------------------------
-- Table structure for `business_exch_model`
-- ----------------------------
DROP TABLE IF EXISTS `business_exch_model`;
;

-- ----------------------------
-- Records of business_exch_model
-- ----------------------------
INSERT INTO `business_exch_model` VALUES ('0100', '数据交换');
INSERT INTO `business_exch_model` VALUES ('0101', '文件传输');
INSERT INTO `business_exch_model` VALUES ('0102', '数据库传输');
INSERT INTO `business_exch_model` VALUES ('0103', '流媒体');
INSERT INTO `business_exch_model` VALUES ('0200', '授权访问');
INSERT INTO `business_exch_model` VALUES ('0300', '其他');

-- ----------------------------
-- Table structure for `business_hour_report`
-- ----------------------------
DROP TABLE IF EXISTS `business_hour_report`;
CREATE TABLE `business_hour_report` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(60) NOT NULL DEFAULT '' COMMENT '业务名',
  `report_date` date NOT NULL DEFAULT '2010-03-01' COMMENT '统计日',
  `report_hour` int(11) NOT NULL DEFAULT '0' COMMENT '统计小时',
  `ext_dataflow` float(10,2) DEFAULT '0.00',
  `int_dataflow` float(10,2) DEFAULT '0.00',
  `ext_record_count` int(11) DEFAULT '0' COMMENT '外网记录数',
  `int_record_count` int(11) DEFAULT '0' COMMENT '内网记录数',
  `alert_count` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `business_name` (`business_name`,`report_date`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='业务小时统计表';

-- ----------------------------
-- Records of business_hour_report
-- ----------------------------
INSERT INTO `business_hour_report` VALUES ('1', 'bsquery', '2012-11-22', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('2', 'bbb', '2012-11-22', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('3', 'bsquery', '2012-11-22', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('4', 'bbb', '2012-11-22', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('5', 'bsquery', '2012-11-22', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('6', 'bbb', '2012-11-22', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('7', 'bsquery', '2012-11-22', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('8', 'bbb', '2012-11-22', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('9', 'bsquery', '2012-11-22', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('10', 'bbb', '2012-11-22', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('11', 'bsquery', '2012-11-22', '19', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('12', 'bbb', '2012-11-22', '19', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('13', 'bsquery', '2012-11-23', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('14', 'bbb', '2012-11-23', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('15', 'bsquery', '2012-11-23', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('16', 'bbb', '2012-11-23', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('17', 'bsquery', '2012-11-23', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('18', 'bbb', '2012-11-23', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('19', 'bsquery', '2012-11-23', '12', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('20', 'bbb', '2012-11-23', '12', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('21', 'bsquery', '2012-11-23', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('22', 'bbb', '2012-11-23', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('23', 'bsquery', '2012-11-23', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('24', 'bbb', '2012-11-23', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('25', 'bsquery', '2012-11-23', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('26', 'bbb', '2012-11-23', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('27', 'bsquery', '2012-11-23', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('28', 'bbb', '2012-11-23', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('29', 'bbb', '2012-11-23', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('30', 'bsquery', '2012-11-23', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('31', 'bsquery', '2012-11-23', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('32', 'bbb', '2012-11-23', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('33', 'bsquery', '2012-11-26', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('34', 'bbb', '2012-11-26', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('35', 'bsquery', '2012-11-26', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('36', 'bbb', '2012-11-26', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('37', 'bsquery', '2012-11-26', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('38', 'bbb', '2012-11-26', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('39', 'bsquery', '2012-11-26', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('40', 'bbb', '2012-11-26', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('41', 'bsquery', '2012-11-26', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('42', 'bbb', '2012-11-26', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('43', 'bsquery', '2012-11-27', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('44', 'bbb', '2012-11-27', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('45', 'bsquery', '2012-11-27', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('46', 'bbb', '2012-11-27', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('47', 'bsquery', '2012-11-28', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('48', 'bbb', '2012-11-28', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('49', 'bsquery', '2012-11-28', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('50', 'bbb', '2012-11-28', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('51', 'bsquery', '2012-11-28', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('52', 'bbb', '2012-11-28', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('53', 'bsquery', '2012-11-28', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('54', 'bbb', '2012-11-28', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('55', 'bsquery', '2012-11-29', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('56', 'bbb', '2012-11-29', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('57', 'bsquery', '2012-11-29', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('58', 'bbb', '2012-11-29', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('59', 'bsquery', '2012-11-29', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('60', 'bbb', '2012-11-29', '11', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('61', 'bsquery', '2012-11-29', '12', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('62', 'bbb', '2012-11-29', '12', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('63', 'bbb', '2012-11-29', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('64', 'bsquery', '2012-11-29', '13', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('65', 'bsquery', '2012-11-29', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('66', 'bbb', '2012-11-29', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('67', 'bsquery', '2012-11-29', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('68', 'bbb', '2012-11-29', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('69', 'bsquery', '2012-11-29', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('70', 'bbb', '2012-11-29', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('71', 'bsquery', '2012-11-29', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('72', 'bbb', '2012-11-29', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('73', 'bsquery', '2012-11-29', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('74', 'bbb', '2012-11-29', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('75', 'bsquery', '2012-11-30', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('76', 'bbb', '2012-11-30', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('77', 'bsquery', '2012-11-30', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('78', 'bbb', '2012-11-30', '10', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('79', 'bsquery', '2012-11-30', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('80', 'bbb', '2012-11-30', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('81', 'bbb', '2012-11-30', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('82', 'bsquery', '2012-11-30', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('83', 'bsquery', '2012-11-30', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('84', 'bbb', '2012-11-30', '18', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('85', 'bsquery', '2012-11-30', '19', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('86', 'bbb', '2012-11-30', '19', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('87', 'bsquery', '2012-11-30', '20', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('88', 'bbb', '2012-11-30', '20', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('89', 'bsquery', '2012-11-30', '21', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('90', 'bbb', '2012-11-30', '21', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('91', 'bsquery', '2012-12-03', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('92', 'bbb', '2012-12-03', '9', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('93', 'bsquery', '2012-12-03', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('94', 'bbb', '2012-12-03', '14', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('95', 'bsquery', '2012-12-03', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('96', 'bbb', '2012-12-03', '15', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('97', 'bsquery', '2012-12-03', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('98', 'bbb', '2012-12-03', '16', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('99', 'bsquery', '2012-12-03', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('100', 'bbb', '2012-12-03', '17', '0.00', '0.00', '0', '0', '0', '1');
INSERT INTO `business_hour_report` VALUES ('101', 'bsquery', '2012-12-04', '9', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('102', 'bbb', '2012-12-04', '9', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('103', 'bbb', '2012-12-04', '10', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('104', 'bsquery', '2012-12-04', '10', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('105', 'bsquery', '2012-12-04', '14', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('106', 'bbb', '2012-12-04', '14', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('107', 'bsquery', '2012-12-04', '15', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('108', 'bbb', '2012-12-04', '15', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('109', 'bsquery', '2012-12-04', '16', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('110', 'bbb', '2012-12-04', '16', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('111', 'bsquery', '2012-12-04', '17', '0.00', '0.00', '0', '0', '0', '0');
INSERT INTO `business_hour_report` VALUES ('112', 'bbb', '2012-12-04', '17', '0.00', '0.00', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `business_log`
-- ----------------------------
DROP TABLE IF EXISTS `business_log`;
CREATE TABLE `business_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) DEFAULT NULL COMMENT '统计等级',
  `log_time` datetime DEFAULT NULL COMMENT '统计时间',
  `business_name` varchar(60) DEFAULT NULL COMMENT '业务名',
  `platform_name` varchar(255) DEFAULT NULL COMMENT '平台名称',
  `audit_info` text,
  `source_ip` varchar(255) DEFAULT NULL,
  `source_dest` varchar(255) DEFAULT NULL,
  `dest_ip` varchar(255) DEFAULT NULL,
  `dest_port` varchar(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `operation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ind_business_audit` (`level`,`business_name`,`log_time`,`platform_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务日志表';

-- ----------------------------
-- Records of business_log
-- ----------------------------

-- ----------------------------
-- Table structure for `business_month_report`
-- ----------------------------
DROP TABLE IF EXISTS `business_month_report`;
CREATE TABLE `business_month_report` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(255) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `report_month` int(11) DEFAULT NULL,
  `ext_dataflow` double DEFAULT NULL,
  `int_dataflow` double DEFAULT NULL,
  `ext_record_count` bigint(20) DEFAULT NULL,
  `int_record_count` bigint(20) DEFAULT NULL,
  `alert_count` bigint(20) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_month_report
-- ----------------------------
INSERT INTO `business_month_report` VALUES ('1', 'bbb', '2012-11-22 00:00:00', '11', '0', '0', '0', '0', '0', '2012');
INSERT INTO `business_month_report` VALUES ('2', 'bsquery', '2012-11-22 00:00:00', '11', '0', '0', '0', '0', '0', '2012');
INSERT INTO `business_month_report` VALUES ('3', 'bbb', '2012-12-03 00:00:00', '12', '0', '0', '0', '0', '0', '2012');
INSERT INTO `business_month_report` VALUES ('4', 'bsquery', '2012-12-03 00:00:00', '12', '0', '0', '0', '0', '0', '2012');

-- ----------------------------
-- Table structure for `business_register`
-- ----------------------------
DROP TABLE IF EXISTS `business_register`;
CREATE TABLE `business_register` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(60) NOT NULL DEFAULT '' COMMENT '业务系统名称',
  `business_depart` varchar(80) DEFAULT NULL COMMENT '业务管理部门',
  `business_code` varchar(30) DEFAULT NULL COMMENT '业务编码',
  `link_name` varchar(60) DEFAULT NULL COMMENT '链路名',
  `business_exch_model` varchar(10) DEFAULT NULL COMMENT '业务交换方式',
  `business_admin` varchar(40) DEFAULT NULL COMMENT '业务主管人',
  `admin_phone` varchar(20) DEFAULT NULL COMMENT '业务主管人电话',
  `admin_email` varchar(60) DEFAULT NULL COMMENT '主管人邮件',
  `admin_other_phone` varchar(20) DEFAULT NULL COMMENT '主管人其他联系方式',
  `auth_depart` varchar(80) DEFAULT NULL COMMENT '审批部门',
  `auth_date` timestamp NULL DEFAULT NULL COMMENT '审批时间',
  `auth_serial` varchar(80) DEFAULT NULL,
  `auth_material` mediumblob,
  `register_date` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `exchange_direction` varchar(10) DEFAULT NULL,
  `exch_protocol` varchar(40) DEFAULT NULL COMMENT '业务交换协议',
  `is_realtime` char(1) DEFAULT 'N' COMMENT '是否有实时性要求',
  `day_datavolume` int(11) DEFAULT NULL COMMENT '日数据交换量（M）',
  `is_approved` char(1) DEFAULT NULL COMMENT '是否备案',
  `approved_depart` varchar(255) DEFAULT NULL COMMENT '备案单位名称',
  `tp_graph` mediumblob COMMENT '拓扑图',
  `business_protocol` varchar(40) DEFAULT NULL COMMENT '业务协议名',
  `protocol_desc` varchar(255) DEFAULT NULL COMMENT '协议描述',
  `s_ip_range` varchar(255) DEFAULT NULL COMMENT '源IP地址范围',
  `s_port_range` varchar(255) DEFAULT NULL COMMENT '源端口范围',
  `d_ip_range` varchar(255) DEFAULT NULL COMMENT '目标IP地址范围',
  `d_port_range` varchar(255) DEFAULT NULL COMMENT '目标端口范围',
  `use_depart` varchar(80) DEFAULT NULL COMMENT '使用单位',
  `use_depart_tpye` varchar(10) DEFAULT NULL COMMENT '使用单位类型',
  `use_depart_address` varchar(255) DEFAULT NULL COMMENT '使用单位物理地址',
  `use_depart_admin_name` varchar(40) DEFAULT NULL COMMENT '使用单位主管人姓名',
  `use_depart_admin_phone` varchar(255) DEFAULT NULL COMMENT '使用单位主管人电话',
  `use_depart_admin_email` varchar(60) DEFAULT NULL COMMENT '使用单位主管人邮件',
  `use_depart_admin_other_phone` varchar(255) DEFAULT NULL COMMENT '其他联系方式',
  `terminal_amount` int(11) DEFAULT NULL COMMENT '终端数量',
  `user_amount` int(11) DEFAULT NULL COMMENT '用户数量',
  `business_desc` varchar(60) DEFAULT NULL,
  `auth_material_file_name` varchar(255) DEFAULT NULL,
  `tp_graph_file_name` varchar(255) DEFAULT NULL,
  `enablereport` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `FKFB985842A3483068` (`approved_depart`),
  KEY `FKFB985842FFDA8798` (`use_depart`),
  KEY `FKFB985842366A3857` (`auth_depart`),
  KEY `FKFB98584250ED4B3F` (`business_depart`),
  CONSTRAINT `FKFB985842366A3857` FOREIGN KEY (`auth_depart`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FKFB98584250ED4B3F` FOREIGN KEY (`business_depart`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FKFB985842A3483068` FOREIGN KEY (`approved_depart`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FKFB985842FFDA8798` FOREIGN KEY (`use_depart`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='业务注册表';

-- ----------------------------
-- Records of business_register
-- ----------------------------
INSERT INTO `business_register` VALUES ('9', 'bsquery', '410100', ' 03000', 'B/SAccess', '0100', 'admin', '133333333', 'admin@ga.gov.cn', 'admin@ga.gov.cn', '410000', '2012-04-10 07:50:07', '121111111111111', null, '2012-04-10 07:50:15', '0001', '0002', '1', '2000', '1', '410000', null, 'https', 'https', '192.168.20.22-192.168.20.254', '1-65536', '192.168.20.22-192.168.20.254', '1-65536', '410100', '03001', '北京市', 'admin', '133332222222', 'admin@ga.gov.cn', '111', '200', '200', '查询比对', null, null, '1');
INSERT INTO `business_register` VALUES ('10', 'bbb', '107000', ' 06002', 'B/SAccess', '0100', 'www', '2222', 'kai@com', '222222', '520322', '2012-05-18 10:10:03', '2222', null, '2012-05-18 10:10:10', '0002', '0002', '1', '200', '1', '530328', null, '', '', '10.20.30.22', '2525', '12.12.12.12', '100', '510304', '01501', 'sd', 'sddd', 'd', 'd', 'ddd', '22', '22', 'bbbbb', null, null, '0');

-- ----------------------------
-- Table structure for `business_report`
-- ----------------------------
DROP TABLE IF EXISTS `business_report`;
CREATE TABLE `business_report` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `report_date` date DEFAULT NULL COMMENT '统计时间',
  `business_name` varchar(80) DEFAULT NULL COMMENT '业务名',
  `record_count` int(11) DEFAULT '0' COMMENT '统计数',
  `datavolume` float(12,2) DEFAULT '0.00',
  `alert_count` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `report_date` (`report_date`,`business_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务统计表';

-- ----------------------------
-- Records of business_report
-- ----------------------------

-- ----------------------------
-- Table structure for `cardtype`
-- ----------------------------
DROP TABLE IF EXISTS `cardtype`;
CREATE TABLE `cardtype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(10) DEFAULT NULL,
  `type_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cardtype
-- ----------------------------
INSERT INTO `cardtype` VALUES ('1', 'USBKEY', '001');
INSERT INTO `cardtype` VALUES ('2', 'TFCard', '002');
INSERT INTO `cardtype` VALUES ('3', '其它类型', '003');

-- ----------------------------
-- Table structure for `conf`
-- ----------------------------
DROP TABLE IF EXISTS `conf`;
CREATE TABLE `conf` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conf
-- ----------------------------

-- ----------------------------
-- Table structure for `district`
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `district_id` varchar(6) NOT NULL,
  `district_name` varchar(80) NOT NULL,
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES ('100000', '公安部');
INSERT INTO `district` VALUES ('107000', '公安部消防局');
INSERT INTO `district` VALUES ('110000', '北京市');
INSERT INTO `district` VALUES ('110101', '北京市市辖区东城区');
INSERT INTO `district` VALUES ('110102', '北京市市辖区西城区');
INSERT INTO `district` VALUES ('110103', '北京市市辖区崇文区');
INSERT INTO `district` VALUES ('110104', '北京市市辖区宣武区');
INSERT INTO `district` VALUES ('110105', '北京市市辖区朝阳区');
INSERT INTO `district` VALUES ('110106', '北京市市辖区丰台区');
INSERT INTO `district` VALUES ('110107', '北京市市辖区石景山区');
INSERT INTO `district` VALUES ('110108', '北京市市辖区海淀区');
INSERT INTO `district` VALUES ('110109', '北京市市辖区门头沟区');
INSERT INTO `district` VALUES ('110111', '北京市市辖区房山区');
INSERT INTO `district` VALUES ('110112', '北京市市辖区通州区');
INSERT INTO `district` VALUES ('110113', '北京市市辖区顺义区');
INSERT INTO `district` VALUES ('110114', '北京市市辖区昌平区');
INSERT INTO `district` VALUES ('110115', '北京市市辖区大兴区');
INSERT INTO `district` VALUES ('110116', '北京市市辖区怀柔区');
INSERT INTO `district` VALUES ('110117', '北京市市辖区平谷区');
INSERT INTO `district` VALUES ('120000', '天津市');
INSERT INTO `district` VALUES ('120101', '天津市市辖区和平区');
INSERT INTO `district` VALUES ('120102', '天津市市辖区河东区');
INSERT INTO `district` VALUES ('120103', '天津市市辖区河西区');
INSERT INTO `district` VALUES ('120104', '天津市市辖区南开区');
INSERT INTO `district` VALUES ('120105', '天津市市辖区河北区');
INSERT INTO `district` VALUES ('120106', '天津市市辖区红桥区');
INSERT INTO `district` VALUES ('120107', '天津市市辖区塘沽区');
INSERT INTO `district` VALUES ('120108', '天津市市辖区汉沽区');
INSERT INTO `district` VALUES ('120109', '天津市市辖区大港区');
INSERT INTO `district` VALUES ('120110', '天津市市辖区东丽区');
INSERT INTO `district` VALUES ('120111', '天津市市辖区西青区');
INSERT INTO `district` VALUES ('120112', '天津市市辖区津南区');
INSERT INTO `district` VALUES ('120113', '天津市市辖区北辰区');
INSERT INTO `district` VALUES ('120114', '天津市市辖区武清区');
INSERT INTO `district` VALUES ('120115', '天津市市辖区宝坻区');
INSERT INTO `district` VALUES ('130000', '河北省');
INSERT INTO `district` VALUES ('130100', '河北省石家庄市');
INSERT INTO `district` VALUES ('130101', '河北省石家庄市市辖区');
INSERT INTO `district` VALUES ('130102', '河北省石家庄市长安区');
INSERT INTO `district` VALUES ('130103', '河北省石家庄市桥东区');
INSERT INTO `district` VALUES ('130104', '河北省石家庄市桥西区');
INSERT INTO `district` VALUES ('130105', '河北省石家庄市新华区');
INSERT INTO `district` VALUES ('130106', '河北省石家庄市郊区');
INSERT INTO `district` VALUES ('130107', '河北省石家庄市井陉矿区');
INSERT INTO `district` VALUES ('130108', '河北省石家庄市裕华区');
INSERT INTO `district` VALUES ('130121', '河北省石家庄市井陉县');
INSERT INTO `district` VALUES ('130123', '河北省石家庄市正定县');
INSERT INTO `district` VALUES ('130124', '河北省石家庄市栾城县');
INSERT INTO `district` VALUES ('130125', '河北省石家庄市行唐县');
INSERT INTO `district` VALUES ('130126', '河北省石家庄市灵寿县');
INSERT INTO `district` VALUES ('130127', '河北省石家庄市高邑县');
INSERT INTO `district` VALUES ('130128', '河北省石家庄市深泽县');
INSERT INTO `district` VALUES ('130129', '河北省石家庄市赞皇县');
INSERT INTO `district` VALUES ('130130', '河北省石家庄市无极县');
INSERT INTO `district` VALUES ('130131', '河北省石家庄市平山县');
INSERT INTO `district` VALUES ('130132', '河北省石家庄市元氏县');
INSERT INTO `district` VALUES ('130133', '河北省石家庄市赵县');
INSERT INTO `district` VALUES ('130181', '河北省石家庄市辛集市');
INSERT INTO `district` VALUES ('130182', '河北省石家庄市藁城市');
INSERT INTO `district` VALUES ('130183', '河北省石家庄市晋州市');
INSERT INTO `district` VALUES ('130184', '河北省石家庄市新乐市');
INSERT INTO `district` VALUES ('130185', '河北省石家庄市鹿泉市');
INSERT INTO `district` VALUES ('130200', '河北省唐山市');
INSERT INTO `district` VALUES ('130201', '河北省唐山市市辖区');
INSERT INTO `district` VALUES ('130202', '河北省唐山市路南区');
INSERT INTO `district` VALUES ('130203', '河北省唐山市路北区');
INSERT INTO `district` VALUES ('130204', '河北省唐山市古冶区');
INSERT INTO `district` VALUES ('130205', '河北省唐山市开平区');
INSERT INTO `district` VALUES ('130206', '河北省唐山市新区');
INSERT INTO `district` VALUES ('130207', '河北省唐山市丰南区');
INSERT INTO `district` VALUES ('130208', '河北省唐山市丰润区');
INSERT INTO `district` VALUES ('130221', '河北省唐山市丰润县');
INSERT INTO `district` VALUES ('130223', '河北省唐山市滦县');
INSERT INTO `district` VALUES ('130224', '河北省唐山市滦南县');
INSERT INTO `district` VALUES ('130225', '河北省唐山市乐亭县');
INSERT INTO `district` VALUES ('130227', '河北省唐山市迁西县');
INSERT INTO `district` VALUES ('130229', '河北省唐山市玉田县');
INSERT INTO `district` VALUES ('130230', '河北省唐山市唐海县');
INSERT INTO `district` VALUES ('130281', '河北省唐山市遵化市');
INSERT INTO `district` VALUES ('130282', '河北省唐山市丰南市');
INSERT INTO `district` VALUES ('130283', '河北省唐山市迁安市');
INSERT INTO `district` VALUES ('130300', '河北省秦皇岛市');
INSERT INTO `district` VALUES ('130301', '河北省秦皇岛市市辖区');
INSERT INTO `district` VALUES ('130302', '河北省秦皇岛市海港区');
INSERT INTO `district` VALUES ('130303', '河北省秦皇岛市山海关区');
INSERT INTO `district` VALUES ('130304', '河北省秦皇岛市北戴河区');
INSERT INTO `district` VALUES ('130321', '河北省秦皇岛市青龙满族自治县');
INSERT INTO `district` VALUES ('130322', '河北省秦皇岛市昌黎县');
INSERT INTO `district` VALUES ('130323', '河北省秦皇岛市抚宁县');
INSERT INTO `district` VALUES ('130324', '河北省秦皇岛市卢龙县');
INSERT INTO `district` VALUES ('130400', '河北省邯郸市');
INSERT INTO `district` VALUES ('130401', '河北省邯郸市市辖区');
INSERT INTO `district` VALUES ('130402', '河北省邯郸市邯山区');
INSERT INTO `district` VALUES ('130403', '河北省邯郸市丛台区');
INSERT INTO `district` VALUES ('130404', '河北省邯郸市复兴区');
INSERT INTO `district` VALUES ('130406', '河北省邯郸市峰峰矿区');
INSERT INTO `district` VALUES ('130421', '河北省邯郸市邯郸县');
INSERT INTO `district` VALUES ('130423', '河北省邯郸市临漳县');
INSERT INTO `district` VALUES ('130424', '河北省邯郸市成安县');
INSERT INTO `district` VALUES ('130425', '河北省邯郸市大名县');
INSERT INTO `district` VALUES ('130426', '河北省邯郸市涉县');
INSERT INTO `district` VALUES ('130427', '河北省邯郸市磁县');
INSERT INTO `district` VALUES ('130428', '河北省邯郸市肥乡县');
INSERT INTO `district` VALUES ('130429', '河北省邯郸市永年县');
INSERT INTO `district` VALUES ('130430', '河北省邯郸市邱县');
INSERT INTO `district` VALUES ('130431', '河北省邯郸市鸡泽县');
INSERT INTO `district` VALUES ('130432', '河北省邯郸市广平县');
INSERT INTO `district` VALUES ('130433', '河北省邯郸市馆陶县');
INSERT INTO `district` VALUES ('130434', '河北省邯郸市魏县');
INSERT INTO `district` VALUES ('130435', '河北省邯郸市曲周县');
INSERT INTO `district` VALUES ('130481', '河北省邯郸市武安市');
INSERT INTO `district` VALUES ('130500', '河北省邢台市');
INSERT INTO `district` VALUES ('130501', '河北省邢台市市辖区');
INSERT INTO `district` VALUES ('130502', '河北省邢台市桥东区');
INSERT INTO `district` VALUES ('130503', '河北省邢台市桥西区');
INSERT INTO `district` VALUES ('130521', '河北省邢台市邢台县');
INSERT INTO `district` VALUES ('130522', '河北省邢台市临城县');
INSERT INTO `district` VALUES ('130523', '河北省邢台市内丘县');
INSERT INTO `district` VALUES ('130524', '河北省邢台市柏乡县');
INSERT INTO `district` VALUES ('130525', '河北省邢台市隆尧县');
INSERT INTO `district` VALUES ('130526', '河北省邢台市任县');
INSERT INTO `district` VALUES ('130527', '河北省邢台市南和县');
INSERT INTO `district` VALUES ('130528', '河北省邢台市宁晋县');
INSERT INTO `district` VALUES ('130529', '河北省邢台市巨鹿县');
INSERT INTO `district` VALUES ('130530', '河北省邢台市新河县');
INSERT INTO `district` VALUES ('130531', '河北省邢台市广宗县');
INSERT INTO `district` VALUES ('130532', '河北省邢台市平乡县');
INSERT INTO `district` VALUES ('130533', '河北省邢台市威县');
INSERT INTO `district` VALUES ('130534', '河北省邢台市清河县');
INSERT INTO `district` VALUES ('130535', '河北省邢台市临西县');
INSERT INTO `district` VALUES ('130581', '河北省邢台市南宫市');
INSERT INTO `district` VALUES ('130582', '河北省邢台市沙河市');
INSERT INTO `district` VALUES ('130600', '河北省保定市');
INSERT INTO `district` VALUES ('130601', '河北省保定市市辖区');
INSERT INTO `district` VALUES ('130602', '河北省保定市新市区');
INSERT INTO `district` VALUES ('130603', '河北省保定市北市区');
INSERT INTO `district` VALUES ('130604', '河北省保定市南市区');
INSERT INTO `district` VALUES ('130621', '河北省保定市满城县');
INSERT INTO `district` VALUES ('130622', '河北省保定市清苑县');
INSERT INTO `district` VALUES ('130623', '河北省保定市涞水县');
INSERT INTO `district` VALUES ('130624', '河北省保定市阜平县');
INSERT INTO `district` VALUES ('130625', '河北省保定市徐水县');
INSERT INTO `district` VALUES ('130626', '河北省保定市定兴县');
INSERT INTO `district` VALUES ('130627', '河北省保定市唐县');
INSERT INTO `district` VALUES ('130628', '河北省保定市高阳县');
INSERT INTO `district` VALUES ('130629', '河北省保定市容城县');
INSERT INTO `district` VALUES ('130630', '河北省保定市涞源县');
INSERT INTO `district` VALUES ('130631', '河北省保定市望都县');
INSERT INTO `district` VALUES ('130632', '河北省保定市安新县');
INSERT INTO `district` VALUES ('130633', '河北省保定市易县');
INSERT INTO `district` VALUES ('130634', '河北省保定市曲阳县');
INSERT INTO `district` VALUES ('130635', '河北省保定市蠡县');
INSERT INTO `district` VALUES ('130636', '河北省保定市顺平县');
INSERT INTO `district` VALUES ('130637', '河北省保定市博野县');
INSERT INTO `district` VALUES ('130638', '河北省保定市雄县');
INSERT INTO `district` VALUES ('130681', '河北省保定市涿州市');
INSERT INTO `district` VALUES ('130682', '河北省保定市定州市');
INSERT INTO `district` VALUES ('130683', '河北省保定市安国市');
INSERT INTO `district` VALUES ('130684', '河北省保定市高碑店市');
INSERT INTO `district` VALUES ('130700', '河北省张家口市');
INSERT INTO `district` VALUES ('130701', '河北省张家口市市辖区');
INSERT INTO `district` VALUES ('130702', '河北省张家口市桥东区');
INSERT INTO `district` VALUES ('130703', '河北省张家口市桥西区');
INSERT INTO `district` VALUES ('130705', '河北省张家口市宣化区');
INSERT INTO `district` VALUES ('130706', '河北省张家口市下花园区');
INSERT INTO `district` VALUES ('130721', '河北省张家口市宣化县');
INSERT INTO `district` VALUES ('130722', '河北省张家口市张北县');
INSERT INTO `district` VALUES ('130723', '河北省张家口市康保县');
INSERT INTO `district` VALUES ('130724', '河北省张家口市沽源县');
INSERT INTO `district` VALUES ('130725', '河北省张家口市尚义县');
INSERT INTO `district` VALUES ('130726', '河北省张家口市蔚县');
INSERT INTO `district` VALUES ('130727', '河北省张家口市阳原县');
INSERT INTO `district` VALUES ('130728', '河北省张家口市怀安县');
INSERT INTO `district` VALUES ('130729', '河北省张家口市万全县');
INSERT INTO `district` VALUES ('130730', '河北省张家口市怀来县');
INSERT INTO `district` VALUES ('130731', '河北省张家口市涿鹿县');
INSERT INTO `district` VALUES ('130732', '河北省张家口市赤城县');
INSERT INTO `district` VALUES ('130733', '河北省张家口市崇礼县');
INSERT INTO `district` VALUES ('130800', '河北省承德市');
INSERT INTO `district` VALUES ('130801', '河北省承德市市辖区');
INSERT INTO `district` VALUES ('130802', '河北省承德市双桥区');
INSERT INTO `district` VALUES ('130803', '河北省承德市双滦区');
INSERT INTO `district` VALUES ('130804', '河北省承德市鹰手营子矿区');
INSERT INTO `district` VALUES ('130821', '河北省承德市承德县');
INSERT INTO `district` VALUES ('130822', '河北省承德市兴隆县');
INSERT INTO `district` VALUES ('130823', '河北省承德市平泉县');
INSERT INTO `district` VALUES ('130824', '河北省承德市滦平县');
INSERT INTO `district` VALUES ('130825', '河北省承德市隆化县');
INSERT INTO `district` VALUES ('130826', '河北省承德市丰宁满族自治县');
INSERT INTO `district` VALUES ('130827', '河北省承德市宽城满族自治县');
INSERT INTO `district` VALUES ('130828', '河北省承德市围场满族蒙古族自治县');
INSERT INTO `district` VALUES ('130900', '河北省沧州市');
INSERT INTO `district` VALUES ('130901', '河北省沧州市市辖区');
INSERT INTO `district` VALUES ('130902', '河北省沧州市新华区');
INSERT INTO `district` VALUES ('130903', '河北省沧州市运河区');
INSERT INTO `district` VALUES ('130921', '河北省沧州市沧县');
INSERT INTO `district` VALUES ('130922', '河北省沧州市青县');
INSERT INTO `district` VALUES ('130923', '河北省沧州市东光县');
INSERT INTO `district` VALUES ('130924', '河北省沧州市海兴县');
INSERT INTO `district` VALUES ('130925', '河北省沧州市盐山县');
INSERT INTO `district` VALUES ('130926', '河北省沧州市肃宁县');
INSERT INTO `district` VALUES ('130927', '河北省沧州市南皮县');
INSERT INTO `district` VALUES ('130928', '河北省沧州市吴桥县');
INSERT INTO `district` VALUES ('130929', '河北省沧州市献县');
INSERT INTO `district` VALUES ('130930', '河北省沧州市孟村回族自治县');
INSERT INTO `district` VALUES ('130981', '河北省沧州市泊头市');
INSERT INTO `district` VALUES ('130982', '河北省沧州市任丘市');
INSERT INTO `district` VALUES ('130983', '河北省沧州市黄骅市');
INSERT INTO `district` VALUES ('130984', '河北省沧州市河间市');
INSERT INTO `district` VALUES ('131000', '河北省廊坊市');
INSERT INTO `district` VALUES ('131001', '河北省廊坊市市辖区');
INSERT INTO `district` VALUES ('131002', '河北省廊坊市安次区');
INSERT INTO `district` VALUES ('131003', '河北省廊坊市广阳区');
INSERT INTO `district` VALUES ('131022', '河北省廊坊市固安县');
INSERT INTO `district` VALUES ('131023', '河北省廊坊市永清县');
INSERT INTO `district` VALUES ('131024', '河北省廊坊市香河县');
INSERT INTO `district` VALUES ('131025', '河北省廊坊市大城县');
INSERT INTO `district` VALUES ('131026', '河北省廊坊市文安县');
INSERT INTO `district` VALUES ('131028', '河北省廊坊市大厂回族自治县');
INSERT INTO `district` VALUES ('131081', '河北省廊坊市霸州市');
INSERT INTO `district` VALUES ('131082', '河北省廊坊市三河市');
INSERT INTO `district` VALUES ('131100', '河北省衡水市');
INSERT INTO `district` VALUES ('131101', '河北省衡水市市辖区');
INSERT INTO `district` VALUES ('131102', '河北省衡水市桃城区');
INSERT INTO `district` VALUES ('131121', '河北省衡水市枣强县');
INSERT INTO `district` VALUES ('131122', '河北省衡水市武邑县');
INSERT INTO `district` VALUES ('131123', '河北省衡水市武强县');
INSERT INTO `district` VALUES ('131124', '河北省衡水市饶阳县');
INSERT INTO `district` VALUES ('131125', '河北省衡水市安平县');
INSERT INTO `district` VALUES ('131126', '河北省衡水市故城县');
INSERT INTO `district` VALUES ('131127', '河北省衡水市景县');
INSERT INTO `district` VALUES ('131128', '河北省衡水市阜城县');
INSERT INTO `district` VALUES ('131181', '河北省衡水市冀州市');
INSERT INTO `district` VALUES ('131182', '河北省衡水市深州市');
INSERT INTO `district` VALUES ('140000', '山西省');
INSERT INTO `district` VALUES ('140100', '山西省太原市');
INSERT INTO `district` VALUES ('140101', '山西省太原市市辖区');
INSERT INTO `district` VALUES ('140105', '山西省太原市小店区');
INSERT INTO `district` VALUES ('140106', '山西省太原市迎泽区');
INSERT INTO `district` VALUES ('140107', '山西省太原市杏花岭区');
INSERT INTO `district` VALUES ('140108', '山西省太原市尖草坪区');
INSERT INTO `district` VALUES ('140109', '山西省太原市万柏林区');
INSERT INTO `district` VALUES ('140110', '山西省太原市晋源区');
INSERT INTO `district` VALUES ('140121', '山西省太原市清徐县');
INSERT INTO `district` VALUES ('140122', '山西省太原市阳曲县');
INSERT INTO `district` VALUES ('140123', '山西省太原市娄烦县');
INSERT INTO `district` VALUES ('140181', '山西省太原市古交市');
INSERT INTO `district` VALUES ('140200', '山西省大同市');
INSERT INTO `district` VALUES ('140201', '山西省大同市市辖区');
INSERT INTO `district` VALUES ('140202', '山西省大同市城区');
INSERT INTO `district` VALUES ('140203', '山西省大同市矿区');
INSERT INTO `district` VALUES ('140211', '山西省大同市南郊区');
INSERT INTO `district` VALUES ('140212', '山西省大同市新荣区');
INSERT INTO `district` VALUES ('140221', '山西省大同市阳高县');
INSERT INTO `district` VALUES ('140222', '山西省大同市天镇县');
INSERT INTO `district` VALUES ('140223', '山西省大同市广灵县');
INSERT INTO `district` VALUES ('140224', '山西省大同市灵丘县');
INSERT INTO `district` VALUES ('140225', '山西省大同市浑源县');
INSERT INTO `district` VALUES ('140226', '山西省大同市左云县');
INSERT INTO `district` VALUES ('140227', '山西省大同市大同县');
INSERT INTO `district` VALUES ('140300', '山西省阳泉市');
INSERT INTO `district` VALUES ('140301', '山西省阳泉市市辖区');
INSERT INTO `district` VALUES ('140302', '山西省阳泉市城区');
INSERT INTO `district` VALUES ('140303', '山西省阳泉市矿区');
INSERT INTO `district` VALUES ('140311', '山西省阳泉市郊区');
INSERT INTO `district` VALUES ('140321', '山西省阳泉市平定县');
INSERT INTO `district` VALUES ('140322', '山西省阳泉市盂县');
INSERT INTO `district` VALUES ('140400', '山西省长治市');
INSERT INTO `district` VALUES ('140401', '山西省长治市市辖区');
INSERT INTO `district` VALUES ('140402', '山西省长治市城区');
INSERT INTO `district` VALUES ('140411', '山西省长治市郊区');
INSERT INTO `district` VALUES ('140421', '山西省长治市长治县');
INSERT INTO `district` VALUES ('140423', '山西省长治市襄垣县');
INSERT INTO `district` VALUES ('140424', '山西省长治市屯留县');
INSERT INTO `district` VALUES ('140425', '山西省长治市平顺县');
INSERT INTO `district` VALUES ('140426', '山西省长治市黎城县');
INSERT INTO `district` VALUES ('140427', '山西省长治市壶关县');
INSERT INTO `district` VALUES ('140428', '山西省长治市长子县');
INSERT INTO `district` VALUES ('140429', '山西省长治市武乡县');
INSERT INTO `district` VALUES ('140430', '山西省长治市沁县');
INSERT INTO `district` VALUES ('140431', '山西省长治市沁源县');
INSERT INTO `district` VALUES ('140481', '山西省长治市潞城市');
INSERT INTO `district` VALUES ('140500', '山西省晋城市');
INSERT INTO `district` VALUES ('140501', '山西省晋城市市辖区');
INSERT INTO `district` VALUES ('140502', '山西省晋城市城区');
INSERT INTO `district` VALUES ('140521', '山西省晋城市沁水县');
INSERT INTO `district` VALUES ('140522', '山西省晋城市阳城县');
INSERT INTO `district` VALUES ('140524', '山西省晋城市陵川县');
INSERT INTO `district` VALUES ('140525', '山西省晋城市泽州县');
INSERT INTO `district` VALUES ('140581', '山西省晋城市高平市');
INSERT INTO `district` VALUES ('140600', '山西省朔州市');
INSERT INTO `district` VALUES ('140601', '山西省朔州市市辖区');
INSERT INTO `district` VALUES ('140602', '山西省朔州市朔城区');
INSERT INTO `district` VALUES ('140603', '山西省朔州市平鲁区');
INSERT INTO `district` VALUES ('140621', '山西省朔州市山阴县');
INSERT INTO `district` VALUES ('140622', '山西省朔州市应县');
INSERT INTO `district` VALUES ('140623', '山西省朔州市右玉县');
INSERT INTO `district` VALUES ('140624', '山西省朔州市怀仁县');
INSERT INTO `district` VALUES ('140700', '山西省晋中市');
INSERT INTO `district` VALUES ('140701', '山西省晋中市市辖区');
INSERT INTO `district` VALUES ('140702', '山西省晋中市榆次区');
INSERT INTO `district` VALUES ('140721', '山西省晋中市榆社县');
INSERT INTO `district` VALUES ('140722', '山西省晋中市左权县');
INSERT INTO `district` VALUES ('140723', '山西省晋中市和顺县');
INSERT INTO `district` VALUES ('140724', '山西省晋中市昔阳县');
INSERT INTO `district` VALUES ('140725', '山西省晋中市寿阳县');
INSERT INTO `district` VALUES ('140726', '山西省晋中市太谷县');
INSERT INTO `district` VALUES ('140727', '山西省晋中市祁县');
INSERT INTO `district` VALUES ('140728', '山西省晋中市平遥县');
INSERT INTO `district` VALUES ('140729', '山西省晋中市灵石县');
INSERT INTO `district` VALUES ('140781', '山西省晋中市介休市');
INSERT INTO `district` VALUES ('140800', '山西省运城市');
INSERT INTO `district` VALUES ('140801', '山西省运城市市辖区');
INSERT INTO `district` VALUES ('140802', '山西省运城市盐湖区');
INSERT INTO `district` VALUES ('140821', '山西省运城市临猗县');
INSERT INTO `district` VALUES ('140822', '山西省运城市万荣县');
INSERT INTO `district` VALUES ('140823', '山西省运城市闻喜县');
INSERT INTO `district` VALUES ('140824', '山西省运城市稷山县');
INSERT INTO `district` VALUES ('140825', '山西省运城市新绛县');
INSERT INTO `district` VALUES ('140826', '山西省运城市绛县');
INSERT INTO `district` VALUES ('140827', '山西省运城市垣曲县');
INSERT INTO `district` VALUES ('140828', '山西省运城市夏县');
INSERT INTO `district` VALUES ('140829', '山西省运城市平陆县');
INSERT INTO `district` VALUES ('140830', '山西省运城市芮城县');
INSERT INTO `district` VALUES ('140881', '山西省运城市永济市');
INSERT INTO `district` VALUES ('140882', '山西省运城市河津市');
INSERT INTO `district` VALUES ('140900', '山西省忻州市');
INSERT INTO `district` VALUES ('140901', '山西省忻州市市辖区');
INSERT INTO `district` VALUES ('140902', '山西省忻州市忻府区');
INSERT INTO `district` VALUES ('140921', '山西省忻州市定襄县');
INSERT INTO `district` VALUES ('140922', '山西省忻州市五台县');
INSERT INTO `district` VALUES ('140923', '山西省忻州市代县');
INSERT INTO `district` VALUES ('140924', '山西省忻州市繁峙县');
INSERT INTO `district` VALUES ('140925', '山西省忻州市宁武县');
INSERT INTO `district` VALUES ('140926', '山西省忻州市静乐县');
INSERT INTO `district` VALUES ('140927', '山西省忻州市神池县');
INSERT INTO `district` VALUES ('140928', '山西省忻州市五寨县');
INSERT INTO `district` VALUES ('140929', '山西省忻州市岢岚县');
INSERT INTO `district` VALUES ('140930', '山西省忻州市河曲县');
INSERT INTO `district` VALUES ('140931', '山西省忻州市保德县');
INSERT INTO `district` VALUES ('140932', '山西省忻州市偏关县');
INSERT INTO `district` VALUES ('140981', '山西省忻州市原平市');
INSERT INTO `district` VALUES ('141000', '山西省临汾市');
INSERT INTO `district` VALUES ('141001', '山西省临汾市市辖区');
INSERT INTO `district` VALUES ('141002', '山西省临汾市尧都区');
INSERT INTO `district` VALUES ('141021', '山西省临汾市曲沃县');
INSERT INTO `district` VALUES ('141022', '山西省临汾市翼城县');
INSERT INTO `district` VALUES ('141023', '山西省临汾市襄汾县');
INSERT INTO `district` VALUES ('141024', '山西省临汾市洪洞县');
INSERT INTO `district` VALUES ('141025', '山西省临汾市古县');
INSERT INTO `district` VALUES ('141026', '山西省临汾市安泽县');
INSERT INTO `district` VALUES ('141027', '山西省临汾市浮山县');
INSERT INTO `district` VALUES ('141028', '山西省临汾市吉县');
INSERT INTO `district` VALUES ('141029', '山西省临汾市乡宁县');
INSERT INTO `district` VALUES ('141030', '山西省临汾市大宁县');
INSERT INTO `district` VALUES ('141031', '山西省临汾市隰县');
INSERT INTO `district` VALUES ('141032', '山西省临汾市永和县');
INSERT INTO `district` VALUES ('141033', '山西省临汾市蒲县');
INSERT INTO `district` VALUES ('141034', '山西省临汾市汾西县');
INSERT INTO `district` VALUES ('141081', '山西省临汾市侯马市');
INSERT INTO `district` VALUES ('141082', '山西省临汾市霍州市');
INSERT INTO `district` VALUES ('141100', '山西省吕梁市');
INSERT INTO `district` VALUES ('141101', '山西省吕梁市市辖区');
INSERT INTO `district` VALUES ('141102', '山西省吕梁市离石区');
INSERT INTO `district` VALUES ('141121', '山西省吕梁市文水县');
INSERT INTO `district` VALUES ('141122', '山西省吕梁市交城县');
INSERT INTO `district` VALUES ('141123', '山西省吕梁市兴县');
INSERT INTO `district` VALUES ('141124', '山西省吕梁市临县');
INSERT INTO `district` VALUES ('141125', '山西省吕梁市柳林县');
INSERT INTO `district` VALUES ('141126', '山西省吕梁市石楼县');
INSERT INTO `district` VALUES ('141127', '山西省吕梁市岚县');
INSERT INTO `district` VALUES ('141128', '山西省吕梁市方山县');
INSERT INTO `district` VALUES ('141129', '山西省吕梁市中阳县');
INSERT INTO `district` VALUES ('141130', '山西省吕梁市交口县');
INSERT INTO `district` VALUES ('141181', '山西省吕梁市孝义市');
INSERT INTO `district` VALUES ('141182', '山西省吕梁市汾阳市');
INSERT INTO `district` VALUES ('142200', '山西省忻州地区');
INSERT INTO `district` VALUES ('142201', '山西省忻州地区忻州市');
INSERT INTO `district` VALUES ('142202', '山西省忻州地区原平市');
INSERT INTO `district` VALUES ('142222', '山西省忻州地区定襄县');
INSERT INTO `district` VALUES ('142223', '山西省忻州地区五台县');
INSERT INTO `district` VALUES ('142225', '山西省忻州地区代县');
INSERT INTO `district` VALUES ('142226', '山西省忻州地区繁峙县');
INSERT INTO `district` VALUES ('142227', '山西省忻州地区宁武县');
INSERT INTO `district` VALUES ('142228', '山西省忻州地区静乐县');
INSERT INTO `district` VALUES ('142229', '山西省忻州地区神池县');
INSERT INTO `district` VALUES ('142230', '山西省忻州地区五寨县');
INSERT INTO `district` VALUES ('142231', '山西省忻州地区岢岚县');
INSERT INTO `district` VALUES ('142232', '山西省忻州地区河曲县');
INSERT INTO `district` VALUES ('142233', '山西省忻州地区保德县');
INSERT INTO `district` VALUES ('142234', '山西省忻州地区偏关县');
INSERT INTO `district` VALUES ('142300', '山西省吕梁地区');
INSERT INTO `district` VALUES ('142301', '山西省吕梁地区孝义市');
INSERT INTO `district` VALUES ('142302', '山西省吕梁地区离石市');
INSERT INTO `district` VALUES ('142303', '山西省吕梁地区汾阳市');
INSERT INTO `district` VALUES ('142322', '山西省吕梁地区文水县');
INSERT INTO `district` VALUES ('142323', '山西省吕梁地区交城县');
INSERT INTO `district` VALUES ('142325', '山西省吕梁地区兴县');
INSERT INTO `district` VALUES ('142326', '山西省吕梁地区临县');
INSERT INTO `district` VALUES ('142327', '山西省吕梁地区柳林县');
INSERT INTO `district` VALUES ('142328', '山西省吕梁地区石楼县');
INSERT INTO `district` VALUES ('142329', '山西省吕梁地区岚县');
INSERT INTO `district` VALUES ('142330', '山西省吕梁地区方山县');
INSERT INTO `district` VALUES ('142332', '山西省吕梁地区中阳县');
INSERT INTO `district` VALUES ('142333', '山西省吕梁地区交口县');
INSERT INTO `district` VALUES ('142400', '山西省晋中地区');
INSERT INTO `district` VALUES ('142401', '山西省晋中地区榆次市');
INSERT INTO `district` VALUES ('142402', '山西省晋中地区介休市');
INSERT INTO `district` VALUES ('142421', '山西省晋中地区榆社县');
INSERT INTO `district` VALUES ('142422', '山西省晋中地区左权县');
INSERT INTO `district` VALUES ('142423', '山西省晋中地区和顺县');
INSERT INTO `district` VALUES ('142424', '山西省晋中地区昔阳县');
INSERT INTO `district` VALUES ('142427', '山西省晋中地区寿阳县');
INSERT INTO `district` VALUES ('142429', '山西省晋中地区太谷县');
INSERT INTO `district` VALUES ('142430', '山西省晋中地区祁县');
INSERT INTO `district` VALUES ('142431', '山西省晋中地区平遥县');
INSERT INTO `district` VALUES ('142433', '山西省晋中地区灵石县');
INSERT INTO `district` VALUES ('142600', '山西省临汾地区');
INSERT INTO `district` VALUES ('142601', '山西省临汾地区临汾市');
INSERT INTO `district` VALUES ('142602', '山西省临汾地区侯马市');
INSERT INTO `district` VALUES ('142603', '山西省临汾地区霍州市');
INSERT INTO `district` VALUES ('142621', '山西省临汾地区曲沃县');
INSERT INTO `district` VALUES ('142622', '山西省临汾地区翼城县');
INSERT INTO `district` VALUES ('142623', '山西省临汾地区襄汾县');
INSERT INTO `district` VALUES ('142625', '山西省临汾地区洪洞县');
INSERT INTO `district` VALUES ('142627', '山西省临汾地区古县');
INSERT INTO `district` VALUES ('142628', '山西省临汾地区安泽县');
INSERT INTO `district` VALUES ('142629', '山西省临汾地区浮山县');
INSERT INTO `district` VALUES ('142630', '山西省临汾地区吉县');
INSERT INTO `district` VALUES ('142631', '山西省临汾地区乡宁县');
INSERT INTO `district` VALUES ('142632', '山西省临汾地区蒲县');
INSERT INTO `district` VALUES ('142633', '山西省临汾地区大宁县');
INSERT INTO `district` VALUES ('142634', '山西省临汾地区永和县');
INSERT INTO `district` VALUES ('142635', '山西省临汾地区隰县');
INSERT INTO `district` VALUES ('142636', '山西省临汾地区汾西县');
INSERT INTO `district` VALUES ('142700', '山西省运城地区');
INSERT INTO `district` VALUES ('142701', '山西省运城地区运城市');
INSERT INTO `district` VALUES ('142702', '山西省运城地区永济市');
INSERT INTO `district` VALUES ('142703', '山西省运城地区河津市');
INSERT INTO `district` VALUES ('142723', '山西省运城地区芮城县');
INSERT INTO `district` VALUES ('142724', '山西省运城地区临猗县');
INSERT INTO `district` VALUES ('142725', '山西省运城地区万荣县');
INSERT INTO `district` VALUES ('142726', '山西省运城地区新绛县');
INSERT INTO `district` VALUES ('142727', '山西省运城地区稷山县');
INSERT INTO `district` VALUES ('142729', '山西省运城地区闻喜县');
INSERT INTO `district` VALUES ('142730', '山西省运城地区夏县');
INSERT INTO `district` VALUES ('142731', '山西省运城地区绛县');
INSERT INTO `district` VALUES ('142732', '山西省运城地区平陆县');
INSERT INTO `district` VALUES ('142733', '山西省运城地区垣曲县');
INSERT INTO `district` VALUES ('150000', '内蒙古');
INSERT INTO `district` VALUES ('150100', '内蒙古自治区呼和浩特市');
INSERT INTO `district` VALUES ('150101', '内蒙古自治区呼和浩特市市辖区');
INSERT INTO `district` VALUES ('150102', '内蒙古自治区呼和浩特市新城区');
INSERT INTO `district` VALUES ('150103', '内蒙古自治区呼和浩特市回民区');
INSERT INTO `district` VALUES ('150104', '内蒙古自治区呼和浩特市玉泉区');
INSERT INTO `district` VALUES ('150105', '内蒙古自治区呼和浩特市郊区');
INSERT INTO `district` VALUES ('150121', '内蒙古自治区呼和浩特市土默特左旗');
INSERT INTO `district` VALUES ('150122', '内蒙古自治区呼和浩特市托克托县');
INSERT INTO `district` VALUES ('150123', '内蒙古自治区呼和浩特市和林格尔县');
INSERT INTO `district` VALUES ('150124', '内蒙古自治区呼和浩特市清水河县');
INSERT INTO `district` VALUES ('150125', '内蒙古自治区呼和浩特市武川县');
INSERT INTO `district` VALUES ('150200', '内蒙古自治区包头市');
INSERT INTO `district` VALUES ('150201', '内蒙古自治区包头市市辖区');
INSERT INTO `district` VALUES ('150202', '内蒙古自治区包头市东河区');
INSERT INTO `district` VALUES ('150203', '内蒙古自治区包头市昆都伦区');
INSERT INTO `district` VALUES ('150204', '内蒙古自治区包头市青山区');
INSERT INTO `district` VALUES ('150205', '内蒙古自治区包头市石拐矿区');
INSERT INTO `district` VALUES ('150206', '内蒙古自治区包头市白云矿区');
INSERT INTO `district` VALUES ('150207', '内蒙古自治区包头市郊区');
INSERT INTO `district` VALUES ('150221', '内蒙古自治区包头市土默特右旗');
INSERT INTO `district` VALUES ('150222', '内蒙古自治区包头市固阳县');
INSERT INTO `district` VALUES ('150223', '内蒙古自治区包头市达尔罕茂明安联合旗');
INSERT INTO `district` VALUES ('150300', '内蒙古自治区乌海市');
INSERT INTO `district` VALUES ('150301', '内蒙古自治区乌海市市辖区');
INSERT INTO `district` VALUES ('150302', '内蒙古自治区乌海市海勃湾区');
INSERT INTO `district` VALUES ('150303', '内蒙古自治区乌海市海南区');
INSERT INTO `district` VALUES ('150304', '内蒙古自治区乌海市乌达区');
INSERT INTO `district` VALUES ('150400', '内蒙古自治区赤峰市');
INSERT INTO `district` VALUES ('150401', '内蒙古自治区赤峰市市辖区');
INSERT INTO `district` VALUES ('150402', '内蒙古自治区赤峰市红山区');
INSERT INTO `district` VALUES ('150403', '内蒙古自治区赤峰市元宝山区');
INSERT INTO `district` VALUES ('150404', '内蒙古自治区赤峰市松山区');
INSERT INTO `district` VALUES ('150421', '内蒙古自治区赤峰市阿鲁科尔沁旗');
INSERT INTO `district` VALUES ('150422', '内蒙古自治区赤峰市巴林左旗');
INSERT INTO `district` VALUES ('150423', '内蒙古自治区赤峰市巴林右旗');
INSERT INTO `district` VALUES ('150424', '内蒙古自治区赤峰市林西县');
INSERT INTO `district` VALUES ('150425', '内蒙古自治区赤峰市克什克腾旗');
INSERT INTO `district` VALUES ('150426', '内蒙古自治区赤峰市翁牛特旗');
INSERT INTO `district` VALUES ('150428', '内蒙古自治区赤峰市喀喇沁旗');
INSERT INTO `district` VALUES ('150429', '内蒙古自治区赤峰市宁城县');
INSERT INTO `district` VALUES ('150430', '内蒙古自治区赤峰市敖汉旗');
INSERT INTO `district` VALUES ('150500', '内蒙古自治区通辽市');
INSERT INTO `district` VALUES ('150501', '内蒙古自治区通辽市市辖区');
INSERT INTO `district` VALUES ('150502', '内蒙古自治区通辽市科尔沁区');
INSERT INTO `district` VALUES ('150521', '内蒙古自治区通辽市科尔沁左翼中旗');
INSERT INTO `district` VALUES ('150522', '内蒙古自治区通辽市科尔沁左翼后旗');
INSERT INTO `district` VALUES ('150523', '内蒙古自治区通辽市开鲁县');
INSERT INTO `district` VALUES ('150524', '内蒙古自治区通辽市库伦旗');
INSERT INTO `district` VALUES ('150525', '内蒙古自治区通辽市奈曼旗');
INSERT INTO `district` VALUES ('150526', '内蒙古自治区通辽市扎鲁特旗');
INSERT INTO `district` VALUES ('150581', '内蒙古自治区通辽市霍林郭勒市');
INSERT INTO `district` VALUES ('150600', '内蒙古自治区鄂尔多斯市');
INSERT INTO `district` VALUES ('150602', '内蒙古自治区鄂尔多斯市东胜区');
INSERT INTO `district` VALUES ('150621', '内蒙古自治区鄂尔多斯市达拉特旗');
INSERT INTO `district` VALUES ('150622', '内蒙古自治区鄂尔多斯市准格尔旗');
INSERT INTO `district` VALUES ('150623', '内蒙古自治区鄂尔多斯市鄂托克前旗');
INSERT INTO `district` VALUES ('150624', '内蒙古自治区鄂尔多斯市鄂托克旗');
INSERT INTO `district` VALUES ('150625', '内蒙古自治区鄂尔多斯市杭锦旗');
INSERT INTO `district` VALUES ('150626', '内蒙古自治区鄂尔多斯市乌审旗');
INSERT INTO `district` VALUES ('150627', '内蒙古自治区鄂尔多斯市伊金霍洛旗');
INSERT INTO `district` VALUES ('150700', '内蒙古自治区呼伦贝尔市');
INSERT INTO `district` VALUES ('150701', '内蒙古自治区呼伦贝尔市市辖区');
INSERT INTO `district` VALUES ('150702', '内蒙古自治区呼伦贝尔市海拉尔区');
INSERT INTO `district` VALUES ('150721', '内蒙古自治区呼伦贝尔市阿荣旗');
INSERT INTO `district` VALUES ('150722', '内蒙古自治区呼伦贝尔市莫力达瓦达斡尔族自治旗');
INSERT INTO `district` VALUES ('150723', '内蒙古自治区呼伦贝尔市鄂伦春自治旗');
INSERT INTO `district` VALUES ('150724', '内蒙古自治区呼伦贝尔市鄂温克族自治旗');
INSERT INTO `district` VALUES ('150725', '内蒙古自治区呼伦贝尔市陈巴尔虎旗');
INSERT INTO `district` VALUES ('150726', '内蒙古自治区呼伦贝尔市新巴尔虎左旗');
INSERT INTO `district` VALUES ('150727', '内蒙古自治区呼伦贝尔市新巴尔虎右旗');
INSERT INTO `district` VALUES ('150781', '内蒙古自治区呼伦贝尔市满洲里市');
INSERT INTO `district` VALUES ('150782', '内蒙古自治区呼伦贝尔市牙克石市');
INSERT INTO `district` VALUES ('150783', '内蒙古自治区呼伦贝尔市扎兰屯市');
INSERT INTO `district` VALUES ('150784', '内蒙古自治区呼伦贝尔市额尔古纳市');
INSERT INTO `district` VALUES ('150785', '内蒙古自治区呼伦贝尔市根河市');
INSERT INTO `district` VALUES ('150800', '内蒙古自治区巴彦淖尔市');
INSERT INTO `district` VALUES ('150801', '内蒙古自治区巴彦淖尔市市辖区');
INSERT INTO `district` VALUES ('150802', '内蒙古自治区巴彦淖尔市临河区');
INSERT INTO `district` VALUES ('150821', '内蒙古自治区巴彦淖尔市五原县');
INSERT INTO `district` VALUES ('150822', '内蒙古自治区巴彦淖尔市磴口县');
INSERT INTO `district` VALUES ('150823', '内蒙古自治区巴彦淖尔市乌拉特前旗');
INSERT INTO `district` VALUES ('150824', '内蒙古自治区巴彦淖尔市乌拉特中旗');
INSERT INTO `district` VALUES ('150825', '内蒙古自治区巴彦淖尔市乌拉特后旗');
INSERT INTO `district` VALUES ('150826', '内蒙古自治区巴彦淖尔市杭锦后旗');
INSERT INTO `district` VALUES ('150900', '内蒙古自治区乌兰察布市');
INSERT INTO `district` VALUES ('150901', '内蒙古自治区乌兰察布市市辖区');
INSERT INTO `district` VALUES ('150902', '内蒙古自治区乌兰察布市集宁区');
INSERT INTO `district` VALUES ('150921', '内蒙古自治区乌兰察布市卓资县');
INSERT INTO `district` VALUES ('150922', '内蒙古自治区乌兰察布市化德县');
INSERT INTO `district` VALUES ('150923', '内蒙古自治区乌兰察布市商都县');
INSERT INTO `district` VALUES ('150924', '内蒙古自治区乌兰察布市兴和县');
INSERT INTO `district` VALUES ('150925', '内蒙古自治区乌兰察布市凉城县');
INSERT INTO `district` VALUES ('150926', '内蒙古自治区乌兰察布市察哈尔右翼前旗');
INSERT INTO `district` VALUES ('150927', '内蒙古自治区乌兰察布市察哈尔右翼中旗');
INSERT INTO `district` VALUES ('150928', '内蒙古自治区乌兰察布市察哈尔右翼后旗');
INSERT INTO `district` VALUES ('150929', '内蒙古自治区乌兰察布市四子王旗');
INSERT INTO `district` VALUES ('150981', '内蒙古自治区乌兰察布市丰镇市');
INSERT INTO `district` VALUES ('152100', '内蒙古自治区呼伦贝尔盟');
INSERT INTO `district` VALUES ('152101', '内蒙古自治区呼伦贝尔盟海拉尔市');
INSERT INTO `district` VALUES ('152102', '内蒙古自治区呼伦贝尔盟满洲里市');
INSERT INTO `district` VALUES ('152103', '内蒙古自治区呼伦贝尔盟扎兰屯市');
INSERT INTO `district` VALUES ('152104', '内蒙古自治区呼伦贝尔盟牙克石市');
INSERT INTO `district` VALUES ('152105', '内蒙古自治区呼伦贝尔盟根河市');
INSERT INTO `district` VALUES ('152106', '内蒙古自治区呼伦贝尔盟额尔古纳市');
INSERT INTO `district` VALUES ('152122', '内蒙古自治区呼伦贝尔盟阿荣旗');
INSERT INTO `district` VALUES ('152123', '内蒙古自治区呼伦贝尔盟莫力达瓦达斡尔族自治旗');
INSERT INTO `district` VALUES ('152127', '内蒙古自治区呼伦贝尔盟鄂伦春自治旗');
INSERT INTO `district` VALUES ('152128', '内蒙古自治区呼伦贝尔盟鄂温克族自治旗');
INSERT INTO `district` VALUES ('152129', '内蒙古自治区呼伦贝尔盟新巴尔虎右旗');
INSERT INTO `district` VALUES ('152130', '内蒙古自治区呼伦贝尔盟新巴尔虎左旗');
INSERT INTO `district` VALUES ('152131', '内蒙古自治区呼伦贝尔盟陈巴尔虎旗');
INSERT INTO `district` VALUES ('152200', '内蒙古自治区兴安盟');
INSERT INTO `district` VALUES ('152201', '内蒙古自治区兴安盟乌兰浩特市');
INSERT INTO `district` VALUES ('152202', '内蒙古自治区兴安盟阿尔山市');
INSERT INTO `district` VALUES ('152221', '内蒙古自治区兴安盟科尔沁右翼前旗');
INSERT INTO `district` VALUES ('152222', '内蒙古自治区兴安盟科尔沁右翼中旗');
INSERT INTO `district` VALUES ('152223', '内蒙古自治区兴安盟扎赉特旗');
INSERT INTO `district` VALUES ('152224', '内蒙古自治区兴安盟突泉县');
INSERT INTO `district` VALUES ('152300', '内蒙古自治区哲里木盟');
INSERT INTO `district` VALUES ('152301', '内蒙古自治区哲里木盟通辽市');
INSERT INTO `district` VALUES ('152302', '内蒙古自治区哲里木盟霍林郭勒市');
INSERT INTO `district` VALUES ('152322', '内蒙古自治区哲里木盟科尔沁左翼中旗');
INSERT INTO `district` VALUES ('152323', '内蒙古自治区哲里木盟科尔沁左翼后旗');
INSERT INTO `district` VALUES ('152324', '内蒙古自治区哲里木盟开鲁县');
INSERT INTO `district` VALUES ('152325', '内蒙古自治区哲里木盟库伦旗');
INSERT INTO `district` VALUES ('152326', '内蒙古自治区哲里木盟奈曼旗');
INSERT INTO `district` VALUES ('152327', '内蒙古自治区哲里木盟扎鲁特旗');
INSERT INTO `district` VALUES ('152500', '内蒙古自治区锡林郭勒盟');
INSERT INTO `district` VALUES ('152501', '内蒙古自治区锡林郭勒盟二连浩特市');
INSERT INTO `district` VALUES ('152502', '内蒙古自治区锡林郭勒盟锡林浩特市');
INSERT INTO `district` VALUES ('152522', '内蒙古自治区锡林郭勒盟阿巴嘎旗');
INSERT INTO `district` VALUES ('152523', '内蒙古自治区锡林郭勒盟苏尼特左旗');
INSERT INTO `district` VALUES ('152524', '内蒙古自治区锡林郭勒盟苏尼特右旗');
INSERT INTO `district` VALUES ('152525', '内蒙古自治区锡林郭勒盟东乌珠穆沁旗');
INSERT INTO `district` VALUES ('152526', '内蒙古自治区锡林郭勒盟西乌珠穆沁旗');
INSERT INTO `district` VALUES ('152527', '内蒙古自治区锡林郭勒盟太仆寺旗');
INSERT INTO `district` VALUES ('152528', '内蒙古自治区锡林郭勒盟镶黄旗');
INSERT INTO `district` VALUES ('152529', '内蒙古自治区锡林郭勒盟正镶白旗');
INSERT INTO `district` VALUES ('152530', '内蒙古自治区锡林郭勒盟正蓝旗');
INSERT INTO `district` VALUES ('152531', '内蒙古自治区锡林郭勒盟多伦县');
INSERT INTO `district` VALUES ('152600', '内蒙古自治区乌兰察布盟');
INSERT INTO `district` VALUES ('152601', '内蒙古自治区乌兰察布盟集宁市');
INSERT INTO `district` VALUES ('152602', '内蒙古自治区乌兰察布盟丰镇市');
INSERT INTO `district` VALUES ('152624', '内蒙古自治区乌兰察布盟卓资县');
INSERT INTO `district` VALUES ('152625', '内蒙古自治区乌兰察布盟化德县');
INSERT INTO `district` VALUES ('152626', '内蒙古自治区乌兰察布盟商都县');
INSERT INTO `district` VALUES ('152627', '内蒙古自治区乌兰察布盟兴和县');
INSERT INTO `district` VALUES ('152629', '内蒙古自治区乌兰察布盟凉城县');
INSERT INTO `district` VALUES ('152630', '内蒙古自治区乌兰察布盟察哈尔右翼前旗');
INSERT INTO `district` VALUES ('152631', '内蒙古自治区乌兰察布盟察哈尔右翼中旗');
INSERT INTO `district` VALUES ('152632', '内蒙古自治区乌兰察布盟察哈尔右翼后旗');
INSERT INTO `district` VALUES ('152634', '内蒙古自治区乌兰察布盟四子王旗');
INSERT INTO `district` VALUES ('152700', '内蒙古自治区伊克昭盟');
INSERT INTO `district` VALUES ('152701', '内蒙古自治区伊克昭盟东胜市');
INSERT INTO `district` VALUES ('152722', '内蒙古自治区伊克昭盟达拉特旗');
INSERT INTO `district` VALUES ('152723', '内蒙古自治区伊克昭盟准格尔旗');
INSERT INTO `district` VALUES ('152724', '内蒙古自治区伊克昭盟鄂托克前旗');
INSERT INTO `district` VALUES ('152725', '内蒙古自治区伊克昭盟鄂托克旗');
INSERT INTO `district` VALUES ('152726', '内蒙古自治区伊克昭盟杭锦旗');
INSERT INTO `district` VALUES ('152727', '内蒙古自治区伊克昭盟乌审旗');
INSERT INTO `district` VALUES ('152728', '内蒙古自治区伊克昭盟伊金霍洛旗');
INSERT INTO `district` VALUES ('152800', '内蒙古自治区巴彦淖尔盟');
INSERT INTO `district` VALUES ('152801', '内蒙古自治区巴彦淖尔盟临河市');
INSERT INTO `district` VALUES ('152822', '内蒙古自治区巴彦淖尔盟五原县');
INSERT INTO `district` VALUES ('152823', '内蒙古自治区巴彦淖尔盟磴口县');
INSERT INTO `district` VALUES ('152824', '内蒙古自治区巴彦淖尔盟乌拉特前旗');
INSERT INTO `district` VALUES ('152825', '内蒙古自治区巴彦淖尔盟乌拉特中旗');
INSERT INTO `district` VALUES ('152826', '内蒙古自治区巴彦淖尔盟乌拉特后旗');
INSERT INTO `district` VALUES ('152827', '内蒙古自治区巴彦淖尔盟杭锦后旗');
INSERT INTO `district` VALUES ('152900', '内蒙古自治区阿拉善盟');
INSERT INTO `district` VALUES ('152921', '内蒙古自治区阿拉善盟阿拉善左旗');
INSERT INTO `district` VALUES ('152922', '内蒙古自治区阿拉善盟阿拉善右旗');
INSERT INTO `district` VALUES ('152923', '内蒙古自治区阿拉善盟额济纳旗');
INSERT INTO `district` VALUES ('210000', '辽宁省');
INSERT INTO `district` VALUES ('210100', '辽宁省沈阳市');
INSERT INTO `district` VALUES ('210101', '辽宁省沈阳市市辖区');
INSERT INTO `district` VALUES ('210102', '辽宁省沈阳市和平区');
INSERT INTO `district` VALUES ('210103', '辽宁省沈阳市沈河区');
INSERT INTO `district` VALUES ('210104', '辽宁省沈阳市大东区');
INSERT INTO `district` VALUES ('210105', '辽宁省沈阳市皇姑区');
INSERT INTO `district` VALUES ('210106', '辽宁省沈阳市铁西区');
INSERT INTO `district` VALUES ('210111', '辽宁省沈阳市苏家屯区');
INSERT INTO `district` VALUES ('210112', '辽宁省沈阳市东陵区');
INSERT INTO `district` VALUES ('210113', '辽宁省沈阳市新城子区');
INSERT INTO `district` VALUES ('210114', '辽宁省沈阳市于洪区');
INSERT INTO `district` VALUES ('210122', '辽宁省沈阳市辽中县');
INSERT INTO `district` VALUES ('210123', '辽宁省沈阳市康平县');
INSERT INTO `district` VALUES ('210124', '辽宁省沈阳市法库县');
INSERT INTO `district` VALUES ('210181', '辽宁省沈阳市新民市');
INSERT INTO `district` VALUES ('210200', '辽宁省大连市');
INSERT INTO `district` VALUES ('210201', '辽宁省大连市市辖区');
INSERT INTO `district` VALUES ('210202', '辽宁省大连市中山区');
INSERT INTO `district` VALUES ('210203', '辽宁省大连市西岗区');
INSERT INTO `district` VALUES ('210204', '辽宁省大连市沙河口区');
INSERT INTO `district` VALUES ('210211', '辽宁省大连市甘井子区');
INSERT INTO `district` VALUES ('210212', '辽宁省大连市旅顺口区');
INSERT INTO `district` VALUES ('210213', '辽宁省大连市金州区');
INSERT INTO `district` VALUES ('210224', '辽宁省大连市长海县');
INSERT INTO `district` VALUES ('210281', '辽宁省大连市瓦房店市');
INSERT INTO `district` VALUES ('210282', '辽宁省大连市普兰店市');
INSERT INTO `district` VALUES ('210283', '辽宁省大连市庄河市');
INSERT INTO `district` VALUES ('210300', '辽宁省鞍山市');
INSERT INTO `district` VALUES ('210301', '辽宁省鞍山市市辖区');
INSERT INTO `district` VALUES ('210302', '辽宁省鞍山市铁东区');
INSERT INTO `district` VALUES ('210303', '辽宁省鞍山市铁西区');
INSERT INTO `district` VALUES ('210304', '辽宁省鞍山市立山区');
INSERT INTO `district` VALUES ('210311', '辽宁省鞍山市千山区');
INSERT INTO `district` VALUES ('210321', '辽宁省鞍山市台安县');
INSERT INTO `district` VALUES ('210323', '辽宁省鞍山市岫岩满族自治县');
INSERT INTO `district` VALUES ('210381', '辽宁省鞍山市海城市');
INSERT INTO `district` VALUES ('210400', '辽宁省抚顺市');
INSERT INTO `district` VALUES ('210401', '辽宁省抚顺市市辖区');
INSERT INTO `district` VALUES ('210402', '辽宁省抚顺市新抚区');
INSERT INTO `district` VALUES ('210403', '辽宁省抚顺市露天区');
INSERT INTO `district` VALUES ('210404', '辽宁省抚顺市望花区');
INSERT INTO `district` VALUES ('210411', '辽宁省抚顺市顺城区');
INSERT INTO `district` VALUES ('210421', '辽宁省抚顺市抚顺县');
INSERT INTO `district` VALUES ('210422', '辽宁省抚顺市新宾满族自治县');
INSERT INTO `district` VALUES ('210423', '辽宁省抚顺市清原满族自治县');
INSERT INTO `district` VALUES ('210500', '辽宁省本溪市');
INSERT INTO `district` VALUES ('210501', '辽宁省本溪市市辖区');
INSERT INTO `district` VALUES ('210502', '辽宁省本溪市平山区');
INSERT INTO `district` VALUES ('210503', '辽宁省本溪市溪湖区');
INSERT INTO `district` VALUES ('210504', '辽宁省本溪市明山区');
INSERT INTO `district` VALUES ('210505', '辽宁省本溪市南芬区');
INSERT INTO `district` VALUES ('210521', '辽宁省本溪市本溪满族自治县');
INSERT INTO `district` VALUES ('210522', '辽宁省本溪市桓仁满族自治县');
INSERT INTO `district` VALUES ('210600', '辽宁省丹东市');
INSERT INTO `district` VALUES ('210601', '辽宁省丹东市市辖区');
INSERT INTO `district` VALUES ('210602', '辽宁省丹东市元宝区');
INSERT INTO `district` VALUES ('210603', '辽宁省丹东市振兴区');
INSERT INTO `district` VALUES ('210604', '辽宁省丹东市振安区');
INSERT INTO `district` VALUES ('210624', '辽宁省丹东市宽甸满族自治县');
INSERT INTO `district` VALUES ('210681', '辽宁省丹东市东港市');
INSERT INTO `district` VALUES ('210682', '辽宁省丹东市凤城市');
INSERT INTO `district` VALUES ('210700', '辽宁省锦州市');
INSERT INTO `district` VALUES ('210701', '辽宁省锦州市市辖区');
INSERT INTO `district` VALUES ('210702', '辽宁省锦州市古塔区');
INSERT INTO `district` VALUES ('210703', '辽宁省锦州市凌河区');
INSERT INTO `district` VALUES ('210711', '辽宁省锦州市太和区');
INSERT INTO `district` VALUES ('210726', '辽宁省锦州市黑山县');
INSERT INTO `district` VALUES ('210727', '辽宁省锦州市义县');
INSERT INTO `district` VALUES ('210781', '辽宁省锦州市凌海市');
INSERT INTO `district` VALUES ('210782', '辽宁省锦州市北宁市');
INSERT INTO `district` VALUES ('210800', '辽宁省营口市');
INSERT INTO `district` VALUES ('210801', '辽宁省营口市市辖区');
INSERT INTO `district` VALUES ('210802', '辽宁省营口市站前区');
INSERT INTO `district` VALUES ('210803', '辽宁省营口市西市区');
INSERT INTO `district` VALUES ('210804', '辽宁省营口市鲅鱼圈区');
INSERT INTO `district` VALUES ('210811', '辽宁省营口市老边区');
INSERT INTO `district` VALUES ('210881', '辽宁省营口市盖州市');
INSERT INTO `district` VALUES ('210882', '辽宁省营口市大石桥市');
INSERT INTO `district` VALUES ('210900', '辽宁省阜新市');
INSERT INTO `district` VALUES ('210901', '辽宁省阜新市市辖区');
INSERT INTO `district` VALUES ('210902', '辽宁省阜新市海州区');
INSERT INTO `district` VALUES ('210903', '辽宁省阜新市新邱区');
INSERT INTO `district` VALUES ('210904', '辽宁省阜新市太平区');
INSERT INTO `district` VALUES ('210905', '辽宁省阜新市清河门区');
INSERT INTO `district` VALUES ('210911', '辽宁省阜新市细河区');
INSERT INTO `district` VALUES ('210921', '辽宁省阜新市阜新蒙古族自治县');
INSERT INTO `district` VALUES ('210922', '辽宁省阜新市彰武县');
INSERT INTO `district` VALUES ('211000', '辽宁省辽阳市');
INSERT INTO `district` VALUES ('211001', '辽宁省辽阳市市辖区');
INSERT INTO `district` VALUES ('211002', '辽宁省辽阳市白塔区');
INSERT INTO `district` VALUES ('211003', '辽宁省辽阳市文圣区');
INSERT INTO `district` VALUES ('211004', '辽宁省辽阳市宏伟区');
INSERT INTO `district` VALUES ('211005', '辽宁省辽阳市弓长岭区');
INSERT INTO `district` VALUES ('211011', '辽宁省辽阳市太子河区');
INSERT INTO `district` VALUES ('211021', '辽宁省辽阳市辽阳县');
INSERT INTO `district` VALUES ('211081', '辽宁省辽阳市灯塔市');
INSERT INTO `district` VALUES ('211100', '辽宁省盘锦市');
INSERT INTO `district` VALUES ('211101', '辽宁省盘锦市市辖区');
INSERT INTO `district` VALUES ('211102', '辽宁省盘锦市双台子区');
INSERT INTO `district` VALUES ('211103', '辽宁省盘锦市兴隆台区');
INSERT INTO `district` VALUES ('211121', '辽宁省盘锦市大洼县');
INSERT INTO `district` VALUES ('211122', '辽宁省盘锦市盘山县');
INSERT INTO `district` VALUES ('211200', '辽宁省铁岭市');
INSERT INTO `district` VALUES ('211201', '辽宁省铁岭市市辖区');
INSERT INTO `district` VALUES ('211202', '辽宁省铁岭市银州区');
INSERT INTO `district` VALUES ('211204', '辽宁省铁岭市清河区');
INSERT INTO `district` VALUES ('211221', '辽宁省铁岭市铁岭县');
INSERT INTO `district` VALUES ('211223', '辽宁省铁岭市西丰县');
INSERT INTO `district` VALUES ('211224', '辽宁省铁岭市昌图县');
INSERT INTO `district` VALUES ('211281', '辽宁省铁岭市铁法市');
INSERT INTO `district` VALUES ('211282', '辽宁省铁岭市开原市');
INSERT INTO `district` VALUES ('211300', '辽宁省朝阳市');
INSERT INTO `district` VALUES ('211301', '辽宁省朝阳市市辖区');
INSERT INTO `district` VALUES ('211302', '辽宁省朝阳市双塔区');
INSERT INTO `district` VALUES ('211303', '辽宁省朝阳市龙城区');
INSERT INTO `district` VALUES ('211321', '辽宁省朝阳市朝阳县');
INSERT INTO `district` VALUES ('211322', '辽宁省朝阳市建平县');
INSERT INTO `district` VALUES ('211324', '辽宁省朝阳市喀喇沁左翼蒙古族自治县');
INSERT INTO `district` VALUES ('211381', '辽宁省朝阳市北票市');
INSERT INTO `district` VALUES ('211382', '辽宁省朝阳市凌源市');
INSERT INTO `district` VALUES ('211400', '辽宁省葫芦岛市');
INSERT INTO `district` VALUES ('211401', '辽宁省葫芦岛市市辖区');
INSERT INTO `district` VALUES ('211402', '辽宁省葫芦岛市连山区');
INSERT INTO `district` VALUES ('211403', '辽宁省葫芦岛市龙港区');
INSERT INTO `district` VALUES ('211404', '辽宁省葫芦岛市南票区');
INSERT INTO `district` VALUES ('211421', '辽宁省葫芦岛市绥中县');
INSERT INTO `district` VALUES ('211422', '辽宁省葫芦岛市建昌县');
INSERT INTO `district` VALUES ('211481', '辽宁省葫芦岛市兴城市');
INSERT INTO `district` VALUES ('220000', '吉林省');
INSERT INTO `district` VALUES ('220100', '吉林省长春市');
INSERT INTO `district` VALUES ('220101', '吉林省长春市市辖区');
INSERT INTO `district` VALUES ('220102', '吉林省长春市南关区');
INSERT INTO `district` VALUES ('220103', '吉林省长春市宽城区');
INSERT INTO `district` VALUES ('220104', '吉林省长春市朝阳区');
INSERT INTO `district` VALUES ('220105', '吉林省长春市二道区');
INSERT INTO `district` VALUES ('220106', '吉林省长春市绿园区');
INSERT INTO `district` VALUES ('220112', '吉林省长春市双阳区');
INSERT INTO `district` VALUES ('220122', '吉林省长春市农安县');
INSERT INTO `district` VALUES ('220181', '吉林省长春市九台市');
INSERT INTO `district` VALUES ('220182', '吉林省长春市榆树市');
INSERT INTO `district` VALUES ('220183', '吉林省长春市德惠市');
INSERT INTO `district` VALUES ('220200', '吉林省吉林市');
INSERT INTO `district` VALUES ('220201', '吉林省吉林市市辖区');
INSERT INTO `district` VALUES ('220202', '吉林省吉林市昌邑区');
INSERT INTO `district` VALUES ('220203', '吉林省吉林市龙潭区');
INSERT INTO `district` VALUES ('220204', '吉林省吉林市船营区');
INSERT INTO `district` VALUES ('220211', '吉林省吉林市丰满区');
INSERT INTO `district` VALUES ('220221', '吉林省吉林市永吉县');
INSERT INTO `district` VALUES ('220281', '吉林省吉林市蛟河市');
INSERT INTO `district` VALUES ('220282', '吉林省吉林市桦甸市');
INSERT INTO `district` VALUES ('220283', '吉林省吉林市舒兰市');
INSERT INTO `district` VALUES ('220284', '吉林省吉林市磐石市');
INSERT INTO `district` VALUES ('220300', '吉林省四平市');
INSERT INTO `district` VALUES ('220301', '吉林省四平市市辖区');
INSERT INTO `district` VALUES ('220302', '吉林省四平市铁西区');
INSERT INTO `district` VALUES ('220303', '吉林省四平市铁东区');
INSERT INTO `district` VALUES ('220322', '吉林省四平市梨树县');
INSERT INTO `district` VALUES ('220323', '吉林省四平市伊通满族自治县');
INSERT INTO `district` VALUES ('220381', '吉林省四平市公主岭市');
INSERT INTO `district` VALUES ('220382', '吉林省四平市双辽市');
INSERT INTO `district` VALUES ('220400', '吉林省辽源市');
INSERT INTO `district` VALUES ('220401', '吉林省辽源市市辖区');
INSERT INTO `district` VALUES ('220402', '吉林省辽源市龙山区');
INSERT INTO `district` VALUES ('220403', '吉林省辽源市西安区');
INSERT INTO `district` VALUES ('220421', '吉林省辽源市东丰县');
INSERT INTO `district` VALUES ('220422', '吉林省辽源市东辽县');
INSERT INTO `district` VALUES ('220500', '吉林省通化市');
INSERT INTO `district` VALUES ('220501', '吉林省通化市市辖区');
INSERT INTO `district` VALUES ('220502', '吉林省通化市东昌区');
INSERT INTO `district` VALUES ('220503', '吉林省通化市二道江区');
INSERT INTO `district` VALUES ('220521', '吉林省通化市通化县');
INSERT INTO `district` VALUES ('220523', '吉林省通化市辉南县');
INSERT INTO `district` VALUES ('220524', '吉林省通化市柳河县');
INSERT INTO `district` VALUES ('220581', '吉林省通化市梅河口市');
INSERT INTO `district` VALUES ('220582', '吉林省通化市集安市');
INSERT INTO `district` VALUES ('220600', '吉林省白山市');
INSERT INTO `district` VALUES ('220601', '吉林省白山市市辖区');
INSERT INTO `district` VALUES ('220602', '吉林省白山市八道江区');
INSERT INTO `district` VALUES ('220621', '吉林省白山市抚松县');
INSERT INTO `district` VALUES ('220622', '吉林省白山市靖宇县');
INSERT INTO `district` VALUES ('220623', '吉林省白山市长白朝鲜族自治县');
INSERT INTO `district` VALUES ('220625', '吉林省白山市江源县');
INSERT INTO `district` VALUES ('220681', '吉林省白山市临江市');
INSERT INTO `district` VALUES ('220700', '吉林省松原市');
INSERT INTO `district` VALUES ('220701', '吉林省松原市市辖区');
INSERT INTO `district` VALUES ('220702', '吉林省松原市宁江区');
INSERT INTO `district` VALUES ('220721', '吉林省松原市前郭尔罗斯蒙古族自治县');
INSERT INTO `district` VALUES ('220722', '吉林省松原市长岭县');
INSERT INTO `district` VALUES ('220723', '吉林省松原市乾安县');
INSERT INTO `district` VALUES ('220724', '吉林省松原市扶余县');
INSERT INTO `district` VALUES ('220800', '吉林省白城市');
INSERT INTO `district` VALUES ('220801', '吉林省白城市市辖区');
INSERT INTO `district` VALUES ('220802', '吉林省白城市洮北区');
INSERT INTO `district` VALUES ('220821', '吉林省白城市镇赉县');
INSERT INTO `district` VALUES ('220822', '吉林省白城市通榆县');
INSERT INTO `district` VALUES ('220881', '吉林省白城市洮南市');
INSERT INTO `district` VALUES ('220882', '吉林省白城市大安市');
INSERT INTO `district` VALUES ('222400', '吉林省延边朝鲜族自治州');
INSERT INTO `district` VALUES ('222401', '吉林省延边朝鲜族自治州延吉市');
INSERT INTO `district` VALUES ('222402', '吉林省延边朝鲜族自治州图们市');
INSERT INTO `district` VALUES ('222403', '吉林省延边朝鲜族自治州敦化市');
INSERT INTO `district` VALUES ('222404', '吉林省延边朝鲜族自治州珲春市');
INSERT INTO `district` VALUES ('222405', '吉林省延边朝鲜族自治州龙井市');
INSERT INTO `district` VALUES ('222406', '吉林省延边朝鲜族自治州和龙市');
INSERT INTO `district` VALUES ('222424', '吉林省延边朝鲜族自治州汪清县');
INSERT INTO `district` VALUES ('222426', '吉林省延边朝鲜族自治州安图县');
INSERT INTO `district` VALUES ('230000', '黑龙江省');
INSERT INTO `district` VALUES ('230100', '黑龙江省哈尔滨市');
INSERT INTO `district` VALUES ('230101', '黑龙江省哈尔滨市市辖区');
INSERT INTO `district` VALUES ('230102', '黑龙江省哈尔滨市道里区');
INSERT INTO `district` VALUES ('230103', '黑龙江省哈尔滨市南岗区');
INSERT INTO `district` VALUES ('230104', '黑龙江省哈尔滨市道外区');
INSERT INTO `district` VALUES ('230105', '黑龙江省哈尔滨市太平区');
INSERT INTO `district` VALUES ('230106', '黑龙江省哈尔滨市香坊区');
INSERT INTO `district` VALUES ('230107', '黑龙江省哈尔滨市动力区');
INSERT INTO `district` VALUES ('230108', '黑龙江省哈尔滨市平房区');
INSERT INTO `district` VALUES ('230121', '黑龙江省哈尔滨市呼兰县');
INSERT INTO `district` VALUES ('230123', '黑龙江省哈尔滨市依兰县');
INSERT INTO `district` VALUES ('230124', '黑龙江省哈尔滨市方正县');
INSERT INTO `district` VALUES ('230125', '黑龙江省哈尔滨市宾县');
INSERT INTO `district` VALUES ('230126', '黑龙江省哈尔滨市巴彦县');
INSERT INTO `district` VALUES ('230127', '黑龙江省哈尔滨市木兰县');
INSERT INTO `district` VALUES ('230128', '黑龙江省哈尔滨市通河县');
INSERT INTO `district` VALUES ('230129', '黑龙江省哈尔滨市延寿县');
INSERT INTO `district` VALUES ('230181', '黑龙江省哈尔滨市阿城市');
INSERT INTO `district` VALUES ('230182', '黑龙江省哈尔滨市双城市');
INSERT INTO `district` VALUES ('230183', '黑龙江省哈尔滨市尚志市');
INSERT INTO `district` VALUES ('230184', '黑龙江省哈尔滨市五常市');
INSERT INTO `district` VALUES ('230200', '黑龙江省齐齐哈尔市');
INSERT INTO `district` VALUES ('230201', '黑龙江省齐齐哈尔市市辖区');
INSERT INTO `district` VALUES ('230202', '黑龙江省齐齐哈尔市龙沙区');
INSERT INTO `district` VALUES ('230203', '黑龙江省齐齐哈尔市建华区');
INSERT INTO `district` VALUES ('230204', '黑龙江省齐齐哈尔市铁锋区');
INSERT INTO `district` VALUES ('230205', '黑龙江省齐齐哈尔市昂昂溪区');
INSERT INTO `district` VALUES ('230206', '黑龙江省齐齐哈尔市富拉尔基区');
INSERT INTO `district` VALUES ('230207', '黑龙江省齐齐哈尔市碾子山区');
INSERT INTO `district` VALUES ('230208', '黑龙江省齐齐哈尔市梅里斯达斡尔族区');
INSERT INTO `district` VALUES ('230221', '黑龙江省齐齐哈尔市龙江县');
INSERT INTO `district` VALUES ('230223', '黑龙江省齐齐哈尔市依安县');
INSERT INTO `district` VALUES ('230224', '黑龙江省齐齐哈尔市泰来县');
INSERT INTO `district` VALUES ('230225', '黑龙江省齐齐哈尔市甘南县');
INSERT INTO `district` VALUES ('230227', '黑龙江省齐齐哈尔市富裕县');
INSERT INTO `district` VALUES ('230229', '黑龙江省齐齐哈尔市克山县');
INSERT INTO `district` VALUES ('230230', '黑龙江省齐齐哈尔市克东县');
INSERT INTO `district` VALUES ('230231', '黑龙江省齐齐哈尔市拜泉县');
INSERT INTO `district` VALUES ('230281', '黑龙江省齐齐哈尔市讷河市');
INSERT INTO `district` VALUES ('230300', '黑龙江省鸡西市');
INSERT INTO `district` VALUES ('230301', '黑龙江省鸡西市市辖区');
INSERT INTO `district` VALUES ('230302', '黑龙江省鸡西市鸡冠区');
INSERT INTO `district` VALUES ('230303', '黑龙江省鸡西市恒山区');
INSERT INTO `district` VALUES ('230304', '黑龙江省鸡西市滴道区');
INSERT INTO `district` VALUES ('230305', '黑龙江省鸡西市梨树区');
INSERT INTO `district` VALUES ('230306', '黑龙江省鸡西市城子河区');
INSERT INTO `district` VALUES ('230307', '黑龙江省鸡西市麻山区');
INSERT INTO `district` VALUES ('230321', '黑龙江省鸡西市鸡东县');
INSERT INTO `district` VALUES ('230381', '黑龙江省鸡西市虎林市');
INSERT INTO `district` VALUES ('230382', '黑龙江省鸡西市密山市');
INSERT INTO `district` VALUES ('230400', '黑龙江省鹤岗市');
INSERT INTO `district` VALUES ('230401', '黑龙江省鹤岗市市辖区');
INSERT INTO `district` VALUES ('230402', '黑龙江省鹤岗市向阳区');
INSERT INTO `district` VALUES ('230403', '黑龙江省鹤岗市工农区');
INSERT INTO `district` VALUES ('230404', '黑龙江省鹤岗市南山区');
INSERT INTO `district` VALUES ('230405', '黑龙江省鹤岗市兴安区');
INSERT INTO `district` VALUES ('230406', '黑龙江省鹤岗市东山区');
INSERT INTO `district` VALUES ('230407', '黑龙江省鹤岗市兴山区');
INSERT INTO `district` VALUES ('230421', '黑龙江省鹤岗市萝北县');
INSERT INTO `district` VALUES ('230422', '黑龙江省鹤岗市绥滨县');
INSERT INTO `district` VALUES ('230500', '黑龙江省双鸭山市');
INSERT INTO `district` VALUES ('230501', '黑龙江省双鸭山市市辖区');
INSERT INTO `district` VALUES ('230502', '黑龙江省双鸭山市尖山区');
INSERT INTO `district` VALUES ('230503', '黑龙江省双鸭山市岭东区');
INSERT INTO `district` VALUES ('230505', '黑龙江省双鸭山市四方台区');
INSERT INTO `district` VALUES ('230506', '黑龙江省双鸭山市宝山区');
INSERT INTO `district` VALUES ('230521', '黑龙江省双鸭山市集贤县');
INSERT INTO `district` VALUES ('230522', '黑龙江省双鸭山市友谊县');
INSERT INTO `district` VALUES ('230523', '黑龙江省双鸭山市宝清县');
INSERT INTO `district` VALUES ('230524', '黑龙江省双鸭山市饶河县');
INSERT INTO `district` VALUES ('230600', '黑龙江省大庆市');
INSERT INTO `district` VALUES ('230601', '黑龙江省大庆市市辖区');
INSERT INTO `district` VALUES ('230602', '黑龙江省大庆市萨尔图区');
INSERT INTO `district` VALUES ('230603', '黑龙江省大庆市龙凤区');
INSERT INTO `district` VALUES ('230604', '黑龙江省大庆市让胡路区');
INSERT INTO `district` VALUES ('230605', '黑龙江省大庆市红岗区');
INSERT INTO `district` VALUES ('230606', '黑龙江省大庆市大同区');
INSERT INTO `district` VALUES ('230621', '黑龙江省大庆市肇州县');
INSERT INTO `district` VALUES ('230622', '黑龙江省大庆市肇源县');
INSERT INTO `district` VALUES ('230623', '黑龙江省大庆市林甸县');
INSERT INTO `district` VALUES ('230624', '黑龙江省大庆市杜尔伯特蒙古族自治县');
INSERT INTO `district` VALUES ('230700', '黑龙江省伊春市');
INSERT INTO `district` VALUES ('230701', '黑龙江省伊春市市辖区');
INSERT INTO `district` VALUES ('230702', '黑龙江省伊春市伊春区');
INSERT INTO `district` VALUES ('230703', '黑龙江省伊春市南岔区');
INSERT INTO `district` VALUES ('230704', '黑龙江省伊春市友好区');
INSERT INTO `district` VALUES ('230705', '黑龙江省伊春市西林区');
INSERT INTO `district` VALUES ('230706', '黑龙江省伊春市翠峦区');
INSERT INTO `district` VALUES ('230707', '黑龙江省伊春市新青区');
INSERT INTO `district` VALUES ('230708', '黑龙江省伊春市美溪区');
INSERT INTO `district` VALUES ('230709', '黑龙江省伊春市金山屯区');
INSERT INTO `district` VALUES ('230710', '黑龙江省伊春市五营区');
INSERT INTO `district` VALUES ('230711', '黑龙江省伊春市乌马河区');
INSERT INTO `district` VALUES ('230712', '黑龙江省伊春市汤旺河区');
INSERT INTO `district` VALUES ('230713', '黑龙江省伊春市带岭区');
INSERT INTO `district` VALUES ('230714', '黑龙江省伊春市乌伊岭区');
INSERT INTO `district` VALUES ('230715', '黑龙江省伊春市红星区');
INSERT INTO `district` VALUES ('230716', '黑龙江省伊春市上甘岭区');
INSERT INTO `district` VALUES ('230722', '黑龙江省伊春市嘉荫县');
INSERT INTO `district` VALUES ('230781', '黑龙江省伊春市铁力市');
INSERT INTO `district` VALUES ('230800', '黑龙江省佳木斯市');
INSERT INTO `district` VALUES ('230801', '黑龙江省佳木斯市市辖区');
INSERT INTO `district` VALUES ('230802', '黑龙江省佳木斯市永红区');
INSERT INTO `district` VALUES ('230803', '黑龙江省佳木斯市向阳区');
INSERT INTO `district` VALUES ('230804', '黑龙江省佳木斯市前进区');
INSERT INTO `district` VALUES ('230805', '黑龙江省佳木斯市东风区');
INSERT INTO `district` VALUES ('230811', '黑龙江省佳木斯市郊区');
INSERT INTO `district` VALUES ('230822', '黑龙江省佳木斯市桦南县');
INSERT INTO `district` VALUES ('230826', '黑龙江省佳木斯市桦川县');
INSERT INTO `district` VALUES ('230828', '黑龙江省佳木斯市汤原县');
INSERT INTO `district` VALUES ('230833', '黑龙江省佳木斯市抚远县');
INSERT INTO `district` VALUES ('230881', '黑龙江省佳木斯市同江市');
INSERT INTO `district` VALUES ('230882', '黑龙江省佳木斯市富锦市');
INSERT INTO `district` VALUES ('230900', '黑龙江省七台河市');
INSERT INTO `district` VALUES ('230901', '黑龙江省七台河市市辖区');
INSERT INTO `district` VALUES ('230902', '黑龙江省七台河市新兴区');
INSERT INTO `district` VALUES ('230903', '黑龙江省七台河市桃山区');
INSERT INTO `district` VALUES ('230904', '黑龙江省七台河市茄子河区');
INSERT INTO `district` VALUES ('230921', '黑龙江省七台河市勃利县');
INSERT INTO `district` VALUES ('231000', '黑龙江省牡丹江市');
INSERT INTO `district` VALUES ('231001', '黑龙江省牡丹江市市辖区');
INSERT INTO `district` VALUES ('231002', '黑龙江省牡丹江市东安区');
INSERT INTO `district` VALUES ('231003', '黑龙江省牡丹江市阳明区');
INSERT INTO `district` VALUES ('231004', '黑龙江省牡丹江市爱民区');
INSERT INTO `district` VALUES ('231005', '黑龙江省牡丹江市西安区');
INSERT INTO `district` VALUES ('231024', '黑龙江省牡丹江市东宁县');
INSERT INTO `district` VALUES ('231025', '黑龙江省牡丹江市林口县');
INSERT INTO `district` VALUES ('231081', '黑龙江省牡丹江市绥芬河市');
INSERT INTO `district` VALUES ('231083', '黑龙江省牡丹江市海林市');
INSERT INTO `district` VALUES ('231084', '黑龙江省牡丹江市宁安市');
INSERT INTO `district` VALUES ('231085', '黑龙江省牡丹江市穆棱市');
INSERT INTO `district` VALUES ('231100', '黑龙江省黑河市');
INSERT INTO `district` VALUES ('231101', '黑龙江省黑河市市辖区');
INSERT INTO `district` VALUES ('231102', '黑龙江省黑河市爱辉区');
INSERT INTO `district` VALUES ('231121', '黑龙江省黑河市嫩江县');
INSERT INTO `district` VALUES ('231123', '黑龙江省黑河市逊克县');
INSERT INTO `district` VALUES ('231124', '黑龙江省黑河市孙吴县');
INSERT INTO `district` VALUES ('231181', '黑龙江省黑河市北安市');
INSERT INTO `district` VALUES ('231182', '黑龙江省黑河市五大连池市');
INSERT INTO `district` VALUES ('231200', '黑龙江省绥化市');
INSERT INTO `district` VALUES ('231201', '黑龙江省绥化市市辖区');
INSERT INTO `district` VALUES ('231202', '黑龙江省绥化市北林区');
INSERT INTO `district` VALUES ('231221', '黑龙江省绥化市望奎县');
INSERT INTO `district` VALUES ('231222', '黑龙江省绥化市兰西县');
INSERT INTO `district` VALUES ('231223', '黑龙江省绥化市青冈县');
INSERT INTO `district` VALUES ('231224', '黑龙江省绥化市庆安县');
INSERT INTO `district` VALUES ('231225', '黑龙江省绥化市明水县');
INSERT INTO `district` VALUES ('231226', '黑龙江省绥化市绥棱县');
INSERT INTO `district` VALUES ('231281', '黑龙江省绥化市安达市');
INSERT INTO `district` VALUES ('231282', '黑龙江省绥化市肇东市');
INSERT INTO `district` VALUES ('231283', '黑龙江省绥化市海伦市');
INSERT INTO `district` VALUES ('232300', '黑龙江省绥化地区');
INSERT INTO `district` VALUES ('232301', '黑龙江省绥化地区绥化市');
INSERT INTO `district` VALUES ('232302', '黑龙江省绥化地区安达市');
INSERT INTO `district` VALUES ('232303', '黑龙江省绥化地区肇东市');
INSERT INTO `district` VALUES ('232304', '黑龙江省绥化地区海伦市');
INSERT INTO `district` VALUES ('232324', '黑龙江省绥化地区望奎县');
INSERT INTO `district` VALUES ('232325', '黑龙江省绥化地区兰西县');
INSERT INTO `district` VALUES ('232326', '黑龙江省绥化地区青冈县');
INSERT INTO `district` VALUES ('232330', '黑龙江省绥化地区庆安县');
INSERT INTO `district` VALUES ('232331', '黑龙江省绥化地区明水县');
INSERT INTO `district` VALUES ('232332', '黑龙江省绥化地区绥棱县');
INSERT INTO `district` VALUES ('232700', '黑龙江省大兴安岭地区');
INSERT INTO `district` VALUES ('232721', '黑龙江省大兴安岭地区呼玛县');
INSERT INTO `district` VALUES ('232722', '黑龙江省大兴安岭地区塔河县');
INSERT INTO `district` VALUES ('232723', '黑龙江省大兴安岭地区漠河县');
INSERT INTO `district` VALUES ('310000', '上海市');
INSERT INTO `district` VALUES ('310101', '上海市市辖区黄浦区');
INSERT INTO `district` VALUES ('310102', '上海市市辖区南市区');
INSERT INTO `district` VALUES ('310103', '上海市市辖区卢湾区');
INSERT INTO `district` VALUES ('310104', '上海市市辖区徐汇区');
INSERT INTO `district` VALUES ('310105', '上海市市辖区长宁区');
INSERT INTO `district` VALUES ('310106', '上海市市辖区静安区');
INSERT INTO `district` VALUES ('310107', '上海市市辖区普陀区');
INSERT INTO `district` VALUES ('310108', '上海市市辖区闸北区');
INSERT INTO `district` VALUES ('310109', '上海市市辖区虹口区');
INSERT INTO `district` VALUES ('310110', '上海市市辖区杨浦区');
INSERT INTO `district` VALUES ('310112', '上海市市辖区闵行区');
INSERT INTO `district` VALUES ('310113', '上海市市辖区宝山区');
INSERT INTO `district` VALUES ('310114', '上海市市辖区嘉定区');
INSERT INTO `district` VALUES ('310115', '上海市市辖区浦东新区');
INSERT INTO `district` VALUES ('310116', '上海市市辖区金山区');
INSERT INTO `district` VALUES ('310117', '上海市市辖区松江区');
INSERT INTO `district` VALUES ('310118', '上海市市辖区青浦区');
INSERT INTO `district` VALUES ('310119', '上海市市辖区南汇区');
INSERT INTO `district` VALUES ('310120', '上海市市辖区奉贤区');
INSERT INTO `district` VALUES ('320000', '江苏省');
INSERT INTO `district` VALUES ('320100', '江苏省南京市');
INSERT INTO `district` VALUES ('320101', '江苏省南京市市辖区');
INSERT INTO `district` VALUES ('320102', '江苏省南京市玄武区');
INSERT INTO `district` VALUES ('320103', '江苏省南京市白下区');
INSERT INTO `district` VALUES ('320104', '江苏省南京市秦淮区');
INSERT INTO `district` VALUES ('320105', '江苏省南京市建邺区');
INSERT INTO `district` VALUES ('320106', '江苏省南京市鼓楼区');
INSERT INTO `district` VALUES ('320107', '江苏省南京市下关区');
INSERT INTO `district` VALUES ('320111', '江苏省南京市浦口区');
INSERT INTO `district` VALUES ('320112', '江苏省南京市大厂区');
INSERT INTO `district` VALUES ('320113', '江苏省南京市栖霞区');
INSERT INTO `district` VALUES ('320114', '江苏省南京市雨花台区');
INSERT INTO `district` VALUES ('320115', '江苏省南京市江宁区');
INSERT INTO `district` VALUES ('320116', '江苏省南京市六合区');
INSERT INTO `district` VALUES ('320121', '江苏省南京市江宁县');
INSERT INTO `district` VALUES ('320122', '江苏省南京市江浦县');
INSERT INTO `district` VALUES ('320123', '江苏省南京市六合县');
INSERT INTO `district` VALUES ('320124', '江苏省南京市溧水县');
INSERT INTO `district` VALUES ('320125', '江苏省南京市高淳县');
INSERT INTO `district` VALUES ('320200', '江苏省无锡市');
INSERT INTO `district` VALUES ('320201', '江苏省无锡市市辖区');
INSERT INTO `district` VALUES ('320202', '江苏省无锡市崇安区');
INSERT INTO `district` VALUES ('320203', '江苏省无锡市南长区');
INSERT INTO `district` VALUES ('320204', '江苏省无锡市北塘区');
INSERT INTO `district` VALUES ('320205', '江苏省无锡市锡山区');
INSERT INTO `district` VALUES ('320206', '江苏省无锡市惠山区');
INSERT INTO `district` VALUES ('320211', '江苏省无锡市郊区');
INSERT INTO `district` VALUES ('320212', '江苏省无锡市马山区');
INSERT INTO `district` VALUES ('320281', '江苏省无锡市江阴市');
INSERT INTO `district` VALUES ('320282', '江苏省无锡市宜兴市');
INSERT INTO `district` VALUES ('320283', '江苏省无锡市锡山市');
INSERT INTO `district` VALUES ('320300', '江苏省徐州市');
INSERT INTO `district` VALUES ('320301', '江苏省徐州市市辖区');
INSERT INTO `district` VALUES ('320302', '江苏省徐州市鼓楼区');
INSERT INTO `district` VALUES ('320303', '江苏省徐州市云龙区');
INSERT INTO `district` VALUES ('320304', '江苏省徐州市九里区');
INSERT INTO `district` VALUES ('320305', '江苏省徐州市贾汪区');
INSERT INTO `district` VALUES ('320311', '江苏省徐州市泉山区');
INSERT INTO `district` VALUES ('320321', '江苏省徐州市丰县');
INSERT INTO `district` VALUES ('320322', '江苏省徐州市沛县');
INSERT INTO `district` VALUES ('320323', '江苏省徐州市铜山县');
INSERT INTO `district` VALUES ('320324', '江苏省徐州市睢宁县');
INSERT INTO `district` VALUES ('320381', '江苏省徐州市新沂市');
INSERT INTO `district` VALUES ('320382', '江苏省徐州市邳州市');
INSERT INTO `district` VALUES ('320400', '江苏省常州市');
INSERT INTO `district` VALUES ('320401', '江苏省常州市市辖区');
INSERT INTO `district` VALUES ('320402', '江苏省常州市天宁区');
INSERT INTO `district` VALUES ('320404', '江苏省常州市钟楼区');
INSERT INTO `district` VALUES ('320405', '江苏省常州市戚墅堰区');
INSERT INTO `district` VALUES ('320411', '江苏省常州市郊区');
INSERT INTO `district` VALUES ('320412', '江苏省常州市武进区');
INSERT INTO `district` VALUES ('320481', '江苏省常州市溧阳市');
INSERT INTO `district` VALUES ('320482', '江苏省常州市金坛市');
INSERT INTO `district` VALUES ('320483', '江苏省常州市武进市');
INSERT INTO `district` VALUES ('320500', '江苏省苏州市');
INSERT INTO `district` VALUES ('320501', '江苏省苏州市市辖区');
INSERT INTO `district` VALUES ('320502', '江苏省苏州市沧浪区');
INSERT INTO `district` VALUES ('320503', '江苏省苏州市平江区');
INSERT INTO `district` VALUES ('320504', '江苏省苏州市金阊区');
INSERT INTO `district` VALUES ('320505', '江苏省苏州市虎丘区');
INSERT INTO `district` VALUES ('320506', '江苏省苏州市吴中区');
INSERT INTO `district` VALUES ('320507', '江苏省苏州市相城区');
INSERT INTO `district` VALUES ('320511', '江苏省苏州市郊区');
INSERT INTO `district` VALUES ('320581', '江苏省苏州市常熟市');
INSERT INTO `district` VALUES ('320582', '江苏省苏州市张家港市');
INSERT INTO `district` VALUES ('320583', '江苏省苏州市昆山市');
INSERT INTO `district` VALUES ('320584', '江苏省苏州市吴江市');
INSERT INTO `district` VALUES ('320585', '江苏省苏州市太仓市');
INSERT INTO `district` VALUES ('320586', '江苏省苏州市吴县市');
INSERT INTO `district` VALUES ('320600', '江苏省南通市');
INSERT INTO `district` VALUES ('320601', '江苏省南通市市辖区');
INSERT INTO `district` VALUES ('320602', '江苏省南通市崇川区');
INSERT INTO `district` VALUES ('320611', '江苏省南通市港闸区');
INSERT INTO `district` VALUES ('320621', '江苏省南通市海安县');
INSERT INTO `district` VALUES ('320623', '江苏省南通市如东县');
INSERT INTO `district` VALUES ('320681', '江苏省南通市启东市');
INSERT INTO `district` VALUES ('320682', '江苏省南通市如皋市');
INSERT INTO `district` VALUES ('320683', '江苏省南通市通州市');
INSERT INTO `district` VALUES ('320684', '江苏省南通市海门市');
INSERT INTO `district` VALUES ('320700', '江苏省连云港市');
INSERT INTO `district` VALUES ('320701', '江苏省连云港市市辖区');
INSERT INTO `district` VALUES ('320703', '江苏省连云港市连云区');
INSERT INTO `district` VALUES ('320704', '江苏省连云港市云台区');
INSERT INTO `district` VALUES ('320705', '江苏省连云港市新浦区');
INSERT INTO `district` VALUES ('320706', '江苏省连云港市海州区');
INSERT INTO `district` VALUES ('320721', '江苏省连云港市赣榆县');
INSERT INTO `district` VALUES ('320722', '江苏省连云港市东海县');
INSERT INTO `district` VALUES ('320723', '江苏省连云港市灌云县');
INSERT INTO `district` VALUES ('320724', '江苏省连云港市灌南县');
INSERT INTO `district` VALUES ('320800', '江苏省淮阴市');
INSERT INTO `district` VALUES ('320801', '江苏省淮阴市市辖区');
INSERT INTO `district` VALUES ('320802', '江苏省淮阴市清河区');
INSERT INTO `district` VALUES ('320803', '江苏省淮阴市楚州区');
INSERT INTO `district` VALUES ('320804', '江苏省淮阴市淮阴区');
INSERT INTO `district` VALUES ('320811', '江苏省淮阴市清浦区');
INSERT INTO `district` VALUES ('320821', '江苏省淮阴市淮阴县');
INSERT INTO `district` VALUES ('320826', '江苏省淮阴市涟水县');
INSERT INTO `district` VALUES ('320829', '江苏省淮阴市洪泽县');
INSERT INTO `district` VALUES ('320830', '江苏省淮阴市盱眙县');
INSERT INTO `district` VALUES ('320831', '江苏省淮阴市金湖县');
INSERT INTO `district` VALUES ('320882', '江苏省淮阴市淮安市');
INSERT INTO `district` VALUES ('320900', '江苏省盐城市');
INSERT INTO `district` VALUES ('320901', '江苏省盐城市市辖区');
INSERT INTO `district` VALUES ('320902', '江苏省盐城市城区');
INSERT INTO `district` VALUES ('320903', '江苏省盐城市盐都区');
INSERT INTO `district` VALUES ('320921', '江苏省盐城市响水县');
INSERT INTO `district` VALUES ('320922', '江苏省盐城市滨海县');
INSERT INTO `district` VALUES ('320923', '江苏省盐城市阜宁县');
INSERT INTO `district` VALUES ('320924', '江苏省盐城市射阳县');
INSERT INTO `district` VALUES ('320925', '江苏省盐城市建湖县');
INSERT INTO `district` VALUES ('320928', '江苏省盐城市盐都县');
INSERT INTO `district` VALUES ('320981', '江苏省盐城市东台市');
INSERT INTO `district` VALUES ('320982', '江苏省盐城市大丰市');
INSERT INTO `district` VALUES ('321000', '江苏省扬州市');
INSERT INTO `district` VALUES ('321001', '江苏省扬州市市辖区');
INSERT INTO `district` VALUES ('321002', '江苏省扬州市广陵区');
INSERT INTO `district` VALUES ('321003', '江苏省扬州市邗江区');
INSERT INTO `district` VALUES ('321011', '江苏省扬州市郊区');
INSERT INTO `district` VALUES ('321023', '江苏省扬州市宝应县');
INSERT INTO `district` VALUES ('321027', '江苏省扬州市邗江县');
INSERT INTO `district` VALUES ('321081', '江苏省扬州市仪征市');
INSERT INTO `district` VALUES ('321084', '江苏省扬州市高邮市');
INSERT INTO `district` VALUES ('321088', '江苏省扬州市江都市');
INSERT INTO `district` VALUES ('321100', '江苏省镇江市');
INSERT INTO `district` VALUES ('321101', '江苏省镇江市市辖区');
INSERT INTO `district` VALUES ('321102', '江苏省镇江市京口区');
INSERT INTO `district` VALUES ('321111', '江苏省镇江市润州区');
INSERT INTO `district` VALUES ('321112', '江苏省镇江市丹徒区');
INSERT INTO `district` VALUES ('321121', '江苏省镇江市丹徒县');
INSERT INTO `district` VALUES ('321181', '江苏省镇江市丹阳市');
INSERT INTO `district` VALUES ('321182', '江苏省镇江市扬中市');
INSERT INTO `district` VALUES ('321183', '江苏省镇江市句容市');
INSERT INTO `district` VALUES ('321200', '江苏省泰州市');
INSERT INTO `district` VALUES ('321201', '江苏省泰州市市辖区');
INSERT INTO `district` VALUES ('321202', '江苏省泰州市海陵区');
INSERT INTO `district` VALUES ('321203', '江苏省泰州市高港区');
INSERT INTO `district` VALUES ('321281', '江苏省泰州市兴化市');
INSERT INTO `district` VALUES ('321282', '江苏省泰州市靖江市');
INSERT INTO `district` VALUES ('321283', '江苏省泰州市泰兴市');
INSERT INTO `district` VALUES ('321284', '江苏省泰州市姜堰市');
INSERT INTO `district` VALUES ('321300', '江苏省宿迁市');
INSERT INTO `district` VALUES ('321301', '江苏省宿迁市市辖区');
INSERT INTO `district` VALUES ('321302', '江苏省宿迁市宿城区');
INSERT INTO `district` VALUES ('321321', '江苏省宿迁市宿豫县');
INSERT INTO `district` VALUES ('321322', '江苏省宿迁市沭阳县');
INSERT INTO `district` VALUES ('321323', '江苏省宿迁市泗阳县');
INSERT INTO `district` VALUES ('321324', '江苏省宿迁市泗洪县');
INSERT INTO `district` VALUES ('330000', '浙江省');
INSERT INTO `district` VALUES ('330100', '浙江省杭州市');
INSERT INTO `district` VALUES ('330101', '浙江省杭州市市辖区');
INSERT INTO `district` VALUES ('330102', '浙江省杭州市上城区');
INSERT INTO `district` VALUES ('330103', '浙江省杭州市下城区');
INSERT INTO `district` VALUES ('330104', '浙江省杭州市江干区');
INSERT INTO `district` VALUES ('330105', '浙江省杭州市拱墅区');
INSERT INTO `district` VALUES ('330106', '浙江省杭州市西湖区');
INSERT INTO `district` VALUES ('330108', '浙江省杭州市滨江区');
INSERT INTO `district` VALUES ('330109', '浙江省杭州市萧山区');
INSERT INTO `district` VALUES ('330110', '浙江省杭州市余杭区');
INSERT INTO `district` VALUES ('330122', '浙江省杭州市桐庐县');
INSERT INTO `district` VALUES ('330127', '浙江省杭州市淳安县');
INSERT INTO `district` VALUES ('330181', '浙江省杭州市萧山市');
INSERT INTO `district` VALUES ('330182', '浙江省杭州市建德市');
INSERT INTO `district` VALUES ('330183', '浙江省杭州市富阳市');
INSERT INTO `district` VALUES ('330184', '浙江省杭州市余杭市');
INSERT INTO `district` VALUES ('330185', '浙江省杭州市临安市');
INSERT INTO `district` VALUES ('330200', '浙江省宁波市');
INSERT INTO `district` VALUES ('330201', '浙江省宁波市市辖区');
INSERT INTO `district` VALUES ('330203', '浙江省宁波市海曙区');
INSERT INTO `district` VALUES ('330204', '浙江省宁波市江东区');
INSERT INTO `district` VALUES ('330205', '浙江省宁波市江北区');
INSERT INTO `district` VALUES ('330206', '浙江省宁波市北仑区');
INSERT INTO `district` VALUES ('330211', '浙江省宁波市镇海区');
INSERT INTO `district` VALUES ('330212', '浙江省宁波市鄞州区');
INSERT INTO `district` VALUES ('330225', '浙江省宁波市象山县');
INSERT INTO `district` VALUES ('330226', '浙江省宁波市宁海县');
INSERT INTO `district` VALUES ('330227', '浙江省宁波市鄞县');
INSERT INTO `district` VALUES ('330281', '浙江省宁波市余姚市');
INSERT INTO `district` VALUES ('330282', '浙江省宁波市慈溪市');
INSERT INTO `district` VALUES ('330283', '浙江省宁波市奉化市');
INSERT INTO `district` VALUES ('330300', '浙江省温州市');
INSERT INTO `district` VALUES ('330301', '浙江省温州市市辖区');
INSERT INTO `district` VALUES ('330302', '浙江省温州市鹿城区');
INSERT INTO `district` VALUES ('330303', '浙江省温州市龙湾区');
INSERT INTO `district` VALUES ('330304', '浙江省温州市瓯海区');
INSERT INTO `district` VALUES ('330322', '浙江省温州市洞头县');
INSERT INTO `district` VALUES ('330324', '浙江省温州市永嘉县');
INSERT INTO `district` VALUES ('330326', '浙江省温州市平阳县');
INSERT INTO `district` VALUES ('330327', '浙江省温州市苍南县');
INSERT INTO `district` VALUES ('330328', '浙江省温州市文成县');
INSERT INTO `district` VALUES ('330329', '浙江省温州市泰顺县');
INSERT INTO `district` VALUES ('330381', '浙江省温州市瑞安市');
INSERT INTO `district` VALUES ('330382', '浙江省温州市乐清市');
INSERT INTO `district` VALUES ('330400', '浙江省嘉兴市');
INSERT INTO `district` VALUES ('330401', '浙江省嘉兴市市辖区');
INSERT INTO `district` VALUES ('330402', '浙江省嘉兴市秀城区');
INSERT INTO `district` VALUES ('330411', '浙江省嘉兴市郊区');
INSERT INTO `district` VALUES ('330421', '浙江省嘉兴市嘉善县');
INSERT INTO `district` VALUES ('330424', '浙江省嘉兴市海盐县');
INSERT INTO `district` VALUES ('330481', '浙江省嘉兴市海宁市');
INSERT INTO `district` VALUES ('330482', '浙江省嘉兴市平湖市');
INSERT INTO `district` VALUES ('330483', '浙江省嘉兴市桐乡市');
INSERT INTO `district` VALUES ('330500', '浙江省湖州市');
INSERT INTO `district` VALUES ('330501', '浙江省湖州市市辖区');
INSERT INTO `district` VALUES ('330502', '浙江省湖州市吴兴区');
INSERT INTO `district` VALUES ('330503', '浙江省湖州市南浔区');
INSERT INTO `district` VALUES ('330521', '浙江省湖州市德清县');
INSERT INTO `district` VALUES ('330522', '浙江省湖州市长兴县');
INSERT INTO `district` VALUES ('330523', '浙江省湖州市安吉县');
INSERT INTO `district` VALUES ('330600', '浙江省绍兴市');
INSERT INTO `district` VALUES ('330601', '浙江省绍兴市市辖区');
INSERT INTO `district` VALUES ('330602', '浙江省绍兴市越城区');
INSERT INTO `district` VALUES ('330621', '浙江省绍兴市绍兴县');
INSERT INTO `district` VALUES ('330624', '浙江省绍兴市新昌县');
INSERT INTO `district` VALUES ('330681', '浙江省绍兴市诸暨市');
INSERT INTO `district` VALUES ('330682', '浙江省绍兴市上虞市');
INSERT INTO `district` VALUES ('330683', '浙江省绍兴市嵊州市');
INSERT INTO `district` VALUES ('330700', '浙江省金华市');
INSERT INTO `district` VALUES ('330701', '浙江省金华市市辖区');
INSERT INTO `district` VALUES ('330702', '浙江省金华市婺城区');
INSERT INTO `district` VALUES ('330703', '浙江省金华市金东区');
INSERT INTO `district` VALUES ('330721', '浙江省金华市金华县');
INSERT INTO `district` VALUES ('330723', '浙江省金华市武义县');
INSERT INTO `district` VALUES ('330726', '浙江省金华市浦江县');
INSERT INTO `district` VALUES ('330727', '浙江省金华市磐安县');
INSERT INTO `district` VALUES ('330781', '浙江省金华市兰溪市');
INSERT INTO `district` VALUES ('330782', '浙江省金华市义乌市');
INSERT INTO `district` VALUES ('330783', '浙江省金华市东阳市');
INSERT INTO `district` VALUES ('330784', '浙江省金华市永康市');
INSERT INTO `district` VALUES ('330800', '浙江省衢州市');
INSERT INTO `district` VALUES ('330801', '浙江省衢州市市辖区');
INSERT INTO `district` VALUES ('330802', '浙江省衢州市柯城区');
INSERT INTO `district` VALUES ('330803', '浙江省衢州市衢江区');
INSERT INTO `district` VALUES ('330821', '浙江省衢州市衢县');
INSERT INTO `district` VALUES ('330822', '浙江省衢州市常山县');
INSERT INTO `district` VALUES ('330824', '浙江省衢州市开化县');
INSERT INTO `district` VALUES ('330825', '浙江省衢州市龙游县');
INSERT INTO `district` VALUES ('330881', '浙江省衢州市江山市');
INSERT INTO `district` VALUES ('330900', '浙江省舟山市');
INSERT INTO `district` VALUES ('330901', '浙江省舟山市市辖区');
INSERT INTO `district` VALUES ('330902', '浙江省舟山市定海区');
INSERT INTO `district` VALUES ('330903', '浙江省舟山市普陀区');
INSERT INTO `district` VALUES ('330921', '浙江省舟山市岱山县');
INSERT INTO `district` VALUES ('330922', '浙江省舟山市嵊泗县');
INSERT INTO `district` VALUES ('331000', '浙江省台州市');
INSERT INTO `district` VALUES ('331001', '浙江省台州市市辖区');
INSERT INTO `district` VALUES ('331002', '浙江省台州市椒江区');
INSERT INTO `district` VALUES ('331003', '浙江省台州市黄岩区');
INSERT INTO `district` VALUES ('331004', '浙江省台州市路桥区');
INSERT INTO `district` VALUES ('331021', '浙江省台州市玉环县');
INSERT INTO `district` VALUES ('331022', '浙江省台州市三门县');
INSERT INTO `district` VALUES ('331023', '浙江省台州市天台县');
INSERT INTO `district` VALUES ('331024', '浙江省台州市仙居县');
INSERT INTO `district` VALUES ('331081', '浙江省台州市温岭市');
INSERT INTO `district` VALUES ('331082', '浙江省台州市临海市');
INSERT INTO `district` VALUES ('331100', '浙江省丽水市');
INSERT INTO `district` VALUES ('331101', '浙江省丽水市市辖区');
INSERT INTO `district` VALUES ('331102', '浙江省丽水市莲都区');
INSERT INTO `district` VALUES ('331121', '浙江省丽水市青田县');
INSERT INTO `district` VALUES ('331122', '浙江省丽水市缙云县');
INSERT INTO `district` VALUES ('331123', '浙江省丽水市遂昌县');
INSERT INTO `district` VALUES ('331124', '浙江省丽水市松阳县');
INSERT INTO `district` VALUES ('331125', '浙江省丽水市云和县');
INSERT INTO `district` VALUES ('331126', '浙江省丽水市庆元县');
INSERT INTO `district` VALUES ('331127', '浙江省丽水市景宁畲族自治县');
INSERT INTO `district` VALUES ('331181', '浙江省丽水市龙泉市');
INSERT INTO `district` VALUES ('332500', '浙江省丽水地区');
INSERT INTO `district` VALUES ('332501', '浙江省丽水地区丽水市');
INSERT INTO `district` VALUES ('332502', '浙江省丽水地区龙泉市');
INSERT INTO `district` VALUES ('332522', '浙江省丽水地区青田县');
INSERT INTO `district` VALUES ('332523', '浙江省丽水地区云和县');
INSERT INTO `district` VALUES ('332525', '浙江省丽水地区庆元县');
INSERT INTO `district` VALUES ('332526', '浙江省丽水地区缙云县');
INSERT INTO `district` VALUES ('332527', '浙江省丽水地区遂昌县');
INSERT INTO `district` VALUES ('332528', '浙江省丽水地区松阳县');
INSERT INTO `district` VALUES ('332529', '浙江省丽水地区景宁畲族自治县');
INSERT INTO `district` VALUES ('340000', '安徽省');
INSERT INTO `district` VALUES ('340100', '安徽省合肥市');
INSERT INTO `district` VALUES ('340101', '安徽省合肥市市辖区');
INSERT INTO `district` VALUES ('340102', '安徽省合肥市东市区');
INSERT INTO `district` VALUES ('340103', '安徽省合肥市中市区');
INSERT INTO `district` VALUES ('340104', '安徽省合肥市西市区');
INSERT INTO `district` VALUES ('340111', '安徽省合肥市郊区');
INSERT INTO `district` VALUES ('340121', '安徽省合肥市长丰县');
INSERT INTO `district` VALUES ('340122', '安徽省合肥市肥东县');
INSERT INTO `district` VALUES ('340123', '安徽省合肥市肥西县');
INSERT INTO `district` VALUES ('340200', '安徽省芜湖市');
INSERT INTO `district` VALUES ('340201', '安徽省芜湖市市辖区');
INSERT INTO `district` VALUES ('340202', '安徽省芜湖市镜湖区');
INSERT INTO `district` VALUES ('340203', '安徽省芜湖市马塘区');
INSERT INTO `district` VALUES ('340204', '安徽省芜湖市新芜区');
INSERT INTO `district` VALUES ('340207', '安徽省芜湖市鸠江区');
INSERT INTO `district` VALUES ('340221', '安徽省芜湖市芜湖县');
INSERT INTO `district` VALUES ('340222', '安徽省芜湖市繁昌县');
INSERT INTO `district` VALUES ('340223', '安徽省芜湖市南陵县');
INSERT INTO `district` VALUES ('340300', '安徽省蚌埠市');
INSERT INTO `district` VALUES ('340301', '安徽省蚌埠市市辖区');
INSERT INTO `district` VALUES ('340302', '安徽省蚌埠市东市区');
INSERT INTO `district` VALUES ('340303', '安徽省蚌埠市中市区');
INSERT INTO `district` VALUES ('340304', '安徽省蚌埠市西市区');
INSERT INTO `district` VALUES ('340311', '安徽省蚌埠市郊区');
INSERT INTO `district` VALUES ('340321', '安徽省蚌埠市怀远县');
INSERT INTO `district` VALUES ('340322', '安徽省蚌埠市五河县');
INSERT INTO `district` VALUES ('340323', '安徽省蚌埠市固镇县');
INSERT INTO `district` VALUES ('340400', '安徽省淮南市');
INSERT INTO `district` VALUES ('340401', '安徽省淮南市市辖区');
INSERT INTO `district` VALUES ('340402', '安徽省淮南市大通区');
INSERT INTO `district` VALUES ('340403', '安徽省淮南市田家庵区');
INSERT INTO `district` VALUES ('340404', '安徽省淮南市谢家集区');
INSERT INTO `district` VALUES ('340405', '安徽省淮南市八公山区');
INSERT INTO `district` VALUES ('340406', '安徽省淮南市潘集区');
INSERT INTO `district` VALUES ('340421', '安徽省淮南市凤台县');
INSERT INTO `district` VALUES ('340500', '安徽省马鞍山市');
INSERT INTO `district` VALUES ('340501', '安徽省马鞍山市市辖区');
INSERT INTO `district` VALUES ('340502', '安徽省马鞍山市金家庄区');
INSERT INTO `district` VALUES ('340503', '安徽省马鞍山市花山区');
INSERT INTO `district` VALUES ('340504', '安徽省马鞍山市雨山区');
INSERT INTO `district` VALUES ('340505', '安徽省马鞍山市向山区');
INSERT INTO `district` VALUES ('340521', '安徽省马鞍山市当涂县');
INSERT INTO `district` VALUES ('340600', '安徽省淮北市');
INSERT INTO `district` VALUES ('340601', '安徽省淮北市市辖区');
INSERT INTO `district` VALUES ('340602', '安徽省淮北市杜集区');
INSERT INTO `district` VALUES ('340603', '安徽省淮北市相山区');
INSERT INTO `district` VALUES ('340604', '安徽省淮北市烈山区');
INSERT INTO `district` VALUES ('340621', '安徽省淮北市濉溪县');
INSERT INTO `district` VALUES ('340700', '安徽省铜陵市');
INSERT INTO `district` VALUES ('340701', '安徽省铜陵市市辖区');
INSERT INTO `district` VALUES ('340702', '安徽省铜陵市铜官山区');
INSERT INTO `district` VALUES ('340703', '安徽省铜陵市狮子山区');
INSERT INTO `district` VALUES ('340711', '安徽省铜陵市郊区');
INSERT INTO `district` VALUES ('340721', '安徽省铜陵市铜陵县');
INSERT INTO `district` VALUES ('340800', '安徽省安庆市');
INSERT INTO `district` VALUES ('340801', '安徽省安庆市市辖区');
INSERT INTO `district` VALUES ('340802', '安徽省安庆市迎江区');
INSERT INTO `district` VALUES ('340803', '安徽省安庆市大观区');
INSERT INTO `district` VALUES ('340811', '安徽省安庆市郊区');
INSERT INTO `district` VALUES ('340822', '安徽省安庆市怀宁县');
INSERT INTO `district` VALUES ('340823', '安徽省安庆市枞阳县');
INSERT INTO `district` VALUES ('340824', '安徽省安庆市潜山县');
INSERT INTO `district` VALUES ('340825', '安徽省安庆市太湖县');
INSERT INTO `district` VALUES ('340826', '安徽省安庆市宿松县');
INSERT INTO `district` VALUES ('340827', '安徽省安庆市望江县');
INSERT INTO `district` VALUES ('340828', '安徽省安庆市岳西县');
INSERT INTO `district` VALUES ('340881', '安徽省安庆市桐城市');
INSERT INTO `district` VALUES ('341000', '安徽省黄山市');
INSERT INTO `district` VALUES ('341001', '安徽省黄山市市辖区');
INSERT INTO `district` VALUES ('341002', '安徽省黄山市屯溪区');
INSERT INTO `district` VALUES ('341003', '安徽省黄山市黄山区');
INSERT INTO `district` VALUES ('341004', '安徽省黄山市徽州区');
INSERT INTO `district` VALUES ('341021', '安徽省黄山市歙县');
INSERT INTO `district` VALUES ('341022', '安徽省黄山市休宁县');
INSERT INTO `district` VALUES ('341023', '安徽省黄山市黟县');
INSERT INTO `district` VALUES ('341024', '安徽省黄山市祁门县');
INSERT INTO `district` VALUES ('341100', '安徽省滁州市');
INSERT INTO `district` VALUES ('341101', '安徽省滁州市市辖区');
INSERT INTO `district` VALUES ('341102', '安徽省滁州市琅琊区');
INSERT INTO `district` VALUES ('341103', '安徽省滁州市南谯区');
INSERT INTO `district` VALUES ('341122', '安徽省滁州市来安县');
INSERT INTO `district` VALUES ('341124', '安徽省滁州市全椒县');
INSERT INTO `district` VALUES ('341125', '安徽省滁州市定远县');
INSERT INTO `district` VALUES ('341126', '安徽省滁州市凤阳县');
INSERT INTO `district` VALUES ('341181', '安徽省滁州市天长市');
INSERT INTO `district` VALUES ('341182', '安徽省滁州市明光市');
INSERT INTO `district` VALUES ('341200', '安徽省阜阳市');
INSERT INTO `district` VALUES ('341201', '安徽省阜阳市市辖区');
INSERT INTO `district` VALUES ('341202', '安徽省阜阳市颍州区');
INSERT INTO `district` VALUES ('341203', '安徽省阜阳市颍东区');
INSERT INTO `district` VALUES ('341204', '安徽省阜阳市颍泉区');
INSERT INTO `district` VALUES ('341221', '安徽省阜阳市临泉县');
INSERT INTO `district` VALUES ('341222', '安徽省阜阳市太和县');
INSERT INTO `district` VALUES ('341223', '安徽省阜阳市涡阳县');
INSERT INTO `district` VALUES ('341224', '安徽省阜阳市蒙城县');
INSERT INTO `district` VALUES ('341225', '安徽省阜阳市阜南县');
INSERT INTO `district` VALUES ('341226', '安徽省阜阳市颍上县');
INSERT INTO `district` VALUES ('341227', '安徽省阜阳市利辛县');
INSERT INTO `district` VALUES ('341281', '安徽省阜阳市亳州市');
INSERT INTO `district` VALUES ('341282', '安徽省阜阳市界首市');
INSERT INTO `district` VALUES ('341300', '安徽省宿州市');
INSERT INTO `district` VALUES ('341301', '安徽省宿州市市辖区');
INSERT INTO `district` VALUES ('341302', '安徽省宿州市墉桥区');
INSERT INTO `district` VALUES ('341321', '安徽省宿州市砀山县');
INSERT INTO `district` VALUES ('341322', '安徽省宿州市萧县');
INSERT INTO `district` VALUES ('341323', '安徽省宿州市灵璧县');
INSERT INTO `district` VALUES ('341324', '安徽省宿州市泗县');
INSERT INTO `district` VALUES ('341400', '安徽省巢湖市');
INSERT INTO `district` VALUES ('341401', '安徽省巢湖市市辖区');
INSERT INTO `district` VALUES ('341402', '安徽省巢湖市居巢区');
INSERT INTO `district` VALUES ('341421', '安徽省巢湖市庐江县');
INSERT INTO `district` VALUES ('341422', '安徽省巢湖市无为县');
INSERT INTO `district` VALUES ('341423', '安徽省巢湖市含山县');
INSERT INTO `district` VALUES ('341424', '安徽省巢湖市和县');
INSERT INTO `district` VALUES ('341500', '安徽省六安市');
INSERT INTO `district` VALUES ('341501', '安徽省六安市市辖区');
INSERT INTO `district` VALUES ('341502', '安徽省六安市金安区');
INSERT INTO `district` VALUES ('341503', '安徽省六安市裕安区');
INSERT INTO `district` VALUES ('341521', '安徽省六安市寿县');
INSERT INTO `district` VALUES ('341522', '安徽省六安市霍邱县');
INSERT INTO `district` VALUES ('341523', '安徽省六安市舒城县');
INSERT INTO `district` VALUES ('341524', '安徽省六安市金寨县');
INSERT INTO `district` VALUES ('341525', '安徽省六安市霍山县');
INSERT INTO `district` VALUES ('341600', '安徽省亳州市');
INSERT INTO `district` VALUES ('341601', '安徽省亳州市市辖区');
INSERT INTO `district` VALUES ('341602', '安徽省亳州市谯城区');
INSERT INTO `district` VALUES ('341621', '安徽省亳州市涡阳县');
INSERT INTO `district` VALUES ('341622', '安徽省亳州市蒙城县');
INSERT INTO `district` VALUES ('341623', '安徽省亳州市利辛县');
INSERT INTO `district` VALUES ('341700', '安徽省池州市');
INSERT INTO `district` VALUES ('341701', '安徽省池州市市辖区');
INSERT INTO `district` VALUES ('341702', '安徽省池州市贵池区');
INSERT INTO `district` VALUES ('341721', '安徽省池州市东至县');
INSERT INTO `district` VALUES ('341722', '安徽省池州市石台县');
INSERT INTO `district` VALUES ('341723', '安徽省池州市青阳县');
INSERT INTO `district` VALUES ('341800', '安徽省宣城市');
INSERT INTO `district` VALUES ('341801', '安徽省宣城市市辖区');
INSERT INTO `district` VALUES ('341802', '安徽省宣城市宣州区');
INSERT INTO `district` VALUES ('341821', '安徽省宣城市郎溪县');
INSERT INTO `district` VALUES ('341822', '安徽省宣城市广德县');
INSERT INTO `district` VALUES ('341823', '安徽省宣城市泾县');
INSERT INTO `district` VALUES ('341824', '安徽省宣城市绩溪县');
INSERT INTO `district` VALUES ('341825', '安徽省宣城市旌德县');
INSERT INTO `district` VALUES ('341881', '安徽省宣城市宁国市');
INSERT INTO `district` VALUES ('342400', '安徽省六安地区');
INSERT INTO `district` VALUES ('342401', '安徽省六安地区六安市');
INSERT INTO `district` VALUES ('342422', '安徽省六安地区寿县');
INSERT INTO `district` VALUES ('342423', '安徽省六安地区霍邱县');
INSERT INTO `district` VALUES ('342425', '安徽省六安地区舒城县');
INSERT INTO `district` VALUES ('342426', '安徽省六安地区金寨县');
INSERT INTO `district` VALUES ('342427', '安徽省六安地区霍山县');
INSERT INTO `district` VALUES ('342500', '安徽省宣城地区');
INSERT INTO `district` VALUES ('342501', '安徽省宣城地区宣州市');
INSERT INTO `district` VALUES ('342502', '安徽省宣城地区宁国市');
INSERT INTO `district` VALUES ('342522', '安徽省宣城地区郎溪县');
INSERT INTO `district` VALUES ('342523', '安徽省宣城地区广德县');
INSERT INTO `district` VALUES ('342529', '安徽省宣城地区泾县');
INSERT INTO `district` VALUES ('342530', '安徽省宣城地区旌德县');
INSERT INTO `district` VALUES ('342531', '安徽省宣城地区绩溪县');
INSERT INTO `district` VALUES ('342600', '安徽省巢湖地区');
INSERT INTO `district` VALUES ('342601', '安徽省巢湖地区巢湖市');
INSERT INTO `district` VALUES ('342622', '安徽省巢湖地区庐江县');
INSERT INTO `district` VALUES ('342623', '安徽省巢湖地区无为县');
INSERT INTO `district` VALUES ('342625', '安徽省巢湖地区含山县');
INSERT INTO `district` VALUES ('342626', '安徽省巢湖地区和县');
INSERT INTO `district` VALUES ('342900', '安徽省池州地区');
INSERT INTO `district` VALUES ('342901', '安徽省池州地区贵池市');
INSERT INTO `district` VALUES ('342921', '安徽省池州地区东至县');
INSERT INTO `district` VALUES ('342922', '安徽省池州地区石台县');
INSERT INTO `district` VALUES ('342923', '安徽省池州地区青阳县');
INSERT INTO `district` VALUES ('350000', '福建省');
INSERT INTO `district` VALUES ('350100', '福建省福州市');
INSERT INTO `district` VALUES ('350101', '福建省福州市市辖区');
INSERT INTO `district` VALUES ('350102', '福建省福州市鼓楼区');
INSERT INTO `district` VALUES ('350103', '福建省福州市台江区');
INSERT INTO `district` VALUES ('350104', '福建省福州市仓山区');
INSERT INTO `district` VALUES ('350105', '福建省福州市马尾区');
INSERT INTO `district` VALUES ('350111', '福建省福州市晋安区');
INSERT INTO `district` VALUES ('350121', '福建省福州市闽侯县');
INSERT INTO `district` VALUES ('350122', '福建省福州市连江县');
INSERT INTO `district` VALUES ('350123', '福建省福州市罗源县');
INSERT INTO `district` VALUES ('350124', '福建省福州市闽清县');
INSERT INTO `district` VALUES ('350125', '福建省福州市永泰县');
INSERT INTO `district` VALUES ('350128', '福建省福州市平潭县');
INSERT INTO `district` VALUES ('350181', '福建省福州市福清市');
INSERT INTO `district` VALUES ('350182', '福建省福州市长乐市');
INSERT INTO `district` VALUES ('350200', '福建省厦门市');
INSERT INTO `district` VALUES ('350201', '福建省厦门市市辖区');
INSERT INTO `district` VALUES ('350202', '福建省厦门市鼓浪屿区');
INSERT INTO `district` VALUES ('350203', '福建省厦门市思明区');
INSERT INTO `district` VALUES ('350204', '福建省厦门市开元区');
INSERT INTO `district` VALUES ('350205', '福建省厦门市杏林区');
INSERT INTO `district` VALUES ('350206', '福建省厦门市湖里区');
INSERT INTO `district` VALUES ('350211', '福建省厦门市集美区');
INSERT INTO `district` VALUES ('350212', '福建省厦门市同安区');
INSERT INTO `district` VALUES ('350213', '福建省厦门市翔安区');
INSERT INTO `district` VALUES ('350300', '福建省莆田市');
INSERT INTO `district` VALUES ('350301', '福建省莆田市市辖区');
INSERT INTO `district` VALUES ('350302', '福建省莆田市城厢区');
INSERT INTO `district` VALUES ('350303', '福建省莆田市涵江区');
INSERT INTO `district` VALUES ('350304', '福建省莆田市荔城区');
INSERT INTO `district` VALUES ('350305', '福建省莆田市秀屿区');
INSERT INTO `district` VALUES ('350321', '福建省莆田市莆田县');
INSERT INTO `district` VALUES ('350322', '福建省莆田市仙游县');
INSERT INTO `district` VALUES ('350400', '福建省三明市');
INSERT INTO `district` VALUES ('350401', '福建省三明市市辖区');
INSERT INTO `district` VALUES ('350402', '福建省三明市梅列区');
INSERT INTO `district` VALUES ('350403', '福建省三明市三元区');
INSERT INTO `district` VALUES ('350421', '福建省三明市明溪县');
INSERT INTO `district` VALUES ('350423', '福建省三明市清流县');
INSERT INTO `district` VALUES ('350424', '福建省三明市宁化县');
INSERT INTO `district` VALUES ('350425', '福建省三明市大田县');
INSERT INTO `district` VALUES ('350426', '福建省三明市尤溪县');
INSERT INTO `district` VALUES ('350427', '福建省三明市沙县');
INSERT INTO `district` VALUES ('350428', '福建省三明市将乐县');
INSERT INTO `district` VALUES ('350429', '福建省三明市泰宁县');
INSERT INTO `district` VALUES ('350430', '福建省三明市建宁县');
INSERT INTO `district` VALUES ('350481', '福建省三明市永安市');
INSERT INTO `district` VALUES ('350500', '福建省泉州市');
INSERT INTO `district` VALUES ('350501', '福建省泉州市市辖区');
INSERT INTO `district` VALUES ('350502', '福建省泉州市鲤城区');
INSERT INTO `district` VALUES ('350503', '福建省泉州市丰泽区');
INSERT INTO `district` VALUES ('350504', '福建省泉州市洛江区');
INSERT INTO `district` VALUES ('350505', '福建省泉州市泉港区');
INSERT INTO `district` VALUES ('350521', '福建省泉州市惠安县');
INSERT INTO `district` VALUES ('350524', '福建省泉州市安溪县');
INSERT INTO `district` VALUES ('350525', '福建省泉州市永春县');
INSERT INTO `district` VALUES ('350526', '福建省泉州市德化县');
INSERT INTO `district` VALUES ('350527', '福建省泉州市金门县');
INSERT INTO `district` VALUES ('350581', '福建省泉州市石狮市');
INSERT INTO `district` VALUES ('350582', '福建省泉州市晋江市');
INSERT INTO `district` VALUES ('350583', '福建省泉州市南安市');
INSERT INTO `district` VALUES ('350600', '福建省漳州市');
INSERT INTO `district` VALUES ('350601', '福建省漳州市市辖区');
INSERT INTO `district` VALUES ('350602', '福建省漳州市芗城区');
INSERT INTO `district` VALUES ('350603', '福建省漳州市龙文区');
INSERT INTO `district` VALUES ('350622', '福建省漳州市云霄县');
INSERT INTO `district` VALUES ('350623', '福建省漳州市漳浦县');
INSERT INTO `district` VALUES ('350624', '福建省漳州市诏安县');
INSERT INTO `district` VALUES ('350625', '福建省漳州市长泰县');
INSERT INTO `district` VALUES ('350626', '福建省漳州市东山县');
INSERT INTO `district` VALUES ('350627', '福建省漳州市南靖县');
INSERT INTO `district` VALUES ('350628', '福建省漳州市平和县');
INSERT INTO `district` VALUES ('350629', '福建省漳州市华安县');
INSERT INTO `district` VALUES ('350681', '福建省漳州市龙海市');
INSERT INTO `district` VALUES ('350700', '福建省南平市');
INSERT INTO `district` VALUES ('350701', '福建省南平市市辖区');
INSERT INTO `district` VALUES ('350702', '福建省南平市延平区');
INSERT INTO `district` VALUES ('350721', '福建省南平市顺昌县');
INSERT INTO `district` VALUES ('350722', '福建省南平市浦城县');
INSERT INTO `district` VALUES ('350723', '福建省南平市光泽县');
INSERT INTO `district` VALUES ('350724', '福建省南平市松溪县');
INSERT INTO `district` VALUES ('350725', '福建省南平市政和县');
INSERT INTO `district` VALUES ('350781', '福建省南平市邵武市');
INSERT INTO `district` VALUES ('350782', '福建省南平市武夷山市');
INSERT INTO `district` VALUES ('350783', '福建省南平市建瓯市');
INSERT INTO `district` VALUES ('350784', '福建省南平市建阳市');
INSERT INTO `district` VALUES ('350800', '福建省龙岩市');
INSERT INTO `district` VALUES ('350801', '福建省龙岩市市辖区');
INSERT INTO `district` VALUES ('350802', '福建省龙岩市新罗区');
INSERT INTO `district` VALUES ('350821', '福建省龙岩市长汀县');
INSERT INTO `district` VALUES ('350822', '福建省龙岩市永定县');
INSERT INTO `district` VALUES ('350823', '福建省龙岩市上杭县');
INSERT INTO `district` VALUES ('350824', '福建省龙岩市武平县');
INSERT INTO `district` VALUES ('350825', '福建省龙岩市连城县');
INSERT INTO `district` VALUES ('350881', '福建省龙岩市漳平市');
INSERT INTO `district` VALUES ('350900', '福建省宁德市');
INSERT INTO `district` VALUES ('350901', '福建省宁德市市辖区');
INSERT INTO `district` VALUES ('350902', '福建省宁德市蕉城区');
INSERT INTO `district` VALUES ('350921', '福建省宁德市霞浦县');
INSERT INTO `district` VALUES ('350922', '福建省宁德市古田县');
INSERT INTO `district` VALUES ('350923', '福建省宁德市屏南县');
INSERT INTO `district` VALUES ('350924', '福建省宁德市寿宁县');
INSERT INTO `district` VALUES ('350925', '福建省宁德市周宁县');
INSERT INTO `district` VALUES ('350926', '福建省宁德市柘荣县');
INSERT INTO `district` VALUES ('350981', '福建省宁德市福安市');
INSERT INTO `district` VALUES ('350982', '福建省宁德市福鼎市');
INSERT INTO `district` VALUES ('352200', '福建省宁德地区');
INSERT INTO `district` VALUES ('352201', '福建省宁德地区宁德市');
INSERT INTO `district` VALUES ('352202', '福建省宁德地区福安市');
INSERT INTO `district` VALUES ('352203', '福建省宁德地区福鼎市');
INSERT INTO `district` VALUES ('352225', '福建省宁德地区霞浦县');
INSERT INTO `district` VALUES ('352227', '福建省宁德地区古田县');
INSERT INTO `district` VALUES ('352228', '福建省宁德地区屏南县');
INSERT INTO `district` VALUES ('352229', '福建省宁德地区寿宁县');
INSERT INTO `district` VALUES ('352230', '福建省宁德地区周宁县');
INSERT INTO `district` VALUES ('352231', '福建省宁德地区柘荣县');
INSERT INTO `district` VALUES ('360000', '江西省');
INSERT INTO `district` VALUES ('360100', '江西省南昌市');
INSERT INTO `district` VALUES ('360101', '江西省南昌市市辖区');
INSERT INTO `district` VALUES ('360102', '江西省南昌市东湖区');
INSERT INTO `district` VALUES ('360103', '江西省南昌市西湖区');
INSERT INTO `district` VALUES ('360104', '江西省南昌市青云谱区');
INSERT INTO `district` VALUES ('360105', '江西省南昌市湾里区');
INSERT INTO `district` VALUES ('360111', '江西省南昌市郊区');
INSERT INTO `district` VALUES ('360121', '江西省南昌市南昌县');
INSERT INTO `district` VALUES ('360122', '江西省南昌市新建县');
INSERT INTO `district` VALUES ('360123', '江西省南昌市安义县');
INSERT INTO `district` VALUES ('360124', '江西省南昌市进贤县');
INSERT INTO `district` VALUES ('360200', '江西省景德镇市');
INSERT INTO `district` VALUES ('360201', '江西省景德镇市市辖区');
INSERT INTO `district` VALUES ('360202', '江西省景德镇市昌江区');
INSERT INTO `district` VALUES ('360203', '江西省景德镇市珠山区');
INSERT INTO `district` VALUES ('360222', '江西省景德镇市浮梁县');
INSERT INTO `district` VALUES ('360281', '江西省景德镇市乐平市');
INSERT INTO `district` VALUES ('360300', '江西省萍乡市');
INSERT INTO `district` VALUES ('360301', '江西省萍乡市市辖区');
INSERT INTO `district` VALUES ('360302', '江西省萍乡市安源区');
INSERT INTO `district` VALUES ('360313', '江西省萍乡市湘东区');
INSERT INTO `district` VALUES ('360321', '江西省萍乡市莲花县');
INSERT INTO `district` VALUES ('360322', '江西省萍乡市上栗县');
INSERT INTO `district` VALUES ('360323', '江西省萍乡市芦溪县');
INSERT INTO `district` VALUES ('360400', '江西省九江市');
INSERT INTO `district` VALUES ('360401', '江西省九江市市辖区');
INSERT INTO `district` VALUES ('360402', '江西省九江市庐山区');
INSERT INTO `district` VALUES ('360403', '江西省九江市浔阳区');
INSERT INTO `district` VALUES ('360421', '江西省九江市九江县');
INSERT INTO `district` VALUES ('360423', '江西省九江市武宁县');
INSERT INTO `district` VALUES ('360424', '江西省九江市修水县');
INSERT INTO `district` VALUES ('360425', '江西省九江市永修县');
INSERT INTO `district` VALUES ('360426', '江西省九江市德安县');
INSERT INTO `district` VALUES ('360427', '江西省九江市星子县');
INSERT INTO `district` VALUES ('360428', '江西省九江市都昌县');
INSERT INTO `district` VALUES ('360429', '江西省九江市湖口县');
INSERT INTO `district` VALUES ('360430', '江西省九江市彭泽县');
INSERT INTO `district` VALUES ('360481', '江西省九江市瑞昌市');
INSERT INTO `district` VALUES ('360500', '江西省新余市');
INSERT INTO `district` VALUES ('360501', '江西省新余市市辖区');
INSERT INTO `district` VALUES ('360502', '江西省新余市渝水区');
INSERT INTO `district` VALUES ('360521', '江西省新余市分宜县');
INSERT INTO `district` VALUES ('360600', '江西省鹰潭市');
INSERT INTO `district` VALUES ('360601', '江西省鹰潭市市辖区');
INSERT INTO `district` VALUES ('360602', '江西省鹰潭市月湖区');
INSERT INTO `district` VALUES ('360622', '江西省鹰潭市余江县');
INSERT INTO `district` VALUES ('360681', '江西省鹰潭市贵溪市');
INSERT INTO `district` VALUES ('360700', '江西省赣州市');
INSERT INTO `district` VALUES ('360701', '江西省赣州市市辖区');
INSERT INTO `district` VALUES ('360702', '江西省赣州市章贡区');
INSERT INTO `district` VALUES ('360721', '江西省赣州市赣县');
INSERT INTO `district` VALUES ('360722', '江西省赣州市信丰县');
INSERT INTO `district` VALUES ('360723', '江西省赣州市大余县');
INSERT INTO `district` VALUES ('360724', '江西省赣州市上犹县');
INSERT INTO `district` VALUES ('360725', '江西省赣州市崇义县');
INSERT INTO `district` VALUES ('360726', '江西省赣州市安远县');
INSERT INTO `district` VALUES ('360727', '江西省赣州市龙南县');
INSERT INTO `district` VALUES ('360728', '江西省赣州市定南县');
INSERT INTO `district` VALUES ('360729', '江西省赣州市全南县');
INSERT INTO `district` VALUES ('360730', '江西省赣州市宁都县');
INSERT INTO `district` VALUES ('360731', '江西省赣州市于都县');
INSERT INTO `district` VALUES ('360732', '江西省赣州市兴国县');
INSERT INTO `district` VALUES ('360733', '江西省赣州市会昌县');
INSERT INTO `district` VALUES ('360734', '江西省赣州市寻乌县');
INSERT INTO `district` VALUES ('360735', '江西省赣州市石城县');
INSERT INTO `district` VALUES ('360781', '江西省赣州市瑞金市');
INSERT INTO `district` VALUES ('360782', '江西省赣州市南康市');
INSERT INTO `district` VALUES ('360800', '江西省吉安市');
INSERT INTO `district` VALUES ('360801', '江西省吉安市市辖区');
INSERT INTO `district` VALUES ('360802', '江西省吉安市吉州区');
INSERT INTO `district` VALUES ('360803', '江西省吉安市青原区');
INSERT INTO `district` VALUES ('360821', '江西省吉安市吉安县');
INSERT INTO `district` VALUES ('360822', '江西省吉安市吉水县');
INSERT INTO `district` VALUES ('360823', '江西省吉安市峡江县');
INSERT INTO `district` VALUES ('360824', '江西省吉安市新干县');
INSERT INTO `district` VALUES ('360825', '江西省吉安市永丰县');
INSERT INTO `district` VALUES ('360826', '江西省吉安市泰和县');
INSERT INTO `district` VALUES ('360827', '江西省吉安市遂川县');
INSERT INTO `district` VALUES ('360828', '江西省吉安市万安县');
INSERT INTO `district` VALUES ('360829', '江西省吉安市安福县');
INSERT INTO `district` VALUES ('360830', '江西省吉安市永新县');
INSERT INTO `district` VALUES ('360881', '江西省吉安市井冈山市');
INSERT INTO `district` VALUES ('360900', '江西省宜春市');
INSERT INTO `district` VALUES ('360901', '江西省宜春市市辖区');
INSERT INTO `district` VALUES ('360902', '江西省宜春市袁州区');
INSERT INTO `district` VALUES ('360921', '江西省宜春市奉新县');
INSERT INTO `district` VALUES ('360922', '江西省宜春市万载县');
INSERT INTO `district` VALUES ('360923', '江西省宜春市上高县');
INSERT INTO `district` VALUES ('360924', '江西省宜春市宜丰县');
INSERT INTO `district` VALUES ('360925', '江西省宜春市靖安县');
INSERT INTO `district` VALUES ('360926', '江西省宜春市铜鼓县');
INSERT INTO `district` VALUES ('360981', '江西省宜春市丰城市');
INSERT INTO `district` VALUES ('360982', '江西省宜春市樟树市');
INSERT INTO `district` VALUES ('360983', '江西省宜春市高安市');
INSERT INTO `district` VALUES ('361000', '江西省抚州市');
INSERT INTO `district` VALUES ('361001', '江西省抚州市市辖区');
INSERT INTO `district` VALUES ('361002', '江西省抚州市临川区');
INSERT INTO `district` VALUES ('361021', '江西省抚州市南城县');
INSERT INTO `district` VALUES ('361022', '江西省抚州市黎川县');
INSERT INTO `district` VALUES ('361023', '江西省抚州市南丰县');
INSERT INTO `district` VALUES ('361024', '江西省抚州市崇仁县');
INSERT INTO `district` VALUES ('361025', '江西省抚州市乐安县');
INSERT INTO `district` VALUES ('361026', '江西省抚州市宜黄县');
INSERT INTO `district` VALUES ('361027', '江西省抚州市金溪县');
INSERT INTO `district` VALUES ('361028', '江西省抚州市资溪县');
INSERT INTO `district` VALUES ('361029', '江西省抚州市东乡县');
INSERT INTO `district` VALUES ('361030', '江西省抚州市广昌县');
INSERT INTO `district` VALUES ('361100', '江西省上饶市');
INSERT INTO `district` VALUES ('361101', '江西省上饶市市辖区');
INSERT INTO `district` VALUES ('361102', '江西省上饶市信州区');
INSERT INTO `district` VALUES ('361121', '江西省上饶市上饶县');
INSERT INTO `district` VALUES ('361122', '江西省上饶市广丰县');
INSERT INTO `district` VALUES ('361123', '江西省上饶市玉山县');
INSERT INTO `district` VALUES ('361124', '江西省上饶市铅山县');
INSERT INTO `district` VALUES ('361125', '江西省上饶市横峰县');
INSERT INTO `district` VALUES ('361126', '江西省上饶市弋阳县');
INSERT INTO `district` VALUES ('361127', '江西省上饶市余干县');
INSERT INTO `district` VALUES ('361128', '江西省上饶市鄱阳县');
INSERT INTO `district` VALUES ('361129', '江西省上饶市万年县');
INSERT INTO `district` VALUES ('361130', '江西省上饶市婺源县');
INSERT INTO `district` VALUES ('361181', '江西省上饶市德兴市');
INSERT INTO `district` VALUES ('362200', '江西省宜春地区');
INSERT INTO `district` VALUES ('362201', '江西省宜春地区宜春市');
INSERT INTO `district` VALUES ('362202', '江西省宜春地区丰城市');
INSERT INTO `district` VALUES ('362203', '江西省宜春地区樟树市');
INSERT INTO `district` VALUES ('362204', '江西省宜春地区高安市');
INSERT INTO `district` VALUES ('362226', '江西省宜春地区奉新县');
INSERT INTO `district` VALUES ('362227', '江西省宜春地区万载县');
INSERT INTO `district` VALUES ('362228', '江西省宜春地区上高县');
INSERT INTO `district` VALUES ('362229', '江西省宜春地区宜丰县');
INSERT INTO `district` VALUES ('362232', '江西省宜春地区靖安县');
INSERT INTO `district` VALUES ('362233', '江西省宜春地区铜鼓县');
INSERT INTO `district` VALUES ('362300', '江西省上饶地区');
INSERT INTO `district` VALUES ('362301', '江西省上饶地区上饶市');
INSERT INTO `district` VALUES ('362302', '江西省上饶地区德兴市');
INSERT INTO `district` VALUES ('362321', '江西省上饶地区上饶县');
INSERT INTO `district` VALUES ('362322', '江西省上饶地区广丰县');
INSERT INTO `district` VALUES ('362323', '江西省上饶地区玉山县');
INSERT INTO `district` VALUES ('362324', '江西省上饶地区铅山县');
INSERT INTO `district` VALUES ('362325', '江西省上饶地区横峰县');
INSERT INTO `district` VALUES ('362326', '江西省上饶地区弋阳县');
INSERT INTO `district` VALUES ('362329', '江西省上饶地区余干县');
INSERT INTO `district` VALUES ('362330', '江西省上饶地区波阳县');
INSERT INTO `district` VALUES ('362331', '江西省上饶地区万年县');
INSERT INTO `district` VALUES ('362334', '江西省上饶地区婺源县');
INSERT INTO `district` VALUES ('362400', '江西省吉安地区');
INSERT INTO `district` VALUES ('362401', '江西省吉安地区吉安市');
INSERT INTO `district` VALUES ('362402', '江西省吉安地区井冈山市');
INSERT INTO `district` VALUES ('362421', '江西省吉安地区吉安县');
INSERT INTO `district` VALUES ('362422', '江西省吉安地区吉水县');
INSERT INTO `district` VALUES ('362423', '江西省吉安地区峡江县');
INSERT INTO `district` VALUES ('362424', '江西省吉安地区新干县');
INSERT INTO `district` VALUES ('362425', '江西省吉安地区永丰县');
INSERT INTO `district` VALUES ('362426', '江西省吉安地区泰和县');
INSERT INTO `district` VALUES ('362427', '江西省吉安地区遂川县');
INSERT INTO `district` VALUES ('362428', '江西省吉安地区万安县');
INSERT INTO `district` VALUES ('362429', '江西省吉安地区安福县');
INSERT INTO `district` VALUES ('362430', '江西省吉安地区永新县');
INSERT INTO `district` VALUES ('362432', '江西省吉安地区宁冈县');
INSERT INTO `district` VALUES ('362500', '江西省抚州地区');
INSERT INTO `district` VALUES ('362501', '江西省抚州地区临川市');
INSERT INTO `district` VALUES ('362522', '江西省抚州地区南城县');
INSERT INTO `district` VALUES ('362523', '江西省抚州地区黎川县');
INSERT INTO `district` VALUES ('362524', '江西省抚州地区南丰县');
INSERT INTO `district` VALUES ('362525', '江西省抚州地区崇仁县');
INSERT INTO `district` VALUES ('362526', '江西省抚州地区乐安县');
INSERT INTO `district` VALUES ('362527', '江西省抚州地区宜黄县');
INSERT INTO `district` VALUES ('362528', '江西省抚州地区金溪县');
INSERT INTO `district` VALUES ('362529', '江西省抚州地区资溪县');
INSERT INTO `district` VALUES ('362531', '江西省抚州地区东乡县');
INSERT INTO `district` VALUES ('362532', '江西省抚州地区广昌县');
INSERT INTO `district` VALUES ('370000', '山东省');
INSERT INTO `district` VALUES ('370100', '山东省济南市');
INSERT INTO `district` VALUES ('370101', '山东省济南市市辖区');
INSERT INTO `district` VALUES ('370102', '山东省济南市历下区');
INSERT INTO `district` VALUES ('370103', '山东省济南市市中区');
INSERT INTO `district` VALUES ('370104', '山东省济南市槐荫区');
INSERT INTO `district` VALUES ('370105', '山东省济南市天桥区');
INSERT INTO `district` VALUES ('370112', '山东省济南市历城区');
INSERT INTO `district` VALUES ('370113', '山东省济南市长清区');
INSERT INTO `district` VALUES ('370123', '山东省济南市长清县');
INSERT INTO `district` VALUES ('370124', '山东省济南市平阴县');
INSERT INTO `district` VALUES ('370125', '山东省济南市济阳县');
INSERT INTO `district` VALUES ('370126', '山东省济南市商河县');
INSERT INTO `district` VALUES ('370181', '山东省济南市章丘市');
INSERT INTO `district` VALUES ('370200', '山东省青岛市');
INSERT INTO `district` VALUES ('370201', '山东省青岛市市辖区');
INSERT INTO `district` VALUES ('370202', '山东省青岛市市南区');
INSERT INTO `district` VALUES ('370203', '山东省青岛市市北区');
INSERT INTO `district` VALUES ('370205', '山东省青岛市四方区');
INSERT INTO `district` VALUES ('370211', '山东省青岛市黄岛区');
INSERT INTO `district` VALUES ('370212', '山东省青岛市崂山区');
INSERT INTO `district` VALUES ('370213', '山东省青岛市李沧区');
INSERT INTO `district` VALUES ('370214', '山东省青岛市城阳区');
INSERT INTO `district` VALUES ('370281', '山东省青岛市胶州市');
INSERT INTO `district` VALUES ('370282', '山东省青岛市即墨市');
INSERT INTO `district` VALUES ('370283', '山东省青岛市平度市');
INSERT INTO `district` VALUES ('370284', '山东省青岛市胶南市');
INSERT INTO `district` VALUES ('370285', '山东省青岛市莱西市');
INSERT INTO `district` VALUES ('370300', '山东省淄博市');
INSERT INTO `district` VALUES ('370301', '山东省淄博市市辖区');
INSERT INTO `district` VALUES ('370302', '山东省淄博市淄川区');
INSERT INTO `district` VALUES ('370303', '山东省淄博市张店区');
INSERT INTO `district` VALUES ('370304', '山东省淄博市博山区');
INSERT INTO `district` VALUES ('370305', '山东省淄博市临淄区');
INSERT INTO `district` VALUES ('370306', '山东省淄博市周村区');
INSERT INTO `district` VALUES ('370321', '山东省淄博市桓台县');
INSERT INTO `district` VALUES ('370322', '山东省淄博市高青县');
INSERT INTO `district` VALUES ('370323', '山东省淄博市沂源县');
INSERT INTO `district` VALUES ('370400', '山东省枣庄市');
INSERT INTO `district` VALUES ('370401', '山东省枣庄市市辖区');
INSERT INTO `district` VALUES ('370402', '山东省枣庄市市中区');
INSERT INTO `district` VALUES ('370403', '山东省枣庄市薛城区');
INSERT INTO `district` VALUES ('370404', '山东省枣庄市峄城区');
INSERT INTO `district` VALUES ('370405', '山东省枣庄市台儿庄区');
INSERT INTO `district` VALUES ('370406', '山东省枣庄市山亭区');
INSERT INTO `district` VALUES ('370481', '山东省枣庄市滕州市');
INSERT INTO `district` VALUES ('370500', '山东省东营市');
INSERT INTO `district` VALUES ('370501', '山东省东营市市辖区');
INSERT INTO `district` VALUES ('370502', '山东省东营市东营区');
INSERT INTO `district` VALUES ('370503', '山东省东营市河口区');
INSERT INTO `district` VALUES ('370521', '山东省东营市垦利县');
INSERT INTO `district` VALUES ('370522', '山东省东营市利津县');
INSERT INTO `district` VALUES ('370523', '山东省东营市广饶县');
INSERT INTO `district` VALUES ('370600', '山东省烟台市');
INSERT INTO `district` VALUES ('370601', '山东省烟台市市辖区');
INSERT INTO `district` VALUES ('370602', '山东省烟台市芝罘区');
INSERT INTO `district` VALUES ('370611', '山东省烟台市福山区');
INSERT INTO `district` VALUES ('370612', '山东省烟台市牟平区');
INSERT INTO `district` VALUES ('370613', '山东省烟台市莱山区');
INSERT INTO `district` VALUES ('370634', '山东省烟台市长岛县');
INSERT INTO `district` VALUES ('370681', '山东省烟台市龙口市');
INSERT INTO `district` VALUES ('370682', '山东省烟台市莱阳市');
INSERT INTO `district` VALUES ('370683', '山东省烟台市莱州市');
INSERT INTO `district` VALUES ('370684', '山东省烟台市蓬莱市');
INSERT INTO `district` VALUES ('370685', '山东省烟台市招远市');
INSERT INTO `district` VALUES ('370686', '山东省烟台市栖霞市');
INSERT INTO `district` VALUES ('370687', '山东省烟台市海阳市');
INSERT INTO `district` VALUES ('370700', '山东省潍坊市');
INSERT INTO `district` VALUES ('370701', '山东省潍坊市市辖区');
INSERT INTO `district` VALUES ('370702', '山东省潍坊市潍城区');
INSERT INTO `district` VALUES ('370703', '山东省潍坊市寒亭区');
INSERT INTO `district` VALUES ('370704', '山东省潍坊市坊子区');
INSERT INTO `district` VALUES ('370705', '山东省潍坊市奎文区');
INSERT INTO `district` VALUES ('370724', '山东省潍坊市临朐县');
INSERT INTO `district` VALUES ('370725', '山东省潍坊市昌乐县');
INSERT INTO `district` VALUES ('370781', '山东省潍坊市青州市');
INSERT INTO `district` VALUES ('370782', '山东省潍坊市诸城市');
INSERT INTO `district` VALUES ('370783', '山东省潍坊市寿光市');
INSERT INTO `district` VALUES ('370784', '山东省潍坊市安丘市');
INSERT INTO `district` VALUES ('370785', '山东省潍坊市高密市');
INSERT INTO `district` VALUES ('370786', '山东省潍坊市昌邑市');
INSERT INTO `district` VALUES ('370800', '山东省济宁市');
INSERT INTO `district` VALUES ('370801', '山东省济宁市市辖区');
INSERT INTO `district` VALUES ('370802', '山东省济宁市市中区');
INSERT INTO `district` VALUES ('370811', '山东省济宁市任城区');
INSERT INTO `district` VALUES ('370826', '山东省济宁市微山县');
INSERT INTO `district` VALUES ('370827', '山东省济宁市鱼台县');
INSERT INTO `district` VALUES ('370828', '山东省济宁市金乡县');
INSERT INTO `district` VALUES ('370829', '山东省济宁市嘉祥县');
INSERT INTO `district` VALUES ('370830', '山东省济宁市汶上县');
INSERT INTO `district` VALUES ('370831', '山东省济宁市泗水县');
INSERT INTO `district` VALUES ('370832', '山东省济宁市梁山县');
INSERT INTO `district` VALUES ('370881', '山东省济宁市曲阜市');
INSERT INTO `district` VALUES ('370882', '山东省济宁市兖州市');
INSERT INTO `district` VALUES ('370883', '山东省济宁市邹城市');
INSERT INTO `district` VALUES ('370900', '山东省泰安市');
INSERT INTO `district` VALUES ('370901', '山东省泰安市市辖区');
INSERT INTO `district` VALUES ('370902', '山东省泰安市泰山区');
INSERT INTO `district` VALUES ('370903', '山东省泰安市岱岳区');
INSERT INTO `district` VALUES ('370911', '山东省泰安市郊区');
INSERT INTO `district` VALUES ('370921', '山东省泰安市宁阳县');
INSERT INTO `district` VALUES ('370923', '山东省泰安市东平县');
INSERT INTO `district` VALUES ('370982', '山东省泰安市新泰市');
INSERT INTO `district` VALUES ('370983', '山东省泰安市肥城市');
INSERT INTO `district` VALUES ('371000', '山东省威海市');
INSERT INTO `district` VALUES ('371001', '山东省威海市市辖区');
INSERT INTO `district` VALUES ('371002', '山东省威海市环翠区');
INSERT INTO `district` VALUES ('371081', '山东省威海市文登市');
INSERT INTO `district` VALUES ('371082', '山东省威海市荣成市');
INSERT INTO `district` VALUES ('371083', '山东省威海市乳山市');
INSERT INTO `district` VALUES ('371100', '山东省日照市');
INSERT INTO `district` VALUES ('371101', '山东省日照市市辖区');
INSERT INTO `district` VALUES ('371102', '山东省日照市东港区');
INSERT INTO `district` VALUES ('371121', '山东省日照市五莲县');
INSERT INTO `district` VALUES ('371122', '山东省日照市莒县');
INSERT INTO `district` VALUES ('371200', '山东省莱芜市');
INSERT INTO `district` VALUES ('371201', '山东省莱芜市市辖区');
INSERT INTO `district` VALUES ('371202', '山东省莱芜市莱城区');
INSERT INTO `district` VALUES ('371203', '山东省莱芜市钢城区');
INSERT INTO `district` VALUES ('371300', '山东省临沂市');
INSERT INTO `district` VALUES ('371301', '山东省临沂市市辖区');
INSERT INTO `district` VALUES ('371302', '山东省临沂市兰山区');
INSERT INTO `district` VALUES ('371311', '山东省临沂市罗庄区');
INSERT INTO `district` VALUES ('371312', '山东省临沂市河东区');
INSERT INTO `district` VALUES ('371321', '山东省临沂市沂南县');
INSERT INTO `district` VALUES ('371322', '山东省临沂市郯城县');
INSERT INTO `district` VALUES ('371323', '山东省临沂市沂水县');
INSERT INTO `district` VALUES ('371324', '山东省临沂市苍山县');
INSERT INTO `district` VALUES ('371325', '山东省临沂市费县');
INSERT INTO `district` VALUES ('371326', '山东省临沂市平邑县');
INSERT INTO `district` VALUES ('371327', '山东省临沂市莒南县');
INSERT INTO `district` VALUES ('371328', '山东省临沂市蒙阴县');
INSERT INTO `district` VALUES ('371329', '山东省临沂市临沭县');
INSERT INTO `district` VALUES ('371400', '山东省德州市');
INSERT INTO `district` VALUES ('371401', '山东省德州市市辖区');
INSERT INTO `district` VALUES ('371402', '山东省德州市德城区');
INSERT INTO `district` VALUES ('371421', '山东省德州市陵县');
INSERT INTO `district` VALUES ('371422', '山东省德州市宁津县');
INSERT INTO `district` VALUES ('371423', '山东省德州市庆云县');
INSERT INTO `district` VALUES ('371424', '山东省德州市临邑县');
INSERT INTO `district` VALUES ('371425', '山东省德州市齐河县');
INSERT INTO `district` VALUES ('371426', '山东省德州市平原县');
INSERT INTO `district` VALUES ('371427', '山东省德州市夏津县');
INSERT INTO `district` VALUES ('371428', '山东省德州市武城县');
INSERT INTO `district` VALUES ('371481', '山东省德州市乐陵市');
INSERT INTO `district` VALUES ('371482', '山东省德州市禹城市');
INSERT INTO `district` VALUES ('371500', '山东省聊城市');
INSERT INTO `district` VALUES ('371501', '山东省聊城市市辖区');
INSERT INTO `district` VALUES ('371502', '山东省聊城市东昌府区');
INSERT INTO `district` VALUES ('371521', '山东省聊城市阳谷县');
INSERT INTO `district` VALUES ('371522', '山东省聊城市莘县');
INSERT INTO `district` VALUES ('371523', '山东省聊城市茌平县');
INSERT INTO `district` VALUES ('371524', '山东省聊城市东阿县');
INSERT INTO `district` VALUES ('371525', '山东省聊城市冠县');
INSERT INTO `district` VALUES ('371526', '山东省聊城市高唐县');
INSERT INTO `district` VALUES ('371581', '山东省聊城市临清市');
INSERT INTO `district` VALUES ('371600', '山东省滨州市');
INSERT INTO `district` VALUES ('371601', '山东省滨州市市辖区');
INSERT INTO `district` VALUES ('371602', '山东省滨州市滨城区');
INSERT INTO `district` VALUES ('371621', '山东省滨州市惠民县');
INSERT INTO `district` VALUES ('371622', '山东省滨州市阳信县');
INSERT INTO `district` VALUES ('371623', '山东省滨州市无棣县');
INSERT INTO `district` VALUES ('371624', '山东省滨州市沾化县');
INSERT INTO `district` VALUES ('371625', '山东省滨州市博兴县');
INSERT INTO `district` VALUES ('371626', '山东省滨州市邹平县');
INSERT INTO `district` VALUES ('371700', '山东省荷泽市');
INSERT INTO `district` VALUES ('371701', '山东省荷泽市市辖区');
INSERT INTO `district` VALUES ('371702', '山东省荷泽市牡丹区');
INSERT INTO `district` VALUES ('371721', '山东省荷泽市曹县');
INSERT INTO `district` VALUES ('371722', '山东省荷泽市单县');
INSERT INTO `district` VALUES ('371723', '山东省荷泽市成武县');
INSERT INTO `district` VALUES ('371724', '山东省荷泽市巨野县');
INSERT INTO `district` VALUES ('371725', '山东省荷泽市郓城县');
INSERT INTO `district` VALUES ('371726', '山东省荷泽市鄄城县');
INSERT INTO `district` VALUES ('371727', '山东省荷泽市定陶县');
INSERT INTO `district` VALUES ('371728', '山东省荷泽市东明县');
INSERT INTO `district` VALUES ('372300', '山东省滨州地区');
INSERT INTO `district` VALUES ('372301', '山东省滨州地区滨州市');
INSERT INTO `district` VALUES ('372321', '山东省滨州地区惠民县');
INSERT INTO `district` VALUES ('372323', '山东省滨州地区阳信县');
INSERT INTO `district` VALUES ('372324', '山东省滨州地区无棣县');
INSERT INTO `district` VALUES ('372325', '山东省滨州地区沾化县');
INSERT INTO `district` VALUES ('372328', '山东省滨州地区博兴县');
INSERT INTO `district` VALUES ('372330', '山东省滨州地区邹平县');
INSERT INTO `district` VALUES ('372900', '山东省菏泽地区');
INSERT INTO `district` VALUES ('372901', '山东省菏泽地区菏泽市');
INSERT INTO `district` VALUES ('372922', '山东省菏泽地区曹县');
INSERT INTO `district` VALUES ('372923', '山东省菏泽地区定陶县');
INSERT INTO `district` VALUES ('372924', '山东省菏泽地区成武县');
INSERT INTO `district` VALUES ('372925', '山东省菏泽地区单县');
INSERT INTO `district` VALUES ('372926', '山东省菏泽地区巨野县');
INSERT INTO `district` VALUES ('372928', '山东省菏泽地区郓城县');
INSERT INTO `district` VALUES ('372929', '山东省菏泽地区鄄城县');
INSERT INTO `district` VALUES ('372930', '山东省菏泽地区东明县');
INSERT INTO `district` VALUES ('410000', '河南省');
INSERT INTO `district` VALUES ('410100', '河南省郑州市');
INSERT INTO `district` VALUES ('410101', '河南省郑州市市辖区');
INSERT INTO `district` VALUES ('410102', '河南省郑州市中原区');
INSERT INTO `district` VALUES ('410103', '河南省郑州市二七区');
INSERT INTO `district` VALUES ('410104', '河南省郑州市管城回族区');
INSERT INTO `district` VALUES ('410105', '河南省郑州市金水区');
INSERT INTO `district` VALUES ('410106', '河南省郑州市上街区');
INSERT INTO `district` VALUES ('410108', '河南省郑州市邙山区');
INSERT INTO `district` VALUES ('410122', '河南省郑州市中牟县');
INSERT INTO `district` VALUES ('410181', '河南省郑州市巩义市');
INSERT INTO `district` VALUES ('410182', '河南省郑州市荥阳市');
INSERT INTO `district` VALUES ('410183', '河南省郑州市新密市');
INSERT INTO `district` VALUES ('410184', '河南省郑州市新郑市');
INSERT INTO `district` VALUES ('410185', '河南省郑州市登封市');
INSERT INTO `district` VALUES ('410200', '河南省开封市');
INSERT INTO `district` VALUES ('410201', '河南省开封市市辖区');
INSERT INTO `district` VALUES ('410202', '河南省开封市龙亭区');
INSERT INTO `district` VALUES ('410203', '河南省开封市顺河回族区');
INSERT INTO `district` VALUES ('410204', '河南省开封市鼓楼区');
INSERT INTO `district` VALUES ('410205', '河南省开封市南关区');
INSERT INTO `district` VALUES ('410211', '河南省开封市郊区');
INSERT INTO `district` VALUES ('410221', '河南省开封市杞县');
INSERT INTO `district` VALUES ('410222', '河南省开封市通许县');
INSERT INTO `district` VALUES ('410223', '河南省开封市尉氏县');
INSERT INTO `district` VALUES ('410224', '河南省开封市开封县');
INSERT INTO `district` VALUES ('410225', '河南省开封市兰考县');
INSERT INTO `district` VALUES ('410300', '河南省洛阳市');
INSERT INTO `district` VALUES ('410301', '河南省洛阳市市辖区');
INSERT INTO `district` VALUES ('410302', '河南省洛阳市老城区');
INSERT INTO `district` VALUES ('410303', '河南省洛阳市西工区');
INSERT INTO `district` VALUES ('410304', '河南省洛阳市廛河回族区');
INSERT INTO `district` VALUES ('410305', '河南省洛阳市涧西区');
INSERT INTO `district` VALUES ('410306', '河南省洛阳市吉利区');
INSERT INTO `district` VALUES ('410307', '河南省洛阳市洛龙区');
INSERT INTO `district` VALUES ('410311', '河南省洛阳市郊区');
INSERT INTO `district` VALUES ('410322', '河南省洛阳市孟津县');
INSERT INTO `district` VALUES ('410323', '河南省洛阳市新安县');
INSERT INTO `district` VALUES ('410324', '河南省洛阳市栾川县');
INSERT INTO `district` VALUES ('410325', '河南省洛阳市嵩县');
INSERT INTO `district` VALUES ('410326', '河南省洛阳市汝阳县');
INSERT INTO `district` VALUES ('410327', '河南省洛阳市宜阳县');
INSERT INTO `district` VALUES ('410328', '河南省洛阳市洛宁县');
INSERT INTO `district` VALUES ('410329', '河南省洛阳市伊川县');
INSERT INTO `district` VALUES ('410381', '河南省洛阳市偃师市');
INSERT INTO `district` VALUES ('410400', '河南省平顶山市');
INSERT INTO `district` VALUES ('410401', '河南省平顶山市市辖区');
INSERT INTO `district` VALUES ('410402', '河南省平顶山市新华区');
INSERT INTO `district` VALUES ('410403', '河南省平顶山市卫东区');
INSERT INTO `district` VALUES ('410404', '河南省平顶山市石龙区');
INSERT INTO `district` VALUES ('410411', '河南省平顶山市湛河区');
INSERT INTO `district` VALUES ('410421', '河南省平顶山市宝丰县');
INSERT INTO `district` VALUES ('410422', '河南省平顶山市叶县');
INSERT INTO `district` VALUES ('410423', '河南省平顶山市鲁山县');
INSERT INTO `district` VALUES ('410425', '河南省平顶山市郏县');
INSERT INTO `district` VALUES ('410481', '河南省平顶山市舞钢市');
INSERT INTO `district` VALUES ('410482', '河南省平顶山市汝州市');
INSERT INTO `district` VALUES ('410500', '河南省安阳市');
INSERT INTO `district` VALUES ('410501', '河南省安阳市市辖区');
INSERT INTO `district` VALUES ('410502', '河南省安阳市文峰区');
INSERT INTO `district` VALUES ('410503', '河南省安阳市北关区');
INSERT INTO `district` VALUES ('410504', '河南省安阳市铁西区');
INSERT INTO `district` VALUES ('410505', '河南省安阳市殷都区');
INSERT INTO `district` VALUES ('410506', '河南省安阳市龙安区');
INSERT INTO `district` VALUES ('410511', '河南省安阳市郊区');
INSERT INTO `district` VALUES ('410522', '河南省安阳市安阳县');
INSERT INTO `district` VALUES ('410523', '河南省安阳市汤阴县');
INSERT INTO `district` VALUES ('410526', '河南省安阳市滑县');
INSERT INTO `district` VALUES ('410527', '河南省安阳市内黄县');
INSERT INTO `district` VALUES ('410581', '河南省安阳市林州市');
INSERT INTO `district` VALUES ('410600', '河南省鹤壁市');
INSERT INTO `district` VALUES ('410601', '河南省鹤壁市市辖区');
INSERT INTO `district` VALUES ('410602', '河南省鹤壁市鹤山区');
INSERT INTO `district` VALUES ('410603', '河南省鹤壁市山城区');
INSERT INTO `district` VALUES ('410611', '河南省鹤壁市郊区');
INSERT INTO `district` VALUES ('410621', '河南省鹤壁市浚县');
INSERT INTO `district` VALUES ('410622', '河南省鹤壁市淇县');
INSERT INTO `district` VALUES ('410700', '河南省新乡市');
INSERT INTO `district` VALUES ('410701', '河南省新乡市市辖区');
INSERT INTO `district` VALUES ('410702', '河南省新乡市红旗区');
INSERT INTO `district` VALUES ('410703', '河南省新乡市新华区');
INSERT INTO `district` VALUES ('410704', '河南省新乡市北站区');
INSERT INTO `district` VALUES ('410711', '河南省新乡市郊区');
INSERT INTO `district` VALUES ('410721', '河南省新乡市新乡县');
INSERT INTO `district` VALUES ('410724', '河南省新乡市获嘉县');
INSERT INTO `district` VALUES ('410725', '河南省新乡市原阳县');
INSERT INTO `district` VALUES ('410726', '河南省新乡市延津县');
INSERT INTO `district` VALUES ('410727', '河南省新乡市封丘县');
INSERT INTO `district` VALUES ('410728', '河南省新乡市长垣县');
INSERT INTO `district` VALUES ('410781', '河南省新乡市卫辉市');
INSERT INTO `district` VALUES ('410782', '河南省新乡市辉县市');
INSERT INTO `district` VALUES ('410800', '河南省焦作市');
INSERT INTO `district` VALUES ('410801', '河南省焦作市市辖区');
INSERT INTO `district` VALUES ('410802', '河南省焦作市解放区');
INSERT INTO `district` VALUES ('410803', '河南省焦作市中站区');
INSERT INTO `district` VALUES ('410804', '河南省焦作市马村区');
INSERT INTO `district` VALUES ('410811', '河南省焦作市山阳区');
INSERT INTO `district` VALUES ('410821', '河南省焦作市修武县');
INSERT INTO `district` VALUES ('410822', '河南省焦作市博爱县');
INSERT INTO `district` VALUES ('410823', '河南省焦作市武陟县');
INSERT INTO `district` VALUES ('410825', '河南省焦作市温县');
INSERT INTO `district` VALUES ('410881', '河南省焦作市济源市');
INSERT INTO `district` VALUES ('410882', '河南省焦作市沁阳市');
INSERT INTO `district` VALUES ('410883', '河南省焦作市孟州市');
INSERT INTO `district` VALUES ('410900', '河南省濮阳市');
INSERT INTO `district` VALUES ('410901', '河南省濮阳市市辖区');
INSERT INTO `district` VALUES ('410902', '河南省濮阳市市区');
INSERT INTO `district` VALUES ('410922', '河南省濮阳市清丰县');
INSERT INTO `district` VALUES ('410923', '河南省濮阳市南乐县');
INSERT INTO `district` VALUES ('410926', '河南省濮阳市范县');
INSERT INTO `district` VALUES ('410927', '河南省濮阳市台前县');
INSERT INTO `district` VALUES ('410928', '河南省濮阳市濮阳县');
INSERT INTO `district` VALUES ('411000', '河南省许昌市');
INSERT INTO `district` VALUES ('411001', '河南省许昌市市辖区');
INSERT INTO `district` VALUES ('411002', '河南省许昌市魏都区');
INSERT INTO `district` VALUES ('411023', '河南省许昌市许昌县');
INSERT INTO `district` VALUES ('411024', '河南省许昌市鄢陵县');
INSERT INTO `district` VALUES ('411025', '河南省许昌市襄城县');
INSERT INTO `district` VALUES ('411081', '河南省许昌市禹州市');
INSERT INTO `district` VALUES ('411082', '河南省许昌市长葛市');
INSERT INTO `district` VALUES ('411100', '河南省漯河市');
INSERT INTO `district` VALUES ('411101', '河南省漯河市市辖区');
INSERT INTO `district` VALUES ('411102', '河南省漯河市源汇区');
INSERT INTO `district` VALUES ('411121', '河南省漯河市舞阳县');
INSERT INTO `district` VALUES ('411122', '河南省漯河市临颍县');
INSERT INTO `district` VALUES ('411123', '河南省漯河市郾城县');
INSERT INTO `district` VALUES ('411200', '河南省三门峡市');
INSERT INTO `district` VALUES ('411201', '河南省三门峡市市辖区');
INSERT INTO `district` VALUES ('411202', '河南省三门峡市湖滨区');
INSERT INTO `district` VALUES ('411221', '河南省三门峡市渑池县');
INSERT INTO `district` VALUES ('411222', '河南省三门峡市陕县');
INSERT INTO `district` VALUES ('411224', '河南省三门峡市卢氏县');
INSERT INTO `district` VALUES ('411281', '河南省三门峡市义马市');
INSERT INTO `district` VALUES ('411282', '河南省三门峡市灵宝市');
INSERT INTO `district` VALUES ('411300', '河南省南阳市');
INSERT INTO `district` VALUES ('411301', '河南省南阳市市辖区');
INSERT INTO `district` VALUES ('411302', '河南省南阳市宛城区');
INSERT INTO `district` VALUES ('411303', '河南省南阳市卧龙区');
INSERT INTO `district` VALUES ('411321', '河南省南阳市南召县');
INSERT INTO `district` VALUES ('411322', '河南省南阳市方城县');
INSERT INTO `district` VALUES ('411323', '河南省南阳市西峡县');
INSERT INTO `district` VALUES ('411324', '河南省南阳市镇平县');
INSERT INTO `district` VALUES ('411325', '河南省南阳市内乡县');
INSERT INTO `district` VALUES ('411326', '河南省南阳市淅川县');
INSERT INTO `district` VALUES ('411327', '河南省南阳市社旗县');
INSERT INTO `district` VALUES ('411328', '河南省南阳市唐河县');
INSERT INTO `district` VALUES ('411329', '河南省南阳市新野县');
INSERT INTO `district` VALUES ('411330', '河南省南阳市桐柏县');
INSERT INTO `district` VALUES ('411381', '河南省南阳市邓州市');
INSERT INTO `district` VALUES ('411400', '河南省商丘市');
INSERT INTO `district` VALUES ('411401', '河南省商丘市市辖区');
INSERT INTO `district` VALUES ('411402', '河南省商丘市梁园区');
INSERT INTO `district` VALUES ('411403', '河南省商丘市睢阳区');
INSERT INTO `district` VALUES ('411421', '河南省商丘市民权县');
INSERT INTO `district` VALUES ('411422', '河南省商丘市睢县');
INSERT INTO `district` VALUES ('411423', '河南省商丘市宁陵县');
INSERT INTO `district` VALUES ('411424', '河南省商丘市柘城县');
INSERT INTO `district` VALUES ('411425', '河南省商丘市虞城县');
INSERT INTO `district` VALUES ('411426', '河南省商丘市夏邑县');
INSERT INTO `district` VALUES ('411481', '河南省商丘市永城市');
INSERT INTO `district` VALUES ('411500', '河南省信阳市');
INSERT INTO `district` VALUES ('411501', '河南省信阳市市辖区');
INSERT INTO `district` VALUES ('411502', '河南省信阳市师河区');
INSERT INTO `district` VALUES ('411503', '河南省信阳市平桥区');
INSERT INTO `district` VALUES ('411521', '河南省信阳市罗山县');
INSERT INTO `district` VALUES ('411522', '河南省信阳市光山县');
INSERT INTO `district` VALUES ('411523', '河南省信阳市新县');
INSERT INTO `district` VALUES ('411524', '河南省信阳市商城县');
INSERT INTO `district` VALUES ('411525', '河南省信阳市固始县');
INSERT INTO `district` VALUES ('411526', '河南省信阳市潢川县');
INSERT INTO `district` VALUES ('411527', '河南省信阳市淮滨县');
INSERT INTO `district` VALUES ('411528', '河南省信阳市息县');
INSERT INTO `district` VALUES ('411600', '河南省周口市');
INSERT INTO `district` VALUES ('411601', '河南省周口市市辖区');
INSERT INTO `district` VALUES ('411602', '河南省周口市川汇区');
INSERT INTO `district` VALUES ('411621', '河南省周口市扶沟县');
INSERT INTO `district` VALUES ('411622', '河南省周口市西华县');
INSERT INTO `district` VALUES ('411623', '河南省周口市商水县');
INSERT INTO `district` VALUES ('411624', '河南省周口市沈丘县');
INSERT INTO `district` VALUES ('411625', '河南省周口市郸城县');
INSERT INTO `district` VALUES ('411626', '河南省周口市淮阳县');
INSERT INTO `district` VALUES ('411627', '河南省周口市太康县');
INSERT INTO `district` VALUES ('411628', '河南省周口市鹿邑县');
INSERT INTO `district` VALUES ('411681', '河南省周口市项城市');
INSERT INTO `district` VALUES ('411700', '河南省驻马店市');
INSERT INTO `district` VALUES ('411701', '河南省驻马店市市辖区');
INSERT INTO `district` VALUES ('411702', '河南省驻马店市驿城区');
INSERT INTO `district` VALUES ('411721', '河南省驻马店市西平县');
INSERT INTO `district` VALUES ('411722', '河南省驻马店市上蔡县');
INSERT INTO `district` VALUES ('411723', '河南省驻马店市平舆县');
INSERT INTO `district` VALUES ('411724', '河南省驻马店市正阳县');
INSERT INTO `district` VALUES ('411725', '河南省驻马店市确山县');
INSERT INTO `district` VALUES ('411726', '河南省驻马店市泌阳县');
INSERT INTO `district` VALUES ('411727', '河南省驻马店市汝南县');
INSERT INTO `district` VALUES ('411728', '河南省驻马店市遂平县');
INSERT INTO `district` VALUES ('411729', '河南省驻马店市新蔡县');
INSERT INTO `district` VALUES ('412700', '河南省周口地区');
INSERT INTO `district` VALUES ('412701', '河南省周口地区周口市');
INSERT INTO `district` VALUES ('412702', '河南省周口地区项城市');
INSERT INTO `district` VALUES ('412721', '河南省周口地区扶沟县');
INSERT INTO `district` VALUES ('412722', '河南省周口地区西华县');
INSERT INTO `district` VALUES ('412723', '河南省周口地区商水县');
INSERT INTO `district` VALUES ('412724', '河南省周口地区太康县');
INSERT INTO `district` VALUES ('412725', '河南省周口地区鹿邑县');
INSERT INTO `district` VALUES ('412726', '河南省周口地区郸城县');
INSERT INTO `district` VALUES ('412727', '河南省周口地区淮阳县');
INSERT INTO `district` VALUES ('412728', '河南省周口地区沈丘县');
INSERT INTO `district` VALUES ('412800', '河南省驻马店地区');
INSERT INTO `district` VALUES ('412801', '河南省驻马店地区驻马店市');
INSERT INTO `district` VALUES ('412821', '河南省驻马店地区确山县');
INSERT INTO `district` VALUES ('412822', '河南省驻马店地区泌阳县');
INSERT INTO `district` VALUES ('412823', '河南省驻马店地区遂平县');
INSERT INTO `district` VALUES ('412824', '河南省驻马店地区西平县');
INSERT INTO `district` VALUES ('412825', '河南省驻马店地区上蔡县');
INSERT INTO `district` VALUES ('412826', '河南省驻马店地区汝南县');
INSERT INTO `district` VALUES ('412827', '河南省驻马店地区平舆县');
INSERT INTO `district` VALUES ('412828', '河南省驻马店地区新蔡县');
INSERT INTO `district` VALUES ('412829', '河南省驻马店地区正阳县');
INSERT INTO `district` VALUES ('412900', '河南省济源地区');
INSERT INTO `district` VALUES ('412901', '河南省济源地区济源市');
INSERT INTO `district` VALUES ('420000', '湖北省');
INSERT INTO `district` VALUES ('420100', '湖北省武汉市');
INSERT INTO `district` VALUES ('420101', '湖北省武汉市市辖区');
INSERT INTO `district` VALUES ('420102', '湖北省武汉市江岸区');
INSERT INTO `district` VALUES ('420103', '湖北省武汉市江汉区');
INSERT INTO `district` VALUES ('420104', '湖北省武汉市乔口区');
INSERT INTO `district` VALUES ('420105', '湖北省武汉市汉阳区');
INSERT INTO `district` VALUES ('420106', '湖北省武汉市武昌区');
INSERT INTO `district` VALUES ('420107', '湖北省武汉市青山区');
INSERT INTO `district` VALUES ('420111', '湖北省武汉市洪山区');
INSERT INTO `district` VALUES ('420112', '湖北省武汉市东西湖区');
INSERT INTO `district` VALUES ('420113', '湖北省武汉市汉南区');
INSERT INTO `district` VALUES ('420114', '湖北省武汉市蔡甸区');
INSERT INTO `district` VALUES ('420115', '湖北省武汉市江夏区');
INSERT INTO `district` VALUES ('420116', '湖北省武汉市黄陂区');
INSERT INTO `district` VALUES ('420117', '湖北省武汉市新洲区');
INSERT INTO `district` VALUES ('420200', '湖北省黄石市');
INSERT INTO `district` VALUES ('420201', '湖北省黄石市市辖区');
INSERT INTO `district` VALUES ('420202', '湖北省黄石市黄石港区');
INSERT INTO `district` VALUES ('420203', '湖北省黄石市石灰窑区');
INSERT INTO `district` VALUES ('420204', '湖北省黄石市下陆区');
INSERT INTO `district` VALUES ('420205', '湖北省黄石市铁山区');
INSERT INTO `district` VALUES ('420222', '湖北省黄石市阳新县');
INSERT INTO `district` VALUES ('420281', '湖北省黄石市大冶市');
INSERT INTO `district` VALUES ('420300', '湖北省十堰市');
INSERT INTO `district` VALUES ('420301', '湖北省十堰市市辖区');
INSERT INTO `district` VALUES ('420302', '湖北省十堰市茅箭区');
INSERT INTO `district` VALUES ('420303', '湖北省十堰市张湾区');
INSERT INTO `district` VALUES ('420321', '湖北省十堰市郧县');
INSERT INTO `district` VALUES ('420322', '湖北省十堰市郧西县');
INSERT INTO `district` VALUES ('420323', '湖北省十堰市竹山县');
INSERT INTO `district` VALUES ('420324', '湖北省十堰市竹溪县');
INSERT INTO `district` VALUES ('420325', '湖北省十堰市房县');
INSERT INTO `district` VALUES ('420381', '湖北省十堰市丹江口市');
INSERT INTO `district` VALUES ('420500', '湖北省宜昌市');
INSERT INTO `district` VALUES ('420501', '湖北省宜昌市市辖区');
INSERT INTO `district` VALUES ('420502', '湖北省宜昌市西陵区');
INSERT INTO `district` VALUES ('420503', '湖北省宜昌市伍家岗区');
INSERT INTO `district` VALUES ('420504', '湖北省宜昌市点军区');
INSERT INTO `district` VALUES ('420505', '湖北省宜昌市虎亭区');
INSERT INTO `district` VALUES ('420506', '湖北省宜昌市夷陵区');
INSERT INTO `district` VALUES ('420521', '湖北省宜昌市宜昌县');
INSERT INTO `district` VALUES ('420525', '湖北省宜昌市远安县');
INSERT INTO `district` VALUES ('420526', '湖北省宜昌市兴山县');
INSERT INTO `district` VALUES ('420527', '湖北省宜昌市秭归县');
INSERT INTO `district` VALUES ('420528', '湖北省宜昌市长阳土家族自治县');
INSERT INTO `district` VALUES ('420529', '湖北省宜昌市五峰土家族自治县');
INSERT INTO `district` VALUES ('420581', '湖北省宜昌市宜都市');
INSERT INTO `district` VALUES ('420582', '湖北省宜昌市当阳市');
INSERT INTO `district` VALUES ('420583', '湖北省宜昌市枝江市');
INSERT INTO `district` VALUES ('420600', '湖北省襄樊市');
INSERT INTO `district` VALUES ('420601', '湖北省襄樊市市辖区');
INSERT INTO `district` VALUES ('420602', '湖北省襄樊市襄城区');
INSERT INTO `district` VALUES ('420606', '湖北省襄樊市樊城区');
INSERT INTO `district` VALUES ('420607', '湖北省襄樊市襄阳区');
INSERT INTO `district` VALUES ('420621', '湖北省襄樊市襄阳县');
INSERT INTO `district` VALUES ('420624', '湖北省襄樊市南漳县');
INSERT INTO `district` VALUES ('420625', '湖北省襄樊市谷城县');
INSERT INTO `district` VALUES ('420626', '湖北省襄樊市保康县');
INSERT INTO `district` VALUES ('420682', '湖北省襄樊市老河口市');
INSERT INTO `district` VALUES ('420683', '湖北省襄樊市枣阳市');
INSERT INTO `district` VALUES ('420684', '湖北省襄樊市宜城市');
INSERT INTO `district` VALUES ('420700', '湖北省鄂州市');
INSERT INTO `district` VALUES ('420701', '湖北省鄂州市市辖区');
INSERT INTO `district` VALUES ('420702', '湖北省鄂州市梁子湖区');
INSERT INTO `district` VALUES ('420703', '湖北省鄂州市华容区');
INSERT INTO `district` VALUES ('420704', '湖北省鄂州市鄂城区');
INSERT INTO `district` VALUES ('420800', '湖北省荆门市');
INSERT INTO `district` VALUES ('420801', '湖北省荆门市市辖区');
INSERT INTO `district` VALUES ('420802', '湖北省荆门市东宝区');
INSERT INTO `district` VALUES ('420804', '湖北省荆门市掇刀区');
INSERT INTO `district` VALUES ('420821', '湖北省荆门市京山县');
INSERT INTO `district` VALUES ('420822', '湖北省荆门市沙洋县');
INSERT INTO `district` VALUES ('420881', '湖北省荆门市钟祥市');
INSERT INTO `district` VALUES ('420900', '湖北省孝感市');
INSERT INTO `district` VALUES ('420901', '湖北省孝感市市辖区');
INSERT INTO `district` VALUES ('420902', '湖北省孝感市孝南区');
INSERT INTO `district` VALUES ('420921', '湖北省孝感市孝昌县');
INSERT INTO `district` VALUES ('420922', '湖北省孝感市大悟县');
INSERT INTO `district` VALUES ('420923', '湖北省孝感市云梦县');
INSERT INTO `district` VALUES ('420981', '湖北省孝感市应城市');
INSERT INTO `district` VALUES ('420982', '湖北省孝感市安陆市');
INSERT INTO `district` VALUES ('420983', '湖北省孝感市广水市');
INSERT INTO `district` VALUES ('420984', '湖北省孝感市汉川市');
INSERT INTO `district` VALUES ('421000', '湖北省荆州市');
INSERT INTO `district` VALUES ('421001', '湖北省荆州市市辖区');
INSERT INTO `district` VALUES ('421002', '湖北省荆州市沙市区');
INSERT INTO `district` VALUES ('421003', '湖北省荆州市荆州区');
INSERT INTO `district` VALUES ('421022', '湖北省荆州市公安县');
INSERT INTO `district` VALUES ('421023', '湖北省荆州市监利县');
INSERT INTO `district` VALUES ('421024', '湖北省荆州市江陵县');
INSERT INTO `district` VALUES ('421081', '湖北省荆州市石首市');
INSERT INTO `district` VALUES ('421083', '湖北省荆州市洪湖市');
INSERT INTO `district` VALUES ('421087', '湖北省荆州市松滋市');
INSERT INTO `district` VALUES ('421100', '湖北省黄冈市');
INSERT INTO `district` VALUES ('421101', '湖北省黄冈市市辖区');
INSERT INTO `district` VALUES ('421102', '湖北省黄冈市黄州区');
INSERT INTO `district` VALUES ('421121', '湖北省黄冈市团风县');
INSERT INTO `district` VALUES ('421122', '湖北省黄冈市红安县');
INSERT INTO `district` VALUES ('421123', '湖北省黄冈市罗田县');
INSERT INTO `district` VALUES ('421124', '湖北省黄冈市英山县');
INSERT INTO `district` VALUES ('421125', '湖北省黄冈市浠水县');
INSERT INTO `district` VALUES ('421126', '湖北省黄冈市蕲春县');
INSERT INTO `district` VALUES ('421127', '湖北省黄冈市黄梅县');
INSERT INTO `district` VALUES ('421181', '湖北省黄冈市麻城市');
INSERT INTO `district` VALUES ('421182', '湖北省黄冈市武穴市');
INSERT INTO `district` VALUES ('421200', '湖北省咸宁市');
INSERT INTO `district` VALUES ('421201', '湖北省咸宁市市辖区');
INSERT INTO `district` VALUES ('421202', '湖北省咸宁市咸安区');
INSERT INTO `district` VALUES ('421221', '湖北省咸宁市嘉鱼县');
INSERT INTO `district` VALUES ('421222', '湖北省咸宁市通城县');
INSERT INTO `district` VALUES ('421223', '湖北省咸宁市崇阳县');
INSERT INTO `district` VALUES ('421224', '湖北省咸宁市通山县');
INSERT INTO `district` VALUES ('421281', '湖北省咸宁市赤壁市');
INSERT INTO `district` VALUES ('421300', '湖北省随州市');
INSERT INTO `district` VALUES ('421301', '湖北省随州市市辖区');
INSERT INTO `district` VALUES ('421302', '湖北省随州市曾都区');
INSERT INTO `district` VALUES ('421381', '湖北省随州市广水市');
INSERT INTO `district` VALUES ('422800', '湖北省恩施土家族苗族自治州');
INSERT INTO `district` VALUES ('422801', '湖北省恩施土家族苗族自治州恩施市');
INSERT INTO `district` VALUES ('422802', '湖北省恩施土家族苗族自治州利川市');
INSERT INTO `district` VALUES ('422822', '湖北省恩施土家族苗族自治州建始县');
INSERT INTO `district` VALUES ('422823', '湖北省恩施土家族苗族自治州巴东县');
INSERT INTO `district` VALUES ('422825', '湖北省恩施土家族苗族自治州宣恩县');
INSERT INTO `district` VALUES ('422826', '湖北省恩施土家族苗族自治州咸丰县');
INSERT INTO `district` VALUES ('422827', '湖北省恩施土家族苗族自治州来凤县');
INSERT INTO `district` VALUES ('422828', '湖北省恩施土家族苗族自治州鹤峰县');
INSERT INTO `district` VALUES ('429000', '湖北省省直辖行政单位');
INSERT INTO `district` VALUES ('429001', '湖北省省直辖行政单位随州市');
INSERT INTO `district` VALUES ('429004', '湖北省省直辖行政单位仙桃市');
INSERT INTO `district` VALUES ('429005', '湖北省省直辖行政单位潜江市');
INSERT INTO `district` VALUES ('429006', '湖北省省直辖行政单位天门市');
INSERT INTO `district` VALUES ('429021', '湖北省省直辖行政单位神农架林区');
INSERT INTO `district` VALUES ('430000', '湖南省');
INSERT INTO `district` VALUES ('430100', '湖南省长沙市');
INSERT INTO `district` VALUES ('430101', '湖南省长沙市市辖区');
INSERT INTO `district` VALUES ('430102', '湖南省长沙市芙蓉区');
INSERT INTO `district` VALUES ('430103', '湖南省长沙市天心区');
INSERT INTO `district` VALUES ('430104', '湖南省长沙市岳麓区');
INSERT INTO `district` VALUES ('430105', '湖南省长沙市开福区');
INSERT INTO `district` VALUES ('430111', '湖南省长沙市雨花区');
INSERT INTO `district` VALUES ('430121', '湖南省长沙市长沙县');
INSERT INTO `district` VALUES ('430122', '湖南省长沙市望城县');
INSERT INTO `district` VALUES ('430124', '湖南省长沙市宁乡县');
INSERT INTO `district` VALUES ('430181', '湖南省长沙市浏阳市');
INSERT INTO `district` VALUES ('430200', '湖南省株洲市');
INSERT INTO `district` VALUES ('430201', '湖南省株洲市市辖区');
INSERT INTO `district` VALUES ('430202', '湖南省株洲市荷塘区');
INSERT INTO `district` VALUES ('430203', '湖南省株洲市芦淞区');
INSERT INTO `district` VALUES ('430204', '湖南省株洲市石峰区');
INSERT INTO `district` VALUES ('430211', '湖南省株洲市天元区');
INSERT INTO `district` VALUES ('430221', '湖南省株洲市株洲县');
INSERT INTO `district` VALUES ('430223', '湖南省株洲市攸县');
INSERT INTO `district` VALUES ('430224', '湖南省株洲市茶陵县');
INSERT INTO `district` VALUES ('430225', '湖南省株洲市炎陵县');
INSERT INTO `district` VALUES ('430281', '湖南省株洲市醴陵市');
INSERT INTO `district` VALUES ('430300', '湖南省湘潭市');
INSERT INTO `district` VALUES ('430301', '湖南省湘潭市市辖区');
INSERT INTO `district` VALUES ('430302', '湖南省湘潭市雨湖区');
INSERT INTO `district` VALUES ('430304', '湖南省湘潭市岳塘区');
INSERT INTO `district` VALUES ('430321', '湖南省湘潭市湘潭县');
INSERT INTO `district` VALUES ('430381', '湖南省湘潭市湘乡市');
INSERT INTO `district` VALUES ('430382', '湖南省湘潭市韶山市');
INSERT INTO `district` VALUES ('430400', '湖南省衡阳市');
INSERT INTO `district` VALUES ('430401', '湖南省衡阳市市辖区');
INSERT INTO `district` VALUES ('430402', '湖南省衡阳市江东区');
INSERT INTO `district` VALUES ('430403', '湖南省衡阳市城南区');
INSERT INTO `district` VALUES ('430404', '湖南省衡阳市城北区');
INSERT INTO `district` VALUES ('430405', '湖南省衡阳市珠晖区');
INSERT INTO `district` VALUES ('430406', '湖南省衡阳市雁峰区');
INSERT INTO `district` VALUES ('430407', '湖南省衡阳市石鼓区');
INSERT INTO `district` VALUES ('430408', '湖南省衡阳市蒸湘区');
INSERT INTO `district` VALUES ('430411', '湖南省衡阳市郊区');
INSERT INTO `district` VALUES ('430412', '湖南省衡阳市南岳区');
INSERT INTO `district` VALUES ('430421', '湖南省衡阳市衡阳县');
INSERT INTO `district` VALUES ('430422', '湖南省衡阳市衡南县');
INSERT INTO `district` VALUES ('430423', '湖南省衡阳市衡山县');
INSERT INTO `district` VALUES ('430424', '湖南省衡阳市衡东县');
INSERT INTO `district` VALUES ('430426', '湖南省衡阳市祁东县');
INSERT INTO `district` VALUES ('430481', '湖南省衡阳市耒阳市');
INSERT INTO `district` VALUES ('430482', '湖南省衡阳市常宁市');
INSERT INTO `district` VALUES ('430500', '湖南省邵阳市');
INSERT INTO `district` VALUES ('430501', '湖南省邵阳市市辖区');
INSERT INTO `district` VALUES ('430502', '湖南省邵阳市双清区');
INSERT INTO `district` VALUES ('430503', '湖南省邵阳市大祥区');
INSERT INTO `district` VALUES ('430511', '湖南省邵阳市北塔区');
INSERT INTO `district` VALUES ('430521', '湖南省邵阳市邵东县');
INSERT INTO `district` VALUES ('430522', '湖南省邵阳市新邵县');
INSERT INTO `district` VALUES ('430523', '湖南省邵阳市邵阳县');
INSERT INTO `district` VALUES ('430524', '湖南省邵阳市隆回县');
INSERT INTO `district` VALUES ('430525', '湖南省邵阳市洞口县');
INSERT INTO `district` VALUES ('430527', '湖南省邵阳市绥宁县');
INSERT INTO `district` VALUES ('430528', '湖南省邵阳市新宁县');
INSERT INTO `district` VALUES ('430529', '湖南省邵阳市城步苗族自治县');
INSERT INTO `district` VALUES ('430581', '湖南省邵阳市武冈市');
INSERT INTO `district` VALUES ('430600', '湖南省岳阳市');
INSERT INTO `district` VALUES ('430601', '湖南省岳阳市市辖区');
INSERT INTO `district` VALUES ('430602', '湖南省岳阳市岳阳楼区');
INSERT INTO `district` VALUES ('430603', '湖南省岳阳市云溪区');
INSERT INTO `district` VALUES ('430611', '湖南省岳阳市君山区');
INSERT INTO `district` VALUES ('430621', '湖南省岳阳市岳阳县');
INSERT INTO `district` VALUES ('430623', '湖南省岳阳市华容县');
INSERT INTO `district` VALUES ('430624', '湖南省岳阳市湘阴县');
INSERT INTO `district` VALUES ('430626', '湖南省岳阳市平江县');
INSERT INTO `district` VALUES ('430681', '湖南省岳阳市汨罗市');
INSERT INTO `district` VALUES ('430682', '湖南省岳阳市临湘市');
INSERT INTO `district` VALUES ('430700', '湖南省常德市');
INSERT INTO `district` VALUES ('430701', '湖南省常德市市辖区');
INSERT INTO `district` VALUES ('430702', '湖南省常德市武陵区');
INSERT INTO `district` VALUES ('430703', '湖南省常德市鼎城区');
INSERT INTO `district` VALUES ('430721', '湖南省常德市安乡县');
INSERT INTO `district` VALUES ('430722', '湖南省常德市汉寿县');
INSERT INTO `district` VALUES ('430723', '湖南省常德市澧县');
INSERT INTO `district` VALUES ('430724', '湖南省常德市临澧县');
INSERT INTO `district` VALUES ('430725', '湖南省常德市桃源县');
INSERT INTO `district` VALUES ('430726', '湖南省常德市石门县');
INSERT INTO `district` VALUES ('430781', '湖南省常德市津市市');
INSERT INTO `district` VALUES ('430800', '湖南省张家界市');
INSERT INTO `district` VALUES ('430801', '湖南省张家界市市辖区');
INSERT INTO `district` VALUES ('430802', '湖南省张家界市永定区');
INSERT INTO `district` VALUES ('430811', '湖南省张家界市武陵源区');
INSERT INTO `district` VALUES ('430821', '湖南省张家界市慈利县');
INSERT INTO `district` VALUES ('430822', '湖南省张家界市桑植县');
INSERT INTO `district` VALUES ('430900', '湖南省益阳市');
INSERT INTO `district` VALUES ('430901', '湖南省益阳市市辖区');
INSERT INTO `district` VALUES ('430902', '湖南省益阳市资阳区');
INSERT INTO `district` VALUES ('430903', '湖南省益阳市赫山区');
INSERT INTO `district` VALUES ('430921', '湖南省益阳市南县');
INSERT INTO `district` VALUES ('430922', '湖南省益阳市桃江县');
INSERT INTO `district` VALUES ('430923', '湖南省益阳市安化县');
INSERT INTO `district` VALUES ('430981', '湖南省益阳市沅江市');
INSERT INTO `district` VALUES ('431000', '湖南省郴州市');
INSERT INTO `district` VALUES ('431001', '湖南省郴州市市辖区');
INSERT INTO `district` VALUES ('431002', '湖南省郴州市北湖区');
INSERT INTO `district` VALUES ('431003', '湖南省郴州市苏仙区');
INSERT INTO `district` VALUES ('431021', '湖南省郴州市桂阳县');
INSERT INTO `district` VALUES ('431022', '湖南省郴州市宜章县');
INSERT INTO `district` VALUES ('431023', '湖南省郴州市永兴县');
INSERT INTO `district` VALUES ('431024', '湖南省郴州市嘉禾县');
INSERT INTO `district` VALUES ('431025', '湖南省郴州市临武县');
INSERT INTO `district` VALUES ('431026', '湖南省郴州市汝城县');
INSERT INTO `district` VALUES ('431027', '湖南省郴州市桂东县');
INSERT INTO `district` VALUES ('431028', '湖南省郴州市安仁县');
INSERT INTO `district` VALUES ('431081', '湖南省郴州市资兴市');
INSERT INTO `district` VALUES ('431100', '湖南省永州市');
INSERT INTO `district` VALUES ('431101', '湖南省永州市市辖区');
INSERT INTO `district` VALUES ('431102', '湖南省永州市芝山区');
INSERT INTO `district` VALUES ('431103', '湖南省永州市冷水滩区');
INSERT INTO `district` VALUES ('431121', '湖南省永州市祁阳县');
INSERT INTO `district` VALUES ('431122', '湖南省永州市东安县');
INSERT INTO `district` VALUES ('431123', '湖南省永州市双牌县');
INSERT INTO `district` VALUES ('431124', '湖南省永州市道县');
INSERT INTO `district` VALUES ('431125', '湖南省永州市江永县');
INSERT INTO `district` VALUES ('431126', '湖南省永州市宁远县');
INSERT INTO `district` VALUES ('431127', '湖南省永州市蓝山县');
INSERT INTO `district` VALUES ('431128', '湖南省永州市新田县');
INSERT INTO `district` VALUES ('431129', '湖南省永州市江华瑶族自治县');
INSERT INTO `district` VALUES ('431200', '湖南省怀化市');
INSERT INTO `district` VALUES ('431201', '湖南省怀化市市辖区');
INSERT INTO `district` VALUES ('431202', '湖南省怀化市鹤城区');
INSERT INTO `district` VALUES ('431221', '湖南省怀化市中方县');
INSERT INTO `district` VALUES ('431222', '湖南省怀化市沅陵县');
INSERT INTO `district` VALUES ('431223', '湖南省怀化市辰溪县');
INSERT INTO `district` VALUES ('431224', '湖南省怀化市溆浦县');
INSERT INTO `district` VALUES ('431225', '湖南省怀化市会同县');
INSERT INTO `district` VALUES ('431226', '湖南省怀化市麻阳苗族自治县');
INSERT INTO `district` VALUES ('431227', '湖南省怀化市新晃侗族自治县');
INSERT INTO `district` VALUES ('431228', '湖南省怀化市芷江侗族自治县');
INSERT INTO `district` VALUES ('431229', '湖南省怀化市靖州苗族侗族自治县');
INSERT INTO `district` VALUES ('431230', '湖南省怀化市通道侗族自治县');
INSERT INTO `district` VALUES ('431281', '湖南省怀化市洪江市');
INSERT INTO `district` VALUES ('431300', '湖南省娄底市');
INSERT INTO `district` VALUES ('431301', '湖南省娄底市市辖区');
INSERT INTO `district` VALUES ('431302', '湖南省娄底市娄星区');
INSERT INTO `district` VALUES ('431321', '湖南省娄底市双峰县');
INSERT INTO `district` VALUES ('431322', '湖南省娄底市新化县');
INSERT INTO `district` VALUES ('431381', '湖南省娄底市冷水江市');
INSERT INTO `district` VALUES ('431382', '湖南省娄底市涟源市');
INSERT INTO `district` VALUES ('432500', '湖南省娄底地区');
INSERT INTO `district` VALUES ('432501', '湖南省娄底地区娄底市');
INSERT INTO `district` VALUES ('432502', '湖南省娄底地区冷水江市');
INSERT INTO `district` VALUES ('432503', '湖南省娄底地区涟源市');
INSERT INTO `district` VALUES ('432522', '湖南省娄底地区双峰县');
INSERT INTO `district` VALUES ('432524', '湖南省娄底地区新化县');
INSERT INTO `district` VALUES ('433100', '湖南省湘西土家族苗族自治州');
INSERT INTO `district` VALUES ('433101', '湖南省湘西土家族苗族自治州吉首市');
INSERT INTO `district` VALUES ('433122', '湖南省湘西土家族苗族自治州泸溪县');
INSERT INTO `district` VALUES ('433123', '湖南省湘西土家族苗族自治州凤凰县');
INSERT INTO `district` VALUES ('433124', '湖南省湘西土家族苗族自治州花垣县');
INSERT INTO `district` VALUES ('433125', '湖南省湘西土家族苗族自治州保靖县');
INSERT INTO `district` VALUES ('433126', '湖南省湘西土家族苗族自治州古丈县');
INSERT INTO `district` VALUES ('433127', '湖南省湘西土家族苗族自治州永顺县');
INSERT INTO `district` VALUES ('433130', '湖南省湘西土家族苗族自治州龙山县');
INSERT INTO `district` VALUES ('440000', '广东省');
INSERT INTO `district` VALUES ('440100', '广东省广州市');
INSERT INTO `district` VALUES ('440101', '广东省广州市市辖区');
INSERT INTO `district` VALUES ('440102', '广东省广州市东山区');
INSERT INTO `district` VALUES ('440103', '广东省广州市荔湾区');
INSERT INTO `district` VALUES ('440104', '广东省广州市越秀区');
INSERT INTO `district` VALUES ('440105', '广东省广州市海珠区');
INSERT INTO `district` VALUES ('440106', '广东省广州市天河区');
INSERT INTO `district` VALUES ('440107', '广东省广州市芳村区');
INSERT INTO `district` VALUES ('440111', '广东省广州市白云区');
INSERT INTO `district` VALUES ('440112', '广东省广州市黄埔区');
INSERT INTO `district` VALUES ('440113', '广东省广州市番禺区');
INSERT INTO `district` VALUES ('440114', '广东省广州市花都区');
INSERT INTO `district` VALUES ('440181', '广东省广州市番禺市');
INSERT INTO `district` VALUES ('440182', '广东省广州市花都市');
INSERT INTO `district` VALUES ('440183', '广东省广州市增城市');
INSERT INTO `district` VALUES ('440184', '广东省广州市从化市');
INSERT INTO `district` VALUES ('440200', '广东省韶关市');
INSERT INTO `district` VALUES ('440201', '广东省韶关市市辖区');
INSERT INTO `district` VALUES ('440202', '广东省韶关市北江区');
INSERT INTO `district` VALUES ('440203', '广东省韶关市武江区');
INSERT INTO `district` VALUES ('440204', '广东省韶关市浈江区');
INSERT INTO `district` VALUES ('440221', '广东省韶关市曲江县');
INSERT INTO `district` VALUES ('440222', '广东省韶关市始兴县');
INSERT INTO `district` VALUES ('440224', '广东省韶关市仁化县');
INSERT INTO `district` VALUES ('440229', '广东省韶关市翁源县');
INSERT INTO `district` VALUES ('440232', '广东省韶关市乳源瑶族自治县');
INSERT INTO `district` VALUES ('440233', '广东省韶关市新丰县');
INSERT INTO `district` VALUES ('440281', '广东省韶关市乐昌市');
INSERT INTO `district` VALUES ('440282', '广东省韶关市南雄市');
INSERT INTO `district` VALUES ('440300', '广东省深圳市');
INSERT INTO `district` VALUES ('440301', '广东省深圳市市辖区');
INSERT INTO `district` VALUES ('440303', '广东省深圳市罗湖区');
INSERT INTO `district` VALUES ('440304', '广东省深圳市福田区');
INSERT INTO `district` VALUES ('440305', '广东省深圳市南山区');
INSERT INTO `district` VALUES ('440306', '广东省深圳市宝安区');
INSERT INTO `district` VALUES ('440307', '广东省深圳市龙岗区');
INSERT INTO `district` VALUES ('440308', '广东省深圳市盐田区');
INSERT INTO `district` VALUES ('440400', '广东省珠海市');
INSERT INTO `district` VALUES ('440401', '广东省珠海市市辖区');
INSERT INTO `district` VALUES ('440402', '广东省珠海市香洲区');
INSERT INTO `district` VALUES ('440403', '广东省珠海市斗门区');
INSERT INTO `district` VALUES ('440404', '广东省珠海市金湾区');
INSERT INTO `district` VALUES ('440421', '广东省珠海市斗门县');
INSERT INTO `district` VALUES ('440500', '广东省汕头市');
INSERT INTO `district` VALUES ('440501', '广东省汕头市市辖区');
INSERT INTO `district` VALUES ('440506', '广东省汕头市达濠区');
INSERT INTO `district` VALUES ('440507', '广东省汕头市龙湖区');
INSERT INTO `district` VALUES ('440508', '广东省汕头市金园区');
INSERT INTO `district` VALUES ('440509', '广东省汕头市升平区');
INSERT INTO `district` VALUES ('440510', '广东省汕头市河浦区');
INSERT INTO `district` VALUES ('440511', '广东省汕头市金平区');
INSERT INTO `district` VALUES ('440512', '广东省汕头市濠江区');
INSERT INTO `district` VALUES ('440513', '广东省汕头市潮阳区');
INSERT INTO `district` VALUES ('440514', '广东省汕头市潮南区');
INSERT INTO `district` VALUES ('440515', '广东省汕头市澄海区');
INSERT INTO `district` VALUES ('440523', '广东省汕头市南澳县');
INSERT INTO `district` VALUES ('440582', '广东省汕头市潮阳市');
INSERT INTO `district` VALUES ('440583', '广东省汕头市澄海市');
INSERT INTO `district` VALUES ('440600', '广东省佛山市');
INSERT INTO `district` VALUES ('440601', '广东省佛山市市辖区');
INSERT INTO `district` VALUES ('440602', '广东省佛山市城区');
INSERT INTO `district` VALUES ('440603', '广东省佛山市石湾区');
INSERT INTO `district` VALUES ('440604', '广东省佛山市禅城区');
INSERT INTO `district` VALUES ('440605', '广东省佛山市南海区');
INSERT INTO `district` VALUES ('440606', '广东省佛山市顺德区');
INSERT INTO `district` VALUES ('440607', '广东省佛山市三水区');
INSERT INTO `district` VALUES ('440608', '广东省佛山市高明区');
INSERT INTO `district` VALUES ('440681', '广东省佛山市顺德市');
INSERT INTO `district` VALUES ('440682', '广东省佛山市南海市');
INSERT INTO `district` VALUES ('440683', '广东省佛山市三水市');
INSERT INTO `district` VALUES ('440684', '广东省佛山市高明市');
INSERT INTO `district` VALUES ('440700', '广东省江门市');
INSERT INTO `district` VALUES ('440701', '广东省江门市市辖区');
INSERT INTO `district` VALUES ('440703', '广东省江门市蓬江区');
INSERT INTO `district` VALUES ('440704', '广东省江门市江海区');
INSERT INTO `district` VALUES ('440705', '广东省江门市新会区');
INSERT INTO `district` VALUES ('440781', '广东省江门市台山市');
INSERT INTO `district` VALUES ('440782', '广东省江门市新会市');
INSERT INTO `district` VALUES ('440783', '广东省江门市开平市');
INSERT INTO `district` VALUES ('440784', '广东省江门市鹤山市');
INSERT INTO `district` VALUES ('440785', '广东省江门市恩平市');
INSERT INTO `district` VALUES ('440800', '广东省湛江市');
INSERT INTO `district` VALUES ('440801', '广东省湛江市市辖区');
INSERT INTO `district` VALUES ('440802', '广东省湛江市赤坎区');
INSERT INTO `district` VALUES ('440803', '广东省湛江市霞山区');
INSERT INTO `district` VALUES ('440804', '广东省湛江市坡头区');
INSERT INTO `district` VALUES ('440811', '广东省湛江市麻章区');
INSERT INTO `district` VALUES ('440823', '广东省湛江市遂溪县');
INSERT INTO `district` VALUES ('440825', '广东省湛江市徐闻县');
INSERT INTO `district` VALUES ('440881', '广东省湛江市廉江市');
INSERT INTO `district` VALUES ('440882', '广东省湛江市雷州市');
INSERT INTO `district` VALUES ('440883', '广东省湛江市吴川市');
INSERT INTO `district` VALUES ('440900', '广东省茂名市');
INSERT INTO `district` VALUES ('440901', '广东省茂名市市辖区');
INSERT INTO `district` VALUES ('440902', '广东省茂名市茂南区');
INSERT INTO `district` VALUES ('440903', '广东省茂名市茂港区');
INSERT INTO `district` VALUES ('440923', '广东省茂名市电白县');
INSERT INTO `district` VALUES ('440981', '广东省茂名市高州市');
INSERT INTO `district` VALUES ('440982', '广东省茂名市化州市');
INSERT INTO `district` VALUES ('440983', '广东省茂名市信宜市');
INSERT INTO `district` VALUES ('441200', '广东省肇庆市');
INSERT INTO `district` VALUES ('441201', '广东省肇庆市市辖区');
INSERT INTO `district` VALUES ('441202', '广东省肇庆市端州区');
INSERT INTO `district` VALUES ('441203', '广东省肇庆市鼎湖区');
INSERT INTO `district` VALUES ('441223', '广东省肇庆市广宁县');
INSERT INTO `district` VALUES ('441224', '广东省肇庆市怀集县');
INSERT INTO `district` VALUES ('441225', '广东省肇庆市封开县');
INSERT INTO `district` VALUES ('441226', '广东省肇庆市德庆县');
INSERT INTO `district` VALUES ('441283', '广东省肇庆市高要市');
INSERT INTO `district` VALUES ('441284', '广东省肇庆市四会市');
INSERT INTO `district` VALUES ('441300', '广东省惠州市');
INSERT INTO `district` VALUES ('441301', '广东省惠州市市辖区');
INSERT INTO `district` VALUES ('441302', '广东省惠州市惠城区');
INSERT INTO `district` VALUES ('441303', '广东省惠州市惠阳区');
INSERT INTO `district` VALUES ('441322', '广东省惠州市博罗县');
INSERT INTO `district` VALUES ('441323', '广东省惠州市惠东县');
INSERT INTO `district` VALUES ('441324', '广东省惠州市龙门县');
INSERT INTO `district` VALUES ('441381', '广东省惠州市惠阳市');
INSERT INTO `district` VALUES ('441400', '广东省梅州市');
INSERT INTO `district` VALUES ('441401', '广东省梅州市市辖区');
INSERT INTO `district` VALUES ('441402', '广东省梅州市梅江区');
INSERT INTO `district` VALUES ('441421', '广东省梅州市梅县');
INSERT INTO `district` VALUES ('441422', '广东省梅州市大埔县');
INSERT INTO `district` VALUES ('441423', '广东省梅州市丰顺县');
INSERT INTO `district` VALUES ('441424', '广东省梅州市五华县');
INSERT INTO `district` VALUES ('441425', '广东省梅州市兴宁市');
INSERT INTO `district` VALUES ('441426', '广东省梅州市平远县');
INSERT INTO `district` VALUES ('441427', '广东省梅州市蕉岭县');
INSERT INTO `district` VALUES ('441481', '广东省梅州市兴宁市');
INSERT INTO `district` VALUES ('441500', '广东省汕尾市');
INSERT INTO `district` VALUES ('441501', '广东省汕尾市市辖区');
INSERT INTO `district` VALUES ('441502', '广东省汕尾市城区');
INSERT INTO `district` VALUES ('441521', '广东省汕尾市海丰县');
INSERT INTO `district` VALUES ('441523', '广东省汕尾市陆河县');
INSERT INTO `district` VALUES ('441581', '广东省汕尾市陆丰市');
INSERT INTO `district` VALUES ('441600', '广东省河源市');
INSERT INTO `district` VALUES ('441601', '广东省河源市市辖区');
INSERT INTO `district` VALUES ('441602', '广东省河源市源城区');
INSERT INTO `district` VALUES ('441621', '广东省河源市紫金县');
INSERT INTO `district` VALUES ('441622', '广东省河源市龙川县');
INSERT INTO `district` VALUES ('441623', '广东省河源市连平县');
INSERT INTO `district` VALUES ('441624', '广东省河源市和平县');
INSERT INTO `district` VALUES ('441625', '广东省河源市东源县');
INSERT INTO `district` VALUES ('441700', '广东省阳江市');
INSERT INTO `district` VALUES ('441701', '广东省阳江市市辖区');
INSERT INTO `district` VALUES ('441702', '广东省阳江市江城区');
INSERT INTO `district` VALUES ('441721', '广东省阳江市阳西县');
INSERT INTO `district` VALUES ('441723', '广东省阳江市阳东县');
INSERT INTO `district` VALUES ('441781', '广东省阳江市阳春市');
INSERT INTO `district` VALUES ('441800', '广东省清远市');
INSERT INTO `district` VALUES ('441801', '广东省清远市市辖区');
INSERT INTO `district` VALUES ('441802', '广东省清远市清城区');
INSERT INTO `district` VALUES ('441821', '广东省清远市佛冈县');
INSERT INTO `district` VALUES ('441823', '广东省清远市阳山县');
INSERT INTO `district` VALUES ('441825', '广东省清远市连山壮族瑶族自治县');
INSERT INTO `district` VALUES ('441826', '广东省清远市连南瑶族自治县');
INSERT INTO `district` VALUES ('441827', '广东省清远市清新县');
INSERT INTO `district` VALUES ('441881', '广东省清远市英德市');
INSERT INTO `district` VALUES ('441882', '广东省清远市连州市');
INSERT INTO `district` VALUES ('441900', '广东省东莞市');
INSERT INTO `district` VALUES ('442000', '广东省中山市');
INSERT INTO `district` VALUES ('445100', '广东省潮州市');
INSERT INTO `district` VALUES ('445101', '广东省潮州市市辖区');
INSERT INTO `district` VALUES ('445102', '广东省潮州市湘桥区');
INSERT INTO `district` VALUES ('445121', '广东省潮州市潮安县');
INSERT INTO `district` VALUES ('445122', '广东省潮州市饶平县');
INSERT INTO `district` VALUES ('445200', '广东省揭阳市');
INSERT INTO `district` VALUES ('445201', '广东省揭阳市市辖区');
INSERT INTO `district` VALUES ('445202', '广东省揭阳市榕城区');
INSERT INTO `district` VALUES ('445221', '广东省揭阳市揭东县');
INSERT INTO `district` VALUES ('445222', '广东省揭阳市揭西县');
INSERT INTO `district` VALUES ('445224', '广东省揭阳市惠来县');
INSERT INTO `district` VALUES ('445281', '广东省揭阳市普宁市');
INSERT INTO `district` VALUES ('445300', '广东省云浮市');
INSERT INTO `district` VALUES ('445301', '广东省云浮市市辖区');
INSERT INTO `district` VALUES ('445302', '广东省云浮市云城区');
INSERT INTO `district` VALUES ('445321', '广东省云浮市新兴县');
INSERT INTO `district` VALUES ('445322', '广东省云浮市郁南县');
INSERT INTO `district` VALUES ('445323', '广东省云浮市云安县');
INSERT INTO `district` VALUES ('445381', '广东省云浮市罗定市');
INSERT INTO `district` VALUES ('450000', '广西');
INSERT INTO `district` VALUES ('450100', '广西壮族自治区南宁市');
INSERT INTO `district` VALUES ('450101', '广西壮族自治区南宁市市辖区');
INSERT INTO `district` VALUES ('450102', '广西壮族自治区南宁市兴宁区');
INSERT INTO `district` VALUES ('450103', '广西壮族自治区南宁市新城区');
INSERT INTO `district` VALUES ('450104', '广西壮族自治区南宁市城北区');
INSERT INTO `district` VALUES ('450105', '广西壮族自治区南宁市江南区');
INSERT INTO `district` VALUES ('450106', '广西壮族自治区南宁市永新区');
INSERT INTO `district` VALUES ('450121', '广西壮族自治区南宁市邕宁县');
INSERT INTO `district` VALUES ('450122', '广西壮族自治区南宁市武鸣县');
INSERT INTO `district` VALUES ('450123', '广西壮族自治区南宁市隆安县');
INSERT INTO `district` VALUES ('450124', '广西壮族自治区南宁市马山县');
INSERT INTO `district` VALUES ('450125', '广西壮族自治区南宁市上林县');
INSERT INTO `district` VALUES ('450126', '广西壮族自治区南宁市宾阳县');
INSERT INTO `district` VALUES ('450127', '广西壮族自治区南宁市横县');
INSERT INTO `district` VALUES ('450200', '广西壮族自治区柳州市');
INSERT INTO `district` VALUES ('450201', '广西壮族自治区柳州市市辖区');
INSERT INTO `district` VALUES ('450202', '广西壮族自治区柳州市城中区');
INSERT INTO `district` VALUES ('450203', '广西壮族自治区柳州市鱼峰区');
INSERT INTO `district` VALUES ('450204', '广西壮族自治区柳州市柳南区');
INSERT INTO `district` VALUES ('450205', '广西壮族自治区柳州市柳北区');
INSERT INTO `district` VALUES ('450211', '广西壮族自治区柳州市市郊区');
INSERT INTO `district` VALUES ('450221', '广西壮族自治区柳州市柳江县');
INSERT INTO `district` VALUES ('450222', '广西壮族自治区柳州市柳城县');
INSERT INTO `district` VALUES ('450223', '广西壮族自治区柳州市鹿寨县');
INSERT INTO `district` VALUES ('450224', '广西壮族自治区柳州市融安县');
INSERT INTO `district` VALUES ('450225', '广西壮族自治区柳州市融水苗族自治县');
INSERT INTO `district` VALUES ('450226', '广西壮族自治区柳州市三江侗族自治县');
INSERT INTO `district` VALUES ('450300', '广西壮族自治区桂林市');
INSERT INTO `district` VALUES ('450301', '广西壮族自治区桂林市市辖区');
INSERT INTO `district` VALUES ('450302', '广西壮族自治区桂林市秀峰区');
INSERT INTO `district` VALUES ('450303', '广西壮族自治区桂林市叠彩区');
INSERT INTO `district` VALUES ('450304', '广西壮族自治区桂林市象山区');
INSERT INTO `district` VALUES ('450305', '广西壮族自治区桂林市七星区');
INSERT INTO `district` VALUES ('450311', '广西壮族自治区桂林市雁山区');
INSERT INTO `district` VALUES ('450321', '广西壮族自治区桂林市阳朔县');
INSERT INTO `district` VALUES ('450322', '广西壮族自治区桂林市临桂县');
INSERT INTO `district` VALUES ('450323', '广西壮族自治区桂林市灵川县');
INSERT INTO `district` VALUES ('450324', '广西壮族自治区桂林市全州县');
INSERT INTO `district` VALUES ('450325', '广西壮族自治区桂林市兴安县');
INSERT INTO `district` VALUES ('450326', '广西壮族自治区桂林市永福县');
INSERT INTO `district` VALUES ('450327', '广西壮族自治区桂林市灌阳县');
INSERT INTO `district` VALUES ('450328', '广西壮族自治区桂林市龙胜各族自治县');
INSERT INTO `district` VALUES ('450329', '广西壮族自治区桂林市资源县');
INSERT INTO `district` VALUES ('450330', '广西壮族自治区桂林市平乐县');
INSERT INTO `district` VALUES ('450331', '广西壮族自治区桂林市荔蒲县');
INSERT INTO `district` VALUES ('450332', '广西壮族自治区桂林市恭城瑶族自治县');
INSERT INTO `district` VALUES ('450400', '广西壮族自治区梧州市');
INSERT INTO `district` VALUES ('450401', '广西壮族自治区梧州市市辖区');
INSERT INTO `district` VALUES ('450403', '广西壮族自治区梧州市万秀区');
INSERT INTO `district` VALUES ('450404', '广西壮族自治区梧州市蝶山区');
INSERT INTO `district` VALUES ('450405', '广西壮族自治区梧州市长洲区');
INSERT INTO `district` VALUES ('450411', '广西壮族自治区梧州市市郊区');
INSERT INTO `district` VALUES ('450421', '广西壮族自治区梧州市苍梧县');
INSERT INTO `district` VALUES ('450422', '广西壮族自治区梧州市藤县');
INSERT INTO `district` VALUES ('450423', '广西壮族自治区梧州市蒙山县');
INSERT INTO `district` VALUES ('450481', '广西壮族自治区梧州市岑溪市');
INSERT INTO `district` VALUES ('450500', '广西壮族自治区北海市');
INSERT INTO `district` VALUES ('450501', '广西壮族自治区北海市市辖区');
INSERT INTO `district` VALUES ('450502', '广西壮族自治区北海市海城区');
INSERT INTO `district` VALUES ('450503', '广西壮族自治区北海市银海区');
INSERT INTO `district` VALUES ('450512', '广西壮族自治区北海市铁山港区');
INSERT INTO `district` VALUES ('450521', '广西壮族自治区北海市合浦县');
INSERT INTO `district` VALUES ('450600', '广西壮族自治区防城港市');
INSERT INTO `district` VALUES ('450601', '广西壮族自治区防城港市市辖区');
INSERT INTO `district` VALUES ('450602', '广西壮族自治区防城港市港口区');
INSERT INTO `district` VALUES ('450603', '广西壮族自治区防城港市防城区');
INSERT INTO `district` VALUES ('450621', '广西壮族自治区防城港市上思县');
INSERT INTO `district` VALUES ('450681', '广西壮族自治区防城港市东兴市');
INSERT INTO `district` VALUES ('450700', '广西壮族自治区钦州市');
INSERT INTO `district` VALUES ('450701', '广西壮族自治区钦州市市辖区');
INSERT INTO `district` VALUES ('450702', '广西壮族自治区钦州市钦南区');
INSERT INTO `district` VALUES ('450703', '广西壮族自治区钦州市钦北区');
INSERT INTO `district` VALUES ('450721', '广西壮族自治区钦州市灵山县');
INSERT INTO `district` VALUES ('450722', '广西壮族自治区钦州市浦北县');
INSERT INTO `district` VALUES ('450800', '广西壮族自治区贵港市');
INSERT INTO `district` VALUES ('450801', '广西壮族自治区贵港市市辖区');
INSERT INTO `district` VALUES ('450802', '广西壮族自治区贵港市港北区');
INSERT INTO `district` VALUES ('450803', '广西壮族自治区贵港市港南区');
INSERT INTO `district` VALUES ('450804', '广西壮族自治区贵港市覃塘区');
INSERT INTO `district` VALUES ('450821', '广西壮族自治区贵港市平南县');
INSERT INTO `district` VALUES ('450881', '广西壮族自治区贵港市桂平市');
INSERT INTO `district` VALUES ('450900', '广西壮族自治区玉林市');
INSERT INTO `district` VALUES ('450901', '广西壮族自治区玉林市市辖区');
INSERT INTO `district` VALUES ('450902', '广西壮族自治区玉林市玉州区');
INSERT INTO `district` VALUES ('450921', '广西壮族自治区玉林市容县');
INSERT INTO `district` VALUES ('450922', '广西壮族自治区玉林市陆川县');
INSERT INTO `district` VALUES ('450923', '广西壮族自治区玉林市博白县');
INSERT INTO `district` VALUES ('450924', '广西壮族自治区玉林市兴业县');
INSERT INTO `district` VALUES ('450981', '广西壮族自治区玉林市北流市');
INSERT INTO `district` VALUES ('451000', '广西壮族自治区百色市');
INSERT INTO `district` VALUES ('451001', '广西壮族自治区百色市市辖区');
INSERT INTO `district` VALUES ('451002', '广西壮族自治区百色市右江区');
INSERT INTO `district` VALUES ('451021', '广西壮族自治区百色市田阳县');
INSERT INTO `district` VALUES ('451022', '广西壮族自治区百色市田东县');
INSERT INTO `district` VALUES ('451023', '广西壮族自治区百色市平果县');
INSERT INTO `district` VALUES ('451024', '广西壮族自治区百色市德保县');
INSERT INTO `district` VALUES ('451025', '广西壮族自治区百色市靖西县');
INSERT INTO `district` VALUES ('451026', '广西壮族自治区百色市那坡县');
INSERT INTO `district` VALUES ('451027', '广西壮族自治区百色市凌云县');
INSERT INTO `district` VALUES ('451028', '广西壮族自治区百色市乐业县');
INSERT INTO `district` VALUES ('451029', '广西壮族自治区百色市田林县');
INSERT INTO `district` VALUES ('451030', '广西壮族自治区百色市西林县');
INSERT INTO `district` VALUES ('451031', '广西壮族自治区百色市隆林各族自治县');
INSERT INTO `district` VALUES ('451100', '广西壮族自治区贺州市');
INSERT INTO `district` VALUES ('451101', '广西壮族自治区贺州市市辖区');
INSERT INTO `district` VALUES ('451102', '广西壮族自治区贺州市八步区');
INSERT INTO `district` VALUES ('451121', '广西壮族自治区贺州市昭平县');
INSERT INTO `district` VALUES ('451122', '广西壮族自治区贺州市钟山县');
INSERT INTO `district` VALUES ('451123', '广西壮族自治区贺州市富川瑶族自治县');
INSERT INTO `district` VALUES ('451200', '广西壮族自治区河池市');
INSERT INTO `district` VALUES ('451201', '广西壮族自治区河池市市辖区');
INSERT INTO `district` VALUES ('451202', '广西壮族自治区河池市金城江区');
INSERT INTO `district` VALUES ('451221', '广西壮族自治区河池市南丹县');
INSERT INTO `district` VALUES ('451222', '广西壮族自治区河池市天峨县');
INSERT INTO `district` VALUES ('451223', '广西壮族自治区河池市凤山县');
INSERT INTO `district` VALUES ('451224', '广西壮族自治区河池市东兰县');
INSERT INTO `district` VALUES ('451225', '广西壮族自治区河池市罗城仫佬族自治县');
INSERT INTO `district` VALUES ('451226', '广西壮族自治区河池市环江毛南族自治县');
INSERT INTO `district` VALUES ('451227', '广西壮族自治区河池市巴马瑶族自治县');
INSERT INTO `district` VALUES ('451228', '广西壮族自治区河池市都安瑶族自治县');
INSERT INTO `district` VALUES ('451229', '广西壮族自治区河池市大化瑶族自治县');
INSERT INTO `district` VALUES ('451281', '广西壮族自治区河池市宜州市');
INSERT INTO `district` VALUES ('451300', '广西壮族自治区来宾市');
INSERT INTO `district` VALUES ('451301', '广西壮族自治区来宾市市辖区');
INSERT INTO `district` VALUES ('451302', '广西壮族自治区来宾市兴宾区');
INSERT INTO `district` VALUES ('451321', '广西壮族自治区来宾市忻城县');
INSERT INTO `district` VALUES ('451322', '广西壮族自治区来宾市象州县');
INSERT INTO `district` VALUES ('451323', '广西壮族自治区来宾市武宣县');
INSERT INTO `district` VALUES ('451324', '广西壮族自治区来宾市金秀瑶族自治县');
INSERT INTO `district` VALUES ('451381', '广西壮族自治区来宾市合山市');
INSERT INTO `district` VALUES ('451400', '广西壮族自治区崇左市');
INSERT INTO `district` VALUES ('451401', '广西壮族自治区崇左市市辖区');
INSERT INTO `district` VALUES ('451402', '广西壮族自治区崇左市江洲区');
INSERT INTO `district` VALUES ('451421', '广西壮族自治区崇左市扶绥县');
INSERT INTO `district` VALUES ('451422', '广西壮族自治区崇左市宁明县');
INSERT INTO `district` VALUES ('451423', '广西壮族自治区崇左市龙州县');
INSERT INTO `district` VALUES ('451424', '广西壮族自治区崇左市大新县');
INSERT INTO `district` VALUES ('451425', '广西壮族自治区崇左市天等县');
INSERT INTO `district` VALUES ('451481', '广西壮族自治区崇左市凭祥市');
INSERT INTO `district` VALUES ('452100', '广西壮族自治区南宁地区');
INSERT INTO `district` VALUES ('452101', '广西壮族自治区南宁地区凭祥市');
INSERT INTO `district` VALUES ('452122', '广西壮族自治区南宁地区横县');
INSERT INTO `district` VALUES ('452123', '广西壮族自治区南宁地区宾阳县');
INSERT INTO `district` VALUES ('452124', '广西壮族自治区南宁地区上林县');
INSERT INTO `district` VALUES ('452126', '广西壮族自治区南宁地区隆安县');
INSERT INTO `district` VALUES ('452127', '广西壮族自治区南宁地区马山县');
INSERT INTO `district` VALUES ('452128', '广西壮族自治区南宁地区扶绥县');
INSERT INTO `district` VALUES ('452129', '广西壮族自治区南宁地区崇左县');
INSERT INTO `district` VALUES ('452130', '广西壮族自治区南宁地区大新县');
INSERT INTO `district` VALUES ('452131', '广西壮族自治区南宁地区天等县');
INSERT INTO `district` VALUES ('452132', '广西壮族自治区南宁地区宁明县');
INSERT INTO `district` VALUES ('452133', '广西壮族自治区南宁地区龙州县');
INSERT INTO `district` VALUES ('452200', '广西壮族自治区柳州地区');
INSERT INTO `district` VALUES ('452201', '广西壮族自治区柳州地区合山市');
INSERT INTO `district` VALUES ('452223', '广西壮族自治区柳州地区鹿寨县');
INSERT INTO `district` VALUES ('452224', '广西壮族自治区柳州地区象州县');
INSERT INTO `district` VALUES ('452225', '广西壮族自治区柳州地区武宣县');
INSERT INTO `district` VALUES ('452226', '广西壮族自治区柳州地区来宾县');
INSERT INTO `district` VALUES ('452227', '广西壮族自治区柳州地区融安县');
INSERT INTO `district` VALUES ('452228', '广西壮族自治区柳州地区三江侗族自治县');
INSERT INTO `district` VALUES ('452229', '广西壮族自治区柳州地区融水苗族自治县');
INSERT INTO `district` VALUES ('452230', '广西壮族自治区柳州地区金秀瑶族自治县');
INSERT INTO `district` VALUES ('452231', '广西壮族自治区柳州地区忻城县');
INSERT INTO `district` VALUES ('452400', '广西壮族自治区贺州地区');
INSERT INTO `district` VALUES ('452402', '广西壮族自治区贺州地区贺州市');
INSERT INTO `district` VALUES ('452424', '广西壮族自治区贺州地区昭平县');
INSERT INTO `district` VALUES ('452427', '广西壮族自治区贺州地区钟山县');
INSERT INTO `district` VALUES ('452428', '广西壮族自治区贺州地区富川瑶族自治县');
INSERT INTO `district` VALUES ('452600', '广西壮族自治区百色地区');
INSERT INTO `district` VALUES ('452601', '广西壮族自治区百色地区百色市');
INSERT INTO `district` VALUES ('452622', '广西壮族自治区百色地区田阳县');
INSERT INTO `district` VALUES ('452623', '广西壮族自治区百色地区田东县');
INSERT INTO `district` VALUES ('452624', '广西壮族自治区百色地区平果县');
INSERT INTO `district` VALUES ('452625', '广西壮族自治区百色地区德保县');
INSERT INTO `district` VALUES ('452626', '广西壮族自治区百色地区靖西县');
INSERT INTO `district` VALUES ('452627', '广西壮族自治区百色地区那坡县');
INSERT INTO `district` VALUES ('452628', '广西壮族自治区百色地区凌云县');
INSERT INTO `district` VALUES ('452629', '广西壮族自治区百色地区乐业县');
INSERT INTO `district` VALUES ('452630', '广西壮族自治区百色地区田林县');
INSERT INTO `district` VALUES ('452631', '广西壮族自治区百色地区隆林各族自治县');
INSERT INTO `district` VALUES ('452632', '广西壮族自治区百色地区西林县');
INSERT INTO `district` VALUES ('452700', '广西壮族自治区河池地区');
INSERT INTO `district` VALUES ('452701', '广西壮族自治区河池地区河池市');
INSERT INTO `district` VALUES ('452702', '广西壮族自治区河池地区宜州市');
INSERT INTO `district` VALUES ('452723', '广西壮族自治区河池地区罗城仫佬族自治县');
INSERT INTO `district` VALUES ('452724', '广西壮族自治区河池地区环江毛南族自治县');
INSERT INTO `district` VALUES ('452725', '广西壮族自治区河池地区南丹县');
INSERT INTO `district` VALUES ('452726', '广西壮族自治区河池地区天峨县');
INSERT INTO `district` VALUES ('452727', '广西壮族自治区河池地区凤山县');
INSERT INTO `district` VALUES ('452728', '广西壮族自治区河池地区东兰县');
INSERT INTO `district` VALUES ('452729', '广西壮族自治区河池地区巴马瑶族自治县');
INSERT INTO `district` VALUES ('452730', '广西壮族自治区河池地区都安瑶族自治县');
INSERT INTO `district` VALUES ('452731', '广西壮族自治区河池地区大化瑶族自治县');
INSERT INTO `district` VALUES ('460000', '海南省');
INSERT INTO `district` VALUES ('460100', '海南省海口市');
INSERT INTO `district` VALUES ('460101', '海南省海口市市辖区');
INSERT INTO `district` VALUES ('460102', '海南省海口市振东区');
INSERT INTO `district` VALUES ('460103', '海南省海口市新华区');
INSERT INTO `district` VALUES ('460104', '海南省海口市秀英区');
INSERT INTO `district` VALUES ('460105', '海南省海口市秀英区');
INSERT INTO `district` VALUES ('460106', '海南省海口市龙华区');
INSERT INTO `district` VALUES ('460107', '海南省海口市琼山区');
INSERT INTO `district` VALUES ('460108', '海南省海口市美兰区');
INSERT INTO `district` VALUES ('460200', '海南省三亚市');
INSERT INTO `district` VALUES ('460201', '海南省三亚市市辖区');
INSERT INTO `district` VALUES ('500000', '重庆市');
INSERT INTO `district` VALUES ('500101', '重庆市市辖区万州区');
INSERT INTO `district` VALUES ('500102', '重庆市市辖区涪陵区');
INSERT INTO `district` VALUES ('500103', '重庆市市辖区渝中区');
INSERT INTO `district` VALUES ('500104', '重庆市市辖区大渡口区');
INSERT INTO `district` VALUES ('500105', '重庆市市辖区江北区');
INSERT INTO `district` VALUES ('500106', '重庆市市辖区沙坪坝区');
INSERT INTO `district` VALUES ('500107', '重庆市市辖区九龙坡区');
INSERT INTO `district` VALUES ('500108', '重庆市市辖区南岸区');
INSERT INTO `district` VALUES ('500109', '重庆市市辖区北碚区');
INSERT INTO `district` VALUES ('500110', '重庆市市辖区万盛区');
INSERT INTO `district` VALUES ('500111', '重庆市市辖区双桥区');
INSERT INTO `district` VALUES ('500112', '重庆市市辖区渝北区');
INSERT INTO `district` VALUES ('500113', '重庆市市辖区巴南区');
INSERT INTO `district` VALUES ('500114', '重庆市市辖区黔江区');
INSERT INTO `district` VALUES ('500115', '重庆市市辖区长寿区');
INSERT INTO `district` VALUES ('510000', '四川省');
INSERT INTO `district` VALUES ('510100', '四川省成都市');
INSERT INTO `district` VALUES ('510101', '四川省成都市市辖区');
INSERT INTO `district` VALUES ('510104', '四川省成都市锦江区');
INSERT INTO `district` VALUES ('510105', '四川省成都市青羊区');
INSERT INTO `district` VALUES ('510106', '四川省成都市金牛区');
INSERT INTO `district` VALUES ('510107', '四川省成都市武侯区');
INSERT INTO `district` VALUES ('510108', '四川省成都市成华区');
INSERT INTO `district` VALUES ('510112', '四川省成都市龙泉驿区');
INSERT INTO `district` VALUES ('510113', '四川省成都市青白江区');
INSERT INTO `district` VALUES ('510114', '四川省成都市新都区');
INSERT INTO `district` VALUES ('510121', '四川省成都市金堂县');
INSERT INTO `district` VALUES ('510122', '四川省成都市双流县');
INSERT INTO `district` VALUES ('510123', '四川省成都市温江县');
INSERT INTO `district` VALUES ('510124', '四川省成都市郫县');
INSERT INTO `district` VALUES ('510125', '四川省成都市新都县');
INSERT INTO `district` VALUES ('510129', '四川省成都市大邑县');
INSERT INTO `district` VALUES ('510131', '四川省成都市蒲江县');
INSERT INTO `district` VALUES ('510132', '四川省成都市新津县');
INSERT INTO `district` VALUES ('510181', '四川省成都市都江堰市');
INSERT INTO `district` VALUES ('510182', '四川省成都市彭州市');
INSERT INTO `district` VALUES ('510183', '四川省成都市邛崃市');
INSERT INTO `district` VALUES ('510184', '四川省成都市崇州市');
INSERT INTO `district` VALUES ('510300', '四川省自贡市');
INSERT INTO `district` VALUES ('510301', '四川省自贡市市辖区');
INSERT INTO `district` VALUES ('510302', '四川省自贡市自流井区');
INSERT INTO `district` VALUES ('510303', '四川省自贡市贡井区');
INSERT INTO `district` VALUES ('510304', '四川省自贡市大安区');
INSERT INTO `district` VALUES ('510311', '四川省自贡市沿滩区');
INSERT INTO `district` VALUES ('510321', '四川省自贡市荣县');
INSERT INTO `district` VALUES ('510322', '四川省自贡市富顺县');
INSERT INTO `district` VALUES ('510400', '四川省攀枝花市');
INSERT INTO `district` VALUES ('510401', '四川省攀枝花市市辖区');
INSERT INTO `district` VALUES ('510402', '四川省攀枝花市东区');
INSERT INTO `district` VALUES ('510403', '四川省攀枝花市西区');
INSERT INTO `district` VALUES ('510411', '四川省攀枝花市仁和区');
INSERT INTO `district` VALUES ('510421', '四川省攀枝花市米易县');
INSERT INTO `district` VALUES ('510422', '四川省攀枝花市盐边县');
INSERT INTO `district` VALUES ('510500', '四川省泸州市');
INSERT INTO `district` VALUES ('510501', '四川省泸州市市辖区');
INSERT INTO `district` VALUES ('510502', '四川省泸州市江阳区');
INSERT INTO `district` VALUES ('510503', '四川省泸州市纳溪区');
INSERT INTO `district` VALUES ('510504', '四川省泸州市龙马潭区');
INSERT INTO `district` VALUES ('510521', '四川省泸州市泸县');
INSERT INTO `district` VALUES ('510522', '四川省泸州市合江县');
INSERT INTO `district` VALUES ('510524', '四川省泸州市叙永县');
INSERT INTO `district` VALUES ('510525', '四川省泸州市古蔺县');
INSERT INTO `district` VALUES ('510600', '四川省德阳市');
INSERT INTO `district` VALUES ('510601', '四川省德阳市市辖区');
INSERT INTO `district` VALUES ('510603', '四川省德阳市旌阳区');
INSERT INTO `district` VALUES ('510623', '四川省德阳市中江县');
INSERT INTO `district` VALUES ('510626', '四川省德阳市罗江县');
INSERT INTO `district` VALUES ('510681', '四川省德阳市广汉市');
INSERT INTO `district` VALUES ('510682', '四川省德阳市什邡市');
INSERT INTO `district` VALUES ('510683', '四川省德阳市绵竹市');
INSERT INTO `district` VALUES ('510700', '四川省绵阳市');
INSERT INTO `district` VALUES ('510701', '四川省绵阳市市辖区');
INSERT INTO `district` VALUES ('510703', '四川省绵阳市涪城区');
INSERT INTO `district` VALUES ('510704', '四川省绵阳市游仙区');
INSERT INTO `district` VALUES ('510722', '四川省绵阳市三台县');
INSERT INTO `district` VALUES ('510723', '四川省绵阳市盐亭县');
INSERT INTO `district` VALUES ('510724', '四川省绵阳市安县');
INSERT INTO `district` VALUES ('510725', '四川省绵阳市梓潼县');
INSERT INTO `district` VALUES ('510726', '四川省绵阳市北川县');
INSERT INTO `district` VALUES ('510727', '四川省绵阳市平武县');
INSERT INTO `district` VALUES ('510781', '四川省绵阳市江油市');
INSERT INTO `district` VALUES ('510800', '四川省广元市');
INSERT INTO `district` VALUES ('510801', '四川省广元市市辖区');
INSERT INTO `district` VALUES ('510802', '四川省广元市市中区');
INSERT INTO `district` VALUES ('510811', '四川省广元市元坝区');
INSERT INTO `district` VALUES ('510812', '四川省广元市朝天区');
INSERT INTO `district` VALUES ('510821', '四川省广元市旺苍县');
INSERT INTO `district` VALUES ('510822', '四川省广元市青川县');
INSERT INTO `district` VALUES ('510823', '四川省广元市剑阁县');
INSERT INTO `district` VALUES ('510824', '四川省广元市苍溪县');
INSERT INTO `district` VALUES ('510900', '四川省遂宁市');
INSERT INTO `district` VALUES ('510901', '四川省遂宁市市辖区');
INSERT INTO `district` VALUES ('510902', '四川省遂宁市市中区');
INSERT INTO `district` VALUES ('510903', '四川省遂宁市船山区');
INSERT INTO `district` VALUES ('510904', '四川省遂宁市安居区');
INSERT INTO `district` VALUES ('510921', '四川省遂宁市蓬溪县');
INSERT INTO `district` VALUES ('510922', '四川省遂宁市射洪县');
INSERT INTO `district` VALUES ('510923', '四川省遂宁市大英县');
INSERT INTO `district` VALUES ('511000', '四川省内江市');
INSERT INTO `district` VALUES ('511001', '四川省内江市市辖区');
INSERT INTO `district` VALUES ('511002', '四川省内江市市中区');
INSERT INTO `district` VALUES ('511011', '四川省内江市东兴区');
INSERT INTO `district` VALUES ('511024', '四川省内江市威远县');
INSERT INTO `district` VALUES ('511025', '四川省内江市资中县');
INSERT INTO `district` VALUES ('511028', '四川省内江市隆昌县');
INSERT INTO `district` VALUES ('511100', '四川省乐山市');
INSERT INTO `district` VALUES ('511101', '四川省乐山市市辖区');
INSERT INTO `district` VALUES ('511102', '四川省乐山市市中区');
INSERT INTO `district` VALUES ('511111', '四川省乐山市沙湾区');
INSERT INTO `district` VALUES ('511112', '四川省乐山市五通桥区');
INSERT INTO `district` VALUES ('511113', '四川省乐山市金口河区');
INSERT INTO `district` VALUES ('511123', '四川省乐山市犍为县');
INSERT INTO `district` VALUES ('511124', '四川省乐山市井研县');
INSERT INTO `district` VALUES ('511126', '四川省乐山市夹江县');
INSERT INTO `district` VALUES ('511129', '四川省乐山市沐川县');
INSERT INTO `district` VALUES ('511132', '四川省乐山市峨边彝族自治县');
INSERT INTO `district` VALUES ('511133', '四川省乐山市马边彝族自治县');
INSERT INTO `district` VALUES ('511181', '四川省乐山市峨眉山市');
INSERT INTO `district` VALUES ('511300', '四川省南充市');
INSERT INTO `district` VALUES ('511301', '四川省南充市市辖区');
INSERT INTO `district` VALUES ('511302', '四川省南充市顺庆区');
INSERT INTO `district` VALUES ('511303', '四川省南充市高坪区');
INSERT INTO `district` VALUES ('511304', '四川省南充市嘉陵区');
INSERT INTO `district` VALUES ('511321', '四川省南充市南部县');
INSERT INTO `district` VALUES ('511322', '四川省南充市营山县');
INSERT INTO `district` VALUES ('511323', '四川省南充市蓬安县');
INSERT INTO `district` VALUES ('511324', '四川省南充市仪陇县');
INSERT INTO `district` VALUES ('511325', '四川省南充市西充县');
INSERT INTO `district` VALUES ('511381', '四川省南充市阆中市');
INSERT INTO `district` VALUES ('511400', '四川省眉山市');
INSERT INTO `district` VALUES ('511401', '四川省眉山市市辖区');
INSERT INTO `district` VALUES ('511402', '四川省眉山市东坡区');
INSERT INTO `district` VALUES ('511421', '四川省眉山市仁寿县');
INSERT INTO `district` VALUES ('511422', '四川省眉山市彭山县');
INSERT INTO `district` VALUES ('511423', '四川省眉山市洪雅县');
INSERT INTO `district` VALUES ('511424', '四川省眉山市丹棱县');
INSERT INTO `district` VALUES ('511425', '四川省眉山市青神县');
INSERT INTO `district` VALUES ('511500', '四川省宜宾市');
INSERT INTO `district` VALUES ('511501', '四川省宜宾市市辖区');
INSERT INTO `district` VALUES ('511502', '四川省宜宾市翠屏区');
INSERT INTO `district` VALUES ('511521', '四川省宜宾市宜宾县');
INSERT INTO `district` VALUES ('511522', '四川省宜宾市南溪县');
INSERT INTO `district` VALUES ('511523', '四川省宜宾市江安县');
INSERT INTO `district` VALUES ('511524', '四川省宜宾市长宁县');
INSERT INTO `district` VALUES ('511525', '四川省宜宾市高县');
INSERT INTO `district` VALUES ('511526', '四川省宜宾市珙县');
INSERT INTO `district` VALUES ('511527', '四川省宜宾市筠连县');
INSERT INTO `district` VALUES ('511528', '四川省宜宾市兴文县');
INSERT INTO `district` VALUES ('511529', '四川省宜宾市屏山县');
INSERT INTO `district` VALUES ('511600', '四川省广安市');
INSERT INTO `district` VALUES ('511601', '四川省广安市市辖区');
INSERT INTO `district` VALUES ('511602', '四川省广安市广安区');
INSERT INTO `district` VALUES ('511621', '四川省广安市岳池县');
INSERT INTO `district` VALUES ('511622', '四川省广安市武胜县');
INSERT INTO `district` VALUES ('511623', '四川省广安市邻水县');
INSERT INTO `district` VALUES ('511681', '四川省广安市华莹市');
INSERT INTO `district` VALUES ('511700', '四川省达州市');
INSERT INTO `district` VALUES ('511701', '四川省达州市市辖区');
INSERT INTO `district` VALUES ('511702', '四川省达州市通川区');
INSERT INTO `district` VALUES ('511721', '四川省达州市达县');
INSERT INTO `district` VALUES ('511722', '四川省达州市宣汉县');
INSERT INTO `district` VALUES ('511723', '四川省达州市开江县');
INSERT INTO `district` VALUES ('511724', '四川省达州市大竹县');
INSERT INTO `district` VALUES ('511725', '四川省达州市渠县');
INSERT INTO `district` VALUES ('511781', '四川省达州市万源市');
INSERT INTO `district` VALUES ('511800', '四川省雅安市');
INSERT INTO `district` VALUES ('511801', '四川省雅安市市辖区');
INSERT INTO `district` VALUES ('511802', '四川省雅安市雨城区');
INSERT INTO `district` VALUES ('511821', '四川省雅安市名山县');
INSERT INTO `district` VALUES ('511822', '四川省雅安市荥经县');
INSERT INTO `district` VALUES ('511823', '四川省雅安市汉源县');
INSERT INTO `district` VALUES ('511824', '四川省雅安市石棉县');
INSERT INTO `district` VALUES ('511825', '四川省雅安市天全县');
INSERT INTO `district` VALUES ('511826', '四川省雅安市芦山县');
INSERT INTO `district` VALUES ('511827', '四川省雅安市宝兴县');
INSERT INTO `district` VALUES ('511900', '四川省巴中市');
INSERT INTO `district` VALUES ('511901', '四川省巴中市市辖区');
INSERT INTO `district` VALUES ('511902', '四川省巴中市巴州区');
INSERT INTO `district` VALUES ('511921', '四川省巴中市通江县');
INSERT INTO `district` VALUES ('511922', '四川省巴中市南江县');
INSERT INTO `district` VALUES ('511923', '四川省巴中市平昌县');
INSERT INTO `district` VALUES ('512000', '四川省资阳市');
INSERT INTO `district` VALUES ('512001', '四川省资阳市市辖区');
INSERT INTO `district` VALUES ('512002', '四川省资阳市雁江区');
INSERT INTO `district` VALUES ('512021', '四川省资阳市安岳县');
INSERT INTO `district` VALUES ('512022', '四川省资阳市乐至县');
INSERT INTO `district` VALUES ('512081', '四川省资阳市简阳市');
INSERT INTO `district` VALUES ('513000', '四川省达川地区');
INSERT INTO `district` VALUES ('513001', '四川省达川地区达川市');
INSERT INTO `district` VALUES ('513002', '四川省达川地区万源市');
INSERT INTO `district` VALUES ('513021', '四川省达川地区达县');
INSERT INTO `district` VALUES ('513022', '四川省达川地区宣汉县');
INSERT INTO `district` VALUES ('513023', '四川省达川地区开江县');
INSERT INTO `district` VALUES ('513029', '四川省达川地区大竹县');
INSERT INTO `district` VALUES ('513030', '四川省达川地区渠县');
INSERT INTO `district` VALUES ('513100', '四川省雅安地区');
INSERT INTO `district` VALUES ('513101', '四川省雅安地区雅安市');
INSERT INTO `district` VALUES ('513122', '四川省雅安地区名山县');
INSERT INTO `district` VALUES ('513123', '四川省雅安地区荥经县');
INSERT INTO `district` VALUES ('513124', '四川省雅安地区汉源县');
INSERT INTO `district` VALUES ('513125', '四川省雅安地区石棉县');
INSERT INTO `district` VALUES ('513126', '四川省雅安地区天全县');
INSERT INTO `district` VALUES ('513127', '四川省雅安地区芦山县');
INSERT INTO `district` VALUES ('513128', '四川省雅安地区宝兴县');
INSERT INTO `district` VALUES ('513200', '四川省阿坝藏族羌族自治州');
INSERT INTO `district` VALUES ('513221', '四川省阿坝藏族羌族自治州汶川县');
INSERT INTO `district` VALUES ('513222', '四川省阿坝藏族羌族自治州理县');
INSERT INTO `district` VALUES ('513223', '四川省阿坝藏族羌族自治州茂县');
INSERT INTO `district` VALUES ('513224', '四川省阿坝藏族羌族自治州松潘县');
INSERT INTO `district` VALUES ('513225', '四川省阿坝藏族羌族自治州九寨沟县');
INSERT INTO `district` VALUES ('513226', '四川省阿坝藏族羌族自治州金川县');
INSERT INTO `district` VALUES ('513227', '四川省阿坝藏族羌族自治州小金县');
INSERT INTO `district` VALUES ('513228', '四川省阿坝藏族羌族自治州黑水县');
INSERT INTO `district` VALUES ('513229', '四川省阿坝藏族羌族自治州马尔康县');
INSERT INTO `district` VALUES ('513230', '四川省阿坝藏族羌族自治州壤塘县');
INSERT INTO `district` VALUES ('513231', '四川省阿坝藏族羌族自治州阿坝县');
INSERT INTO `district` VALUES ('513232', '四川省阿坝藏族羌族自治州若尔盖县');
INSERT INTO `district` VALUES ('513233', '四川省阿坝藏族羌族自治州红原县');
INSERT INTO `district` VALUES ('513300', '四川省甘孜藏族自治州');
INSERT INTO `district` VALUES ('513321', '四川省甘孜藏族自治州康定县');
INSERT INTO `district` VALUES ('513322', '四川省甘孜藏族自治州泸定县');
INSERT INTO `district` VALUES ('513323', '四川省甘孜藏族自治州丹巴县');
INSERT INTO `district` VALUES ('513324', '四川省甘孜藏族自治州九龙县');
INSERT INTO `district` VALUES ('513325', '四川省甘孜藏族自治州雅江县');
INSERT INTO `district` VALUES ('513326', '四川省甘孜藏族自治州道孚县');
INSERT INTO `district` VALUES ('513327', '四川省甘孜藏族自治州炉霍县');
INSERT INTO `district` VALUES ('513328', '四川省甘孜藏族自治州甘孜县');
INSERT INTO `district` VALUES ('513329', '四川省甘孜藏族自治州新龙县');
INSERT INTO `district` VALUES ('513330', '四川省甘孜藏族自治州德格县');
INSERT INTO `district` VALUES ('513331', '四川省甘孜藏族自治州白玉县');
INSERT INTO `district` VALUES ('513332', '四川省甘孜藏族自治州石渠县');
INSERT INTO `district` VALUES ('513333', '四川省甘孜藏族自治州色达县');
INSERT INTO `district` VALUES ('513334', '四川省甘孜藏族自治州理塘县');
INSERT INTO `district` VALUES ('513335', '四川省甘孜藏族自治州巴塘县');
INSERT INTO `district` VALUES ('513336', '四川省甘孜藏族自治州乡城县');
INSERT INTO `district` VALUES ('513337', '四川省甘孜藏族自治州稻城县');
INSERT INTO `district` VALUES ('513338', '四川省甘孜藏族自治州得荣县');
INSERT INTO `district` VALUES ('513400', '四川省凉山彝族自治州');
INSERT INTO `district` VALUES ('513401', '四川省凉山彝族自治州西昌市');
INSERT INTO `district` VALUES ('513422', '四川省凉山彝族自治州木里藏族自治县');
INSERT INTO `district` VALUES ('513423', '四川省凉山彝族自治州盐源县');
INSERT INTO `district` VALUES ('513424', '四川省凉山彝族自治州德昌县');
INSERT INTO `district` VALUES ('513425', '四川省凉山彝族自治州会理县');
INSERT INTO `district` VALUES ('513426', '四川省凉山彝族自治州会东县');
INSERT INTO `district` VALUES ('513427', '四川省凉山彝族自治州宁南县');
INSERT INTO `district` VALUES ('513428', '四川省凉山彝族自治州普格县');
INSERT INTO `district` VALUES ('513429', '四川省凉山彝族自治州布拖县');
INSERT INTO `district` VALUES ('513430', '四川省凉山彝族自治州金阳县');
INSERT INTO `district` VALUES ('513431', '四川省凉山彝族自治州昭觉县');
INSERT INTO `district` VALUES ('513432', '四川省凉山彝族自治州喜德县');
INSERT INTO `district` VALUES ('513433', '四川省凉山彝族自治州冕宁县');
INSERT INTO `district` VALUES ('513434', '四川省凉山彝族自治州越西县');
INSERT INTO `district` VALUES ('513435', '四川省凉山彝族自治州甘洛县');
INSERT INTO `district` VALUES ('513436', '四川省凉山彝族自治州美姑县');
INSERT INTO `district` VALUES ('513437', '四川省凉山彝族自治州雷波县');
INSERT INTO `district` VALUES ('513700', '四川省巴中地区');
INSERT INTO `district` VALUES ('513701', '四川省巴中地区巴中市');
INSERT INTO `district` VALUES ('513721', '四川省巴中地区通江县');
INSERT INTO `district` VALUES ('513722', '四川省巴中地区南江县');
INSERT INTO `district` VALUES ('513723', '四川省巴中地区平昌县');
INSERT INTO `district` VALUES ('513800', '四川省眉山地区');
INSERT INTO `district` VALUES ('513821', '四川省眉山地区眉山县');
INSERT INTO `district` VALUES ('513822', '四川省眉山地区仁寿县');
INSERT INTO `district` VALUES ('513823', '四川省眉山地区彭山县');
INSERT INTO `district` VALUES ('513824', '四川省眉山地区洪雅县');
INSERT INTO `district` VALUES ('513825', '四川省眉山地区丹棱县');
INSERT INTO `district` VALUES ('513826', '四川省眉山地区青神县');
INSERT INTO `district` VALUES ('513900', '四川省资阳地区');
INSERT INTO `district` VALUES ('513901', '四川省资阳地区资阳市');
INSERT INTO `district` VALUES ('513902', '四川省资阳地区简阳市');
INSERT INTO `district` VALUES ('513921', '四川省资阳地区安岳县');
INSERT INTO `district` VALUES ('513922', '四川省资阳地区乐至县');
INSERT INTO `district` VALUES ('520000', '贵州省');
INSERT INTO `district` VALUES ('520100', '贵州省贵阳市');
INSERT INTO `district` VALUES ('520101', '贵州省贵阳市市辖区');
INSERT INTO `district` VALUES ('520102', '贵州省贵阳市南明区');
INSERT INTO `district` VALUES ('520103', '贵州省贵阳市云岩区');
INSERT INTO `district` VALUES ('520111', '贵州省贵阳市花溪区');
INSERT INTO `district` VALUES ('520112', '贵州省贵阳市乌当区');
INSERT INTO `district` VALUES ('520113', '贵州省贵阳市白云区');
INSERT INTO `district` VALUES ('520114', '贵州省贵阳市小河区');
INSERT INTO `district` VALUES ('520121', '贵州省贵阳市开阳县');
INSERT INTO `district` VALUES ('520122', '贵州省贵阳市息烽县');
INSERT INTO `district` VALUES ('520123', '贵州省贵阳市修文县');
INSERT INTO `district` VALUES ('520181', '贵州省贵阳市清镇市');
INSERT INTO `district` VALUES ('520200', '贵州省六盘水市');
INSERT INTO `district` VALUES ('520201', '贵州省六盘水市钟山区');
INSERT INTO `district` VALUES ('520202', '贵州省六盘水市盘县特区');
INSERT INTO `district` VALUES ('520203', '贵州省六盘水市六枝特区');
INSERT INTO `district` VALUES ('520221', '贵州省六盘水市水城县');
INSERT INTO `district` VALUES ('520222', '贵州省六盘水市盘县');
INSERT INTO `district` VALUES ('520300', '贵州省遵义市');
INSERT INTO `district` VALUES ('520301', '贵州省遵义市市辖区');
INSERT INTO `district` VALUES ('520302', '贵州省遵义市红花岗区');
INSERT INTO `district` VALUES ('520303', '贵州省遵义市汇川区');
INSERT INTO `district` VALUES ('520321', '贵州省遵义市遵义县');
INSERT INTO `district` VALUES ('520322', '贵州省遵义市桐梓县');
INSERT INTO `district` VALUES ('520323', '贵州省遵义市绥阳县');
INSERT INTO `district` VALUES ('520324', '贵州省遵义市正安县');
INSERT INTO `district` VALUES ('520325', '贵州省遵义市道真仡佬族苗族自治县');
INSERT INTO `district` VALUES ('520326', '贵州省遵义市务川仡佬族苗族自治县');
INSERT INTO `district` VALUES ('520327', '贵州省遵义市凤冈县');
INSERT INTO `district` VALUES ('520328', '贵州省遵义市湄潭县');
INSERT INTO `district` VALUES ('520329', '贵州省遵义市余庆县');
INSERT INTO `district` VALUES ('520330', '贵州省遵义市习水县');
INSERT INTO `district` VALUES ('520381', '贵州省遵义市赤水市');
INSERT INTO `district` VALUES ('520382', '贵州省遵义市仁怀市');
INSERT INTO `district` VALUES ('520400', '贵州省安顺市');
INSERT INTO `district` VALUES ('520401', '贵州省安顺市市辖区');
INSERT INTO `district` VALUES ('520402', '贵州省安顺市西秀区');
INSERT INTO `district` VALUES ('520421', '贵州省安顺市平坝县');
INSERT INTO `district` VALUES ('520422', '贵州省安顺市普定县');
INSERT INTO `district` VALUES ('520423', '贵州省安顺市镇宁布依族苗族自治县');
INSERT INTO `district` VALUES ('520424', '贵州省安顺市关岭布依族苗族自治县');
INSERT INTO `district` VALUES ('520425', '贵州省安顺市紫云苗族布依族自治县');
INSERT INTO `district` VALUES ('522200', '贵州省铜仁地区');
INSERT INTO `district` VALUES ('522201', '贵州省铜仁地区铜仁市');
INSERT INTO `district` VALUES ('522222', '贵州省铜仁地区江口县');
INSERT INTO `district` VALUES ('522223', '贵州省铜仁地区玉屏侗族自治县');
INSERT INTO `district` VALUES ('522224', '贵州省铜仁地区石阡县');
INSERT INTO `district` VALUES ('522225', '贵州省铜仁地区思南县');
INSERT INTO `district` VALUES ('522226', '贵州省铜仁地区印江土家族苗族自治县');
INSERT INTO `district` VALUES ('522227', '贵州省铜仁地区德江县');
INSERT INTO `district` VALUES ('522228', '贵州省铜仁地区沿河土家族自治县');
INSERT INTO `district` VALUES ('522229', '贵州省铜仁地区松桃苗族自治县');
INSERT INTO `district` VALUES ('522230', '贵州省铜仁地区万山特区');
INSERT INTO `district` VALUES ('522300', '贵州省黔西南布依族苗族自治州');
INSERT INTO `district` VALUES ('522301', '贵州省黔西南布依族苗族自治州兴义市');
INSERT INTO `district` VALUES ('522322', '贵州省黔西南布依族苗族自治州兴仁县');
INSERT INTO `district` VALUES ('522323', '贵州省黔西南布依族苗族自治州普安县');
INSERT INTO `district` VALUES ('522324', '贵州省黔西南布依族苗族自治州晴隆县');
INSERT INTO `district` VALUES ('522325', '贵州省黔西南布依族苗族自治州贞丰县');
INSERT INTO `district` VALUES ('522326', '贵州省黔西南布依族苗族自治州望谟县');
INSERT INTO `district` VALUES ('522327', '贵州省黔西南布依族苗族自治州册亨县');
INSERT INTO `district` VALUES ('522328', '贵州省黔西南布依族苗族自治州安龙县');
INSERT INTO `district` VALUES ('522400', '贵州省毕节地区');
INSERT INTO `district` VALUES ('522401', '贵州省毕节地区毕节市');
INSERT INTO `district` VALUES ('522422', '贵州省毕节地区大方县');
INSERT INTO `district` VALUES ('522423', '贵州省毕节地区黔西县');
INSERT INTO `district` VALUES ('522424', '贵州省毕节地区金沙县');
INSERT INTO `district` VALUES ('522425', '贵州省毕节地区织金县');
INSERT INTO `district` VALUES ('522426', '贵州省毕节地区纳雍县');
INSERT INTO `district` VALUES ('522427', '贵州省毕节地区威宁彝族回族苗族自治县');
INSERT INTO `district` VALUES ('522428', '贵州省毕节地区赫章县');
INSERT INTO `district` VALUES ('522500', '贵州省安顺地区');
INSERT INTO `district` VALUES ('522501', '贵州省安顺地区安顺市');
INSERT INTO `district` VALUES ('522526', '贵州省安顺地区平坝县');
INSERT INTO `district` VALUES ('522527', '贵州省安顺地区普定县');
INSERT INTO `district` VALUES ('522528', '贵州省安顺地区关岭布依族苗族自治县');
INSERT INTO `district` VALUES ('522529', '贵州省安顺地区镇宁布依族苗族自治县');
INSERT INTO `district` VALUES ('522530', '贵州省安顺地区紫云苗族布依族自治县');
INSERT INTO `district` VALUES ('522600', '贵州省黔东南苗族侗族自治州');
INSERT INTO `district` VALUES ('522601', '贵州省黔东南苗族侗族自治州凯里市');
INSERT INTO `district` VALUES ('522622', '贵州省黔东南苗族侗族自治州黄平县');
INSERT INTO `district` VALUES ('522623', '贵州省黔东南苗族侗族自治州施秉县');
INSERT INTO `district` VALUES ('522624', '贵州省黔东南苗族侗族自治州三穗县');
INSERT INTO `district` VALUES ('522625', '贵州省黔东南苗族侗族自治州镇远县');
INSERT INTO `district` VALUES ('522626', '贵州省黔东南苗族侗族自治州岑巩县');
INSERT INTO `district` VALUES ('522627', '贵州省黔东南苗族侗族自治州天柱县');
INSERT INTO `district` VALUES ('522628', '贵州省黔东南苗族侗族自治州锦屏县');
INSERT INTO `district` VALUES ('522629', '贵州省黔东南苗族侗族自治州剑河县');
INSERT INTO `district` VALUES ('522630', '贵州省黔东南苗族侗族自治州台江县');
INSERT INTO `district` VALUES ('522631', '贵州省黔东南苗族侗族自治州黎平县');
INSERT INTO `district` VALUES ('522632', '贵州省黔东南苗族侗族自治州榕江县');
INSERT INTO `district` VALUES ('522633', '贵州省黔东南苗族侗族自治州从江县');
INSERT INTO `district` VALUES ('522634', '贵州省黔东南苗族侗族自治州雷山县');
INSERT INTO `district` VALUES ('522635', '贵州省黔东南苗族侗族自治州麻江县');
INSERT INTO `district` VALUES ('522636', '贵州省黔东南苗族侗族自治州丹寨县');
INSERT INTO `district` VALUES ('522700', '贵州省黔南布依族苗族自治州');
INSERT INTO `district` VALUES ('522701', '贵州省黔南布依族苗族自治州都匀市');
INSERT INTO `district` VALUES ('522702', '贵州省黔南布依族苗族自治州福泉市');
INSERT INTO `district` VALUES ('522722', '贵州省黔南布依族苗族自治州荔波县');
INSERT INTO `district` VALUES ('522723', '贵州省黔南布依族苗族自治州贵定县');
INSERT INTO `district` VALUES ('522725', '贵州省黔南布依族苗族自治州瓮安县');
INSERT INTO `district` VALUES ('522726', '贵州省黔南布依族苗族自治州独山县');
INSERT INTO `district` VALUES ('522727', '贵州省黔南布依族苗族自治州平塘县');
INSERT INTO `district` VALUES ('522728', '贵州省黔南布依族苗族自治州罗甸县');
INSERT INTO `district` VALUES ('522729', '贵州省黔南布依族苗族自治州长顺县');
INSERT INTO `district` VALUES ('522730', '贵州省黔南布依族苗族自治州龙里县');
INSERT INTO `district` VALUES ('522731', '贵州省黔南布依族苗族自治州惠水县');
INSERT INTO `district` VALUES ('522732', '贵州省黔南布依族苗族自治州三都水族自治县');
INSERT INTO `district` VALUES ('530000', '云南省');
INSERT INTO `district` VALUES ('530100', '云南省昆明市');
INSERT INTO `district` VALUES ('530101', '云南省昆明市市辖区');
INSERT INTO `district` VALUES ('530102', '云南省昆明市五华区');
INSERT INTO `district` VALUES ('530103', '云南省昆明市盘龙区');
INSERT INTO `district` VALUES ('530111', '云南省昆明市官渡区');
INSERT INTO `district` VALUES ('530112', '云南省昆明市西山区');
INSERT INTO `district` VALUES ('530113', '云南省昆明市东川区');
INSERT INTO `district` VALUES ('530121', '云南省昆明市呈贡县');
INSERT INTO `district` VALUES ('530122', '云南省昆明市晋宁县');
INSERT INTO `district` VALUES ('530124', '云南省昆明市富民县');
INSERT INTO `district` VALUES ('530125', '云南省昆明市宜良县');
INSERT INTO `district` VALUES ('530126', '云南省昆明市石林彝族自治县');
INSERT INTO `district` VALUES ('530127', '云南省昆明市嵩明县');
INSERT INTO `district` VALUES ('530128', '云南省昆明市禄劝彝族苗族自治县');
INSERT INTO `district` VALUES ('530129', '云南省昆明市寻甸回族彝族自治县');
INSERT INTO `district` VALUES ('530181', '云南省昆明市安宁市');
INSERT INTO `district` VALUES ('530300', '云南省曲靖市');
INSERT INTO `district` VALUES ('530301', '云南省曲靖市市辖区');
INSERT INTO `district` VALUES ('530302', '云南省曲靖市麒麟区');
INSERT INTO `district` VALUES ('530321', '云南省曲靖市马龙县');
INSERT INTO `district` VALUES ('530322', '云南省曲靖市陆良县');
INSERT INTO `district` VALUES ('530323', '云南省曲靖市师宗县');
INSERT INTO `district` VALUES ('530324', '云南省曲靖市罗平县');
INSERT INTO `district` VALUES ('530325', '云南省曲靖市富源县');
INSERT INTO `district` VALUES ('530326', '云南省曲靖市会泽县');
INSERT INTO `district` VALUES ('530328', '云南省曲靖市沾益县');
INSERT INTO `district` VALUES ('530381', '云南省曲靖市宣威市');
INSERT INTO `district` VALUES ('530400', '云南省玉溪市');
INSERT INTO `district` VALUES ('530401', '云南省玉溪市市辖区');
INSERT INTO `district` VALUES ('530402', '云南省玉溪市红塔区');
INSERT INTO `district` VALUES ('530421', '云南省玉溪市江川县');
INSERT INTO `district` VALUES ('530422', '云南省玉溪市澄江县');
INSERT INTO `district` VALUES ('530423', '云南省玉溪市通海县');
INSERT INTO `district` VALUES ('530424', '云南省玉溪市华宁县');
INSERT INTO `district` VALUES ('530425', '云南省玉溪市易门县');
INSERT INTO `district` VALUES ('530426', '云南省玉溪市峨山彝族自治县');
INSERT INTO `district` VALUES ('530427', '云南省玉溪市新平彝族傣族自治县');
INSERT INTO `district` VALUES ('530428', '云南省玉溪市元江哈尼族彝族傣族自治县');
INSERT INTO `district` VALUES ('530500', '云南省保山市');
INSERT INTO `district` VALUES ('530501', '云南省保山市市辖区');
INSERT INTO `district` VALUES ('530502', '云南省保山市隆阳区');
INSERT INTO `district` VALUES ('530521', '云南省保山市施甸县');
INSERT INTO `district` VALUES ('530522', '云南省保山市腾冲县');
INSERT INTO `district` VALUES ('530523', '云南省保山市龙陵县');
INSERT INTO `district` VALUES ('530524', '云南省保山市昌宁县');
INSERT INTO `district` VALUES ('530600', '云南省昭通市');
INSERT INTO `district` VALUES ('530601', '云南省昭通市市辖区');
INSERT INTO `district` VALUES ('530602', '云南省昭通市昭阳区');
INSERT INTO `district` VALUES ('530621', '云南省昭通市鲁甸县');
INSERT INTO `district` VALUES ('530622', '云南省昭通市巧家县');
INSERT INTO `district` VALUES ('530623', '云南省昭通市盐津县');
INSERT INTO `district` VALUES ('530624', '云南省昭通市大关县');
INSERT INTO `district` VALUES ('530625', '云南省昭通市永善县');
INSERT INTO `district` VALUES ('530626', '云南省昭通市绥江县');
INSERT INTO `district` VALUES ('530627', '云南省昭通市镇雄县');
INSERT INTO `district` VALUES ('530628', '云南省昭通市彝良县');
INSERT INTO `district` VALUES ('530629', '云南省昭通市威信县');
INSERT INTO `district` VALUES ('530630', '云南省昭通市水富县');
INSERT INTO `district` VALUES ('530700', '云南省丽江市');
INSERT INTO `district` VALUES ('530701', '云南省丽江市市辖区');
INSERT INTO `district` VALUES ('530702', '云南省丽江市古城区');
INSERT INTO `district` VALUES ('530721', '云南省丽江市玉龙纳西族自治县');
INSERT INTO `district` VALUES ('530722', '云南省丽江市永胜县');
INSERT INTO `district` VALUES ('530723', '云南省丽江市华坪县');
INSERT INTO `district` VALUES ('530724', '云南省丽江市宁蒗彝族自治县');
INSERT INTO `district` VALUES ('530800', '云南省思茅市');
INSERT INTO `district` VALUES ('530801', '云南省思茅市市辖区');
INSERT INTO `district` VALUES ('530802', '云南省思茅市翠云区');
INSERT INTO `district` VALUES ('530821', '云南省思茅市普洱哈尼族彝族自治县');
INSERT INTO `district` VALUES ('530822', '云南省思茅市墨江哈尼族自治县');
INSERT INTO `district` VALUES ('530823', '云南省思茅市景东彝族自治县');
INSERT INTO `district` VALUES ('530824', '云南省思茅市景谷傣族彝族自治县');
INSERT INTO `district` VALUES ('530825', '云南省思茅市镇沅彝族哈尼族拉祜族自治县');
INSERT INTO `district` VALUES ('530826', '云南省思茅市江城哈尼族彝族自治县');
INSERT INTO `district` VALUES ('530827', '云南省思茅市孟连傣族拉祜族佤族自治县');
INSERT INTO `district` VALUES ('530828', '云南省思茅市澜沧拉祜族自治县');
INSERT INTO `district` VALUES ('530829', '云南省思茅市西盟佤族自治县');
INSERT INTO `district` VALUES ('530900', '云南省临沧市');
INSERT INTO `district` VALUES ('530901', '云南省临沧市市辖区');
INSERT INTO `district` VALUES ('530902', '云南省临沧市临翔区');
INSERT INTO `district` VALUES ('530921', '云南省临沧市凤庆县');
INSERT INTO `district` VALUES ('530922', '云南省临沧市云县');
INSERT INTO `district` VALUES ('530923', '云南省临沧市永德县');
INSERT INTO `district` VALUES ('530924', '云南省临沧市镇康县');
INSERT INTO `district` VALUES ('530925', '云南省临沧市双江拉祜族佤族布朗族傣族自治县');
INSERT INTO `district` VALUES ('530926', '云南省临沧市耿马傣族佤族自治县');
INSERT INTO `district` VALUES ('530927', '云南省临沧市沧源佤族自治县');
INSERT INTO `district` VALUES ('532100', '云南省昭通地区');
INSERT INTO `district` VALUES ('532101', '云南省昭通地区昭通市');
INSERT INTO `district` VALUES ('532122', '云南省昭通地区鲁甸县');
INSERT INTO `district` VALUES ('532123', '云南省昭通地区巧家县');
INSERT INTO `district` VALUES ('532124', '云南省昭通地区盐津县');
INSERT INTO `district` VALUES ('532125', '云南省昭通地区大关县');
INSERT INTO `district` VALUES ('532126', '云南省昭通地区永善县');
INSERT INTO `district` VALUES ('532127', '云南省昭通地区绥江县');
INSERT INTO `district` VALUES ('532128', '云南省昭通地区镇雄县');
INSERT INTO `district` VALUES ('532129', '云南省昭通地区彝良县');
INSERT INTO `district` VALUES ('532130', '云南省昭通地区威信县');
INSERT INTO `district` VALUES ('532131', '云南省昭通地区水富县');
INSERT INTO `district` VALUES ('532300', '云南省楚雄彝族自治州');
INSERT INTO `district` VALUES ('532301', '云南省楚雄彝族自治州楚雄市');
INSERT INTO `district` VALUES ('532322', '云南省楚雄彝族自治州双柏县');
INSERT INTO `district` VALUES ('532323', '云南省楚雄彝族自治州牟定县');
INSERT INTO `district` VALUES ('532324', '云南省楚雄彝族自治州南华县');
INSERT INTO `district` VALUES ('532325', '云南省楚雄彝族自治州姚安县');
INSERT INTO `district` VALUES ('532326', '云南省楚雄彝族自治州大姚县');
INSERT INTO `district` VALUES ('532327', '云南省楚雄彝族自治州永仁县');
INSERT INTO `district` VALUES ('532328', '云南省楚雄彝族自治州元谋县');
INSERT INTO `district` VALUES ('532329', '云南省楚雄彝族自治州武定县');
INSERT INTO `district` VALUES ('532331', '云南省楚雄彝族自治州禄丰县');
INSERT INTO `district` VALUES ('532500', '云南省红河哈尼族彝族自治州');
INSERT INTO `district` VALUES ('532501', '云南省红河哈尼族彝族自治州个旧市');
INSERT INTO `district` VALUES ('532502', '云南省红河哈尼族彝族自治州开远市');
INSERT INTO `district` VALUES ('532522', '云南省红河哈尼族彝族自治州蒙自县');
INSERT INTO `district` VALUES ('532523', '云南省红河哈尼族彝族自治州屏边苗族自治县');
INSERT INTO `district` VALUES ('532524', '云南省红河哈尼族彝族自治州建水县');
INSERT INTO `district` VALUES ('532525', '云南省红河哈尼族彝族自治州石屏县');
INSERT INTO `district` VALUES ('532526', '云南省红河哈尼族彝族自治州弥勒县');
INSERT INTO `district` VALUES ('532527', '云南省红河哈尼族彝族自治州泸西县');
INSERT INTO `district` VALUES ('532528', '云南省红河哈尼族彝族自治州元阳县');
INSERT INTO `district` VALUES ('532529', '云南省红河哈尼族彝族自治州红河县');
INSERT INTO `district` VALUES ('532530', '云南省红河哈尼族彝族自治州金平苗族瑶族傣族自治县');
INSERT INTO `district` VALUES ('532531', '云南省红河哈尼族彝族自治州绿春县');
INSERT INTO `district` VALUES ('532532', '云南省红河哈尼族彝族自治州河口瑶族自治县');
INSERT INTO `district` VALUES ('532600', '云南省文山壮族苗族自治州');
INSERT INTO `district` VALUES ('532621', '云南省文山壮族苗族自治州文山县');
INSERT INTO `district` VALUES ('532622', '云南省文山壮族苗族自治州砚山县');
INSERT INTO `district` VALUES ('532623', '云南省文山壮族苗族自治州西畴县');
INSERT INTO `district` VALUES ('532624', '云南省文山壮族苗族自治州麻栗坡县');
INSERT INTO `district` VALUES ('532625', '云南省文山壮族苗族自治州马关县');
INSERT INTO `district` VALUES ('532626', '云南省文山壮族苗族自治州丘北县');
INSERT INTO `district` VALUES ('532627', '云南省文山壮族苗族自治州广南县');
INSERT INTO `district` VALUES ('532628', '云南省文山壮族苗族自治州富宁县');
INSERT INTO `district` VALUES ('532700', '云南省思茅地区');
INSERT INTO `district` VALUES ('532701', '云南省思茅地区思茅市');
INSERT INTO `district` VALUES ('532722', '云南省思茅地区普洱哈尼族彝族自治县');
INSERT INTO `district` VALUES ('532723', '云南省思茅地区墨江哈尼族自治县');
INSERT INTO `district` VALUES ('532724', '云南省思茅地区景东彝族自治县');
INSERT INTO `district` VALUES ('532725', '云南省思茅地区景谷傣族彝族自治县');
INSERT INTO `district` VALUES ('532726', '云南省思茅地区镇沅彝族哈尼族拉祜族自治县');
INSERT INTO `district` VALUES ('532727', '云南省思茅地区江城哈尼族彝族自治县');
INSERT INTO `district` VALUES ('532728', '云南省思茅地区孟连傣族拉祜族佤族自治县');
INSERT INTO `district` VALUES ('532729', '云南省思茅地区澜沧拉祜族自治县');
INSERT INTO `district` VALUES ('532730', '云南省思茅地区西盟佤族自治县');
INSERT INTO `district` VALUES ('532800', '云南省西双版纳傣族自治州');
INSERT INTO `district` VALUES ('532801', '云南省西双版纳傣族自治州景洪市');
INSERT INTO `district` VALUES ('532822', '云南省西双版纳傣族自治州勐海县');
INSERT INTO `district` VALUES ('532823', '云南省西双版纳傣族自治州勐腊县');
INSERT INTO `district` VALUES ('532900', '云南省大理白族自治州');
INSERT INTO `district` VALUES ('532901', '云南省大理白族自治州大理市');
INSERT INTO `district` VALUES ('532922', '云南省大理白族自治州漾濞彝族自治县');
INSERT INTO `district` VALUES ('532923', '云南省大理白族自治州祥云县');
INSERT INTO `district` VALUES ('532924', '云南省大理白族自治州宾川县');
INSERT INTO `district` VALUES ('532925', '云南省大理白族自治州弥渡县');
INSERT INTO `district` VALUES ('532926', '云南省大理白族自治州南涧彝族自治县');
INSERT INTO `district` VALUES ('532927', '云南省大理白族自治州巍山彝族回族自治县');
INSERT INTO `district` VALUES ('532928', '云南省大理白族自治州永平县');
INSERT INTO `district` VALUES ('532929', '云南省大理白族自治州云龙县');
INSERT INTO `district` VALUES ('532930', '云南省大理白族自治州洱源县');
INSERT INTO `district` VALUES ('532931', '云南省大理白族自治州剑川县');
INSERT INTO `district` VALUES ('532932', '云南省大理白族自治州鹤庆县');
INSERT INTO `district` VALUES ('533000', '云南省保山地区');
INSERT INTO `district` VALUES ('533001', '云南省保山地区保山市');
INSERT INTO `district` VALUES ('533022', '云南省保山地区施甸县');
INSERT INTO `district` VALUES ('533023', '云南省保山地区腾冲县');
INSERT INTO `district` VALUES ('533024', '云南省保山地区龙陵县');
INSERT INTO `district` VALUES ('533025', '云南省保山地区昌宁县');
INSERT INTO `district` VALUES ('533100', '云南省德宏傣族景颇族自治州');
INSERT INTO `district` VALUES ('533101', '云南省德宏傣族景颇族自治州畹町市');
INSERT INTO `district` VALUES ('533102', '云南省德宏傣族景颇族自治州瑞丽市');
INSERT INTO `district` VALUES ('533103', '云南省德宏傣族景颇族自治州潞西市');
INSERT INTO `district` VALUES ('533122', '云南省德宏傣族景颇族自治州梁河县');
INSERT INTO `district` VALUES ('533123', '云南省德宏傣族景颇族自治州盈江县');
INSERT INTO `district` VALUES ('533124', '云南省德宏傣族景颇族自治州陇川县');
INSERT INTO `district` VALUES ('533200', '云南省丽江地区');
INSERT INTO `district` VALUES ('533221', '云南省丽江地区丽江纳西族自治县');
INSERT INTO `district` VALUES ('533222', '云南省丽江地区永胜县');
INSERT INTO `district` VALUES ('533223', '云南省丽江地区华坪县');
INSERT INTO `district` VALUES ('533224', '云南省丽江地区宁蒗彝族自治县');
INSERT INTO `district` VALUES ('533300', '云南省怒江傈僳族自治州');
INSERT INTO `district` VALUES ('533321', '云南省怒江傈僳族自治州泸水县');
INSERT INTO `district` VALUES ('533323', '云南省怒江傈僳族自治州福贡县');
INSERT INTO `district` VALUES ('533324', '云南省怒江傈僳族自治州贡山独龙族怒族自治县');
INSERT INTO `district` VALUES ('533325', '云南省怒江傈僳族自治州兰坪白族普米族自治县');
INSERT INTO `district` VALUES ('533400', '云南省迪庆藏族自治州');
INSERT INTO `district` VALUES ('533421', '云南省迪庆藏族自治州中甸县');
INSERT INTO `district` VALUES ('533422', '云南省迪庆藏族自治州德钦县');
INSERT INTO `district` VALUES ('533423', '云南省迪庆藏族自治州维西傈僳族自治县');
INSERT INTO `district` VALUES ('533500', '云南省临沧地区');
INSERT INTO `district` VALUES ('533521', '云南省临沧地区临沧县');
INSERT INTO `district` VALUES ('533522', '云南省临沧地区凤庆县');
INSERT INTO `district` VALUES ('533523', '云南省临沧地区云县');
INSERT INTO `district` VALUES ('533524', '云南省临沧地区永德县');
INSERT INTO `district` VALUES ('533525', '云南省临沧地区镇康县');
INSERT INTO `district` VALUES ('533526', '云南省临沧地区双江拉祜族佤族布朗族傣族自治县');
INSERT INTO `district` VALUES ('533527', '云南省临沧地区耿马傣族佤族自治县');
INSERT INTO `district` VALUES ('533528', '云南省临沧地区沧源佤族自治县');
INSERT INTO `district` VALUES ('540000', '西藏');
INSERT INTO `district` VALUES ('540100', '西藏自治区拉萨市');
INSERT INTO `district` VALUES ('540101', '西藏自治区拉萨市市辖区');
INSERT INTO `district` VALUES ('540102', '西藏自治区拉萨市城关区');
INSERT INTO `district` VALUES ('540121', '西藏自治区拉萨市林周县');
INSERT INTO `district` VALUES ('540122', '西藏自治区拉萨市当雄县');
INSERT INTO `district` VALUES ('540123', '西藏自治区拉萨市尼木县');
INSERT INTO `district` VALUES ('540124', '西藏自治区拉萨市曲水县');
INSERT INTO `district` VALUES ('540125', '西藏自治区拉萨市堆龙德庆县');
INSERT INTO `district` VALUES ('540126', '西藏自治区拉萨市达孜县');
INSERT INTO `district` VALUES ('540127', '西藏自治区拉萨市墨竹工卡县');
INSERT INTO `district` VALUES ('542100', '西藏自治区昌都地区');
INSERT INTO `district` VALUES ('542121', '西藏自治区昌都地区昌都县');
INSERT INTO `district` VALUES ('542122', '西藏自治区昌都地区江达县');
INSERT INTO `district` VALUES ('542123', '西藏自治区昌都地区贡觉县');
INSERT INTO `district` VALUES ('542124', '西藏自治区昌都地区类乌齐县');
INSERT INTO `district` VALUES ('542125', '西藏自治区昌都地区丁青县');
INSERT INTO `district` VALUES ('542126', '西藏自治区昌都地区察雅县');
INSERT INTO `district` VALUES ('542127', '西藏自治区昌都地区八宿县');
INSERT INTO `district` VALUES ('542128', '西藏自治区昌都地区左贡县');
INSERT INTO `district` VALUES ('542129', '西藏自治区昌都地区芒康县');
INSERT INTO `district` VALUES ('542132', '西藏自治区昌都地区洛隆县');
INSERT INTO `district` VALUES ('542133', '西藏自治区昌都地区边坝县');
INSERT INTO `district` VALUES ('542134', '西藏自治区昌都地区盐井县');
INSERT INTO `district` VALUES ('542135', '西藏自治区昌都地区碧土县');
INSERT INTO `district` VALUES ('542136', '西藏自治区昌都地区妥坝县');
INSERT INTO `district` VALUES ('542137', '西藏自治区昌都地区生达县');
INSERT INTO `district` VALUES ('542200', '西藏自治区山南地区');
INSERT INTO `district` VALUES ('542221', '西藏自治区山南地区乃东县');
INSERT INTO `district` VALUES ('542222', '西藏自治区山南地区扎囊县');
INSERT INTO `district` VALUES ('542223', '西藏自治区山南地区贡嘎县');
INSERT INTO `district` VALUES ('542224', '西藏自治区山南地区桑日县');
INSERT INTO `district` VALUES ('542225', '西藏自治区山南地区琼结县');
INSERT INTO `district` VALUES ('542226', '西藏自治区山南地区曲松县');
INSERT INTO `district` VALUES ('542227', '西藏自治区山南地区措美县');
INSERT INTO `district` VALUES ('542228', '西藏自治区山南地区洛扎县');
INSERT INTO `district` VALUES ('542229', '西藏自治区山南地区加查县');
INSERT INTO `district` VALUES ('542231', '西藏自治区山南地区隆子县');
INSERT INTO `district` VALUES ('542232', '西藏自治区山南地区错那县');
INSERT INTO `district` VALUES ('542233', '西藏自治区山南地区浪卡子县');
INSERT INTO `district` VALUES ('542300', '西藏自治区日喀则地区');
INSERT INTO `district` VALUES ('542301', '西藏自治区日喀则地区日喀则市');
INSERT INTO `district` VALUES ('542322', '西藏自治区日喀则地区南木林县');
INSERT INTO `district` VALUES ('542323', '西藏自治区日喀则地区江孜县');
INSERT INTO `district` VALUES ('542324', '西藏自治区日喀则地区定日县');
INSERT INTO `district` VALUES ('542325', '西藏自治区日喀则地区萨迦县');
INSERT INTO `district` VALUES ('542326', '西藏自治区日喀则地区拉孜县');
INSERT INTO `district` VALUES ('542327', '西藏自治区日喀则地区昂仁县');
INSERT INTO `district` VALUES ('542328', '西藏自治区日喀则地区谢通门县');
INSERT INTO `district` VALUES ('542329', '西藏自治区日喀则地区白朗县');
INSERT INTO `district` VALUES ('542330', '西藏自治区日喀则地区仁布县');
INSERT INTO `district` VALUES ('542331', '西藏自治区日喀则地区康马县');
INSERT INTO `district` VALUES ('542332', '西藏自治区日喀则地区定结县');
INSERT INTO `district` VALUES ('542333', '西藏自治区日喀则地区仲巴县');
INSERT INTO `district` VALUES ('542334', '西藏自治区日喀则地区亚东县');
INSERT INTO `district` VALUES ('542335', '西藏自治区日喀则地区吉隆县');
INSERT INTO `district` VALUES ('542336', '西藏自治区日喀则地区聂拉木县');
INSERT INTO `district` VALUES ('542337', '西藏自治区日喀则地区萨嘎县');
INSERT INTO `district` VALUES ('542338', '西藏自治区日喀则地区岗巴县');
INSERT INTO `district` VALUES ('542400', '西藏自治区那曲地区');
INSERT INTO `district` VALUES ('542421', '西藏自治区那曲地区那曲县');
INSERT INTO `district` VALUES ('542422', '西藏自治区那曲地区嘉黎县');
INSERT INTO `district` VALUES ('542423', '西藏自治区那曲地区比如县');
INSERT INTO `district` VALUES ('542424', '西藏自治区那曲地区聂荣县');
INSERT INTO `district` VALUES ('542425', '西藏自治区那曲地区安多县');
INSERT INTO `district` VALUES ('542426', '西藏自治区那曲地区申扎县');
INSERT INTO `district` VALUES ('542427', '西藏自治区那曲地区索县');
INSERT INTO `district` VALUES ('542428', '西藏自治区那曲地区班戈县');
INSERT INTO `district` VALUES ('542429', '西藏自治区那曲地区巴青县');
INSERT INTO `district` VALUES ('542430', '西藏自治区那曲地区尼玛县');
INSERT INTO `district` VALUES ('542500', '西藏自治区阿里地区');
INSERT INTO `district` VALUES ('542521', '西藏自治区阿里地区普兰县');
INSERT INTO `district` VALUES ('542522', '西藏自治区阿里地区札达县');
INSERT INTO `district` VALUES ('542523', '西藏自治区阿里地区噶尔县');
INSERT INTO `district` VALUES ('542524', '西藏自治区阿里地区日土县');
INSERT INTO `district` VALUES ('542525', '西藏自治区阿里地区革吉县');
INSERT INTO `district` VALUES ('542526', '西藏自治区阿里地区改则县');
INSERT INTO `district` VALUES ('542527', '西藏自治区阿里地区措勤县');
INSERT INTO `district` VALUES ('542528', '西藏自治区阿里地区隆格尔县');
INSERT INTO `district` VALUES ('542600', '西藏自治区林芝地区');
INSERT INTO `district` VALUES ('542621', '西藏自治区林芝地区林芝县');
INSERT INTO `district` VALUES ('542622', '西藏自治区林芝地区工布江达县');
INSERT INTO `district` VALUES ('542623', '西藏自治区林芝地区米林县');
INSERT INTO `district` VALUES ('542624', '西藏自治区林芝地区墨脱县');
INSERT INTO `district` VALUES ('542625', '西藏自治区林芝地区波密县');
INSERT INTO `district` VALUES ('542626', '西藏自治区林芝地区察隅县');
INSERT INTO `district` VALUES ('542627', '西藏自治区林芝地区朗县');
INSERT INTO `district` VALUES ('610000', '陕西省');
INSERT INTO `district` VALUES ('610100', '陕西省西安市');
INSERT INTO `district` VALUES ('610101', '陕西省西安市市辖区');
INSERT INTO `district` VALUES ('610102', '陕西省西安市新城区');
INSERT INTO `district` VALUES ('610103', '陕西省西安市碑林区');
INSERT INTO `district` VALUES ('610104', '陕西省西安市莲湖区');
INSERT INTO `district` VALUES ('610111', '陕西省西安市灞桥区');
INSERT INTO `district` VALUES ('610112', '陕西省西安市未央区');
INSERT INTO `district` VALUES ('610113', '陕西省西安市雁塔区');
INSERT INTO `district` VALUES ('610114', '陕西省西安市阎良区');
INSERT INTO `district` VALUES ('610115', '陕西省西安市临潼区');
INSERT INTO `district` VALUES ('610116', '陕西省西安市长安区');
INSERT INTO `district` VALUES ('610121', '陕西省西安市长安县');
INSERT INTO `district` VALUES ('610122', '陕西省西安市蓝田县');
INSERT INTO `district` VALUES ('610124', '陕西省西安市周至县');
INSERT INTO `district` VALUES ('610125', '陕西省西安市户县');
INSERT INTO `district` VALUES ('610126', '陕西省西安市高陵县');
INSERT INTO `district` VALUES ('610200', '陕西省铜川市');
INSERT INTO `district` VALUES ('610201', '陕西省铜川市市辖区');
INSERT INTO `district` VALUES ('610202', '陕西省铜川市城区');
INSERT INTO `district` VALUES ('610203', '陕西省铜川市郊区');
INSERT INTO `district` VALUES ('610204', '陕西省铜川市耀州区');
INSERT INTO `district` VALUES ('610221', '陕西省铜川市耀县');
INSERT INTO `district` VALUES ('610222', '陕西省铜川市宜君县');
INSERT INTO `district` VALUES ('610300', '陕西省宝鸡市');
INSERT INTO `district` VALUES ('610301', '陕西省宝鸡市市辖区');
INSERT INTO `district` VALUES ('610302', '陕西省宝鸡市渭滨区');
INSERT INTO `district` VALUES ('610303', '陕西省宝鸡市金台区');
INSERT INTO `district` VALUES ('610304', '陕西省宝鸡市陈仓区');
INSERT INTO `district` VALUES ('610321', '陕西省宝鸡市宝鸡县');
INSERT INTO `district` VALUES ('610322', '陕西省宝鸡市凤翔县');
INSERT INTO `district` VALUES ('610323', '陕西省宝鸡市岐山县');
INSERT INTO `district` VALUES ('610324', '陕西省宝鸡市扶风县');
INSERT INTO `district` VALUES ('610326', '陕西省宝鸡市眉县');
INSERT INTO `district` VALUES ('610327', '陕西省宝鸡市陇县');
INSERT INTO `district` VALUES ('610328', '陕西省宝鸡市千阳县');
INSERT INTO `district` VALUES ('610329', '陕西省宝鸡市麟游县');
INSERT INTO `district` VALUES ('610330', '陕西省宝鸡市凤县');
INSERT INTO `district` VALUES ('610331', '陕西省宝鸡市太白县');
INSERT INTO `district` VALUES ('610400', '陕西省咸阳市');
INSERT INTO `district` VALUES ('610401', '陕西省咸阳市市辖区');
INSERT INTO `district` VALUES ('610402', '陕西省咸阳市秦都区');
INSERT INTO `district` VALUES ('610403', '陕西省咸阳市杨陵区');
INSERT INTO `district` VALUES ('610404', '陕西省咸阳市渭城区');
INSERT INTO `district` VALUES ('610422', '陕西省咸阳市三原县');
INSERT INTO `district` VALUES ('610423', '陕西省咸阳市泾阳县');
INSERT INTO `district` VALUES ('610424', '陕西省咸阳市乾县');
INSERT INTO `district` VALUES ('610425', '陕西省咸阳市礼泉县');
INSERT INTO `district` VALUES ('610426', '陕西省咸阳市永寿县');
INSERT INTO `district` VALUES ('610427', '陕西省咸阳市彬县');
INSERT INTO `district` VALUES ('610428', '陕西省咸阳市长武县');
INSERT INTO `district` VALUES ('610429', '陕西省咸阳市旬邑县');
INSERT INTO `district` VALUES ('610430', '陕西省咸阳市淳化县');
INSERT INTO `district` VALUES ('610431', '陕西省咸阳市武功县');
INSERT INTO `district` VALUES ('610481', '陕西省咸阳市兴平市');
INSERT INTO `district` VALUES ('610500', '陕西省渭南市');
INSERT INTO `district` VALUES ('610501', '陕西省渭南市市辖区');
INSERT INTO `district` VALUES ('610502', '陕西省渭南市临渭区');
INSERT INTO `district` VALUES ('610521', '陕西省渭南市华县');
INSERT INTO `district` VALUES ('610522', '陕西省渭南市潼关县');
INSERT INTO `district` VALUES ('610523', '陕西省渭南市大荔县');
INSERT INTO `district` VALUES ('610524', '陕西省渭南市合阳县');
INSERT INTO `district` VALUES ('610525', '陕西省渭南市澄城县');
INSERT INTO `district` VALUES ('610526', '陕西省渭南市蒲城县');
INSERT INTO `district` VALUES ('610527', '陕西省渭南市白水县');
INSERT INTO `district` VALUES ('610528', '陕西省渭南市富平县');
INSERT INTO `district` VALUES ('610581', '陕西省渭南市韩城市');
INSERT INTO `district` VALUES ('610582', '陕西省渭南市华阴市');
INSERT INTO `district` VALUES ('610600', '陕西省延安市');
INSERT INTO `district` VALUES ('610601', '陕西省延安市市辖区');
INSERT INTO `district` VALUES ('610602', '陕西省延安市宝塔区');
INSERT INTO `district` VALUES ('610621', '陕西省延安市延长县');
INSERT INTO `district` VALUES ('610622', '陕西省延安市延川县');
INSERT INTO `district` VALUES ('610623', '陕西省延安市子长县');
INSERT INTO `district` VALUES ('610624', '陕西省延安市安塞县');
INSERT INTO `district` VALUES ('610625', '陕西省延安市志丹县');
INSERT INTO `district` VALUES ('610626', '陕西省延安市吴旗县');
INSERT INTO `district` VALUES ('610627', '陕西省延安市甘泉县');
INSERT INTO `district` VALUES ('610628', '陕西省延安市富县');
INSERT INTO `district` VALUES ('610629', '陕西省延安市洛川县');
INSERT INTO `district` VALUES ('610630', '陕西省延安市宜川县');
INSERT INTO `district` VALUES ('610631', '陕西省延安市黄龙县');
INSERT INTO `district` VALUES ('610632', '陕西省延安市黄陵县');
INSERT INTO `district` VALUES ('610700', '陕西省汉中市');
INSERT INTO `district` VALUES ('610701', '陕西省汉中市市辖区');
INSERT INTO `district` VALUES ('610702', '陕西省汉中市汉台区');
INSERT INTO `district` VALUES ('610721', '陕西省汉中市南郑县');
INSERT INTO `district` VALUES ('610722', '陕西省汉中市城固县');
INSERT INTO `district` VALUES ('610723', '陕西省汉中市洋县');
INSERT INTO `district` VALUES ('610724', '陕西省汉中市西乡县');
INSERT INTO `district` VALUES ('610725', '陕西省汉中市勉县');
INSERT INTO `district` VALUES ('610726', '陕西省汉中市宁强县');
INSERT INTO `district` VALUES ('610727', '陕西省汉中市略阳县');
INSERT INTO `district` VALUES ('610728', '陕西省汉中市镇巴县');
INSERT INTO `district` VALUES ('610729', '陕西省汉中市留坝县');
INSERT INTO `district` VALUES ('610730', '陕西省汉中市佛坪县');
INSERT INTO `district` VALUES ('610800', '陕西省榆林市');
INSERT INTO `district` VALUES ('610801', '陕西省榆林市市辖区');
INSERT INTO `district` VALUES ('610802', '陕西省榆林市榆阳区');
INSERT INTO `district` VALUES ('610822', '陕西省榆林市府谷县');
INSERT INTO `district` VALUES ('610823', '陕西省榆林市横山县');
INSERT INTO `district` VALUES ('610824', '陕西省榆林市靖边县');
INSERT INTO `district` VALUES ('610825', '陕西省榆林市定边县');
INSERT INTO `district` VALUES ('610826', '陕西省榆林市绥德县');
INSERT INTO `district` VALUES ('610827', '陕西省榆林市米脂县');
INSERT INTO `district` VALUES ('610828', '陕西省榆林市佳县');
INSERT INTO `district` VALUES ('610829', '陕西省榆林市吴堡县');
INSERT INTO `district` VALUES ('610830', '陕西省榆林市清涧县');
INSERT INTO `district` VALUES ('610831', '陕西省榆林市子洲县');
INSERT INTO `district` VALUES ('610900', '陕西省安康市');
INSERT INTO `district` VALUES ('610901', '陕西省安康市市辖区');
INSERT INTO `district` VALUES ('610902', '陕西省安康市汉滨区');
INSERT INTO `district` VALUES ('610921', '陕西省安康市汉阴县');
INSERT INTO `district` VALUES ('610922', '陕西省安康市石泉县');
INSERT INTO `district` VALUES ('610923', '陕西省安康市宁陕县');
INSERT INTO `district` VALUES ('610924', '陕西省安康市紫阳县');
INSERT INTO `district` VALUES ('610925', '陕西省安康市岚皋县');
INSERT INTO `district` VALUES ('610926', '陕西省安康市平利县');
INSERT INTO `district` VALUES ('610927', '陕西省安康市镇坪县');
INSERT INTO `district` VALUES ('610928', '陕西省安康市旬阳县');
INSERT INTO `district` VALUES ('610929', '陕西省安康市白河县');
INSERT INTO `district` VALUES ('611000', '陕西省商洛市');
INSERT INTO `district` VALUES ('611001', '陕西省商洛市市辖区');
INSERT INTO `district` VALUES ('611002', '陕西省商洛市商州区');
INSERT INTO `district` VALUES ('611021', '陕西省商洛市洛南县');
INSERT INTO `district` VALUES ('611022', '陕西省商洛市丹凤县');
INSERT INTO `district` VALUES ('611023', '陕西省商洛市商南县');
INSERT INTO `district` VALUES ('611024', '陕西省商洛市山阳县');
INSERT INTO `district` VALUES ('611025', '陕西省商洛市镇安县');
INSERT INTO `district` VALUES ('611026', '陕西省商洛市柞水县');
INSERT INTO `district` VALUES ('612400', '陕西省安康地区');
INSERT INTO `district` VALUES ('612401', '陕西省安康地区安康市');
INSERT INTO `district` VALUES ('612422', '陕西省安康地区汉阴县');
INSERT INTO `district` VALUES ('612423', '陕西省安康地区石泉县');
INSERT INTO `district` VALUES ('612424', '陕西省安康地区宁陕县');
INSERT INTO `district` VALUES ('612425', '陕西省安康地区紫阳县');
INSERT INTO `district` VALUES ('612426', '陕西省安康地区岚皋县');
INSERT INTO `district` VALUES ('612427', '陕西省安康地区平利县');
INSERT INTO `district` VALUES ('612428', '陕西省安康地区镇坪县');
INSERT INTO `district` VALUES ('612429', '陕西省安康地区旬阳县');
INSERT INTO `district` VALUES ('612430', '陕西省安康地区白河县');
INSERT INTO `district` VALUES ('612500', '陕西省商洛地区');
INSERT INTO `district` VALUES ('612501', '陕西省商洛地区商州市');
INSERT INTO `district` VALUES ('612522', '陕西省商洛地区洛南县');
INSERT INTO `district` VALUES ('612523', '陕西省商洛地区丹凤县');
INSERT INTO `district` VALUES ('612524', '陕西省商洛地区商南县');
INSERT INTO `district` VALUES ('612525', '陕西省商洛地区山阳县');
INSERT INTO `district` VALUES ('612526', '陕西省商洛地区镇安县');
INSERT INTO `district` VALUES ('612527', '陕西省商洛地区柞水县');
INSERT INTO `district` VALUES ('612700', '陕西省榆林地区');
INSERT INTO `district` VALUES ('612701', '陕西省榆林地区榆林市');
INSERT INTO `district` VALUES ('612722', '陕西省榆林地区神木县');
INSERT INTO `district` VALUES ('612723', '陕西省榆林地区府谷县');
INSERT INTO `district` VALUES ('612724', '陕西省榆林地区横山县');
INSERT INTO `district` VALUES ('612725', '陕西省榆林地区靖边县');
INSERT INTO `district` VALUES ('612726', '陕西省榆林地区定边县');
INSERT INTO `district` VALUES ('612727', '陕西省榆林地区绥德县');
INSERT INTO `district` VALUES ('612728', '陕西省榆林地区米脂县');
INSERT INTO `district` VALUES ('612729', '陕西省榆林地区佳县');
INSERT INTO `district` VALUES ('612730', '陕西省榆林地区吴堡县');
INSERT INTO `district` VALUES ('612731', '陕西省榆林地区清涧县');
INSERT INTO `district` VALUES ('612732', '陕西省榆林地区子洲县');
INSERT INTO `district` VALUES ('620000', '甘肃省');
INSERT INTO `district` VALUES ('620100', '甘肃省兰州市');
INSERT INTO `district` VALUES ('620101', '甘肃省兰州市市辖区');
INSERT INTO `district` VALUES ('620102', '甘肃省兰州市城关区');
INSERT INTO `district` VALUES ('620103', '甘肃省兰州市七里河区');
INSERT INTO `district` VALUES ('620104', '甘肃省兰州市西固区');
INSERT INTO `district` VALUES ('620105', '甘肃省兰州市安宁区');
INSERT INTO `district` VALUES ('620111', '甘肃省兰州市红古区');
INSERT INTO `district` VALUES ('620121', '甘肃省兰州市永登县');
INSERT INTO `district` VALUES ('620122', '甘肃省兰州市皋兰县');
INSERT INTO `district` VALUES ('620123', '甘肃省兰州市榆中县');
INSERT INTO `district` VALUES ('620200', '甘肃省嘉峪关市');
INSERT INTO `district` VALUES ('620201', '甘肃省嘉峪关市市辖区');
INSERT INTO `district` VALUES ('620300', '甘肃省金昌市');
INSERT INTO `district` VALUES ('620301', '甘肃省金昌市市辖区');
INSERT INTO `district` VALUES ('620302', '甘肃省金昌市金川区');
INSERT INTO `district` VALUES ('620321', '甘肃省金昌市永昌县');
INSERT INTO `district` VALUES ('620400', '甘肃省白银市');
INSERT INTO `district` VALUES ('620401', '甘肃省白银市市辖区');
INSERT INTO `district` VALUES ('620402', '甘肃省白银市白银区');
INSERT INTO `district` VALUES ('620403', '甘肃省白银市平川区');
INSERT INTO `district` VALUES ('620421', '甘肃省白银市靖远县');
INSERT INTO `district` VALUES ('620422', '甘肃省白银市会宁县');
INSERT INTO `district` VALUES ('620423', '甘肃省白银市景泰县');
INSERT INTO `district` VALUES ('620500', '甘肃省天水市');
INSERT INTO `district` VALUES ('620501', '甘肃省天水市市辖区');
INSERT INTO `district` VALUES ('620502', '甘肃省天水市秦城区');
INSERT INTO `district` VALUES ('620503', '甘肃省天水市北道区');
INSERT INTO `district` VALUES ('620521', '甘肃省天水市清水县');
INSERT INTO `district` VALUES ('620522', '甘肃省天水市秦安县');
INSERT INTO `district` VALUES ('620523', '甘肃省天水市甘谷县');
INSERT INTO `district` VALUES ('620524', '甘肃省天水市武山县');
INSERT INTO `district` VALUES ('620525', '甘肃省天水市张家川回族自治县');
INSERT INTO `district` VALUES ('620600', '甘肃省武威市');
INSERT INTO `district` VALUES ('620601', '甘肃省武威市市辖区');
INSERT INTO `district` VALUES ('620602', '甘肃省武威市凉州区');
INSERT INTO `district` VALUES ('620621', '甘肃省武威市民勤县');
INSERT INTO `district` VALUES ('620622', '甘肃省武威市古浪县');
INSERT INTO `district` VALUES ('620623', '甘肃省武威市天祝藏族自治县');
INSERT INTO `district` VALUES ('620700', '甘肃省张掖市');
INSERT INTO `district` VALUES ('620701', '甘肃省张掖市市辖区');
INSERT INTO `district` VALUES ('620702', '甘肃省张掖市甘州区');
INSERT INTO `district` VALUES ('620721', '甘肃省张掖市肃南裕固族自治县');
INSERT INTO `district` VALUES ('620722', '甘肃省张掖市民乐县');
INSERT INTO `district` VALUES ('620723', '甘肃省张掖市临泽县');
INSERT INTO `district` VALUES ('620724', '甘肃省张掖市高台县');
INSERT INTO `district` VALUES ('620725', '甘肃省张掖市山丹县');
INSERT INTO `district` VALUES ('620800', '甘肃省平凉市');
INSERT INTO `district` VALUES ('620801', '甘肃省平凉市市辖区');
INSERT INTO `district` VALUES ('620802', '甘肃省平凉市崆峒区');
INSERT INTO `district` VALUES ('620821', '甘肃省平凉市泾川县');
INSERT INTO `district` VALUES ('620822', '甘肃省平凉市灵台县');
INSERT INTO `district` VALUES ('620823', '甘肃省平凉市崇信县');
INSERT INTO `district` VALUES ('620824', '甘肃省平凉市华亭县');
INSERT INTO `district` VALUES ('620825', '甘肃省平凉市庄浪县');
INSERT INTO `district` VALUES ('620826', '甘肃省平凉市静宁县');
INSERT INTO `district` VALUES ('620900', '甘肃省酒泉市');
INSERT INTO `district` VALUES ('620901', '甘肃省酒泉市市辖区');
INSERT INTO `district` VALUES ('620902', '甘肃省酒泉市肃州区');
INSERT INTO `district` VALUES ('620921', '甘肃省酒泉市金塔县');
INSERT INTO `district` VALUES ('620922', '甘肃省酒泉市安西县');
INSERT INTO `district` VALUES ('620923', '甘肃省酒泉市肃北蒙古族自治县');
INSERT INTO `district` VALUES ('620924', '甘肃省酒泉市阿克塞哈萨克族自治县');
INSERT INTO `district` VALUES ('620981', '甘肃省酒泉市玉门市');
INSERT INTO `district` VALUES ('620982', '甘肃省酒泉市敦煌市');
INSERT INTO `district` VALUES ('621000', '甘肃省庆阳市');
INSERT INTO `district` VALUES ('621001', '甘肃省庆阳市市辖区');
INSERT INTO `district` VALUES ('621002', '甘肃省庆阳市西峰区');
INSERT INTO `district` VALUES ('621021', '甘肃省庆阳市庆阳县');
INSERT INTO `district` VALUES ('621022', '甘肃省庆阳市环县');
INSERT INTO `district` VALUES ('621023', '甘肃省庆阳市华池县');
INSERT INTO `district` VALUES ('621024', '甘肃省庆阳市合水县');
INSERT INTO `district` VALUES ('621025', '甘肃省庆阳市正宁县');
INSERT INTO `district` VALUES ('621026', '甘肃省庆阳市宁县');
INSERT INTO `district` VALUES ('621027', '甘肃省庆阳市镇原县');
INSERT INTO `district` VALUES ('621100', '甘肃省定西市');
INSERT INTO `district` VALUES ('621101', '甘肃省定西市市辖区');
INSERT INTO `district` VALUES ('621102', '甘肃省定西市安定区');
INSERT INTO `district` VALUES ('621121', '甘肃省定西市通渭县');
INSERT INTO `district` VALUES ('621122', '甘肃省定西市陇西县');
INSERT INTO `district` VALUES ('621123', '甘肃省定西市渭源县');
INSERT INTO `district` VALUES ('621124', '甘肃省定西市临洮县');
INSERT INTO `district` VALUES ('621125', '甘肃省定西市漳县');
INSERT INTO `district` VALUES ('621126', '甘肃省定西市岷县');
INSERT INTO `district` VALUES ('622100', '甘肃省酒泉地区');
INSERT INTO `district` VALUES ('622101', '甘肃省酒泉地区玉门市');
INSERT INTO `district` VALUES ('622102', '甘肃省酒泉地区酒泉市');
INSERT INTO `district` VALUES ('622103', '甘肃省酒泉地区敦煌市');
INSERT INTO `district` VALUES ('622123', '甘肃省酒泉地区金塔县');
INSERT INTO `district` VALUES ('622124', '甘肃省酒泉地区肃北蒙古族自治县');
INSERT INTO `district` VALUES ('622125', '甘肃省酒泉地区阿克塞哈萨克族自治县');
INSERT INTO `district` VALUES ('622126', '甘肃省酒泉地区安西县');
INSERT INTO `district` VALUES ('622200', '甘肃省张掖地区');
INSERT INTO `district` VALUES ('622201', '甘肃省张掖地区张掖市');
INSERT INTO `district` VALUES ('622222', '甘肃省张掖地区肃南裕固族自治县');
INSERT INTO `district` VALUES ('622223', '甘肃省张掖地区民乐县');
INSERT INTO `district` VALUES ('622224', '甘肃省张掖地区临泽县');
INSERT INTO `district` VALUES ('622225', '甘肃省张掖地区高台县');
INSERT INTO `district` VALUES ('622226', '甘肃省张掖地区山丹县');
INSERT INTO `district` VALUES ('622300', '甘肃省武威地区');
INSERT INTO `district` VALUES ('622301', '甘肃省武威地区武威市');
INSERT INTO `district` VALUES ('622322', '甘肃省武威地区民勤县');
INSERT INTO `district` VALUES ('622323', '甘肃省武威地区古浪县');
INSERT INTO `district` VALUES ('622326', '甘肃省武威地区天祝藏族自治县');
INSERT INTO `district` VALUES ('622400', '甘肃省定西地区');
INSERT INTO `district` VALUES ('622421', '甘肃省定西地区定西县');
INSERT INTO `district` VALUES ('622424', '甘肃省定西地区通渭县');
INSERT INTO `district` VALUES ('622425', '甘肃省定西地区陇西县');
INSERT INTO `district` VALUES ('622426', '甘肃省定西地区渭源县');
INSERT INTO `district` VALUES ('622427', '甘肃省定西地区临洮县');
INSERT INTO `district` VALUES ('622428', '甘肃省定西地区漳县');
INSERT INTO `district` VALUES ('622429', '甘肃省定西地区岷县');
INSERT INTO `district` VALUES ('622600', '甘肃省陇南地区');
INSERT INTO `district` VALUES ('622621', '甘肃省陇南地区武都县');
INSERT INTO `district` VALUES ('622623', '甘肃省陇南地区宕昌县');
INSERT INTO `district` VALUES ('622624', '甘肃省陇南地区成县');
INSERT INTO `district` VALUES ('622625', '甘肃省陇南地区康县');
INSERT INTO `district` VALUES ('622626', '甘肃省陇南地区文县');
INSERT INTO `district` VALUES ('622627', '甘肃省陇南地区西和县');
INSERT INTO `district` VALUES ('622628', '甘肃省陇南地区礼县');
INSERT INTO `district` VALUES ('622629', '甘肃省陇南地区两当县');
INSERT INTO `district` VALUES ('622630', '甘肃省陇南地区徽县');
INSERT INTO `district` VALUES ('622700', '甘肃省平凉地区');
INSERT INTO `district` VALUES ('622701', '甘肃省平凉地区平凉市');
INSERT INTO `district` VALUES ('622722', '甘肃省平凉地区泾川县');
INSERT INTO `district` VALUES ('622723', '甘肃省平凉地区灵台县');
INSERT INTO `district` VALUES ('622724', '甘肃省平凉地区崇信县');
INSERT INTO `district` VALUES ('622725', '甘肃省平凉地区华亭县');
INSERT INTO `district` VALUES ('622726', '甘肃省平凉地区庄浪县');
INSERT INTO `district` VALUES ('622727', '甘肃省平凉地区静宁县');
INSERT INTO `district` VALUES ('622800', '甘肃省庆阳地区');
INSERT INTO `district` VALUES ('622801', '甘肃省庆阳地区西峰市');
INSERT INTO `district` VALUES ('622821', '甘肃省庆阳地区庆阳县');
INSERT INTO `district` VALUES ('622822', '甘肃省庆阳地区环县');
INSERT INTO `district` VALUES ('622823', '甘肃省庆阳地区华池县');
INSERT INTO `district` VALUES ('622824', '甘肃省庆阳地区合水县');
INSERT INTO `district` VALUES ('622825', '甘肃省庆阳地区正宁县');
INSERT INTO `district` VALUES ('622826', '甘肃省庆阳地区宁县');
INSERT INTO `district` VALUES ('622827', '甘肃省庆阳地区镇原县');
INSERT INTO `district` VALUES ('622900', '甘肃省临夏回族自治州');
INSERT INTO `district` VALUES ('622901', '甘肃省临夏回族自治州临夏市');
INSERT INTO `district` VALUES ('622921', '甘肃省临夏回族自治州临夏县');
INSERT INTO `district` VALUES ('622922', '甘肃省临夏回族自治州康乐县');
INSERT INTO `district` VALUES ('622923', '甘肃省临夏回族自治州永靖县');
INSERT INTO `district` VALUES ('622924', '甘肃省临夏回族自治州广河县');
INSERT INTO `district` VALUES ('622925', '甘肃省临夏回族自治州和政县');
INSERT INTO `district` VALUES ('622926', '甘肃省临夏回族自治州东乡族自治县');
INSERT INTO `district` VALUES ('622927', '甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县');
INSERT INTO `district` VALUES ('623000', '甘肃省甘南藏族自治州');
INSERT INTO `district` VALUES ('623001', '甘肃省甘南藏族自治州合作市');
INSERT INTO `district` VALUES ('623021', '甘肃省甘南藏族自治州临潭县');
INSERT INTO `district` VALUES ('623022', '甘肃省甘南藏族自治州卓尼县');
INSERT INTO `district` VALUES ('623023', '甘肃省甘南藏族自治州舟曲县');
INSERT INTO `district` VALUES ('623024', '甘肃省甘南藏族自治州迭部县');
INSERT INTO `district` VALUES ('623025', '甘肃省甘南藏族自治州玛曲县');
INSERT INTO `district` VALUES ('623026', '甘肃省甘南藏族自治州碌曲县');
INSERT INTO `district` VALUES ('623027', '甘肃省甘南藏族自治州夏河县');
INSERT INTO `district` VALUES ('630000', '青海省');
INSERT INTO `district` VALUES ('630100', '青海省西宁市');
INSERT INTO `district` VALUES ('630101', '青海省西宁市市辖区');
INSERT INTO `district` VALUES ('630102', '青海省西宁市城东区');
INSERT INTO `district` VALUES ('630103', '青海省西宁市城中区');
INSERT INTO `district` VALUES ('630104', '青海省西宁市城西区');
INSERT INTO `district` VALUES ('630105', '青海省西宁市城北区');
INSERT INTO `district` VALUES ('630121', '青海省西宁市大通回族土族自治县');
INSERT INTO `district` VALUES ('630122', '青海省西宁市湟中县');
INSERT INTO `district` VALUES ('630123', '青海省西宁市湟源县');
INSERT INTO `district` VALUES ('632100', '青海省海东地区');
INSERT INTO `district` VALUES ('632121', '青海省海东地区平安县');
INSERT INTO `district` VALUES ('632122', '青海省海东地区民和回族土族自治县');
INSERT INTO `district` VALUES ('632123', '青海省海东地区乐都县');
INSERT INTO `district` VALUES ('632124', '青海省海东地区湟中县');
INSERT INTO `district` VALUES ('632125', '青海省海东地区湟源县');
INSERT INTO `district` VALUES ('632126', '青海省海东地区互助土族自治县');
INSERT INTO `district` VALUES ('632127', '青海省海东地区化隆回族自治县');
INSERT INTO `district` VALUES ('632128', '青海省海东地区循化撒拉族自治县');
INSERT INTO `district` VALUES ('632200', '青海省海北藏族自治州');
INSERT INTO `district` VALUES ('632221', '青海省海北藏族自治州门源回族自治县');
INSERT INTO `district` VALUES ('632222', '青海省海北藏族自治州祁连县');
INSERT INTO `district` VALUES ('632223', '青海省海北藏族自治州海晏县');
INSERT INTO `district` VALUES ('632224', '青海省海北藏族自治州刚察县');
INSERT INTO `district` VALUES ('632300', '青海省黄南藏族自治州');
INSERT INTO `district` VALUES ('632321', '青海省黄南藏族自治州同仁县');
INSERT INTO `district` VALUES ('632322', '青海省黄南藏族自治州尖扎县');
INSERT INTO `district` VALUES ('632323', '青海省黄南藏族自治州泽库县');
INSERT INTO `district` VALUES ('632324', '青海省黄南藏族自治州河南蒙古族自治县');
INSERT INTO `district` VALUES ('632500', '青海省海南藏族自治州');
INSERT INTO `district` VALUES ('632521', '青海省海南藏族自治州共和县');
INSERT INTO `district` VALUES ('632522', '青海省海南藏族自治州同德县');
INSERT INTO `district` VALUES ('632523', '青海省海南藏族自治州贵德县');
INSERT INTO `district` VALUES ('632524', '青海省海南藏族自治州兴海县');
INSERT INTO `district` VALUES ('632525', '青海省海南藏族自治州贵南县');
INSERT INTO `district` VALUES ('632600', '青海省果洛藏族自治州');
INSERT INTO `district` VALUES ('632621', '青海省果洛藏族自治州玛沁县');
INSERT INTO `district` VALUES ('632622', '青海省果洛藏族自治州班玛县');
INSERT INTO `district` VALUES ('632623', '青海省果洛藏族自治州甘德县');
INSERT INTO `district` VALUES ('632624', '青海省果洛藏族自治州达日县');
INSERT INTO `district` VALUES ('632625', '青海省果洛藏族自治州久治县');
INSERT INTO `district` VALUES ('632626', '青海省果洛藏族自治州玛多县');
INSERT INTO `district` VALUES ('632700', '青海省玉树藏族自治州');
INSERT INTO `district` VALUES ('632721', '青海省玉树藏族自治州玉树县');
INSERT INTO `district` VALUES ('632722', '青海省玉树藏族自治州杂多县');
INSERT INTO `district` VALUES ('632723', '青海省玉树藏族自治州称多县');
INSERT INTO `district` VALUES ('632724', '青海省玉树藏族自治州治多县');
INSERT INTO `district` VALUES ('632725', '青海省玉树藏族自治州囊谦县');
INSERT INTO `district` VALUES ('632726', '青海省玉树藏族自治州曲麻莱县');
INSERT INTO `district` VALUES ('632800', '青海省海西蒙古族藏族自治州');
INSERT INTO `district` VALUES ('632801', '青海省海西蒙古族藏族自治州格尔木市');
INSERT INTO `district` VALUES ('632802', '青海省海西蒙古族藏族自治州德令哈市');
INSERT INTO `district` VALUES ('632821', '青海省海西蒙古族藏族自治州乌兰县');
INSERT INTO `district` VALUES ('632822', '青海省海西蒙古族藏族自治州都兰县');
INSERT INTO `district` VALUES ('632823', '青海省海西蒙古族藏族自治州天峻县');
INSERT INTO `district` VALUES ('640000', '宁夏');
INSERT INTO `district` VALUES ('640100', '宁夏回族自治区银川市');
INSERT INTO `district` VALUES ('640101', '宁夏回族自治区银川市市辖区');
INSERT INTO `district` VALUES ('640102', '宁夏回族自治区银川市城区');
INSERT INTO `district` VALUES ('640103', '宁夏回族自治区银川市新城区');
INSERT INTO `district` VALUES ('640104', '宁夏回族自治区银川市兴庆区');
INSERT INTO `district` VALUES ('640105', '宁夏回族自治区银川市西夏区');
INSERT INTO `district` VALUES ('640106', '宁夏回族自治区银川市金凤区');
INSERT INTO `district` VALUES ('640111', '宁夏回族自治区银川市郊区');
INSERT INTO `district` VALUES ('640121', '宁夏回族自治区银川市永宁县');
INSERT INTO `district` VALUES ('640122', '宁夏回族自治区银川市贺兰县');
INSERT INTO `district` VALUES ('640181', '宁夏回族自治区银川市灵武市');
INSERT INTO `district` VALUES ('640200', '宁夏回族自治区石嘴山市');
INSERT INTO `district` VALUES ('640201', '宁夏回族自治区石嘴山市市辖区');
INSERT INTO `district` VALUES ('640202', '宁夏回族自治区石嘴山市大武口区');
INSERT INTO `district` VALUES ('640203', '宁夏回族自治区石嘴山市石嘴山区');
INSERT INTO `district` VALUES ('640204', '宁夏回族自治区石嘴山市石炭井区');
INSERT INTO `district` VALUES ('640205', '宁夏回族自治区石嘴山市惠农区');
INSERT INTO `district` VALUES ('640221', '宁夏回族自治区石嘴山市平罗县');
INSERT INTO `district` VALUES ('640222', '宁夏回族自治区石嘴山市陶乐县');
INSERT INTO `district` VALUES ('640223', '宁夏回族自治区石嘴山市惠农县');
INSERT INTO `district` VALUES ('640300', '宁夏回族自治区吴忠市');
INSERT INTO `district` VALUES ('640301', '宁夏回族自治区吴忠市市辖区');
INSERT INTO `district` VALUES ('640302', '宁夏回族自治区吴忠市利通区');
INSERT INTO `district` VALUES ('640321', '宁夏回族自治区吴忠市中卫县');
INSERT INTO `district` VALUES ('640322', '宁夏回族自治区吴忠市中宁县');
INSERT INTO `district` VALUES ('640323', '宁夏回族自治区吴忠市盐池县');
INSERT INTO `district` VALUES ('640324', '宁夏回族自治区吴忠市同心县');
INSERT INTO `district` VALUES ('640381', '宁夏回族自治区吴忠市青铜峡市');
INSERT INTO `district` VALUES ('640382', '宁夏回族自治区吴忠市灵武市');
INSERT INTO `district` VALUES ('640400', '宁夏回族自治区固原市');
INSERT INTO `district` VALUES ('640401', '宁夏回族自治区固原市市辖区');
INSERT INTO `district` VALUES ('640402', '宁夏回族自治区固原市原州区');
INSERT INTO `district` VALUES ('640421', '宁夏回族自治区固原市海原县');
INSERT INTO `district` VALUES ('640422', '宁夏回族自治区固原市西吉县');
INSERT INTO `district` VALUES ('640423', '宁夏回族自治区固原市隆德县');
INSERT INTO `district` VALUES ('640424', '宁夏回族自治区固原市泾源县');
INSERT INTO `district` VALUES ('640425', '宁夏回族自治区固原市彭阳县');
INSERT INTO `district` VALUES ('640500', '宁夏回族自治区中卫市');
INSERT INTO `district` VALUES ('640501', '宁夏回族自治区中卫市市辖区');
INSERT INTO `district` VALUES ('640502', '宁夏回族自治区中卫市沙坡头区');
INSERT INTO `district` VALUES ('640521', '宁夏回族自治区中卫市中宁县');
INSERT INTO `district` VALUES ('640522', '宁夏回族自治区中卫市海原县');
INSERT INTO `district` VALUES ('642200', '宁夏回族自治区固原地区');
INSERT INTO `district` VALUES ('642221', '宁夏回族自治区固原地区固原县');
INSERT INTO `district` VALUES ('642222', '宁夏回族自治区固原地区海原县');
INSERT INTO `district` VALUES ('642223', '宁夏回族自治区固原地区西吉县');
INSERT INTO `district` VALUES ('642224', '宁夏回族自治区固原地区隆德县');
INSERT INTO `district` VALUES ('642225', '宁夏回族自治区固原地区泾源县');
INSERT INTO `district` VALUES ('642226', '宁夏回族自治区固原地区彭阳县');
INSERT INTO `district` VALUES ('650000', '新疆');
INSERT INTO `district` VALUES ('650100', '新疆维吾尔自治区乌鲁木齐市');
INSERT INTO `district` VALUES ('650101', '新疆维吾尔自治区乌鲁木齐市市辖区');
INSERT INTO `district` VALUES ('650102', '新疆维吾尔自治区乌鲁木齐市天山区');
INSERT INTO `district` VALUES ('650103', '新疆维吾尔自治区乌鲁木齐市沙依巴克区');
INSERT INTO `district` VALUES ('650104', '新疆维吾尔自治区乌鲁木齐市新市区');
INSERT INTO `district` VALUES ('650105', '新疆维吾尔自治区乌鲁木齐市水磨沟区');
INSERT INTO `district` VALUES ('650106', '新疆维吾尔自治区乌鲁木齐市头屯河区');
INSERT INTO `district` VALUES ('650107', '新疆维吾尔自治区乌鲁木齐市南山矿区');
INSERT INTO `district` VALUES ('650108', '新疆维吾尔自治区乌鲁木齐市东山区');
INSERT INTO `district` VALUES ('650121', '新疆维吾尔自治区乌鲁木齐市乌鲁木齐县');
INSERT INTO `district` VALUES ('650200', '新疆维吾尔自治区克拉玛依市');
INSERT INTO `district` VALUES ('650201', '新疆维吾尔自治区克拉玛依市市辖区');
INSERT INTO `district` VALUES ('650202', '新疆维吾尔自治区克拉玛依市独山子区');
INSERT INTO `district` VALUES ('650204', '新疆维吾尔自治区克拉玛依市白碱滩区');
INSERT INTO `district` VALUES ('650205', '新疆维吾尔自治区克拉玛依市乌尔禾区');
INSERT INTO `district` VALUES ('652100', '新疆维吾尔自治区吐鲁番地区');
INSERT INTO `district` VALUES ('652101', '新疆维吾尔自治区吐鲁番地区吐鲁番市');
INSERT INTO `district` VALUES ('652122', '新疆维吾尔自治区吐鲁番地区鄯善县');
INSERT INTO `district` VALUES ('652123', '新疆维吾尔自治区吐鲁番地区托克逊县');
INSERT INTO `district` VALUES ('652200', '新疆维吾尔自治区哈密地区');
INSERT INTO `district` VALUES ('652201', '新疆维吾尔自治区哈密地区哈密市');
INSERT INTO `district` VALUES ('652222', '新疆维吾尔自治区哈密地区巴里坤哈萨克自治县');
INSERT INTO `district` VALUES ('652223', '新疆维吾尔自治区哈密地区伊吾县');
INSERT INTO `district` VALUES ('652300', '新疆维吾尔自治区昌吉回族自治州');
INSERT INTO `district` VALUES ('652301', '新疆维吾尔自治区昌吉回族自治州昌吉市');
INSERT INTO `district` VALUES ('652302', '新疆维吾尔自治区昌吉回族自治州阜康市');
INSERT INTO `district` VALUES ('652303', '新疆维吾尔自治区昌吉回族自治州米泉市');
INSERT INTO `district` VALUES ('652323', '新疆维吾尔自治区昌吉回族自治州呼图壁县');
INSERT INTO `district` VALUES ('652324', '新疆维吾尔自治区昌吉回族自治州玛纳斯县');
INSERT INTO `district` VALUES ('652325', '新疆维吾尔自治区昌吉回族自治州奇台县');
INSERT INTO `district` VALUES ('652327', '新疆维吾尔自治区昌吉回族自治州吉木萨尔县');
INSERT INTO `district` VALUES ('652328', '新疆维吾尔自治区昌吉回族自治州木垒哈萨克自治县');
INSERT INTO `district` VALUES ('652700', '新疆维吾尔自治区博尔塔拉蒙古自治州');
INSERT INTO `district` VALUES ('652701', '新疆维吾尔自治区博尔塔拉蒙古自治州博乐市');
INSERT INTO `district` VALUES ('652722', '新疆维吾尔自治区博尔塔拉蒙古自治州精河县');
INSERT INTO `district` VALUES ('652723', '新疆维吾尔自治区博尔塔拉蒙古自治州温泉县');
INSERT INTO `district` VALUES ('652800', '新疆维吾尔自治区巴音郭楞蒙古自治州');
INSERT INTO `district` VALUES ('652801', '新疆维吾尔自治区巴音郭楞蒙古自治州库尔勒市');
INSERT INTO `district` VALUES ('652822', '新疆维吾尔自治区巴音郭楞蒙古自治州轮台县');
INSERT INTO `district` VALUES ('652823', '新疆维吾尔自治区巴音郭楞蒙古自治州尉犁县');
INSERT INTO `district` VALUES ('652824', '新疆维吾尔自治区巴音郭楞蒙古自治州若羌县');
INSERT INTO `district` VALUES ('652825', '新疆维吾尔自治区巴音郭楞蒙古自治州且末县');
INSERT INTO `district` VALUES ('652826', '新疆维吾尔自治区巴音郭楞蒙古自治州焉耆回族自治县');
INSERT INTO `district` VALUES ('652827', '新疆维吾尔自治区巴音郭楞蒙古自治州和静县');
INSERT INTO `district` VALUES ('652828', '新疆维吾尔自治区巴音郭楞蒙古自治州和硕县');
INSERT INTO `district` VALUES ('652829', '新疆维吾尔自治区巴音郭楞蒙古自治州博湖县');
INSERT INTO `district` VALUES ('652900', '新疆维吾尔自治区阿克苏地区');
INSERT INTO `district` VALUES ('652901', '新疆维吾尔自治区阿克苏地区阿克苏市');
INSERT INTO `district` VALUES ('652922', '新疆维吾尔自治区阿克苏地区温宿县');
INSERT INTO `district` VALUES ('652923', '新疆维吾尔自治区阿克苏地区库车县');
INSERT INTO `district` VALUES ('652924', '新疆维吾尔自治区阿克苏地区沙雅县');
INSERT INTO `district` VALUES ('652925', '新疆维吾尔自治区阿克苏地区新和县');
INSERT INTO `district` VALUES ('652926', '新疆维吾尔自治区阿克苏地区拜城县');
INSERT INTO `district` VALUES ('652927', '新疆维吾尔自治区阿克苏地区乌什县');
INSERT INTO `district` VALUES ('652928', '新疆维吾尔自治区阿克苏地区阿瓦提县');
INSERT INTO `district` VALUES ('652929', '新疆维吾尔自治区阿克苏地区柯坪县');
INSERT INTO `district` VALUES ('653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州');
INSERT INTO `district` VALUES ('653001', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿图什市');
INSERT INTO `district` VALUES ('653022', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿克陶县');
INSERT INTO `district` VALUES ('653023', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿合奇县');
INSERT INTO `district` VALUES ('653024', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州乌恰县');
INSERT INTO `district` VALUES ('653100', '新疆维吾尔自治区喀什地区');
INSERT INTO `district` VALUES ('653101', '新疆维吾尔自治区喀什地区喀什市');
INSERT INTO `district` VALUES ('653121', '新疆维吾尔自治区喀什地区疏附县');
INSERT INTO `district` VALUES ('653122', '新疆维吾尔自治区喀什地区疏勒县');
INSERT INTO `district` VALUES ('653123', '新疆维吾尔自治区喀什地区英吉沙县');
INSERT INTO `district` VALUES ('653124', '新疆维吾尔自治区喀什地区泽普县');
INSERT INTO `district` VALUES ('653125', '新疆维吾尔自治区喀什地区莎车县');
INSERT INTO `district` VALUES ('653126', '新疆维吾尔自治区喀什地区叶城县');
INSERT INTO `district` VALUES ('653127', '新疆维吾尔自治区喀什地区麦盖提县');
INSERT INTO `district` VALUES ('653128', '新疆维吾尔自治区喀什地区岳普湖县');
INSERT INTO `district` VALUES ('653129', '新疆维吾尔自治区喀什地区伽师县');
INSERT INTO `district` VALUES ('653130', '新疆维吾尔自治区喀什地区巴楚县');
INSERT INTO `district` VALUES ('653131', '新疆维吾尔自治区喀什地区塔什库尔干塔吉克自治县');
INSERT INTO `district` VALUES ('653200', '新疆维吾尔自治区和田地区');
INSERT INTO `district` VALUES ('653201', '新疆维吾尔自治区和田地区和田市');
INSERT INTO `district` VALUES ('653221', '新疆维吾尔自治区和田地区和田县');
INSERT INTO `district` VALUES ('653222', '新疆维吾尔自治区和田地区墨玉县');
INSERT INTO `district` VALUES ('653223', '新疆维吾尔自治区和田地区皮山县');
INSERT INTO `district` VALUES ('653224', '新疆维吾尔自治区和田地区洛浦县');
INSERT INTO `district` VALUES ('653225', '新疆维吾尔自治区和田地区策勒县');
INSERT INTO `district` VALUES ('653226', '新疆维吾尔自治区和田地区于田县');
INSERT INTO `district` VALUES ('653227', '新疆维吾尔自治区和田地区民丰县');
INSERT INTO `district` VALUES ('654000', '新疆维吾尔自治区伊犁哈萨克自治州');
INSERT INTO `district` VALUES ('654001', '新疆维吾尔自治区伊犁哈萨克自治州奎屯市');
INSERT INTO `district` VALUES ('654002', '新疆维吾尔自治区伊犁哈萨克自治州伊宁市');
INSERT INTO `district` VALUES ('654003', '新疆维吾尔自治区伊犁哈萨克自治州奎屯市');
INSERT INTO `district` VALUES ('654021', '新疆维吾尔自治区伊犁哈萨克自治州伊宁县');
INSERT INTO `district` VALUES ('654022', '新疆维吾尔自治区伊犁哈萨克自治州察布查尔锡伯自治县');
INSERT INTO `district` VALUES ('654023', '新疆维吾尔自治区伊犁哈萨克自治州霍城县');
INSERT INTO `district` VALUES ('654024', '新疆维吾尔自治区伊犁哈萨克自治州巩留县');
INSERT INTO `district` VALUES ('654025', '新疆维吾尔自治区伊犁哈萨克自治州新源县');
INSERT INTO `district` VALUES ('654026', '新疆维吾尔自治区伊犁哈萨克自治州昭苏县');
INSERT INTO `district` VALUES ('654027', '新疆维吾尔自治区伊犁哈萨克自治州特克斯县');
INSERT INTO `district` VALUES ('654028', '新疆维吾尔自治区伊犁哈萨克自治州尼勒克县');
INSERT INTO `district` VALUES ('654100', '新疆维吾尔自治区伊犁地区');
INSERT INTO `district` VALUES ('654101', '新疆维吾尔自治区伊犁地区伊宁市');
INSERT INTO `district` VALUES ('654121', '新疆维吾尔自治区伊犁地区伊宁县');
INSERT INTO `district` VALUES ('654122', '新疆维吾尔自治区伊犁地区察布查尔锡伯自治县');
INSERT INTO `district` VALUES ('654123', '新疆维吾尔自治区伊犁地区霍城县');
INSERT INTO `district` VALUES ('654124', '新疆维吾尔自治区伊犁地区巩留县');
INSERT INTO `district` VALUES ('654125', '新疆维吾尔自治区伊犁地区新源县');
INSERT INTO `district` VALUES ('654126', '新疆维吾尔自治区伊犁地区昭苏县');
INSERT INTO `district` VALUES ('654127', '新疆维吾尔自治区伊犁地区特克斯县');
INSERT INTO `district` VALUES ('654128', '新疆维吾尔自治区伊犁地区尼勒克县');
INSERT INTO `district` VALUES ('654200', '新疆维吾尔自治区塔城地区');
INSERT INTO `district` VALUES ('654201', '新疆维吾尔自治区塔城地区塔城市');
INSERT INTO `district` VALUES ('654202', '新疆维吾尔自治区塔城地区乌苏市');
INSERT INTO `district` VALUES ('654221', '新疆维吾尔自治区塔城地区额敏县');
INSERT INTO `district` VALUES ('654223', '新疆维吾尔自治区塔城地区沙湾县');
INSERT INTO `district` VALUES ('654224', '新疆维吾尔自治区塔城地区托里县');
INSERT INTO `district` VALUES ('654225', '新疆维吾尔自治区塔城地区裕民县');
INSERT INTO `district` VALUES ('654226', '新疆维吾尔自治区塔城地区和布克赛尔蒙古自治县');
INSERT INTO `district` VALUES ('654300', '新疆维吾尔自治区阿勒泰地区');
INSERT INTO `district` VALUES ('654301', '新疆维吾尔自治区阿勒泰地区阿勒泰市');
INSERT INTO `district` VALUES ('654321', '新疆维吾尔自治区阿勒泰地区布尔津县');
INSERT INTO `district` VALUES ('654322', '新疆维吾尔自治区阿勒泰地区富蕴县');
INSERT INTO `district` VALUES ('654323', '新疆维吾尔自治区阿勒泰地区福海县');
INSERT INTO `district` VALUES ('654324', '新疆维吾尔自治区阿勒泰地区哈巴河县');
INSERT INTO `district` VALUES ('654325', '新疆维吾尔自治区阿勒泰地区青河县');
INSERT INTO `district` VALUES ('654326', '新疆维吾尔自治区阿勒泰地区吉木乃县');
INSERT INTO `district` VALUES ('659000', '新疆维吾尔自治区省直辖行政单位');
INSERT INTO `district` VALUES ('659001', '新疆维吾尔自治区省直辖行政单位石河子市');
INSERT INTO `district` VALUES ('659002', '新疆维吾尔自治区省直辖行政单位阿拉尔市');
INSERT INTO `district` VALUES ('659003', '新疆维吾尔自治区省直辖行政单位图木舒克市');
INSERT INTO `district` VALUES ('659004', '新疆维吾尔自治区省直辖行政单位五家渠市');
INSERT INTO `district` VALUES ('660000', '建设兵团');

-- ----------------------------
-- Table structure for `equipment`
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(40) NOT NULL DEFAULT '' COMMENT '设备名称',
  `equ_type` varchar(20) DEFAULT NULL COMMENT '设备类型',
  `equ_Icon_code` varchar(10) DEFAULT NULL COMMENT '设备图标',
  `net_station` char(1) DEFAULT NULL COMMENT '网络位置 说明：e为外网，i为内网',
  `monitor_used` char(1) DEFAULT 'N' COMMENT '是否开启监控 说明：N未开启，Y开启',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `other_ip` varchar(255) DEFAULT NULL COMMENT '次选IP',
  `MAC` varchar(60) DEFAULT NULL COMMENT 'MAC地址',
  `subnet_mask` varchar(30) DEFAULT NULL,
  `int_or_ext` char(1) DEFAULT NULL COMMENT '链路位置 说明：外部链路e,内部链路i',
  `link_name` varchar(60) DEFAULT NULL COMMENT '链路 说明：对应ext_link或者int_link表link_name字段,根据int_or_ext判断',
  `equ_station` varchar(255) DEFAULT NULL COMMENT '设备位置 ',
  `equ_info` varchar(255) DEFAULT NULL COMMENT '设备硬件配置',
  `manufacturer` varchar(40) DEFAULT NULL,
  `model` varchar(40) DEFAULT NULL COMMENT '产品型号',
  `provider` varchar(40) DEFAULT NULL,
  `equ_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `other_phone` varchar(20) DEFAULT NULL COMMENT '其他联系方式',
  `buy_day` datetime DEFAULT NULL,
  `unrepair_day` datetime DEFAULT NULL,
  `is_key_device` varchar(1) DEFAULT '0',
  `equSysConfig` varchar(255) DEFAULT NULL,
  `equManagerDepart` varchar(255) DEFAULT NULL,
  `equ_port` varchar(255) DEFAULT '0' COMMENT 'snmp服务端口号',
  `equ_snmppwd` varchar(255) DEFAULT NULL COMMENT 'snmp密码',
  `equ_snmpver` varchar(255) DEFAULT NULL COMMENT 'snmp版本',
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `snmpver` varchar(255) DEFAULT NULL,
  `cpuuse` varchar(255) DEFAULT NULL,
  `disktotal` varchar(255) DEFAULT NULL,
  `diskuse` varchar(255) DEFAULT NULL,
  `memtotal` varchar(255) DEFAULT NULL,
  `memuse` varchar(255) DEFAULT NULL,
  `curconn` varchar(255) DEFAULT NULL,
  `equ_oidname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ip` (`ip`),
  KEY `FK4027E58E7AC796BA` (`equ_type`),
  CONSTRAINT `FK4027E58E7AC796BA` FOREIGN KEY (`equ_type`) REFERENCES `equipment_type` (`type_code`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('1', 'cms', '2004', '2004', 'i', 'Y', '192.168.2.131', '127.0.0.1', '00-0c-29-6d-98-df', '255.255.255.0', 'i', 'B/SAccess', '/data', '', '', '', '', '', '', null, null, '1', null, '', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('8', 'vpn', '0000', '1005', 'i', 'Y', '192.168.20.20', '192.168.10.20,192.168.20.20', '00-22-46-0f-e1-c5', '255.255.255.0', 'i', 'B/SAccess', '', '无线VPN网关', '中盾安全技术', '4020', null, '', '', null, null, '1', '/usr/app/cms/data/vpn.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('9', 'ca', '4007', '2002', 'i', 'Y', '192.168.20.21', '192.168.20.21', '00-0c-29-1b-6e-ee', '255.255.255.0', 'i', 'B/SAccess', '', '', '', '', '', '', '', '2012-04-10 13:45:50', '2016-04-10 13:45:52', '1', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('10', 'jbpg', '4008', '2003', 'i', 'Y', '192.168.20.22', '192.168.20.22,192.168.20.22', '00-90-0b-22-52-84', '255.255.255.0', 'i', 'B/SAccess', '', null, '中盾安全技术', '4020', null, '', '', null, null, '1', '/usr/app/cms/data/jbpg.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('11', 'bsmams', '4004', '2002', 'i', 'Y', '192.168.20.62', '192.168.20.62,130.130.0.1', '00-22-46-11-91-29', '255.255.255.0', 'i', 'B/SAccess', '', '移动应用管理系统', '中盾安全技术', '4020', null, '', '', null, null, '1', '/usr/app/cms/data/bsmams.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('12', 'bsmaps', '4003', '2001', 'i', 'Y', '3.3.3.3', '3.3.3.3', '00-0e-f5-01-2f-69', '255.255.255.0', 'i', 'B/SAccess', '', '移动应用代理系统', '中盾安全技术', '4020', null, '', '', null, null, '1', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('13', 'usg-fw', '1001', '1001', 'i', 'Y', '5.5.5.5', '171.168.1.2,10.0.0.1,192.168.10.10,5.5.5.5,192.168.34.1,192.168.35.1', '01-02-0e-F4-0f-06', '255.255.255.0', 'i', 'B/SAccess', '', null, '中网科技', 'EX-520', null, '', '', null, null, '1', '/usr/app/cms/data/usg-fw.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('14', 'wangza-wai', '4005', '1002', 'i', 'Y', '1.1.1.1', '1.1.1.1,2.2.2.2,3.3.3.3,1.1.1.72,130.130.0.5', '00-0e-f5-01-2f-69', '255.255.255.0', 'i', 'B/SAccess', '', null, '中网科技', 'X-GAP8500', null, '', '', null, null, '1', '/usr/app/cms/data/wangza-wai.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('15', 'wangza-nei', '4005', '1002', 'i', 'Y', '2.2.2.2', '1.1.1.1,130.130.0.1,192.168.20.20,192.168.20.21,192.168.20.72,192.168.30.71', '00-0e-f5-01-2f-69', '255.255.255.0', 'i', 'B/SAccess', '', null, '中网科技', 'X-GAP8500', null, '', '', null, null, '0', '/usr/app/cms/data/wangza-nei.zip', '中盾', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('16', 'BS查询服务器', '2001', '2001', 'i', 'N', '192.168.30.30', '192.168.30.30', '00-19-B9-24-19-98', '255.255.255.0', 'i', 'B/SAccess', '公安通信网210机柜', '', 'Dell ', 'Optiplex745', '', '', '', null, null, '0', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('17', 'zdjg', '1030', '1001', 'e', 'Y', '192.168.20.23', '192.168.20.23', '00-00-00-00-00-00', '255.255.255.0', 'e', '联通3G移动警务接入', '接入安全区', 'zdjg', '中盾', '', '', '', '', null, null, '1', null, 'zdjg', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `equipment` VALUES ('41', 'qxppc', '0000', '1001', 'i', 'Y', '172.16.2.6', '(例):1.1.1.1', '(例):0a-45-bE-e6-00-AF', '(例):1.1.1.1', 'e', '联通3G移动警务接入', '', '', '', '', '', '', '', null, null, '1', null, '', '161', 'public', 'v2', null, null, null, null, null, null, null, null, null, null, 'windowsos');
INSERT INTO `equipment` VALUES ('42', 'lmypc', '0000', '1001', 'e', 'Y', '172.16.2.2', '(例):1.1.1.1', '(例):0a-45-bE-e6-00-AF', '(例):1.1.1.1', 'i', 'B/SAccess', '', '', '', '', '', '', '', null, null, '1', null, '', '', '', 'v1', null, null, null, null, null, null, null, null, null, null, '');

-- ----------------------------
-- Table structure for `equipment_alert`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_alert`;
CREATE TABLE `equipment_alert` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(255) DEFAULT NULL,
  `alert_time` datetime DEFAULT NULL,
  `alert_info` varchar(255) DEFAULT NULL,
  `isread` char(1) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_alert
-- ----------------------------

-- ----------------------------
-- Table structure for `equipment_day_report`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_day_report`;
CREATE TABLE `equipment_day_report` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(255) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `report_day` int(11) DEFAULT NULL,
  `report_month` int(11) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  `breakdown_count` bigint(20) DEFAULT NULL,
  `alert_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_day_report
-- ----------------------------

-- ----------------------------
-- Table structure for `equipment_hour_report`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_hour_report`;
CREATE TABLE `equipment_hour_report` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(60) DEFAULT NULL COMMENT '设备名',
  `report_date` date DEFAULT NULL COMMENT '统计日',
  `report_time` int(11) DEFAULT NULL COMMENT '统计小时',
  `breakdown_count` int(11) DEFAULT '0' COMMENT '断开统计',
  `alert_count` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备日统计表';

-- ----------------------------
-- Records of equipment_hour_report
-- ----------------------------

-- ----------------------------
-- Table structure for `equipment_log`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_log`;
CREATE TABLE `equipment_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '日志时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志等级',
  `link_name` varchar(40) DEFAULT NULL COMMENT '链路名',
  `equipment_name` varchar(60) DEFAULT NULL COMMENT '设备名',
  `log_info` text COMMENT '日志内容',
  `link_property` varchar(255) DEFAULT NULL,
  `link_type` varchar(255) DEFAULT NULL,
  `link_Corp` varchar(255) DEFAULT NULL,
  `link_security` varchar(255) DEFAULT NULL,
  `link_amount` bigint(20) DEFAULT NULL,
  `link_bandwidth` bigint(20) DEFAULT NULL,
  `other_security` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`equipment_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备日志表';

-- ----------------------------
-- Records of equipment_log
-- ----------------------------

-- ----------------------------
-- Table structure for `equipment_month_report`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_month_report`;
CREATE TABLE `equipment_month_report` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(255) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `report_month` int(11) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  `breakdown_count` bigint(20) DEFAULT NULL,
  `alert_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_month_report
-- ----------------------------

-- ----------------------------
-- Table structure for `equipment_type`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_type`;
CREATE TABLE `equipment_type` (
  `type_code` varchar(255) NOT NULL DEFAULT '',
  `tpye_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_type
-- ----------------------------
INSERT INTO `equipment_type` VALUES ('0000', '其它');
INSERT INTO `equipment_type` VALUES ('1000', '安全及网络设备');
INSERT INTO `equipment_type` VALUES ('1001', '防火墙');
INSERT INTO `equipment_type` VALUES ('1002', '可信安全网关');
INSERT INTO `equipment_type` VALUES ('1003', '入侵检测系统');
INSERT INTO `equipment_type` VALUES ('1004', '网络防毒设备');
INSERT INTO `equipment_type` VALUES ('1005', '安全隔离设备');
INSERT INTO `equipment_type` VALUES ('1006', 'VPN网关');
INSERT INTO `equipment_type` VALUES ('1007', '入侵防御系统');
INSERT INTO `equipment_type` VALUES ('1008', ' AAA 服务器');
INSERT INTO `equipment_type` VALUES ('1009', '漏洞扫描系统');
INSERT INTO `equipment_type` VALUES ('1010', '边界安全监测设备');
INSERT INTO `equipment_type` VALUES ('1011', '应用代理服务器');
INSERT INTO `equipment_type` VALUES ('1012', '路由器');
INSERT INTO `equipment_type` VALUES ('1013', '交换机');
INSERT INTO `equipment_type` VALUES ('1014', '认证服务器');
INSERT INTO `equipment_type` VALUES ('1015', '串口线');
INSERT INTO `equipment_type` VALUES ('1030', '其他安全及网络设备');
INSERT INTO `equipment_type` VALUES ('2000', '应用服务器');
INSERT INTO `equipment_type` VALUES ('2001', 'WEB服务器');
INSERT INTO `equipment_type` VALUES ('2002', 'FTP服务器');
INSERT INTO `equipment_type` VALUES ('2003', '邮件服务器');
INSERT INTO `equipment_type` VALUES ('2004', '数据库服务器');
INSERT INTO `equipment_type` VALUES ('2020', '其他类型服务器');
INSERT INTO `equipment_type` VALUES ('3000', '终端');
INSERT INTO `equipment_type` VALUES ('3001', '台式计算机');
INSERT INTO `equipment_type` VALUES ('3002', '笔记本电脑');
INSERT INTO `equipment_type` VALUES ('3003', 'IP音视频终端');
INSERT INTO `equipment_type` VALUES ('3004', '手持终端设备');
INSERT INTO `equipment_type` VALUES ('3005', '其他终端设备');
INSERT INTO `equipment_type` VALUES ('4000', 'IPSec VPN网关');
INSERT INTO `equipment_type` VALUES ('4001', 'SSL VPN网关');
INSERT INTO `equipment_type` VALUES ('4002', '短信接入网关');
INSERT INTO `equipment_type` VALUES ('4003', 'B/S应用代理服务器');
INSERT INTO `equipment_type` VALUES ('4004', 'B/S应用管理服务器');
INSERT INTO `equipment_type` VALUES ('4005', '网络隔离设备');
INSERT INTO `equipment_type` VALUES ('4006', '');
INSERT INTO `equipment_type` VALUES ('4007', '设备证书管理中心');
INSERT INTO `equipment_type` VALUES ('4008', '鉴别评估管理服务器');
INSERT INTO `equipment_type` VALUES ('4009', '监控管理探针');
INSERT INTO `equipment_type` VALUES ('4010', '监控管理与级联服务器');

-- ----------------------------
-- Table structure for `exchange_direction`
-- ----------------------------
DROP TABLE IF EXISTS `exchange_direction`;
CREATE TABLE `exchange_direction` (
  `code` varchar(10) DEFAULT NULL,
  `code_desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据交换方向';

-- ----------------------------
-- Records of exchange_direction
-- ----------------------------
INSERT INTO `exchange_direction` VALUES ('0001', '单向入');
INSERT INTO `exchange_direction` VALUES ('0002', '单向出');
INSERT INTO `exchange_direction` VALUES ('0003', '双向');

-- ----------------------------
-- Table structure for `exchange_platform`
-- ----------------------------
DROP TABLE IF EXISTS `exchange_platform`;
CREATE TABLE `exchange_platform` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(60) NOT NULL DEFAULT '' COMMENT '交换平台名称',
  `platform_ip` varchar(20) DEFAULT NULL COMMENT '平台IP',
  `platform_port` int(11) NOT NULL DEFAULT '8090' COMMENT '平台监控端口',
  `is_encrypted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否加密 说明：N为未加密，Y加密',
  `link_name` varchar(60) DEFAULT NULL COMMENT '链路名',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '证书路径',
  `certificate_pwd` varchar(255) DEFAULT NULL COMMENT '证书密码',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='交换平台配置表';

-- ----------------------------
-- Records of exchange_platform
-- ----------------------------
INSERT INTO `exchange_platform` VALUES ('1', 'YZJW', '192.168.30.71', '8001', 'Y', 'B/SAccess', null, '', '');

-- ----------------------------
-- Table structure for `ext_link`
-- ----------------------------
DROP TABLE IF EXISTS `ext_link`;
CREATE TABLE `ext_link` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(60) NOT NULL DEFAULT '' COMMENT '链路名称',
  `link_property` varchar(20) DEFAULT NULL COMMENT '链路性质',
  `link_type` varchar(20) DEFAULT NULL,
  `link_Corp` varchar(40) DEFAULT NULL COMMENT '链路运营商',
  `link_security` varchar(255) DEFAULT NULL COMMENT '链路安全机制',
  `link_amount` int(11) DEFAULT NULL COMMENT '链路数量',
  `link_bandwidth` int(11) DEFAULT NULL COMMENT '链路带宽（M）',
  `other_security` varchar(255) DEFAULT NULL COMMENT '其他安全设施',
  `ip` varchar(50) DEFAULT NULL,
  `mask` varchar(50) DEFAULT NULL,
  `gateway` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='外部链路';

-- ----------------------------
-- Records of ext_link
-- ----------------------------
INSERT INTO `ext_link` VALUES ('3', '联通3G移动警务接入', '0001', ' 0003', '001', 'VPDN', '1', '20004', 'SSL VPN', '171.168.1.0', '255.255.255.0', '171.168.1.2');

-- ----------------------------
-- Table structure for `ext_link_property`
-- ----------------------------
DROP TABLE IF EXISTS `ext_link_property`;
CREATE TABLE `ext_link_property` (
  `code` varchar(10) DEFAULT NULL COMMENT '性质代码',
  `code_desc` varchar(40) DEFAULT NULL COMMENT '性质描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外部链路性质表';

-- ----------------------------
-- Records of ext_link_property
-- ----------------------------
INSERT INTO `ext_link_property` VALUES ('0001', '专线');
INSERT INTO `ext_link_property` VALUES ('0002', '拨号');
INSERT INTO `ext_link_property` VALUES ('其他', '保留');

-- ----------------------------
-- Table structure for `ext_link_type`
-- ----------------------------
DROP TABLE IF EXISTS `ext_link_type`;
CREATE TABLE `ext_link_type` (
  `link_type_code` varchar(10) DEFAULT NULL COMMENT '类型代码',
  `link_type_name` varchar(40) DEFAULT NULL COMMENT '类型名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外部链路类型';

-- ----------------------------
-- Records of ext_link_type
-- ----------------------------
INSERT INTO `ext_link_type` VALUES (' 0001', ' DDN');
INSERT INTO `ext_link_type` VALUES (' 0002', ' SDH');
INSERT INTO `ext_link_type` VALUES (' 0003', ' ISDN');
INSERT INTO `ext_link_type` VALUES (' 0004', ' PSTN');
INSERT INTO `ext_link_type` VALUES (' 0005', ' 双绞线');
INSERT INTO `ext_link_type` VALUES (' 0006', ' ADSL');
INSERT INTO `ext_link_type` VALUES (' 0007', ' 串口线');
INSERT INTO `ext_link_type` VALUES (' 0008', ' GSM');
INSERT INTO `ext_link_type` VALUES (' 0009', ' CDMA');
INSERT INTO `ext_link_type` VALUES (' 0010', ' GPRS');
INSERT INTO `ext_link_type` VALUES (' 保留', ' 其它');

-- ----------------------------
-- Table structure for `ext_link_vender`
-- ----------------------------
DROP TABLE IF EXISTS `ext_link_vender`;
CREATE TABLE `ext_link_vender` (
  `code` int(3) DEFAULT NULL,
  `code_desc` varchar(5) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ext_link_vender
-- ----------------------------
INSERT INTO `ext_link_vender` VALUES ('1', '中国移动');
INSERT INTO `ext_link_vender` VALUES ('2', '中国联通');
INSERT INTO `ext_link_vender` VALUES ('3', '中国电信');
INSERT INTO `ext_link_vender` VALUES ('0', '其他');

-- ----------------------------
-- Table structure for `int_link`
-- ----------------------------
DROP TABLE IF EXISTS `int_link`;
CREATE TABLE `int_link` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(60) NOT NULL DEFAULT '' COMMENT '链路名称',
  `jrdx` varchar(20) DEFAULT NULL,
  `exchange_mode` varchar(20) DEFAULT NULL COMMENT '业务交换方式',
  `link_bandwidth` int(11) DEFAULT NULL COMMENT '链路带宽（M）',
  `FW_used` char(1) DEFAULT 'N' COMMENT '是否使用防火墙',
  `sec_gateway_used` char(1) DEFAULT 'N' COMMENT '是否使用安全网关',
  `gap_used` char(1) DEFAULT 'N' COMMENT '是否使用网闸',
  `VPN_used` char(1) DEFAULT 'N' COMMENT '是否使用VPN',
  `other_security` varchar(255) DEFAULT NULL COMMENT '其他安全设施',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='内部链路表';

-- ----------------------------
-- Records of int_link
-- ----------------------------
INSERT INTO `int_link` VALUES ('5', 'B/SAccess', '移动警务', '数据交换', '1000', 'Y', 'Y', 'Y', 'Y', '');

-- ----------------------------
-- Table structure for `jbqk`
-- ----------------------------
DROP TABLE IF EXISTS `jbqk`;
CREATE TABLE `jbqk` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(60) NOT NULL DEFAULT '' COMMENT '平台名称̨',
  `platform_id` varchar(255) DEFAULT NULL COMMENT '平台标识（机构代码）',
  `system_class` varchar(5) NOT NULL DEFAULT '00000',
  `address` varchar(80) DEFAULT 'XXʡXX' COMMENT '物理地址',
  `fzr_name` varchar(20) DEFAULT '负责人姓名',
  `fzr_phone` varchar(14) DEFAULT NULL COMMENT '负责人电话',
  `fzr_email` varchar(60) DEFAULT NULL COMMENT '负责人邮件',
  `fzr_other_phone` varchar(15) DEFAULT NULL COMMENT '负责人其他联系方式',
  `jksys_ip` varchar(24) DEFAULT NULL COMMENT '本监控系统IP',
  `jsdw` varchar(6) DEFAULT NULL COMMENT '建设单位',
  `zycjdw` varchar(60) DEFAULT NULL COMMENT '主要承建部门',
  `js_day` datetime DEFAULT NULL,
  `bmxy` char(1) DEFAULT 'N' COMMENT '是否签署保密协议',
  `spdw` varchar(60) DEFAULT NULL COMMENT '审批单位（机构代码）',
  `sp_day` datetime DEFAULT NULL,
  `spph` varchar(40) DEFAULT NULL,
  `spcl` mediumblob,
  `Technology_solution` mediumblob,
  `Confidential_Agreement` mediumblob COMMENT '保密协议',
  `main_comp` varchar(60) DEFAULT NULL COMMENT '运维单位名称（代码）',
  `maintainer_name` varchar(20) DEFAULT NULL COMMENT '管理员姓名',
  `maintainer_phone` varchar(15) DEFAULT NULL COMMENT '管理员电话',
  `maintainer_email` varchar(80) DEFAULT NULL COMMENT '管理员邮箱',
  `maintainer_other_phone` varchar(15) DEFAULT NULL COMMENT '管理员其他联系方式',
  `platform_tp` mediumblob COMMENT '平台拓扑图',
  `spcl_file_name` varchar(255) DEFAULT NULL,
  `technology_solution_file_name` varchar(255) DEFAULT NULL,
  `confidential_agreement_file_name` varchar(255) DEFAULT NULL,
  `platform_tp_file_name` varchar(255) DEFAULT NULL,
  `jksys_mac` varchar(255) DEFAULT NULL,
  `mainComp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK31AD52720EE988` (`jsdw`,`main_comp`,`spdw`),
  KEY `FK31AD527212F59C` (`spdw`),
  KEY `FK31AD5262DF0BC1` (`main_comp`),
  KEY `FK31AD52BE42DC71` (`system_class`),
  CONSTRAINT `FK31AD5262DF0BC1` FOREIGN KEY (`main_comp`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK31AD52720EE988` FOREIGN KEY (`jsdw`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK31AD527212F59C` FOREIGN KEY (`spdw`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK31AD52BE42DC71` FOREIGN KEY (`system_class`) REFERENCES `sys_class` (`system_class_code`),
  CONSTRAINT `FK_jbqk_district` FOREIGN KEY (`spdw`) REFERENCES `district` (`district_id`),
  CONSTRAINT `FK_jbqk_district_2` FOREIGN KEY (`main_comp`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jbqk
-- ----------------------------
INSERT INTO `jbqk` VALUES ('1', '河南省郑州市公安信息通信网移动警务系统', 'S41010001', '00000', '北京市公安局3楼机房', '张三', '666666666', '66@66.com', '99@99.com', '192.168.30.72:8000', '410100', 'YS', '2012-04-10 12:04:34', 'N', '410000', '2010-08-30 14:47:48', '103333', null, null, null, '410000', '1', 'sss', 'sss', '12312', null, '/usr/app/cms/upload/index.html', '/usr/app/cms/upload/LDAPAction.java', '/usr/app/cms/upload/createtables.sql', '/usr/app/cms/upload100013461250.jpg', '00:a1:b0:d2:f5:89', null);

-- ----------------------------
-- Table structure for `ldapconn`
-- ----------------------------
DROP TABLE IF EXISTS `ldapconn`;
CREATE TABLE `ldapconn` (
  `ldapip` varchar(12) COLLATE utf8_bin NOT NULL,
  `ldapport` int(11) NOT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `ldapadmin` varchar(20) COLLATE utf8_bin NOT NULL,
  `ldapbasedn` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ldapconn
-- ----------------------------

-- ----------------------------
-- Table structure for `lower_sysabnormal`
-- ----------------------------
DROP TABLE IF EXISTS `lower_sysabnormal`;
CREATE TABLE `lower_sysabnormal` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Idalertmatter` int(11) DEFAULT NULL,
  `Abnormaltypecode` varchar(4) DEFAULT NULL,
  `Connectobjectcode` varchar(3) DEFAULT NULL,
  `Exceptiondesc` varchar(255) DEFAULT NULL,
  `Happentime` datetime DEFAULT NULL,
  `Treattime` datetime DEFAULT NULL,
  `Treadresult` varchar(3) DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Treadresult` (`Treadresult`),
  KEY `Connectobjectcode` (`Connectobjectcode`),
  KEY `Abnormaltypecode` (`Abnormaltypecode`),
  KEY `FK1B591DD7FC5A369B` (`Treadresult`),
  KEY `FK1B591DD7EFAB2DF` (`Abnormaltypecode`),
  KEY `FK1B591DD7BF89F786` (`Connectobjectcode`),
  CONSTRAINT `FK1B591DD7BF89F786` FOREIGN KEY (`Connectobjectcode`) REFERENCES `sysabnormal_object_type` (`code`),
  CONSTRAINT `FK1B591DD7EFAB2DF` FOREIGN KEY (`Abnormaltypecode`) REFERENCES `sysabnormal_event_code` (`code`),
  CONSTRAINT `FK1B591DD7FC5A369B` FOREIGN KEY (`Treadresult`) REFERENCES `sysabnormal_result` (`code`),
  CONSTRAINT `FK_lower_sysabnormal_sysabnormal_event_code` FOREIGN KEY (`Abnormaltypecode`) REFERENCES `sysabnormal_event_code` (`code`),
  CONSTRAINT `FK_lower_sysabnormal_sysabnormal_object_type` FOREIGN KEY (`Connectobjectcode`) REFERENCES `sysabnormal_object_type` (`code`),
  CONSTRAINT `FK_lower_sysabnormal_sysabnormal_result` FOREIGN KEY (`Treadresult`) REFERENCES `sysabnormal_result` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lower_sysabnormal
-- ----------------------------

-- ----------------------------
-- Table structure for `lower_sysbizstatus`
-- ----------------------------
DROP TABLE IF EXISTS `lower_sysbizstatus`;
CREATE TABLE `lower_sysbizstatus` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(255) DEFAULT NULL,
  `Idbiz` bigint(20) DEFAULT NULL,
  `Bizname` varchar(255) DEFAULT NULL,
  `Access` bigint(20) DEFAULT NULL,
  `Terminalnum` bigint(20) DEFAULT NULL,
  `Influx` bigint(20) DEFAULT NULL,
  `Outflux` bigint(20) DEFAULT NULL,
  `Accesssum` bigint(20) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lower_sysbizstatus
-- ----------------------------

-- ----------------------------
-- Table structure for `lower_sysruntime`
-- ----------------------------
DROP TABLE IF EXISTS `lower_sysruntime`;
CREATE TABLE `lower_sysruntime` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Runstatecode` varchar(4) DEFAULT NULL,
  `Desc` varchar(254) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lower_sysruntime
-- ----------------------------

-- ----------------------------
-- Table structure for `lower_sysstatus`
-- ----------------------------
DROP TABLE IF EXISTS `lower_sysstatus`;
CREATE TABLE `lower_sysstatus` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Access` int(8) DEFAULT NULL,
  `Terminalnum` int(8) DEFAULT NULL,
  `Influx` bigint(20) DEFAULT NULL,
  `Outflux` bigint(20) DEFAULT NULL,
  `Accesssum` int(11) DEFAULT NULL,
  `Bizsum` int(11) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lower_sysstatus
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_agent`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_agent`;
CREATE TABLE `monitor_agent` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(40) NOT NULL DEFAULT '' COMMENT '探针名',
  `agent_ip` varchar(20) DEFAULT NULL COMMENT '探针IP',
  `agent_port` int(11) DEFAULT NULL COMMENT '探针控制命令端口',
  `is_encrypted` char(1) DEFAULT 'N' COMMENT '是否加密传输',
  `link_name` varchar(60) DEFAULT NULL COMMENT '链路',
  `agent_station` char(1) DEFAULT NULL COMMENT '探针位置 说明：i为内网，e为外网',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '证书路径',
  `certificate_pwd` varchar(255) DEFAULT NULL COMMENT '证书密码',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='探针表';

-- ----------------------------
-- Records of monitor_agent
-- ----------------------------
INSERT INTO `monitor_agent` VALUES ('2', 'mc1', '192.168.30.71', '1161', 'Y', 'B/SAccess', 'i', null, '', '');
INSERT INTO `monitor_agent` VALUES ('3', 'mc2', '192.168.1.222', '1112', 'Y', 'B/SAccess', 'i', null, '', '');

-- ----------------------------
-- Table structure for `parent_exchange_platform`
-- ----------------------------
DROP TABLE IF EXISTS `parent_exchange_platform`;
CREATE TABLE `parent_exchange_platform` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(255) DEFAULT NULL,
  `platform_ip` varchar(255) DEFAULT NULL,
  `platform_port` int(11) DEFAULT NULL,
  `is_encrypted` varchar(1) DEFAULT NULL,
  `link_name` varchar(255) DEFAULT NULL,
  `certificate_path` varchar(255) DEFAULT NULL,
  `certificate_pwd` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `enablereport` char(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  `minute` varchar(255) DEFAULT NULL,
  `second` varchar(255) DEFAULT NULL,
  `platform_mac` varchar(255) DEFAULT NULL,
  `timeType` varchar(2) DEFAULT '24',
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parent_exchange_platform
-- ----------------------------
INSERT INTO `parent_exchange_platform` VALUES ('2', 'YDJW', '192.168.2.103', '8080', 'Y', 'B/SAccess', '/usr/app/cms/uploadclientStore.jks', '1qaz@wsxstorepass', '', '0', '/Webservice/services/inceptData', '03', '0', '0', '00:0c:29:65:b7:2d', '24', 'Webservice', null, null);
INSERT INTO `parent_exchange_platform` VALUES ('4', 'cms', '192.168.1.123', '23', 'Y', 'B/SAccess', null, '', '', '0', 'qq', '', '', '', '00:0c:29:65:b7:2d', '2', 'Ftp', 'a', 'a');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('100', 'TOP_YXJK', '运行监管', null, '0', '0');
INSERT INTO `permission` VALUES ('101', 'SECOND_YWYXJK', '业务运行监控', null, '100', '1');
INSERT INTO `permission` VALUES ('102', 'SECOND_SBYXJK', '设备运行监控', null, '100', '2');
INSERT INTO `permission` VALUES ('103', 'SECOND_ZDYHYXJK', '终端用户运行监控', null, '100', '3');
INSERT INTO `permission` VALUES ('110', 'TOP_SJGL', '审计管理', null, '0', '0');
INSERT INTO `permission` VALUES ('111', 'SECOND_XTRZSJ', '系统日志审计', null, '110', '1');
INSERT INTO `permission` VALUES ('112', 'SECOND_YHRZSJ', '用户日志审计', null, '110', '2');
INSERT INTO `permission` VALUES ('113', 'SECOND_YWRZSJ', '业务日志审计', null, '110', '3');
INSERT INTO `permission` VALUES ('114', 'SECOND_SBRZSJ', '设备日志审计', null, '110', '4');
INSERT INTO `permission` VALUES ('120', 'TOP_BJGL', '报警管理', null, '0', '0');
INSERT INTO `permission` VALUES ('121', 'SECOND_BJSZ', '报警配置', null, '120', '1');
INSERT INTO `permission` VALUES ('122', 'SECOND_YWYCBJ', '业务异常报警', null, '120', '2');
INSERT INTO `permission` VALUES ('123', 'SECOND_AQSJBJ', '安全事件报警', null, '120', '3');
INSERT INTO `permission` VALUES ('124', 'SECOND_SBGZBJ', '设备故障报警', null, '120', '4');
INSERT INTO `permission` VALUES ('130', 'TOP_TJBB', '统计报表', null, '0', '0');
INSERT INTO `permission` VALUES ('131', 'SECOND_YWRTJB', '业务日统计表', null, '130', '1');
INSERT INTO `permission` VALUES ('132', 'SECOND_YWYTJB', '业务月统计表', null, '130', '2');
INSERT INTO `permission` VALUES ('133', 'SECOND_YWNTJB', '业务年统计表', null, '130', '3');
INSERT INTO `permission` VALUES ('134', 'SECOND_SBRTJB', '设备日统计表', null, '130', '4');
INSERT INTO `permission` VALUES ('135', 'SECOND_SBYTJB', '设备月统计表', null, '130', '5');
INSERT INTO `permission` VALUES ('136', 'SECOND_SBNTJB', '设备年统计表', null, '130', '6');
INSERT INTO `permission` VALUES ('137', 'SECOND_ZDYHFWRTJB', '终端用户访问日统计表', null, '130', '7');
INSERT INTO `permission` VALUES ('138', 'SECOND_ZDYHYXRTJB', '终端用户运行日统计表', null, '130', '8');
INSERT INTO `permission` VALUES ('140', 'TOP_JLSB', '级联上报', null, '0', '0');
INSERT INTO `permission` VALUES ('150', 'TOP_QXGL', '权限管理', null, '0', '0');
INSERT INTO `permission` VALUES ('151', 'SECOND_YHGL', '用户管理', null, '150', '1');
INSERT INTO `permission` VALUES ('152', 'SECOND_JSGL', '角色管理', null, '150', '2');
INSERT INTO `permission` VALUES ('160', 'TOP_PZGL', '配置管理', null, '0', '0');
INSERT INTO `permission` VALUES ('161', 'SECOND_SJKGL', '审计库管理', null, '160', '1');
INSERT INTO `permission` VALUES ('162', 'SECOND_JBPZ', '基本配置', null, '160', '2');
INSERT INTO `permission` VALUES ('163', 'SECOND_LLPZ', '链路配置', null, '160', '3');
INSERT INTO `permission` VALUES ('164', 'SECOND_JHPTPZ', '交换平台配置', null, '160', '4');
INSERT INTO `permission` VALUES ('165', 'SECOND_TZTD', '探针通道', null, '160', '5');
INSERT INTO `permission` VALUES ('166', 'SECOND_SJBFCL', '审计备份策略', null, '160', '6');
INSERT INTO `permission` VALUES ('167', 'SECOND_SBGLPZ', '设备管理配置', null, '160', '7');
INSERT INTO `permission` VALUES ('168', 'SECOND_YWZCGL', '业务注册管理', null, '160', '8');
INSERT INTO `permission` VALUES ('169', 'SECOND_KZCEZCGL', '控制策略注册管理', null, '160', '9');
INSERT INTO `permission` VALUES ('170', 'SECOND_AQCLZCGL', '安全策略注册管理', null, '160', '10');
INSERT INTO `permission` VALUES ('171', 'SECOND_WGCLCLZC', '违规处理策略注册 ', null, '160', '11');
INSERT INTO `permission` VALUES ('200', 'TOP_ZDGL', '终端管理', null, '0', '0');
INSERT INTO `permission` VALUES ('201', 'SECOND_ZXYHGL', '在线用户管理', null, '180', '1');
INSERT INTO `permission` VALUES ('202', 'SECOND_QBYHLB', '全部用户列表', null, '180', '2');
INSERT INTO `permission` VALUES ('203', 'SECOND_YHGLSJ', '用户管理审计', null, '180', '3');
INSERT INTO `permission` VALUES ('204', 'SECOND_YHFWSJ', '用户访问审计', null, '180', '4');
INSERT INTO `permission` VALUES ('205', 'SECOND_ZDSBGL', '终端设备管理', null, '180', '5');
INSERT INTO `permission` VALUES ('206', 'SECOND_ZDYHFWURL', '终端用户访问URL', null, '180', '6');
INSERT INTO `permission` VALUES ('210', 'TOP_WLGL', '网络管理', null, '0', '0');
INSERT INTO `permission` VALUES ('211', 'SECOND_JKGL', '接口管理', null, '190', '1');
INSERT INTO `permission` VALUES ('212', 'SECOND_LTCS', '连通测试', null, '190', '2');
INSERT INTO `permission` VALUES ('213', 'SECOND_DKCS', '端口测试', null, '190', '3');
INSERT INTO `permission` VALUES ('214', 'SECOND_LYGL', '路由管理', null, '190', '4');
INSERT INTO `permission` VALUES ('220', 'TOP_XTGL', '系统管理', null, '0', '0');
INSERT INTO `permission` VALUES ('221', 'SECOND_PTGL', '平台管理', null, '200', '1');
INSERT INTO `permission` VALUES ('222', 'SECOND_PZGL', '安全配置', null, '200', '2');
INSERT INTO `permission` VALUES ('223', 'SECOND_BBSJ', '版本升级', null, '200', '3');
INSERT INTO `permission` VALUES ('224', 'SECOND_RZXZ', '日志下载', null, '200', '4');

-- ----------------------------
-- Table structure for `platform_function_module`
-- ----------------------------
DROP TABLE IF EXISTS `platform_function_module`;
CREATE TABLE `platform_function_module` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) NOT NULL DEFAULT '' COMMENT '模块编号',
  `code_desc` varchar(40) NOT NULL DEFAULT '' COMMENT '模块名',
  PRIMARY KEY (`Id`),
  KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='功能模块表';

-- ----------------------------
-- Records of platform_function_module
-- ----------------------------
INSERT INTO `platform_function_module` VALUES ('1', '0100', '');
INSERT INTO `platform_function_module` VALUES ('2', '0101', '');
INSERT INTO `platform_function_module` VALUES ('3', '0102', '');
INSERT INTO `platform_function_module` VALUES ('4', '0200', '');
INSERT INTO `platform_function_module` VALUES ('5', '0201', '');
INSERT INTO `platform_function_module` VALUES ('6', '0202', '');
INSERT INTO `platform_function_module` VALUES ('7', '0203', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('8', '0204', '');
INSERT INTO `platform_function_module` VALUES ('9', '0300', '');
INSERT INTO `platform_function_module` VALUES ('10', '0301', '');
INSERT INTO `platform_function_module` VALUES ('11', '0302', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('12', '0303', '');
INSERT INTO `platform_function_module` VALUES ('13', '0304', '');
INSERT INTO `platform_function_module` VALUES ('14', '0400', 'ͳ');
INSERT INTO `platform_function_module` VALUES ('15', '0410', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('16', '0411', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('17', '0412', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('18', '0413', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('19', '0420', '');
INSERT INTO `platform_function_module` VALUES ('20', '0421', '');
INSERT INTO `platform_function_module` VALUES ('21', '0422', '');
INSERT INTO `platform_function_module` VALUES ('22', '0423', '');
INSERT INTO `platform_function_module` VALUES ('23', '0500', '');
INSERT INTO `platform_function_module` VALUES ('24', '0600', 'Ȩ');
INSERT INTO `platform_function_module` VALUES ('25', '0601', '');
INSERT INTO `platform_function_module` VALUES ('26', '0602', '');
INSERT INTO `platform_function_module` VALUES ('27', '0603', '');
INSERT INTO `platform_function_module` VALUES ('28', '0700', '');
INSERT INTO `platform_function_module` VALUES ('29', '0701', '');
INSERT INTO `platform_function_module` VALUES ('30', '0702', '');
INSERT INTO `platform_function_module` VALUES ('31', '0703', '');
INSERT INTO `platform_function_module` VALUES ('32', '0704', '');
INSERT INTO `platform_function_module` VALUES ('33', '0705', '̽');
INSERT INTO `platform_function_module` VALUES ('34', '0706', '');
INSERT INTO `platform_function_module` VALUES ('35', '0707', '');
INSERT INTO `platform_function_module` VALUES ('36', '0708', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('37', '0709', 'ҵ');
INSERT INTO `platform_function_module` VALUES ('38', '0800', '');

-- ----------------------------
-- Table structure for `platform_running_code`
-- ----------------------------
DROP TABLE IF EXISTS `platform_running_code`;
CREATE TABLE `platform_running_code` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `code_desc` varchar(20) DEFAULT NULL COMMENT '平台运行状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='平台运行状态代码表';

-- ----------------------------
-- Records of platform_running_code
-- ----------------------------
INSERT INTO `platform_running_code` VALUES ('1', ' 0001', '');
INSERT INTO `platform_running_code` VALUES ('2', ' 0002', '');
INSERT INTO `platform_running_code` VALUES ('3', ' 0003', '');
INSERT INTO `platform_running_code` VALUES ('4', ' 0004', '');
INSERT INTO `platform_running_code` VALUES ('5', ' 0005', '');
INSERT INTO `platform_running_code` VALUES ('6', '', '');

-- ----------------------------
-- Table structure for `protocol_type`
-- ----------------------------
DROP TABLE IF EXISTS `protocol_type`;
CREATE TABLE `protocol_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `protocol_code` varchar(40) NOT NULL DEFAULT '' COMMENT '协议编号',
  `protocol_name` varchar(255) DEFAULT NULL COMMENT '协议名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='协议代码表';

-- ----------------------------
-- Records of protocol_type
-- ----------------------------
INSERT INTO `protocol_type` VALUES ('1', '0001', 'TCP/IP');
INSERT INTO `protocol_type` VALUES ('2', '0002', 'SSL');
INSERT INTO `protocol_type` VALUES ('3', '0003', 'IPSEC');
INSERT INTO `protocol_type` VALUES ('4', '0004', 'FTP');
INSERT INTO `protocol_type` VALUES ('5', '0005', 'H323');
INSERT INTO `protocol_type` VALUES ('6', '0006', 'H320');
INSERT INTO `protocol_type` VALUES ('7', '0007', 'SNMP');
INSERT INTO `protocol_type` VALUES ('8', '0008', 'UDP');
INSERT INTO `protocol_type` VALUES ('9', '0100', '');
INSERT INTO `protocol_type` VALUES ('10', '0101', 'SIP');
INSERT INTO `protocol_type` VALUES ('11', '0102', 'SOCKET');

-- ----------------------------
-- Table structure for `province_exchange_platform`
-- ----------------------------
DROP TABLE IF EXISTS `province_exchange_platform`;
CREATE TABLE `province_exchange_platform` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(60) NOT NULL DEFAULT '' COMMENT '交换平台名称',
  `platform_ip` varchar(20) DEFAULT NULL COMMENT '平台IP',
  `platform_port` int(11) NOT NULL DEFAULT '8090' COMMENT '平台监控端口',
  `is_encrypted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否加密  说明：N为未加密，Y加密',
  `link_name` varchar(60) DEFAULT NULL COMMENT '链路名',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '证书路径',
  `certificate_pwd` varchar(255) DEFAULT NULL COMMENT '证书密码',
  `memo` varchar(255) DEFAULT NULL COMMENT '描述',
  `enablereport` char(1) DEFAULT '0',
  `address` varchar(255) DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  `minute` varchar(255) DEFAULT NULL,
  `second` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='交换平台配置';

-- ----------------------------
-- Records of province_exchange_platform
-- ----------------------------
INSERT INTO `province_exchange_platform` VALUES ('1', '下级', '192.168.1.121', '1122', 'Y', '1', 'D:\\\\fartec\\\\ichange\\\\monitorservice\\\\src\\\\resources\\\\clientStore.jks', '1213', '', '1', null, null, null, null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '超级管理员', '2010-07-04 15:07:08', '2012-07-01 10:33:06');
INSERT INTO `role` VALUES ('2', '授权管理员', '授权管理员', '2012-03-14 12:33:05', '2012-07-01 10:25:49');
INSERT INTO `role` VALUES ('3', '审计管理员', '审计管理员', '2012-06-29 16:28:45', '2012-07-01 10:32:39');
INSERT INTO `role` VALUES ('4', '配置管理员', '配置管理员', '2012-07-01 10:34:44', '2012-07-01 10:34:44');
INSERT INTO `role` VALUES ('5', '测试管理员', '测试管理', '2012-11-22 13:12:46', '2012-11-22 13:12:46');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKBD40D53851BABF58` (`role_id`),
  KEY `FKBD40D53852A81638` (`permission_id`),
  KEY `FK9C6EC93851BABF58` (`role_id`),
  KEY `FK9C6EC93852A81638` (`permission_id`),
  CONSTRAINT `FK9C6EC93851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK9C6EC93852A81638` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D53851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D53852A81638` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('150', '1');
INSERT INTO `role_permission` VALUES ('152', '1');
INSERT INTO `role_permission` VALUES ('160', '1');
INSERT INTO `role_permission` VALUES ('161', '1');
INSERT INTO `role_permission` VALUES ('166', '1');
INSERT INTO `role_permission` VALUES ('210', '1');
INSERT INTO `role_permission` VALUES ('211', '1');
INSERT INTO `role_permission` VALUES ('212', '1');
INSERT INTO `role_permission` VALUES ('213', '1');
INSERT INTO `role_permission` VALUES ('214', '1');
INSERT INTO `role_permission` VALUES ('220', '1');
INSERT INTO `role_permission` VALUES ('221', '1');
INSERT INTO `role_permission` VALUES ('222', '1');
INSERT INTO `role_permission` VALUES ('223', '1');
INSERT INTO `role_permission` VALUES ('224', '1');
INSERT INTO `role_permission` VALUES ('150', '2');
INSERT INTO `role_permission` VALUES ('151', '2');
INSERT INTO `role_permission` VALUES ('100', '3');
INSERT INTO `role_permission` VALUES ('101', '3');
INSERT INTO `role_permission` VALUES ('102', '3');
INSERT INTO `role_permission` VALUES ('103', '3');
INSERT INTO `role_permission` VALUES ('110', '3');
INSERT INTO `role_permission` VALUES ('111', '3');
INSERT INTO `role_permission` VALUES ('112', '3');
INSERT INTO `role_permission` VALUES ('113', '3');
INSERT INTO `role_permission` VALUES ('114', '3');
INSERT INTO `role_permission` VALUES ('120', '3');
INSERT INTO `role_permission` VALUES ('121', '3');
INSERT INTO `role_permission` VALUES ('122', '3');
INSERT INTO `role_permission` VALUES ('123', '3');
INSERT INTO `role_permission` VALUES ('124', '3');
INSERT INTO `role_permission` VALUES ('130', '3');
INSERT INTO `role_permission` VALUES ('131', '3');
INSERT INTO `role_permission` VALUES ('132', '3');
INSERT INTO `role_permission` VALUES ('133', '3');
INSERT INTO `role_permission` VALUES ('134', '3');
INSERT INTO `role_permission` VALUES ('135', '3');
INSERT INTO `role_permission` VALUES ('136', '3');
INSERT INTO `role_permission` VALUES ('137', '3');
INSERT INTO `role_permission` VALUES ('138', '3');
INSERT INTO `role_permission` VALUES ('120', '4');
INSERT INTO `role_permission` VALUES ('121', '4');
INSERT INTO `role_permission` VALUES ('140', '4');
INSERT INTO `role_permission` VALUES ('160', '4');
INSERT INTO `role_permission` VALUES ('161', '4');
INSERT INTO `role_permission` VALUES ('162', '4');
INSERT INTO `role_permission` VALUES ('163', '4');
INSERT INTO `role_permission` VALUES ('164', '4');
INSERT INTO `role_permission` VALUES ('165', '4');
INSERT INTO `role_permission` VALUES ('166', '4');
INSERT INTO `role_permission` VALUES ('167', '4');
INSERT INTO `role_permission` VALUES ('168', '4');
INSERT INTO `role_permission` VALUES ('169', '4');
INSERT INTO `role_permission` VALUES ('170', '4');
INSERT INTO `role_permission` VALUES ('171', '4');
INSERT INTO `role_permission` VALUES ('200', '4');
INSERT INTO `role_permission` VALUES ('205', '4');
INSERT INTO `role_permission` VALUES ('210', '4');
INSERT INTO `role_permission` VALUES ('211', '4');
INSERT INTO `role_permission` VALUES ('212', '4');
INSERT INTO `role_permission` VALUES ('213', '4');
INSERT INTO `role_permission` VALUES ('214', '4');
INSERT INTO `role_permission` VALUES ('220', '4');
INSERT INTO `role_permission` VALUES ('221', '4');
INSERT INTO `role_permission` VALUES ('222', '4');
INSERT INTO `role_permission` VALUES ('224', '4');
INSERT INTO `role_permission` VALUES ('100', '5');
INSERT INTO `role_permission` VALUES ('101', '5');
INSERT INTO `role_permission` VALUES ('102', '5');
INSERT INTO `role_permission` VALUES ('103', '5');
INSERT INTO `role_permission` VALUES ('110', '5');
INSERT INTO `role_permission` VALUES ('111', '5');
INSERT INTO `role_permission` VALUES ('112', '5');
INSERT INTO `role_permission` VALUES ('113', '5');
INSERT INTO `role_permission` VALUES ('114', '5');
INSERT INTO `role_permission` VALUES ('120', '5');
INSERT INTO `role_permission` VALUES ('121', '5');
INSERT INTO `role_permission` VALUES ('122', '5');
INSERT INTO `role_permission` VALUES ('123', '5');
INSERT INTO `role_permission` VALUES ('124', '5');
INSERT INTO `role_permission` VALUES ('130', '5');
INSERT INTO `role_permission` VALUES ('131', '5');
INSERT INTO `role_permission` VALUES ('132', '5');
INSERT INTO `role_permission` VALUES ('133', '5');
INSERT INTO `role_permission` VALUES ('134', '5');
INSERT INTO `role_permission` VALUES ('135', '5');
INSERT INTO `role_permission` VALUES ('136', '5');
INSERT INTO `role_permission` VALUES ('137', '5');
INSERT INTO `role_permission` VALUES ('138', '5');
INSERT INTO `role_permission` VALUES ('140', '5');
INSERT INTO `role_permission` VALUES ('150', '5');
INSERT INTO `role_permission` VALUES ('151', '5');
INSERT INTO `role_permission` VALUES ('152', '5');
INSERT INTO `role_permission` VALUES ('160', '5');
INSERT INTO `role_permission` VALUES ('161', '5');
INSERT INTO `role_permission` VALUES ('162', '5');
INSERT INTO `role_permission` VALUES ('163', '5');
INSERT INTO `role_permission` VALUES ('164', '5');
INSERT INTO `role_permission` VALUES ('165', '5');
INSERT INTO `role_permission` VALUES ('166', '5');
INSERT INTO `role_permission` VALUES ('167', '5');
INSERT INTO `role_permission` VALUES ('168', '5');
INSERT INTO `role_permission` VALUES ('169', '5');
INSERT INTO `role_permission` VALUES ('170', '5');
INSERT INTO `role_permission` VALUES ('171', '5');
INSERT INTO `role_permission` VALUES ('200', '5');
INSERT INTO `role_permission` VALUES ('201', '5');
INSERT INTO `role_permission` VALUES ('202', '5');
INSERT INTO `role_permission` VALUES ('203', '5');
INSERT INTO `role_permission` VALUES ('204', '5');
INSERT INTO `role_permission` VALUES ('205', '5');
INSERT INTO `role_permission` VALUES ('206', '5');
INSERT INTO `role_permission` VALUES ('210', '5');
INSERT INTO `role_permission` VALUES ('211', '5');
INSERT INTO `role_permission` VALUES ('212', '5');
INSERT INTO `role_permission` VALUES ('213', '5');
INSERT INTO `role_permission` VALUES ('214', '5');
INSERT INTO `role_permission` VALUES ('220', '5');
INSERT INTO `role_permission` VALUES ('221', '5');
INSERT INTO `role_permission` VALUES ('222', '5');
INSERT INTO `role_permission` VALUES ('223', '5');
INSERT INTO `role_permission` VALUES ('224', '5');

-- ----------------------------
-- Table structure for `sbpz`
-- ----------------------------
DROP TABLE IF EXISTS `sbpz`;
CREATE TABLE `sbpz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `hour` int(11) DEFAULT NULL,
  `minute` int(11) DEFAULT NULL,
  `second` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sbpz
-- ----------------------------
INSERT INTO `sbpz` VALUES ('1', '省级', '192.168.1.12', '2121', '1212', '12121', '2', '22', '22', '2012-04-05 12:29:44', '2012-04-05 12:29:44');

-- ----------------------------
-- Table structure for `security_event_alert`
-- ----------------------------
DROP TABLE IF EXISTS `security_event_alert`;
CREATE TABLE `security_event_alert` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equ_name` varchar(255) DEFAULT NULL,
  `alert_time` datetime DEFAULT NULL,
  `event_code` varchar(255) DEFAULT NULL,
  `alert_info` varchar(255) DEFAULT NULL,
  `isread` char(1) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK6B1478F882850C1B` (`event_code`),
  CONSTRAINT `FK6B1478F882850C1B` FOREIGN KEY (`event_code`) REFERENCES `security_event_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_event_alert
-- ----------------------------

-- ----------------------------
-- Table structure for `security_event_code`
-- ----------------------------
DROP TABLE IF EXISTS `security_event_code`;
CREATE TABLE `security_event_code` (
  `code` varchar(10) NOT NULL DEFAULT '代码',
  `code_desc` varchar(80) DEFAULT NULL COMMENT '安全事件类型',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全事件代码表';

-- ----------------------------
-- Records of security_event_code
-- ----------------------------
INSERT INTO `security_event_code` VALUES ('0001', ' 自然灾害类');
INSERT INTO `security_event_code` VALUES ('0002', ' 人为破坏类');
INSERT INTO `security_event_code` VALUES ('0003', ' 误操作类');
INSERT INTO `security_event_code` VALUES ('0004', ' 网络攻击类');
INSERT INTO `security_event_code` VALUES ('0005', ' 操作系统攻击类');
INSERT INTO `security_event_code` VALUES ('0006', ' 数据库攻击类');
INSERT INTO `security_event_code` VALUES ('0007', ' 病毒类');
INSERT INTO `security_event_code` VALUES ('0008', ' 应用系统（软件）程序故障类');
INSERT INTO `security_event_code` VALUES ('0009', ' 设备硬件故障类');
INSERT INTO `security_event_code` VALUES ('0010', ' 黑客入侵类');
INSERT INTO `security_event_code` VALUES ('0011', ' 越权访问类');
INSERT INTO `security_event_code` VALUES ('0012', ' 应用系统攻击类');
INSERT INTO `security_event_code` VALUES ('保留', ' 其它');

-- ----------------------------
-- Table structure for `snmpoid`
-- ----------------------------
DROP TABLE IF EXISTS `snmpoid`;
CREATE TABLE `snmpoid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `snmpver` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `cpuuse` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `disktotal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `diskuse` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `memtotal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `memuse` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `curconn` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of snmpoid
-- ----------------------------
INSERT INTO `snmpoid` VALUES ('3', '1', '1', 'huawei', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `snmpoid` VALUES ('4', '2', '2', '2', 'v2', '2', '2', '2', '2', '2', '2');
INSERT INTO `snmpoid` VALUES ('5', 'windowsos', 'pcserver', 'pcserver', 'v2', '.1.3.6.1.2.1.25.3.3.1.2', '.1.3.6.1.2.1.25.2.3.1.5', '.1.3.6.1.2.1.25.2.3.1.6', '.1.3.6.1.2.1.25.2.2', '.1.3.6.1.2.1.25.2.3.1.6.6', '.1.3.6.2.1.6.4');

-- ----------------------------
-- Table structure for `strategyinf`
-- ----------------------------
DROP TABLE IF EXISTS `strategyinf`;
CREATE TABLE `strategyinf` (
  `code` varchar(4) NOT NULL DEFAULT '',
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of strategyinf
-- ----------------------------
INSERT INTO `strategyinf` VALUES ('0000', '其它');
INSERT INTO `strategyinf` VALUES ('1001', 'tcp');
INSERT INTO `strategyinf` VALUES ('1002', 'ftp');
INSERT INTO `strategyinf` VALUES ('1003', 'http');
INSERT INTO `strategyinf` VALUES ('1004', 'https');
INSERT INTO `strategyinf` VALUES ('2001', 'udp');

-- ----------------------------
-- Table structure for `sys_class`
-- ----------------------------
DROP TABLE IF EXISTS `sys_class`;
CREATE TABLE `sys_class` (
  `system_class_code` varchar(5) NOT NULL,
  `system_class_name` varchar(60) NOT NULL,
  PRIMARY KEY (`system_class_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_class
-- ----------------------------
INSERT INTO `sys_class` VALUES ('00000', '信息通信部门');
INSERT INTO `sys_class` VALUES ('01000', '国内安全保卫管理部门');
INSERT INTO `sys_class` VALUES ('02000', '经济犯罪侦查管理部门');
INSERT INTO `sys_class` VALUES ('03000', '治安管理部门');
INSERT INTO `sys_class` VALUES ('04000', '边防管理部门');
INSERT INTO `sys_class` VALUES ('05000', '刑事侦查管理部门');
INSERT INTO `sys_class` VALUES ('06000', '出入境管理部门');
INSERT INTO `sys_class` VALUES ('07000', '消防管理部门');
INSERT INTO `sys_class` VALUES ('08000', '警卫管理部门');
INSERT INTO `sys_class` VALUES ('10000', '铁道公安管理部门');
INSERT INTO `sys_class` VALUES ('11000', '网络安全监察管理部门');
INSERT INTO `sys_class` VALUES ('12000', '行动技术管理部门 ');
INSERT INTO `sys_class` VALUES ('13000', '监所管理部门');
INSERT INTO `sys_class` VALUES ('14000', '交通公安管理部门');
INSERT INTO `sys_class` VALUES ('15000', '民航公安管理部门');
INSERT INTO `sys_class` VALUES ('16000', '林业公安管理部门');
INSERT INTO `sys_class` VALUES ('17000', '交通管理部门');
INSERT INTO `sys_class` VALUES ('18000', '法制管理部门');
INSERT INTO `sys_class` VALUES ('19000', '外事管理部门');
INSERT INTO `sys_class` VALUES ('20000', '装备财务管理部门');
INSERT INTO `sys_class` VALUES ('21000', '禁毒管理部门');
INSERT INTO `sys_class` VALUES ('22000', '科技管理部门');
INSERT INTO `sys_class` VALUES ('23000', '基础和综合管理部门');
INSERT INTO `sys_class` VALUES ('24000', '海关公安管理部门');
INSERT INTO `sys_class` VALUES ('26000', '反邪教管理部门');
INSERT INTO `sys_class` VALUES ('27000', '反恐怖管理部门');
INSERT INTO `sys_class` VALUES ('31000', '办公管理部门(指挥中心管理部门)');
INSERT INTO `sys_class` VALUES ('32000', '纪委监察管理部门');
INSERT INTO `sys_class` VALUES ('34000', '督察管理部门');
INSERT INTO `sys_class` VALUES ('36000', '人事管理部门');
INSERT INTO `sys_class` VALUES ('39000', '离退休干部管理部门');
INSERT INTO `sys_class` VALUES ('92000', '其他');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '产生时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志等级',
  `audit_module` varchar(40) DEFAULT NULL COMMENT '审计模块',
  `audit_action` varchar(40) DEFAULT NULL COMMENT '审计行为',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`audit_module`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志审计表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sysabnormal`
-- ----------------------------
DROP TABLE IF EXISTS `sysabnormal`;
CREATE TABLE `sysabnormal` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idalertmatter` int(11) DEFAULT NULL,
  `Abnormaltypecode` varchar(4) DEFAULT NULL,
  `Connectobjectcode` varchar(3) DEFAULT NULL,
  `Exceptiondesc` varchar(255) DEFAULT NULL,
  `Happentime` datetime DEFAULT NULL,
  `Treattime` datetime DEFAULT NULL,
  `Treadresult` varchar(3) DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  `Idsystem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Treadresult` (`Treadresult`),
  KEY `Abnormaltypecode` (`Abnormaltypecode`),
  KEY `Connectobjectcode` (`Connectobjectcode`),
  KEY `FK52AC0E15FC5A369B` (`Treadresult`),
  KEY `FK52AC0E15EFAB2DF` (`Abnormaltypecode`),
  KEY `FK52AC0E15BF89F786` (`Connectobjectcode`),
  CONSTRAINT `FK52AC0E15BF89F786` FOREIGN KEY (`Connectobjectcode`) REFERENCES `sysabnormal_object_type` (`code`),
  CONSTRAINT `FK52AC0E15EFAB2DF` FOREIGN KEY (`Abnormaltypecode`) REFERENCES `sysabnormal_event_code` (`code`),
  CONSTRAINT `FK52AC0E15FC5A369B` FOREIGN KEY (`Treadresult`) REFERENCES `sysabnormal_result` (`code`),
  CONSTRAINT `FK_sysabnormal_sysabnormal_event_code` FOREIGN KEY (`Abnormaltypecode`) REFERENCES `sysabnormal_event_code` (`code`),
  CONSTRAINT `FK_sysabnormal_sysabnormal_object_type` FOREIGN KEY (`Connectobjectcode`) REFERENCES `sysabnormal_object_type` (`code`),
  CONSTRAINT `FK_sysabnormal_sysabnormal_result` FOREIGN KEY (`Treadresult`) REFERENCES `sysabnormal_result` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysabnormal
-- ----------------------------
INSERT INTO `sysabnormal` VALUES ('1', '11111', '0004', '002', '121212', '2012-04-01 12:00:06', '2012-04-01 12:00:07', '004', '2012-04-01 12:00:15', null);

-- ----------------------------
-- Table structure for `sysabnormal_event_code`
-- ----------------------------
DROP TABLE IF EXISTS `sysabnormal_event_code`;
CREATE TABLE `sysabnormal_event_code` (
  `code` varchar(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysabnormal_event_code
-- ----------------------------
INSERT INTO `sysabnormal_event_code` VALUES ('0000', '其它违规');
INSERT INTO `sysabnormal_event_code` VALUES ('0001', '网络违规');
INSERT INTO `sysabnormal_event_code` VALUES ('0002', '进程违规');
INSERT INTO `sysabnormal_event_code` VALUES ('0003', '外设违规');
INSERT INTO `sysabnormal_event_code` VALUES ('0004', 'URL违规');
INSERT INTO `sysabnormal_event_code` VALUES ('0005', '流量违规');

-- ----------------------------
-- Table structure for `sysabnormal_object_type`
-- ----------------------------
DROP TABLE IF EXISTS `sysabnormal_object_type`;
CREATE TABLE `sysabnormal_object_type` (
  `code` varchar(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysabnormal_object_type
-- ----------------------------
INSERT INTO `sysabnormal_object_type` VALUES ('001', '终端');
INSERT INTO `sysabnormal_object_type` VALUES ('002', '用户');
INSERT INTO `sysabnormal_object_type` VALUES ('003', '设备');
INSERT INTO `sysabnormal_object_type` VALUES ('004', '策略');
INSERT INTO `sysabnormal_object_type` VALUES ('005', '其它对象');

-- ----------------------------
-- Table structure for `sysabnormal_result`
-- ----------------------------
DROP TABLE IF EXISTS `sysabnormal_result`;
CREATE TABLE `sysabnormal_result` (
  `code` varchar(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysabnormal_result
-- ----------------------------
INSERT INTO `sysabnormal_result` VALUES ('001', '已处理');
INSERT INTO `sysabnormal_result` VALUES ('002', '待处理');
INSERT INTO `sysabnormal_result` VALUES ('003', '未处理');
INSERT INTO `sysabnormal_result` VALUES ('004', '处理中');
INSERT INTO `sysabnormal_result` VALUES ('005', '其它结果');

-- ----------------------------
-- Table structure for `sysabnormalinf`
-- ----------------------------
DROP TABLE IF EXISTS `sysabnormalinf`;
CREATE TABLE `sysabnormalinf` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abnormalTypeCode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `connectObjectCode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `exceptionDesc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `exceptionIf` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sysabnormalinf
-- ----------------------------

-- ----------------------------
-- Table structure for `sysbizstatus`
-- ----------------------------
DROP TABLE IF EXISTS `sysbizstatus`;
CREATE TABLE `sysbizstatus` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Idbiz` int(10) DEFAULT NULL,
  `Bizname` varchar(50) DEFAULT NULL,
  `Access` int(11) DEFAULT NULL,
  `Terminalnum` int(11) DEFAULT NULL,
  `Influx` int(11) DEFAULT NULL,
  `Outflux` int(11) DEFAULT NULL,
  `Accesssum` int(11) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysbizstatus
-- ----------------------------
INSERT INTO `sysbizstatus` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '2012-11-04 15:33:19', '2012-12-31 15:33:22', '2012-12-04 15:33:31');

-- ----------------------------
-- Table structure for `sysclientservice`
-- ----------------------------
DROP TABLE IF EXISTS `sysclientservice`;
CREATE TABLE `sysclientservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idsystem` varchar(10) NOT NULL,
  `idterminal` int(10) NOT NULL,
  `taction` char(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idterminal` (`idterminal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysclientservice
-- ----------------------------

-- ----------------------------
-- Table structure for `syscontrolrulesinf`
-- ----------------------------
DROP TABLE IF EXISTS `syscontrolrulesinf`;
CREATE TABLE `syscontrolrulesinf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(128) NOT NULL,
  `controldesc` varchar(256) DEFAULT NULL,
  `collecttime` datetime NOT NULL,
  `idsystem` varchar(10) DEFAULT NULL,
  `idcontrolrules` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syscontrolrulesinf
-- ----------------------------
INSERT INTO `syscontrolrulesinf` VALUES ('1', 'usg-fw.zip', '防火墙配置策略', '2012-04-10 16:10:28', null, null);
INSERT INTO `syscontrolrulesinf` VALUES ('2', 'wangza-nei.zip', '安全隔离设备内主机配置', '2012-04-10 16:11:15', null, null);
INSERT INTO `syscontrolrulesinf` VALUES ('3', 'wangza-wai.zip', '安全隔离设备外主机配置', '2012-04-10 16:12:01', null, null);
INSERT INTO `syscontrolrulesinf` VALUES ('4', 'bsmams.zip', 'B/S应用管理服务器配置', '2012-04-10 16:13:58', null, null);
INSERT INTO `syscontrolrulesinf` VALUES ('5', 'bsmaps.zip', 'B/S应用代理配置', '2012-04-10 16:14:42', null, null);

-- ----------------------------
-- Table structure for `sysdelservice`
-- ----------------------------
DROP TABLE IF EXISTS `sysdelservice`;
CREATE TABLE `sysdelservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idsystem` varchar(10) COLLATE utf8_bin NOT NULL,
  `objectname` varchar(20) COLLATE utf8_bin NOT NULL,
  `ids` varchar(500) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sysdelservice
-- ----------------------------

-- ----------------------------
-- Table structure for `sysqueryservice`
-- ----------------------------
DROP TABLE IF EXISTS `sysqueryservice`;
CREATE TABLE `sysqueryservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idsystem` varchar(10) NOT NULL,
  `objectname` varchar(20) NOT NULL,
  `ids` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysqueryservice
-- ----------------------------

-- ----------------------------
-- Table structure for `sysruntime`
-- ----------------------------
DROP TABLE IF EXISTS `sysruntime`;
CREATE TABLE `sysruntime` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Runstatecode` varchar(4) DEFAULT NULL,
  `Desc` varchar(254) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysruntime
-- ----------------------------
INSERT INTO `sysruntime` VALUES ('1', '1', '1', '1', '2012-12-02 15:33:47', '2012-12-31 15:33:51', '2012-12-04 15:33:55');

-- ----------------------------
-- Table structure for `sysstatus`
-- ----------------------------
DROP TABLE IF EXISTS `sysstatus`;
CREATE TABLE `sysstatus` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Idsystem` varchar(10) DEFAULT NULL,
  `Access` int(8) DEFAULT NULL,
  `Terminalnum` int(8) DEFAULT NULL,
  `Influx` bigint(20) DEFAULT NULL,
  `Outflux` bigint(20) DEFAULT NULL,
  `Accesssum` int(11) DEFAULT NULL,
  `Bizsum` int(11) DEFAULT NULL,
  `Starttime` datetime DEFAULT NULL,
  `Endtime` datetime DEFAULT NULL,
  `writetime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysstatus
-- ----------------------------
INSERT INTO `sysstatus` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '2012-12-03 15:34:07', '2012-12-31 15:34:10', '2012-12-04 15:34:19');

-- ----------------------------
-- Table structure for `sysstrategyinf`
-- ----------------------------
DROP TABLE IF EXISTS `sysstrategyinf`;
CREATE TABLE `sysstrategyinf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `strategy_desc` varchar(60) COLLATE utf8_bin NOT NULL,
  `strategy_protocal_type` varchar(4) COLLATE utf8_bin NOT NULL DEFAULT '',
  `scr_ip` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '无',
  `dest_ip` varchar(48) COLLATE utf8_bin NOT NULL DEFAULT '无',
  `src_port` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '无',
  `dest_port` varchar(48) COLLATE utf8_bin NOT NULL DEFAULT '无',
  `collect_time` datetime NOT NULL,
  `id_system` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `id_strategy` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `strategy_protocal_type` (`strategy_protocal_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sysstrategyinf
-- ----------------------------
INSERT INTO `sysstrategyinf` VALUES ('1', '查询比对安全策略', '1004', '192.168.20.21-192.168.20.254', '192.168.20.21-192.168.20.254', '1-65535', '1-65535', '2012-04-10 16:16:23', null, null);

-- ----------------------------
-- Table structure for `systerminalinfo`
-- ----------------------------
DROP TABLE IF EXISTS `systerminalinfo`;
CREATE TABLE `systerminalinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TerminalName` varchar(30) NOT NULL,
  `Terminaltype` varchar(4) NOT NULL,
  `TermianlOutlink` varchar(3) NOT NULL,
  `Termianlos` varchar(3) NOT NULL,
  `Termianlband` varchar(3) NOT NULL,
  `cardtype` varchar(4) NOT NULL,
  `Cardname` varchar(30) NOT NULL,
  `card_version` varchar(30) NOT NULL,
  `userid` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `userdepart` varchar(5) NOT NULL,
  `userzone` varchar(6) NOT NULL,
  `policenumber` varchar(50) NOT NULL,
  `Regtime` datetime NOT NULL,
  `Ifcancel` varchar(2) NOT NULL,
  `flag` varchar(1) NOT NULL,
  `Idsystem` bigint(20) DEFAULT NULL,
  `Idterminal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systerminalinfo
-- ----------------------------
INSERT INTO `systerminalinfo` VALUES ('1', '一所测试1', '003', '001', '005', '027', '001', '200', 'v3.0', '123456789123456789', 'sxl', '00000', '410100', '123456789123456789', '2012-04-17 13:37:01', '0', '2', null, null);
INSERT INTO `systerminalinfo` VALUES ('10', '一所测试2', '003', '002', '005', '013', '001', '200', 'v3.0', '330585451258523321', '李四', '14000', '410000', '222222222222222222', '2012-04-17 13:38:24', '0', '2', null, null);

-- ----------------------------
-- Table structure for `systerminalstatus`
-- ----------------------------
DROP TABLE IF EXISTS `systerminalstatus`;
CREATE TABLE `systerminalstatus` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `idterminal` bigint(11) NOT NULL,
  `isonline` char(1) NOT NULL DEFAULT '0',
  `tprintscreen` varchar(500) NOT NULL,
  `tscreentime` date NOT NULL,
  `accessurl` varchar(254) NOT NULL,
  `idsystem` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systerminalstatus
-- ----------------------------

-- ----------------------------
-- Table structure for `systerminalstatus_report`
-- ----------------------------
DROP TABLE IF EXISTS `systerminalstatus_report`;
CREATE TABLE `systerminalstatus_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auditDate` datetime NOT NULL,
  `accessUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `busName` varchar(50) NOT NULL,
  `cardType` varchar(4) NOT NULL,
  `count` int(11) NOT NULL,
  `flux` int(11) NOT NULL,
  `idTerminal` bigint(12) NOT NULL,
  `policeNumber` varchar(50) NOT NULL,
  `userdepart` varchar(12) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `userZone` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systerminalstatus_report
-- ----------------------------

-- ----------------------------
-- Table structure for `systerminalstatus_run`
-- ----------------------------
DROP TABLE IF EXISTS `systerminalstatus_run`;
CREATE TABLE `systerminalstatus_run` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auditDate` datetime NOT NULL,
  `accessUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `busName` varchar(50) NOT NULL,
  `cardType` varchar(4) NOT NULL,
  `count` int(11) NOT NULL,
  `flux` int(20) NOT NULL,
  `idTerminal` int(12) NOT NULL,
  `policeNumber` varchar(50) NOT NULL,
  `userdepart` varchar(12) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `userZone` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systerminalstatus_run
-- ----------------------------

-- ----------------------------
-- Table structure for `systerminalstatus_run_report`
-- ----------------------------
DROP TABLE IF EXISTS `systerminalstatus_run_report`;
CREATE TABLE `systerminalstatus_run_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auditDate` datetime DEFAULT NULL,
  `accessUrl` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `busName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cardType` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  `flux` bigint(20) DEFAULT NULL,
  `idTerminal` bigint(20) DEFAULT NULL,
  `policeNumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `userDepart` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `userId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `userZone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of systerminalstatus_run_report
-- ----------------------------

-- ----------------------------
-- Table structure for `tenimal_list`
-- ----------------------------
DROP TABLE IF EXISTS `tenimal_list`;
CREATE TABLE `tenimal_list` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(30) DEFAULT NULL,
  `cardtype` varchar(10) DEFAULT NULL,
  `card_model` varchar(20) DEFAULT NULL,
  `card_version` varchar(10) DEFAULT NULL,
  `card_certid_date` datetime DEFAULT NULL,
  `userdepart` varchar(30) DEFAULT NULL,
  `userzone` varchar(30) DEFAULT NULL,
  `policenumber` varchar(20) DEFAULT NULL,
  `police_name` varchar(20) DEFAULT NULL,
  `cert_id` varchar(60) DEFAULT NULL,
  `certcreate_date` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `block_type` char(1) DEFAULT 'A',
  `status` varchar(1) DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenimal_list
-- ----------------------------
INSERT INTO `tenimal_list` VALUES ('1', '11', '1', '111', '11', '2012-03-20 11:11:27', '11', '11', '11', '11', '11', '2012-03-20 11:22:11', '2012-03-20 11:22:31', 'A', '0', null);
INSERT INTO `tenimal_list` VALUES ('2', '22', '2', '222', '22', '2012-03-20 11:53:13', '22', '22', '22', '22', '22', '2012-03-20 11:53:18', '2012-03-20 11:53:19', 'A', '1', null);

-- ----------------------------
-- Table structure for `tenimal_operation_audit`
-- ----------------------------
DROP TABLE IF EXISTS `tenimal_operation_audit`;
CREATE TABLE `tenimal_operation_audit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(255) DEFAULT NULL,
  `cardtype` varchar(10) DEFAULT NULL,
  `policenumber` varchar(100) DEFAULT NULL,
  `police_name` varchar(100) DEFAULT NULL,
  `cert_id` varchar(100) DEFAULT NULL,
  `operate_tpye` char(1) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  `operater` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenimal_operation_audit
-- ----------------------------

-- ----------------------------
-- Table structure for `termianlband`
-- ----------------------------
DROP TABLE IF EXISTS `termianlband`;
CREATE TABLE `termianlband` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `band_name` varchar(20) DEFAULT NULL,
  `band_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of termianlband
-- ----------------------------
INSERT INTO `termianlband` VALUES ('1', '联想', '001');
INSERT INTO `termianlband` VALUES ('2', '惠普', '002');
INSERT INTO `termianlband` VALUES ('3', '华硕', '003');
INSERT INTO `termianlband` VALUES ('4', '索尼', '004');
INSERT INTO `termianlband` VALUES ('5', '三星', '005');
INSERT INTO `termianlband` VALUES ('6', '戴尔', '006');
INSERT INTO `termianlband` VALUES ('7', '东芝', '007');
INSERT INTO `termianlband` VALUES ('8', '明基', '008');
INSERT INTO `termianlband` VALUES ('9', '神舟', '009');
INSERT INTO `termianlband` VALUES ('10', '海尔', '010');
INSERT INTO `termianlband` VALUES ('11', '清华同方', '011');
INSERT INTO `termianlband` VALUES ('12', '方正', '012');
INSERT INTO `termianlband` VALUES ('13', '苹果', '013');
INSERT INTO `termianlband` VALUES ('14', '富士通', '014');
INSERT INTO `termianlband` VALUES ('15', '诺基亚', '015');
INSERT INTO `termianlband` VALUES ('16', 'HTC', '016');
INSERT INTO `termianlband` VALUES ('17', '摩托罗拉', '107');
INSERT INTO `termianlband` VALUES ('18', '索尼爱立信', '018');
INSERT INTO `termianlband` VALUES ('19', '黑莓', '019');
INSERT INTO `termianlband` VALUES ('20', '天语', '020');
INSERT INTO `termianlband` VALUES ('21', 'LG', '021');
INSERT INTO `termianlband` VALUES ('22', '酷派', '022');
INSERT INTO `termianlband` VALUES ('23', '魅族', '023');
INSERT INTO `termianlband` VALUES ('24', '步步高', '024');
INSERT INTO `termianlband` VALUES ('25', '华为', '025');
INSERT INTO `termianlband` VALUES ('26', '中兴', '026');
INSERT INTO `termianlband` VALUES ('27', '爱国者', '027');
INSERT INTO `termianlband` VALUES ('28', '其他', '000');

-- ----------------------------
-- Table structure for `termianlos`
-- ----------------------------
DROP TABLE IF EXISTS `termianlos`;
CREATE TABLE `termianlos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `OS_name` varchar(40) DEFAULT NULL,
  `OS_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of termianlos
-- ----------------------------
INSERT INTO `termianlos` VALUES ('1', 'Android', '001');
INSERT INTO `termianlos` VALUES ('2', 'Windows Mobile', '002');
INSERT INTO `termianlos` VALUES ('3', 'Windows Phone 7', '003');
INSERT INTO `termianlos` VALUES ('4', 'Windows CE', '004');
INSERT INTO `termianlos` VALUES ('5', 'Windows XP', '005');
INSERT INTO `termianlos` VALUES ('6', 'Windows 2000', '006');
INSERT INTO `termianlos` VALUES ('7', 'Windows 7', '007');
INSERT INTO `termianlos` VALUES ('8', 'Ubuntu', '008');
INSERT INTO `termianlos` VALUES ('9', 'Redhat', '009');
INSERT INTO `termianlos` VALUES ('10', 'Fedora', '010');
INSERT INTO `termianlos` VALUES ('11', 'CentOS', '011');
INSERT INTO `termianlos` VALUES ('12', 'Symbian', '012');
INSERT INTO `termianlos` VALUES ('13', '其他', '000');

-- ----------------------------
-- Table structure for `termianloutlink`
-- ----------------------------
DROP TABLE IF EXISTS `termianloutlink`;
CREATE TABLE `termianloutlink` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(20) DEFAULT NULL,
  `link_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of termianloutlink
-- ----------------------------
INSERT INTO `termianloutlink` VALUES ('1', '中国移动', '001');
INSERT INTO `termianloutlink` VALUES ('2', '中国联通', '002');
INSERT INTO `termianloutlink` VALUES ('3', '中国电信', '003');
INSERT INTO `termianloutlink` VALUES ('4', '其它', '000');

-- ----------------------------
-- Table structure for `terminal_access_url`
-- ----------------------------
DROP TABLE IF EXISTS `terminal_access_url`;
CREATE TABLE `terminal_access_url` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `busName` varchar(50) COLLATE utf8_bin NOT NULL,
  `accessUrl` varchar(500) COLLATE utf8_bin NOT NULL,
  `resourceRegx` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '**',
  `action` varchar(1) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `idTerminal` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of terminal_access_url
-- ----------------------------
INSERT INTO `terminal_access_url` VALUES ('1', 'bsquery', 'http://192.168.30.30/1/', '**', '1', '10');

-- ----------------------------
-- Table structure for `terminal_address`
-- ----------------------------
DROP TABLE IF EXISTS `terminal_address`;
CREATE TABLE `terminal_address` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '0.0.0.0' COMMENT '终端加固地址',
  `port` int(6) NOT NULL DEFAULT '8080' COMMENT '终端加固端口',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of terminal_address
-- ----------------------------
INSERT INTO `terminal_address` VALUES ('1', '192.168.1.211', '225');

-- ----------------------------
-- Table structure for `terminal_auth_code`
-- ----------------------------
DROP TABLE IF EXISTS `terminal_auth_code`;
CREATE TABLE `terminal_auth_code` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL COMMENT '代码',
  `code_desc` varchar(40) DEFAULT NULL COMMENT '认证方式',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='终端认证方式代码表';

-- ----------------------------
-- Records of terminal_auth_code
-- ----------------------------
INSERT INTO `terminal_auth_code` VALUES ('1', ' 0001', ' 硬件数字证书');
INSERT INTO `terminal_auth_code` VALUES ('2', ' 0002', ' 公安PKI/PMI系统定制数字证书');
INSERT INTO `terminal_auth_code` VALUES ('3', ' 0003', ' 公安数字证书');
INSERT INTO `terminal_auth_code` VALUES ('4', ' 0004', ' 动态令牌');
INSERT INTO `terminal_auth_code` VALUES ('5', ' 保留', ' 其它');

-- ----------------------------
-- Table structure for `terminaltype`
-- ----------------------------
DROP TABLE IF EXISTS `terminaltype`;
CREATE TABLE `terminaltype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  `type_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of terminaltype
-- ----------------------------
INSERT INTO `terminaltype` VALUES ('1', '手持设备', '001');
INSERT INTO `terminaltype` VALUES ('2', '车载专用设备', '002');
INSERT INTO `terminaltype` VALUES ('3', '笔记本', '003');
INSERT INTO `terminaltype` VALUES ('4', '其它类型', '004');

-- ----------------------------
-- Table structure for `ternimal_access_audit`
-- ----------------------------
DROP TABLE IF EXISTS `ternimal_access_audit`;
CREATE TABLE `ternimal_access_audit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `card_type` varchar(10) DEFAULT NULL,
  `police_id` varchar(50) DEFAULT NULL,
  `police_name` varchar(30) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `message_level` varchar(10) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `userId` varchar(50) NOT NULL,
  `userDepart` varchar(12) NOT NULL,
  `userZone` varchar(12) NOT NULL,
  `flux` int(12) NOT NULL DEFAULT '0',
  `busName` varchar(50) NOT NULL,
  `count` int(12) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ternimal_access_audit
-- ----------------------------

-- ----------------------------
-- Table structure for `ternimal_access_audit_report`
-- ----------------------------
DROP TABLE IF EXISTS `ternimal_access_audit_report`;
CREATE TABLE `ternimal_access_audit_report` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `cardType` varchar(4) NOT NULL,
  `policeType` varchar(50) DEFAULT NULL,
  `userId` varchar(50) NOT NULL,
  `userDepart` varchar(12) NOT NULL COMMENT '公安机关机构代码',
  `reportDate` datetime NOT NULL,
  `flux` int(12) NOT NULL DEFAULT '0',
  `busName` varchar(50) NOT NULL COMMENT '业务名称',
  `count` int(12) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ternimal_access_audit_report
-- ----------------------------

-- ----------------------------
-- Table structure for `use_depart_type`
-- ----------------------------
DROP TABLE IF EXISTS `use_depart_type`;
CREATE TABLE `use_depart_type` (
  `depart_code` varchar(10) DEFAULT NULL COMMENT '部门号',
  `depart_tpye_desc` varchar(40) DEFAULT NULL COMMENT '部门描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of use_depart_type
-- ----------------------------
INSERT INTO `use_depart_type` VALUES ('01000', '社会企事业单位');
INSERT INTO `use_depart_type` VALUES ('01501', '商业银行部门');
INSERT INTO `use_depart_type` VALUES ('01505', '保险部门');
INSERT INTO `use_depart_type` VALUES ('01529', '电信运营商');
INSERT INTO `use_depart_type` VALUES ('02000', '党/政/军部门');
INSERT INTO `use_depart_type` VALUES ('02216', '政法委员会');
INSERT INTO `use_depart_type` VALUES ('02301', '外交部门');
INSERT INTO `use_depart_type` VALUES ('02313', '安全部门');
INSERT INTO `use_depart_type` VALUES ('02320', '中国人民银行');
INSERT INTO `use_depart_type` VALUES ('02348', '交通部门');
INSERT INTO `use_depart_type` VALUES ('02436', '政府港澳事务办公室');
INSERT INTO `use_depart_type` VALUES ('03000', '公安机关驻地外接入点');
INSERT INTO `use_depart_type` VALUES ('03001', '社区警务室');
INSERT INTO `use_depart_type` VALUES ('03002', '农村警务室');
INSERT INTO `use_depart_type` VALUES ('03003', '政府办证大厅');
INSERT INTO `use_depart_type` VALUES ('其他', '保留');

-- ----------------------------
-- Table structure for `user_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_oper_log`;
CREATE TABLE `user_oper_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '审计时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志级别',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `audit_module` varchar(255) DEFAULT NULL COMMENT '审计位置',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8 COMMENT='用户操作审计表';

-- ----------------------------
-- Records of user_oper_log
-- ----------------------------
INSERT INTO `user_oper_log` VALUES ('1', '2012-11-22 13:11:30', 'INFO', 'admin', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('2', '2012-11-22 13:12:46', 'INFO', 'admin', '角色管理', '角色名 测试管理员 新增成功');
INSERT INTO `user_oper_log` VALUES ('3', '2012-11-22 13:12:52', 'INFO', 'admin', '用户登录', '用户退出成功');
INSERT INTO `user_oper_log` VALUES ('4', '2012-11-22 13:13:04', 'INFO', 'authadmin', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('5', '2012-11-22 13:13:53', 'INFO', 'authadmin', '用户管理', '用户 测试员 新增成功');
INSERT INTO `user_oper_log` VALUES ('6', '2012-11-22 13:13:57', 'INFO', 'authadmin', '用户登录', '用户退出成功');
INSERT INTO `user_oper_log` VALUES ('7', '2012-11-22 13:14:16', 'INFO', 'authadmin', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('8', '2012-11-22 13:14:34', 'INFO', 'authadmin', '用户管理', '用户 测试员 修改成功');
INSERT INTO `user_oper_log` VALUES ('9', '2012-11-22 13:14:38', 'INFO', 'authadmin', '用户登录', '用户退出成功');
INSERT INTO `user_oper_log` VALUES ('10', '2012-11-22 13:14:43', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('11', '2012-11-22 13:15:13', 'ERROR', 'test', '接口管理', '用户读取接口信息不成功 ');
INSERT INTO `user_oper_log` VALUES ('12', '2012-11-22 13:15:17', 'INFO', 'test', '路由管理', '用户读取路由信息成功 ');
INSERT INTO `user_oper_log` VALUES ('13', '2012-11-22 13:18:57', 'INFO', 'test', '连通测试', '用户ping成功 ');
INSERT INTO `user_oper_log` VALUES ('14', '2012-11-22 14:44:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('15', '2012-11-22 15:03:13', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('16', '2012-11-22 15:12:01', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('17', '2012-11-22 15:13:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('18', '2012-11-22 15:22:30', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('19', '2012-11-22 15:24:45', 'INFO', 'test', '链路配置', '内部链路 231 新增成功');
INSERT INTO `user_oper_log` VALUES ('20', '2012-11-22 15:24:57', 'INFO', 'test', '链路配置', '内部链路 231 删除成功');
INSERT INTO `user_oper_log` VALUES ('21', '2012-11-22 15:30:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('22', '2012-11-22 15:43:55', 'ERROR', 'test', '接口管理', '用户读取接口信息不成功 ');
INSERT INTO `user_oper_log` VALUES ('23', '2012-11-22 16:01:39', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('24', '2012-11-22 16:01:46', 'INFO', 'test', '路由管理', '用户读取路由信息成功 ');
INSERT INTO `user_oper_log` VALUES ('25', '2012-11-22 16:09:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('26', '2012-11-22 16:12:23', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('27', '2012-11-22 16:13:18', 'INFO', 'test', '路由管理', '用户读取路由信息成功 ');
INSERT INTO `user_oper_log` VALUES ('28', '2012-11-22 16:13:21', 'INFO', 'test', '路由管理', '用户读取所有接口名成功 ');
INSERT INTO `user_oper_log` VALUES ('29', '2012-11-22 16:15:22', 'INFO', 'test', '连通测试', '用户ping成功 ');
INSERT INTO `user_oper_log` VALUES ('30', '2012-11-22 16:49:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('31', '2012-11-22 16:51:55', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('32', '2012-11-22 16:59:10', 'ERROR', 'test', '连通测试', '用户ping不成功 ');
INSERT INTO `user_oper_log` VALUES ('33', '2012-11-22 16:59:13', 'ERROR', 'test', '连通测试', '用户ping不成功 ');
INSERT INTO `user_oper_log` VALUES ('34', '2012-11-22 16:59:49', 'ERROR', 'test', '连通测试', '用户ping不成功 ');
INSERT INTO `user_oper_log` VALUES ('35', '2012-11-22 17:01:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('36', '2012-11-22 17:08:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('37', '2012-11-22 17:14:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('38', '2012-11-22 17:46:58', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('39', '2012-11-22 17:52:13', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('40', '2012-11-22 17:59:29', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('41', '2012-11-22 18:04:14', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('42', '2012-11-22 18:16:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('43', '2012-11-22 18:23:22', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('44', '2012-11-22 18:24:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('45', '2012-11-22 18:26:05', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('46', '2012-11-22 18:26:40', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('47', '2012-11-22 18:26:54', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('48', '2012-11-22 18:26:54', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('49', '2012-11-22 18:35:40', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('50', '2012-11-22 18:38:03', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('51', '2012-11-22 18:41:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('52', '2012-11-22 18:41:39', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('53', '2012-11-22 18:43:01', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('54', '2012-11-22 18:43:37', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('55', '2012-11-22 18:43:58', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('56', '2012-11-22 18:44:20', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('57', '2012-11-22 18:45:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('58', '2012-11-22 18:45:47', 'INFO', 'test', '设备配置管理', '设备 123456 新增成功');
INSERT INTO `user_oper_log` VALUES ('59', '2012-11-22 18:48:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('60', '2012-11-22 18:48:53', 'INFO', 'test', '设备配置管理', '设备 123456 删除成功');
INSERT INTO `user_oper_log` VALUES ('61', '2012-11-22 18:49:06', 'INFO', 'test', '设备配置管理', '设备 4131 新增成功');
INSERT INTO `user_oper_log` VALUES ('62', '2012-11-22 19:05:35', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('63', '2012-11-22 19:06:08', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('64', '2012-11-22 19:06:26', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('65', '2012-11-22 19:06:44', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('66', '2012-11-22 19:07:04', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('67', '2012-11-22 19:08:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('68', '2012-11-22 19:08:46', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('69', '2012-11-23 09:31:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('70', '2012-11-23 09:31:57', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('71', '2012-11-23 09:32:32', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('72', '2012-11-23 09:32:36', 'INFO', 'test', '设备配置管理', '设备 4131 删除成功');
INSERT INTO `user_oper_log` VALUES ('73', '2012-11-23 09:32:45', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('74', '2012-11-23 09:34:44', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('75', '2012-11-23 09:35:10', 'INFO', 'test', '设备配置管理', '设备 523hgd 新增成功');
INSERT INTO `user_oper_log` VALUES ('76', '2012-11-23 09:35:36', 'INFO', 'test', '设备配置管理', '设备 523hgd 删除成功');
INSERT INTO `user_oper_log` VALUES ('77', '2012-11-23 09:35:46', 'INFO', 'test', '设备配置管理', '设备 13113 新增成功');
INSERT INTO `user_oper_log` VALUES ('78', '2012-11-23 09:36:20', 'INFO', 'test', '设备配置管理', '设备 13113 删除成功');
INSERT INTO `user_oper_log` VALUES ('79', '2012-11-23 09:43:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('80', '2012-11-23 09:45:19', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('81', '2012-11-23 09:47:51', 'INFO', 'test', '设备配置管理', '设备 313131 新增成功');
INSERT INTO `user_oper_log` VALUES ('82', '2012-11-23 09:48:01', 'INFO', 'test', '设备配置管理', '设备 313131 删除成功');
INSERT INTO `user_oper_log` VALUES ('83', '2012-11-23 09:50:48', 'INFO', 'test', '设备配置管理', '设备 31331 新增成功');
INSERT INTO `user_oper_log` VALUES ('84', '2012-11-23 09:55:30', 'INFO', 'test', '设备配置管理', '设备 131213 新增成功');
INSERT INTO `user_oper_log` VALUES ('85', '2012-11-23 09:55:39', 'INFO', 'test', '设备配置管理', '设备 31331 删除成功');
INSERT INTO `user_oper_log` VALUES ('86', '2012-11-23 09:55:39', 'INFO', 'test', '设备配置管理', '设备 131213 删除成功');
INSERT INTO `user_oper_log` VALUES ('87', '2012-11-23 09:59:27', 'INFO', 'test', '设备配置管理', '设备 13131 新增成功');
INSERT INTO `user_oper_log` VALUES ('88', '2012-11-23 10:00:47', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('89', '2012-11-23 10:00:55', 'INFO', 'test', '设备配置管理', '设备 13131 删除成功');
INSERT INTO `user_oper_log` VALUES ('90', '2012-11-23 10:01:47', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('91', '2012-11-23 10:03:58', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('92', '2012-11-23 10:11:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('93', '2012-11-23 10:11:42', 'INFO', 'test', '设备配置管理', '设备 33333 删除成功');
INSERT INTO `user_oper_log` VALUES ('94', '2012-11-23 10:13:07', 'INFO', 'test', '设备配置管理', '设备 3131313131 新增成功');
INSERT INTO `user_oper_log` VALUES ('95', '2012-11-23 10:14:44', 'INFO', 'test', '设备配置管理', '设备 3131313131 删除成功');
INSERT INTO `user_oper_log` VALUES ('96', '2012-11-23 10:16:09', 'INFO', 'test', '设备配置管理', '设备 31131 新增成功');
INSERT INTO `user_oper_log` VALUES ('97', '2012-11-23 10:18:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('98', '2012-11-23 10:19:40', 'INFO', 'test', '设备配置管理', '设备 12313 新增成功');
INSERT INTO `user_oper_log` VALUES ('99', '2012-11-23 10:20:28', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('100', '2012-11-23 10:23:16', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('101', '2012-11-23 10:23:27', 'INFO', 'test', '设备配置管理', '设备 31131 删除成功');
INSERT INTO `user_oper_log` VALUES ('102', '2012-11-23 10:23:27', 'INFO', 'test', '设备配置管理', '设备 12313 删除成功');
INSERT INTO `user_oper_log` VALUES ('103', '2012-11-23 10:24:25', 'INFO', 'test', '设备配置管理', '设备 adada 新增成功');
INSERT INTO `user_oper_log` VALUES ('104', '2012-11-23 10:25:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('105', '2012-11-23 10:25:55', 'INFO', 'test', '设备配置管理', '设备 adada 删除成功');
INSERT INTO `user_oper_log` VALUES ('106', '2012-11-23 10:26:15', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('107', '2012-11-23 10:27:35', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('108', '2012-11-23 10:28:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('109', '2012-11-23 10:29:03', 'INFO', 'test', '设备配置管理', '设备 123313 新增成功');
INSERT INTO `user_oper_log` VALUES ('110', '2012-11-23 10:29:23', 'INFO', 'test', '设备配置管理', '设备 123313 删除成功');
INSERT INTO `user_oper_log` VALUES ('111', '2012-11-23 10:34:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('112', '2012-11-23 10:41:56', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('113', '2012-11-23 10:42:16', 'INFO', 'test', '设备配置管理', '设备 313131 新增成功');
INSERT INTO `user_oper_log` VALUES ('114', '2012-11-23 10:43:00', 'INFO', 'test', '设备配置管理', '设备 313131 修改成功');
INSERT INTO `user_oper_log` VALUES ('115', '2012-11-23 10:47:36', 'INFO', 'test', '设备配置管理', '设备 313131 修改成功');
INSERT INTO `user_oper_log` VALUES ('116', '2012-11-23 10:48:09', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('117', '2012-11-23 10:48:20', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('118', '2012-11-23 10:50:02', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('119', '2012-11-23 10:52:55', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('120', '2012-11-23 10:58:42', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('121', '2012-11-23 11:00:47', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('122', '2012-11-23 11:05:13', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('123', '2012-11-23 11:05:37', 'INFO', 'test', '设备配置管理', '设备 2222 修改成功');
INSERT INTO `user_oper_log` VALUES ('124', '2012-11-23 11:06:06', 'INFO', 'test', '设备配置管理', '设备 222222313 新增成功');
INSERT INTO `user_oper_log` VALUES ('125', '2012-11-23 11:08:03', 'INFO', 'test', '设备配置管理', '设备 1313131 新增成功');
INSERT INTO `user_oper_log` VALUES ('126', '2012-11-23 11:08:18', 'INFO', 'test', '设备配置管理', '设备 1313131 删除成功');
INSERT INTO `user_oper_log` VALUES ('127', '2012-11-23 11:08:18', 'INFO', 'test', '设备配置管理', '设备 2222 删除成功');
INSERT INTO `user_oper_log` VALUES ('128', '2012-11-23 11:08:18', 'INFO', 'test', '设备配置管理', '设备 222222313 删除成功');
INSERT INTO `user_oper_log` VALUES ('129', '2012-11-23 11:09:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('130', '2012-11-23 11:09:43', 'ERROR', 'test', '设备配置管理', '设备 null 新增不成功');
INSERT INTO `user_oper_log` VALUES ('131', '2012-11-23 11:10:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('132', '2012-11-23 11:10:41', 'INFO', 'test', '设备配置管理', '设备 3323 新增成功');
INSERT INTO `user_oper_log` VALUES ('133', '2012-11-23 11:10:55', 'INFO', 'test', '设备配置管理', '设备 3323 修改成功');
INSERT INTO `user_oper_log` VALUES ('134', '2012-11-23 12:16:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('135', '2012-11-23 12:24:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('136', '2012-11-23 12:38:23', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('137', '2012-11-23 13:25:30', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('138', '2012-11-23 13:47:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('139', '2012-11-23 13:47:40', 'INFO', 'test', '设备配置管理', '设备 qxppc 新增成功');
INSERT INTO `user_oper_log` VALUES ('140', '2012-11-23 14:21:22', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('141', '2012-11-23 14:25:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('142', '2012-11-23 14:30:57', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('143', '2012-11-23 14:35:21', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('144', '2012-11-23 14:35:23', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('145', '2012-11-23 14:35:24', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('146', '2012-11-23 14:35:24', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('147', '2012-11-23 14:35:24', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('148', '2012-11-23 14:35:25', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('149', '2012-11-23 14:35:25', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('150', '2012-11-23 14:35:26', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('151', '2012-11-23 14:35:26', 'INFO', 'test', '设备配置管理', '设备 lmypc 新增成功');
INSERT INTO `user_oper_log` VALUES ('152', '2012-11-23 14:36:51', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('153', '2012-11-23 14:36:58', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('154', '2012-11-23 14:37:08', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('155', '2012-11-23 14:37:15', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('156', '2012-11-23 14:37:15', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('157', '2012-11-23 14:37:15', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('158', '2012-11-23 14:37:15', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('159', '2012-11-23 14:37:21', 'INFO', 'test', '设备配置管理', '设备 lmypc 删除成功');
INSERT INTO `user_oper_log` VALUES ('160', '2012-11-23 14:49:50', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('161', '2012-11-23 14:50:20', 'ERROR', 'test', '设备配置管理', '设备 e1313131 新增不成功');
INSERT INTO `user_oper_log` VALUES ('162', '2012-11-23 14:50:36', 'INFO', 'test', '设备配置管理', '设备 3131 新增成功');
INSERT INTO `user_oper_log` VALUES ('163', '2012-11-23 14:50:41', 'INFO', 'test', '设备配置管理', '设备 3131 删除成功');
INSERT INTO `user_oper_log` VALUES ('164', '2012-11-23 15:08:40', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('165', '2012-11-23 15:25:30', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('166', '2012-11-23 15:25:44', 'INFO', 'test', '设备配置管理', '设备 3323 删除成功');
INSERT INTO `user_oper_log` VALUES ('167', '2012-11-23 16:24:42', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('168', '2012-11-23 18:08:23', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('169', '2012-11-26 09:25:22', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('170', '2012-11-26 09:33:50', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('171', '2012-11-26 10:48:17', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('172', '2012-11-26 10:55:13', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('173', '2012-11-26 11:23:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('174', '2012-11-26 11:23:43', 'INFO', 'test', '连通测试', '用户ping成功 ');
INSERT INTO `user_oper_log` VALUES ('175', '2012-11-26 11:27:16', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('176', '2012-11-26 13:55:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('177', '2012-11-26 14:58:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('178', '2012-11-27 09:17:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('179', '2012-11-27 09:57:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('180', '2012-11-27 10:00:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('181', '2012-11-27 10:11:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('182', '2012-11-27 10:13:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('183', '2012-11-27 10:20:34', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('184', '2012-11-27 10:22:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('185', '2012-11-27 10:24:20', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('186', '2012-11-27 10:24:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('187', '2012-11-27 10:27:44', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('188', '2012-11-27 10:28:58', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('189', '2012-11-27 10:30:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('190', '2012-11-27 10:35:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('191', '2012-11-27 10:40:07', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('192', '2012-11-27 10:41:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('193', '2012-11-27 10:46:40', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('194', '2012-11-27 11:11:52', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('195', '2012-11-27 11:31:15', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('196', '2012-11-27 11:34:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('197', '2012-11-27 14:22:22', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('198', '2012-11-27 14:42:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('199', '2012-11-27 14:46:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('200', '2012-11-27 15:02:34', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('201', '2012-11-27 15:06:47', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('202', '2012-11-27 15:08:03', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('203', '2012-11-27 15:13:09', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('204', '2012-11-27 15:16:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('205', '2012-11-27 15:19:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('206', '2012-11-27 15:23:07', 'INFO', 'test', '日志下载', ' 用户获取所有本地日志名称、大小信息成功');
INSERT INTO `user_oper_log` VALUES ('207', '2012-11-27 15:23:11', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('208', '2012-11-27 15:23:12', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('209', '2012-11-27 15:23:12', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('210', '2012-11-27 15:23:54', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('211', '2012-11-27 15:30:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('212', '2012-11-27 15:33:19', 'INFO', 'test', '日志下载', ' 用户获取所有本地日志名称、大小信息成功');
INSERT INTO `user_oper_log` VALUES ('213', '2012-11-27 15:33:21', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('214', '2012-11-27 15:41:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('215', '2012-11-27 16:07:16', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('216', '2012-11-27 16:22:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('217', '2012-11-27 16:34:40', 'INFO', 'test', '设备配置管理', '设备  新增成功');
INSERT INTO `user_oper_log` VALUES ('218', '2012-11-27 16:34:47', 'INFO', 'test', '设备配置管理', '设备  删除成功');
INSERT INTO `user_oper_log` VALUES ('219', '2012-11-27 16:45:36', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('220', '2012-11-27 17:12:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('221', '2012-11-27 17:13:12', 'INFO', 'test', '设备配置管理', '设备  新增成功');
INSERT INTO `user_oper_log` VALUES ('222', '2012-11-27 17:14:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('223', '2012-11-27 17:14:43', 'INFO', 'test', '设备配置管理', '设备  删除成功');
INSERT INTO `user_oper_log` VALUES ('224', '2012-11-27 17:19:21', 'INFO', 'test', '设备配置管理', '设备  新增成功');
INSERT INTO `user_oper_log` VALUES ('225', '2012-11-27 17:23:53', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('226', '2012-11-27 17:24:05', 'INFO', 'test', '设备配置管理', '设备  删除成功');
INSERT INTO `user_oper_log` VALUES ('227', '2012-11-27 17:24:13', 'INFO', 'test', '设备配置管理', '设备 lmy 新增成功');
INSERT INTO `user_oper_log` VALUES ('228', '2012-11-27 17:24:18', 'INFO', 'test', '设备配置管理', '设备 lmy 删除成功');
INSERT INTO `user_oper_log` VALUES ('229', '2012-11-27 17:41:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('230', '2012-11-27 17:45:05', 'INFO', 'test', '日志下载', ' 用户获取所有本地日志名称、大小信息成功');
INSERT INTO `user_oper_log` VALUES ('231', '2012-11-27 17:45:06', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('232', '2012-11-27 17:45:07', 'INFO', 'test', '日志下载', ' 用户下载日志成功');
INSERT INTO `user_oper_log` VALUES ('233', '2012-11-27 17:48:21', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('234', '2012-11-27 17:50:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('235', '2012-11-27 17:56:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('236', '2012-11-27 18:00:46', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('237', '2012-11-27 18:03:09', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('238', '2012-11-27 18:06:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('239', '2012-11-27 18:12:19', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('240', '2012-11-27 18:13:58', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('241', '2012-11-27 18:16:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('242', '2012-11-27 18:20:09', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('243', '2012-11-27 18:21:53', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('244', '2012-11-28 09:25:39', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('245', '2012-11-28 09:28:53', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('246', '2012-11-28 09:43:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('247', '2012-11-28 09:49:05', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('248', '2012-11-28 09:49:44', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('249', '2012-11-28 10:14:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('250', '2012-11-28 10:20:52', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('251', '2012-11-28 10:22:50', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('252', '2012-11-28 10:25:37', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('253', '2012-11-28 11:03:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('254', '2012-11-28 11:53:30', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('255', '2012-11-28 11:57:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('256', '2012-11-28 11:59:37', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('257', '2012-11-28 12:01:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('258', '2012-11-28 12:03:29', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('259', '2012-11-28 12:05:10', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('260', '2012-11-28 12:07:50', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('261', '2012-11-28 12:09:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('262', '2012-11-28 13:25:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('263', '2012-11-28 14:05:08', 'INFO', 'test', '设备配置管理', '设备 1231313 新增成功');
INSERT INTO `user_oper_log` VALUES ('264', '2012-11-28 14:05:52', 'INFO', 'test', '设备配置管理', '设备 1231313 删除成功');
INSERT INTO `user_oper_log` VALUES ('265', '2012-11-28 14:08:48', 'INFO', 'test', '设备配置管理', '设备 231231 新增成功');
INSERT INTO `user_oper_log` VALUES ('266', '2012-11-28 14:08:52', 'INFO', 'test', '设备配置管理', '设备 231231 删除成功');
INSERT INTO `user_oper_log` VALUES ('267', '2012-11-28 14:10:11', 'INFO', 'test', '设备配置管理', '设备 1231 新增成功');
INSERT INTO `user_oper_log` VALUES ('268', '2012-11-28 14:12:25', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('269', '2012-11-28 14:12:45', 'INFO', 'test', '设备配置管理', '设备 1231 删除成功');
INSERT INTO `user_oper_log` VALUES ('270', '2012-11-28 14:13:05', 'INFO', 'test', '设备配置管理', '设备 313 新增成功');
INSERT INTO `user_oper_log` VALUES ('271', '2012-11-28 14:13:35', 'INFO', 'test', '设备配置管理', '设备 313 删除成功');
INSERT INTO `user_oper_log` VALUES ('272', '2012-11-28 15:00:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('273', '2012-11-28 17:06:43', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('274', '2012-11-28 17:07:15', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('275', '2012-11-28 17:09:02', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('276', '2012-11-28 17:11:36', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('277', '2012-11-28 17:13:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('278', '2012-11-28 17:13:53', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('279', '2012-11-28 17:14:09', 'INFO', 'test', '设备配置管理', '设备  新增成功');
INSERT INTO `user_oper_log` VALUES ('280', '2012-11-28 17:14:16', 'INFO', 'test', '设备配置管理', '设备  删除成功');
INSERT INTO `user_oper_log` VALUES ('281', '2012-11-28 17:17:19', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('282', '2012-11-28 17:47:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('283', '2012-11-28 17:51:23', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('284', '2012-11-28 17:52:22', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('285', '2012-11-29 09:56:18', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('286', '2012-11-29 10:23:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('287', '2012-11-29 11:06:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('288', '2012-11-29 11:18:39', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('289', '2012-11-29 11:20:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('290', '2012-11-29 11:22:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('291', '2012-11-29 11:24:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('292', '2012-11-29 11:42:25', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('293', '2012-11-29 11:46:59', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('294', '2012-11-29 11:50:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('295', '2012-11-29 12:01:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('296', '2012-11-29 13:45:30', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('297', '2012-11-29 14:18:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('298', '2012-11-29 16:07:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('299', '2012-11-29 16:15:17', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('300', '2012-11-29 16:23:20', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('301', '2012-11-29 16:24:10', 'INFO', 'test', '基本配置', '用户保存配置成功 ');
INSERT INTO `user_oper_log` VALUES ('302', '2012-11-29 16:25:22', 'INFO', 'test', '基本配置', '用户保存配置成功 ');
INSERT INTO `user_oper_log` VALUES ('303', '2012-11-29 16:32:31', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('304', '2012-11-29 17:01:23', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('305', '2012-11-29 17:06:11', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('306', '2012-11-29 17:59:17', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('307', '2012-11-29 18:21:22', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('308', '2012-11-29 18:23:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('309', '2012-11-29 18:39:25', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('310', '2012-11-30 09:38:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('311', '2012-11-30 09:43:05', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('312', '2012-11-30 09:46:57', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('313', '2012-11-30 09:49:15', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('314', '2012-11-30 09:50:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('315', '2012-11-30 09:53:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('316', '2012-11-30 09:54:35', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('317', '2012-11-30 09:55:43', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('318', '2012-11-30 09:57:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('319', '2012-11-30 09:58:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('320', '2012-11-30 09:59:25', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('321', '2012-11-30 10:08:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('322', '2012-11-30 17:32:34', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('323', '2012-11-30 18:34:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('324', '2012-11-30 18:38:03', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('325', '2012-11-30 18:39:17', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('326', '2012-11-30 18:43:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('327', '2012-11-30 18:53:53', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('328', '2012-11-30 18:56:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('329', '2012-11-30 19:21:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('330', '2012-11-30 19:28:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('331', '2012-11-30 19:31:52', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('332', '2012-11-30 19:49:21', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('333', '2012-11-30 19:52:05', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('334', '2012-11-30 19:55:39', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('335', '2012-11-30 19:59:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('336', '2012-11-30 20:00:45', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('337', '2012-11-30 20:04:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('338', '2012-11-30 20:06:02', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('339', '2012-11-30 20:08:57', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('340', '2012-11-30 20:11:33', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('341', '2012-11-30 20:16:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('342', '2012-11-30 20:37:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('343', '2012-12-03 09:32:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('344', '2012-12-03 09:36:32', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('345', '2012-12-03 09:40:02', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('346', '2012-12-03 14:24:55', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('347', '2012-12-03 14:55:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('348', '2012-12-03 15:32:08', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('349', '2012-12-03 15:44:36', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('350', '2012-12-03 15:46:11', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('351', '2012-12-03 15:47:29', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('352', '2012-12-03 15:47:59', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('353', '2012-12-03 15:50:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('354', '2012-12-03 15:51:19', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('355', '2012-12-03 15:53:43', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('356', '2012-12-03 15:56:01', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('357', '2012-12-03 15:56:22', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('358', '2012-12-03 15:56:46', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('359', '2012-12-03 15:57:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('360', '2012-12-03 15:57:52', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('361', '2012-12-03 16:09:49', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('362', '2012-12-03 16:11:10', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('363', '2012-12-03 16:18:12', 'INFO', 'test', '设备配置管理', '设备 lmypc 修改成功');
INSERT INTO `user_oper_log` VALUES ('364', '2012-12-03 16:19:07', 'INFO', 'test', '设备配置管理', '设备 lmypc 修改成功');
INSERT INTO `user_oper_log` VALUES ('365', '2012-12-03 16:35:42', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('366', '2012-12-03 16:36:52', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('367', '2012-12-03 16:39:11', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('368', '2012-12-03 16:39:51', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('369', '2012-12-03 16:48:42', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('370', '2012-12-03 16:49:11', 'INFO', 'test', '设备配置管理', '设备 qxppc 修改成功');
INSERT INTO `user_oper_log` VALUES ('371', '2012-12-03 16:52:00', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('372', '2012-12-03 16:53:48', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('373', '2012-12-03 16:55:12', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('374', '2012-12-03 16:56:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('375', '2012-12-03 16:58:24', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('376', '2012-12-03 17:02:16', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('377', '2012-12-03 17:05:51', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('378', '2012-12-03 17:44:54', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('379', '2012-12-03 17:45:59', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('380', '2012-12-04 09:43:59', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('381', '2012-12-04 15:34:57', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('382', '2012-12-04 15:46:09', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('383', '2012-12-04 15:47:47', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('384', '2012-12-04 15:53:28', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('385', '2012-12-04 15:55:40', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('386', '2012-12-04 15:56:41', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('387', '2012-12-04 16:36:26', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('388', '2012-12-04 16:47:04', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('389', '2012-12-04 16:52:05', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('390', '2012-12-04 16:54:43', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('391', '2012-12-04 17:10:05', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('392', '2012-12-04 17:12:27', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('393', '2012-12-04 17:14:06', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('394', '2012-12-04 17:15:25', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('395', '2012-12-04 17:18:21', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('396', '2012-12-04 17:20:10', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('397', '2012-12-04 17:20:29', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('398', '2012-12-04 17:26:47', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('399', '2012-12-04 17:29:46', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('400', '2012-12-04 17:30:28', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('401', '2012-12-04 17:32:47', 'INFO', 'test', '用户登录', '用户登录成功');
INSERT INTO `user_oper_log` VALUES ('402', '2012-12-04 17:36:17', 'INFO', 'test', '用户登录', '用户登录成功');
