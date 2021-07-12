package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.Address;
import br.com.alessandro.alga.service.AddressService;

@RestController
@RequestMapping(value = "/api/v1/addresses")
public class AddressResource {
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public List<Address> getAllAddresses() {
		return addressService.getAllAddresses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> getOneAddress(@PathVariable Long id) {
		return addressService.getOneAddress(id) != null ? ResponseEntity.ok(addressService.getOneAddress(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneAddress(@PathVariable Long id) {
		addressService.delete(id);
	}
}
