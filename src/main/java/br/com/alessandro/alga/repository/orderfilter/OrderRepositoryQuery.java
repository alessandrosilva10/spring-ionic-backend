package br.com.alessandro.alga.repository.orderfilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.repository.filter.OrderFilter;

public interface OrderRepositoryQuery {
	public Page<Order> filterPagination(OrderFilter orderFilter, Pageable pageable);
}
