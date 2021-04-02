SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS `jsf_projekat`;
CREATE DATABASE IF NOT EXISTS `jsf_projekat` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `jsf_projekat`;

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE IF NOT EXISTS `korisnici` (
  `jmbg` bigint NOT NULL,
  `korime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `ime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `tip` tinyint(4) NOT NULL COMMENT '1-korisnik, 2-administrator, 3-supervizor, 4-gost ',
  `email` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `zanimanje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `pol` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `pitanje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `odgovor` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`jmbg`),
  UNIQUE KEY `UNIQUE_korime` (`korime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `korisnici` (`jmbg`, `korime`, `lozinka`, `ime`, `prezime`, `tip`, `email`, `zanimanje`, `pol`, `pitanje`, `odgovor` ) VALUES
(3012997721052, 'bogdan', 'sifra1#S', 'Bogdan', 'Micovic', 2, 'bogdanmicovic@gmail.com', 'student', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Fogo'),
(2012897721052, 'stefan', 'sifra1#S', 'Stefan', 'Markovic', 1, 'stefan@gmail.com', 'student', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Akan'),
(2112897721052, 'marko', 'sifra1#S', 'Marko', 'Jankovic', 1, 'mare@gmail.com', 'student', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Dadan'),
(4012997721052, 'luka', 'sifra1#S', 'Luka', 'Matic', 1, 'lukson@gmail.com', 'lekar', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Jastreb'),
(5012997721052, 'user', 'sifra1#S', 'User', 'Cyber', 1, 'user@gmail.com', 'haker', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Mast'),
(6012997721052, 'user1', 'sifra1#S', 'User1', 'Cyber1', 1, 'user1@gmail.com', 'student', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Lux'),
(7012997721052, 'maki', 'sifra1#S', 'Matej', 'Micovic', 3, 'matej@gmail.com', 'student', 'Muski', 'Ime Vaseg prvog ljubimca?', 'Astra');


DROP TABLE IF EXISTS `anagrami`;
CREATE TABLE IF NOT EXISTS `anagrami` (
`naziv` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
`resenje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
`datum` date,
`odigran` int,
PRIMARY KEY (`naziv`),
UNIQUE KEY `UNIQUE_naziv` (`naziv`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `anagrami` ( `naziv`, `resenje` , `datum` , `odigran` ) VALUES
('iska jablan rakije', 'laki nabra kajsije' , '2019-09-20' , '0'),
('nanovo piksi', 'ivo panonski' , '2019-09-20' , '0'),
('raja u formi i stali', 'rijaliti sou farma' , '2019-09-20' , '0'),
('snovi postoje lagodno', 'sloboda point sevojno' , '2019-09-20' , '0');


DROP TABLE IF EXISTS `anagrami1`;
CREATE TABLE IF NOT EXISTS `anagrami1` (
`naziv` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
`resenje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
PRIMARY KEY (`naziv`),
UNIQUE KEY `UNIQUE_naziv` (`naziv`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT INTO `anagrami1` ( `naziv`, `resenje`)  VALUES
('iska jablan rakije', 'laki nabra kajsije'),
('nanovo piksi', 'ivo panonski' ),
('raja u formi i stali', 'rijaliti sou farma'),
('snovi postoje lagodno', 'sloboda point sevojno');

DROP TABLE IF EXISTS `drzave`;
CREATE TABLE `drzave` (
  `idDrzave` INT NOT NULL AUTO_INCREMENT,
  `naziv` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idDrzave`));
  
  INSERT INTO `drzave`(`naziv`) VALUES ('Uzbekistan'),('Jordan'),('Indonezija'),('Srbija'),('Rusija'),('Brazil'),('Spanija'),('Grcka'),('Holandija'),('Kina'),('Italija'),('Francuska'),('Letonija'),('Litvanija'),('Nemacka');

DROP TABLE IF EXISTS `gradovi`;
CREATE TABLE `gradovi` (
  `idGrada` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idGrada`));
INSERT INTO `gradovi`(`naziv`) VALUES ('Uzice'),('Jagodina'),('Ivanjica'),('Beograd'),('Njujork'),('Amsterdam'),('Barselona'),('Moskva'),('Atina'),('Rim'),('Pariz'),('Berlin'),('Brazilija'),('London'),('Sidnej');
  
DROP TABLE IF EXISTS `jezera`;
CREATE TABLE `jezera` (
  `idJezera` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idJezera`));
