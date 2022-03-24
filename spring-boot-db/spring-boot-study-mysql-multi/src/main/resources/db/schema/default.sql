CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '姓名',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别：0-男，1-女',
  `addr` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '设备名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8