package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.State;
import br.com.alessandro.alga.service.StateService;

@RestController
@RequestMapping(value = "/api/v1/states")
public class StateResource {
	@Autowired
	private StateService stateService;
	
	@GetMapping
	public List<State> getAllStates() {
		return stateService.getAllStates();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> getOneState(@PathVariable Long id) {
		return stateService.getOneState(id) != null ? ResponseEntity.ok(stateService.getOneState(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneState(@PathVariable Long id) {
		stateService.delete(id);
	}
}
