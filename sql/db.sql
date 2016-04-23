-- phpMyAdmin SQL Dump
-- http://www.phpmyadmin.net
--
-- 生成日期: 2016 年 04 月 15 日 19:18

-- --------------------------------------------------------

--
-- 表的结构 `medicine`
--

CREATE TABLE IF NOT EXISTS `apidatabase`.`Medicine` (
  `productId` int(10) NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) NOT NULL,
  `productPrice` double DEFAULT NULL,
  `productBrand` varchar(45) DEFAULT NULL,
  `productParameter` varchar(200) DEFAULT NULL,
  `productType` varchar(45) NOT NULL,
  `productEffect` varchar(200) DEFAULT NULL,
  `productDirection` varchar(200) DEFAULT NULL,
  `productSideEffect` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE `apidatabase`.`Record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(60) NOT NULL,
  `postTime` DATE NOT NULL,
  `source` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

