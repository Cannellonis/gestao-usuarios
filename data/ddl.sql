-- MySQL Script generated by MySQL Workbench
-- Fri Oct 13 16:47:52 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TURNO_TATICO
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TURNO_TATICO
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TURNO_TATICO` DEFAULT CHARACTER SET utf8 ;
USE `TURNO_TATICO` ;

-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`usuarios` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(256) NOT NULL,
  `criado` TIMESTAMP NOT NULL,
  `atualizado` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`racas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`racas` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`racas` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(256) NOT NULL,
  `descricao` TEXT(2000) NULL,
  `atributo` JSON NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idraca_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`classes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`classes` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`classes` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`fichas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`fichas` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`fichas` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT(2000) NOT NULL,
  `atributo` JSON NOT NULL,
  `criado` TIMESTAMP NOT NULL,
  `atualizado` TIMESTAMP NOT NULL,
  `usuarios_id` CHAR(36) NULL,
  `raca_id` CHAR(36) NULL,
  `classe_id` CHAR(36) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fichas_usuarios_idx` (`usuarios_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_fichas_raca1_idx` (`raca_id` ASC) VISIBLE,
  INDEX `fk_fichas_classe1_idx` (`classe_id` ASC) VISIBLE,
  CONSTRAINT `fk_fichas_usuarios`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `TURNO_TATICO`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fichas_raca1`
    FOREIGN KEY (`raca_id`)
    REFERENCES `TURNO_TATICO`.`racas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fichas_classe1`
    FOREIGN KEY (`classe_id`)
    REFERENCES `TURNO_TATICO`.`classes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`atributos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`atributos` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`atributos` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `valor` INT NOT NULL,
  `descricao` TEXT(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`habilidades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`habilidades` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`habilidades` (
  `id` CHAR(36) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `efeito` VARCHAR(45) NOT NULL,
  `descricao` TEXT(256) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`fichas_atributos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`fichas_atributos` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`fichas_atributos` (
  `ficha_id` CHAR(36) NOT NULL,
  `atributo_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`ficha_id`, `atributo_id`),
  INDEX `fk_fichas_has_atributos_atributos1_idx` (`atributo_id` ASC) VISIBLE,
  INDEX `fk_fichas_has_atributos_fichas1_idx` (`ficha_id` ASC) VISIBLE,
  CONSTRAINT `fk_fichas_has_atributos_fichas1`
    FOREIGN KEY (`ficha_id`)
    REFERENCES `TURNO_TATICO`.`fichas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fichas_has_atributos_atributos1`
    FOREIGN KEY (`atributo_id`)
    REFERENCES `TURNO_TATICO`.`atributos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`fichas_habilidades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`fichas_habilidades` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`fichas_habilidades` (
  `ficha_id` CHAR(36) NOT NULL,
  `habilidade_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`ficha_id`, `habilidade_id`),
  INDEX `fk_fichas_has_habilidades_habilidades1_idx` (`habilidade_id` ASC) VISIBLE,
  INDEX `fk_fichas_has_habilidades_fichas1_idx` (`ficha_id` ASC) VISIBLE,
  CONSTRAINT `fk_fichas_has_habilidades_fichas1`
    FOREIGN KEY (`ficha_id`)
    REFERENCES `TURNO_TATICO`.`fichas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fichas_has_habilidades_habilidades1`
    FOREIGN KEY (`habilidade_id`)
    REFERENCES `TURNO_TATICO`.`habilidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TURNO_TATICO`.`classes_habilidades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TURNO_TATICO`.`classes_habilidades` ;

CREATE TABLE IF NOT EXISTS `TURNO_TATICO`.`classes_habilidades` (
  `classe_id` CHAR(36) NOT NULL,
  `habilidade_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`classe_id`, `habilidade_id`),
  INDEX `fk_classe_has_habilidades_habilidades1_idx` (`habilidade_id` ASC) VISIBLE,
  INDEX `fk_classe_has_habilidades_classe1_idx` (`classe_id` ASC) VISIBLE,
  CONSTRAINT `fk_classe_has_habilidades_classe1`
    FOREIGN KEY (`classe_id`)
    REFERENCES `TURNO_TATICO`.`classes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_classe_has_habilidades_habilidades1`
    FOREIGN KEY (`habilidade_id`)
    REFERENCES `TURNO_TATICO`.`habilidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;