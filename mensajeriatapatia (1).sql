-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-06-2024 a las 03:39:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mensajeriatapatia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinatario`
--

CREATE TABLE `destinatario` (
  `idDestinatario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `destinatario`
--

INSERT INTO `destinatario` (`idDestinatario`, `nombre`, `direccion`) VALUES
(14, 'fedez', 'ha'),
(15, 'Paola', 'Tabachines');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `idMensaje` int(11) NOT NULL,
  `emisor` varchar(50) NOT NULL,
  `destinatario` varchar(50) NOT NULL,
  `mensajero` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `contenido` varchar(50) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`idMensaje`, `emisor`, `destinatario`, `mensajero`, `tipo`, `contenido`, `fecha`) VALUES
(1, '', '0', '0', 'Mensaje', 'hola', '0000-00-00 00:00:00'),
(2, '', '14', '1', 'Paquete', 'hola', '0000-00-00 00:00:00'),
(3, '', '14', '1', 'Mensaje', 'va', '0000-00-00 00:00:00'),
(4, '', '14', '1', 'Paquete', 'Rosa pastel pero no de belanova', '2024-06-14 18:03:05'),
(5, '', '15', '3', 'Paquete', 'Fragil', '2024-06-14 18:33:44');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajero`
--

CREATE TABLE `mensajero` (
  `idMensajero` int(11) NOT NULL,
  `empresa` varchar(50) NOT NULL,
  `vehiculo` varchar(50) NOT NULL,
  `valoraciones` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajero`
--

INSERT INTO `mensajero` (`idMensajero`, `empresa`, `vehiculo`, `valoraciones`) VALUES
(2, 'DHL', 'Van', 4.5),
(3, 'Moovit', 'Coche', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(50) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Edad` int(10) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Contrasena` varchar(50) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `Nombre`, `Edad`, `Correo`, `Contrasena`, `isAdmin`) VALUES
(1, 'Erick', 20, 'erick556@gmail.com', '123', 0),
(7, 'charlie', 20, 'c@gmail.com', '123', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `destinatario`
--
ALTER TABLE `destinatario`
  ADD PRIMARY KEY (`idDestinatario`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`idMensaje`);

--
-- Indices de la tabla `mensajero`
--
ALTER TABLE `mensajero`
  ADD PRIMARY KEY (`idMensajero`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `destinatario`
--
ALTER TABLE `destinatario`
  MODIFY `idDestinatario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `idMensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `mensajero`
--
ALTER TABLE `mensajero`
  MODIFY `idMensajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
