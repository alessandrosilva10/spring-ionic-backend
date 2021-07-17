package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Address;
import br.com.alessandro.alga.model.Client;
import br.com.alessandro.alga.repository.AddressRepository;
import org.springframework.beans.BeanUtils;

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
	
	public Address createAddress(Address address) {
		address.setId(null);
		return addressRepository.save(address);
	}
	
	public Address updateAddress(Long id, Client client) {
		Address savedAddress = addressRepository.findById(id).orElse(null);
		BeanUtils.copyProperties(client, savedAddress, "id");
		return addressRepository.save(savedAddress);
	}
}
