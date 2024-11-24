-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library-manager
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `avatar_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_author_avatar_id_idx` (`avatar_id`),
  CONSTRAINT `fk_author_avatar_id` FOREIGN KEY (`avatar_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Аркадий Стругацкий',NULL),(2,'Борис Стругацкий',NULL),(3,'Гэри Чепмен',NULL),(4,'Толкин Джон Рональд Руэль',NULL),(5,'Джейн Остин',NULL),(6,'Филип Пулман',NULL),(7,'Дуглас Адамс',NULL),(8,'Джоан Роулинг',NULL),(9,'Харпер Ли',NULL),(10,'Алан Александр Милн',NULL),(11,'Джордж Оруэлл',NULL),(12,'Клайв Стейплз Льюис',NULL),(13,'Шарлотта Бронте',NULL),(14,'Джозеф Хеллер',NULL),(15,'Эмили Бронте',NULL),(16,'Себастьян Фолкс',NULL),(17,'Дафна Дюморье',NULL),(18,'Джером Сэлинджер',NULL),(19,'Кеннет Грэм',NULL),(20,'Чарльз Диккенс',NULL),(21,'Луиза Мэй Олкотт',NULL),(22,'Луи де Берньер',NULL),(23,'Лев Толстой',NULL),(24,'Маргарет Митчелл',NULL),(25,'Томас Харди',NULL);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_authors`
--

DROP TABLE IF EXISTS `book_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_authors` (
  `author_id` int NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`author_id`,`book_id`),
  KEY `fk_book_id_idx` (`book_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`),
  CONSTRAINT `fk_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_authors`
--

LOCK TABLES `book_authors` WRITE;
/*!40000 ALTER TABLE `book_authors` DISABLE KEYS */;
INSERT INTO `book_authors` VALUES (1,1),(2,1),(3,2),(4,3),(5,4),(6,5),(7,6),(8,7),(9,8),(10,9),(11,10),(12,11),(13,12),(14,13),(15,14),(16,15),(17,16),(18,17),(19,18),(20,19),(21,20),(22,21),(23,22),(24,23),(25,24);
/*!40000 ALTER TABLE `book_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_instances`
--

DROP TABLE IF EXISTS `book_instances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_instances` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `barcode` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_id_idx` (`book_id`),
  CONSTRAINT `fk_book_id2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_instances`
--

LOCK TABLES `book_instances` WRITE;
/*!40000 ALTER TABLE `book_instances` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_instances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `language` varchar(45) NOT NULL,
  `publisher_id` int NOT NULL,
  `genre_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_publisher_id_idx` (`publisher_id`),
  KEY `fk_genre_id_idx` (`genre_id`),
  CONSTRAINT `fk_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
  CONSTRAINT `fk_publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Пикник на обочине',2003,'RU',1,5),(2,'Пять языков любви',1992,'RU',2,6),(3,'Властелин колец',1955,'RU',3,5),(4,'Гордость и предубеждение',1797,'RU',3,7),(5,'Тёмные начала',2000,'RU',4,5),(6,'Автостопом по галактике',1979,'RU',3,8),(7,'Гарри Поттер и Кубок огня',2000,'RU',4,5),(8,'Убить пересмешника',1960,'RU',3,7),(9,'Винни Пух',1926,'RU',4,9),(10,'1984',1948,'RU',3,7),(11,'Лев, колдунья и плятной шкаф',1950,'RU',5,5),(12,'Джейн Эйр',1847,'RU',3,7),(13,'Уловка-22',1961,'RU',3,10),(14,'Грозовой перевал ',1847,'RU',5,7),(15,'Пение птиц',1993,'EN',6,7),(16,'Ребекка',1938,'RU',3,7),(17,'Над пропастью во ржи',1951,'RU',5,7),(18,'Ветер в ивах',1908,'RU',3,11),(19,'Большие надежды',1861,'RU',3,7),(20,'Маленькие женщины',1869,'RU',3,7),(21,'Мандолина капитана Корелли',1994,'RU',3,7),(22,'Война и мир',1869,'RU',5,7),(23,'Унесённые ветром',1936,'RU',5,7),(24,'Тэсс из рода д\'Эрбервиллей',1891,'RU',5,12);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Ужасы'),(2,'Фантастика'),(3,'Приключения'),(4,'Детективы'),(5,'Фентези'),(6,'Домоводство'),(7,'Роман'),(8,'Научная фантастика'),(9,'Детская повесть'),(10,'Сатира'),(11,'Сказочная повесть'),(12,'Трагедия');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `id` int NOT NULL,
  `reader_id` int NOT NULL,
  `reservation_date` date NOT NULL,
  `reservation_deadline` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `instance_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reader_id_idx` (`reader_id`),
  KEY `fk_instance_id_idx` (`instance_id`),
  CONSTRAINT `fk_instance_id` FOREIGN KEY (`instance_id`) REFERENCES `book_instances` (`id`),
  CONSTRAINT `fk_reader_id` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `avatar_image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_publisher_image_id_idx` (`avatar_image_id`),
  CONSTRAINT `fk_publisher_image_id` FOREIGN KEY (`avatar_image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'Аверсэв','Москва, проспект Мира, 1',NULL),(2,'Библия для всех','Сaнкт-Петербург, улица Лебедев, 31',NULL),(3,'ACT, Neoclassic','Москва, Звездный бульвар, 21',NULL),(4,'РОСМЭН','Москва, проспект Ленинградский, 36',NULL),(5,'Эксмо','Москва, улица Зорге, 1',NULL),(6,'Hutchinson & Co','Польша',NULL);
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `registration_date` date NOT NULL,
  `card_number` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_date` date NOT NULL,
  `reservation_deadline` date NOT NULL,
  `status` varchar(100) NOT NULL,
  `instance_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_instance_id_idx` (`instance_id`),
  CONSTRAINT `fk_reservation_instance_id` FOREIGN KEY (`instance_id`) REFERENCES `book_instances` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 18:48:01
