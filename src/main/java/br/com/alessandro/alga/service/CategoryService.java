package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
