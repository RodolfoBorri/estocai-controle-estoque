package com.uem.controle.estoque.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

	private String nome;

	private BigDecimal precoUnitario;
	
	private ClasseProdutoDTO classeProduto;

	private UnidadeMedidaDTO unidadeMedida;

	private Integer quantidadeEstoque;
	
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

	public UnidadeMedidaDTO getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedidaDTO unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public ProdutoDTO(String nome, BigDecimal precoUnitario, UnidadeMedidaDTO unidadeMedida, int quantidadeEstoque,
			BigDecimal valorTotalEstoque, ClasseProdutoDTO classeProduto) {
		super();
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.unidadeMedida = unidadeMedida;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorTotalEstoque = valorTotalEstoque;
		this.classeProduto = classeProduto;
	}

	public ProdutoDTO() { }

	public ClasseProdutoDTO getClasseProduto() {
		return classeProduto;
	}

	public void setClasseProduto(ClasseProdutoDTO classeProduto) {
		this.classeProduto = classeProduto;
	}

}
