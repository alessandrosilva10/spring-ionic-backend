DROP TABLE IF EXISTS client;

CREATE TABLE client (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	cpf_or_cnpj VARCHAR(50),
	email VARCHAR(50) NOT NULL,
	type BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO client (name, cpf_or_cnpj, email, type) values ('Alesso', '46821', 'alessandr@gmail.com', 1);
