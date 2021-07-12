DROP TABLE IF EXISTS state;

CREATE TABLE state (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO state (name) values ('São Paulo');
INSERT INTO state (name) values ('Rio de Janeiro');
INSERT INTO state (name) values ('Minas Gerais');