DROP TABLE IF EXISTS bank_payment_slip;

CREATE TABLE bank_payment_slip (
	order_id BIGINT(20) PRIMARY KEY,
	due_date DATETIME,
	date_payment DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;