package com.uem.controle.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.controle.estoque.entity.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
