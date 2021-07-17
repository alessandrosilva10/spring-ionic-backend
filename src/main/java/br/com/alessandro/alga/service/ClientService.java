package br.com.alessandro.alga.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.dto.ClientDTO;
import br.com.alessandro.alga.dto.ClientNewDTO;
import br.com.alessandro.alga.model.Address;
import br.com.alessandro.alga.model.City;
import br.com.alessandro.alga.model.Client;
import br.com.alessandro.alga.model.ClientEnum;
import br.com.alessandro.alga.repository.AddressRepository;
import br.com.alessandro.alga.repository.CityRepository;
import br.com.alessandro.alga.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client getOneClient(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	public void delete(Long id){
		//Client client = clientRepository.findById(id).orElse(null);
		try {
			clientRepository.deleteById(id);
		}catch(ConstraintViolationException ex){
			throw new ConstraintViolationException("Cliente", null, null, ex.toString());
		}
	}
	
	@Transactional
	public Client createClient(Client client) {
		//client.setId(null);
		client = clientRepository.save(client);
		addressRepository.saveAll(client.getAddresses());
		return client;
	}
	
	@Transactional
	public Client updateClient(Long id, Client objDTO) {
		Client newObj = findClientById(id);

		newObj.getAddresses().clear();
		newObj.getAddresses().addAll(objDTO.getAddresses());
		newObj.getAddresses().forEach(c -> c.setClient(newObj));

		BeanUtils.copyProperties(objDTO, newObj, "id", "addresses");
		return clientRepository.save(newObj);
	}
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	
	public Client findClientById(Long id) {
		Client clientSaved = clientRepository.findById(id).orElse(null);
		if(clientSaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clientSaved;
	}

	public Page<Client> clientPagination(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO clientNewDTO) {
		Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), clientNewDTO.getCpfOrCnpj(), ClientEnum.toEnum(clientNewDTO.getType()));
		City city = cityRepository.findById(clientNewDTO.getCityId()).orElse(null);
		Address address = new Address(null, clientNewDTO.getPublic_place(), clientNewDTO.getNumber(), clientNewDTO.getComplement(), clientNewDTO.getDistrict(), clientNewDTO.getCep(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(clientNewDTO.getPhone1());
		if(clientNewDTO.getPhone2() != null) {
			client.getPhones().add(clientNewDTO.getPhone2());
		}
		
		if(clientNewDTO.getPhone3() != null) {
			client.getPhones().add(clientNewDTO.getPhone3());
		}
		return client;
	}
	/*
	private void updateClientData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	private void updatAddressData(Client newObj, Client obj) {
		newObj.setId((long) 1);
		newObj.setAddresses(obj.getAddresses());
	}*/
}

	
