package com.uem.controle.estoque.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CE_MOVIMENTACAO")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USUARIO_MOVIMENTACAO")
	private String usuarioMovimentacao;

	@Column(name = "DATA_MOVIMENTACAO",nullable = false)
	private LocalDateTime dataMovimentacao;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	public Movimentacao() { }
	
	public Movimentacao(String usuarioMovimentacao, LocalDateTime dataMovimentacao, Produto produto) {
		super();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuarioMovimentacao() {
		return usuarioMovimentacao;
	}

	public void setUsuarioMovimentacao(String usuarioMovimentacao) {
		this.usuarioMovimentacao = usuarioMovimentacao;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
