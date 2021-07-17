package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Category;
import br.com.alessandro.alga.model.Product;
import br.com.alessandro.alga.repository.CategoryRepository;
import br.com.alessandro.alga.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Page<Product> searchByNameAndCategory(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		List<Category> category = categoryRepository.findAllById(ids);
		return productRepository.findDistinctByNameContainingOrCategoryIn(name, category, pageRequest);
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getOneProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
