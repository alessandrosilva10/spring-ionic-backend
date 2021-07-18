package br.com.alessandro.alga.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.BankPaymentSlip;
import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.model.OrderItem;
import br.com.alessandro.alga.model.PaymentEnum;
import br.com.alessandro.alga.repository.OrderItemRepository;
import br.com.alessandro.alga.repository.OrderRepository;
import br.com.alessandro.alga.repository.PaymentRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BankPaymentSlipService bankPaymentSlipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOneOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
	
	@Transactional
	public Order createOrder(Order order) {
		order.setId(null);
		order.setInstant(new Date());
		order.setClient(clientService.findClientById(order.getClient().getId()));
		order.getPayment().setPayment(PaymentEnum.PENDENTE);
		order.getPayment().setOrder(order);
		order.setAddress(clientService.findClientById(order.getClient().getId()).getAddresses().get(0));
		
		if (order.getPayment() instanceof BankPaymentSlip) {
			BankPaymentSlip payment = (BankPaymentSlip) order.getPayment();
			bankPaymentSlipService.addBankSlip(payment, order.getInstant());
		}
		
		order = orderRepository.save(order);
		
		paymentRepository.save(order.getPayment());
		for (OrderItem order_items : order.getItems()) {
			BigDecimal discount = new BigDecimal(0);
			order_items.setDiscount(discount);
			order_items.setProduct(productService.findProductById(order_items.getProduct().getId()));
			order_items.setPrice(order_items.getProduct().getPrice());
			order_items.setOrder(order);
		}
		
		orderItemRepository.saveAll(order.getItems());
		//emailService.sendOrderConfirmationEmail(obj);
		System.out.println(order);
		return order;
	}
}
