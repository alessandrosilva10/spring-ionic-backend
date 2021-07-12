package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.alessandro.alga.model.State;
import br.com.alessandro.alga.repository.StateRepository;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository ;
	
	public State getOneState(Long id) {
		return stateRepository.findById(id).orElse(null);
	}

	public List<State> getAllStates() {
		return stateRepository.findAll();
	}
	
	public void delete(Long id) {
		stateRepository.deleteById(id);
	}
}
