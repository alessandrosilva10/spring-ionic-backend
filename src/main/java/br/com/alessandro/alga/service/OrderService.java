package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOneOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
