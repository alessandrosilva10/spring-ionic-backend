DROP TABLE IF EXISTS city;

CREATE TABLE city (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	state_id BIGINT(20) NOT NULL,
	FOREIGN KEY (state_id) REFERENCES state(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO city (name, state_id) values ('Assis', 1);
INSERT INTO city (name, state_id) values ('Tarumã', 1);
INSERT INTO city (name, state_id) values ('Macaé', 2);