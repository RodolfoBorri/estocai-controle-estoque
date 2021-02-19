package com.uem.controle.estoque.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CE_REAJUSTE_PRECO")
public class ReajustePreco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USUARIO_REAJUSTE", nullable = false)
	private String usuarioMovimentacao;

	@Column(name = "DATA_REAJUSTE", nullable = false)
	private LocalDateTime dataMovimentacao;
	
	@Column(name = "TIPO_ALTERACAO", nullable = false)
	private String tipoAlteracao;
	
	@Column(name = "PERCENTUAL_REAJUSTE", nullable = false)
	private BigDecimal percentualReajuste;
	
	@OneToOne
	@JoinColumn(name = "id_produto", nullable = true)
	private Produto produto;

	public ReajustePreco() { }

	public ReajustePreco(String usuarioMovimentacao, String tipoAlteracao, BigDecimal percentualReajuste,
			Produto produto) {
		super();
		this.dataMovimentacao = LocalDateTime.now();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.tipoAlteracao = tipoAlteracao;
		this.percentualReajuste = percentualReajuste;
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

	public String getTipoAlteracao() {
		return tipoAlteracao;
	}

	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}

	public BigDecimal getPercentualReajuste() {
		return percentualReajuste;
	}

	public void setPercentualReajuste(BigDecimal percentualReajuste) {
		this.percentualReajuste = percentualReajuste;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