INSERT INTO `jezera`(`naziv`) VALUES ('Skadarsko'),('Albano'),('Jasna'),('Kaspijsko'),('Gornje'),('Viktorijino'),('Hjuron'),('Micigen'),('Tanganjika'),('Bajkalsko'),('Veliko medvedje'),('Malavi'),('Veliko ropsko'),('Iri'),('Vinipeg');

DROP TABLE IF EXISTS `planine`;
CREATE TABLE `planine` (
  `idPlanine` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idPlanine`));
INSERT INTO `planine`(`naziv`) VALUES ('Rtanj'),('Velebit'),('Tara'),('Licancabur'),('Benbulben'),('Stetind'),('Alpi'),('Himalaji'),('Miroc'),('Prokletijske'),('Sumadijske'),('Kopaonik'),('Homoljske'),('Mokra gora'),('Zlatar');


DROP TABLE IF EXISTS `reke`;
CREATE TABLE `reke` (
  `idReke` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idReke`));
INSERT INTO `reke`(`naziv`) VALUES ('Bojana'),('Gradac'),('Una'),('Velika Morava'),('Dunav'),('Sava'),('Zapadna Morava'),('Juzna Morava'),('Amazon'),('Rajna'),('Tisa'),('Drina'),('Tamis'),('Ibar'),('Mlava');

DROP TABLE IF EXISTS `zivotinje`;
CREATE TABLE `zivotinje` (
  `idZivotinje` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idZivotinje`));
INSERT INTO `zivotinje`(`naziv`) VALUES ('Hijena'),('Iguana'),('Jazavac'),('Lav'),('Bik'),('Zec'),('Tigar'),('Gepard'),('Leopard'),('Slon'),('Nilski konj'),('Aligator'),('Konj'),('Svinja'),('Petao');

DROP TABLE IF EXISTS `biljke`;
CREATE TABLE `biljke` (
  `idBiljke` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idBiljke`));
INSERT INTO `biljke`(`naziv`) VALUES ('Anis'),('Raskovnik'),('Selen'),('Kaktus'),('Fikus'),('Ruza'),('Visibaba'),('Cicak'),('Kopriva'),('Virak'),('Kozlac'),('Breza'),('Pelen'),('Krasuljak'),('Pitomi kesten');

DROP TABLE IF EXISTS `grupe`;
CREATE TABLE `grupe` (
  `idgrupe` INT NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  PRIMARY KEY (`idgrupe`));
INSERT INTO `grupe`(`naziv`) VALUES ('Kaliopi'),('Smak'),('Pilot'),('ACDC'),('Metalika'),('Bijelo dugme'),('Partibrejkersi'),('EKV'),('Megadet'),('Goblini'),('Hladno pivo'),('Amadeus'),('Ju grupa'),('Alisa'),('Galija');
 

