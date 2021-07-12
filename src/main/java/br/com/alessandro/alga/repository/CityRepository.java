package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
