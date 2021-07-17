package br.com.alessandro.alga.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class BankPaymentSlip extends Payment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dueDate;
	private Date datePayment;
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDatePayment() {
		return datePayment;
	}
	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
}
