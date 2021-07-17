package br.com.alessandro.alga.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderFilter {
	private String payment;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dueDateOf;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dueDateUntil;

	public LocalDate getDueDateOf() {
		return dueDateOf;
	}

	public void setDueDateOf(LocalDate dueDateOf) {
		this.dueDateOf = dueDateOf;
	}

	public LocalDate getDueDateUntil() {
		return dueDateUntil;
	}

	public void setDueDateUntil(LocalDate dueDateUntil) {
		this.dueDateUntil = dueDateUntil;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
}
