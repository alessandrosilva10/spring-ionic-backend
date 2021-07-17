package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.dto.CategoryDTO;
import br.com.alessandro.alga.model.Category;
import br.com.alessandro.alga.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category getOneCategory(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public Category createCategory(Category category) {
		//category.setId(null);
		return categoryRepository.save(category);
	}
	
	public Category updateCategory(Long id, Category category) {
		Category categorySaved = findCategoryById(id);
		BeanUtils.copyProperties(category, categorySaved, "id");
		return categoryRepository.save(categorySaved);
	}
	
	public Category findCategoryById(Long id) {
		Category categorySaved = categoryRepository.findById(id).orElse(null);
		if(categorySaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categorySaved;
	}
	
	public Page<Category> categoryPagination(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
}
