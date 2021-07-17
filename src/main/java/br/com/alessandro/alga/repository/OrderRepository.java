package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.repository.orderfilter.OrderRepositoryQuery;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryQuery{

}
