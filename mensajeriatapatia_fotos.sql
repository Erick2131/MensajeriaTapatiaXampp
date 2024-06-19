-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-06-2024 a las 11:22:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

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
  `fecha` datetime NOT NULL,
  `foto` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`idMensaje`, `emisor`, `destinatario`, `mensajero`, `tipo`, `contenido`, `fecha`, `foto`) VALUES
(1, '', '0', '0', 'Mensaje', 'hola', '0000-00-00 00:00:00', ''),
(2, '', '14', '1', 'Paquete', 'hola', '0000-00-00 00:00:00', ''),
(3, '', '14', '1', 'Mensaje', 'va', '0000-00-00 00:00:00', ''),
(4, '', '14', '1', 'Paquete', 'Rosa pastel pero no de belanova', '2024-06-14 18:03:05', ''),
(5, '', '15', '3', 'Paquete', 'Fragil', '2024-06-14 18:33:44', ''),
(6, '', '14', '2', 'Paquete', 'aaaaaaaaaaaaaaaaaaa', '2024-06-19 09:06:26', ''),
(7, '', '14', '2', 'Paquete', 'aaaaaaaaaaaaaaaaaaa', '2024-06-19 09:16:37', 0x89504e470d0a1a0a0000000d49484452000000ae000000e80802000000f96f48c8000000017352474200aece1ce90000000373424954080808dbe14fe00000060949444154789cedddcd531b6500c7f167372f0bc84b5ab0c550e958abe24ca1bde4e0c999cee8c10377fda7bcf82ff4ecd98307b875aae368d5c14eb0d32a9436e1a51058d26c763df4375e7c36e3b3ae9b00dfcf719f3ed987f02ddbee3c59bce434318031feb0178051410a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021050829404801420a1052809002841420a40021054879d80bc8228e93fdfdfd61afe2bc39a329c49d4e67d8ab386fb840404801420a1052809002841420a40019e9fb0a9de09ef57814449df9ad821733145e62befdba1a1e4d3bcdbaf65efbce4726f1dcce35d229f4cdd394e3513fb8102918639eed0647bbb34e53c6eadbd198f389b840404801420a1052809002841420a40029e8be42b7fbaadd6ebbce7a79fda2dc3c180505a59024491445aeb3324c41665c2020a40021050829404801420a90a25248682e132f2eee54c96952c0698ebcdf1e275fbace7afad595300cff8ff59c219d4e2749dcbe47954a656ccc79ef4a41b798bc4c3f15c230e40371c618cf73db9b16455186f78d9fdb1052809002841420a40021050829404801420a1052809002841420a400210508294072deaf10457debf1bed737a57c4f859c6549e138b817a53c1ae78979f2dfd683a1e1020121050829404801420a1052809002a49c7653c8f77ddf777c4a30ceb2f2d696fd7947939393b3b3970b5e0d86880b04841420a40021050829404801420a9091fedd51e18de9de69f59fc7e3383e3c3c2c7e3de7db48a7f0ec8b15ebf153737affc7fb052fe6dce3020121050829404801420a1052809002a4dc6ab5ac03711c5fba74c93e64e2d8d81f439df66ad9b4aeda5fadd7ebe57816bc565e5b5bb30e2c2d2dcdcfcf5b87bae3ddc874ad4369af96cddad509ebf152a974f9323bac72c60502420a1052809002841420a400210548d9f70fac03be7f90369498a9d8cc5987e2befd38465f39a8fe601d08aa6150b5df6dec9beb5df38175a87bfa736e4b33c6988bfeebe48a94ed02c165e51ce29b0a21050829404801420a105280941b8d8675606eee4cde2cdadcdc6c369b79bddac2c2c2ad5bb7ac430707076198db6d8fc9c9c9a9a929ebd0fafabaeb89062c7b80d414cea866b3197ff36b5eaff647636f400abbbbbb799da85eafa7a570bcfebdd9737bb501cb1e800b04841420a40021050829404801420a902c0fe079a37f1278f64f477d727bc97afca0133ed82ce2378c5d391adfd9769fb6604ce236e3e6b37717ff7cdb78f60f0c5a24fef1f8cb476f3d763a4bec19d3f6cc8edbe2aa47e34e7ffeb54c2944a19fb2b64f6f7f683dbef9a2554c0af38763f196f317f5a21eb94e797ffb66f577b7133daf3d774dc14fcc6c7bbab475ec342be8049e73db992e10691d2077b167122fcbdb9d610eff56809002841420a40021050829400a7ac673ad56bb7bf7aeeb2cff867df74eafd7dbd8d8b00e2d2f2f7ffcf90dd713dd6bd8ffbb3f0a5bb9565757675a6ebfda6ffb4ea9e37ea2825298989858bab9e83a6b27e5837227c1c986b1a750afd7971b35d7133d6a0cff5b9e666565e5cae198d3949faeed3f34f6cfbb0ec00502420a1052809002841420a40021054841f715827e7fdeb44de2585ec57937cee69b873b33276e735c5755203f31df2dee55fa6eb38eab51ec396f2b2928053f89c7a37fbdf7eb6fee299c54fbc755c777ce8b47b986fd71fbdec1c1326c2f1addb7209b4c3b7ecedb9b900def02841420a40021050829404801420a1052809002841420a40021050829404801420a9082b6ae6473c704d6e347e6d5c35fdc9e49933bff6ad3ccb8fd45aa557a9f79ef5887caad72696fc85fd148a7504bf9a1550afda9ce909f0294545ef62a6e537c63e67a55fb58cf64db7693232e101052809002841420a40021050829404801420a1052809002841420a40021050829404801f2175b9651a7097cf2cc0000000049454e44ae426082);

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
(7, 'charlie', 20, 'c@gmail.com', '123', 0),
(8, 'aa', 12, 'a@a.com', '1234', 0);

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
  MODIFY `idMensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `mensajero`
--
ALTER TABLE `mensajero`
  MODIFY `idMensajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
