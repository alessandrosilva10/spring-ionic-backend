package br.com.alessandro.alga.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.alessandro.alga.model.Product;
import br.com.alessandro.alga.service.ProductService;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOneProduct(@PathVariable Long id) {
		return productService.getOneProduct(id) != null ? ResponseEntity.ok(productService.getOneProduct(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneProduct(@PathVariable Long id) {
		productService.delete(id);
	}
}
