package com.uem.controle.estoque.builder;

import java.time.LocalDateTime;

import com.uem.controle.estoque.entity.Movimentacao;
import com.uem.controle.estoque.entity.Produto;

public class MovimentacaoBuilder {

	private String usuarioMovimentacao;
	
	private LocalDateTime dataMovimentacao;
	
	private Produto produto;
	
	public MovimentacaoBuilder() { }

	public MovimentacaoBuilder(String usuarioMovimentacao, LocalDateTime dataMovimentacao, Produto produto) {
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.produto = produto;
	}
	
	public MovimentacaoBuilder setProduto(Produto produto) {
		this.produto = produto;
		return this;
	}

	public MovimentacaoBuilder setUsuarioMovimentacao(String usuarioMovimentacao) {
		this.usuarioMovimentacao = usuarioMovimentacao;
		return this;
	}

	public MovimentacaoBuilder setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
		return this;
	}

	public Movimentacao build() {
		return new Movimentacao(usuarioMovimentacao, dataMovimentacao, produto);
	}
	
}

