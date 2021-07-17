package br.com.alessandro.alga.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.alessandro.alga.model.Client;

public class ClientDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotEmpty
	@Size(min=3, max=20)
	@Pattern(regexp="^[A-Za-z]*$")
	private String name;
	
	@NotEmpty
	@Email
	@Column(unique=true)
	private String email;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		ClientDTO other = (ClientDTO) obj;
		return Objects.equals(id, other.id);
	}
}
