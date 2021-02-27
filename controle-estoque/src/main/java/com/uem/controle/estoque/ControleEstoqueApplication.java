package com.uem.controle.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.uem.controle.estoque.view.MenuPrincipalView;

@SpringBootApplication
public class ControleEstoqueApplication {
	
	public static void main(String[] args) {
		
		SpringApplication application = new SpringApplicationBuilder(ControleEstoqueApplication.class)
																				   .headless(false)
																				   .build();				
		
		ApplicationContext context = application.run(args);			   			 //startando o spring
	    
	    MenuPrincipalView a = context.getBean(MenuPrincipalView.class);		     //chamando a tela inicial
	    a.frame.setVisible(true);
	}
}
