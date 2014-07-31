-- Run this file to add the DB schema, tables, user, priveleges etc. that will be
-- used by this app.  This also adds some entries to the tables (very few) to get started.

-- The first time you run it, there should be no errors.  The second time, you will get
-- an error because the user already exists.  I placed these statements at the end of the
-- file so even though the user couldn't be created (again), the tables will still be regenerated
-- clean each time back to what they were.

-- if you don't want to see the error after the first build, just uncomment the following
-- line, and it will drop that user and re-create the user properly.

DROP USER `comp461`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `cart_comp461_db`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_comp461_db`.`Product` ;

CREATE  TABLE IF NOT EXISTS `cart_comp461_db`.`Product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `description` VARCHAR(255) NULL ,
  `categoryName` VARCHAR(45) NULL ,
  `price` DOUBLE NULL ,
  `amt_in_stock` INT NULL ,
  `amt_on_order` INT NULL ,
  `reorder_threshold` INT NULL ,
  `is_discontinued` TINYINT(1) NULL ,
  `image_path` VARCHAR(255) NULL ,
  PRIMARY KEY (`idProduct`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cart_comp461_db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_comp461_db`.`User` ;

CREATE  TABLE IF NOT EXISTS `cart_comp461_db`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `address` VARCHAR(45) NULL ,
  `city` VARCHAR(45) NULL ,
  `state` VARCHAR(45) NULL ,
  `zip` VARCHAR(45) NULL ,
  `phone` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUser`),
  UNIQUE (`name`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cart_comp461_db`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_comp461_db`.`Order` ;

CREATE  TABLE IF NOT EXISTS `cart_comp461_db`.`Order` (
  `idOrder` INT NOT NULL AUTO_INCREMENT ,
  `idUser` INT NOT NULL ,
  PRIMARY KEY (`idOrder`) ,
  `orderStatus` VARCHAR(255) NULL ,
  INDEX `fk_Order_User1` (`idUser` ASC) ,
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`idUser` )
    REFERENCES `cart_comp461_db`.`User` (`idUser` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `cart_comp461_db`.`OrderItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_comp461_db`.`OrderItem` ;

CREATE  TABLE IF NOT EXISTS `cart_comp461_db`.`OrderItem` (
  `idOrderItem` INT NOT NULL AUTO_INCREMENT ,
  `idOrder` INT NOT NULL ,
  `idProduct` INT NOT NULL ,
  `quantity` INT NULL ,
  PRIMARY KEY (`idOrderItem`) ,
  INDEX `fk_OrderItem_Order1` (`idOrder` ASC) ,
  INDEX `fk_OrderItem_Product1` (`idProduct` ASC) ,
  CONSTRAINT `fk_OrderItem_Order1`
    FOREIGN KEY (`idOrder` )
    REFERENCES `cart_comp461_db`.`Order` (`idOrder` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderItem_Product1`
    FOREIGN KEY (`idProduct` )
    REFERENCES `cart_comp461_db`.`Product` (`idProduct` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cart_comp461_db`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `cart_comp461_db`;
INSERT INTO `cart_comp461_db`.`Product` (`idProduct`, `name`, `description`, `categoryName`, `price`, `amt_in_stock`, `amt_on_order`, `reorder_threshold`, `is_discontinued`, `image_path`) VALUES (1, 'Swiss', NULL, 'cheese', 4.99, 10, 2, 3, 0,'imgpath');
INSERT INTO `cart_comp461_db`.`Product` (`idProduct`, `name`, `description`, `categoryName`, `price`, `amt_in_stock`, `amt_on_order`, `reorder_threshold`, `is_discontinued`, `image_path`) VALUES (2, 'American', NULL, 'cheese', 1.99, 2, 0, 1, 1,'imgpath');
INSERT INTO `cart_comp461_db`.`Product` (`idProduct`, `name`, `description`, `categoryName`, `price`, `amt_in_stock`, `amt_on_order`, `reorder_threshold`, `is_discontinued`, `image_path`) VALUES (3, 'Jeans', NULL, 'clothes', 24.99, 1, 10, 2, 0,'imgpath');

COMMIT;

-- -----------------------------------------------------
-- Data for table `cart_comp461_db`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `cart_comp461_db`;
INSERT INTO `cart_comp461_db`.`User` (`idUser`, `name`, `address`, `city`, `state`, `zip`, `phone`) VALUES (1, 'John', '123 Home Road', 'Delaware', 'OH', '43015', '7401234567');

COMMIT;

-- -----------------------------------------------------
-- Data for table `cart_comp461_db`.`Order`
-- -----------------------------------------------------
START TRANSACTION;
USE `cart_comp461_db`;
INSERT INTO `cart_comp461_db`.`Order` (`idOrder`, `idUser`) VALUES (1, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `cart_comp461_db`.`OrderItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `cart_comp461_db`;
INSERT INTO `cart_comp461_db`.`OrderItem` (`idOrderItem`, `idOrder`, `idProduct`, `quantity`) VALUES (1, 1, 1, 3);

COMMIT;

CREATE USER `comp461` IDENTIFIED BY 'comp461';

grant DELETE on TABLE `cart_comp461_db`.`Order` to comp461;
grant INSERT on TABLE `cart_comp461_db`.`Order` to comp461;
grant UPDATE on TABLE `cart_comp461_db`.`Order` to comp461;
grant SELECT on TABLE `cart_comp461_db`.`Order` to comp461;
grant DELETE on TABLE `cart_comp461_db`.`OrderItem` to comp461;
grant INSERT on TABLE `cart_comp461_db`.`OrderItem` to comp461;
grant SELECT on TABLE `cart_comp461_db`.`OrderItem` to comp461;
grant UPDATE on TABLE `cart_comp461_db`.`OrderItem` to comp461;
grant DELETE on TABLE `cart_comp461_db`.`Product` to comp461;
grant INSERT on TABLE `cart_comp461_db`.`Product` to comp461;
grant SELECT on TABLE `cart_comp461_db`.`Product` to comp461;
grant UPDATE on TABLE `cart_comp461_db`.`Product` to comp461;
grant DELETE on TABLE `cart_comp461_db`.`User` to comp461;
grant INSERT on TABLE `cart_comp461_db`.`User` to comp461;
grant SELECT on TABLE `cart_comp461_db`.`User` to comp461;
grant UPDATE on TABLE `cart_comp461_db`.`User` to comp461;
