package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
