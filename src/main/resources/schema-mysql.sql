CREATE DATABASE IF NOT EXISTS `book_systems`;

CREATE TABLE IF NOT EXISTS `user`(
  `account` varchar(20) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `born` datetime DEFAULT NULL,
  `redate` datetime DEFAULT NULL,
  `locked_status` int DEFAULT '0',
  PRIMARY KEY (`account`)
);

CREATE TABLE IF NOT EXISTS `supplier` (
  `serial_num` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `compiled` int DEFAULT '0',
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `location_id` varchar(2) DEFAULT NULL,
  `location_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`serial_num`)
);

CREATE TABLE IF NOT EXISTS `personinfo` (
  `id` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `born` date DEFAULT NULL,
  `manager` int DEFAULT '0',
  `redate` date DEFAULT NULL,
  `locked_status` int DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `location` (
  `location_id` varchar(2) NOT NULL,
  `locationcol_name` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
);