CREATE DATABASE  IF NOT EXISTS `pharmacyS` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `pharmacyS`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 10.211.55.6    Database: pharmacyS
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ORDER`
--

DROP TABLE IF EXISTS `ORDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime NOT NULL,
  `LIST_PRODUCTPHARMACY` varchar(400) NOT NULL COMMENT 'sequence string is: “productId-quantity, productId-quantity”',
  `TOTALPRICE` float NOT NULL,
  `USEREMAIL` varchar(120) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDER`
--

LOCK TABLES `ORDER` WRITE;
/*!40000 ALTER TABLE `ORDER` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAYMENT`
--

DROP TABLE IF EXISTS `PAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAYMENT` (
  `ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `PAIDOUT` tinyint(1) NOT NULL DEFAULT '0',
  `DATE` datetime DEFAULT NULL,
  `METHOD` varchar(45) DEFAULT 'VISA' COMMENT 'VISA, MASTERCARD, PAYPAL',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAYMENT`
--

LOCK TABLES `PAYMENT` WRITE;
/*!40000 ALTER TABLE `PAYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `PAYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHARMACY`
--

DROP TABLE IF EXISTS `PHARMACY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHARMACY` (
  `CIF` varchar(9) NOT NULL,
  `NAME` varchar(120) NOT NULL,
  `PHONE_NUMBER` int(11) NOT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `START_SCHEDULE` int(11) DEFAULT '0',
  `END_SCHEDULE` int(11) DEFAULT '0',
  PRIMARY KEY (`CIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHARMACY`
--

LOCK TABLES `PHARMACY` WRITE;
/*!40000 ALTER TABLE `PHARMACY` DISABLE KEYS */;
/*!40000 ALTER TABLE `PHARMACY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHARMACY_PRODUCT`
--

DROP TABLE IF EXISTS `PHARMACY_PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHARMACY_PRODUCT` (
  `ID` int(11) NOT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `PRICE` decimal(3,2) NOT NULL DEFAULT '0.00',
  `QUANTITY` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHARMACY_PRODUCT`
--

LOCK TABLES `PHARMACY_PRODUCT` WRITE;
/*!40000 ALTER TABLE `PHARMACY_PRODUCT` DISABLE KEYS */;
/*!40000 ALTER TABLE `PHARMACY_PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY` varchar(300) NOT NULL,
  `NAME` varchar(160) NOT NULL,
  `DESCRIPTION` varchar(600) NOT NULL,
  `LABORATORY` varchar(80) NOT NULL,
  `UNITS` varchar(5) NOT NULL,
  `EXPIRATION_DATE` date NOT NULL,
  `SIZE` int(11) NOT NULL,
  `LOT` varchar(45) NOT NULL,
  `URL_IMG` varchar(500) DEFAULT NULL,
  `QUERY_COUNT` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT`
--

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
INSERT INTO `PRODUCT` VALUES (1,'BABY','Nestle Nestum Cerelaes','Nestle Nestum Cerelaes Sin Gluten es una papilla de cereales con bifidus lactis y prebióticos. No contiene azucares ni gluten. Proporcionan sabores mas naturales, facilitan la digestión y ayudan al sistema inmunitario.','Nestle','g','2016-08-17',500,'129081BA',NULL,0),(3,'medical','ibuprofeno','','layer','gr','2019-10-21',22,'34989B','',0);
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATION`
--

DROP TABLE IF EXISTS `RESERVATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESERVATION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime NOT NULL,
  `USEREMAIL` varchar(120) NOT NULL,
  `LIST_PRODUCT` varchar(45) NOT NULL,
  `AVIABLE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATION`
--

LOCK TABLES `RESERVATION` WRITE;
/*!40000 ALTER TABLE `RESERVATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESERVATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `EMAIL` varchar(100) NOT NULL,
  `NAME` varchar(80) NOT NULL DEFAULT '',
  `SURNAME` varchar(180) DEFAULT NULL,
  `PASSWORD` tinytext NOT NULL,
  `RESETHASH` tinytext,
  `ROLE` tinyint(1) NOT NULL,
  `LIST_ORDERS` varchar(400) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES ('pharmacysds@gmail.com','admin','admin','9c9de5fa26966625507c2afa04b368be534321503dc3565a68a0eb54725d9f2cc88dca78c142d7b9f71ca823876511eeb57597bb3a65cc65aaead896031395cf','',1,'',1);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-02 21:43:29
