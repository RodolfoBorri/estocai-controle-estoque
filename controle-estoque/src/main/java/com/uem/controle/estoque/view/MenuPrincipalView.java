package com.uem.controle.estoque.view;

public class MenuPrincipalView {
	
	public static void buildMenuView() {
		StringBuilder menu = new StringBuilder();
		menu.append(HeaderView.buildHeader());
		menu.append("          MENU PRINCIPAL             \n");
		menu.append("                                     \n");
		menu.append("     1 - CADASTRO DE PRODUTOS        \n");
		menu.append("     2 - MOVIMENTACAO				  \n");
		menu.append("     3 - REAJUSTE DE PREÇOS          \n");
		menu.append("     4 - RELATORIOS				  \n");
		menu.append("     0 - FINALIZAR					  \n");
		menu.append("                                     \n");
		
		System.out.println(menu.toString());
	}
	
}
