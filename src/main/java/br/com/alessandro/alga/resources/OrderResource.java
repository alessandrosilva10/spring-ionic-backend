package br.com.alessandro.alga.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.event.URIEvent;
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
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
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
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@Validated @RequestBody Order order, HttpServletResponse response){
		Order orderSaved = orderService.createOrder(order);
		publisher.publishEvent(new URIEvent(this, response, orderSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(orderSaved);
	}
}
