package com.uem.controle.estoque.enumerator;

public enum ExceptionEnum {

	CE_1("Produto de Nome %s já cadastrado.");
	
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	private ExceptionEnum(String codigo) {
		this.codigo = codigo;
	}    
}
