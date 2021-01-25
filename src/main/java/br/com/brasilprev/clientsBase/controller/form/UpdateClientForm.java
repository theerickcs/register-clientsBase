package br.com.brasilprev.clientsBase.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.brasilprev.clientsBase.model.Client;
import br.com.brasilprev.clientsBase.repository.ClientRepository;

public class UpdateClientForm {

	@NotNull @NotEmpty @Length(min = 3)
	private String name;
	
	@NotNull @NotEmpty
	private String address;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Client update(Long id, ClientRepository clientRepository) {
		Client client = clientRepository.getOne(id);
		client.setName(this.name);
		client.setAddress(this.address);
		return client;
	}
	
	
	
}
