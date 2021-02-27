package com.uem.controle.estoque.builder;

import java.util.Date;

import com.uem.controle.estoque.entity.Movimentacao;
import com.uem.controle.estoque.entity.Produto;

public class MovimentacaoBuilder {

	private String usuarioMovimentacao;
	
	private Date dataMovimentacao;
	
	private Produto produto;
	
	private Integer quantidade;
	
	private String tipoMovimentacao;
	
	public MovimentacaoBuilder() { }
	
	public MovimentacaoBuilder(String usuarioMovimentacao, Date dataMovimentacao, Produto produto,
			Integer quantidade, String tipoMovimentacao) {
		super();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.produto = produto;
		this.quantidade = quantidade;
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public MovimentacaoBuilder setProduto(Produto produto) {
		this.produto = produto;
		return this;
	}

	public MovimentacaoBuilder setUsuarioMovimentacao(String usuarioMovimentacao) {
		this.usuarioMovimentacao = usuarioMovimentacao;
		return this;
	}

	public MovimentacaoBuilder setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
		return this;
	}
	
	public MovimentacaoBuilder setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}

	public MovimentacaoBuilder setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
		return this;
	}
	

	public Movimentacao build() {
		return new Movimentacao(usuarioMovimentacao, dataMovimentacao, quantidade,
				tipoMovimentacao, produto);
	}
}

