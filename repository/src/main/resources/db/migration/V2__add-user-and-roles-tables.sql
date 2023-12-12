-- -----------------------------------------------------
-- Table `cookstore`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(128) NOT NULL,
  `email` NVARCHAR(255) NOT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM('Admin', 'Customer') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookstore`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cookstore`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_role_1_to_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `cookstore`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_1_to_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `cookstore`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;