DROP DATABASE IF EXISTS propify;

CREATE DATABASE propify;

USE propify;

DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS property;

CREATE TABLE property (
  id            INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
);


CREATE TABLE address (
  id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
);
