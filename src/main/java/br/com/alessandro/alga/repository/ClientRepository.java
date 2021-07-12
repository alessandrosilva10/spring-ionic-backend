package br.com.alessandro.alga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alessandro.alga.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
