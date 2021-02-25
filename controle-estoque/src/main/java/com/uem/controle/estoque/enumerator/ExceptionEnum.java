package com.uem.controle.estoque.enumerator;

public enum ExceptionEnum {

	CE_1("Produto de nome %s já cadastrado."),
	CE_2("Por favor digite um numero valido para o menu."),
	CE_3("Por favor digite um preço maior do que 0."),
	CE_4("Por favor digite uma quantidade maior ou igual a zero."),
	CE_5("O nome do produto deve ser diferente de vazio."),
	CE_6("A unidade do produto deve ser diferente de vazio."),
	CE_7("Produto de nome %s não encontrado.");
	
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	private ExceptionEnum(String codigo) {
		this.codigo = codigo;
	}    
}
