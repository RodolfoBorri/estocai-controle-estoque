package com.uem.controle.estoque.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CE_PRODUTO") //CE é de Controle Estoque
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "PRECO_UNITARIO", nullable = false)
	private BigDecimal precoUnitario;
	
	@OneToOne
	@JoinColumn(name = "id_unidade", nullable = false, referencedColumnName = "id")
	private UnidadeMedida unidadeMedida;
	
	@Column(name = "QUANTIDADE_ESTOQUE", nullable = false)
	private Integer quantidadeEstoque;
	
	@Column(name = "VALOR_TOTAL_ESTOQUE", nullable = false)
	private BigDecimal valorTotalEstoque;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Movimentacao> movimentacoes;
	
	public Produto() { }

	public Produto(String nome, BigDecimal precoUnitario, UnidadeMedida unidadeMedida, int quantidadeEstoque) {
		super();
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.unidadeMedida = unidadeMedida;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorTotalEstoque = precoUnitario.multiply(new BigDecimal(quantidadeEstoque));
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return precoUnitario;
	}
	public void setPreco(BigDecimal preco) {
		this.precoUnitario = preco;
	}
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public BigDecimal getValorTotalEstoque() {
		return valorTotalEstoque;
	}
	public void setValorTotalEstoque(BigDecimal valorTotalEstoque) {
		this.valorTotalEstoque = valorTotalEstoque;
	}
}
