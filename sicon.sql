-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 07-Ago-2018 às 16:25
-- Versão do servidor: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sicon`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `honorario_padrao` decimal(10,2) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `honorario_padrao`, `nome`, `status`) VALUES
(1, '540.00', 'DJ CHRIS & CHRIS LTDA', b'1'),
(2, '0.90', 'JULIOUS ECONOMIA - ME', b'1'),
(3, '960.00', 'Tonia\'s Shenanigans - ME', b'1'),
(4, '460.56', 'banco do brasil s/a', b'1'),
(5, '510.00', 'CBF', b'1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesa`
--

CREATE TABLE `despesa` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `valor` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `despesa`
--

INSERT INTO `despesa` (`id`, `nome`, `status`, `valor`) VALUES
(1, 'Pasta', b'1', '7.10'),
(2, 'Xerox', b'1', '0.25'),
(3, 'Livro de Registro de Empregados', b'1', '9.10'),
(6, 'livros', b'1', '7.00'),
(7, 'testeDecimal', b'1', '15.23'),
(8, 'decimal', b'1', '16.65');

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesasadicionais`
--

CREATE TABLE `despesasadicionais` (
  `id` bigint(20) NOT NULL,
  `competencia` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `honorario_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  `despesa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `despesasadicionais`
--

INSERT INTO `despesasadicionais` (`id`, `competencia`, `descricao`, `quantidade`, `valorTotal`, `cliente_id`, `honorario_id`, `usuario_id`, `despesa_id`) VALUES
(34, '2018-08-01', NULL, 17, '4.25', 2, 19, NULL, 2),
(35, '2018-08-01', NULL, 16, '145.60', 3, 20, NULL, 3),
(36, '2018-08-01', NULL, 127, '31.75', 4, 21, NULL, 2),
(37, '2018-08-01', NULL, 16, '4.00', 5, 22, NULL, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `devolucao`
--

CREATE TABLE `devolucao` (
  `id` bigint(20) NOT NULL,
  `dataDevolucao` date DEFAULT NULL,
  `documento_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `documento`
--

CREATE TABLE `documento` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `documento`
--

