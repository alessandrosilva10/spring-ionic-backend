package br.com.alessandro.alga.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {
	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private BigDecimal discount;
	private Integer quantity;
	private BigDecimal price;
	
	public OrderItem() {
		
	}
	
	public OrderItem(Order order, Product product, OrderItemPK id, BigDecimal discount, Integer quantity, BigDecimal price) {
		super();
		this.id = id;
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
		id.setOrder(order);
		id.setProduct(product);
	}

	
	public BigDecimal getSubTotal() {
		return (price.subtract(discount)).multiply(BigDecimal.valueOf(quantity));
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public OrderItemPK getId() {
		return id;
	}
	
	public void setId(OrderItemPK id) {
		this.id = id;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Quantity: ");
		builder.append(getQuantity());
		builder.append(", Price: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
}
