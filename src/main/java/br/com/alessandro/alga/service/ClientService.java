package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.Client;
import br.com.alessandro.alga.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client getOneClient(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		clientRepository.deleteById(id);
	}
}
