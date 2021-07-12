package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
