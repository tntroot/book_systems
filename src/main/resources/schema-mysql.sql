CREATE SCHEMA IF NOT EXISTS `book_systems`;

CREATE TABLE IF NOT EXISTS `user`(
  `account` varchar(20) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `born` date NOT NULL,
  `manager` int DEFAULT '2',
  `redate` datetime DEFAULT NULL,
  `locked_status` int DEFAULT '0',
  PRIMARY KEY (`account`)
);

INSERT INTO `user` (`account`,`user_name`,`email`,`pwd`,`born`,`manager`,`redate`,`locked_status`) 
VALUES ('aaa','aaa','b@gmail.com','$2a$10$p/hcKv18COtrOHoBl6x3aey2WafMs4bAq7HKHS.wwOGa7z5G3fT4.','2023-10-04',2,'2023-10-22 15:58:56',0),
('eee','eee','d@gmail.com','$2a$10$eOmg9fkD3vpk1tNb/fsCOO5kOlM.ymWKGDSN5zgCZrhdemM/CsKGm','2023-10-05',1,'2023-10-22 14:58:12',0),
('ssss','ssss','a@gmail.com','$2a$10$xpXpbZEwYK0Lyd5.3b7f9OEyQ16WVFC9xZgZupVgG4vyTvashMMxa','2014-01-10',0,'2023-10-21 21:03:11',0)
on duplicate key update `account` = `account`;

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

CREATE TABLE IF NOT EXISTS `location` (
  `location_id` varchar(2) NOT NULL,
  `locationcol_name` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
);

INSERT INTO  `location` 
VALUES ('A','臺北市'),('B','臺中市'),('D','臺南市'),('E','高雄市'),('F','新北市'),('H','桃園市'),('I','嘉義市'),('J','新竹縣'),('K','苗栗縣'),('M','南投縣'),('N','彰化縣'),('O','新竹市'),
('P','雲林縣'),('Q','嘉義縣'),('T','屏東縣'),('U','花蓮縣'),('V','臺東縣'),('W','金門縣'),('X','澎湖縣'),('Y','陽明山'),('Z','連江縣') on duplicate key update location_id = location_id;