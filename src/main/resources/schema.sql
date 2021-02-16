DROP DATABASE IF EXISTS propify;

CREATE DATABASE propify;

USE propify;

DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  street   VARCHAR(80) NOT NULL,
  city     VARCHAR(80) NOT NULL,
  state    VARCHAR(2)  NOT NULL,
  zip      VARCHAR(5)  NOT NULL,
  timezone VARCHAR(35)  NOT NULL
);

CREATE TABLE property (
  id            INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  type          VARCHAR(15)  NOT NULL,
  rent_price    FLOAT        NOT NULL,
  address_id    INT UNSIGNED NOT NULL,
  email_address VARCHAR(100) NOT NULL,
  code          VARCHAR(10)  NOT NULL,
  INDEX rent_price_ind (rent_price),
  INDEX address_id_ind (address_id),
      FOREIGN KEY (address_id)
          REFERENCES address(id)
          ON DELETE CASCADE
);
