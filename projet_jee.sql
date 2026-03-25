-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 21 jan. 2025 à 16:45
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_jee`
--

-- --------------------------------------------------------

--
-- Structure de la table `batiment`
--

DROP TABLE IF EXISTS `batiment`;
CREATE TABLE IF NOT EXISTS `batiment` (
  `codeB` varchar(16) NOT NULL,
  `anneeC` int DEFAULT NULL,
  `campus` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`codeB`),
  KEY `FKa7ox1bssmsuy3sbt72js43p1v` (`campus`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `batiment`
--

INSERT INTO `batiment` (`codeB`, `anneeC`, `campus`) VALUES
('triolet_b36', 2019, 'Triolet'),
('triolet_b16', 1966, 'Triolet'),
('triolet_b05', 1964, 'Triolet'),
('stPriest_b02', 1982, 'St Priest'),
('triolet_b10', 1960, 'Triolet'),
('triolet_b01', 1898, 'Triolet'),
('triolet_b12', 2050, 'Education'),
('P31', 2000, 'ESPA'),
('Amphi2', 2000, 'ESPA'),
('meds1', 1980, 'Meds'),
('DemiToneau', 1960, 'EGS'),
('Amphi1', 2000, 'ESPA'),
('triolet_b07', 2000, 'Triolet'),
('eni1', 2000, 'ENI'),
('eni2', 2003, 'ENI'),
('triolet_b31', 2020, 'Triolet'),
('Conaco1', 1960, 'EGS'),
('batiment1', 2001, 'campus1'),
('batiment2', 2002, 'campus1'),
('batTest1', 2025, 'ESPA'),
('Polyvalent1', 2025, 'Tsiory');

-- --------------------------------------------------------

--
-- Structure de la table `campus`
--

DROP TABLE IF EXISTS `campus`;
CREATE TABLE IF NOT EXISTS `campus` (
  `nomC` varchar(16) NOT NULL,
  `ville` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nomC`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `campus`
--

INSERT INTO `campus` (`nomC`, `ville`) VALUES
('St Priest', 'Paris'),
('Pharmacie', 'Montpellier'),
('Richter', 'Montpellier'),
('FDE Mende', 'Mende'),
('Medecine Nimes', 'Nimes'),
('Triolet', 'Montpellier'),
('Faculte Droit', 'Montpellier'),
('STAPS', 'Montpellier'),
('ENSCM', 'Montpellier'),
('Education', 'Montpellier'),
('MOMA', 'Itaosy'),
('ESPA', 'Vontovorona'),
('EGS', 'Ambohitsaina'),
('Barikadimy', 'Toamasina'),
('ENI', 'Fianarantsoa'),
('campus1', 'ville1');

-- --------------------------------------------------------

--
-- Structure de la table `composante`
--

