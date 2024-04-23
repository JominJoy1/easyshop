/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - easyshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`easyshop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `easyshop`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`) values 
(1,'chocolates'),
(3,'snacks');

/*Table structure for table `complaints` */

DROP TABLE IF EXISTS `complaints`;

CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(300) DEFAULT NULL,
  `reply` varchar(300) DEFAULT NULL,
  `date_time` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `complaints` */

insert  into `complaints`(`complaint_id`,`user_id`,`complaint`,`reply`,`date_time`) values 
(1,1,'hhhhh','ok','2023-11-02'),
(2,1,'hhhhh','ok','2023-11-02'),
(3,1,'hhhhh','pending','2023-11-02'),
(4,1,'hhhhh','pending','2023-11-02'),
(5,1,'hhhhh','pending','2023-11-02'),
(6,1,'hhhh','pending','2023-11-13'),
(7,1,'hiii','pending','2023-11-13'),
(8,1,'hhh','pending','2024-03-20');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usertype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'ghjk','dvbhjk','shop'),
(2,'fghjkl;','dfghj','shop'),
(3,'ertghjkl','fghjkl','shop'),
(4,'dfgbnm,.','dfghjk','shop'),
(5,'ssss','ssss','shop'),
(9,'uu','uu','user'),
(8,'admin','admin','admin'),
(10,'ggg','ggg','user'),
(11,'uuu','dddd','shop'),
(12,'hhh','hhh','shop'),
(13,'hhh','hhhh','shop'),
(17,'vjay','vjay','user'),
(16,'push','push','pending');

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `notification_id` int(20) NOT NULL AUTO_INCREMENT,
  `stock_id` int(20) DEFAULT NULL,
  `notification` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`notification_id`,`stock_id`,`notification`,`date`,`status`) values 
(1,8,'pending','2024-01-04','stocks available now'),
(2,8,'pending','2024-01-04','stocks available now'),
(3,8,'pending','2024-01-05','stocks available now'),
(4,7,'pending','2024-01-05','stocks available now'),
(5,8,'pending','2024-01-05','stocks available now'),
(6,8,'pending','2024-01-05','stocks available now'),
(7,8,'pending','2024-01-05','stocks available now'),
(8,8,'pending','2024-01-05','stocks available now'),
(9,8,'pending','2024-01-05','stocks available now'),
(10,7,'pending','2024-01-05','stocks available now'),
(11,8,'pending','2024-01-05','stocks available now'),
(12,7,'pending','2024-01-05','stocks available now'),
(13,8,'pending','2024-01-05','stocks available now'),
(14,8,'pending','2024-04-09','out of stock'),
(15,6,'pending','2024-04-09','out of stock'),
(16,6,'pending','2024-04-09','out of stock'),
(17,7,'pending','2024-04-09','out of stock'),
(18,6,'pending','2024-04-09','out of stock');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) DEFAULT NULL,
  `offer_price` varchar(100) DEFAULT NULL,
  `start_date` varchar(50) DEFAULT NULL,
  `end_date` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer_price`,`start_date`,`end_date`,`status`) values 
(5,7,'20','2024-01-01','2024-05-08','offerprice');

/*Table structure for table `order_details` */

DROP TABLE IF EXISTS `order_details`;

CREATE TABLE `order_details` (
  `orderdetails_id` int(11) NOT NULL AUTO_INCREMENT,
  `ordermaster_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `amount` varchar(300) DEFAULT NULL,
  `orderstatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderdetails_id`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

/*Data for the table `order_details` */

