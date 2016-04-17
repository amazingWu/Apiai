-- phpMyAdmin SQL Dump
-- http://www.phpmyadmin.net
--
-- 生成日期: 2016 年 04 月 15 日 19:18

-- --------------------------------------------------------

--
-- 表的结构 `medicine`
--

CREATE TABLE IF NOT EXISTS `medicine` (
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
--
-- 表的结构 `type_product`
--

CREATE TABLE IF NOT EXISTS `type_product` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(45) NOT NULL COMMENT '类型名',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

