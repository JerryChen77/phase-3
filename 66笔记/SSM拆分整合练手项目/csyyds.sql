/*
SQLyog Ultimate v8.32 
MySQL - 5.5.40 : Database - csyyds
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`csyyds` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `csyyds`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` int(30) NOT NULL,
  `repertory` int(30) NOT NULL,
  `u_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`price`,`repertory`,`u_name`) values (1,'潮流沙滩裤',10,300,'路飞'),(2,'沙雕帽',17,100,'大眼'),(3,'你好骚啊衬衫',1350,20,'大眼'),(4,'鸡哥牌假发',2,3000,'大眼'),(5,'蛤蟆镜',30,120,'大眼'),(6,'连腿袜',20,300,'大眼'),(12,'绿帽子',6,40000,'大眼'),(13,'加绒卫衣',200,50,'大眼');

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `goodstype` */

insert  into `goodstype`(`id`,`name`) values (1,'短袖'),(3,'长裤'),(5,'大裤衩'),(6,'拖鞋'),(7,'长袖'),(14,'袜子'),(15,'眼镜'),(16,'花裤衩'),(19,'帽子'),(20,'卫衣'),(21,'假发');

/*Table structure for table `repertory_record` */

DROP TABLE IF EXISTS `repertory_record`;

CREATE TABLE `repertory_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `flag` varchar(10) NOT NULL,
  `nums` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `repertory_record` */

insert  into `repertory_record`(`id`,`u_name`,`time`,`flag`,`nums`,`name`) values (1,'大眼','2021-06-04 23:28:07','in',34,'绿帽子'),(2,'大眼','2021-06-05 14:37:03','in',36000,'鸡哥牌假发'),(3,'大眼','2021-06-05 14:39:54','in',3000,'鸡哥牌假发'),(4,'大眼','2021-06-05 14:45:51','in',3600,'鸡哥牌假发'),(5,'大眼','2021-06-05 14:48:13','out',600,'鸡哥牌假发');

/*Table structure for table `type_goods` */

DROP TABLE IF EXISTS `type_goods`;

CREATE TABLE `type_goods` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `gid` int(5) NOT NULL,
  `tid` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `type_goods` */

insert  into `type_goods`(`id`,`gid`,`tid`) values (1,1,5),(2,2,19),(3,3,1),(4,4,21),(5,10,0),(6,11,0),(7,12,19),(8,13,20);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `u_delete` varchar(10) NOT NULL DEFAULT '0',
  `type` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`u_delete`,`type`,`name`,`tel`,`address`) values (1,'HHH','666','1',0,'HXD','110','北京'),(14,'dayan','ddd','0',1,'大眼','110110','杭州市'),(15,'lufei','lll','0',2,'路飞','119','海贼王'),(16,'xiayule','xxx','0',2,'花裤衩','11211','云霄之上'),(17,'ccc','ccc','0',1,'长枪依在','123123','德玛西亚'),(18,'lufei','lll','0',0,'234','234','234'),(19,'lufei234','lll234','0',0,'234','234','234'),(20,'lufei234','lll','0',0,'234','234','234'),(21,'lufei235','lll235','0',0,'235','235','523'),(22,'lufeieg','llleg','0',0,'235','erg','erg'),(23,'lufeierg','lllerg','0',0,'erg','erg','erg'),(24,'qiaozhixuan','qqq','0',2,'乔志炫','110','食堂'),(25,'yuankang','yyy','0',1,'余安康','119','女寝地下室'),(26,'dayan2344','ddd444','0',1,'234','555','55555'),(27,'dayan2344','ddd444','0',0,'234','555','55555'),(28,'dayan3333','ddd333','0',0,'324234','3333','3333'),(29,'dayan3434','ddd3434','0',0,'33343','34343','3433434'),(30,'dayan234','ddd2342','0',0,'234','234234','234234'),(31,'dayan23523','ddd23525','0',0,'2342542','235235','253235');

/*Table structure for table `user_goods` */

DROP TABLE IF EXISTS `user_goods`;

CREATE TABLE `user_goods` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `uid` int(5) NOT NULL,
  `gid` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user_goods` */

insert  into `user_goods`(`id`,`uid`,`gid`) values (1,14,1),(2,16,2),(3,24,3),(4,25,4),(5,14,11),(6,14,12),(7,14,13);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
