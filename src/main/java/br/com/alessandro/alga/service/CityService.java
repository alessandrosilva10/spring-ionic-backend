package br.com.alessandro.alga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alessandro.alga.model.City;
import br.com.alessandro.alga.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository ;
	
	public City getOneCity(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

	public List<City> getAllCities() {
		return cityRepository.findAll();
	}
	
	public void delete(Long id) {
		cityRepository.deleteById(id);
	}

}
