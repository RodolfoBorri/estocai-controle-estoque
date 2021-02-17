package com.uem.controle.estoque.view;

public class HeaderView {
	
	public static String buildHeader() {
		StringBuilder header = new StringBuilder();
		header.append("                                     \n");
		header.append("  ESTOCAI COMERCIO DE PRODUTOS LTDA. \n");
		header.append("    SISTEMA DE CONTROLE DE ESTOQUE   \n");
		header.append("                                     \n");
		
		return header.toString();
	}
}
