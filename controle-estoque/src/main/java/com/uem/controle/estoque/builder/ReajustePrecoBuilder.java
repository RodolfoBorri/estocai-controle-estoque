package com.uem.controle.estoque.builder;

import java.math.BigDecimal;

import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.entity.ReajustePreco;

public class ReajustePrecoBuilder {
	
	private String usuarioReajuste;
	
	private String tipoReajuste;
	
	private BigDecimal percentualReajuste;
	
	private Produto produto;
	
	public ReajustePrecoBuilder() { }
	
	public ReajustePrecoBuilder(String usuarioReajuste, String tipoReajuste,
			BigDecimal percentualReajuste, Produto produto) {
		super();
		this.usuarioReajuste = usuarioReajuste;
		this.tipoReajuste = tipoReajuste;
		this.percentualReajuste = percentualReajuste;
		this.produto = produto;
	}

	public ReajustePrecoBuilder setUsuarioReajuste(String usuarioReajuste) {
		this.usuarioReajuste = usuarioReajuste;
		return this;
	}

	public ReajustePrecoBuilder setTipoReajuste(String tipoReajuste) {
		this.tipoReajuste = tipoReajuste;
		return this;
	}

	public ReajustePrecoBuilder setPercentualReajuste(BigDecimal percentualReajuste) {
		this.percentualReajuste = percentualReajuste;
		return this;
	}

	public ReajustePrecoBuilder setProduto(Produto produto) {
		this.produto = produto;
		return this;
	}

	public ReajustePreco build() {
		return new ReajustePreco(usuarioReajuste, tipoReajuste, percentualReajuste, produto);
	}
}
