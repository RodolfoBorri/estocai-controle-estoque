package com.uem.controle.estoque;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.uem.controle.estoque.view.MenuPrincipalView;

@SpringBootApplication
public class ControleEstoqueApplication { // Classe principal do sistema, aqui nada é implementado

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(ControleEstoqueApplication.class)
																				   .headless(false)
																				   .run(args);
		
		MenuPrincipalView a = context.getBean(MenuPrincipalView.class);	//chamando a tela inicial
	    a.frame.setVisible(true);
	}
}
