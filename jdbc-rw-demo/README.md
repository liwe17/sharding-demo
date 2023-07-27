# 读写分离示例(主从库)
## SQL

```mysql-sql
-- ds1 主库 ds2,ds3 从库
CREATE DATABASE `ds1`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE DATABASE `ds2`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE DATABASE `ds3`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

sql```