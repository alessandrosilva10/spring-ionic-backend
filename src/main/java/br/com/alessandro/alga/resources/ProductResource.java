package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.dto.ProductDTO;
import br.com.alessandro.alga.model.Product;
import br.com.alessandro.alga.resources.utils.URLParse;
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
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductDTO>> getPages(
			@RequestParam(value="name", defaultValue="") String name, 
			@RequestParam(value="category", defaultValue="0") String category, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="3") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
		) {
		String nameDecoded = URLParse.decodeParam(name);
		System.out.println(nameDecoded);
		List<Long> ids = URLParse.decodeIntList(category);
		Page<Product> list = productService.searchByNameAndCategory(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
