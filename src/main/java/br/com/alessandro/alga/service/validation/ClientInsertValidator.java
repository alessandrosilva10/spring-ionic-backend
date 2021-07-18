package br.com.alessandro.alga.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.alessandro.alga.dto.ClientNewDTO;
import br.com.alessandro.alga.exceptionhandler.FieldMessage;
import br.com.alessandro.alga.model.ClientEnum;
import br.com.alessandro.alga.service.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsertAnnotation, ClientNewDTO> {
	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
	List<FieldMessage> list = new ArrayList<>();
	
		if (objDto.getType().equals(ClientEnum.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
		}

		if (objDto.getType().equals(ClientEnum.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
		}
/*
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}*/
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}