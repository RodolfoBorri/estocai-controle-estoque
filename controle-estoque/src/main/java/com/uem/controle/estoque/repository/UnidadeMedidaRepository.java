package com.uem.controle.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.controle.estoque.entity.UnidadeMedida;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>{

	Optional<UnidadeMedida> findByUnidade(String unidade);

}
