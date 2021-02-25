package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.service.ProdutoService;

@Controller
public class ProdutoController extends ControllerBase{
	
	@Autowired
	ProdutoService produtoService;
	
	public void cadastraProduto(ProdutoDTO produtoDto) {
		produtoService.cadastra(produtoDto);
	}

	public String validaCamposProduto(ProdutoDTO produtoDto, String fluxo) {
		return produtoService.validaCamposProduto(produtoDto, fluxo);		
	}
	
	public Produto buscaProdutoPorNome(String nome) throws ExceptionHandler {
		return produtoService.buscaProdutoPorNome(nome);
	}

	public void exclui(ProdutoDTO produtoDto) {
		produtoService.exclui(produtoDto);
	}

	public void alteraProduto(ProdutoDTO produtoDto) {
		produtoService.altera(produtoDto);
	}
}