DROP TABLE IF EXISTS `composante`;
CREATE TABLE IF NOT EXISTS `composante` (
  `acronyme` varchar(8) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `responsable` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`acronyme`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `composante`
--

INSERT INTO `composante` (`acronyme`, `nom`, `responsable`) VALUES
('EN', 'Electronique', 'Rajao'),
('IAE', 'Ecole Universitaire de Management', 'E Houze'),
('TCO', 'TELECOM', 'R. Rajaonarison'),
('FDS', 'Faculté des Sciences', 'JM Marin'),
('Polytech', 'Polytech Montpellier', 'L. Torres'),
('cmp3', 'cmpcmp33333', 'mr cool'),
('MTO', 'Meteorologie', 'Ramasy'),
('EGS', 'Facultes Economie, Gestion et Sociologie', 'Ramanoelina'),
('ISR', 'Ingenierie des Systemes et des Reseaux', 'Parfait'),
('comp1', 'composante1', 'responsable1'),
('comp2', 'composante2', 'responsable2'),
('INFO', 'Informatique', 'RANDRIAMISAINA');

-- --------------------------------------------------------

--
-- Structure de la table `exploite`
--

DROP TABLE IF EXISTS `exploite`;
CREATE TABLE IF NOT EXISTS `exploite` (
  `team` varchar(8) NOT NULL,
  `building` varchar(16) NOT NULL,
  PRIMARY KEY (`team`,`building`),
  KEY `FKpmvrayx69egn3aa71hnn6ww8u` (`building`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `exploite`
--

INSERT INTO `exploite` (`team`, `building`) VALUES
('cmp3', 'batTest1'),
('EGS', 'Conaco1'),
('EN', 'Amphi2'),
('FDS', 'triolet_b05'),
('FDS', 'triolet_b16'),
('FDS', 'triolet_b36'),
('INFO', 'Polyvalent1'),
('ISR', 'eni1'),
('ISR', 'eni2'),
('MTO', 'batTest1'),
('Polytech', 'triolet_b12'),
('Polytech', 'triolet_b31'),
('TCO', 'Amphi2'),
('TCO', 'P31');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `numS` varchar(16) NOT NULL,
  `capacite` int DEFAULT NULL,
  `typeS` varchar(12) DEFAULT NULL,
  `acces` varchar(3) DEFAULT NULL,
  `etage` varchar(3) DEFAULT NULL,
  `batiment` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`numS`),
  KEY `FKr4ah37yj6kgk0d97h5a4dsf37` (`batiment`)
) ;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`numS`, `capacite`, `typeS`, `acces`, `etage`, `batiment`) VALUES
('A36.03', 999, 'numerique', 'non', '9', 'triolet_b01'),
('A36.02', 120, 'amphi', 'oui', 'rdc', 'triolet_b36'),
('A36.01', 120, 'amphi', 'oui', 'rdc', 'triolet_b36'),
('TD36.202', 40, 'numerique', 'oui', '2', 'triolet_b36'),
('TD36.203', 40, 'numerique', 'oui', '2', 'triolet_b36'),
('TD36.204', 40, 'numerique', 'oui', '2', 'triolet_b36'),
('SC36.04', 80, 'sc', 'oui', '1', 'triolet_b36'),
('TD36.101', 40, 'td', 'oui', '1', 'triolet_b36'),
('TD36.302', 40, 'td', 'oui', '3', 'triolet_b36'),
('TD36.402', 40, 'td', 'oui', '4', 'triolet_b36'),
('SC16.03', 120, 'amphi', 'oui', 'rdc', 'triolet_b16'),
('TD16.02', 18, 'td', 'oui', 'rdc', 'triolet_b16'),
('TPDeptInfo', 40, 'numerique', 'oui', 'rdc', 'triolet_b16'),
('TPBio', 40, 'tp', 'oui', 'rdc', 'triolet_b16'),
('SC16.05', 48, 'sc', 'oui', 'rdc', 'triolet_b16'),
('A5.02', 275, 'amphi', 'oui', '1', 'triolet_b05'),
('TD5.125', 20, 'numerique', 'oui', 'rdc', 'triolet_b05'),
('TD5.126', 31, 'numerique', 'oui', 'rdc', 'triolet_b05'),
('TD5.210', 40, 'numerique', 'oui', '1', 'triolet_b05'),
('A_JJMoreau', 114, 'amphi', 'oui', '1', 'stPriest_b02'),
('B2', 80, 'td', 'oui', 'rdc', 'Amphi2'),
('salle1', 20, 'tp', 'oui', '1', 'batiment1'),
('salle12', 30, 'tp', 'oui', '2', 'batiment1'),
('salle21', 40, 'td', 'oui', '2', 'batiment2'),
('salle22', 20, 'tp', 'oui', 'rdc', 'batiment2'),
('P01', 20, 'td', 'oui', 'rdc', 'Polyvalent1');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `nom` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mdp` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `nom`, `role`, `mdp`) VALUES
(1, 'randria@email.com', 'randria', 'ETUDIANT', '$2a$12$lMrlODCVN7A4fvfsH3zZEOhv9OENuJKv9sKcJfK5DxPpDrSYwd.Ey'),
(2, 'lala@email.com', 'lala', 'GESTIONNAIRE', '$2a$12$nqYeEevS/QjjatfzFOK9JeKSbN42wnEjexsiHQZnR0kyH1lnCnhLq'),
(3, 'koto@email.com', 'koto', 'ETUDIANT', '$2a$12$hozbMti1eKSdex1Dotskouaj/5nzkxG1UlMtEFYgNUYSQ4ByDpBX'),
(4, 'nandra@email.com', 'nandra', 'ADMINISTRATEUR', '$2a$12$Wi1.yb6xGmuOgWBo73JpjesWdJN/Ja4NifWr5Gngxq5ZIYCDQMDea\r\n'),
(5, 'toto@email.com', 'toto', 'ETUDIANT', '$2a$10$9GOUrNTkwbH6/bM47obiZ.aqLS7n/3GX6p8cqb8d.fIK6gaoO0nDu'),
(6, 'titi@email.com', 'titi', 'ETUDIANT', '$2a$10$MrK5Cf791j0p2W3uf.7.OeXeZ.Tw5PDSaRqKLoFk8TQUhhfrCXGiW'),
(7, 'tutu@email.com', 'tutu', 'GESTIONNAIRE', '$2a$10$cMFwUHriOg/lrOXVOoICEOL1Ne/SAaUVZIZdKtEBxof9AP6oRP4P2'),
(8, 'momo@email.com', 'momo', 'ADMINISTRATEUR', '$2a$10$6FuDaMLvyVcrfN9qVAe6NuugBOYJ6u.2mAWMSEql6vnIOg0kWWn9G'),
(9, 'manager@email.com', 'manager', 'GESTIONNAIRE', '$2a$10$Ff0WSzibGwC6cFGOk2L4iOZoXqnMfcsceCFL7215cvGM.pQ8Q1dj.'),
(10, 'admin@email.com', 'admin', 'ADMINISTRATEUR', '$2a$10$4NMe/x7xYba25ycSWsYhjOysWIGBp9F7bIRqWU5Rrc.TyDAlMxPri'),
(12, 'student@email.com', 'Student', 'ETUDIANT', '$2a$10$fmVOdTRuIw.gfxudqR5IMOPWsD/dQQKTcw04f7CrtuXhaj5.GlpzS'),
(13, 'tsiory@gmail.com', 'RANDRIAMISAINA', 'ADMINISTRATEUR', '$2a$10$vFx2P7dP5ivO2AJQrISgP.nOff/eMXPsek3LOLbcNxb5oWg8B748a');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
