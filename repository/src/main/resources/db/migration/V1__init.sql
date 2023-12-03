-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cookstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cookstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cookstore` ;
USE `cookstore` ;

-- -----------------------------------------------------
-- Table `cookstore`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `status` ENUM('Available', 'Unavailable') NOT NULL DEFAULT 'Unavailable',
  `sold_number` INT NOT NULL DEFAULT 0,
  `description` VARCHAR(512) NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `discount` DECIMAL(3,2) NULL,
  `category_id` INT NOT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_to_category_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_to_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `cookstore`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`product_image` (
  `id` BINARY(16) NOT NULL,
  `name` VARCHAR(128) NULL,
  `product_id` INT NOT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_product_image_to_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_image_to_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `cookstore`.`product` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('Paid', 'Unpaid', 'Cancelled', 'Finished') NOT NULL,
  `total_amount` DECIMAL(7,2) NOT NULL,
  `actual_amount` DECIMAL(7,2) NOT NULL,
  `remark` VARCHAR(512) NULL,
  `customer_name` VARCHAR(128) NULL,
  `cancelled_time` DATETIME NULL,
  `finished_time` DATETIME NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` TINYINT NOT NULL,
  `total_amount` DECIMAL(7,2) NOT NULL,
  `actual_amount` DECIMAL(7,2) NOT NULL,
  `discount` DECIMAL(3,2) NULL,
  `table_number` TINYINT NOT NULL,
  `remark` VARCHAR(512) NULL,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `created_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_item_to_order_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_item__to_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_to_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `cookstore`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item__to_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `cookstore`.`product` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
