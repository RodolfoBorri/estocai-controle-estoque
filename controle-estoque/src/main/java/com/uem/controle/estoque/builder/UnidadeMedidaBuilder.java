package com.uem.controle.estoque.builder;

import com.uem.controle.estoque.entity.UnidadeMedida;

public class UnidadeMedidaBuilder {
	
	private String descricao;
	
	private String unidade;

	public UnidadeMedidaBuilder setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public UnidadeMedidaBuilder setUnidade(String unidade) {
		this.unidade = unidade;
		return this;
	}

	public UnidadeMedidaBuilder(String descricao, String unidade) {
		super();
		this.descricao = descricao;
		this.unidade = unidade;
	}

	public UnidadeMedidaBuilder() { }
	
	public UnidadeMedida build() {
		return new UnidadeMedida(descricao, unidade);
	}
}
