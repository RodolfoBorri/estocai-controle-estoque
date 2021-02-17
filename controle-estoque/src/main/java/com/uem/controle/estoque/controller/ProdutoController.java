package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.service.ProdutoService;
import com.uem.controle.estoque.view.ProdutoView;

@Controller
public class ProdutoController extends ControllerBase{
	
	@Autowired
	ProdutoService produtoService;
	
	public void cadastraProduto(Produto produto) {
		produtoService.cadastra(produto);
	}
	
	public void escolhaProduto() throws InterruptedException, ExceptionHandler {
		
		do {
			try {
				ProdutoView.buildViewProduto();
				escolha = recebeEscolha();
			}
			catch(Exception e) {
				continue;
			}			
			
			switch(escolha) {
				case 0: break;
				
				case 1:
					inclusaoProduto();
					break;
					
				default:
					numeroEscolhaInvalido();
					break;
			}
			
		} while(escolha != 0);
		
		escolha = -1;
	}
	
	public void inclusaoProduto() {
		String nome;
		
		
		ProdutoView.buildHeaderInclusaoProduto();
		
		
	}	
}
