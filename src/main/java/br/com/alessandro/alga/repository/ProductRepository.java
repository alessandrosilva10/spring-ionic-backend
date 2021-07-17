package br.com.alessandro.alga.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alessandro.alga.model.Category;
import br.com.alessandro.alga.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Transactional(readOnly=true)
	//@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.category cat WHERE obj.name LIKE %:name% AND cat IN :category")
	Page<Product> findDistinctByNameContainingOrCategoryIn(String name, List<Category> category, Pageable pageRequest); //findDistinctByNameContainingAndCategoryIn
}
