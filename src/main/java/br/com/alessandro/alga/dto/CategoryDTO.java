package br.com.alessandro.alga.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.alessandro.alga.model.Category;

public class CategoryDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotNull
	@Size(min=3, max=20)
	@Pattern(regexp="^[A-Za-z]*$")
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
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
}
