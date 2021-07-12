DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category(
	product_id BIGINT(20) NOT NULL,
	category_id BIGINT(20) NOT NULL,
	FOREIGN KEY (product_id) REFERENCES product(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into product_category (product_id, category_id) values (1, 1);