-- MySQL dump 10.13  Distrib 5.1.65, for Win32 (ia32)
--
-- Host: localhost    Database: saimail
-- ------------------------------------------------------
-- Server version	5.1.65-community

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `line1` varchar(150) NOT NULL,
  `line2` varchar(150) DEFAULT NULL,
  `line3` varchar(150) DEFAULT NULL,
  `city` varchar(60) NOT NULL,
  `state` varchar(150) NOT NULL,
  `country` varchar(150) NOT NULL,
  `region` varchar(150) NOT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `mapslink` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `cd_contact_type`
--

DROP TABLE IF EXISTS `cd_contact_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd_contact_type` (
  `contact_type_id` tinyint(4) NOT NULL,
  `contact_type` varchar(32) NOT NULL,
  `Description` varchar(100) NOT NULL,
  PRIMARY KEY (`contact_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd_contact_type`
--

LOCK TABLES `cd_contact_type` WRITE;
/*!40000 ALTER TABLE `cd_contact_type` DISABLE KEYS */;
INSERT INTO `cd_contact_type` VALUES (1,'MANDIR_TEAM','Mandir Team  internal'),(2,'EXTERNAL','External');
/*!40000 ALTER TABLE `cd_contact_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cd_status`
--

DROP TABLE IF EXISTS `cd_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd_status` (
  `status_id` tinyint(4) NOT NULL,
  `code` varchar(32) NOT NULL,
  `Description` varchar(100) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd_status`
--

LOCK TABLES `cd_status` WRITE;
/*!40000 ALTER TABLE `cd_status` DISABLE KEYS */;
INSERT INTO `cd_status` VALUES (1,'DATA_COLLECTED','Sai Mandir Data Collected'),(2,'DATA_UPLOADED','Sai Mandirs Data Uploaded to Shared Drive'),(3,'DATA_CONVERTED','Sai Mandirs Data Typed'),(4,'DATA_REVIEWED','Reviewed'),(5,'DATA_PUBLISHED','Published'),(6,'QA','Testing'),(7,'INACTIVE','Mandir Inactive');
/*!40000 ALTER TABLE `cd_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `mandir_id` tinyint(4) NOT NULL,
  `firstname` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,
  `email` varchar(200) NOT NULL,
  `primary_phonenumber` varchar(50) NOT NULL,
  `alt_phonenumber` varchar(50) NOT NULL,
  `role` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `country_id` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'USA'),(2,'India');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mandir`
--

DROP TABLE IF EXISTS `mandir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mandir` (
  `MANDIR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `info_gathered_on` datetime NOT NULL,
  `address_id` tinyint(4) NOT NULL,
  `website` varchar(150) DEFAULT NULL,
  `meta_data_id` tinyint(4) DEFAULT NULL,
  `status_id` tinyint(4) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`MANDIR_ID`),
  UNIQUE KEY `code` (`code`),
  KEY `address_id` (`address_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `mandir_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `mandir_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `cd_status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `mandir_data_flow`
--

DROP TABLE IF EXISTS `mandir_data_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mandir_data_flow` (
  `mandir_data_flow_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `mandir_id` tinyint(4) NOT NULL,
  `mandir_team_contact_id` tinyint(4) NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`mandir_data_flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mandir_data_flow`
--

LOCK TABLES `mandir_data_flow` WRITE;
/*!40000 ALTER TABLE `mandir_data_flow` DISABLE KEYS */;
/*!40000 ALTER TABLE `mandir_data_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `privilege_code` varchar(25) NOT NULL,
  `privilege_name` varchar(50) NOT NULL,
  PRIMARY KEY (`privilege_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES ('APPROVE_USER','Approve User'),('DELETE_USER','Delete User'),('TEMPLE_APPROVER','Temple Details Approver'),('TEMPLE_ENTRY','Enters Temple Details');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regions` (
  `region_id` int(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` VALUES (1,'USA'),(2,'Chennai'),(3,'Hyderabad');
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privilege` (
  `role_code` varchar(25) NOT NULL,
  `privilege_code` varchar(25) NOT NULL,
  PRIMARY KEY (`role_code`,`privilege_code`),
  KEY `fk_rpriv_priv_privilegecode` (`privilege_code`),
  CONSTRAINT `fk_rpriv_priv_privilegecode` FOREIGN KEY (`privilege_code`) REFERENCES `privilege` (`privilege_code`),
  CONSTRAINT `fk_rpriv_role_rolecode` FOREIGN KEY (`role_code`) REFERENCES `user_role` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
INSERT INTO `role_privilege` VALUES ('SA','APPROVE_USER'),('SA','DELETE_USER'),('SA','TEMPLE_APPROVER'),('TCO','TEMPLE_APPROVER'),('SA','TEMPLE_ENTRY'),('TCO','TEMPLE_ENTRY'),('USER','TEMPLE_ENTRY');
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `states` (
  `state_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`state_id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `states_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,1,'New Jersey');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `user_id` int(11) NOT NULL,
  `User_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `password` varchar(256) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `role_code` varchar(25) NOT NULL,
  `user_status_code` varchar(20) NOT NULL,
  `invalid_logon_attempt` tinyint(4) DEFAULT NULL,
  `password_date` datetime DEFAULT NULL,
  `rec_created_by` int(11) DEFAULT NULL,
  `rec_created_date` datetime DEFAULT NULL,
  `rec_modified_by` int(11) DEFAULT NULL,
  `rec_modified_date` datetime DEFAULT NULL,
  `rec_version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_info_id`),
  UNIQUE KEY `uk_musr_email` (`email`),
  KEY `fk_urol_role_rolecode` (`role_code`),
  CONSTRAINT `fk_urol_role_rolecode` FOREIGN KEY (`role_code`) REFERENCES `user_role` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_code` varchar(25) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('SA','System Admin'),('TCO','Temple Coordinator'),('USER','Enters Temple Details');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_status` (
  `user_status_code` varchar(20) NOT NULL,
  `user_status_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` VALUES ('APPROVED','Approved'),('DELETED','Deleted'),('LOCKED','Locked'),('NEW','New'),('PENDING','Pending'),('REJECTED','Rejected');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-25 17:54:21
