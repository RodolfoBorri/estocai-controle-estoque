package com.uem.controle.estoque;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.entity.Produto;

@Component
public class AppStartupRunner implements ApplicationRunner{

	@Autowired
	ProdutoController produtoController;
	
	@Override
	public void run(ApplicationArguments args) {
		Produto produto = new Produto("nome", new BigDecimal(4000), "KG", 2); 
		produtoController.cadastraProduto(produto);
	}

}
