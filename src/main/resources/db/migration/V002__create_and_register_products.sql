DROP TABLE IF EXISTS product;

CREATE TABLE product (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	price DECIMAL(6,2) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO product (name, price) values ('Cachorro', 11);
INSERT INTO product (name, price) values ('Cachorro', 11);