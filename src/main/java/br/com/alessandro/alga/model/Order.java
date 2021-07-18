package br.com.alessandro.alga.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "orderr")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instant;
		
	@OneToOne(cascade = CascadeType.ALL, mappedBy="order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Client client;
	
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {
		
	}
	
	
	public Order(Long id, Date instant, Payment payment, Client client, Address address, Set<OrderItem> items) {
		super();
		this.id = id;
		this.instant = instant;
		this.payment = payment;
		this.client = client;
		this.address = address;
		this.items = items;
	}

	public BigDecimal getTotalValue() {
		BigDecimal sum = new BigDecimal(0);
		for(OrderItem order_item: items){
			sum = sum.add(order_item.getSubTotal());
		}
		return sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}   

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Order Number: ");
		builder.append(getId());
		builder.append(", Instant: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Client: ");
		builder.append(getClient().getName());
		builder.append(", Payment Status: ");
		builder.append(getPayment().getPayment().getDescription());
		builder.append("\nDetails:\n");
		for(OrderItem order_item : getItems()) {
			builder.append(order_item.toString());
		}
		builder.append("Total value: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	}
}
