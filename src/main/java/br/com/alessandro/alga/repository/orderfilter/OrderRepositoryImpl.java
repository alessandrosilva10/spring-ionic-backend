package br.com.alessandro.alga.repository.orderfilter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.alessandro.alga.model.Order;
import br.com.alessandro.alga.repository.filter.OrderFilter;

public class OrderRepositoryImpl implements OrderRepositoryQuery{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Order> filterPagination(OrderFilter orderFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);
		
		Predicate[] predicates = createRestrictions(orderFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Order> query = manager.createQuery(criteria);
		
		addRestrictionPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(orderFilter)); //query.getResultList()
	}

	private Predicate[] createRestrictions(OrderFilter orderFilter, CriteriaBuilder builder,
			Root<Order> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(orderFilter.getPayment())) {
			predicates.add(builder.like(
					builder.lower(root.get("payment")), "%" + orderFilter.getPayment().toLowerCase() + "%"));
		}
		
		if (orderFilter.getDueDateOf() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataVencimentoDe"), orderFilter.getDueDateOf()));
		}
		
		if (orderFilter.getDueDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataVencimentoAte"), orderFilter.getDueDateUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addRestrictionPagination(TypedQuery<Order> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalPerPage = pageable.getPageSize();
		int firstRegisterPage = currentPage * totalPerPage;
		
		query.setFirstResult(firstRegisterPage);
		query.setMaxResults(totalPerPage);
	}
	
	private Long total(OrderFilter orderFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Order> root = criteria.from(Order.class);
		
		Predicate[] predicates = createRestrictions(orderFilter, builder, root);
		
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
}
