-- phpMyAdmin SQL Dump
-- version 5.1.4
-- https://www.phpmyadmin.net/
--
-- Host: mysql-dominioproyectosbbdd.alwaysdata.net
-- Generation Time: Apr 25, 2023 at 09:40 PM
-- Server version: 10.6.11-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dominioproyectosbbdd_mibasedatos`
--
CREATE DATABASE IF NOT EXISTS `dominioproyectosbbdd_mibasedatos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dominioproyectosbbdd_mibasedatos`;

-- --------------------------------------------------------

--
-- Table structure for table `aula`
--

CREATE TABLE `aula` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aula`
--

INSERT INTO `aula` (`id`, `nombre`, `capacidad`) VALUES
(1, 'mi aula', 20),
(2, 'Mi aula super nueva', 50),
(3, 'Adios', 50),
(4, 'Prueba', 23),
(6, 'Mi aula nueva', 50),
(7, 'Dama', 50);

-- --------------------------------------------------------

--
-- Table structure for table `cientificos`
--

CREATE TABLE `cientificos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `fechaNacimiento` varchar(10) NOT NULL,
  `fechaDefuncion` varchar(10) DEFAULT NULL,
  `enlaceMasInformacion` varchar(300) NOT NULL,
  `enlaceFoto` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cientificos`
--

