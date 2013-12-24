CREATE SCHEMA IF NOT EXISTS `2014worldcup` DEFAULT CHARACTER SET utf8 ;
USE `2014worldcup`;

DROP TABLE IF exists `2014worldcup`.`goles` ;
DROP TABLE IF EXISTS `2014worldcup`.`partido` ;
DROP TABLE IF EXISTS `2014worldcup`.`estadio` ;
DROP TABLE IF EXISTS `2014worldcup`.`jugador` ;
DROP TABLE IF EXISTS `2014worldcup`.`equipo` ;
DROP TABLE IF EXISTS `2014worldcup`.`usuario` ;


-- -----------------------------------------------------
-- Table `2014worldcup`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2014worldcup`.`usuario` (
    `Ci` INT(11) NOT NULL,
	`Nombre` varchar(50) NOT NULL,
	`Apellido` varchar(50) NOT NULL,
	`Administrador` BIT NOT NULL,
	`Usuario` varchar(50) NOT NULL,
	`Password` varchar(50) NOT NULL,
    PRIMARY KEY (`Ci`)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;



-- -----------------------------------------------------
-- Table `2014worldcup`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2014worldcup`.`equipo` (
	`IdEquipo` INT NOT NULL AUTO_INCREMENT,
    `Pais` VARCHAR(100) NOT NULL,
    `Entrenador` VARCHAR(100) NOT NULL,
	`CabezaSerie` BIT NOT NULL,
    PRIMARY KEY (`IdEquipo`)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


-- -----------------------------------------------------
-- Table `2014worldcup`.`estadio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2014worldcup`.`estadio` (
	`IdEstadio` INT NOT NULL AUTO_INCREMENT,
    `NombreEstadio` VARCHAR(100) NOT NULL,
    `Capacidad` int NOT NULL,
	`TipoCesped` VARCHAR(100) NOT NULL,
	`FotoUrl` VARCHAR(100) NULL,
    PRIMARY KEY (`IdEstadio`)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


-- -----------------------------------------------------
-- Table `2014worldcup`.`jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2014worldcup`.`jugador` (
    `IdJugador` INT(11) NOT NULL AUTO_INCREMENT,
    `Nombre` VARCHAR(45) NULL DEFAULT NULL,
    `Apellido` VARCHAR(45) NULL DEFAULT NULL,
    `Posicion` VARCHAR(45) NULL DEFAULT NULL,
    `IdEquipo` INT NOT NULL,
    PRIMARY KEY (`IdJugador`),
	CONSTRAINT fk_jugadorEquipo FOREIGN KEY (IdEquipo)
	REFERENCES equipo(IdEquipo)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS `2014worldcup`.`partido` (
    `IdPartido` INT(11) NOT NULL AUTO_INCREMENT,
    `Fecha` DATETIME NOT NULL,
    `IdEstadio` INT NOT NULL,
    `IdEquipo1` INT NOT NULL,
    `IdEquipo2` INT NOT NULL,
    PRIMARY KEY (`IdPartido`),
	CONSTRAINT fk_partidoEstadio FOREIGN KEY (IdEstadio)
	REFERENCES estadio(IdEstadio),
	CONSTRAINT fk_partidoEquipo1 FOREIGN KEY (IdEquipo1)
	REFERENCES equipo(IdEquipo),
	CONSTRAINT fk_partidoEquipo2 FOREIGN KEY (IdEquipo2)
	REFERENCES equipo(IdEquipo)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


CREATE TABLE IF NOT EXISTS `2014worldcup`.`goles` (
	`IdGol` INT(11) NOT NULL AUTO_INCREMENT,
    `IdPartido` INT(11) NOT NULL,
    `IdJugador` INT(11) NOT NULL,
    `Minuto` INT(11) NOT NULL,
    PRIMARY KEY (`IdGol`),
	CONSTRAINT fk_golesPartido FOREIGN KEY (IdPartido)
	REFERENCES partido(IdPartido),
	CONSTRAINT fk_golesJugador FOREIGN KEY (IdJugador)
	REFERENCES partido(IdPartido)
)  ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

USE `2014worldcup` ;

-- INSERT USUARIOS
INSERT INTO USUARIO (Ci, Nombre, Apellido, Administrador,Usuario, Password) VALUES (78541125,'Yanick','Tourn',0,'yanick','1234');
INSERT INTO USUARIO (Ci, Nombre, Apellido, Administrador,Usuario, Password) VALUES (54852215,'Amalfi','Marini',1,'amalfi','1234');

-- EQUIPOS
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Uruguay','Maestro Tabarez', 1);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Italia','Cesare Prandelli', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Inglaterra','Roy Hodgson', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Costa Rica','Ni idea', 0);

INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('España','Del Bosque', 1);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Holanda','Van Gaal', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Chile','Jorge Sampaoli', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Australia','Holger Osieck', 0);

INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Germany','Joachim Löw', 1);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Portugal','Paulo Bento', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Ghana','Akwasi Appiah', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('USA','Jürgen Klinsmann', 0);


INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Argentina','Alejandro Sabella', 1);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Bosnia-Herzegovina','Safet Sušić', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Iran','Carlos Queiroz', 0);
INSERT INTO equipo (Pais, Entrenador, CabezaSerie) VALUES ('Nigeria','Stephen Keshi', 0);


INSERT INTO estadio (NombreEstadio, Capacidad, TipoCesped) VALUES ('Arena Pernambuco', 46154,'Natural');
INSERT INTO estadio (NombreEstadio, Capacidad, TipoCesped) VALUES ('Estadio Nacional (Brasilia)', 46154,'Natural');
INSERT INTO estadio (NombreEstadio, Capacidad, TipoCesped) VALUES ('Arena Amazonia (Manaos)', 46154,'Natural');
INSERT INTO estadio (NombreEstadio, Capacidad, TipoCesped) VALUES ('Beira-Rio', 46154,'Natural');
INSERT INTO estadio (NombreEstadio, Capacidad, TipoCesped) VALUES ('Fonte Nova', 46154,'Natural');

