package com.uem.controle.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.controller.MenuPrincipalController;

@Component
public class AppStartupRunner implements ApplicationRunner{
	
	@Autowired
	MenuPrincipalController menuPrincipalController;
	
	@Override
	public void run(ApplicationArguments args) {
		menuPrincipalController.run();
	}
}
