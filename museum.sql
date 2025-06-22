CREATE DATABASE  IF NOT EXISTS `museum` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `museum`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: museum
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mu_colecciones`
--

DROP TABLE IF EXISTS `mu_colecciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_colecciones` (
  `id_coleccion` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `siglo` varchar(10) DEFAULT NULL,
  `descripcion` text,
  `id_sala` int DEFAULT NULL,
  PRIMARY KEY (`id_coleccion`),
  KEY `id_sala` (`id_sala`),
  CONSTRAINT `mu_colecciones_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `mu_salas` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_colecciones`
--

LOCK TABLES `mu_colecciones` WRITE;
/*!40000 ALTER TABLE `mu_colecciones` DISABLE KEYS */;
INSERT INTO `mu_colecciones` VALUES (1,'Colección de Elefantes','XX','Muestra de elefantes africanos y asiáticos',1),(2,'Fósiles de Terópodos','XXI','Colección de fósiles de carnívoros mesozoicos',2);
/*!40000 ALTER TABLE `mu_colecciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_comision`
--

DROP TABLE IF EXISTS `mu_comision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_comision` (
  `id_comision` int NOT NULL AUTO_INCREMENT,
  `id_transaccion` int DEFAULT NULL,
  `comision` varchar(10) DEFAULT NULL,
  `monto_final` int DEFAULT NULL,
  PRIMARY KEY (`id_comision`),
  KEY `id_transaccion` (`id_transaccion`),
  CONSTRAINT `mu_comision_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `mu_transaccion` (`id_transaccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_comision`
--

LOCK TABLES `mu_comision` WRITE;
/*!40000 ALTER TABLE `mu_comision` DISABLE KEYS */;
INSERT INTO `mu_comision` VALUES (1,1,'5%',2850),(2,2,'3%',1455);
/*!40000 ALTER TABLE `mu_comision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_entradas`
--

DROP TABLE IF EXISTS `mu_entradas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_entradas` (
  `id_entrada` int NOT NULL AUTO_INCREMENT,
  `id_museo` int DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `codigo_qr` varchar(50) DEFAULT NULL,
  `precio_total` int DEFAULT NULL,
  PRIMARY KEY (`id_entrada`),
  KEY `id_museo` (`id_museo`),
  CONSTRAINT `mu_entradas_ibfk_1` FOREIGN KEY (`id_museo`) REFERENCES `mu_museos` (`id_museo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_entradas`
--

LOCK TABLES `mu_entradas` WRITE;
/*!40000 ALTER TABLE `mu_entradas` DISABLE KEYS */;
INSERT INTO `mu_entradas` VALUES (1,1,'Carlos Méndez','2025-06-01','QR003',3000),(2,1,'Ana Torres','2025-06-02','QR004',1500);
/*!40000 ALTER TABLE `mu_entradas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_especie`
--

DROP TABLE IF EXISTS `mu_especie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_especie` (
  `id_especie` int NOT NULL AUTO_INCREMENT,
  `nombre_cientifico` varchar(255) DEFAULT NULL,
  `nombre_comun` varchar(255) DEFAULT NULL,
  `fecha_extincion` year DEFAULT NULL,
  `epoca` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `id_coleccion` int DEFAULT NULL,
  PRIMARY KEY (`id_especie`),
  KEY `id_coleccion` (`id_coleccion`),
  CONSTRAINT `mu_especie_ibfk_1` FOREIGN KEY (`id_coleccion`) REFERENCES `mu_colecciones` (`id_coleccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_especie`
--

LOCK TABLES `mu_especie` WRITE;
/*!40000 ALTER TABLE `mu_especie` DISABLE KEYS */;
INSERT INTO `mu_especie` VALUES (1,'Mammuthus primigenius','Mamut lanudo',2000,'Edad de Hielo','Mamífero prehistórico extinto',1),(2,'Tyrannosaurus rex','T-Rex',NULL,'Cretácico','Depredador carnívoro gigante',2);
/*!40000 ALTER TABLE `mu_especie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_fichas`
--

DROP TABLE IF EXISTS `mu_fichas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_fichas` (
  `id_ficha` int NOT NULL AUTO_INCREMENT,
  `id_sala` int DEFAULT NULL,
  `valoracion` int DEFAULT NULL,
  `observacion` text,
  PRIMARY KEY (`id_ficha`),
  KEY `id_sala` (`id_sala`),
  CONSTRAINT `mu_fichas_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `mu_salas` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_fichas`
--

LOCK TABLES `mu_fichas` WRITE;
/*!40000 ALTER TABLE `mu_fichas` DISABLE KEYS */;
INSERT INTO `mu_fichas` VALUES (1,1,5,'Impresionante realismo en las réplicas'),(2,2,5,'La estrella del museo, muy educativo');
/*!40000 ALTER TABLE `mu_fichas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_museos`
--

DROP TABLE IF EXISTS `mu_museos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_museos` (
  `id_museo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `fecha_fundacion` date DEFAULT NULL,
  `nombre_director` varchar(255) DEFAULT NULL,
  `sitio_web` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_museo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_museos`
--

LOCK TABLES `mu_museos` WRITE;
/*!40000 ALTER TABLE `mu_museos` DISABLE KEYS */;
INSERT INTO `mu_museos` VALUES (1,'American Museum of Natural History','Museo de Historia Natural','Central Park West & 79th St, New York, NY 10024, Estados Unidos','1869-04-06','Sean M. Decatur','https://www.amnh.org');
/*!40000 ALTER TABLE `mu_museos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_precios`
--

DROP TABLE IF EXISTS `mu_precios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_precios` (
  `id_precio` int NOT NULL AUTO_INCREMENT,
  `id_sala` int DEFAULT NULL,
  `nombre_precio` varchar(100) DEFAULT NULL,
  `monto` int DEFAULT NULL,
  PRIMARY KEY (`id_precio`),
  KEY `id_sala` (`id_sala`),
  CONSTRAINT `mu_precios_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `mu_salas` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_precios`
--

LOCK TABLES `mu_precios` WRITE;
/*!40000 ALTER TABLE `mu_precios` DISABLE KEYS */;
INSERT INTO `mu_precios` VALUES (1,1,'Entrada regular',6),(2,2,'Entrada especial',3);
/*!40000 ALTER TABLE `mu_precios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_salas`
--

DROP TABLE IF EXISTS `mu_salas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_salas` (
  `id_sala` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `promedio_valoracion` decimal(3,1) DEFAULT NULL,
  `id_museo` int DEFAULT NULL,
  PRIMARY KEY (`id_sala`),
  KEY `id_museo` (`id_museo`),
  CONSTRAINT `mu_salas_ibfk_1` FOREIGN KEY (`id_museo`) REFERENCES `mu_museos` (`id_museo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_salas`
--

LOCK TABLES `mu_salas` WRITE;
/*!40000 ALTER TABLE `mu_salas` DISABLE KEYS */;
INSERT INTO `mu_salas` VALUES (1,'Sala de Mamíferos','Exhibiciones de mamíferos de diferentes continentes',4.8,1),(2,'Sala de Dinosaurios','Fósiles y reconstrucciones de dinosaurios',4.9,1);
/*!40000 ALTER TABLE `mu_salas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_tematicas`
--

DROP TABLE IF EXISTS `mu_tematicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_tematicas` (
  `id_tematica` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `caracteristicas` text,
  `epoca` varchar(100) DEFAULT NULL,
  `imagen_dir` varchar(255) DEFAULT NULL,
  `id_sala` int DEFAULT NULL,
  PRIMARY KEY (`id_tematica`),
  KEY `id_sala` (`id_sala`),
  CONSTRAINT `mu_tematicas_ibfk_1` FOREIGN KEY (`id_sala`) REFERENCES `mu_salas` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_tematicas`
--

LOCK TABLES `mu_tematicas` WRITE;
/*!40000 ALTER TABLE `mu_tematicas` DISABLE KEYS */;
INSERT INTO `mu_tematicas` VALUES (1,'Mamíferos Africanos','Ejemplares disecados, hábitats naturales','Contemporáneo','/img/mamiferos.jpg',1),(2,'T-Rex y sus parientes','Fósiles originales y modelos animados','Mesozoico','/img/dinosaurios.jpg',2);
/*!40000 ALTER TABLE `mu_tematicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mu_transaccion`
--

DROP TABLE IF EXISTS `mu_transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mu_transaccion` (
  `id_transaccion` int NOT NULL AUTO_INCREMENT,
  `id_entrada` int DEFAULT NULL,
  `tipo_tarjeta` enum('Visa','Mastercard','American Express','Dinner Club','Union Pay') DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_transaccion`),
  KEY `id_entrada` (`id_entrada`),
  CONSTRAINT `mu_transaccion_ibfk_1` FOREIGN KEY (`id_entrada`) REFERENCES `mu_entradas` (`id_entrada`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mu_transaccion`
--

LOCK TABLES `mu_transaccion` WRITE;
/*!40000 ALTER TABLE `mu_transaccion` DISABLE KEYS */;
INSERT INTO `mu_transaccion` VALUES (1,1,'Visa','2025-06-01'),(2,2,'Mastercard','2025-06-02');
/*!40000 ALTER TABLE `mu_transaccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18 17:58:28
