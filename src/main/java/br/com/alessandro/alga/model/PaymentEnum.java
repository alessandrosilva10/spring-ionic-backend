package br.com.alessandro.alga.model;

public enum PaymentEnum {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private PaymentEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentEnum toEnum(Integer cod) {
		if(cod ==  null) {
			return null;
		}
		
		for(PaymentEnum x : PaymentEnum.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido");
	}
}
