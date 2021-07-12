DROP TABLE IF EXISTS address;

CREATE TABLE address (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    cep VARCHAR(50) NOT NULL,
	complement VARCHAR(50) NOT NULL,
	district VARCHAR(50) NOT NULL,
	number VARCHAR(50) NOT NULL,
	public_place VARCHAR(50) NOT NULL,
	city_id BIGINT(20) NOT NULL,
	client_id BIGINT(20) NOT NULL,
	FOREIGN KEY (city_id) REFERENCES city(id),
	FOREIGN KEY (client_id) REFERENCES client(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO address (cep, complement, district, number, public_place, city_id, client_id) values ('19802112', 'Fundos', 'Vila Brasileira', "1296", "Cruz e Souza", 1, 1);
