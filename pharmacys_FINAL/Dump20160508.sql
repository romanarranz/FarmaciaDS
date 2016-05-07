CREATE DATABASE  IF NOT EXISTS `pharmacys` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pharmacys`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 10.211.55.6    Database: pharmacys
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
-- Table structure for table `CATEGORY`
--

DROP TABLE IF EXISTS `CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `IMG` varchar(500) DEFAULT 'http://localhost:8080/pharmacys/img/img_no_aviable.png',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORY`
--

LOCK TABLES `CATEGORY` WRITE;
/*!40000 ALTER TABLE `CATEGORY` DISABLE KEYS */;
INSERT INTO `CATEGORY` VALUES (1,'BABY','http://localhost:8080/pharmacys/img/categories/baby_cat.png'),(2,'NUTRITION','http://localhost:8080/pharmacys/img/categories/nutrition_cat.png'),(3,'HEALTH','http://localhost:8080/pharmacys/img/categories/health_cat.png'),(4,'NATURAL','http://localhost:8080/pharmacys/img/categories/natural_cat.png'),(5,'HYGIENE','http://localhost:8080/pharmacys/img/categories/hygiene_cat.png'),(6,'COSMETIC','http://localhost:8080/pharmacys/img/categories/cosmetic_cat.png'),(7,'VETERINARY','http://localhost:8080/pharmacys/img/categories/veterinary_cat.png');
/*!40000 ALTER TABLE `CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INVENTORY` (
  `PHARMACYID` int(11) NOT NULL,
  `PRODUCTID` int(11) NOT NULL,
  `PRICE` float DEFAULT '0',
  `STOCK` int(11) DEFAULT '0',
  `QUERYCOUNT` int(11) DEFAULT '0',
  PRIMARY KEY (`PHARMACYID`,`PRODUCTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INVENTORY`
--

LOCK TABLES `INVENTORY` WRITE;
/*!40000 ALTER TABLE `INVENTORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `INVENTORY` ENABLE KEYS */;
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
  `START_SCHEDULE` tinyint(2) DEFAULT '0',
  `END_SCHEDULE` tinyint(2) DEFAULT '0',
  `LATITUDE` float DEFAULT '0',
  `LONGITUDE` float DEFAULT '0',
  `ADDRESS` varchar(200) DEFAULT '',
  PRIMARY KEY (`CIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHARMACY`
--

LOCK TABLES `PHARMACY` WRITE;
/*!40000 ALTER TABLE `PHARMACY` DISABLE KEYS */;
INSERT INTO `PHARMACY` VALUES ('B18293787','FARMACIA BATANERO',967382917,'FARMACIA DEL CENTRO',0,0,37.1356,-3.5583,'Calle El Romeral, 12-33, 18193 Monachil, Granada, EspaÃ±a');
/*!40000 ALTER TABLE `PHARMACY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(160) NOT NULL,
  `DESCRIPTION` varchar(600) NOT NULL,
  `LABORATORY` varchar(80) NOT NULL,
  `UNITS` varchar(5) NOT NULL,
  `EXPIRATION_DATE` date NOT NULL,
  `SIZE` int(11) NOT NULL,
  `LOT` varchar(45) NOT NULL,
  `URL_IMG` varchar(500) DEFAULT 'http://localhost:8080/pharmacys/img/img_no_aviable.png',
  `CATEGORYID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_idx` (`CATEGORYID`),
  CONSTRAINT `ID` FOREIGN KEY (`CATEGORYID`) REFERENCES `CATEGORY` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT`
--

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
INSERT INTO `PRODUCT` VALUES (1,'askldk','asdad','ldklsakd','gr','0026-02-08',2002,'02','http://localhost:8080/pharmacys/data/avatar.png',NULL),(2,'kljkl','9390','jkl','gr','0028-02-29',30,'3090','http://localhost:8080/pharmacys/data/avatar.png',1),(3,'asd','29','dsas','gr','0025-08-12',2002,'02adas','http://localhost:8080/pharmacys/data/avatar.png',1),(4,'jl','2dkasl','kj','gr','0016-08-12',2020,'20',NULL,1);
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
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
  `CIFPHARMACY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES ('pharmacysds@gmail.com','admin','admin','9c9de5fa26966625507c2afa04b368be534321503dc3565a68a0eb54725d9f2cc88dca78c142d7b9f71ca823876511eeb57597bb3a65cc65aaead896031395cf','',1,'',1,'B18293787');
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

-- Dump completed on 2016-05-08  1:39:36
