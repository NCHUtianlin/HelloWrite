# Host: localhost  (Version 5.6.24)
# Date: 2020-02-17 17:33:55
# Generator: MySQL-Front 6.0  (Build 1.137)


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `pwd` varchar(30) DEFAULT NULL COMMENT '密码',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'田林','15180249029','123123',0),(2,'黎明','18270893623','123123',0);

#
# Structure for table "content"
#

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `wdate` datetime DEFAULT NULL COMMENT '记录时间',
  `detail` varchar(255) DEFAULT NULL COMMENT '具体内容',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`Id`),
  KEY `userID` (`userId`),
  CONSTRAINT `userID` FOREIGN KEY (`userId`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='内容';

#
# Data for table "content"
#

INSERT INTO `content` VALUES (10,1,'2020-01-16 22:00:54','好心情的今天','好心情2222',0),(42,1,'2020-01-16 00:53:02','我的菜','不到',0),(45,1,'2020-01-17 21:56:17','好心情','我的',0);
