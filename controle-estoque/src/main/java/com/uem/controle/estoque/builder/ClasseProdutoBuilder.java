package com.uem.controle.estoque.builder;

import com.uem.controle.estoque.entity.ClasseProduto;

public class ClasseProdutoBuilder {

	private String descricao;

	public ClasseProdutoBuilder setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public ClasseProdutoBuilder(String descricao, String codigo) {
		super();
		this.descricao = descricao;
	}

	public ClasseProdutoBuilder() { }
	
	public ClasseProduto build() {
		return new ClasseProduto(descricao);
	}
}
