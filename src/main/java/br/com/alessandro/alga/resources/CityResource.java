package br.com.alessandro.alga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessandro.alga.model.City;
import br.com.alessandro.alga.service.CityService;

@RestController
@RequestMapping(value = "/api/v1/cities")
public class CityResource {
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public List<City> getAllCities() {
		return cityService.getAllCities();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> getOneCity(@PathVariable Long id) {
		return cityService.getOneCity(id) != null ? ResponseEntity.ok(cityService.getOneCity(id)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneCity(@PathVariable Long id) {
		cityService.delete(id);
	}
}
