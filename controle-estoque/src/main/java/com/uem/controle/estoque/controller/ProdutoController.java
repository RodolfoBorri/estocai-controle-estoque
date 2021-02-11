package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.service.ProdutoService;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	public void cadastraProduto(Produto produto) {
		produtoService.cadastra(produto);
	}
}
