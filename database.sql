CREATE database facture;
Use facture;


CREATE TABLE `facture`.`customer` (
 `id` INT(9) NOT NULL AUTO_INCREMENT ,
 `ref` VARCHAR(20) NOT NULL ,
 `nom` VARCHAR(30) NOT NULL ,
 `tel` VARCHAR(15) NOT NULL ,
 `address` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id`), UNIQUE (`ref`)
  ) ENGINE = MyISAM;

CREATE TABLE `facture`.`product` (
 `id` INT(9) NOT NULL AUTO_INCREMENT , 
 `ref` VARCHAR(20) NOT NULL ,
  `designation` VARCHAR(30) NOT NULL , 
  `description` VARCHAR(30) NULL , 
  `prix` INT(10) NOT NULL , 
  `date` DATE NULL,
   PRIMARY KEY (`id`)
   ) ENGINE = MyISAM;

CREATE TABLE `facture`.`order_product` (
 `id` INT(9) NOT NULL AUTO_INCREMENT , 
 `ref` VARCHAR(20) NOT NULL , 
 `date` DATE NOT NULL ,
 `ref_customer` VARCHAR(20) NOT NULL ,
 `ref_product` VARCHAR(20) NOT NULL , 
 `qte_product` INT(9) NOT NULL ,
    PRIMARY KEY (`id`),
   FOREIGN KEY(`ref_customer`) REFERENCES `customer`(`ref`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   FOREIGN KEY(`ref_product`) REFERENCES `product`(`ref`) ON DELETE RESTRICT ON UPDATE RESTRICT
  ) ENGINE = MyISAM;

CREATE TABLE `facture`.`bill` ( 
  `id` INT(9) NOT NULL AUTO_INCREMENT , 
  `ref` VARCHAR(20) NOT NULL , 
  `date` DATE NOT NULL ,
  `ref_order` VARCHAR(20) NOT NULL ,
  `ref_customer` VARCHAR(20) NOT NULL ,
  `total` INT(10) NOT NULL ,
   PRIMARY KEY (`id`),
   FOREIGN KEY(`ref_customer`) REFERENCES `order_product`(`ref_customer`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   FOREIGN KEY(`ref_order`) REFERENCES `order_product`(`ref`) ON DELETE RESTRICT ON UPDATE RESTRICT
	 )ENGINE = MyISAM;



