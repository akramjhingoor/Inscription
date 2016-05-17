-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 04 Mai 2016 à 13:59
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `inscription`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartenir`
--

CREATE TABLE IF NOT EXISTS `appartenir` (
  `id_candP` int(5) NOT NULL,
  `id_candE` int(5) NOT NULL,
  PRIMARY KEY (`id_candP`,`id_candE`),
  KEY `fk_appart_equi` (`id_candE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `appartenir`
--

INSERT INTO `appartenir` (`id_candP`, `id_candE`) VALUES
(1, 2),
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE IF NOT EXISTS `candidat` (
  `id_cand` int(5) NOT NULL AUTO_INCREMENT,
  `datecreation` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_cand`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `candidat`
--

INSERT INTO `candidat` (`id_cand`, `datecreation`) VALUES
(1, '2016-05-02T10:37:40.495'),
(2, '2016-05-02T10:38:03.269'),
(3, '2016-05-02T13:40:33.379');

--
-- Déclencheurs `candidat`
--
DROP TRIGGER IF EXISTS `before_delete_persequi`;
DELIMITER //
CREATE TRIGGER `before_delete_persequi` BEFORE DELETE ON `candidat`
 FOR EACH ROW BEGIN
		DELETE FROM inscrire 
		WHERE inscrire.id_cand = OLD.id_cand;

		DELETE FROM appartenir
		WHERE appartenir.id_candP = OLD.id_cand;

		DELETE FROM personne
		WHERE personne.id_candP = OLD.id_cand;

		DELETE FROM appartenir
		WHERE appartenir.id_candE = OLD.id_cand;

		DELETE FROM equipe
		WHERE equipe.id_candE = OLD.id_cand;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `competition`
--

CREATE TABLE IF NOT EXISTS `competition` (
  `id_comp` int(5) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) DEFAULT NULL,
  `datecloture` date DEFAULT NULL,
  `enEquipe` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_comp`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `competition`
--

INSERT INTO `competition` (`id_comp`, `nom`, `datecloture`, `enEquipe`) VALUES
(1, 'KFCbest', '2016-06-30', 1);

--
-- Déclencheurs `competition`
--
DROP TRIGGER IF EXISTS `before_delete_competition`;
DELIMITER //
CREATE TRIGGER `before_delete_competition` BEFORE DELETE ON `competition`
 FOR EACH ROW BEGIN
	DELETE FROM inscrire
	WHERE inscrire.id_comp = OLD.id_comp;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE IF NOT EXISTS `equipe` (
  `id_candE` int(5) NOT NULL,
  `nom` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_candE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`id_candE`, `nom`) VALUES
(2, 'KFC Barbes');

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

CREATE TABLE IF NOT EXISTS `inscrire` (
  `id_cand` int(5) NOT NULL,
  `id_comp` int(5) NOT NULL,
  PRIMARY KEY (`id_cand`,`id_comp`),
  KEY `fk_inscr_comp` (`id_comp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `inscrire`
--

INSERT INTO `inscrire` (`id_cand`, `id_comp`) VALUES
(1, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE IF NOT EXISTS `personne` (
  `id_candP` int(5) NOT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_candP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id_candP`, `nom`, `prenom`, `mail`) VALUES
(1, 'GILI', 'Amal', 'kfcam@mail.fr'),
(3, 'JHINGOOR', 'Akram', 'kfcstew@mail.fr');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD CONSTRAINT `fk_appart_equi` FOREIGN KEY (`id_candE`) REFERENCES `equipe` (`id_candE`),
  ADD CONSTRAINT `fk_appart_pers` FOREIGN KEY (`id_candP`) REFERENCES `personne` (`id_candP`);

--
-- Contraintes pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `fk_equi_cand` FOREIGN KEY (`id_candE`) REFERENCES `candidat` (`id_cand`);

--
-- Contraintes pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD CONSTRAINT `fk_inscr_cand` FOREIGN KEY (`id_cand`) REFERENCES `candidat` (`id_cand`),
  ADD CONSTRAINT `fk_inscr_comp` FOREIGN KEY (`id_comp`) REFERENCES `competition` (`id_comp`);

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `fk_pers_cand` FOREIGN KEY (`id_candP`) REFERENCES `candidat` (`id_cand`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
