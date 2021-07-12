package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Product;
import br.com.alessandro.alga.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
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
