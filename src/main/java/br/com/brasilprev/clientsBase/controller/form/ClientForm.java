package br.com.brasilprev.clientsBase.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.brasilprev.clientsBase.model.Client;


public class ClientForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String name;
	
	@NotNull @NotEmpty @Length(min = 11, max = 11)  @CPF
	private String cpf;
	
	@NotNull @NotEmpty
	private String address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
	public Client convert() {
		return new Client(name, cpf, address);
	}
	
	
	
	

	
}
