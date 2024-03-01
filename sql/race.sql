/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.12 : Database - race
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`race` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `race`;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '反馈发送者id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '反馈者姓名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '反馈者性别',
  `age` int(3) DEFAULT NULL COMMENT '反馈者年龄',
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `unit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系单位',
  `content` text COLLATE utf8_unicode_ci COMMENT '反馈内容',
  `type` tinyint(3) DEFAULT NULL COMMENT '反馈类型,1为反馈意见,2为报错提交.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户反馈表';

/*Data for the table `feedback` */

insert  into `feedback`(`id`,`user_id`,`name`,`gender`,`age`,`phone`,`unit`,`content`,`type`) values (1,'string','string','s',0,'string','string','string',0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '物理id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`role_id`) values (1,'超级管理员','admin'),(2,'管理员','manager'),(3,'普通用户','common');

/*Table structure for table `train_record` */

DROP TABLE IF EXISTS `train_record`;

CREATE TABLE `train_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `train_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '训练记录id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '训练者姓名',
  `gender` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '训练者性别',
  `age` int(3) DEFAULT NULL COMMENT '训练者年龄',
  `height` double(4,2) DEFAULT NULL COMMENT '训练者身高',
  `weight` double(4,2) DEFAULT NULL COMMENT '训练者体重',
  `time` datetime DEFAULT NULL COMMENT '训练时间',
  `vo2max` double(8,2) DEFAULT NULL COMMENT '预测最大摄氧量',
  `trainer_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '训练者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='训练记录表';

/*Data for the table `train_record` */

/*Table structure for table `unit` */

DROP TABLE IF EXISTS `unit`;

CREATE TABLE `unit` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `unit_id` varchar(255) DEFAULT NULL COMMENT '单位id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unit_id` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='单位表';

/*Data for the table `unit` */

insert  into `unit`(`id`,`unit_id`,`name`) values (1,'hqu','华侨大学');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `unit_id` varchar(255) DEFAULT NULL COMMENT '所属单位id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '加密盐值',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `weight` double(4,2) DEFAULT NULL COMMENT '体重(kg)',
  `height` double(4,2) DEFAULT NULL COMMENT '身高(m)',
  `is_athlete` tinyint(1) DEFAULT NULL COMMENT '是否是运动员',
  `state` int(2) DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`user_id`,`role_id`,`unit_id`,`username`,`password`,`salt`,`phone`,`name`,`nickname`,`age`,`gender`,`weight`,`height`,`is_athlete`,`state`) values (1,'1','admin','hqu','111','111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'1','admin','hqu','111','111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'2','admin','hqu','444','444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
