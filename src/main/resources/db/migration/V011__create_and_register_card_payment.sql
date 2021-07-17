DROP TABLE IF EXISTS card_payment;

CREATE TABLE card_payment (
	order_id BIGINT(20) PRIMARY KEY,
	number_of_installments BIGINT(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;