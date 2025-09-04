-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.11.6-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para serviciossocialescitas
CREATE DATABASE IF NOT EXISTS `serviciossocialescitas` /*!40100 DEFAULT CHARACTER SET utf32 COLLATE utf32_spanish_ci */;
USE `serviciossocialescitas`;

-- Volcando estructura para tabla serviciossocialescitas.citasdisponibles
CREATE TABLE IF NOT EXISTS `citasdisponibles` (
  `idCitaDisponible` int(11) NOT NULL AUTO_INCREMENT,
  `idTrabajadora` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `util` int(1) NOT NULL DEFAULT 1 COMMENT 'Para poder dejar huecos que no se puedan utilizar',
  `idPersona` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCitaDisponible`),
  KEY `FK1_idtrabajadora` (`idTrabajadora`),
  KEY `FK2_ipPersona` (`idPersona`),
  CONSTRAINT `FK1_idtrabajadora` FOREIGN KEY (`idTrabajadora`) REFERENCES `trabajadoras` (`idTrabajadora`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK2_ipPersona` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- Volcando datos para la tabla serviciossocialescitas.citasdisponibles: ~65 rows (aproximadamente)
INSERT INTO `citasdisponibles` (`idCitaDisponible`, `idTrabajadora`, `fecha`, `hora`, `util`, `idPersona`) VALUES
	(43, 1, '2025-08-20', '10:00:00', 1, NULL),
	(44, 1, '2025-08-20', '10:30:00', 1, 3),
	(45, 1, '2025-08-20', '11:00:00', 1, 1),
	(46, 1, '2025-08-20', '11:30:00', 1, 1),
	(47, 1, '2025-08-20', '12:00:00', 1, 1),
	(48, 1, '2025-08-20', '12:30:00', 1, 1),
	(49, 1, '2025-08-20', '13:00:00', 1, 1),
	(50, 2, '2025-08-20', '10:00:00', 1, NULL),
	(51, 2, '2025-08-20', '10:30:00', 1, 1),
	(52, 2, '2025-08-20', '11:00:00', 1, 3),
	(53, 2, '2025-08-20', '11:30:00', 1, 2),
	(54, 2, '2025-08-20', '12:00:00', 1, 1),
	(55, 2, '2025-08-20', '12:30:00', 1, 1),
	(56, 2, '2025-08-20', '13:00:00', 1, 1),
	(57, 2, '2025-08-21', '10:00:00', 1, 2),
	(58, 2, '2025-08-21', '10:30:00', 1, 3),
	(59, 2, '2025-08-21', '11:00:00', 1, 1),
	(60, 2, '2025-08-21', '11:30:00', 1, NULL),
	(61, 2, '2025-08-21', '12:00:00', 1, NULL),
	(62, 2, '2025-08-21', '12:30:00', 1, NULL),
	(63, 2, '2025-08-21', '13:00:00', 1, NULL),
	(64, 1, '2025-08-21', '10:00:00', 1, NULL),
	(65, 1, '2025-08-21', '10:30:00', 1, NULL),
	(66, 1, '2025-08-21', '11:00:00', 1, NULL),
	(67, 1, '2025-08-21', '11:30:00', 1, NULL),
	(68, 1, '2025-08-21', '12:00:00', 1, 3),
	(69, 1, '2025-08-21', '12:30:00', 1, 1),
	(70, 1, '2025-08-21', '13:00:00', 1, 2),
	(71, 1, '2025-08-22', '10:00:00', 1, NULL),
	(72, 1, '2025-08-22', '10:30:00', 1, 1),
	(73, 1, '2025-08-22', '11:00:00', 1, NULL),
	(74, 1, '2025-08-22', '11:30:00', 1, 3),
	(75, 1, '2025-08-22', '12:00:00', 1, NULL),
	(76, 1, '2025-08-22', '12:30:00', 1, 2),
	(77, 1, '2025-08-22', '13:00:00', 1, NULL),
	(78, 2, '2025-08-22', '10:00:00', 1, NULL),
	(79, 2, '2025-08-22', '10:30:00', 1, NULL),
	(80, 2, '2025-08-22', '11:00:00', 1, NULL),
	(81, 2, '2025-08-22', '11:30:00', 1, 3),
	(82, 2, '2025-08-22', '12:00:00', 1, 1),
	(83, 2, '2025-08-22', '12:30:00', 1, 2),
	(84, 2, '2025-08-22', '13:00:00', 1, NULL),
	(85, 2, '2025-09-08', '10:00:00', 1, 3),
	(86, 2, '2025-09-08', '10:30:00', 1, 4),
	(87, 2, '2025-09-08', '11:00:00', 1, 4),
	(88, 2, '2025-09-08', '11:30:00', 1, 5),
	(89, 2, '2025-09-08', '12:00:00', 1, 5),
	(90, 2, '2025-09-08', '12:30:00', 1, NULL),
	(91, 2, '2025-09-08', '13:00:00', 1, 2),
	(92, 2, '2025-09-10', '10:00:00', 1, NULL),
	(93, 2, '2025-09-10', '10:30:00', 1, NULL),
	(94, 2, '2025-09-10', '11:00:00', 1, NULL),
	(95, 2, '2025-09-10', '11:30:00', 1, NULL),
	(96, 2, '2025-09-10', '12:00:00', 1, NULL),
	(97, 2, '2025-09-10', '12:30:00', 1, NULL),
	(98, 2, '2025-09-10', '13:00:00', 1, NULL),
	(99, 2, '2025-09-09', '10:00:00', 1, NULL),
	(100, 2, '2025-09-09', '10:30:00', 1, NULL),
	(101, 2, '2025-09-09', '11:00:00', 1, 3),
	(102, 2, '2025-09-09', '11:30:00', 1, NULL),
	(103, 2, '2025-09-09', '12:00:00', 1, 5),
	(104, 2, '2025-09-09', '12:30:00', 1, 1),
	(105, 2, '2025-09-09', '13:00:00', 1, 1),
	(106, 1, '2025-09-08', '10:00:00', 1, 1),
	(107, 1, '2025-09-08', '12:00:00', 1, 1);

-- Volcando estructura para tabla serviciossocialescitas.horarioatencion
CREATE TABLE IF NOT EXISTS `horarioatencion` (
  `idHora` int(11) NOT NULL AUTO_INCREMENT,
  `HorarioAntencion` time DEFAULT NULL,
  `idTrabajadora` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHora`),
  KEY `FK1_trabajadora` (`idTrabajadora`),
  CONSTRAINT `FK1_trabajadora` FOREIGN KEY (`idTrabajadora`) REFERENCES `trabajadoras` (`idTrabajadora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- Volcando datos para la tabla serviciossocialescitas.horarioatencion: ~13 rows (aproximadamente)
INSERT INTO `horarioatencion` (`idHora`, `HorarioAntencion`, `idTrabajadora`) VALUES
	(1, '10:00:00', 2),
	(2, '10:30:00', 2),
	(3, '11:00:00', 2),
	(4, '11:30:00', 2),
	(5, '12:00:00', 2),
	(6, '12:30:00', 2),
	(7, '13:00:00', 2),
	(8, '10:00:00', 1),
	(9, '10:30:00', 1),
	(10, '11:00:00', 1),
	(11, '11:30:00', 1),
	(12, '12:00:00', 1),
	(13, '12:30:00', 1),
	(14, '13:00:00', 1);

-- Volcando estructura para tabla serviciossocialescitas.personas
CREATE TABLE IF NOT EXISTS `personas` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` varchar(11) DEFAULT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellidos` varchar(100) DEFAULT NULL,
  `FechaNac` date DEFAULT NULL,
  `Telefono1` varchar(12) DEFAULT NULL,
  `Telefono2` varchar(12) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activa` int(1) DEFAULT 1 COMMENT '1 activa o 0 desactivada',
  `Observaciones` varchar(500) DEFAULT NULL,
  `idTrabajadora` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `FK_personas_trabajadoras` (`idTrabajadora`),
  CONSTRAINT `FK_personas_trabajadoras` FOREIGN KEY (`idTrabajadora`) REFERENCES `trabajadoras` (`idTrabajadora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- Volcando datos para la tabla serviciossocialescitas.personas: ~5 rows (aproximadamente)
INSERT INTO `personas` (`idPersona`, `DNI`, `Nombre`, `Apellidos`, `FechaNac`, `Telefono1`, `Telefono2`, `email`, `activa`, `Observaciones`, `idTrabajadora`) VALUES
	(1, '03885536P', 'Victor', 'Palomo', '1982-01-22', '646268400', NULL, NULL, 1, NULL, 1),
	(2, '03659825D', 'Juan', NULL, NULL, NULL, NULL, NULL, 1, NULL, 2),
	(3, '70405263G', 'Felipe', 'Palomo Garcia', '1988-08-21', '654654687', '925784512', 'juan@gmail.com', 1, 'Observaciones de todas las que se puedan meter', 2),
	(4, '123456', 'Maria', 'Herminda', '2013-05-08', '654654654', '654987654', 'uno', 1, 'Muchas', 2),
	(5, '52602412T', 'Marian', 'Lopez', '2008-09-03', '654654654', '', '', 1, NULL, 2);

-- Volcando estructura para tabla serviciossocialescitas.trabajadoras
CREATE TABLE IF NOT EXISTS `trabajadoras` (
  `idTrabajadora` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) DEFAULT NULL,
  `Puesto` varchar(50) DEFAULT NULL,
  `activa` int(1) DEFAULT 1,
  PRIMARY KEY (`idTrabajadora`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- Volcando datos para la tabla serviciossocialescitas.trabajadoras: ~2 rows (aproximadamente)
INSERT INTO `trabajadoras` (`idTrabajadora`, `Nombre`, `Puesto`, `activa`) VALUES
	(1, 'Dioni', 'Trabajadora 1', 1),
	(2, 'Silvia', 'Trabajadora 2', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
