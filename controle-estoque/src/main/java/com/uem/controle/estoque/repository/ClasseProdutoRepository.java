package com.uem.controle.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.controle.estoque.entity.ClasseProduto;

public interface ClasseProdutoRepository extends JpaRepository<ClasseProduto, Long>{

	Optional<ClasseProduto> findByDescricao(String descricao);

}
