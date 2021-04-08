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
	
	@Column(name = "TIPO_REAJUSTE", nullable = false)
	private String tipoReajuste;
	
	@Column(name = "PERCENTUAL_REAJUSTE", nullable = false)
	private BigDecimal percentualReajuste;
	
	@OneToOne
	@JoinColumn(name = "id_produto", nullable = true, referencedColumnName = "id")
	private Produto produto;

	public ReajustePreco() { }

	public ReajustePreco(String usuarioMovimentacao, String tipoReajuste, BigDecimal percentualReajuste,
			Produto produto) {
		super();
		this.dataMovimentacao = LocalDateTime.now();
		this.usuarioMovimentacao = usuarioMovimentacao;
		this.tipoReajuste = tipoReajuste;
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
		return tipoReajuste;
	}

	public void setTipoAlteracao(String tipoReajuste) {
		this.tipoReajuste = tipoReajuste;
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
