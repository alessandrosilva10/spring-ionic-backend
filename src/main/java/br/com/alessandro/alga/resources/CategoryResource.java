package br.com.alessandro.alga.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.dto.CategoryDTO;
import br.com.alessandro.alga.event.URIEvent;
import br.com.alessandro.alga.model.Category;
import br.com.alessandro.alga.service.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryResource {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<CategoryDTO> getAllCategories() {
		List<Category> list = categoryService.getAllCategories();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getOneCategory(@PathVariable Long id) {
		return categoryService.getOneCategory(id) != null ? ResponseEntity.ok(categoryService.getOneCategory(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneCategory(@PathVariable Long id) {
		categoryService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Validated @RequestBody CategoryDTO category){
		Category objDTO = categoryService.fromDTO(category);
		Category categorySaved = categoryService.updateCategory(id, objDTO);
		return ResponseEntity.ok(categorySaved);
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@Validated @RequestBody CategoryDTO categoryDTO, HttpServletResponse response){
		Category objDTO = categoryService.fromDTO(categoryDTO);
		Category categorySaved = categoryService.createCategory(objDTO);
		publisher.publishEvent(new URIEvent(this, response, categorySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
	
	@GetMapping(value = "/page")
	public Page<CategoryDTO> getPages(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="3") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
		) {
		Page<Category> list = categoryService.categoryPagination(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
		return listDTO;
	}
}
