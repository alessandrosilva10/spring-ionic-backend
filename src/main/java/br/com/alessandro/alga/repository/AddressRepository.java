package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
