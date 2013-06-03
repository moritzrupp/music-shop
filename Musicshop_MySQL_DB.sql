CREATE DATABASE `musicshop`;

CREATE TABLE `musicshop`.`albums` (
  `album_id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `interpreter` varchar(50) DEFAULT NULL,
  `cover_path` varchar(255) NOT NULL,
  PRIMARY KEY (`album_id`),
  UNIQUE KEY `cover_path_UNIQUE` (`cover_path`),
  UNIQUE KEY `name_interpreter_UNIQUE` (`name`,`interpreter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `musicshop`.`media_types` (
  `media_id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `icon_path` varchar(255) NOT NULL,
  `audio` int(1) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  UNIQUE KEY `icon_path_UNIQUE` (`icon_path`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `musicshop`.`media` (
  `medium_id` int(6) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `interpreter` varchar(50) NOT NULL,
  `duration` varchar(8) NOT NULL,
  `filesize` bigint(20) NOT NULL,
  `medium_path` varchar(255) NOT NULL,
  `no_listened` int(11) NOT NULL DEFAULT '0',
  `no_sold` int(11) NOT NULL DEFAULT '0',
  `media_types_media_id` int(6) NOT NULL,
  `albums_album_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`medium_id`),
  UNIQUE KEY `medium_path_UNIQUE` (`medium_path`),
  KEY `albums_fk` (`albums_album_id`),
  KEY `media_types_fk` (`media_types_media_id`),
  CONSTRAINT `albums_fk` FOREIGN KEY (`albums_album_id`) REFERENCES `albums` (`album_id`),
  CONSTRAINT `media_types_fk` FOREIGN KEY (`media_types_media_id`) REFERENCES `media_types` (`media_id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=utf8;