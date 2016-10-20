create database TalaBank;

use TalaBank;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type_id` int(11) NOT NULL,
  `account_name` varchar(100) NOT NULL,
  `account_number` varchar(15) NOT NULL,
  `account_balance` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `fk_account_1_idx` (`account_type_id`),
  KEY `fk_account_2_idx` (`branch_id`),
  CONSTRAINT `fk_account_1` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`account_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_2` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


LOCK TABLES `account` WRITE;
INSERT INTO `account` VALUES (3,1,'Kimando','121343454',0,1,'2016-10-19 14:43:00');
UNLOCK TABLES;

DROP TABLE IF EXISTS `account_type`;
CREATE TABLE `account_type` (
  `account_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`account_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


LOCK TABLES `account_type` WRITE;
INSERT INTO `account_type` VALUES (1,'Jijenge','Savings Account'),(2,'Jiinue','Savings Account'),(3,'Pepea','Current Account');
UNLOCK TABLES;

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `branch_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(100) NOT NULL,
  `branch_location` varchar(100) NOT NULL,
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `branch` WRITE;
INSERT INTO `branch` VALUES (1,'CBD','Nairobi Kenyatta Avenue'),(2,'Thika','Thika Tuskys Mall'),(3,'Eldoret','Eldoret Moi Avenue');
UNLOCK TABLES;


DROP TABLE IF EXISTS `transaction_type`;
CREATE TABLE `transaction_type` (
  `transaction_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `transaction_type` WRITE;
INSERT INTO `transaction_type` VALUES (1,'BAL_ENQ','Balance Enquiry'),(2,'CASH_DEP','Cash Deposit'),(3,'CASH_WITH','Cash Withdrawal');
UNLOCK TABLES;


DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `transaction_type_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `transaction_date` date NOT NULL,
  `transaction_time` time NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_transaction_1_idx` (`account_id`),
  KEY `fk_transaction_2_idx` (`transaction_type_id`),
  CONSTRAINT `fk_transaction_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_2` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;



