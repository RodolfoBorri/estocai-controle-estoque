package com.uem.controle.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Transactional
	public void cadastra(Produto produto) {
		try {
			validaCadastro(produto);
			produtoRepository.save(produto);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void validaCadastro(Produto produto) throws ExceptionHandler {
		if(produtoRepository.findByNome(produto.getNome()).isPresent()) {
			throw new ExceptionHandler(ExceptionEnum.CE_1, produto.getNome());
		}
	}
	
}
