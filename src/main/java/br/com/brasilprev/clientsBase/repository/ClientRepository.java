package br.com.brasilprev.clientsBase.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.clientsBase.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Page<Client> findByCpf(String cpfClient, Pageable pageable);
	Page<Client> findByName(String nameClient, Pageable pageable);
	

}
