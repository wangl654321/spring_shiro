/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.20 : Database - auth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`auth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `auth`;

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '组织名称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '上级部门',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `organization` */

insert  into `organization`(`id`,`name`,`address`,`code`,`icon`,`pid`,`seq`,`create_date`) values (1,'总经办','','01','icon-company',NULL,0,'2014-02-19 01:00:00'),(3,'技术部','','02','icon-company',NULL,1,'2015-10-01 13:10:42'),(5,'产品部','','03','icon-company',NULL,2,'2015-12-06 12:15:30'),(6,'测试组','','04','icon-folder',3,0,'2015-12-06 13:12:18'),(10,'开发部','北京','05','icon-folder',NULL,5,'2019-04-05 21:23:47');

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '资源主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT 'pid',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '上级资源',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类型',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`url`,`description`,`icon`,`pid`,`seq`,`status`,`resource_type`,`create_date`) values (1,'系统管理','','系统管理','icon-company',NULL,0,0,0,'2014-02-19 01:00:00'),(11,'资源管理','/resource/manager','资源管理','icon-folder',1,1,0,0,'2014-02-19 01:00:00'),(12,'角色管理','/role/manager','角色管理','icon-folder',1,2,0,0,'2014-02-19 01:00:00'),(13,'用户管理','/user/manager','用户管理','icon-folder',1,3,0,0,'2014-02-19 01:00:00'),(14,'部门管理','/organization/manager','部门管理','icon-folder',1,4,0,0,'2014-02-19 01:00:00'),(111,'列表','/resource/treeGrid','资源列表','icon-list',11,0,0,1,'2014-02-19 01:00:00'),(112,'添加','/resource/add','资源添加','icon-add',11,0,0,1,'2014-02-19 01:00:00'),(113,'编辑','/resource/edit','资源编辑','icon-edit',11,0,0,1,'2014-02-19 01:00:00'),(114,'删除','/resource/delete','资源删除','icon-del',11,0,0,1,'2014-02-19 01:00:00'),(121,'列表','/role/dataGrid','角色列表','icon-list',12,0,0,1,'2014-02-19 01:00:00'),(122,'添加','/role/add','角色添加','icon-add',12,0,0,1,'2014-02-19 01:00:00'),(123,'编辑','/role/edit','角色编辑','icon-edit',12,0,0,1,'2014-02-19 01:00:00'),(124,'删除','/role/delete','角色删除','icon-del',12,0,0,1,'2014-02-19 01:00:00'),(125,'授权','/role/grant','角色授权','icon-ok',12,0,0,1,'2014-02-19 01:00:00'),(131,'列表','/user/dataGrid','用户列表','icon-list',13,0,0,1,'2014-02-19 01:00:00'),(132,'添加','/user/add','用户添加','icon-add',13,0,0,1,'2014-02-19 01:00:00'),(133,'编辑','/user/edit','用户编辑','icon-edit',13,0,0,1,'2014-02-19 01:00:00'),(134,'删除','/user/delete','用户删除','icon-del',13,0,0,1,'2014-02-19 01:00:00'),(141,'列表','/organization/treeGrid','用户列表','icon-list',14,0,0,1,'2014-02-19 01:00:00'),(142,'添加','/organization/add','部门添加','icon-add',14,0,0,1,'2014-02-19 01:00:00'),(143,'编辑','/organization/edit','部门编辑','icon-edit',14,0,0,1,'2014-02-19 01:00:00'),(144,'删除','/organization/delete','部门删除','icon-del',14,0,0,1,'2014-02-19 01:00:00'),(221,'日志管理','/sysLog/manager',NULL,'icon-company',NULL,2,0,0,'2015-12-01 11:44:20'),(226,'支付查询','',NULL,'icon-company',NULL,1,0,0,'2019-04-04 17:43:51'),(227,'余额支付','/balancePay2',NULL,'',226,1,0,0,'2019-04-04 17:47:46'),(228,'银行卡支付','/bankCardPay',NULL,'',226,1,0,0,'2019-04-05 21:01:39');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '顺序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`seq`,`description`,`status`) values (1,'超级管理员',0,'超级管理员',0),(2,'技术部经理',0,'技术部经理',0),(7,'产品部经理',0,'产品部经理',0),(8,'测试账户',1,'测试',0),(10,'开发小二',3,'开发小二',0);

/*Table structure for table `role_resource` */

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=377 DEFAULT CHARSET=utf8 COMMENT='角色资源';

/*Data for the table `role_resource` */

insert  into `role_resource`(`id`,`role_id`,`resource_id`) values (158,3,1),(159,3,11),(160,3,111),(161,3,112),(162,3,113),(163,3,114),(164,3,12),(165,3,121),(166,3,122),(167,3,123),(168,3,124),(169,3,125),(170,3,13),(171,3,131),(172,3,132),(173,3,133),(174,3,134),(175,3,14),(176,3,141),(177,3,142),(178,3,143),(179,3,144),(263,7,1),(264,7,14),(265,7,141),(266,7,142),(267,7,143),(268,7,222),(269,7,223),(270,7,224),(271,7,221),(285,1,1),(286,1,11),(287,1,111),(288,1,112),(289,1,113),(290,1,114),(291,1,12),(292,1,121),(293,1,122),(294,1,123),(295,1,124),(296,1,125),(297,1,13),(298,1,131),(299,1,132),(300,1,133),(301,1,134),(302,1,14),(303,1,141),(304,1,142),(305,1,143),(306,1,144),(307,1,222),(308,1,223),(309,1,224),(310,1,221),(311,2,1),(312,2,13),(313,2,131),(314,2,132),(315,2,133),(316,2,134),(317,2,221),(339,8,1),(340,8,11),(341,8,111),(342,8,112),(343,8,113),(344,8,114),(345,8,12),(346,8,121),(347,8,13),(348,8,131),(349,8,14),(350,8,141),(351,10,1),(352,10,11),(353,10,111),(354,10,112),(355,10,113),(356,10,114),(357,10,12),(358,10,121),(359,10,122),(360,10,123),(361,10,124),(362,10,125),(363,10,13),(364,10,131),(365,10,132),(366,10,133),(367,10,134),(368,10,14),(369,10,141),(370,10,142),(371,10,143),(372,10,144),(373,10,226),(374,10,227),(375,10,228),(376,10,221);

/*Table structure for table `syslog` */

DROP TABLE IF EXISTS `syslog`;

CREATE TABLE `syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登陆用户名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色用户名',
  `opt_content` varchar(1024) DEFAULT NULL COMMENT '操作方法及属性',
  `client_ip` varchar(255) DEFAULT NULL COMMENT '操作者ip',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `syslog` */

