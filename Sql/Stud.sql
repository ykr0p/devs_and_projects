drop  database if exists university; 
create database university DEFAULT CHARACTER SET utf8;

use university;

DROP TABLE if exists students ; 

DROP TABLE if exists groupps ; 

CREATE TABLE `university`.`groupps` (
  `gr_id` BIGINT NOT NULL AUTO_INCREMENT,
  `gr_name` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`gr_id`)  COMMENT '');
  
INSERT INTO `university`.`groupps` (`gr_id`, `gr_name`) VALUES ('1', 'D');
INSERT INTO `university`.`groupps` (`gr_id`, `gr_name`) VALUES ('2', 'S');
  
  CREATE TABLE `university`.`students` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '',
  `surname` VARCHAR(45) NULL COMMENT '',
  `phone_number` VARCHAR(20) NULL COMMENT '',
  `gr_id` BIGINT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `gk`
    FOREIGN KEY (`gr_id`)
    REFERENCES `university`.`groupps` (`gr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
INSERT INTO `university`.`students` (`id`, `name`, `surname`, `phone_number`, `gr_id`) VALUES ('1', 'FFF', 'w45uyh', '322223322', '1');
INSERT INTO `university`.`students` (`id`, `name`, `surname`, `phone_number`, `gr_id`) VALUES ('2', 'dsdfs', 'rthyhy', '231456', '1');
INSERT INTO `university`.`students` (`id`, `name`, `surname`, `phone_number`, `gr_id`) VALUES ('3', 'sdfg', 'thty6tf', '1234567890', '2');