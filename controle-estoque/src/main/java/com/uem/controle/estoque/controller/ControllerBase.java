package com.uem.controle.estoque.controller;

import java.util.Scanner;

import javax.annotation.PostConstruct;

import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;

public class ControllerBase {

	protected Scanner scanner;
	
	protected int escolha; 
	
	protected void numeroEscolhaInvalido() throws ExceptionHandler {
		escolha = -1;
		throw new ExceptionHandler(ExceptionEnum.CE_2, null);		
	}
	
	protected static void clearScreen() {  
	    System.out.print("\n\n\n\n");  
	    System.out.flush();  
	}
	
	protected int recebeEscolha() throws ExceptionHandler, InterruptedException {
		System.out.print("Opção : ");
		
		String keyboardInput = scanner.next();
		
		try {
			escolha = Integer.parseInt(keyboardInput);
		}
		catch(Exception e) {
			numeroEscolhaInvalido();
			Thread.sleep(5000);
			escolha = -1;
		}
		
		return escolha;
	}
	
	@PostConstruct
	public void instanciaScanner() {
		scanner = new Scanner(System.in);
	}
}
