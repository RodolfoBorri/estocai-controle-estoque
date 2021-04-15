package com.uem.controle.estoque.dto;

public class ClasseProdutoDTO {
	
	private String descricao;
	
	private String codigo;
	
	public ClasseProdutoDTO() { }

	public ClasseProdutoDTO(String descricao, String codigo) {
		super();
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
	    return getCodigo() + " - " + getDescricao();
	}
}
