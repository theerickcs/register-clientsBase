package br.com.brasilprev.clientsBase.config.validation;

public class FormErrorDto {
	private String field;
	private String error;
	
	public FormErrorDto(String field, String error) {
		this.error = error;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
		
	
	
}
