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
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcion` (
  `idopcion` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(400) NOT NULL,
  `puntaje` double DEFAULT NULL,
  `idpregunta` int(11) DEFAULT NULL,
  PRIMARY KEY (`idopcion`),
  KEY `idpreguntaFK` (`idpregunta`),
  CONSTRAINT `idpreguntaFK` FOREIGN KEY (`idpregunta`) REFERENCES `pregunta` (`idpregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion`
--

LOCK TABLES `opcion` WRITE;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` VALUES (1,'Completa pero no es fácil de identificar',50,1),(2,'Completa y presenta un control adecuado',100,1),(3,'Incomprensible',0,1),(4,'Se presenta de forma desordenada',0,2),(5,'En algunos casos es difícil de comprender',50,2),(6,'Sigue una secuencia lógica, de tal forma que facilitan su comprensión',100,2),(7,'Son claras y su significado comprensible (el color, tamaño, movimiento o',100,3),(8,'El material no presenta imágenes',50,3),(9,'No tienen relación con el tema en el que se presentan',0,3),(10,'No se presentan videos en los cursos curso',0,4),(11,'No son de utilidad para el aprendizaje de los temas.',0,4),(12,'Presentan una mala calidad.',50,4),(13,'En general son de utilidad para el comprender mejor los temas.',100,4),(14,'Permite acceder fácil y rápidamente a los temas',100,5),(15,'Es difícil identificar cómo llegar a una página',0,5),(16,'En algunos puntos se dificulta identificar la ruta a seguir.',50,5),(17,'No es uniforme, lo que dificulta identificar los elementos',0,6),(18,'Sigue un patrón que permite identificar fácilmente la ubicación de cada',100,6),(19,'Cada pantalla presenta diferentes elementos.',50,6),(20,'En ningún momento se presentan mensajes.',0,7),(21,'Son confusos por lo que el usuario no sabe cómo responder a ellos.',50,7),(22,'Son congruentes, sencillos y guardan cierta uniformidad, por lo que se',100,7),(23,'No son claras por lo que crean confusión.',0,8),(24,'En forma clara y son fáciles de operar.',100,8),(25,'Algunas veces causan confusión.',50,8),(26,'Se apega totalmente al contenido del curso',100,9),(27,'Es irrelevante para el contenido del curso',0,9),(28,'Cubre parcialmente el contenido del curso',50,9),(29,'En todos los temas se proporciona información bibliográfica adecuada',100,10),(30,'No se proporciona información adicional',0,10),(31,'Se proporciona información adecuada, pero no en todos los temas',50,10),(32,'Se proporciona información bibliográfica que no es útil',0,10),(33,'Visualizar las calificaciones correspondientes a cada actividad',100,11),(34,'No ofrece información sobre la calificación de las actividades',0,11),(35,'Visualizar las calificaciones de una actividad por un periodo de tiempo.',50,11),(36,'No reflejan el aprendizaje que habrá de lograrse al realizarlas',0,12),(37,'No facilita su relación con temas previos o posteriores',0,12),(38,'Relaciona la teoría con práctica',50,12),(39,'Permite relacionar (integrar) los aprendizajes de los diferentes temas',100,12),(40,'Demasiado simples para el nivel',0,13),(41,'Claros',100,13),(42,'Densos pero comprensibles',100,13),(43,'Complicados',0,13),(44,'No tienen relación con los temas',0,14),(45,'Se relacionan con los temas en forma indirecta',50,14),(46,'Se relacionan directamente con los temas',100,14),(47,'Permiten reforzar el conocimiento de los temas',100,14),(48,'Insuficiente',0,15),(49,'Excesiva',0,15),(50,'Suficiente en algunos temas, pero no en todos',50,15),(51,'Suficiente',100,15),(52,'Son excelentes para entender mejor los conceptos.',100,16),(53,'Son buenos pero no se presentan en todos los temas',500,16),(54,'En ningún momento se presentan ejemplos',0,16),(55,'Me regresaron los mensajes por error en la cuenta o por encontrarse lleno',0,17),(56,'Respondió a mis mensajes esporádicamente',50,17),(57,'Siempre contestó oportunamente',100,17),(58,'Respondió pero vagamente. Incluso confundiéndome con otro alumno.',50,17),(59,'Nunca',0,18),(60,'1 ó 2 veces en el curso',50,18),(61,'De 3 a 5 veces en el curso',50,18),(62,'Más de 5',100,18),(63,'No dio retroalimentación',0,19),(64,'Su retroalimentación fue insuficiente',50,19),(65,'Sí proporcionó retroalimentación pero de forma extemporánea',50,19),(66,'Fue descriptivo, explicando cuales fueron los errores en los trabajos y por',100,19),(67,'No había calificado la mitad de las actividades al término del curso',0,20),(68,'Tardó más de 15 días en evaluar la mayoría de las actividades',50,20),(69,'Tardó hasta 15 días en calificar algunas actividades',100,20),(70,'No se ofrece retroalimentación',0,21),(71,'Se ofrecen sugerencias para repasar algún tema en específico',100,21),(72,'Se da retroalimentación sobre los errores pero no se hacen sugerencias',50,21),(73,'Cuenta con un manual útil, completo y sencillo.',100,22),(74,'No proporciona manual para su uso',0,22),(75,'Cuenta con un manual incompleto lo que hace difícil encontrar',50,22),(76,'Está construida de tal forma que no es necesario algún manual 100',100,22),(77,'Fue necesaria la ayuda de otras personas 50',0,23),(78,'Se presentaron problemas frecuentes de acceso 50',0,23),(79,'Fue fácil, pero en ocasiones no funcionó adecuadamente 70',50,23),(80,'Fue fácil y sin contratiempos 100',100,23),(81,'Imposible sin la ayuda de otra persona',0,24),(82,'Complicado.',0,24),(83,'Fácil, pero en ocasiones marcaba error',50,24),(84,'Fácil y sin contratiempos',100,24),(85,'Lo hice en el espacio destinado para otra actividad',0,25),(86,'En ocasiones no se pudo guardar el trabajo, enterándome al ver mi',0,25),(87,'En ocasiones no se pudo guardar el trabajo de inmediato debiendo',50,25),(88,'Nunca tuve contratiempos',100,25);
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;
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
