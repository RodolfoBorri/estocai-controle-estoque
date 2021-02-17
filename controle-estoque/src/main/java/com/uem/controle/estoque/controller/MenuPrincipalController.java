package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.view.MenuPrincipalView;

@Controller
public class MenuPrincipalController extends ControllerBase{
	
	@Autowired
	ProdutoController produtoController;
	
	public void run() {
		
		try {
			
			do {
				clearScreen();
				MenuPrincipalView.buildMenuView();
				
				try {
					escolha = recebeEscolha();
				}
				catch(Exception e) {
					continue;
				}
				
				switch(escolha) {
					case 0: break;
					
					case 1:
						produtoController.escolhaProduto();		//chama o controller do produto
						break;
						
					default:
						numeroEscolhaInvalido();
						break;
				}
				
			} while(escolha != 0);
	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro generico, contate o suporte.");
		}
	}

}
