CREATE DATABASE `chitchat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `chitchat`.`userStatus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `chitchat`.`userStatus`(`name`,`description`)
VALUES
('ACTIVE', 'User is active'),
('INACTIVE', 'User is inactive'),
('BLOCKED', 'User has been disabled by mediator or admin user');

CREATE TABLE `chitchat`.`userType` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `chitchat`.`userType`(`name`,`description`)
VALUES
('ADMIN', 'Master user'),
('MEDIATOR', 'The mediator can remove messages and users from chats'),
('NORMAL', 'Common user');

CREATE TABLE `chitchat`.`chatRoom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sourceUrl` varchar(255) DEFAULT NULL,
  `generatedUrl` varchar(255) DEFAULT NULL,
  `creationDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chitchat`.`user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `typeId` int NOT NULL,
  `statusId` int NOT NULL,
  `statusDate` datetime NOT NULL,
  `changedBy` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `statusIdFk_idx` (`statusId`),
  KEY `typeIdFk_idx` (`typeId`),
  CONSTRAINT `changedByUserFk` FOREIGN KEY (`id`) REFERENCES `user` (`typeId`),
  CONSTRAINT `statusIdFk` FOREIGN KEY (`statusId`) REFERENCES `userStatus` (`id`),
  CONSTRAINT `typeIdFk` FOREIGN KEY (`typeId`) REFERENCES `userType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chitchat`.`message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` blob NOT NULL,
  `sendDate` datetime NOT NULL,
  `chatRoomId` int NOT NULL,
  `userId` int NOT NULL,
  `removedBy` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `chatRoomIdFk_idx` (`chatRoomId`),
  KEY `userIdFk_idx` (`userId`),
  CONSTRAINT `chatRoomIdFk` FOREIGN KEY (`chatRoomId`) REFERENCES `chatRoom` (`id`),
  CONSTRAINT `userIdFk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;