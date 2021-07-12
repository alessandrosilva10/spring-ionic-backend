package br.com.alessandro.alga.model;

public enum ClientEnum {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	private ClientEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientEnum toEnum(Integer cod) {
		if(cod ==  null) {
			return null;
		}
		
		for(ClientEnum x : ClientEnum.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido");
	}
}
