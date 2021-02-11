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
	@JoinColumn
	private Produto produto;
}
