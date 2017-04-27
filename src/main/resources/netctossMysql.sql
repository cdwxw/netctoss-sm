/*
select * from admin_info
where admin_id in (
	select ai.admin_id from admin_info ai
	inner join admin_role ar on ai.admin_id=ar.admin_id
	inner join role_info ri on ri.role_id=ar.role_id
	inner join role_module rm on rm.role_id=ri.role_id
)
order by admin_id
limit 0,5

select s.*,a.idcard_no,a.real_name
from service s
inner join account a
on a.account_id=s.account_id

select ri.*,mi.name
from role_info ri 
inner join role_model rm on rm.role_id=ri.role_id
inner join model_info mi on mi.module_id=rm.module_id

Navicat MySQL Data Transfer

Source Server         : mysql-root
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : netctoss

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-04-15 10:30:58
*/

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `netctoss`;
CREATE DATABASE netctoss;
USE netctoss;

-- 资费信息表
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost` (
  `cost_id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `base_duration` int(11) DEFAULT NULL,
  `base_cost` double(7,2) DEFAULT NULL,
  `unit_cost` double(7,4) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `creatime` datetime DEFAULT current_timestamp COMMENT '数据插入时间',
  `startime` datetime DEFAULT NULL,
  `cost_type` char(1) NOT NULL,
  PRIMARY KEY (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

INSERT INTO `cost` VALUES ('1', '5.9元套餐', '20', '5.90', '0.4000', '1', '5.9元20小时/月,超出部分0.4元/时', '2017-04-08 18:07:38', null, '1');
INSERT INTO `cost` VALUES ('2', '6.9元套餐', '40', '6.90', '0.3000', '1', '6.9元40小时/月,超出部分0.3元/时', '2017-04-08 18:07:38', null, '1');
INSERT INTO `cost` VALUES ('3', '8.5元套餐', '100', '8.50', '0.2000', '1', '8.5元100小时/月,超出部分0.2元/时', '2017-04-08 18:07:38', null, '1');
INSERT INTO `cost` VALUES ('4', '10.5元套餐', '200', '10.50', '0.1000', '1', '10.5元200小时/月,超出部分0.1元/时', '2017-04-08 18:07:38', null, '1');
INSERT INTO `cost` VALUES ('5', '计时收费', null, null, '0.5000', '1', '0.5元/时,不使用不收费', '2017-04-08 18:07:38', null, '3');
INSERT INTO `cost` VALUES ('6', '包月', null, '20.00', null, '1', '每月20元,不限制使用时间', '2017-04-08 18:07:38', null, '2');
INSERT INTO `cost` VALUES ('7', '18元套餐', '20', '5.90', '0.4000', '0', '18元40小时/月,超出部分0.4元/时', '2017-04-08 18:07:38', '2017-04-08 18:07:38', '1');
INSERT INTO `cost` VALUES ('8', '28元套餐', '40', '6.90', '0.3000', '0', '28元60小时/月,超出部分0.3元/时', '2017-04-08 18:07:39', '2017-04-08 18:07:39', '1');
INSERT INTO `cost` VALUES ('9', '38元套餐', '100', '8.50', '0.2000', '0', '38元100小时/月,超出部分0.2元/时', '2017-04-08 18:07:39', '2017-04-08 18:07:39', '1');
INSERT INTO `cost` VALUES ('10', '58元套餐', '200', '10.50', '0.1000', '0', '58元200小时/月,超出部分0.1元/时', '2017-04-08 18:07:39', '2017-04-08 18:07:39', '1');
INSERT INTO `cost` VALUES ('11', '88元套餐', '400', '10.50', '0.1000', '0', '88元400小时/月,超出部分0.1元/时', '2017-04-08 18:07:39', '2017-04-08 18:07:39', '1');
INSERT INTO `cost` VALUES ('12', '包月2', null, '200.00', null, '0', '每月200元,不限制使用时间', '2017-04-08 18:07:39', '2017-04-08 18:07:39', '2');

-- 帐务信息表
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_id` int(9) NOT NULL AUTO_INCREMENT,
  `recommender_id` int(9) DEFAULT NULL,
  `login_name` varchar(30) NOT NULL,
  `login_passwd` varchar(30) NOT NULL,
  `status` char(1) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp COMMENT '数据插入时间',
  `pause_date` datetime DEFAULT NULL,
  `close_date` datetime DEFAULT NULL,
  `real_name` varchar(20) NOT NULL,
  `idcard_no` char(18) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `telephone` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mailaddress` varchar(200) DEFAULT NULL,
  `zipcode` char(6) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `login_name` (`login_name`),
  UNIQUE KEY `idcard_no` (`idcard_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1021 DEFAULT CHARSET=utf8;

INSERT INTO `account` VALUES ('1005', null, 'taiji001', '256528', '1', '2008-03-15 00:00:00', null, null, 'zhangsanfeng', '410381194302256528', '1943-02-25 00:00:00', null, null, '13669351234', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1010', null, 'xl18z60', '190613', '1', '2009-01-10 00:00:00', null, null, 'guojing', '330682196903190613', '1969-03-19 00:00:00', null, null, '13338924567', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1011', '1010', 'dgbf70', '270429', '1', '2009-03-01 00:00:00', null, null, 'huangrong', '330902197108270429', '1971-08-27 00:00:00', null, null, '13637811357', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1015', '1005', 'mjjzh64', '041115', '1', '2010-03-12 00:00:00', null, null, 'zhangwuji', '610121198906041115', '1989-06-04 00:00:00', null, null, '13572952468', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1018', '1011', 'jmdxj00', '010322', '1', '2011-01-01 00:00:00', null, null, 'guofurong', '350581200201010322', '1996-01-01 00:00:00', null, null, '18617832562', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1019', '1011', 'ljxj90', '310346', '1', '2012-02-01 00:00:00', null, null, 'luwushuang', '320211199307310346', '1993-07-31 00:00:00', null, null, '13186454984', null, null, null, null, null, null);
INSERT INTO `account` VALUES ('1020', null, 'kxhxd20', '012115', '1', '2012-02-20 00:00:00', null, null, 'weixiaobao', '321022200010012115', '2000-10-01 00:00:00', null, null, '13953410078', null, null, null, null, null, null);

-- HOST表
DROP TABLE IF EXISTS `host`;
CREATE TABLE `host` (
  `unix_host` varchar(15) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`unix_host`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `host` VALUES ('192.168.0.20', 'sun-server', 'beijing');
INSERT INTO `host` VALUES ('192.168.0.200', 'ultra10', 'chengdu');
INSERT INTO `host` VALUES ('192.168.0.23', 'sun280', 'shenzhen');
INSERT INTO `host` VALUES ('192.168.0.26', 'sunv210', 'shanghai');

-- 业务信息表
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `service_id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` int(9) NOT NULL,
  `unix_host` varchar(15) NOT NULL,
  `os_username` varchar(8) NOT NULL,
  `login_passwd` varchar(30) NOT NULL,
  `status` char(1) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp COMMENT '数据插入时间',
  `pause_date` datetime DEFAULT NULL,
  `close_date` datetime DEFAULT NULL,
  `cost_id` int(4) NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `unix_host` (`unix_host`,`os_username`),
  KEY `fk_serv_acc_id` (`account_id`),
  KEY `fk_serv_cost_id` (`cost_id`),
  CONSTRAINT `fk_serv_acc_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `fk_serv_cost_id` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=utf8;

INSERT INTO `service` VALUES ('2001', '1010', '192.168.0.26', 'guojing', 'guo1234', '0', '2009-03-10 10:00:00', null, null, '1');
INSERT INTO `service` VALUES ('2002', '1011', '192.168.0.26', 'huangr', 'huang234', '0', '2009-03-01 15:30:05', null, null, '1');
INSERT INTO `service` VALUES ('2003', '1011', '192.168.0.20', 'huangr', 'huang234', '0', '2009-03-01 15:30:10', null, null, '3');
INSERT INTO `service` VALUES ('2004', '1011', '192.168.0.23', 'huangr', 'huang234', '0', '2009-03-01 15:30:15', null, null, '6');
INSERT INTO `service` VALUES ('2005', '1019', '192.168.0.26', 'luwsh', 'luwu2345', '0', '2012-02-10 23:50:55', null, null, '4');
INSERT INTO `service` VALUES ('2006', '1019', '192.168.0.20', 'luwsh', 'luwu2345', '0', '2012-02-10 00:00:00', null, null, '5');
INSERT INTO `service` VALUES ('2007', '1020', '192.168.0.20', 'weixb', 'wei12345', '0', '2012-02-10 11:05:20', null, null, '6');
INSERT INTO `service` VALUES ('2008', '1010', '192.168.0.20', 'guojing', 'guo09876', '0', '2012-02-11 12:05:21', null, null, '6');

-- 业务资费更新备份表
DROP TABLE IF EXISTS `service_update`;
CREATE TABLE `service_update` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `service_id` int(10) NOT NULL,
  `unix_host` varchar(15) NOT NULL,
  `os_username` varchar(8) NOT NULL,
  `cost_id` int(4) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp COMMENT '数据插入时间',
  PRIMARY KEY (`id`),
  KEY `fk_supdate_cost_id` (`cost_id`),
  KEY `fk_supdate_host_uhost` (`unix_host`),
  KEY `fk_supdate_service_id` (`service_id`),
  CONSTRAINT `fk_supdate_cost_id` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`cost_id`),
  CONSTRAINT `fk_supdate_service_id` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 模块表
DROP TABLE IF EXISTS `module_info`;
CREATE TABLE `module_info` (
  `module_id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

insert into `module_info` values('1','角色管理');
insert into `module_info` values('2','管理员');
insert into `module_info` values('3','资费管理');
insert into `module_info` values('4','账务账号');
insert into `module_info` values('5','业务账号');
insert into `module_info` values('6','账单管理');
insert into `module_info` values('7','报表');

-- 角色表
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `role_id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `role_info` VALUES ('1', '管理员');
INSERT INTO `role_info` VALUES ('2', '营业员');
INSERT INTO `role_info` VALUES ('3', '经理');
INSERT INTO `role_info` VALUES ('4', 'aaa');
INSERT INTO `role_info` VALUES ('5', 'bbb');
INSERT INTO `role_info` VALUES ('6', 'ccc');

-- 角色-模块表
DROP TABLE IF EXISTS `role_module`;
CREATE TABLE `role_module` (
  `role_id` int(4) NOT NULL,
  `module_id` int(4) NOT NULL,
  CONSTRAINT `fk_rm_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`),
  CONSTRAINT `fk_rm_module_id` FOREIGN KEY (`module_id`) REFERENCES `module_info` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_module` VALUES ('1', '1');
INSERT INTO `role_module` VALUES ('1', '2');
INSERT INTO `role_module` VALUES ('2', '3');
INSERT INTO `role_module` VALUES ('2', '4');
INSERT INTO `role_module` VALUES ('2', '5');
INSERT INTO `role_module` VALUES ('2', '6');
INSERT INTO `role_module` VALUES ('3', '7');

-- 管理员表
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `admin_id` int(8) NOT NULL AUTO_INCREMENT,
  `admin_code` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enrolldate` datetime DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_code` (`admin_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

INSERT INTO `admin_info` VALUES ('1001','admin','123','ADMIN','123456789','admin@qq.com.cn','2017-04-08 18:07:38',null);
INSERT INTO `admin_info` VALUES ('1002','zhangfei','123','ZhangFei','123456789','zhangfei@qq.com.cn','2017-04-08 18:07:38',null);
INSERT INTO `admin_info` VALUES ('1003','liubei','123','LiuBei','123456789','liubei@qq.com.cn','2017-04-08 18:07:38',null);
INSERT INTO `admin_info` VALUES ('1004','caocao','123','CaoCao','123456789','caocao@qq.com.cn','2017-04-08 18:07:38',null);
INSERT INTO `admin_info` VALUES ('1005','aaa','123','AAA','123456789','aaa@qq.com.cn',now(),null);
INSERT INTO `admin_info` VALUES ('1006','bbb','123','BBB','123456789','bbb@qq.com.cn',now(),null);

-- 管理员-角色表
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `admin_id` int(8) NOT NULL,
  `role_id` int(4) NOT NULL,
  CONSTRAINT `fk_ar_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`),
  CONSTRAINT `fk_ar_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin_info` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `admin_role` VALUES ('1001', '1');
INSERT INTO `admin_role` VALUES ('1002', '2');
INSERT INTO `admin_role` VALUES ('1003', '3');
INSERT INTO `admin_role` VALUES ('1004', '1');
INSERT INTO `admin_role` VALUES ('1004', '2');
INSERT INTO `admin_role` VALUES ('1004', '3');

commit;

show tables;