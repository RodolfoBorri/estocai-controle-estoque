package com.uem.controle.estoque.view;

public class ProdutoView {
	
	public static void buildViewProduto() {
		StringBuilder produtoMain = new StringBuilder();
		produtoMain.append(HeaderView.buildHeader());
		produtoMain.append("    	CADASTRO DE PRODUTOS         \n");
		produtoMain.append("                                     \n");
		produtoMain.append("		1 - INCLUSÃO				 \n");
		produtoMain.append("		2 - ALTERAÇÃO				 \n");
		produtoMain.append("		3 - CONSULTA				 \n");
		produtoMain.append("		4 - EXCLUSÃO				 \n");
		produtoMain.append("		0 - RETORNAR				 \n");
		
		
		System.out.println(produtoMain.toString());
	}
	
	public static void buildHeaderInclusaoProduto() {
		StringBuilder inclusaoProduto = new StringBuilder();
		inclusaoProduto.append(HeaderView.buildHeader());
		inclusaoProduto.append("    	INCLUSAO DE PRODUTO          \n");
		inclusaoProduto.append("                                     \n");		
		
		System.out.println(inclusaoProduto.toString());
	}
	
	public static void buildViewProdutoNome() {
		System.out.println("   Nome: ");
	}
	
	public static void buildViewProdutoPreco() {
		System.out.println("   Preco: ");
	}
	
	public static void buildViewProdutoUnidade() {
		System.out.println("   Unidade: ");
	}
	
	public static void buildViewProdutoQuantidade() {
		System.out.println("   Quantidade: ");
	}
	
}
