#创建数据库
CREATE DATABASE IF NOT EXISTS comm;
#选择数据库
USE comm;
#创建user表
CREATE TABLE IF NOT EXISTS user (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `password`   VARCHAR(255)     DEFAULT NULL,
  `salt`       VARCHAR(255)     DEFAULT NULL,
  `username`   VARCHAR(255)     DEFAULT NULL,
  `createDate` DATE             DEFAULT NULL,
  `updateDate` DATE             DEFAULT NULL,
  `locked`     BIT(1)           DEFAULT NULL,
  `name`       VARCHAR(255)     DEFAULT NULL,
  `type`       VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jreodf78a7pl5qidfh43axdfb` (`username`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =12
  DEFAULT CHARSET =utf8;
#插入admin
INSERT
INTO
  user (username, password, salt, type)
VALUES ('admin', '3b6e389b430bd8255156136905a1259c',
        '661205464b49a25295c592526f12191e', 'admin');