DROP DATABASE IF EXISTS oilinventoryservice;
DROP USER IF EXISTS `oil_inventory_service`@`%`;
CREATE DATABASE IF NOT EXISTS oilinventoryservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `oil_inventory_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `oilinventoryservice`.* TO `oil_inventory_service`@`%`;
FLUSH PRIVILEGES;
