package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.service.ProdutoService;

@Controller
public class ProdutoController extends ControllerBase{
	
	@Autowired
	ProdutoService produtoService;
	
	public void cadastraProduto(ProdutoDTO produto) {
		produtoService.cadastra(produto);
	}

	public String validaInsercao(ProdutoDTO produtoDto) {
		return produtoService.validaInsercao(produtoDto);		
	}	
}
