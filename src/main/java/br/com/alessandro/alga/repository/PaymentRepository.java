package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
