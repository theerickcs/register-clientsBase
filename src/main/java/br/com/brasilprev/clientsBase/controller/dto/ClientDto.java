package br.com.brasilprev.clientsBase.controller.dto;

import org.springframework.data.domain.Page;

import br.com.brasilprev.clientsBase.model.Client;

public class ClientDto {
	
	private Long id;
	private String name;
	private String cpf;
	private String address;
	
	public ClientDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.address = client.getAddress();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCpf() {
		return cpf;
	}
	public String getAddress() {
		return address;
	}

	public static Page<ClientDto> convert(Page<Client> clients) {
		return clients.map(ClientDto::new);
	}
	
	

}
