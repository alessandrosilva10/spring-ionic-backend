package br.com.alessandro.alga.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.repository.OrderRepository;
import br.com.alessandro.alga.repository.filter.OrderFilter;
import br.com.alessandro.alga.service.OrderService;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderResource {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/*
	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}*/
	@GetMapping
	public Page<Order> getAllOrders(OrderFilter orderFilter, Pageable pageable) {
		return orderRepository.filterPagination(orderFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOneOrder(@PathVariable Long id) {
		return orderService.getOneOrder(id) != null ? ResponseEntity.ok(orderService.getOneOrder(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneOrder(@PathVariable Long id) {
		orderService.delete(id);
	}
}
