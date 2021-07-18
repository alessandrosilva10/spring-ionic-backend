package br.com.alessandro.alga.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.BankPaymentSlip;

@Service
public class BankPaymentSlipService {
		public void addBankSlip(BankPaymentSlip payment, Date instant) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(instant);
			cal.add(Calendar.DAY_OF_MONTH, 7);
			payment.setDatePayment(cal.getTime());
		}
	}