DROP TABLE IF EXISTS `zahtevi`;
CREATE TABLE IF NOT EXISTS `zahtevi` (
  `jmbg` bigint NOT NULL,
  `korime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `ime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `tip` tinyint(4) NOT NULL COMMENT '1-korisnik, 2-administrator, 3-supervizor, 4-gost ',
  `email` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `zanimanje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `pol` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `pitanje` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `odgovor` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`jmbg`),
  UNIQUE KEY `UNIQUE_korime` (`korime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `partije`;
CREATE TABLE `partije` (
  `idPartije` INT NOT NULL AUTO_INCREMENT,
  `poeni` INT NOT NULL,
  `korime` TEXT NOT NULL,
  `datum` DATE NOT NULL,
  PRIMARY KEY (`idPartije`));

INSERT INTO `partije`(`poeni` , `korime` , `datum`) VALUES
('20' , 'stefan' , '2019-09-20'),
('25' , 'marko' , '2019-09-20'),
('30' , 'luka' , '2019-09-20');

DROP TABLE IF EXISTS `pehari`;
 CREATE TABLE `pehari`(
	`idP` INT NOT NULL AUTO_INCREMENT,
	`pitanje` TEXT NOT NULL,
	`odgovor` TEXT NOT NULL,
	`grupa` INT NOT NULL,
	`datum` DATE NOT NULL,
    `odigran` INT NOT NULL,
	PRIMARY KEY (`idP`));

INSERT INTO `pehari`(`pitanje`,`odgovor`,`grupa`,`datum`, `odigran` ) VALUES ('Vrsta ratnog broda','krstarica','1','2019-09-02','0'),('Mala krasta','krastica','1','2019-09-02','0'),('Stara zena','starica','1','2019-09-02','0'),('Ime pevačice Montiel','Sarita','1','2019-09-02','0'),('Čovek koji koristi rešeto','sitar','1','2019-09-02','0'),('Ime glumice Hayworth','Rita','1','2019-09-02','0'),('Konji u narodnim pesmama','ati','1','2019-09-02','0'),('Prkos ili','inat','1','2019-09-02','0'),('Stara mera za tečnost','pinta','1','2019-09-02','0'),('Zelenkasta rđa','patina','1','2019-09-02','0'),('Izvršiti napad','napasti','1','2019-09-02','0'),('Država u Aziji','Pakistan','1','2019-09-02','0'),('Odobrenje ili saglasnost','pristanak','1','2019-09-02','0'),
  ('Kraljevski dvorac u blizini Madrida','Eskorijal','2','2019-09-04','0'),('Vozači kola','kolesari','2','2019-09-04','0'),('splavari ili','skelari','2','2019-09-04','0'),('Nemački šahovski velemajstor Emanuel','Lasker','2','2019-09-04','0'),('Programski jezik za pocetnike','Karel','2','2019-09-04','0'),('Vodeni tok','reka','2','2019-09-04','0'),('Vodeni ljuskar','rak','2','2019-09-04','0'),('Takmičenje u brzini','trka','2','2019-09-04','0'),('Drugi naziv za ulaznice','karte','2','2019-09-04','0'),('Osoba koja pravi četke','četkar','2','2019-09-04','0'),('Obrtač ili','okretač','2','2019-09-04','0'),('Žene koje se bave stočarstvom','stočarke','2','2019-09-04','0'),('Glasnik, glasonoša ili','skoroteča','2','2019-09-04','0');

  
  
DROP TABLE IF EXISTS `pehari1`;
CREATE TABLE `pehari1` (
  `idP` INT NOT NULL AUTO_INCREMENT,
  `pitanje` TEXT NOT NULL,
  `odgovor` TEXT NOT NULL,
  `grupa` INT NOT NULL,
  PRIMARY KEY (`idP`)); 
  
  
INSERT INTO `pehari1`(`pitanje`,`odgovor`,`grupa`) VALUES ('Vrsta ratnog broda','krstarica','1'),('Mala krasta','krastica','1'),('Stara zena','starica','1'),('Ime pevačice Montiel','Sarita','1'),('Čovek koji koristi rešeto','sitar','1'),('Ime glumice Hayworth','Rita','1'),('Konji u narodnim pesmama','ati','1'),('Prkos ili','inat','1'),('Stara mera za tečnost','pinta','1'),('Zelenkasta rđa','patina','1'),('Izvršiti napad','napasti','1'),('Država u Aziji','Pakistan','1'),('Odobrenje ili saglasnost','pristanak','1'),
  ('Kraljevski dvorac u blizini Madrida','Eskorijal','2'),('Vozači kola','kolesari','2'),('splavari ili','skelari','2'),('Nemački šahovski velemajstor Emanuel','Lasker','2'),('Programski jezik za pocetnike','Karel','2'),('Vodeni tok','reka','2'),('Vodeni ljuskar','rak','2'),('Takmičenje u brzini','trka','2'),('Drugi naziv za ulaznice','karte','2'),('Osoba koja pravi četke','četkar','2'),('Obrtač ili','okretač','2'),('Žene koje se bave stočarstvom','stočarke','2'),('Glasnik, glasonoša ili','skoroteča','2');


DROP TABLE IF EXISTS `zangeosuperv`;
CREATE TABLE `zangeosuperv`(
    `idZahteva` INT NOT NULL AUTO_INCREMENT,
	`idPartije` INT NOT NULL,
	`kategorija` TEXT NOT NULL,
	`odgovor` TEXT NOT NULL,
	PRIMARY KEY (`idZahteva`));  
  
  
  
  
  
  
  
  
  
  
  
  
  


