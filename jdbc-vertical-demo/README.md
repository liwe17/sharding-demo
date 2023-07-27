# 垂直分片示例(分库)
## SQL
```mysql-sql
CREATE DATABASE `db_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE DATABASE `db_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(30) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

sql```