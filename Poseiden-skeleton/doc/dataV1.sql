-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


DROP SCHEMA IF EXISTS `poseidonbis` ;
CREATE SCHEMA `poseidonbis` DEFAULT CHARACTER SET utf8 ;
USE `poseidonbis` ;

CREATE TABLE IF NOT EXISTS `poseidonbis`.`bidList` (
  `bidListId` tinyint(4) NOT NULL AUTO_INCREMENT,
  `vaccount` VARCHAR(30) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `bidQuantity` DOUBLE,
  `askQuantity` DOUBLE,
  `bid` DOUBLE,
  `ask` DOUBLE,
  `benchmark` VARCHAR(125),
  `bidListDate` TIMESTAMP,
  `commentary` VARCHAR(125),
  `security` VARCHAR(125),
  `status` VARCHAR(10),
  `trader` VARCHAR(125),
  `book` VARCHAR(125),
  `creationName` VARCHAR(125),
  `creationDate` TIMESTAMP,
  `revisionName` VARCHAR(125),
  `revisionDate` TIMESTAMP,
  `dealName` VARCHAR(125),
  `dealType` VARCHAR(125),
  `sourceListId` VARCHAR(125),
  `side` VARCHAR(125),

  PRIMARY KEY (`bidListId`)
);

CREATE TABLE IF NOT EXISTS `poseidonbis`.`trade` (
  `tradeId` tinyint(4) NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(30) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `buyQuantity` DOUBLE,
  `sellQuantity` DOUBLE,
  `buyPrice` DOUBLE,
  `sellPrice` DOUBLE,
  `tradeDate` TIMESTAMP,
  `security` VARCHAR(125),
  `status` VARCHAR(10),
  `trader` VARCHAR(125),
  `benchmark` VARCHAR(125),
  `book` VARCHAR(125),
  `creationName` VARCHAR(125),
  `creationDate` TIMESTAMP,
  `revisionName` VARCHAR(125),
  `revisionDate` TIMESTAMP,
  `dealName` VARCHAR(125),
  `dealType` VARCHAR(125),
  `sourceListId` VARCHAR(125),
  `side` VARCHAR(125),

  PRIMARY KEY (`tradeId`)
);

CREATE TABLE IF NOT EXISTS `poseidonbis`.`curvePoint` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `curveId` tinyint,
  `asOfDate` TIMESTAMP,
  `term` DOUBLE,
  `value` DOUBLE,
  `creationDate` TIMESTAMP,

  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `poseidonbis`.`rating` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `moodysRating` VARCHAR(125),
  `sandPRating` VARCHAR(125),
  `fitchRating` VARCHAR(125),
  `orderNumber` tinyint,

  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `poseidonbis`.`ruleName` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(125),
  `description` VARCHAR(125),
  `json` VARCHAR(125),
  `template` VARCHAR(512),
  `sqlStr` VARCHAR(125),
  `sqlPart` VARCHAR(125),

  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `poseidonbis`.`users` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(125),
  `password` VARCHAR(125),
  `fullname` VARCHAR(125),
  `role` VARCHAR(125),

  PRIMARY KEY (`id`)
);

insert into Users(`fullname`, `username`, `password`, `role`) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN");
insert into Users(`fullname`, `username`, `password`, `role`) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");