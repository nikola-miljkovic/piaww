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
  `id` int(11) NOT NULL,
  `manufacturer` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_aircraft_idx` (`owner`),
  KEY `manufacturer_aircraft_idx` (`manufacturer`),
  KEY `type_aircraft_idx` (`type`),
  CONSTRAINT `manufacturer_aircraft` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `owner_aircraft` FOREIGN KEY (`owner`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_aircraft` FOREIGN KEY (`type`) REFERENCES `aircraft_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type`
--

LOCK TABLES `aircraft_type` WRITE;
/*!40000 ALTER TABLE `aircraft_type` DISABLE KEYS */;
INSERT INTO `aircraft_type` VALUES (1,2,'A330',57.500,870,'AZ',254),(2,2,'A320',37.500,828,'AZ',174),(3,2,'A319',33.800,828,'AZ',144);
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
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `country` varchar(64) NOT NULL,
  `website` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `address` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airways`
--

LOCK TABLES `airways` WRITE;
/*!40000 ALTER TABLE `airways` DISABLE KEYS */;
INSERT INTO `airways` VALUES (1,'Air Serbia','Serbia','http://www.airserbia.com/','airserbia@airserbia.com','AirSrbija 2011');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,2,1,123123123,'NIkola','Miljkovic','1231-3122-3123-1231','DEEXWVIC');
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
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employed_at`
--

DROP TABLE IF EXISTS `employed_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employed_at` (
  `user` int(11) DEFAULT NULL,
  `airlines` int(11) DEFAULT NULL,
  KEY `employed_at_airlines_idx` (`airlines`),
  KEY `employed_at_user_idx` (`user`),
  CONSTRAINT `employed_at_airlines` FOREIGN KEY (`airlines`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `employed_at_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employed_at`
--

LOCK TABLES `employed_at` WRITE;
/*!40000 ALTER TABLE `employed_at` DISABLE KEYS */;
/*!40000 ALTER TABLE `employed_at` ENABLE KEYS */;
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
  KEY `flight_aircraft_idx` (`aircraft`),
  KEY `flight_gate_s_idx` (`start_gate`),
  CONSTRAINT `flight_aircraft` FOREIGN KEY (`aircraft`) REFERENCES `aircraft` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_dest_airport` FOREIGN KEY (`dest_airport`) REFERENCES `airport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_gate_e` FOREIGN KEY (`start_gate`) REFERENCES `gate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_gate_s` FOREIGN KEY (`start_gate`) REFERENCES `gate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flight_source_airport` FOREIGN KEY (`airport`) REFERENCES `airport` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'JU186','TIV','BEG',3,0,'2017-08-01 07:00:00','2017-08-01 07:50:00',50,'P',NULL,NULL,10,13,0),(2,'JU187','BEG','TIV',3,0,'2017-08-01 09:00:00','2017-08-01 09:50:00',50,'P',NULL,NULL,13,12,1),(3,'JU182','TIV','BEG',2,0,'2017-08-01 13:40:00','2017-08-01 14:25:00',45,'P',NULL,NULL,6,13,0),(4,'JU183','BEG','TIV',2,0,'2017-08-01 15:00:00','2017-08-01 15:45:00',45,'P',NULL,NULL,13,9,0),(5,'JU184','TIV','BEG',3,0,'2017-08-01 17:05:00','2017-08-01 17:55:00',50,'P',NULL,NULL,12,14,0),(6,'JU185','BEG','TIV',3,0,'2017-08-01 18:45:00','2017-08-01 19:35:00',50,'P',NULL,NULL,14,1,0);
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
  `position` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`position`,`flight`,`radar`),
  KEY `radar_radar_idx` (`radar`),
  KEY `radar_flight_idx` (`flight`),
  CONSTRAINT `radar_flight` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `radar_radar` FOREIGN KEY (`radar`) REFERENCES `radar` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_radars`
--

LOCK TABLES `flight_radars` WRITE;
/*!40000 ALTER TABLE `flight_radars` DISABLE KEYS */;
INSERT INTO `flight_radars` VALUES (1,'TGD',1),(1,'TIV',2),(1,'ZLA',3),(1,'BEG',15),(2,'TGD',1),(2,'TIV',2),(2,'ZLA',3),(2,'BEG',16),(3,'TGD',1),(3,'TIV',2),(3,'ZLA',3),(3,'BEG',17),(4,'TGD',1),(4,'TIV',2),(4,'ZLA',3),(4,'BEG',18),(5,'TGD',1),(5,'TIV',2),(5,'ZLA',3),(5,'BEG',19),(6,'TGD',1),(6,'TIV',2),(6,'ZLA',3),(6,'BEG',20);
/*!40000 ALTER TABLE `flight_radars` ENABLE KEYS */;
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
INSERT INTO `radar` VALUES ('BEG'),('TGD'),('TIV'),('ZLA');
/*!40000 ALTER TABLE `radar` ENABLE KEYS */;
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
  `employeer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `user_emplyeer_idx` (`employeer`),
  CONSTRAINT `user_emplyeer` FOREIGN KEY (`employeer`) REFERENCES `airways` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,'asdadasd','asdadasdasd','2017-08-16','sadadadasas','dadsadasdas','mAirwaysId123T!',1),(2,2,'asdasdasdasdas','dasdasdasddasa','2017-08-25','dadadasdzxzdxzd','dxzxzdzxdzdz','mAirwaysId123T!',1),(3,2,'ssdadsasd','asdadjsiaodjaojd','2017-08-31','milj1337@gmail.com','uasdad','ASDASDDSXsss!!123',1),(4,3,'resadasd','asdadasdas','2017-08-15','dasdasdasda','asdasdadas','ASDASDDSXsss!!123',1),(5,2,'asdsada','sdadas','2017-08-22','dasdasas','dasdasd','ASDASDDSXsss!!123',1),(6,3,'sadasda','dadasd','2017-08-09','asasdasasa','dssasdasasd','ASDASDDSXsss!!123',1),(7,0,'sdadasdas','asdasdasas','2017-08-08','asasdadss','dasdasdadaa','azzxxcv123',1),(8,3,'dadsasda','ddd','2017-08-16','asdda','dasddsds','mAirwaysId123T!',1),(11,2,'Roottest','Roottest','2017-08-08','Roottest','Roottest','Roottest123!',1),(17,3,'test123','test123','2017-08-09','test123','test123','test123!!',1),(20,2,'test1234','test1234','2017-08-14','test1234','test1234','test1234!',1),(21,4,'Nikola','Miljkovic','2017-08-24','milja13375@gmail.com','djodzo','djodzo1234',1);
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

-- Dump completed on 2017-08-24 22:29:48
