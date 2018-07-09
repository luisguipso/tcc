-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Jul-2018 às 03:21
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
  `honorario_padrao` double NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `honorario_padrao`, `nome`, `status`) VALUES
(1, 540, 'DJ CHRIS & CHRIS LTDA', b'1'),
(2, 0.9, 'JULIOUS ECONOMIA - ME', b'1'),
(3, 960, 'Tonia\'s Shenanigans - ME', b'1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesa`
--

CREATE TABLE `despesa` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `despesa`
--

INSERT INTO `despesa` (`id`, `nome`, `status`, `valor`) VALUES
(1, 'Pasta', b'1', 7.1),
(2, 'Xerox', b'1', 0.25),
(3, 'Livro de Registro de Empregados', b'1', 9.1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesas`
--

CREATE TABLE `despesas` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesasadicionais`
--

CREATE TABLE `despesasadicionais` (
  `id` bigint(20) NOT NULL,
  `competencia` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valorTotal` bigint(20) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `despesas_id` bigint(20) DEFAULT NULL,
  `honorario_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  `despesa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `despesasadicionais`
--

INSERT INTO `despesasadicionais` (`id`, `competencia`, `descricao`, `quantidade`, `valorTotal`, `cliente_id`, `despesas_id`, `honorario_id`, `usuario_id`, `despesa_id`) VALUES
(19, '2018-07-01', NULL, 1, 7, 1, NULL, 4, NULL, 1),
(20, '2018-08-01', NULL, 1, 7, 1, NULL, NULL, NULL, 1),
(21, '2018-10-01', NULL, 1, 7, 1, NULL, NULL, NULL, 1),
(22, '2018-11-01', NULL, 15, 4, 3, NULL, NULL, NULL, 2),
(23, '2019-01-01', NULL, 3, 21, 3, NULL, NULL, NULL, 1),
(24, '2015-01-01', NULL, 5, 36, 1, NULL, NULL, NULL, 1),
(25, '2014-01-01', NULL, 40, 10, 3, NULL, NULL, NULL, 2),
(29, '2012-01-01', NULL, 5, 36, 3, NULL, 14, NULL, 1),
(30, '2015-01-01', NULL, 2, 14, 1, NULL, 12, NULL, 1);

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
(13, '2018-05-01', '', 65, '2018-06-06', 1, 14, 654, '2018-07-08', NULL, b'1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `honorario`
--

CREATE TABLE `honorario` (
  `id` bigint(20) NOT NULL,
  `competencia` datetime DEFAULT NULL,
  `desconto` double NOT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `valor` double NOT NULL,
  `vencimento` datetime DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `dataPagamento` datetime DEFAULT NULL,
  `valorPago` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `honorario`
--

INSERT INTO `honorario` (`id`, `competencia`, `desconto`, `situacao`, `status`, `valor`, `vencimento`, `cliente_id`, `dataPagamento`, `valorPago`) VALUES
(4, '2018-07-01 00:00:00', 0, NULL, b'0', 547.1, '2018-07-01 00:00:00', 1, NULL, 0),
(5, '2018-07-01 00:00:00', 0, NULL, b'0', 960, '2018-07-01 00:00:00', 3, NULL, 0),
(6, '2018-06-01 00:00:00', 0, NULL, b'0', 22.199999999999996, '2018-06-01 00:00:00', 2, NULL, 0),
(8, '2018-08-01 00:00:00', 0, NULL, b'0', 540, '2018-08-01 00:00:00', 1, NULL, 0),
(9, '2018-10-01 00:00:00', 0, NULL, b'0', 540, '2018-10-01 00:00:00', 1, NULL, 0),
(10, '2018-11-01 00:00:00', 0, NULL, b'0', 960, '2018-11-01 00:00:00', 3, NULL, 0),
(11, '2019-01-01 00:00:00', 0, NULL, b'0', 960, '2019-01-01 00:00:00', 3, NULL, 0),
(12, '2015-01-01 00:00:00', 0, NULL, b'0', 554.2, '2015-01-01 00:00:00', 1, NULL, 0),
(13, '2014-01-01 00:00:00', 0, NULL, b'0', 960, '2014-01-01 00:00:00', 3, NULL, 0),
(14, '2012-01-01 00:00:00', 0, NULL, b'0', 995.6, '2012-01-01 00:00:00', 3, NULL, 0),
(15, '2018-01-01 00:00:00', 0, NULL, b'0', 959.7, '2018-01-01 00:00:00', 3, NULL, 0);

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
(14, '2018-07-01', '2018-07-08', b'1', 1, 1);

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
-- Indexes for table `despesas`
--
ALTER TABLE `despesas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `despesasadicionais`
--
ALTER TABLE `despesasadicionais`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_96e41hru4xxdmviuyak2gie6u` (`cliente_id`),
  ADD KEY `FK_7n7e7my4hbkgl6ksupehr6jyx` (`despesas_id`),
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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `despesa`
--
ALTER TABLE `despesa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `despesas`
--
ALTER TABLE `despesas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `despesasadicionais`
--
ALTER TABLE `despesasadicionais`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `honorario`
--
ALTER TABLE `honorario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `protocolo`
--
ALTER TABLE `protocolo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
  ADD CONSTRAINT `FK_7n7e7my4hbkgl6ksupehr6jyx` FOREIGN KEY (`despesas_id`) REFERENCES `despesas` (`id`),
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
