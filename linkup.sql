-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: setgame
-- ------------------------------------------------------
-- Server version	5.5.5-10.0.23-MariaDB

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
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `idFriends` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `friendsEmail` varchar(45) NOT NULL,
  PRIMARY KEY (`idFriends`),
  KEY `fk_friends_2_idx` (`friendsEmail`),
  KEY `fk_friends_1_idx` (`email`),
  CONSTRAINT `fk_friends_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,'555','123'),(2,'555','321'),(3,'555','b'),(4,'123','555'),(5,'123','b'),(6,'b','555'),(7,'b','123'),(8,'321','b'),(9,'b','321'),(10,'321','555');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `scores` int(11) NOT NULL DEFAULT '0',
  `photoIdentifier` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1','lujin','ibm11ibm',0,NULL),('1212','12','12',0,NULL),('123','123','132',0,NULL),('321','321','321',0,NULL),('555','555','555',0,'img'),('b','b','b',0,NULL),('bbb','bbb','bbb',0,NULL),('cccc','cccc','cccc',0,NULL),('eee','eee','eee',0,NULL),('fff','fff','fff',0,NULL),('ggg','ggg','ggg',0,NULL),('goodday','hadu','123',0,NULL),('goodday2','goodday','ibm11ibm',0,NULL),('goodday3','gooday3','ibm11ibm',0,NULL),('haha','haha','123',0,NULL),('hh','hhh','hhh',0,NULL),('hooktest','hook','ibm11ibm',0,NULL),('ibm11ibm','123','123',0,NULL),('iii','iii','iii',0,NULL),('junyumeng','jinyumeng','12345',0,NULL),('lujfeng','lujianfeng','123',0,NULL),('lujfeng2','lujianfeng','123',0,NULL),('lujfeng23','lujianfeng','123456',0,NULL),('lujfeng233','lujianfeng','1234',0,NULL),('lujfeng2332','lujianfeng','234',0,NULL),('lujfeng2332124','lujianfeng','2344',0,NULL),('lujfeng3','Your Full Name','123',0,NULL),('lujfeng@hotmail.com','lu jianfeng','ibm11ibm',0,'7d6dc2e9e6447df5649e2f58be43292e'),('lujfeng@hotmail.com2','tttt','ibm11ibm',0,'456ef1a7524536aa5df0ea8ac64216c5'),('lujfeng@hotmail.com3','test','ibm11ibm',0,'9b749cd1ef23105a2345085376acd789'),('lujfeng@hotmail.com4','test','ibm11ibm',0,'88bb2768ff198629579d3b1d649c3c2c'),('lujianfeng100-','dsfdsf','ibm11ibm',0,NULL),('qqq','qqq','qqq',0,NULL),('sss','sss','sss',0,NULL),('sssa','sds','cdcd',0,NULL),('test2','test2','ibm11ibm',0,NULL),('tictaclu','lujianfeng','ibm1ibm',100,NULL),('tictaclu@gmail.com','tictaclu','ibm11ibm',0,'f59252161c2af5f6b7b3cf3d65946cdc'),('vvv','vvv','vvv',0,NULL),('xx','xxx','xx',0,NULL),('xxx','xxx','xxx',0,NULL),('zhangzhonghua','zhangzhonghua','123456',0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-20 14:23:03