INSERT INTO `documento` (`id`, `descricao`, `nome`, `status`) VALUES
(1, 'Fundo de Garantia do Tempo de Serviço', 'FGTS', b'1'),
(2, 'Guia da Previdência Social', 'GPS', b'1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `documentosprotocolos`
--

CREATE TABLE `documentosprotocolos` (
  `id` bigint(20) NOT NULL,
  `competencia` date DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `valor` double NOT NULL,
  `vencimento` date DEFAULT NULL,
  `documento_id` bigint(20) DEFAULT NULL,
  `protocolo_id` bigint(20) DEFAULT NULL,
  `codRecolhimento` int(11) NOT NULL,
  `dataDevolucao` date DEFAULT NULL,
  `responsavelDevolucao_id` bigint(20) DEFAULT NULL,
  `devolvido` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `documentosprotocolos`
--

INSERT INTO `documentosprotocolos` (`id`, `competencia`, `observacao`, `valor`, `vencimento`, `documento_id`, `protocolo_id`, `codRecolhimento`, `dataDevolucao`, `responsavelDevolucao_id`, `devolvido`) VALUES
(1, '2018-07-02', '', 165, '2018-07-06', 1, 3, 34, '2018-07-07', NULL, b'1'),
(2, '2018-05-01', '', 546, '2018-06-07', 1, 4, 156, NULL, NULL, b'1'),
(3, '2018-05-01', '', 654.54, '2018-06-20', 2, 4, 654, NULL, NULL, b'0'),
(4, '2018-05-04', '', 725.54, '2018-07-02', 1, 5, 752, '2018-07-07', NULL, b'1'),
(5, '2018-05-01', '', 156.54, '2018-06-06', 1, 5, 741, '2018-07-07', NULL, b'1'),
(6, '2018-07-01', '', 324, '2018-07-02', 1, 6, 23324, NULL, NULL, b'0'),
(7, '2018-07-01', '', 345, '2018-07-10', 1, 7, 4536, '2018-07-07', NULL, b'1'),
(8, '2018-07-01', '', 165.09, '2018-08-07', 1, 8, 145, NULL, NULL, b'0'),
(9, '2018-07-01', '', 654.65, '2018-08-20', 2, 8, 654, NULL, NULL, b'1'),
(10, '2018-08-08', '', 987.65, '2018-06-20', 1, 9, 0, NULL, NULL, b'1'),
(11, '2018-06-01', 'muito caro', 654.65, '2018-07-06', 1, 11, 65, '2018-07-07', NULL, b'1'),
(12, '2018-07-01', '', 654, '2018-08-20', 2, 12, 654, '2018-07-07', NULL, b'1'),
(13, '2018-05-01', '', 65, '2018-06-06', 1, 14, 654, '2018-07-08', NULL, b'1'),
(14, '2018-06-01', '', 564.52, '2018-12-07', 1, 15, 321, '2018-07-10', NULL, b'1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `honorario`
--

CREATE TABLE `honorario` (
  `id` bigint(20) NOT NULL,
  `competencia` datetime DEFAULT NULL,
  `desconto` decimal(10,2) DEFAULT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `vencimento` datetime DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `dataPagamento` datetime DEFAULT NULL,
  `valorPago` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `honorario`
--

INSERT INTO `honorario` (`id`, `competencia`, `desconto`, `situacao`, `status`, `valor`, `vencimento`, `cliente_id`, `dataPagamento`, `valorPago`) VALUES
(18, '2018-08-01 00:00:00', NULL, NULL, b'0', '540.00', '2018-08-01 00:00:00', 1, NULL, NULL),
(19, '2018-08-01 00:00:00', NULL, NULL, b'0', '5.15', '2018-08-01 00:00:00', 2, NULL, NULL),
(20, '2018-08-01 00:00:00', NULL, NULL, b'0', '1105.60', '2018-08-01 00:00:00', 3, NULL, NULL),
(21, '2018-08-01 00:00:00', NULL, NULL, b'0', '492.31', '2018-08-01 00:00:00', 4, NULL, NULL),
(22, '2018-08-01 00:00:00', NULL, NULL, b'0', '514.00', '2018-08-01 00:00:00', 5, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil`
--

CREATE TABLE `perfil` (
  `id` bigint(20) NOT NULL,
  `nivel_acesso` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `nivelAcesso` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `perfil`
--

INSERT INTO `perfil` (`id`, `nivel_acesso`, `status`, `nivelAcesso`) VALUES
(1, 'administrador', b'1', 'administrador'),
(2, 'funcionario', b'1', 'funcionario');

-- --------------------------------------------------------

--
-- Estrutura da tabela `protocolo`
--

CREATE TABLE `protocolo` (
  `id` bigint(20) NOT NULL,
  `competencia` date DEFAULT NULL,
  `saida` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `protocolo`
--

INSERT INTO `protocolo` (`id`, `competencia`, `saida`, `status`, `cliente_id`, `usuario_id`) VALUES
(1, '2018-07-02', NULL, b'1', 1, 1),
(2, '2018-07-01', NULL, b'1', 1, 1),
(3, '2018-07-01', NULL, b'1', 1, 1),
(4, '2018-05-01', NULL, b'1', 1, 1),
(5, '2018-07-01', NULL, b'1', 2, 1),
(6, '2018-07-01', NULL, b'1', 1, 1),
(7, '2018-07-01', NULL, b'1', 1, 1),
(8, '2018-07-01', NULL, b'1', 1, 1),
(9, '2018-07-01', NULL, b'1', 2, 1),
(10, '2018-07-01', NULL, b'1', 2, 1),
(11, '2018-06-01', NULL, b'1', 3, 1),
(12, '2018-07-01', '2018-07-07', b'1', 3, 1),
(14, '2018-07-01', '2018-07-08', b'1', 1, 1),
(15, NULL, '2018-07-10', b'1', 5, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `perfil_id` bigint(20) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `perfil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `nome`, `senha`, `perfil_id`, `status`, `perfil`) VALUES
(1, 'luisguipso', 'Luis Guilherme', '123', 1, b'1', NULL),
(2, 'josesilva', 'Jose', '321', NULL, b'1', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `despesa`
--
ALTER TABLE `despesa`
  ADD PRIMARY KEY (`id`);

--

--
-- Indexes for table `despesasadicionais`
--
ALTER TABLE `despesasadicionais`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_96e41hru4xxdmviuyak2gie6u` (`cliente_id`),
  ADD KEY `FK_29tlo3mxeox6y78qeeve5ohjk` (`honorario_id`),
  ADD KEY `FK_5jtxwk36n6gskpe3d50aa0jdv` (`usuario_id`),
  ADD KEY `FK_j87yjvw3ih8am84v36sl6l59y` (`despesa_id`);

--
-- Indexes for table `devolucao`
--
ALTER TABLE `devolucao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_bts7sr865kovsddajxlf3uh6x` (`documento_id`),
  ADD KEY `FK_fa87uuboiyql6phx246b6cch1` (`usuario_id`);

--
-- Indexes for table `documento`
--
ALTER TABLE `documento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `documentosprotocolos`
--
ALTER TABLE `documentosprotocolos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_iew0l4b6jutqxn3ws1l8718pn` (`documento_id`),
  ADD KEY `FK_fbtbarvwl4pifu8179qd6ipd8` (`protocolo_id`),
  ADD KEY `FK_tnjdmvf2ryoaokue48ebxrdso` (`responsavelDevolucao_id`);

--
-- Indexes for table `honorario`
--
ALTER TABLE `honorario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_75naydjknd0l9q2kgcp4hkxjf` (`cliente_id`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `protocolo`
--
ALTER TABLE `protocolo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_fguq70d4nnninge91l4ohhop` (`cliente_id`),
  ADD KEY `FK_87qr1hh1w7kq35tn7gr2bxrpd` (`usuario_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_3gsqha3puu1ccw81jefi4j71t` (`perfil_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `despesa`
--
ALTER TABLE `despesa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `despesasadicionais`
--
ALTER TABLE `despesasadicionais`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `devolucao`
--
ALTER TABLE `devolucao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `documento`
--
ALTER TABLE `documento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `documentosprotocolos`
--
ALTER TABLE `documentosprotocolos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `honorario`
--
ALTER TABLE `honorario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `protocolo`
--
ALTER TABLE `protocolo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `despesasadicionais`
--
ALTER TABLE `despesasadicionais`
  ADD CONSTRAINT `FK_29tlo3mxeox6y78qeeve5ohjk` FOREIGN KEY (`honorario_id`) REFERENCES `honorario` (`id`),
  ADD CONSTRAINT `FK_5jtxwk36n6gskpe3d50aa0jdv` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_96e41hru4xxdmviuyak2gie6u` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_j87yjvw3ih8am84v36sl6l59y` FOREIGN KEY (`despesa_id`) REFERENCES `despesa` (`id`);

--
-- Limitadores para a tabela `devolucao`
--
ALTER TABLE `devolucao`
  ADD CONSTRAINT `FK_bts7sr865kovsddajxlf3uh6x` FOREIGN KEY (`documento_id`) REFERENCES `documentosprotocolos` (`id`),
  ADD CONSTRAINT `FK_fa87uuboiyql6phx246b6cch1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `documentosprotocolos`
--
ALTER TABLE `documentosprotocolos`
  ADD CONSTRAINT `FK_fbtbarvwl4pifu8179qd6ipd8` FOREIGN KEY (`protocolo_id`) REFERENCES `protocolo` (`id`),
  ADD CONSTRAINT `FK_iew0l4b6jutqxn3ws1l8718pn` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  ADD CONSTRAINT `FK_tnjdmvf2ryoaokue48ebxrdso` FOREIGN KEY (`responsavelDevolucao_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `honorario`
--
ALTER TABLE `honorario`
  ADD CONSTRAINT `FK_75naydjknd0l9q2kgcp4hkxjf` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Limitadores para a tabela `protocolo`
--
ALTER TABLE `protocolo`
  ADD CONSTRAINT `FK_87qr1hh1w7kq35tn7gr2bxrpd` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_fguq70d4nnninge91l4ohhop` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_3gsqha3puu1ccw81jefi4j71t` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
