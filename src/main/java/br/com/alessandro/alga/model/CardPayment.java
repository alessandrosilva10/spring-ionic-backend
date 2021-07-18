package br.com.alessandro.alga.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("cardPayment")
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