insert  into `order_details`(`orderdetails_id`,`ordermaster_id`,`product_id`,`quantity`,`amount`,`orderstatus`) values 
(1,1,8,'2','200','pending'),
(2,1,9,'2','240','pending'),
(3,1,5,'2','30','pending'),
(4,2,9,'2','240','pending'),
(5,2,8,'3','300','pending'),
(6,3,8,'2','200','pending'),
(7,4,9,'5','600','pending'),
(8,5,9,'2','240','pending'),
(9,5,8,'5','500','pending'),
(10,5,7,'3','150','pending'),
(11,6,9,'6','720','pending'),
(12,6,8,'1','100','pending'),
(13,7,2,'5','50','pending'),
(14,8,8,'5','500','pending'),
(15,9,9,'6','720','pending'),
(16,10,2,'10','100','pending'),
(17,11,7,'7','350','pending'),
(18,12,2,'20','200','pending'),
(19,13,7,'12','600','pending'),
(20,14,9,'21','2520','pending'),
(21,14,6,'5','135','pending'),
(22,15,2,'2','20','pending'),
(23,16,2,'5','50','pending'),
(24,16,8,'4','400','pending'),
(25,17,7,'10','500','pending'),
(26,18,7,'12','600','pending'),
(27,19,9,'10','1200','pending'),
(28,20,8,'10','1000','pending'),
(29,21,2,'9','90','pending'),
(30,22,8,'5','500','pending'),
(31,23,7,'5','5000','pending'),
(32,24,9,'2','240','pending'),
(33,25,5,'3','45','pending'),
(34,26,7,'5','5000','pending'),
(35,27,7,'2','2000','pending'),
(36,28,7,'6','6000','pending'),
(37,29,7,'122','122000','pending'),
(38,29,7,'2','2000','pending'),
(39,30,9,'3','360','pending'),
(40,30,9,'2','240','pending'),
(41,31,9,'1','120','pending'),
(42,32,9,'2','240','pending'),
(43,33,9,'2','240','pending'),
(44,33,9,'2','240','pending'),
(45,34,9,'2','240','pending'),
(46,35,8,'2','200','pending'),
(47,36,8,'8','800','pending'),
(48,37,8,'2','200','pending'),
(53,38,8,'2','200','pending'),
(52,38,8,'2','200','pending'),
(51,38,8,'2','200','pending'),
(54,39,8,'2','200','pending'),
(55,40,7,'2','100','pending'),
(56,41,7,'5','250','pending'),
(57,42,9,'5','600','pending'),
(58,42,8,'2','200','pending'),
(59,43,9,'2','240','pending'),
(60,43,8,'1','100','pending'),
(61,44,9,'1','120','pending'),
(62,45,8,'2','200','pending'),
(63,46,8,'1','100','pending'),
(64,47,7,'1','50','pending'),
(65,48,7,'1','50','pending'),
(66,48,7,'1','50','pending'),
(67,49,8,'1','100','pending'),
(68,49,8,'1','100','pending'),
(69,50,7,'5','100','pending'),
(70,50,7,'2','40','pending'),
(71,50,7,'5','100','pending'),
(72,50,7,'5','100','pending'),
(73,51,7,'5','100','pending');

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `ordermaster_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `date_time` varchar(200) DEFAULT NULL,
  `total_amount` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  KEY `ordermaster` (`ordermaster_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

/*Data for the table `order_master` */

