package com.uem.controle.estoque.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CE_MOVIMENTACAO")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USUARIO_MOVIMENTACAO", nullable = false)
	private String usuarioMovimentacao;

	@Column(name = "DATA_MOVIMENTACAO", nullable = false)	
	private Date dataMovimentacao;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	@Column(name = "TIPO_MOVIMENTACAO", nullable = false, length = 1)
	private String tipoMovimentacao;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	public Movimentacao() { }

	public Movimentacao(String usuarioMovimentacao, Date dataMovimentacao, Integer quantidade,
			String tipoMovimentacao, Produto produto) {
		super();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.quantidade = quantidade;
		this.tipoMovimentacao = tipoMovimentacao;
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
