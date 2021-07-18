package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
