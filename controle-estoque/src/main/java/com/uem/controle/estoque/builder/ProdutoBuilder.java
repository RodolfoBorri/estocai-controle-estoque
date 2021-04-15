package com.uem.controle.estoque.builder;

import java.math.BigDecimal;

import com.uem.controle.estoque.entity.ClasseProduto;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.entity.UnidadeMedida;

public class ProdutoBuilder {

	private String nome;

	private BigDecimal precoUnitario;

	private UnidadeMedida unidadeMedida;
	
	private ClasseProduto classeProduto;

	private int quantidadeEstoque;	
	
	public ProdutoBuilder() { }
	
	public ProdutoBuilder(String nome, BigDecimal precoUnitario, UnidadeMedida unidadeMedida, int quantidadeEstoque, ClasseProduto classeProduto) {
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.unidadeMedida = unidadeMedida;
		this.quantidadeEstoque = quantidadeEstoque;
		this.classeProduto = classeProduto;
	}

	public ProdutoBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ProdutoBuilder setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
		return this;
	}

	public ProdutoBuilder setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
		return this;
	}

	public ProdutoBuilder setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
		return this;
	}
	
	public ProdutoBuilder setClasseProduto(ClasseProduto classeProduto) {
		this.classeProduto = classeProduto;
		return this;
	}

	public Produto build() {
		return new Produto(nome, precoUnitario, unidadeMedida, quantidadeEstoque, classeProduto);
	}
	
	
	
	
}
