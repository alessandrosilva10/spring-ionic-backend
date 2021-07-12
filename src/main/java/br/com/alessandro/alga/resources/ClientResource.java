package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.Client;
import br.com.alessandro.alga.service.ClientService;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientResource {
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public List<Client> getAllCities() {
		return clientService.getAllClients();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getOneCity(@PathVariable Long id) {
		return clientService.getOneClient(id) != null ? ResponseEntity.ok(clientService.getOneClient(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneCity(@PathVariable Long id) {
		clientService.delete(id);
	}
}
