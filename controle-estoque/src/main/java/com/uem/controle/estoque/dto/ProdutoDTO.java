package com.uem.controle.estoque.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

	private String nome;

	private BigDecimal precoUnitario;

	private String unidadeMedida;

	private int quantidadeEstoque;
	
	private BigDecimal valorTotalEstoque;

	public BigDecimal getValorTotalEstoque() {
		return valorTotalEstoque;
	}

	public void setValorTotalEstoque(BigDecimal valorTotalEstoque) {
		this.valorTotalEstoque = valorTotalEstoque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public ProdutoDTO(String nome, BigDecimal precoUnitario, String unidadeMedida, int quantidadeEstoque,
			BigDecimal valorTotalEstoque) {
		super();
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.unidadeMedida = unidadeMedida;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorTotalEstoque = valorTotalEstoque;
	}

	public ProdutoDTO() { }

}