insert  into `order_master`(`ordermaster_id`,`user_id`,`shop_id`,`date_time`,`total_amount`,`status`) values 
(1,1,4,'2023-11-03','5625','paymentcompleted'),
(2,1,4,'2023-11-03','5395','paymentcompleted'),
(3,1,4,'2023-11-03','5055','paymentcompleted'),
(4,1,4,'2023-11-03','5455','paymentcompleted'),
(5,1,4,'2023-11-03','5095','paymentcompleted'),
(6,1,4,'2023-11-03','4925','paymentcompleted'),
(7,1,4,'2023-11-04','4155','paymentcompleted'),
(8,1,4,'2023-11-04','4605','paymentcompleted'),
(9,1,4,'2023-11-04','4825','paymentcompleted'),
(10,1,4,'2023-11-04','4205','paymentcompleted'),
(11,1,4,'2023-11-04','4455','paymentcompleted'),
(12,1,4,'2023-11-04','4305','paymentcompleted'),
(13,1,4,'2023-11-04','4705','paymentcompleted'),
(14,1,4,'2023-11-04','6625','paymentcompleted'),
(15,1,4,'2023-11-04','3990','paymentcompleted'),
(16,1,4,'2023-11-04','4020','paymentcompleted'),
(17,1,4,'2023-11-06','4070','paymentcompleted'),
(18,1,4,'2023-11-06','4170','paymentcompleted'),
(19,1,4,'2023-11-06','4770','paymentcompleted'),
(20,1,4,'2023-11-07','4570','paymentcompleted'),
(21,1,4,'2023-11-07','3660','paymentcompleted'),
(22,1,4,'2023-11-07','4070','paymentcompleted'),
(23,1,4,'2023-11-07','8570','paymentcompleted'),
(24,1,4,'2023-11-07','3810','paymentcompleted'),
(25,1,4,'2023-11-08','3615','paymentcompleted'),
(26,1,4,'2023-11-09','8570','paymentcompleted'),
(27,1,4,'2023-11-11','5570','paymentcompleted'),
(28,1,4,'2023-11-11','9570','paymentcompleted'),
(29,1,4,'2023-11-13','125570','paymentcompleted'),
(30,1,4,'2023-11-13','1930','paymentcompleted'),
(31,1,4,'2024-01-04','1450','paymentcompleted'),
(32,1,4,'2024-01-04','1570','paymentcompleted'),
(33,1,4,'2024-01-04','1570','paymentcompleted'),
(34,1,4,'2024-01-04','1330','paymentcompleted'),
(35,1,4,'2024-01-05','1290','paymentcompleted'),
(36,1,4,'2024-01-05','1890','paymentcompleted'),
(37,1,4,'2024-01-05','1290','paymentcompleted'),
(38,1,4,'2024-01-05','1290','paymentcompleted'),
(39,1,4,'2024-01-05','890','paymentcompleted'),
(40,1,4,'2024-01-05','790','paymentcompleted'),
(41,1,4,'2024-03-20','940','paymentcompleted'),
(42,1,4,'2024-04-09','1290','paymentcompleted'),
(43,1,4,'2024-04-09','730','paymentcompleted'),
(44,1,4,'2024-04-09','510','paymentcompleted'),
(45,1,4,'2024-04-09','590','paymentcompleted'),
(46,1,4,'2024-04-09','490','paymentcompleted'),
(47,1,4,'2024-04-09','440','paymentcompleted'),
(48,1,4,'2024-04-09','440','paymentcompleted'),
(49,1,4,'2024-04-09','440','paymentcompleted'),
(50,1,4,'2024-04-21','340','paymentcompleted'),
(51,1,4,'2024-04-23','100','paymentcompleted');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(20) NOT NULL AUTO_INCREMENT,
  `shop_id` int(20) DEFAULT NULL,
  `ordermaster_id` int(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `amount` int(20) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`shop_id`,`ordermaster_id`,`user_id`,`amount`) values 
(1,8,1,1,54),
(2,8,1,1,54),
(3,8,2,1,100),
(4,8,3,1,100),
(5,8,1,1,200),
(6,8,1,1,200),
(7,8,1,1,470),
(8,8,2,1,540),
(9,8,2,1,540),
(10,8,3,1,200),
(11,8,3,1,200),
(12,8,3,1,200),
(13,8,4,1,600),
(14,8,5,1,890),
(15,8,6,1,820),
(16,8,6,1,820),
(17,8,7,1,50),
(18,8,8,1,500),
(19,8,9,1,720),
(20,8,10,1,100),
(21,8,11,1,350),
(22,8,12,1,200),
(23,8,13,1,600),
(24,8,14,1,2655),
(25,8,15,1,20),
(26,8,16,1,450),
(27,8,16,1,450),
(28,8,17,1,500),
(29,8,18,1,600),
(30,8,19,1,1200),
(31,8,20,1,1000),
(32,8,21,1,90),
(33,8,22,1,500),
(34,8,23,1,5000),
(35,8,24,1,240),
(36,8,25,1,45),
(37,8,26,1,5000),
(38,8,27,1,2000),
(39,8,28,1,6000),
(40,8,29,1,124000),
(41,8,30,1,600),
(42,10,31,1,120),
(43,8,32,1,240),
(44,8,33,1,480),
(45,8,33,1,480),
(46,8,34,1,240),
(47,8,35,1,200),
(48,8,36,1,800),
(49,8,37,1,200),
(50,8,38,1,600),
(51,8,39,1,200),
(52,8,40,1,100),
(53,10,41,1,250),
(54,4,48,1,100),
(55,4,49,1,200),
(56,11,50,1,340),
(57,11,51,1,100);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `price` varchar(100) DEFAULT NULL,
  `product_placed` varchar(100) DEFAULT NULL,
  `qr_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `products` */

insert  into `products`(`product_id`,`category_id`,`shop_id`,`product_name`,`details`,`image`,`price`,`product_placed`,`qr_code`) values 
(3,1,4,'aaa','aa','static/product_images/fc341c02-e308-499d-981b-49bb67ebfd50','20','qq','static/product_qr/dddaaa.png'),
(4,1,4,'hh','hh','static/product_images/418ee005-ed80-4760-8be9-3c2673293997','13','hh','static/product_qr/dddhh.png'),
(5,1,4,'rrr','rrr','static/product_images/53ff27a5-c55d-42bd-b698-56bffe86b7cb','15','rrr','static/product_qr/dddrrr.png'),
(6,1,4,'aaa','aa','static/product_images/fa309898-bebd-49ae-a5d7-b83f46281c52','27','aaa','static/product_qr/dddaaa.png'),
(7,1,4,'dairymilk','dairymilk','static/product_images/8a2b9dcd-4c4f-4857-9ac3-8ab9a0cf5adedairymilk.jpeg','50','hhhh','static/product_qr/ddddairymilk.png'),
(8,1,4,'kitkat','kitkat','static/product_images/f6afa961-6f11-4719-935c-7a9f774a1644kitkat.jpeg','100','right side top left','static/product_qr/dddkitkat.png'),
(9,1,4,'munch','munch','static/product_images/39ccc5dc-73af-46d0-9181-e0f63c9ca72emunch.jpeg','120','right side top left','static/product_qr/dddmunch.png'),
(10,1,4,'aaa','aaa','static/product_images/2f6bccf3-bc71-4343-8983-4a2bdffc9b3e','aaa','aaa','static/product_qr/dddaaa.png');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  `rating` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`product_id`,`rating`) values 
(1,1,8,'3.0'),
(2,1,9,'3.0'),
(3,1,7,'1.5'),
(4,1,7,'4.0'),
(5,1,8,'4.0'),
(6,1,8,'4.5'),
(7,1,8,'4.0'),
(8,1,8,'5.0'),
(9,1,9,'3.5');

/*Table structure for table `shops` */

DROP TABLE IF EXISTS `shops`;

CREATE TABLE `shops` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `shop_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `landmark` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `shops` */

