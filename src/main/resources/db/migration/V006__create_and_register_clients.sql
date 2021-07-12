DROP TABLE IF EXISTS client;

CREATE TABLE client (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	cpf_or_cnpj VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	type BIGINT(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO client (name, cpf_or_cnpj, email, type) values ('Alessandro', '40380576821', 'alessandro.mpgh@gmail.com', 1);
