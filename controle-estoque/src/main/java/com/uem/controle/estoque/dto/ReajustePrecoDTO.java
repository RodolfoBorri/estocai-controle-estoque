package com.uem.controle.estoque.dto;

public class ReajustePrecoDTO {

	private String usuarioReajuste;

	private String tipoReajuste;

	private Integer percentualReajuste;

	private ProdutoDTO produto;

	public ReajustePrecoDTO() {	}

	public ReajustePrecoDTO(String usuarioReajuste, String tipoReajuste, Integer percentualReajuste,
			ProdutoDTO produto) {
		super();
		this.usuarioReajuste = usuarioReajuste;
		this.tipoReajuste = tipoReajuste;
		this.percentualReajuste = percentualReajuste;
		this.produto = produto;
	}

	public String getUsuarioReajuste() {
		return usuarioReajuste;
	}

	public void setUsuarioReajuste(String usuarioReajuste) {
		this.usuarioReajuste = usuarioReajuste;
	}

	public String getTipoReajuste() {
		return tipoReajuste;
	}

	public void setTipoReajuste(String tipoReajuste) {
		this.tipoReajuste = tipoReajuste;
	}

	public Integer getPercentualReajuste() {
		return percentualReajuste;
	}

	public void setPercentualReajuste(Integer percentualReajuste) {
		this.percentualReajuste = percentualReajuste;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
