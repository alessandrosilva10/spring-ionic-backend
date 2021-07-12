package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Address;
import br.com.alessandro.alga.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	public Address getOneAddress(Long id) {
		return addressRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		addressRepository.deleteById(id);
	}
}
