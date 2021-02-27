package com.uem.controle.estoque.dto;

import java.util.Date;

public class MovimentacaoDTO {
	
	private String usuarioMovimentacao;

	private Date dataMovimentacao;
	
	private ProdutoDTO produtoDto;
	
	private Integer quantidade;
	
	private String tipoMovimentacao;
	
	public MovimentacaoDTO() { }
	
	public MovimentacaoDTO(String usuarioMovimentacao, Date dataMovimentacao, ProdutoDTO produtoDto,
			Integer quantidade, String tipoMovimentacao) {
		super();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.produtoDto = produtoDto;
		this.quantidade = quantidade;
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public String getUsuarioMovimentacao() {
		return usuarioMovimentacao;
	}

	public void setUsuarioMovimentacao(String usuarioMovimentacao) {
		this.usuarioMovimentacao = usuarioMovimentacao;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public ProdutoDTO getProdutoDto() {
		return produtoDto;
	}

	public void setProdutoDto(ProdutoDTO produtoDto) {
		this.produtoDto = produtoDto;
	}
	
	
}
