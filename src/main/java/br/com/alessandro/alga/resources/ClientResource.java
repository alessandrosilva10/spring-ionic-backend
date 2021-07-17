package br.com.alessandro.alga.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.dto.ClientDTO;
import br.com.alessandro.alga.dto.ClientNewDTO;
import br.com.alessandro.alga.event.URIEvent;
import br.com.alessandro.alga.model.Client;
import br.com.alessandro.alga.service.ClientService;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientResource {
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/*@GetMapping
	public List<ClientDTO> getAllClients() {
		List<Client> list = clientService.getAllClients();
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}*/
	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getOneClient(@PathVariable Long id) {
		return clientService.getOneClient(id) != null ? ResponseEntity.ok(clientService.getOneClient(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneClient(@PathVariable Long id) {
		clientService.delete(id);
	}
	
	/*@PutMapping("/{id}")
	public ResponseEntity<Client> updateCategory(@PathVariable Long id, @Validated @RequestBody ClientDTO client){
		Client clientSaved = clientService.fromDTO(client);
		clientSaved = clientService.updateClient(id, clientSaved);
		return ResponseEntity.ok(clientSaved);
	}*/


	@PutMapping("/{id}")
	public ResponseEntity<Client> updateCategory(@PathVariable Long id, @Validated @RequestBody ClientNewDTO clientDTO, HttpServletResponse response){
		Client objDTO = clientService.fromDTO(clientDTO);
		Client categorySaved = clientService.updateClient(id, objDTO);
		publisher.publishEvent(new URIEvent(this, response, categorySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
	
	@PostMapping
	public ResponseEntity<Client> createCategory(@Validated @RequestBody ClientNewDTO clientNewDTO, HttpServletResponse response){
		Client objDTO = clientService.fromDTO(clientNewDTO);
		Client categorySaved = clientService.createClient(objDTO);
		publisher.publishEvent(new URIEvent(this, response, categorySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
	
	/*@PostMapping
	public ResponseEntity<Client> createCategory(@Validated @RequestBody ClientDTO clientDTO, HttpServletResponse response){
		Client objDTO = clientService.fromDTO(clientDTO);
		Client categorySaved = clientService.createClient(objDTO);
		publisher.publishEvent(new URIEvent(this, response, categorySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}*/
	
	@GetMapping(value = "/page")
	public Page<ClientDTO> getPages(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="3") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction
		) {
		Page<Client> list = clientService.clientPagination(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDTO = list.map(obj -> new ClientDTO(obj));
		return listDTO;
	}
}
