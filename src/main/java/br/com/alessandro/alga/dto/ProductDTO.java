package br.com.alessandro.alga.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.alessandro.alga.model.Product;

public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private BigDecimal price;
	
	public ProductDTO(Product product) {
		super();
		id = product.getId();
		name = product.getName();
		price = product.getPrice();
	}

	public ProductDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
