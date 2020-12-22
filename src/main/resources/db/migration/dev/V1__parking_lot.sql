--drop table if exists flyway_schema_history;
--drop table if exists usuarios;
--drop table if exists roles;
--drop table if exists users_roles;
--drop table if exists spots;
--drop table if exists vehiculos;

CREATE TABLE `usuarios`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) DEFAULT NULL,
	`username` VARCHAR(15) DEFAULT NULL,
	`password` VARCHAR(100) DEFAULT NULL,
	`email` VARCHAR(40) DEFAULT NULL,
	`created_at` DATETIME DEFAULT NULL,
	`updated_at` DATETIME DEFAULT NULL,
	`version` bigint(20) DEFAULT NULL,
	PRIMARY KEY(`id`),
	UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
	UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
);

CREATE TABLE `roles`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(60) DEFAULT NULL,
	PRIMARY KEY(`id`),
	UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
);

CREATE TABLE `users_roles`(
	`user_id` bigint(20) NOT NULL,
	`role_id` bigint(20) NOT NULL,
	PRIMARY KEY(`user_id`, `role_id`),
	CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`),
  	CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `vehiculos`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`numero_vehiculo` VARCHAR(255) NOT NULL,
	`color` VARCHAR(200) NOT NULL,
	`tipo` VARCHAR(200) NOT NULL,
	`hora_entrada` DATETIME NOT NULL,
	`hora_salida` DATETIME DEFAULT NULL,
	`spot_id` BIGINT(20) DEFAULT NULL,
	`created_at` DATETIME DEFAULT NULL,
	`updated_at` DATETIME DEFAULT NULL,
	`version` bigint(20) DEFAULT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE `spots`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`tipo_vehiculo` VARCHAR(200) DEFAULT NULL,
	`nivel` INT(11) NOT NULL,
	`posicion` INT(11) NOT NULL,
	`vehiculo_id` BIGINT(20) DEFAULT NULL,
	`created_at` DATETIME DEFAULT NULL,
	`updated_at` DATETIME DEFAULT NULL,
	`version` bigint(20) DEFAULT NULL,
	PRIMARY KEY(`id`)
);

ALTER TABLE `vehiculos`
	ADD CONSTRAINT fk_spots FOREIGN KEY(`spot_id`) REFERENCES `spots` (`id`);

ALTER TABLE `spots`
	ADD CONSTRAINT fk_vehiculos FOREIGN KEY(`vehiculo_id`) REFERENCES `vehiculos` (`id`);


INSERT INTO roles(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');