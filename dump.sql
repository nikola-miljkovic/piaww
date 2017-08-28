-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pia_prj
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `manufacturer_aircraft_idx` (`manufacturer`),
  KEY `type_aircraft_idx` (`type`),
  KEY `owner_aircraft_idx` (`owner`),
  CONSTRAINT `manufacturer_aircraft` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `owner_aircraft` FOREIGN KEY (`owner`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_aircraft` FOREIGN KEY (`type`) REFERENCES `aircraft_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
INSERT INTO `aircraft` VALUES (1,2,1,1),(2,2,2,1),(3,2,3,1),(4,2,3,1),(5,2,3,1);
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft_type`
--

DROP TABLE IF EXISTS `aircraft_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `length` decimal(10,3) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `license` varchar(2) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `manufacturer_type_idx` (`manufacturer`),
  CONSTRAINT `manufacturer_type` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type`
--

LOCK TABLES `aircraft_type` WRITE;
/*!40000 ALTER TABLE `aircraft_type` DISABLE KEYS */;
INSERT INTO `aircraft_type` VALUES (1,2,'A330',57.500,870,'AZ',254),(2,2,'A320',37.500,828,'AZ',174),(3,2,'A319',33.800,828,'AZ',144),(4,2,'BMWX6',123.000,123,'AZ',0);
/*!40000 ALTER TABLE `aircraft_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `id` varchar(3) NOT NULL,
  `name` varchar(128) NOT NULL,
  `runway_count` int(11) NOT NULL,
  `city` varchar(128) NOT NULL,
  `country` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('BEG','Aerodrom Nikola Tesla Beograd',20,'Belgrade','Serbia'),('BUD','Budapest Airport',5,'Budapest','Hungary'),('JFK','JFK - New York',10,'New York','United States of America'),('NYC','New York City Airport',20,'New York','United States'),('TIV','Aerodrom Tivat',20,'Tivat','Serbia');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airways`
--

DROP TABLE IF EXISTS `airways`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airways` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `country` varchar(64) NOT NULL,
  `website` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `address` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airways`
--

LOCK TABLES `airways` WRITE;
/*!40000 ALTER TABLE `airways` DISABLE KEYS */;
INSERT INTO `airways` VALUES (1,'Air Serbia','Serbia','http://www.airserbia.com/','airserbia@airserbia.com','AirSrbija 2011'),(2,'Lufthansa','Germany','http://www.lufthansa.com','info@lufthansa.com','Germany 2017');
/*!40000 ALTER TABLE `airways` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `passport` bigint(20) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `creditCard` varchar(45) NOT NULL,
  `flightCode` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_flight_idx` (`flight`),
  CONSTRAINT `booking_flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew` (
  `user` int(11) NOT NULL,
  `flight` int(11) NOT NULL,
  KEY `crew_flight_idx` (`flight`),
  KEY `crew_user_idx` (`user`),
  CONSTRAINT `crew_flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `crew_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (23,27),(27,27),(28,27),(32,27),(35,27),(23,28),(27,28),(28,28),(32,28),(35,28),(23,29),(27,29),(28,29),(32,29),(35,29),(23,30),(27,30),(28,30),(32,30),(35,30),(23,31),(27,31),(28,31),(32,31),(35,31),(23,32),(24,32),(30,32),(31,32),(32,32),(23,33),(24,33),(30,33),(31,33),(32,33),(26,34),(27,34),(29,34),(34,34),(36,34),(37,34),(26,35),(27,35),(29,35),(34,35),(36,35),(37,35),(23,36),(24,36),(30,36),(31,36),(32,36),(23,37),(24,37),(30,37),(31,37),(32,37),(23,38),(27,38),(29,38),(32,38),(34,38);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_id` varchar(10) NOT NULL,
  `airport` varchar(3) NOT NULL,
  `dest_airport` varchar(3) NOT NULL,
  `aircraft` int(11) NOT NULL,
  `charter` tinyint(4) NOT NULL,
  `departure` datetime NOT NULL,
  `arrival` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `eta` datetime DEFAULT NULL,
  `arrived_at` datetime DEFAULT NULL,
  `start_gate` int(11) NOT NULL,
  `end_gate` int(11) NOT NULL,
  `booked` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `airport_idx` (`airport`),
  KEY `dest_airport_idx` (`dest_airport`),
  KEY `flight_gate_s_idx` (`start_gate`),
  KEY `flight_aircraft_idx` (`aircraft`),
  KEY `flight_gate_e_idx` (`end_gate`),
  CONSTRAINT `flight_aircraft` FOREIGN KEY (`aircraft`) REFERENCES `aircraft` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_dest_airport` FOREIGN KEY (`dest_airport`) REFERENCES `airport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_gate_e` FOREIGN KEY (`end_gate`) REFERENCES `gate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_gate_s` FOREIGN KEY (`start_gate`) REFERENCES `gate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_source_airport` FOREIGN KEY (`airport`) REFERENCES `airport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (22,'YU113','TIV','BEG',3,0,'2017-09-01 00:00:00','2017-09-01 00:45:00',45,'P',NULL,NULL,3,16,0),(23,'YU113','TIV','BEG',3,0,'2017-09-08 00:00:00','2017-09-08 00:45:00',45,'P',NULL,NULL,3,16,0),(24,'YU113','TIV','BEG',3,0,'2017-09-15 00:00:00','2017-09-15 00:45:00',45,'P',NULL,NULL,3,16,0),(25,'YU113','TIV','BEG',3,0,'2017-09-22 00:00:00','2017-09-22 00:45:00',45,'P',NULL,NULL,3,16,0),(26,'YU113','TIV','BEG',3,0,'2017-09-29 00:00:00','2017-09-29 00:45:00',45,'P',NULL,NULL,3,16,0),(27,'JU180','TIV','BEG',4,0,'2017-09-01 00:00:00','2017-09-01 00:45:00',45,'D',NULL,NULL,2,17,0),(28,'JU180','TIV','BEG',4,0,'2017-09-08 00:00:00','2017-09-08 00:45:00',45,'D',NULL,NULL,2,17,0),(29,'JU180','TIV','BEG',4,0,'2017-09-15 00:00:00','2017-09-15 00:45:00',45,'D',NULL,NULL,2,17,0),(30,'JU180','TIV','BEG',4,0,'2017-09-22 00:00:00','2017-09-22 00:45:00',45,'D',NULL,NULL,2,17,0),(31,'JU180','TIV','BEG',4,0,'2017-09-29 00:00:00','2017-09-29 00:45:00',45,'D',NULL,NULL,2,17,0),(32,'JU186','TIV','BEG',3,0,'2017-08-01 07:00:00','2017-08-01 07:50:00',50,'P',NULL,NULL,11,14,0),(33,'JU187','BEG','TIV',3,0,'2017-08-01 09:00:00','2017-08-01 09:50:00',50,'P',NULL,NULL,14,13,0),(34,'JU182','TIV','BEG',2,0,'2017-08-01 13:40:00','2017-08-01 14:25:00',45,'P',NULL,NULL,7,14,0),(35,'JU183','BEG','TIV',2,0,'2017-08-01 15:00:00','2017-08-01 15:45:00',45,'P',NULL,NULL,14,10,0),(36,'JU184','TIV','BEG',3,0,'2017-08-01 17:05:00','2017-08-01 17:55:00',50,'P',NULL,NULL,13,15,0),(37,'JU185','BEG','TIV',3,0,'2017-08-01 18:45:00','2017-08-01 19:35:00',50,'P',NULL,NULL,15,2,0),(38,'JU334','BUD','BEG',1,0,'2017-08-29 08:15:00','2017-08-29 09:00:00',45,'D',NULL,NULL,4,27,0);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_radars`
--

DROP TABLE IF EXISTS `flight_radars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_radars` (
  `flight` int(11) NOT NULL,
  `radar` varchar(10) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`position`,`flight`,`radar`),
  KEY `radar_radar_idx` (`radar`),
  KEY `radar_flight_idx` (`flight`),
  CONSTRAINT `radar_flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `radar_radar` FOREIGN KEY (`radar`) REFERENCES `radar` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_radars`
--

LOCK TABLES `flight_radars` WRITE;
/*!40000 ALTER TABLE `flight_radars` DISABLE KEYS */;
INSERT INTO `flight_radars` VALUES (27,'BEG',0),(27,'TGD',1),(27,'TIV',2),(27,'ZLA',3),(28,'BEG',0),(28,'TGD',1),(28,'TIV',2),(28,'ZLA',3),(29,'BEG',0),(29,'TGD',1),(29,'TIV',2),(29,'ZLA',3),(30,'BEG',0),(30,'TGD',1),(30,'TIV',2),(30,'ZLA',3),(31,'BEG',0),(31,'TGD',1),(31,'TIV',2),(31,'ZLA',3),(32,'BEG',0),(32,'TGD',1),(32,'TIV',2),(32,'ZLA',3),(33,'BEG',0),(33,'TGD',1),(33,'TIV',2),(33,'ZLA',3),(34,'BEG',0),(34,'TGD',1),(34,'TIV',2),(34,'ZLA',3),(35,'BEG',0),(35,'TGD',1),(35,'TIV',2),(35,'ZLA',3),(36,'BEG',0),(36,'TGD',1),(36,'TIV',2),(36,'ZLA',3),(37,'BEG',0),(37,'TGD',1),(37,'TIV',2),(37,'ZLA',3),(38,'BEG',0),(38,'BUD',1),(38,'TRT',2);
/*!40000 ALTER TABLE `flight_radars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_status`
--

DROP TABLE IF EXISTS `flight_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flight_status_flight_idx` (`flight`),
  CONSTRAINT `flight_status_flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_status`
--

LOCK TABLES `flight_status` WRITE;
/*!40000 ALTER TABLE `flight_status` DISABLE KEYS */;
INSERT INTO `flight_status` VALUES (1,38,4),(3,28,3),(4,29,3),(5,31,3),(6,30,3),(12,27,3);
/*!40000 ALTER TABLE `flight_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gate`
--

DROP TABLE IF EXISTS `gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal` int(11) NOT NULL,
  `gate` varchar(10) NOT NULL,
  `airport` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gate_airport_idx` (`airport`),
  CONSTRAINT `gate_airport` FOREIGN KEY (`airport`) REFERENCES `airport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gate`
--

LOCK TABLES `gate` WRITE;
/*!40000 ALTER TABLE `gate` DISABLE KEYS */;
INSERT INTO `gate` VALUES (1,1,'A1','JFK'),(2,1,'A1','BEG'),(3,1,'A2','BEG'),(4,1,'A3','BEG'),(5,1,'A4','BEG'),(6,1,'A5','BEG'),(7,1,'A6','BEG'),(8,2,'A7','BEG'),(9,2,'A8','BEG'),(10,2,'A9','BEG'),(11,2,'A10','BEG'),(12,2,'A11','BEG'),(13,2,'A12','BEG'),(14,1,'A1','TIV'),(15,1,'A2','TIV'),(16,1,'A3','TIV'),(17,1,'A4','TIV'),(18,1,'A5','TIV'),(19,1,'A6','TIV'),(20,2,'A7','TIV'),(21,2,'A8','TIV'),(22,2,'A9','TIV'),(23,2,'A10','TIV'),(24,2,'A11','TIV'),(25,2,'A12','TIV'),(26,1,'A1','BUD'),(27,2,'B1','BUD'),(28,3,'C1','BUD');
/*!40000 ALTER TABLE `gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'Boeing'),(2,'Airbus SAS'),(3,'Embraer'),(4,'Tupolev');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radar`
--

DROP TABLE IF EXISTS `radar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radar` (
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radar`
--

LOCK TABLES `radar` WRITE;
/*!40000 ALTER TABLE `radar` DISABLE KEYS */;
INSERT INTO `radar` VALUES ('BEG'),('BUD'),('TGD'),('TIV'),('TRT'),('ZLA');
/*!40000 ALTER TABLE `radar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_request`
--

DROP TABLE IF EXISTS `reservation_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aircraft` int(11) NOT NULL,
  `owner` int(11) NOT NULL,
  `requester` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reservation_aircraft_idx` (`aircraft`),
  KEY `reservation_owner_idx` (`owner`),
  KEY `reservation_requester_idx` (`requester`),
  CONSTRAINT `reservation_aircraft` FOREIGN KEY (`aircraft`) REFERENCES `aircraft` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reservation_owner` FOREIGN KEY (`owner`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reservation_requester` FOREIGN KEY (`requester`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_request`
--

LOCK TABLES `reservation_request` WRITE;
/*!40000 ALTER TABLE `reservation_request` DISABLE KEYS */;
INSERT INTO `reservation_request` VALUES (1,4,1,2,3),(2,1,1,2,2),(3,2,1,2,3);
/*!40000 ALTER TABLE `reservation_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flag` int(10) unsigned NOT NULL,
  `firstName` varchar(64) NOT NULL,
  `lastName` varchar(64) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(256) NOT NULL,
  `employer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `user_employer_idx` (`employer`),
  CONSTRAINT `user_employer` FOREIGN KEY (`employer`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,4,'Nikola','Miljkovic','2017-08-24','milja13375@gmail.com','djodzo','djodzo1234',1),(22,3,'djordjevic','djordjevic','1990-08-24','djordjevic@gmail.com','djordjevic','test1234',1),(23,3,'jevdjevic','jevdjevic','1990-08-24','jevdjevicxdd@gmail.com','jevdjevic','test1234',1),(24,3,'teodosic','teodosic','1990-08-24','teodosic@gmail.com','teodosic','test1234',1),(25,3,'marjanovic','marjanovic','1990-08-24','marjanovic@gmail.com','marjanovic','test1234',1),(26,3,'filipovic','filipovic','1990-08-24','filipovic@gmail.com','filipovic','test1234',1),(27,3,'prlainovic','prlainovic','1990-08-24','prlainovic@gmai.com','prlainovic','test1234',1),(28,2,'vesnavulovic','vesnavulovic','1990-08-24','vesnavulovic@gmai.com','vesnavulovic','test1234',1),(29,2,'nadastankovic','nadastankovic','1990-08-24','nadastankovic@gmai.com','nadastankovic','test1234',1),(30,2,'dusicaspasic','dusicaspasic','1990-08-24','dusicaspasic@gmai.com','dusicaspasic','test1234',1),(31,2,'natasamiljkovic','natasamiljkovic','1990-08-24','natasamiljkovic@gmai.com','natasamiljkovic','test1234',1),(32,2,'sanjapetrovic','sanjapetrovic','1990-08-24','sanjapetrovic@gmai.com','sanjapetrovic','test1234',1),(33,2,'majanenadic','majanenadic','1990-08-24','majanenadicc@gmai.com','majanenadic','test1234',1),(34,2,'katarinasreckovic','katarinasreckovic','1990-08-24','katarinasreckovic@gmai.com','katarinasreckovic','test1234',1),(35,2,'jovanagrbic','jovanagrbic','1990-08-24','jovanagrbic@gm.com','jovanagrbic','test1234',1),(36,2,'tijanadimitrijevic','tijanadimitrijevic','1990-08-24','tijanadimitrijevic@gg.com','tijanadimitrijevic','test1234',1),(37,2,'marijapetrovic','marijapetrovic','1990-08-24','marijapetrovic@gmai.com','marijapetrovic','test1234',1),(38,1,'tester','tester','1990-08-24','tester@gmail.com','tester','test1234',1),(39,1,'tester2','tester2','1990-08-24','tester2@gg.car','tester2','test1234',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-28 22:14:03
