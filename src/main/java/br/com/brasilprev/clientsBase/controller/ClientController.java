package br.com.brasilprev.clientsBase.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilprev.clientsBase.controller.dto.ClientDto;
import br.com.brasilprev.clientsBase.controller.form.ClientForm;
import br.com.brasilprev.clientsBase.controller.form.UpdateClientForm;
import br.com.brasilprev.clientsBase.model.Client;
import br.com.brasilprev.clientsBase.repository.ClientRepository;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;


@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	//@Cacheable(value = "listClients")
	public Page	<ClientDto> list(@RequestParam(required = false) String cpfClient, String nameClient,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10 )Pageable pageable){
		if(cpfClient != null) {
			Page<Client> clients = clientRepository.findByCpf(cpfClient,pageable);
			return ClientDto.convert(clients);
		}else if(nameClient != null){
			Page<Client> clients = clientRepository.findByName(nameClient, pageable);
			return ClientDto.convert(clients);
		}else {
			Page<Client> clients = clientRepository.findAll(pageable);
			return ClientDto.convert(clients);
		
		}
	}
		
	
	@PostMapping
	@Transactional
	//@CacheEvict(value = "listClients", allEntries = true)
	public ResponseEntity<ClientDto> register(@RequestBody @Valid ClientForm form, UriComponentsBuilder uriBuilder) {
		Client client = form.convert();
		clientRepository.save(client);
		
		URI uri = uriBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDto(client));
	}
	
	


	@GetMapping("/{id}")
	@Transactional
	//@CacheEvict(value = "listClients", allEntries = true)
	public ResponseEntity<ClientDto> detail(@PathVariable Long id) {
		Optional<Client> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new ClientDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	//@CacheEvict(value = "listClients", allEntries = true)
	public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody @Valid UpdateClientForm form) {
		Optional<Client> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			Client client = form.update(id, clientRepository);
			return ResponseEntity.ok(new ClientDto(client));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	//@CacheEvict(value = "listClients", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Client> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
