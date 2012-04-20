CREATE DATABASE  IF NOT EXISTS `elearning` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `elearning`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: elearning
-- ------------------------------------------------------
-- Server version	5.5.21

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
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `idpregunta` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(400) NOT NULL,
  `idcategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpregunta`),
  KEY `idcategoriaFK` (`idcategoria`),
  CONSTRAINT `idcategoriaFK` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` VALUES (1,'La organización de la información es:',1),(2,'La presentación de los temas es:',1),(3,'Las imágenes:',1),(4,'Los videos:',1),(5,'La navegación a través de las pantallas:',1),(6,'La ubicación de los elementos en la pantalla:',1),(7,'Los mensajes:',1),(8,'Las opciones que se presentan en los menús:',1),(9,'El material del curso:',2),(10,'Además del material incluido en el curso:',2),(11,'El sistema permite:',2),(12,'La forma en que están diseñadas las actividades:',2),(13,'El lenguaje en el que están escritos los materiales son:',2),(14,'Por su orientación, los materiales',2),(15,'La cantidad de material para aprender lo que los objetivos de aprendizaje',2),(16,'Los ejemplos que se proponen para cada tema:',2),(17,'Al escribir a la cuenta de correo electrónico de un profesor adjunto para',3),(18,'Se proporcionó asesorías al grupo o a mí en lo individual, para aclarar',3),(19,'En cuanto a la retroalimentación sobre mi desempeño',3),(20,'Respecto a la calificación de los trabajos:',3),(21,'Cuando se realiza la evaluación de un tema:',3),(22,'La plataforma:',4),(23,'Durante el curso, al navegar por la plataforma:',4),(24,'Acceder a los archivos del curso y bajarlos a mi computadora fue:',4),(25,'Al registrar mis trabajos en la plataforma',4);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-04-20  0:04:04
