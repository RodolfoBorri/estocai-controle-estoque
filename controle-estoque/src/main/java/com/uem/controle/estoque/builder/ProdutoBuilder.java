package com.uem.controle.estoque.builder;

import java.math.BigDecimal;

import com.uem.controle.estoque.entity.Produto;

public class ProdutoBuilder {

	private String nome;

	private BigDecimal precoUnitario;

	private String unidadeMedida;

	private int quantidadeEstoque;	
	
	public ProdutoBuilder() { }
	
	public ProdutoBuilder(String nome, BigDecimal precoUnitario, String unidadeMedida, int quantidadeEstoque) {
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.unidadeMedida = unidadeMedida;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public ProdutoBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ProdutoBuilder setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
		return this;
	}

	public ProdutoBuilder setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
		return this;
	}

	public ProdutoBuilder setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
		return this;
	}

	public Produto build() {
		return new Produto(nome, precoUnitario, unidadeMedida, quantidadeEstoque);
	}
	
	
	
	
}