insert  into `syslog`(`id`,`login_name`,`role_name`,`opt_content`,`client_ip`,`create_time`) values (318,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-04 17:01:43'),(319,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-04 17:40:54'),(320,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=8&_=1554370912281&','127.0.0.1','2019-04-04 17:42:17'),(321,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grant,[参数]:id=8&resourceIds=1,11,111,12,121,13,131,14,141&','127.0.0.1','2019-04-04 17:42:23'),(322,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-04 17:42:27'),(323,'test','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-04 17:42:55'),(324,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:addPage,[参数]:',NULL,'2019-04-04 17:43:22'),(325,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=1&_=1554371039686&','127.0.0.1','2019-04-04 17:44:07'),(326,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=226&_=1554371039687&','127.0.0.1','2019-04-04 17:44:17'),(327,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=226&_=1554371039688&','127.0.0.1','2019-04-04 17:44:26'),(328,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:addPage,[参数]:',NULL,'2019-04-04 17:47:17'),(329,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=11&_=1554371077590&','127.0.0.1','2019-04-04 17:47:52'),(330,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=1&_=1554371077591&','127.0.0.1','2019-04-04 17:48:27'),(331,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=13&_=1554371077592&','127.0.0.1','2019-04-04 17:48:31'),(332,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=1&_=1554371077593&','127.0.0.1','2019-04-04 17:48:39'),(333,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-04 17:48:59'),(334,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-04 17:49:25'),(335,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:00:51'),(336,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=1&_=1554465640926&','127.0.0.1','2019-04-05 20:01:31'),(337,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=6&_=1554465640927&','127.0.0.1','2019-04-05 20:01:42'),(338,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=3&_=1554465640928&','127.0.0.1','2019-04-05 20:01:49'),(339,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:02:41'),(340,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:03:18'),(341,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=8&_=1554466930962&','127.0.0.1','2019-04-05 20:22:35'),(342,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554466930963&','127.0.0.1','2019-04-05 20:22:37'),(343,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:22:50'),(344,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:23:09'),(345,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:25:22'),(346,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:25:27'),(347,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=16&_=1554467120682&','127.0.0.1','2019-04-05 20:26:08'),(348,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 20:26:16'),(349,'123456','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 20:26:26'),(350,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=1&_=1554467188094&','127.0.0.1','2019-04-05 20:27:25'),(351,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:delete,[参数]:id=16&','127.0.0.1','2019-04-05 20:27:32'),(352,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=1&_=1554467188095&','127.0.0.1','2019-04-05 20:27:38'),(353,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:29:05'),(354,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=15&_=1554467390746&','127.0.0.1','2019-04-05 20:29:55'),(355,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:43:25'),(356,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=7&_=1554468200471&','127.0.0.1','2019-04-05 20:43:51'),(357,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=7&_=1554468200472&','127.0.0.1','2019-04-05 20:44:00'),(358,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=5&_=1554468200473&','127.0.0.1','2019-04-05 20:44:06'),(359,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:delete,[参数]:id=7&','127.0.0.1','2019-04-05 20:44:10'),(360,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 20:44:11'),(361,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=6&_=1554468200475&','127.0.0.1','2019-04-05 20:44:48'),(362,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=8&_=1554468200476&','127.0.0.1','2019-04-05 20:46:28'),(363,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=227&_=1554468935646&','127.0.0.1','2019-04-05 20:55:44'),(364,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=227&_=1554468935647&','127.0.0.1','2019-04-05 20:56:06'),(365,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=227&_=1554468935648&','127.0.0.1','2019-04-05 20:56:51'),(366,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=227&_=1554469244041&','127.0.0.1','2019-04-05 21:00:47'),(367,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=227&_=1554469244042&','127.0.0.1','2019-04-05 21:01:00'),(368,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:01:08'),(369,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=228&_=1554469244044&','127.0.0.1','2019-04-05 21:01:42'),(370,'admin','admin','[类名]:com.manage.controller.ResourceController,[方法]:editPage,[参数]:id=228&_=1554469244045&','127.0.0.1','2019-04-05 21:01:53'),(371,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469244046&','127.0.0.1','2019-04-05 21:02:06'),(372,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469244047&','127.0.0.1','2019-04-05 21:02:11'),(373,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=8&_=1554469244048&','127.0.0.1','2019-04-05 21:02:17'),(374,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grant,[参数]:id=8&resourceIds=1,11,111,112,113,114,12,121,13,131,14,141&','127.0.0.1','2019-04-05 21:02:25'),(375,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=8&_=1554469244049&','127.0.0.1','2019-04-05 21:02:27'),(376,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grant,[参数]:id=8&resourceIds=1,11,111,112,113,114,12,121,13,131,14,141&','127.0.0.1','2019-04-05 21:02:29'),(377,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:02:31'),(378,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=9&_=1554469244051&','127.0.0.1','2019-04-05 21:02:38'),(379,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:delete,[参数]:id=9&','127.0.0.1','2019-04-05 21:02:41'),(380,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469244052&','127.0.0.1','2019-04-05 21:02:43'),(381,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 21:02:48'),(382,'test','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 21:02:55'),(383,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377947&','127.0.0.1','2019-04-05 21:03:02'),(384,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377948&','127.0.0.1','2019-04-05 21:03:06'),(385,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377949&','127.0.0.1','2019-04-05 21:03:13'),(386,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377950&','127.0.0.1','2019-04-05 21:03:20'),(387,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377951&','127.0.0.1','2019-04-05 21:04:15'),(388,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377952&','127.0.0.1','2019-04-05 21:04:34'),(389,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=8&_=1554469377953&','127.0.0.1','2019-04-05 21:04:44'),(390,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:06:03'),(391,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=15&_=1554469377955&','127.0.0.1','2019-04-05 21:06:23'),(392,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 21:09:01'),(393,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=15&_=1554469377956&','127.0.0.1','2019-04-05 21:09:06'),(394,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=15&_=1554469377957&','127.0.0.1','2019-04-05 21:20:54'),(395,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=15&_=1554469377958&','127.0.0.1','2019-04-05 21:21:04'),(396,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:21:26'),(397,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:editPage,[参数]:id=17&_=1554469377960&','127.0.0.1','2019-04-05 21:21:42'),(398,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=8&_=1554469377961&','127.0.0.1','2019-04-05 21:22:15'),(399,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=8&_=1554469377962&','127.0.0.1','2019-04-05 21:22:26'),(400,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=8&_=1554469377963&','127.0.0.1','2019-04-05 21:22:43'),(401,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:editPage,[参数]:id=8&_=1554469377964&','127.0.0.1','2019-04-05 21:22:54'),(402,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:delete,[参数]:id=8&','127.0.0.1','2019-04-05 21:23:01'),(403,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:23:05'),(404,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:delete,[参数]:id=9&','127.0.0.1','2019-04-05 21:23:13'),(405,'admin','admin','[类名]:com.manage.controller.OrganizationController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:23:23'),(406,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:delete,[参数]:id=17&','127.0.0.1','2019-04-05 21:24:01'),(407,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:24:10'),(408,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=10&_=1554469377968&','127.0.0.1','2019-04-05 21:24:29'),(409,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grant,[参数]:id=10&resourceIds=1,11,111,112,113,114,12,121,122,123,124,125,13,131,132,133,134,14,141,142,143,144,226,227,228,221&','127.0.0.1','2019-04-05 21:24:44'),(410,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:grantPage,[参数]:id=10&_=1554469377969&','127.0.0.1','2019-04-05 21:24:46'),(411,'admin','admin','[类名]:com.manage.controller.RoleController,[方法]:editPage,[参数]:id=10&_=1554469377970&','127.0.0.1','2019-04-05 21:24:49'),(412,'admin','admin','[类名]:com.manage.controller.UserController,[方法]:addPage,[参数]:',NULL,'2019-04-05 21:24:53'),(413,'admin','admin','[类名]:com.manage.controller.LoginController,[方法]:logout,[参数]:null','127.0.0.1','2019-04-05 21:25:59'),(414,'ngp','admin','[类名]:com.manage.controller.UserController,[方法]:editPwdPage,[参数]:',NULL,'2019-04-05 21:26:22'),(415,'ngp','admin','[类名]:com.manage.controller.UserController,[方法]:editPwdPage,[参数]:',NULL,'2019-04-05 21:26:28');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `login_name` varchar(64) NOT NULL COMMENT '登陆用户名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `organization_id` int(11) NOT NULL DEFAULT '0' COMMENT '组织id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `user` */

insert  into `user`(`id`,`login_name`,`name`,`password`,`sex`,`age`,`user_type`,`status`,`organization_id`,`create_date`,`phone`) values (1,'admin','admin','098f6bcd4621d373cade4e832627b4f6',0,25,0,0,1,'2015-12-06 13:14:05','18707173376'),(13,'snoopy','snoopy','d41d8cd98f00b204e9800998ecf8427e',0,25,1,0,3,'2015-10-01 13:12:07','18707173376'),(14,'dreamlu','dreamlu','098f6bcd4621d373cade4e832627b4f6',0,25,1,0,5,'2015-10-11 23:12:58','18707173376'),(15,'test','test','d41d8cd98f00b204e9800998ecf8427e',0,25,1,0,6,'2015-12-06 13:13:03','18707173376'),(18,'ngp','ngp','cec79bb1e2d940d68810d378bd13d8d4',0,26,1,0,10,'2019-04-05 21:25:42','13588888888');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(19) NOT NULL COMMENT '用户主键',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (54,1,1),(55,1,2),(56,1,7),(58,14,7),(60,13,2),(63,15,8),(68,18,10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
