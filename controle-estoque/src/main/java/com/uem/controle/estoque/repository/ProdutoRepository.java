package com.uem.controle.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uem.controle.estoque.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findByNome(String nome);
	
	List<Produto> findAllByOrderByNomeAsc();
	
	@Query(value = "select p.*                 " + 
			"from ce_produto p,                " + 
			"	  ce_classe_produto cp,        " + 
			"	  ce_unidade_medida um         " + 
			"where p.id_classe_produto = cp.id " + 
			"and   p.id_unidade = um.id        " + 
			"order by cp.descricao,            " + 
			"		  um.unidade;              ", nativeQuery = true)
	List<Produto> consultaSaldosPorClasseTotalizandoUnidade();
	
}