insert  into `shops`(`shop_id`,`login_id`,`shop_name`,`place`,`landmark`,`phone`,`email`,`status`,`latitude`,`longitude`) values 
(1,2,'cvbnm,','sdfghj','dfgbnm','cvbnm,','cvbnm@gmail.com','pending',NULL,NULL),
(2,3,'fghjk','dfgh','dfgh','rfgbn','dcvbn@gmail.com','pending',NULL,NULL),
(3,4,'asdfgzxcv','sxcvf','asder','09876543201','amaljames@gmail.com','pending',NULL,NULL),
(4,5,'ddd','ddd','ddd','ddd','dddd@gmail.com','pending','10.5155821','76.2180734'),
(7,11,'fff','hhh','hhh','ttt','yyyy','pending',NULL,NULL),
(8,12,'amal','ffff','fff','fff','yyyy','pending',NULL,NULL),
(9,13,'hhh','hhh','hhh','hhh','hhhh','10.206229118423629','76.32137597006148','pending'),
(12,16,'pushkaranz shop','velur','velur','01234567890','amaljames@gmail.com','pending','9.984082866002623','76.28950495505951');

/*Table structure for table `stocks` */

DROP TABLE IF EXISTS `stocks`;

CREATE TABLE `stocks` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `stock_quantity` varchar(100) DEFAULT NULL,
  `date_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `stocks` */

insert  into `stocks`(`stock_id`,`product_id`,`stock_quantity`,`date_time`) values 
(1,2,'10','2023-10-31'),
(2,2,'10','2023-10-31'),
(3,2,'10','2023-10-31'),
(4,5,'2','2023-10-31'),
(5,6,'15','2023-11-01'),
(6,7,'5','2023-11-03'),
(7,8,'10','2023-11-03'),
(8,9,'10','2023-11-03'),
(9,10,'2','2023-11-07'),
(10,11,'5','2024-01-04');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(150) DEFAULT NULL,
  `lastname` varchar(150) DEFAULT NULL,
  `housename` varchar(150) DEFAULT NULL,
  `place` varchar(150) DEFAULT NULL,
  `landmark` varchar(150) DEFAULT NULL,
  `pincode` varchar(150) DEFAULT NULL,
  `phone` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`login_id`,`firstname`,`lastname`,`housename`,`place`,`landmark`,`pincode`,`phone`,`email`) values 
(1,9,'yyy','jjj','yyy','gggf','dddd','dsss','aaaa','uuuu'),
(2,10,'ggg','jhhh','fft','ftt','fff','fff','fff','cfg'),
(3,17,'vijay','ks','vijayanz','palakkad','town','650600','9087654311','vj@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
