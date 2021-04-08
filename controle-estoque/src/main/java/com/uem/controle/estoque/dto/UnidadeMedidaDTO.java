package com.uem.controle.estoque.dto;

public class UnidadeMedidaDTO {

	private String descricao;
	
	private String unidade;
	
	public UnidadeMedidaDTO() { }

	public UnidadeMedidaDTO(String descricao, String unidade) {
		super();
		this.descricao = descricao;
		this.unidade = unidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public String toString() {
	    return getUnidade() + " - " + getDescricao();
	}
}
