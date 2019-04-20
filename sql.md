#登录表
CREATE TABLE `login` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `sign` int(1) unsigned zerofill DEFAULT '0',
  `tel` varchar(11) DEFAULT NULL,
  `vc` varchar(6) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
#验证码表
CREATE TABLE `vcResources` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `tel` varchar(11) DEFAULT NULL,
  `vc` varchar(6) DEFAULT NULL,
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
