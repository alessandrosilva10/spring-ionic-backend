package br.com.alessandro.alga.model;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numberOfInstallments;

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
}
