/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.26 : Database - fcms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fcms` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fcms`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `permissions` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `admin` */

insert  into `admin`(`username`,`password`,`uuid`,`permissions`) values ('admin','123','7adb0f5555e547e88b06083321b398f6','0'),('zhangsan','123','ace3a2a6ebcd4d7884a61914a9ecd56d','1');

/*Table structure for table `assign` */

DROP TABLE IF EXISTS `assign`;

CREATE TABLE `assign` (
  `id` int NOT NULL AUTO_INCREMENT,
  `driver_username` varchar(10) NOT NULL,
  `driver_id_card` varchar(19) DEFAULT NULL,
  `car_name` varchar(30) NOT NULL,
  `car_license_late_number` varchar(15) DEFAULT NULL,
  `profit` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_id_card` (`driver_id_card`),
  UNIQUE KEY `car_license_late_number` (`car_license_late_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

/*Data for the table `assign` */

insert  into `assign`(`id`,`driver_username`,`driver_id_card`,`car_name`,`car_license_late_number`,`profit`) values (12,'聂成鹏','120456580987956123','奥迪','陕A1','20.40'),(19,'聂成鹏','123456789987456123','奥迪','陕A2','50.35'),(20,'焊线鹏','1234006789087456123','奥迪','陕A3','0.00');

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `car_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `car_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `license_late_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `car_describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `state` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未分配',
  `car_pic_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_late_number` (`license_late_number`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;

/*Data for the table `car` */

insert  into `car`(`id`,`car_name`,`car_type`,`license_late_number`,`car_describe`,`state`,`car_pic_path`) values (9,'奥迪','客车','陕A1','无','已分配','/static/upload/d106b4b1d7b040359ad80bd1d203b9c78.jpg'),(10,'奥迪','轿车','陕A2','无','已分配',''),(11,'奥迪','轿车','陕A3','无','已分配',''),(12,'奥迪','轿车','陕A4','无','未分配',''),(13,'奥迪','轿车','陕A5','无','未分配',''),(14,'奥迪','轿车','陕A6','无','未分配',''),(15,'奥迪','轿车','陕A7','无','未分配',''),(16,'奥迪','轿车','陕A8','无','未分配',''),(17,'奥迪','轿车','陕A9','无','未分配',''),(18,'奥迪','轿车','陕A10','无','未分配',''),(19,'奥迪','轿车','陕A11','无','未分配',''),(20,'奥迪','轿车','陕A12','无','未分配',''),(21,'奥迪','轿车','陕A13','无','未分配',''),(22,'奥迪','轿车','陕A14','无','未分配',''),(23,'奥迪','轿车','陕A15','无','未分配',''),(24,'奥迪','轿车','陕A16','无','未分配',''),(25,'奥迪','轿车','陕A17','无','未分配',''),(26,'奥迪','轿车','陕A18','无','未分配',''),(27,'奥迪','轿车','陕A19','无','未分配',''),(28,'奥迪','轿车','陕A20','无','未分配',''),(29,'奥迪','轿车','陕A21','无','未分配',''),(30,'奥迪','轿车','陕A22','无','未分配',''),(31,'奥迪','轿车','陕A23','无','未分配',''),(32,'奥迪','轿车','陕A24','无','未分配',''),(33,'奥迪','轿车','陕A25','无','未分配',''),(34,'奥迪','轿车','陕A26','无','未分配',''),(35,'奥迪','轿车','陕A27','无','未分配',''),(36,'奥迪','轿车','陕A28','无','未分配',''),(37,'奥迪','轿车','陕A29','无','未分配',''),(38,'奥迪','轿车','陕A30','无','未分配',''),(39,'奥迪','轿车','陕A31','无','未分配',''),(40,'奥迪','轿车','陕A32','无','未分配',''),(41,'奥迪','轿车','陕A33','无','未分配',''),(45,'奥迪','轿车','陕A37','无','未分配',''),(46,'奥迪','轿车','陕A38','无','未分配','/static/upload/1f5ee233429c424da2a5e8cba18907e38.jpg'),(57,'奔驰','轿车','陕A6666','无','未分配','/static/upload/47b27c3a9a8f4dd19ef22663067af5fd8.jpg');

/*Table structure for table `driver` */

DROP TABLE IF EXISTS `driver`;

CREATE TABLE `driver` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `age` int NOT NULL,
  `license_and_age` varchar(10) NOT NULL,
  `id_card` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `driver_pic_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb3;

/*Data for the table `driver` */

insert  into `driver`(`id`,`username`,`sex`,`age`,`license_and_age`,`id_card`,`driver_pic_path`) values (182,'聂成鹏','女',54,'C1，5年','123456789987456123','/static/upload/0e1d012516af4750b5f0c9be29c39449head.jpg'),(183,'聂成','女',15,'A1，5年','223456789987456123','/static/upload/0a7b05526299477b8e72245a7785a926head.jpg'),(184,'聂鹏','女',15,'A1，5年','323456789987456123','/static/upload/836df936ff2048d58926ea9438010a9bhead.jpg'),(185,'成鹏','女',15,'A1，5年','423456789987456123','/static/upload/5deca19d8667450fb443353df28d54b6head.jpg'),(186,'鹏聂','女',15,'A1，5年','523456789987456123','/static/upload/43c37e03f7324d1cb32914edad7cd238head.jpg'),(187,'鹏聂成','女',15,'A1，5年','623456789987456123','/static/upload/af7496c275bb45eaabbf1563e45d2cf3head.jpg'),(188,'张之洞','女',15,'A1，5年','723456789987456123','/static/upload/8b89c7b7225343588fcf314298693758head.jpg'),(189,'白五天','女',15,'A2，5年','823456789987456123','/static/upload/75cebd0b9b4c42f0adfd3da5afc13cc4head.jpg'),(190,'张飞天','女',15,'A3，5年','923456789987456123','/static/upload/0fca93e9bfe94fd3ba257b3f6dc7e138head.jpg'),(195,'华清天','女',15,'B2，5年','173456789987456123',''),(196,'遮蓬','女',15,'B2，5年','183456789987456123',''),(197,'那个鹏','女',15,'B2，5年','193456789987456123',''),(198,'那莪鹏','女',15,'B2，5年','124456789987456123',''),(199,'无语鹏','女',15,'B2，5年','123556789987456123',''),(200,'哈哈鹏','女',15,'B3，5年','126456789987456123',''),(201,'校址鹏','女',15,'B3，5年','127456789987456123',''),(202,'聂成鹏','女',15,'B3，5年','123645789987456123',''),(203,'聂成鹏','女',15,'B3，5年','123457789987456123',''),(204,'聂成鹏','女',15,'C1，5年','123459789987456123',''),(205,'聂成鹏','女',15,'C1，5年','123458789987456123',''),(206,'聂成鹏','女',15,'C1，5年','123451789987456123',''),(207,'聂成鹏','女',15,'C1，5年','123452789987456123',''),(208,'聂成鹏','女',15,'C1，5年','123453789987456123',''),(209,'聂成鹏','女',15,'C1，5年','123454789987456123',''),(210,'聂成鹏','女',15,'C1，5年','12345618087456123',''),(211,'聂成鹏','女',15,'C1，5年','123456289807456123',''),(212,'聂成鹏','女',15,'C1，5年','123456389987056123',''),(213,'聂成鹏','女',15,'C1，5年','123450489987056123',''),(214,'聂成鹏','女',15,'C1，5年','120456580987956123',''),(215,'聂成鹏','女',15,'C1，5年','123406701987656123',''),(216,'聂成鹏','女',15,'C1，5年','123406880987756123','/static/upload/46d1cab41ec147ed807fcd11b93d789bhead.jpg'),(217,'聂成鹏','女',15,'C1，5年','123406989907656123',''),(218,'嗯嗯鹏','女',15,'C1，5年','123456709987506123',''),(219,'焊线鹏','女',15,'C1，5年','1234006789087456123',''),(220,'吴迪','女',15,'C1，5年','123456089907456123',''),(221,'张无敌','女',15,'C1，5年','123456089987456123','');

/*Table structure for table `log_record` */

DROP TABLE IF EXISTS `log_record`;

CREATE TABLE `log_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `optIp` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb3;

/*Data for the table `log_record` */

insert  into `log_record`(`id`,`username`,`optIp`,`operation`,`content`,`create_time`) values (60,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 11:28:18'),(61,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了欧阳菲雅司机和奔驰车的分配','2022-02-23 11:52:29'),(69,'zhangsan','127.0.0.1','/driver/deleteCheckDriver','失败的删除了聂成鹏:123456789987456123,聂成:223456789987456123等个人信息','2022-02-23 13:49:48'),(70,'zhangsan','127.0.0.1','/driver/deleteOneDriver','失败的删除了聂成鹏:123456789987456123个人信息','2022-02-23 13:50:22'),(71,'zhangsan','127.0.0.1','/car/deleteCheckCar','删除了奥迪:陕A1等车信息失败了','2022-02-23 14:03:49'),(72,'zhangsan','127.0.0.1','/car/deleteCheckCar','删除了奥迪:陕A1,奥迪:陕A2等车信息失败了','2022-02-23 14:03:52'),(73,'zhangsan','127.0.0.1','/car/deleteOneCar','删除了奥迪:陕A1车信息失败了','2022-02-23 14:03:56'),(74,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 16:50:03'),(75,'zhangsan','127.0.0.1','/assign/saveAssign','添加了焊线鹏司机和奥迪车的分配','2022-02-23 16:52:34'),(76,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂鹏司机和奥迪车的分配','2022-02-23 16:53:28'),(77,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 16:54:13'),(78,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 16:56:17'),(79,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂鹏司机和奥迪车的分配','2022-02-23 16:56:19'),(80,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了焊线鹏司机和奥迪车的分配','2022-02-23 16:56:20'),(81,'zhangsan','127.0.0.1','/assign/saveAssign','添加了焊线鹏司机和奥迪车的分配','2022-02-23 16:56:48'),(82,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 17:02:34'),(83,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂鹏司机和奥迪车的分配','2022-02-23 17:04:42'),(84,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂鹏司机和奥迪车的分配','2022-02-23 17:08:24'),(85,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 17:08:26'),(86,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了焊线鹏司机和奥迪车的分配','2022-02-23 17:08:28'),(87,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 17:08:29'),(88,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成司机和奥迪车的分配','2022-02-23 17:08:31'),(89,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 17:08:33'),(90,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 17:19:00'),(91,'zhangsan','127.0.0.1','/assign/saveAssign','添加了焊线鹏司机和奥迪车的分配','2022-02-23 17:20:41'),(92,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 17:21:05'),(93,'zhangsan','127.0.0.1','/assign/saveAssign','添加了司机和车的分配','2022-02-23 17:22:43'),(94,'zhangsan','127.0.0.1','/assign/saveAssign','添加了嗯嗯鹏司机和奥迪车的分配','2022-02-23 17:37:27'),(95,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 17:38:07'),(96,'zhangsan','127.0.0.1','/assign/saveAssign','添加了吴迪司机和奥迪车的分配','2022-02-23 17:39:58'),(97,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了吴迪司机和奥迪车的分配','2022-02-23 17:40:16'),(98,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 17:40:18'),(99,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了嗯嗯鹏司机和奥迪车的分配','2022-02-23 17:40:20'),(100,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了聂成鹏司机和奥迪车的分配','2022-02-23 17:40:22'),(101,'zhangsan','127.0.0.1','/assign/deleteOneAssign','删除了焊线鹏司机和奥迪车的分配','2022-02-23 17:40:24'),(102,'zhangsan','127.0.0.1','/system/exit','退出了登录','2022-02-23 17:40:33'),(103,'admin','127.0.0.1','/system/login','登录了系统','2022-02-23 17:40:44'),(104,'admin','127.0.0.1','/system/exit','退出了登录','2022-02-23 17:41:45'),(105,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 17:41:54'),(106,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 18:18:42'),(107,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 18:19:15'),(108,'zhangsan','127.0.0.1','/driver/saveDriver','添加了冯阳:61011122222个人信息','2022-02-23 18:20:12'),(109,'zhangsan','127.0.0.1','/driver/saveDriver','添加了zhangsan:61011122222个人信息失败了','2022-02-23 18:20:39'),(110,'zhangsan','127.0.0.1','/driver/updateDriver','修改了冯阳:61011122222个人信息','2022-02-23 18:20:55'),(111,'zhangsan','127.0.0.1','/driver/deleteOneDriver','删除了冯阳:61011122222个人信息','2022-02-23 18:21:04'),(112,'zhangsan','127.0.0.1','/driver/deleteCheckDriver','删除了聂之鹏:123456780987456123,张三:610111255252,zhangsan:2576等个人信息','2022-02-23 18:21:12'),(113,'zhangsan','127.0.0.1','/car/saveCar','添加了保时捷:陕A6666车信息失败了','2022-02-23 18:21:56'),(114,'zhangsan','127.0.0.1','/car/saveCar','添加了保时捷:陕A89457车信息','2022-02-23 18:22:05'),(115,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 18:30:00'),(116,'zhangsan','127.0.0.1','/car/updateCar','修改了奥迪Q8:陕A89457车信息','2022-02-23 18:30:19'),(117,'zhangsan','127.0.0.1','/car/deleteCheckCar','删除了奥迪Q5:陕A555,奥迪Q8:陕A89457等车信息','2022-02-23 18:30:43'),(118,'zhangsan','127.0.0.1','/car/deleteOneCar','删除了奥迪Q5:陕A66678车信息','2022-02-23 18:30:50'),(119,'zhangsan','127.0.0.1','/car/deleteOneCar','删除了奥迪:陕A1车信息失败了','2022-02-23 18:31:34'),(120,'zhangsan','127.0.0.1','/system/exit','退出了登录','2022-02-23 18:32:26'),(121,'admin','127.0.0.1','/system/login','登录了系统','2022-02-23 18:32:36'),(122,'admin','127.0.0.1','/system/exit','退出了登录','2022-02-23 18:33:54'),(123,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 18:34:07'),(124,'zhangsan','127.0.0.1','/assign/saveAssign','添加了聂成鹏司机和奥迪车的分配','2022-02-23 18:34:21'),(125,'zhangsan','127.0.0.1','/assign/saveAssign','添加了焊线鹏司机和奥迪车的分配','2022-02-23 18:34:54'),(126,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 18:54:27'),(127,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-23 19:30:08'),(128,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 10:28:40'),(129,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:19:34'),(130,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:19:54'),(131,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:20:25'),(132,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:20:35'),(133,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:22:58'),(134,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-24 11:23:22'),(135,'zhangsan','127.0.0.1','/car/deleteCheckCar','删除了奥迪:陕A34,奥迪:陕A35,奥迪:陕A36等车信息','2022-02-24 11:30:30'),(136,'zhangsan','127.0.0.1','/system/login','登录了系统','2022-02-28 19:43:28'),(137,'zhangsan','127.0.0.1','/driver/updateDriver','修改了张飞天:923456789987456123个人信息','2022-02-28 19:43:54'),(138,'zhangsan','127.0.0.1','/system/exit','退出了登录','2022-02-28 19:44:18'),(139,'admin','127.0.0.1','/system/login','登录了系统','2022-02-28 19:44:33'),(140,'admin','127.0.0.1','/system/exit','退出了登录','2022-02-28 19:45:27');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