INSERT INTO `cientificos` (`id`, `nombre`, `apellidos`, `nacionalidad`, `fechaNacimiento`, `fechaDefuncion`, `enlaceMasInformacion`, `enlaceFoto`) VALUES
(1, 'Leonhard Paul', 'Euler', 'Suiza', '15/04/1707', '18/09/1783', 'https://es.wikipedia.org/wiki/Leonhard_Euler', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Leonhard_Euler_2.jpg/300px-Leonhard_Euler_2.jpg'),
(2, 'Srinivasa Aiyangar', 'Ramanujan', 'India', '22/12/1887', '26/04/1920', 'https://es.wikipedia.org/wiki/Srinivasa_Ramanujan', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Srinivasa_Ramanujan_-_OPC_-_1.jpg/375px-Srinivasa_Ramanujan_-_OPC_-_1.jpg'),
(3, 'Christian', 'Goldbach', 'Alemania', '18/03/1690', '20/11/1764', 'https://es.wikipedia.org/wiki/Christian_Goldbach', 'https://imagenes.elpais.com/resizer/rKS1tBHRX8ilD4JQUkSgVZdHT2I=/414x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/DRDAVQNTWNFHTBPZF6SYLAABJI.jpg'),
(4, 'Amalie Emmy', 'Noether', 'Alemania', '23/03/1882', '14/04/1935', 'https://es.wikipedia.org/wiki/Emmy_Noether', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/Noether.jpg/330px-Noether.jpg'),
(5, 'Johann Carl Friedrich', 'Gauss', 'Alemania', '30/04/1777', '23/02/1855', 'https://es.wikipedia.org/wiki/Carl_Friedrich_Gauss', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Carl_Friedrich_Gauss_1840_by_Jensen.jpg/330px-Carl_Friedrich_Gauss_1840_by_Jensen.jpg'),
(6, 'Roger', 'Penrose', 'Reino Unido', '08/08/1931', NULL, 'https://es.wikipedia.org/wiki/Roger_Penrose', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Roger_Penrose_at_Festival_della_Scienza_Oct_29_2011.jpg/330px-Roger_Penrose_at_Festival_della_Scienza_Oct_29_2011.jpg'),
(7, 'Pierre', 'De Fermat', 'Francia', '17/08/1601', '12/01/1665', 'https://es.wikipedia.org/wiki/Pierre_de_Fermat', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Pierre_de_Fermat3.jpg/330px-Pierre_de_Fermat3.jpg'),
(8, 'Joseph-Louis', 'De Lagrange', 'Francia', '25/01/1736', '10/04/1813', 'https://es.wikipedia.org/wiki/Joseph-Louis_Lagrange', 'https://upload.wikimedia.org/wikipedia/commons/4/46/%D0%9B%D0%B0%D0%B3%D1%80%D0%B0%D0%BD%D0%B6.jpg'),
(9, 'Georg Friedrich Bernhard', 'Riemann', 'Alemania', '17/09/1826', '20/07/1866', 'https://es.wikipedia.org/wiki/Bernhard_Riemann', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Georg_Friedrich_Bernhard_Riemann.jpeg/330px-Georg_Friedrich_Bernhard_Riemann.jpeg'),
(10, 'Adrien-Marie', 'Legendre', 'Francia', '18/09/1752', '10/01/1833', 'https://es.wikipedia.org/wiki/Adrien-Marie_Legendre', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Louis_Legendre.jpg/220px-Louis_Legendre.jpg'),
(11, 'Alexander', 'Grothendieck', 'Alemania', '28/03/1928', '13/11/2014', 'https://es.wikipedia.org/wiki/Alexander_Grothendieck', 'https://upload.wikimedia.org/wikipedia/commons/e/ef/Alexander_Grothendieck.jpg'),
(12, 'Laurent-Moïse', 'Schwartz', 'Francia', '05/03/1915', '04/07/2002', 'https://hmong.es/wiki/Laurent_Schwartz', 'https://upload.wikimedia.org/wikipedia/commons/5/5c/LaurentSchwartz.jpg'),
(13, 'Jules Henri', 'Poincaré', 'Francia', '29/04/1854', '17/07/1912', 'https://es.wikipedia.org/wiki/Henri_Poincar%C3%A9 ', 'https://upload.wikimedia.org/wikipedia/commons/a/af/JH_Poincare.jpg'),
(14, 'Saharon', 'Shelah', 'Israel', '03/07/1945', NULL, 'https://es.wikipedia.org/wiki/Saharon_Shelah', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Saharon_Shelah_2008.jpg/450px-Saharon_Shelah_2008.jpg'),
(15, 'Andrew', 'Wiles', 'Reino Unido', '11/04/1953', NULL, 'https://es.wikipedia.org/wiki/Andrew_Wiles', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Andrew_wiles1-3.jpg/375px-Andrew_wiles1-3.jpg'),
(16, 'Heinz', 'Hopf', 'Alemania', '19/11/1894', '03/06/1971', 'https://es.wikipedia.org/wiki/Heinz_Hopf', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/ETH-BIB-Hopf%2C_Heinz_%281894-1971%29-Portr_07480.jpg/330px-ETH-BIB-Hopf%2C_Heinz_%281894-1971%29-Portr_07480.jpg'),
(17, 'Kurt', 'Gödel', 'Hungría', '28/04/1906', '14/01/1978', 'https://es.wikipedia.org/wiki/Kurt_G%C3%B6del', 'https://upload.wikimedia.org/wikipedia/commons/6/62/Kurt-godel1.jpg'),
(18, 'Grigori', 'Perelman', 'Rusia', '13/06/1966', NULL, 'https://es.wikipedia.org/wiki/Grigori_Perelm%C3%A1n', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Grigori_Perelman%2C_1993_%28re-scanned%29_%28cropped%29.jpg/330px-Grigori_Perelman%2C_1993_%28re-scanned%29_%28cropped%29.jpg'),
(19, 'Ernst', 'Witt', 'Dinamarca', '26/06/1911', '03/07/1991', 'https://es.wikipedia.org/wiki/Ernst_Witt', 'https://upload.wikimedia.org/wikipedia/commons/9/95/Ernst_Witt.jpeg'),
(20, 'Georges Henry Joseph Édouard', 'Lemaître', 'Bélgica', '17/07/1894', '20/06/1966', 'https://es.wikipedia.org/wiki/Georges_Lema%C3%AEtre', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Lemaitre.jpg/375px-Lemaitre.jpg'),
(21, 'Subrahmanyan', 'Chandrasekhar', 'India', '19/10/1910', '21/08/1995', 'https://es.wikipedia.org/wiki/Subrahmanyan_Chandrasekhar', 'https://upload.wikimedia.org/wikipedia/commons/6/67/Subrahmanyan_Chandrasekhar.gif'),
(22, 'Julius Robert', 'Oppenheimer', 'Estados Unidos', '22/04/1904', '18/02/1967', 'https://es.wikipedia.org/wiki/Robert_Oppenheimer', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/JROppenheimer-LosAlamos.jpg/330px-JROppenheimer-LosAlamos.jpg'),
(23, 'Stephen William', 'Hawking', 'Reino Unido', '08/01/1942', '14/03/2018', 'https://es.wikipedia.org/wiki/Stephen_Hawking', 'https://historia.nationalgeographic.com.es/medio/2021/01/05/captura-de-pantalla-2021-01-05-a-las-111211_c79a53f8_550x594.png'),
(24, 'Theodor Franz Eduard', 'Kaluza', 'Alemania', '09/11/1885', '19/01/1954', 'https://es.wikipedia.org/wiki/Theodor_Kaluza', 'https://mathshistory.st-andrews.ac.uk/Biographies/Kaluza/Kaluza.jpeg'),
(25, 'Oskar Benjamin', 'Klein', 'Suecia', '15/09/1894', '05/02/1977', 'https://es.wikipedia.org/wiki/Oskar_Klein', 'https://upload.wikimedia.org/wikipedia/commons/7/76/Oskar_Klein.jpg'),
(26, 'Walter', 'Gordon', 'Alemania', '03/08/1893', '24/12/1939', 'https://es.wikipedia.org/wiki/Walter_Gordon', 'https://prabook.com/web/show-photo-icon.jpg?id=2601097&width=150&cache=false'),
(27, 'Hermann', 'Minkowski', 'Alemania', '22/06/1864', '12/01/1909', 'https://es.wikipedia.org/wiki/Hermann_Minkowski', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/De_Raum_zeit_Minkowski_Bild.jpg/300px-De_Raum_zeit_Minkowski_Bild.jpg'),
(28, 'John Forbes', 'Nash', 'Estados Unidos', '13/06/1928', '23/05/2015', 'https://es.wikipedia.org/wiki/John_Forbes_Nash', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/John_Forbes_Nash%2C_Jr._by_Peter_Badge.jpg/330px-John_Forbes_Nash%2C_Jr._by_Peter_Badge.jpg'),
(29, 'Évariste', 'Galois', 'Francia', '25/10/1811', '31/05/1832', 'https://es.wikipedia.org/wiki/%C3%89variste_Galois', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Evariste_Galois.jpg/330px-Evariste_Galois.jpg'),
(30, 'Ronald Aylmer', 'Fisher', 'Reino Unido', '17/02/1890', '29/07/1962', 'https://es.wikipedia.org/wiki/Ronald_Fisher', 'https://upload.wikimedia.org/wikipedia/commons/2/21/RonaldFisher1912.jpg'),
(31, 'Johannes', 'Kepler', 'Alemania', '27/12/1571', '15/11/1630', 'https://es.wikipedia.org/wiki/Johannes_Kepler', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/JKepler.jpg/375px-JKepler.jpg'),
(32, 'Sir Isaac', 'Newton', 'Reino Unido', '25/12/1642', '20/03/1727', 'https://es.wikipedia.org/wiki/Isaac_Newton', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Portrait_of_Sir_Isaac_Newton%2C_1689.jpg/330px-Portrait_of_Sir_Isaac_Newton%2C_1689.jpg'),
(33, 'James Clerk', 'Maxwell', 'Reino Unido', '13/06/1831', '05/11/1879', 'https://es.wikipedia.org/wiki/James_Clerk_Maxwell', 'https://upload.wikimedia.org/wikipedia/commons/5/57/James_Clerk_Maxwell.png'),
(34, 'Georg Simon', 'Ohm', 'Alemania', '16/03/1789', '06/07/1854', 'https://es.wikipedia.org/wiki/Georg_Simon_Ohm', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Georg_Simon_Ohm3.jpg/330px-Georg_Simon_Ohm3.jpg'),
(35, 'Karl', 'Schwarzschild', 'Alemania', '09/10/1873', '11/05/1916', 'https://es.wikipedia.org/wiki/Karl_Schwarzschild', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Schwarzschild.jpg/330px-Schwarzschild.jpg'),
(36, 'Hendrik Antoon', 'Lorentz', 'Países Bajos', '18/07/1853', '04/02/1928', 'https://es.wikipedia.org/wiki/Hendrik_Antoon_Lorentz', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hendrik_Antoon_Lorentz.jpg/300px-Hendrik_Antoon_Lorentz.jpg'),
(37, 'Paul Adrien Maurice', 'Dirac', 'Reino Unido', '08/09/1902', '20/10/1984', 'https://es.wikipedia.org/wiki/Paul_Dirac', 'https://upload.wikimedia.org/wikipedia/commons/8/8a/Paul_Dirac%2C_1933%2C_head_and_shoulders_portrait%2C_bw.jpg'),
(38, 'Erwin', 'Schrödinger', 'Austria', '12/09/1887', '04/01/1961', 'https://es.wikipedia.org/wiki/Erwin_Schr%C3%B6dinger', 'https://upload.wikimedia.org/wikipedia/commons/2/2e/Erwin_Schr%C3%B6dinger_%281933%29.jpg'),
(39, 'Albert', 'Einstein', 'Alemania', '14/03/1879', '18/04/1955', 'https://es.wikipedia.org/wiki/Albert_Einstein', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Albert_Einstein_Head.jpg/330px-Albert_Einstein_Head.jpg'),
(40, 'Gottfried Wilhelm', 'Leibniz', 'Alemania', '01/07/1646', '14/11/1716', 'https://es.wikipedia.org/wiki/Gottfried_Leibniz', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Gottfried_Wilhelm_Leibniz%2C_Bernhard_Christoph_Francke.jpg/330px-Gottfried_Wilhelm_Leibniz%2C_Bernhard_Christoph_Francke.jpg'),
(41, 'Wilhelm', 'Jordan', 'Alemania', '01/03/1842', '17/04/1899', 'https://es.wikipedia.org/wiki/Wilhelm_Jordan', 'https://upload.wikimedia.org/wikipedia/commons/7/70/Wilhelm_Jordan.png'),
(42, 'Henry', 'Cavendish', 'Francia', '10/10/1731', '24/02/1810', 'https://es.wikipedia.org/wiki/Henry_Cavendish', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Cavendish_Henry_signature.jpg/330px-Cavendish_Henry_signature.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `cientifico_contribucion`
--

CREATE TABLE `cientifico_contribucion` (
  `id` int(11) NOT NULL,
  `fkContribucion` int(11) NOT NULL,
  `fkCientifico` int(11) NOT NULL,
  `epoca` varchar(4) NOT NULL,
  `aportacion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cientifico_contribucion`
--

INSERT INTO `cientifico_contribucion` (`id`, `fkContribucion`, `fkCientifico`, `epoca`, `aportacion`) VALUES
(1, 1, 1, '1730', 'Definición'),
(2, 2, 1, '1750', 'Método'),
(3, 2, 8, '1787', 'Método'),
(4, 3, 1, '1736', 'Teorema'),
(5, 4, 1, '1775', 'Teorema'),
(6, 5, 1, '1767', 'Teorema'),
(7, 6, 1, '1769', 'Conjetura'),
(8, 7, 1, '1729', 'Definición'),
(9, 8, 1, '1769', 'Definición'),
(10, 9, 1, '1765', 'Teorema'),
(11, 10, 1, '1737', 'Definición'),
(12, 11, 1, '1736', 'Demostración'),
(13, 11, 7, '1636', 'Teorema'),
(14, 11, 40, '1683', 'Demostración'),
(15, 11, 5, '1801', 'Demostración'),
(16, 12, 1, '1735', 'Método'),
(17, 13, 1, '1775', 'Teorema'),
(18, 13, 7, '1640', 'Teorema'),
(19, 13, 8, '1775', 'Teorema'),
(20, 13, 5, '1798', 'Teorema'),
(21, 14, 1, '1749', 'Teorema'),
(22, 14, 8, '1770', 'Teorema'),
(23, 14, 10, '1797', 'Teorema'),
(24, 15, 1, '1739', 'Teorema'),
(25, 15, 5, '1792', 'Conjetura'),
(26, 15, 10, '1798', 'Conjetura'),
(27, 16, 1, '1736', 'Solución'),
(28, 17, 1, '1737', 'Definición'),
(29, 17, 9, '1850', 'Definición'),
(30, 18, 1, '1750', 'Teorema'),
(31, 19, 1, '1765', 'Definición'),
(32, 20, 1, '1748', 'Teorema'),
(33, 20, 3, '1750', 'Método'),
(34, 21, 2, '1913', 'Definición'),
(35, 22, 2, '1913', 'Definición'),
(36, 23, 2, '1913', 'Definición'),
(37, 24, 2, '1919', 'Definición'),
(38, 25, 2, '1913', 'Definición'),
(39, 26, 2, '1910', 'Definición'),
(40, 27, 2, '1916', 'Definición'),
(41, 28, 3, '1742', 'Definición'),
(42, 29, 4, '1916', 'Teorema'),
(43, 30, 5, '1835', 'Ley'),
(44, 31, 5, '1848', 'Teorema'),
(45, 32, 5, '1815', 'Método'),
(46, 33, 5, '1816', 'Teorema'),
(47, 34, 5, '1798', 'Teorema'),
(48, 35, 6, '2010', 'Modelo'),
(49, 36, 6, '1969', 'Hipótesis'),
(50, 37, 6, '1965', 'Teorema'),
(51, 38, 6, '1970', 'Teorema'),
(52, 38, 23, '1970', 'Teorema'),
(53, 39, 7, '1636', 'Definición'),
(54, 40, 7, '1636', 'Definición'),
(55, 43, 7, '1662', 'Principio'),
(56, 44, 40, '1745', 'Principio'),
(57, 45, 40, '1740', 'Teoría'),
(58, 46, 7, '1636', 'Teorema'),
(59, 47, 1, '1750', 'Método'),
(60, 47, 8, '1767', 'Método'),
(61, 48, 1, '1733', 'Teorema'),
(62, 49, 8, '1767', 'Método'),
(63, 49, 1, '1772', 'Método'),
(64, 49, 32, '1687', 'Método'),
(65, 49, 13, '1889', 'Solución'),
(66, 50, 8, '1772', 'Definición'),
(67, 51, 8, '1795', 'Método'),
(68, 52, 8, '1771', 'Método'),
(69, 53, 8, '1771', 'Teorema'),
(70, 54, 9, '1854', 'Teoría'),
(71, 55, 9, '1850', 'Definición'),
(72, 56, 9, '1854', 'Definición'),
(73, 57, 9, '1859', 'Definición'),
(74, 58, 9, '1854', 'Definición'),
(75, 59, 9, '1862', 'Definición'),
(76, 60, 9, '1854', 'Lema'),
(77, 46, 10, '1825', 'Demostración'),
(78, 62, 10, '1798', 'Teorema'),
(79, 62, 5, '1795', 'Teorema'),
(80, 15, 10, '1808', 'Teorema'),
(81, 63, 10, '1785', 'Definición'),
(82, 64, 12, '1950', 'Demostración'),
(83, 64, 11, '1952', 'Teorema'),
(84, 65, 11, '1964', 'Definición'),
(85, 66, 12, '1949', 'Definición'),
(86, 67, 12, '1973', 'Definición'),
(87, 68, 12, '1973', 'Modelo'),
(88, 69, 14, '1980', 'Teoría'),
(89, 70, 14, '1978', 'Teoría'),
(90, 46, 15, '1995', 'Demostración'),
(91, 71, 13, '1904', 'Modelo'),
(92, 71, 18, '2006', 'Demostración'),
(93, 72, 13, '1889', 'Método'),
(94, 73, 39, '1904', 'Teoría'),
(95, 74, 16, '1926', 'Teorema'),
(96, 74, 13, '1904', 'Modelo'),
(97, 75, 16, '1931', 'Definición'),
(98, 76, 16, '1931', 'Método'),
(99, 77, 13, '1893', 'Teorema'),
(100, 78, 19, '1937', 'Teorema'),
(101, 79, 13, '1885', 'Teorema'),
(102, 80, 36, '1963', 'Modelo'),
(103, 81, 13, '1903', 'Teoría'),
(104, 82, 13, '1952', 'Modelo'),
(105, 83, 20, '1931', 'Modelo'),
(106, 84, 22, '1942', 'Método'),
(107, 85, 22, '1939', 'Definición'),
(108, 86, 21, '1930', 'Definición'),
(109, 87, 22, '1950', 'Modelo'),
(110, 88, 23, '1976', 'Teoría'),
(111, 89, 23, '1992', 'Teoría'),
(112, 90, 17, '1931', 'Teorema'),
(113, 91, 17, '1931', 'Definición'),
(114, 92, 17, '1938', 'Modelo'),
(115, 93, 24, '1926', 'Teoría'),
(116, 93, 25, '1926', 'Teoría'),
(117, 94, 25, '1926', 'Teoría'),
(118, 95, 23, '1971', 'Teorema'),
(119, 96, 27, '1870', 'Teorema'),
(120, 98, 27, '1880', 'Método'),
(121, 99, 27, '1882', 'Definición'),
(122, 100, 27, '1907', 'Método'),
(123, 101, 28, '1951', 'Teoría'),
(124, 102, 29, '1832', 'Teoría'),
(125, 103, 30, '1955', 'Teorema'),
(126, 104, 30, '1928', 'Teorema'),
(127, 105, 31, '1609', 'Ley'),
(128, 106, 31, '1609', 'Ley'),
(129, 107, 31, '1619', 'Ley'),
(130, 108, 32, '1687', 'Ley'),
(131, 109, 32, '1687', 'Ley'),
(132, 110, 32, '1704', 'Teoría'),
(133, 111, 32, '1665', 'Teorema'),
(134, 112, 33, '1864', 'Modelo'),
(135, 113, 33, '1864', 'Paradigma'),
(136, 114, 33, '1864', 'Paradigma'),
(137, 115, 34, '1827', 'Ley'),
(138, 117, 35, '1916', 'Ecuación'),
(139, 118, 35, '1916', 'Método'),
(140, 119, 36, '1904', 'Modelo'),
(141, 120, 37, '1928', 'Ecuación'),
(142, 121, 37, '1930', 'Definición'),
(143, 122, 37, '1926', 'Método'),
(144, 123, 37, '1926', 'Definición'),
(145, 124, 37, '1938', 'Ecuación'),
(146, 124, 36, '1938', 'Ecuación'),
(147, 125, 37, '1930', 'Teoría'),
(148, 126, 37, '1937', 'Paradigma'),
(149, 127, 37, '1933', 'Definición'),
(150, 128, 38, '1925', 'Ecuación'),
(151, 129, 38, '1935', 'Modelo'),
(152, 130, 38, '1926', 'Modelo'),
(153, 131, 39, '1905', 'Teoría'),
(154, 132, 39, '1905', 'Solución'),
(155, 133, 39, '1915', 'Teoría'),
(156, 136, 39, '1924', 'Modelo'),
(157, 137, 5, '1823', 'Definición'),
(158, 138, 5, '1828', 'Teorema'),
(159, 139, 41, '1873', 'Método'),
(160, 139, 5, '1829', 'Método'),
(161, 140, 5, '1835', 'Teorema'),
(162, 141, 5, '1801', 'Solución'),
(163, 142, 5, '1827', 'Lema'),
(164, 142, 9, '1854', 'Lema'),
(165, 143, 5, '1823', 'Teorema'),
(166, 144, 1, '1750', 'Teorema'),
(167, 144, 13, '1893', 'Proposición'),
(168, 134, 39, '1915', 'Ecuación'),
(169, 135, 39, '1915', 'Paradigma'),
(170, 27, 2, '1916', 'Definición'),
(171, 28, 3, '1742', 'Conjetura'),
(172, 97, 27, '1889', 'Teorema'),
(173, 116, 42, '1798', 'Experimento'),
(174, 9, 37, '1212', 'Experimento'),
(175, 40, 33, '1730', 'Definición');

-- --------------------------------------------------------

--
-- Table structure for table `contribuciones`
--

CREATE TABLE `contribuciones` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `campo` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `rama` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `enlaceMasInformacion` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contribuciones`
--

INSERT INTO `contribuciones` (`id`, `nombre`, `campo`, `rama`, `enlaceMasInformacion`) VALUES
(1, 'Función Exponencial', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_exponencial'),
(2, 'Ecuaciones de Euler-Lagrange', 'Matemáticas', 'Cálculo', 'https://es.wikipedia.org/wiki/Ecuaciones_de_Euler-Lagrange'),
(3, 'Teorema de Euler', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_de_Euler'),
(4, 'Teorema de Rotación de Euler', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Teorema_de_rotaci%C3%B3n_de_Euler'),
(5, 'Teorema geométrico de Euler', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Teorema_geom%C3%A9trico_de_Euler'),
(6, 'Conjetura de Euler', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Conjetura_de_Euler'),
(7, 'Función gamma de Euler', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_gamma'),
(8, 'Constante de Euler-Mascheroni', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Constante_de_Euler-Mascheroni'),
(9, 'Circunferencia de los nueve puntos', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Circunferencia_de_los_nueve_puntos'),
(10, 'Funciones Trascendentes', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_trascendente'),
(11, 'Pequeño teorema de Fermat', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Peque%C3%B1o_teorema_de_Fermat'),
(12, 'Problema de basilea', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Problema_de_Basilea'),
(13, 'Teorema de Fermat sobre la suma de dos cuadrados', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_de_Fermat_sobre_la_suma_de_dos_cuadrados'),
(14, 'Teorema de los cuatro cuadrados', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_de_los_cuatro_cuadrados'),
(15, 'Teorema de los números primos', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_de_los_n%C3%BAmeros_primos'),
(16, 'Problema de los puentes de Königsberg', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Problema_de_los_puentes_de_K%C3%B6nigsberg'),
(17, 'Zeta de Riemann', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_zeta_de_Riemann'),
(18, 'Teorema de Euler para poliedros', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Teorema_de_Euler_para_poliedros'),
(19, 'Recta de Euler', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Recta_de_Euler'),
(20, 'Fórmula de Euler', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/F%C3%B3rmula_de_Euler#:~:text=Euler%20desarroll%C3%B3%20la%20f%C3%B3rmula%20utilizando,in%20analysin%20infinitorum%20en%20'),
(21, 'Constante de Landau-Ramanujan', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Constante_de_Landau-Ramanujan'),
(22, 'Identidades de Rogers-Ramanuman', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Identidades_de_Rogers-Ramanujan'),
(23, 'Ecuación de Ramanujan-Nagell', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_Ramanujan%E2%80%93Nagell'),
(24, 'Número primo de Ramanujan', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/N%C3%BAmero_primo_de_Ramanujan'),
(25, 'Sumatorio de Ramanujan', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Sumatorio_de_Ramanujan'),
(26, 'Grafo de Ramanujan', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Grafo_de_Ramanujan'),
(27, 'Forma cuadrática ternaria de Ramanujan', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Forma_cuadr%C3%A1tica_ternaria_de_Ramanujan'),
(28, 'Conjetura de Goldbach', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Conjetura_de_Goldbach'),
(29, 'Teorema de Noether', 'Física', 'Mecánica Teórica', 'https://es.wikipedia.org/wiki/Teorema_de_Noether'),
(30, 'Ley de Gauss', 'Física', 'Electromagnetismo', 'https://es.wikipedia.org/wiki/Ley_de_Gauss'),
(31, 'Teorema de Gauss-Bonnet', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Teorema_de_Gauss-Bonnet#:~:text=Se%20nombra%20por%20Carl%20Friedrich,un%20caso%20especial%20en%20'),
(32, 'Cuadratura de Gauss', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Cuadratura_de_Gauss'),
(33, 'Teorema fundamental del álgebra', 'Matemáticas', 'Álgebra', 'https://es.wikipedia.org/wiki/Teorema_fundamental_del_%C3%A1lgebra'),
(34, 'Teorema fundamental de la aritmética', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_fundamental_de_la_aritm%C3%A9tica'),
(35, 'Cosmología cíclica conforme', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/Cosmolog%C3%ADa_c%C3%ADclica_conforme'),
(36, 'Hipótesis de censura cósmica', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/Hip%C3%B3tesis_de_censura_c%C3%B3smica'),
(37, 'Teorema de singularidad', 'Física', 'Cosmología', 'https://francis.naukas.com/2020/10/12/el-teorema-de-singularidad-de-penrose-de-1965/'),
(38, 'Teorema de singularidad de Penrose-Hawking', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/Singularidad_gravitacional'),
(39, 'Espiral de Fermat', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Pierre_de_Fermat#Espiral_de_Fermat'),
(40, 'Números amigos', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Pierre_de_Fermat#N%C3%BAmeros_amigos'),
(43, 'Principio de Fermat', 'Física', 'Óptica', 'https://es.wikipedia.org/wiki/Principio_de_Fermat'),
(44, 'Principio de mínima acción', 'Física', 'Mecánica', 'https://es.wikipedia.org/wiki/Principio_de_m%C3%ADnima_acci%C3%B3n'),
(45, 'Serie de Leibniz', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Serie_de_Leibniz'),
(46, 'Último teorema de Fermat', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Pierre_de_Fermat#%C3%9Altimo_teorema_de_Fermat'),
(47, 'Problema Isoperimétrico', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Isoperimetr%C3%ADa'),
(48, 'Cálculo de variaciones', 'Matemáticas', 'Cálculo', 'https://es.wikipedia.org/wiki/C%C3%A1lculo_de_variaciones'),
(49, 'Problema de n-cuerpos', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Problema_de_los_n_cuerpos'),
(50, 'Puntos de Lagrange', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Puntos_de_Lagrange'),
(51, 'Polinomio de Lagrange', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Interpolaci%C3%B3n_polin%C3%B3mica_de_Lagrange'),
(52, 'Multiplicadores de Lagrange', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Multiplicadores_de_Lagrange'),
(53, 'Teorema de Lagrange', 'Matemáticas', 'Teoría de grupos', 'https://es.wikipedia.org/wiki/Teorema_de_Lagrange_(teor%C3%ADa_de_grupos)'),
(54, 'Geometría de Riemann', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Geometr%C3%ADa_de_Riemann'),
(55, 'Superficie de Riemann', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Superficie_de_Riemann'),
(56, 'Integral de Riemann', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Integral_de_Riemann'),
(57, 'Función Zeta', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_zeta_de_Riemann'),
(58, 'Variedad de Riemann', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Variedad_de_Riemann'),
(59, 'Tensor métrico', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Tensor_m%C3%A9trico'),
(60, 'Lema de Riemann-Lebesgue', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Lema_de_Riemann-Lebesgue'),
(62, 'Ley de reciprocidad cuadrática', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Ley_de_reciprocidad_cuadr%C3%A1tica'),
(63, 'Polinomios de Legendre', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Polinomios_de_Legendre'),
(64, 'Teorema del núcleo de Schwartz', 'Matemáticas', 'Análisis', 'https://hmong.es/wiki/Schwartz_kernel_theorem'),
(65, 'Grupo de grothendiech', 'Matemáticas', 'Geometría', 'https://dialnet.unirioja.es/descarga/articulo/6171163.pdf'),
(66, 'Teoría de distribuciones', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_de_distribuciones'),
(67, 'Espacio de Schwartz', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Espacio_de_Schwartz'),
(68, 'Probabilidades cilíndricas', 'Matemáticas', 'Geometría', 'https://rac.es/ficheros/doc/01208.pdf'),
(69, 'Teoría de clasificación', 'Matemáticas', 'Álgebra', 'https://revistas.unal.edu.co/index.php/bolma/article/download/18233/19140/58969'),
(70, 'Teoría PCF', 'Matemáticas', 'Teoría de conjuntos', 'https://en.wikipedia.org/wiki/PCF_theory'),
(71, 'Conjetura de Poincaré', 'Matemáticas', 'Topología', 'https://es.wikipedia.org/wiki/Hip%C3%B3tesis_de_Poincar%C3%A9'),
(72, 'Problema de los tres cuerpos', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Problema_de_los_tres_cuerpos'),
(73, 'Teoría de la relatividad', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_de_la_relatividad_especial'),
(74, 'Teorema de Poincaré-Hopf', 'Matemáticas', 'Topología', 'https://en.wikipedia.org/wiki/Poincar%C3%A9%E2%80%93Hopf_theorem'),
(75, 'Eslabón de hopf', 'Matemáticas', 'Teoría de nudos', 'https://es.wikipedia.org/wiki/Eslab%C3%B3n_de_Hopf'),
(76, 'Fibración de Hopf', 'Matemáticas', 'Topología', 'https://es.wikipedia.org/wiki/Fibraci%C3%B3n_de_Hopf'),
(77, 'Dualidad de Poincaré', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Dualidad_de_Poincar%C3%A9'),
(78, 'Teorema de Poincaré-Birkhoff-Witt', 'Matemáticas', 'Álgebra', 'https://en.wikipedia.org/wiki/Poincar%C3%A9%E2%80%93Birkhoff%E2%80%93Witt_theorem'),
(79, 'Número de rotación', 'Física', 'Dinámica', 'https://en.wikipedia.org/wiki/Rotation_number'),
(80, 'Atractor de Lorentz', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Atractor_de_Lorenz'),
(81, 'Teoría del Caos', 'Física', 'Teoría del caos', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_del_caos#El_cuestionamiento_de_Poincar%C3%A9'),
(82, 'Esfera-Mundo', 'Matemáticas', 'Geometría', 'https://en.wikipedia.org/wiki/Sphere-world'),
(83, 'Átomo primitivo', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Hip%C3%B3tesis_del_%C3%A1tomo_primigenio'),
(84, 'Cálculo de neutrones rápidos sobre fisión', 'Física', 'Física de partículas', 'https://es.wikipedia.org/wiki/Temperatura_neutr%C3%B3nica'),
(85, 'Límite de Tolman-Oppenheimer-Volkoff', 'Física', 'Astrofísica', 'https://es.wikipedia.org/wiki/L%C3%ADmite_de_Tolman-Oppenheimer-Volkoff'),
(86, 'Límite de Chandrasekhar', 'Física', 'Astrofísica', 'https://es.wikipedia.org/wiki/L%C3%ADmite_de_Chandrasekhar'),
(87, 'Aproximación de Born-Oppenheimer', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Aproximaci%C3%B3n_de_Born-Oppenheimer'),
(88, 'Radiación de Hawking', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Radiaci%C3%B3n_de_Hawking'),
(89, 'Teoría del Big Bang', 'Física', 'Astrofísica', 'https://historia.nationalgeographic.com.es/a/stephen-hawking-y-origen-universo_16059#:~:text=Hawking%20estaba%20convencido%20de%20que,una%20vez%20ha%20sido%20atrapada'),
(90, 'Teoremas de incompletitud de Gödel', 'Matemáticas', 'Lógica', 'https://es.wikipedia.org/wiki/Teoremas_de_incompletitud_de_G%C3%B6del'),
(91, 'Numeración de Gödel', 'Matemáticas', 'Análisis', 'https://es.wikipedia.org/wiki/Numeraci%C3%B3n_de_G%C3%B6del'),
(92, 'Universo constructible', 'Matemáticas', 'Teoría de conjuntos', 'https://es.wikipedia.org/wiki/Universo_constructible'),
(93, 'Teoría de Kaluza-Klein', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_de_Kaluza-Klein#Teor%C3%ADa_original_de_Kaluza-Klein'),
(94, 'Ecuación de Klein-Gordon', 'Física', 'Física cuántica de campos', 'https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_Klein-Gordon'),
(95, 'Entropía de horizontes', 'Física', 'Cosmología', 'https://www.muyinteresante.es/ciencia/29541.html'),
(96, 'Desigualdad triangular', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Desigualdad_triangular'),
(97, 'Teorema de Minkowski', 'Matemáticas', 'Teoría de conjuntos', 'https://es.wikipedia.org/wiki/Teorema_de_Minkowski'),
(98, 'Dimensión de Minkowski-Bouligand', 'Matemáticas', 'Cálculo', 'https://es.wikipedia.org/wiki/Dimensi%C3%B3n_de_Minkowski-Bouligand'),
(99, 'Suma de Minkowski', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Suma_de_Minkowski'),
(100, 'Métrica de Minkowski', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Espacio-tiempo_de_Minkowski'),
(101, 'Equilibrio de Nash', 'Matemáticas', 'Teoría de juegos', 'https://es.wikipedia.org/wiki/Equilibrio_de_Nash'),
(102, 'Teoría de Galois', 'Matemáticas', 'Álgebra', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_de_Galois'),
(103, 'Principio de Fisher', 'Biología', 'Genética', 'https://es.wikipedia.org/wiki/Principio_de_Fisher'),
(104, 'Teorema de valores extremos', 'Matemáticas', 'Estadística', 'https://es.wikipedia.org/wiki/Teorema_de_Fisher-Tippett-Gnedenko'),
(105, 'Primera Ley de Kepler', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Leyes_de_Kepler'),
(106, 'Segunda Ley de Kepler', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Leyes_de_Kepler'),
(107, 'Tercera Ley de Kepler', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Leyes_de_Kepler'),
(108, 'Leyes de la dinámica', 'Física', 'Dinámica', 'https://es.wikipedia.org/wiki/Din%C3%A1mica'),
(109, 'Gravitación Universal', 'Física', 'Mecánica Clásica', 'https://es.wikipedia.org/wiki/Ley_de_gravitaci%C3%B3n_universal'),
(110, 'Teoría corpuscular de la luz', 'Física', 'Óptica', 'http://rsefalicante.umh.es/TemasLuz/Luz03.htm'),
(111, 'Teorema del binomio', 'Matemáticas', 'Teoría de Números', 'https://es.wikipedia.org/wiki/Teorema_del_binomio'),
(112, 'Ecuaciones de Maxwell', 'Física', 'Electromagnetismo', 'http://www2.ulpgc.es/hege/almacen/download/40/40499/tema1.pdf'),
(113, 'Concepción de luz como onda', 'Física', 'Electromagnetismo', 'https://es.wikipedia.org/wiki/James_Clerk_Maxwell#Legado_cient%C3%ADfico'),
(114, 'Teoría del control', 'Ingeniería', 'Sistemas dinámicos', 'https://es.wikipedia.org/wiki/Teor%C3%ADa_del_control'),
(115, 'Ley de Ohm', 'Física', 'Electromagnetismo', 'https://es.wikipedia.org/wiki/Georg_Simon_Ohm#Descubrimientos'),
(116, 'Experimento de Cavendish', 'Física', 'Cálculo', 'https://es.wikipedia.org/wiki/Henry_Cavendish'),
(117, 'Radio de Schwarzschild', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/Radio_de_Schwarzschild'),
(118, 'Métrica de Schwarzschild', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/M%C3%A9trica_de_Schwarzschild'),
(119, 'Transformadas de Lorentz', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Transformaci%C3%B3n_de_Lorentz'),
(120, 'Ecuación de Dirac', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_Dirac'),
(121, 'Delta de Dirac', 'Matemáticas', 'Estadística', 'https://es.wikipedia.org/wiki/Delta_de_Dirac'),
(122, 'Estadística de Fermi-Dirac', 'Física', 'Física Estadística', 'https://es.wikipedia.org/wiki/Estad%C3%ADstica_de_Fermi-Dirac'),
(123, 'Fermión de Dirac', 'Física', 'Física de partículas', 'https://es.wikipedia.org/wiki/Fermi%C3%B3n_de_Dirac'),
(124, 'Fuerza de Abraham-Lorentz-Dirac', 'Física', 'Electromagnetismo', 'https://es.wikipedia.org/wiki/Fuerza_de_Abraham-Lorentz-Dirac'),
(125, 'Mar de Dirac', 'Física', 'Mecánica Cuántica Relativista', 'https://es.wikipedia.org/wiki/Mar_de_Dirac'),
(126, 'Hipótesis de los grandes números', 'Física', 'Cosmología', 'https://es.wikipedia.org/wiki/Hip%C3%B3tesis_de_los_grandes_n%C3%BAmeros_de_Dirac'),
(127, 'Efecto Kapitsa-Dirac', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Efecto_Kapitsa-Dirac'),
(128, 'Ecuación de Schrödinger', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_Schr%C3%B6dinger'),
(129, 'Gato de Schrödinger', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Gato_de_Schr%C3%B6dinger'),
(130, 'Modelo atómico de Schrödinger', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Modelo_at%C3%B3mico_de_Schr%C3%B6dinger'),
(131, 'Teoría de cuantos de luz', 'Física', 'Mecánica Cuántica', 'htpps://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(132, 'Movimiento browniano', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(133, 'Relatividad especial', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(134, 'Equivalencia masa-energía', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(135, 'Relatividad general', 'Física', 'Relatividad', 'https://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(136, 'Estadística de Bose-Einstein', 'Física', 'Mecánica Cuántica', 'https://es.wikipedia.org/wiki/Albert_Einstein#Los_art%C3%ADculos_de_1905'),
(137, 'Teoría de combinación de observaciones', 'Matemáticas', 'Estadística', 'https://es.wikipedia.org/wiki/Funci%C3%B3n_gaussiana'),
(138, 'Teorema Egregium', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Theorema_egregium#:~:text=Gauss%20formul%C3%B3%20el%20teorema%20(traducido,en%20cada%20punto%20permanece%20inalterada'),
(139, 'Método de eliminación de Gauss-Jordan', 'Matemáticas', 'Álgebra', 'https://es.wikipedia.org/wiki/Eliminaci%C3%B3n_de_Gauss-Jordan'),
(140, 'Teorema de la divergencia', 'Matemáticas', 'Cálculo', 'https://es.wikipedia.org/wiki/Teorema_de_la_divergencia'),
(141, 'Curvatura gaussiana', 'Matemáticas', 'Geometría', 'https://es.wikipedia.org/wiki/Curvatura_de_Gauss'),
(142, 'Lema de Gauss-Riemann', 'Matemáticas', 'Geometría', 'https://en.wikipedia.org/wiki/Gauss%27s_lemma_(Riemannian_geometry)'),
(143, 'Inecuación de Gauss', 'Matemáticas', 'Estadística', 'https://en.wikipedia.org/wiki/Gauss%27s_inequality'),
(144, 'Característica de Euler-Poincaré', 'Matemáticas', 'Topología', 'https://es.wikipedia.org/wiki/Caracter%C3%ADstica_de_Euler');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `password`) VALUES
(7, 'usuario1', 'contraseña12D'),
(8, 'usuario2', 'contraseña23D'),
(9, 'usuario3', 'contraseña12D');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_cientifico`
--

CREATE TABLE `usuario_cientifico` (
  `id` int(11) NOT NULL,
  `fkUsuario` int(11) NOT NULL,
  `fkCientifico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario_cientifico`
--

INSERT INTO `usuario_cientifico` (`id`, `fkUsuario`, `fkCientifico`) VALUES
(34, 7, 10),
(37, 7, 5),
(38, 7, 14),
(39, 8, 3),
(40, 8, 1),
(41, 8, 9),
(42, 8, 14),
(43, 9, 2),
(44, 9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_contribucion`
--

CREATE TABLE `usuario_contribucion` (
  `id` int(11) NOT NULL,
  `fkUsuario` int(11) NOT NULL,
  `fkContribucion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario_contribucion`
--

INSERT INTO `usuario_contribucion` (`id`, `fkUsuario`, `fkContribucion`) VALUES
(32, 7, 8),
(34, 8, 2),
(36, 8, 8),
(37, 8, 14),
(38, 8, 17),
(39, 9, 2),
(41, 9, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cientificos`
--
ALTER TABLE `cientificos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cientifico_contribucion`
--
ALTER TABLE `cientifico_contribucion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cientifico_contribucion_ibfk_1` (`fkCientifico`),
  ADD KEY `cientifico_contribucion_ibfk_2` (`fkContribucion`);

--
-- Indexes for table `contribuciones`
--
ALTER TABLE `contribuciones`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario_cientifico`
--
ALTER TABLE `usuario_cientifico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_cientifico_ibfk_1` (`fkUsuario`),
  ADD KEY `usuario_cientifico_ibfk_2` (`fkCientifico`);

--
-- Indexes for table `usuario_contribucion`
--
ALTER TABLE `usuario_contribucion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_contribucion_ibfk_1` (`fkUsuario`),
  ADD KEY `usuario_contribucion_ibfk_2` (`fkContribucion`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aula`
--
ALTER TABLE `aula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cientificos`
--
ALTER TABLE `cientificos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `cientifico_contribucion`
--
ALTER TABLE `cientifico_contribucion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;

--
-- AUTO_INCREMENT for table `contribuciones`
--
ALTER TABLE `contribuciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `usuario_cientifico`
--
ALTER TABLE `usuario_cientifico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `usuario_contribucion`
--
ALTER TABLE `usuario_contribucion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cientifico_contribucion`
--
ALTER TABLE `cientifico_contribucion`
  ADD CONSTRAINT `cientifico_contribucion_ibfk_1` FOREIGN KEY (`fkCientifico`) REFERENCES `cientificos` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `cientifico_contribucion_ibfk_2` FOREIGN KEY (`fkContribucion`) REFERENCES `contribuciones` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `usuario_cientifico`
--
ALTER TABLE `usuario_cientifico`
  ADD CONSTRAINT `usuario_cientifico_ibfk_1` FOREIGN KEY (`fkUsuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_cientifico_ibfk_2` FOREIGN KEY (`fkCientifico`) REFERENCES `cientificos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuario_contribucion`
--
ALTER TABLE `usuario_contribucion`
  ADD CONSTRAINT `usuario_contribucion_ibfk_1` FOREIGN KEY (`fkUsuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_contribucion_ibfk_2` FOREIGN KEY (`fkContribucion`) REFERENCES `contribuciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
