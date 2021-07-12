package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.Category;
import br.com.alessandro.alga.service.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryResource {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getOneCategory(@PathVariable Long id) {
		return categoryService.getOneCategory(id) != null ? ResponseEntity.ok(categoryService.getOneCategory(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneCategory(@PathVariable Long id) {
		categoryService.delete(id);
	}
}
