package com.uem.controle.estoque.relatorios;

import java.math.BigDecimal;
import java.util.List;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.exception.ExceptionHandler;

public class RelatorioBalancoFinanceiro extends RelatorioBase{
	
	private int tamanhoSeparador = 179;
	private int limiteColuna1 = 64;
	private int limiteColuna2 = 29;
	private int limiteColuna3 = 45;
	private int limiteColuna4 = 33;	

	public String criaCabecalhoRelatorioBalanco(String dataFormatada) {
		StringBuilder headerBuilder = new StringBuilder();
		
		String titulo = "BALANÇO FÍSICO-FINANCEIRO";
		
		headerBuilder.append(criaCabecalhoRelatorio(titulo, dataFormatada, tamanhoSeparador));
		headerBuilder.append("PRODUTO\t\t\t\t\t\t\t\t");
		headerBuilder.append("UND\t\t\t");
		headerBuilder.append("PREÇO UNITÁRIO\t\t\t\t\t");
		headerBuilder.append("QTDE\t\t\t\t");
		headerBuilder.append("PREÇO TOTAL\n");
		headerBuilder.append(criaSeparador(tamanhoSeparador) + "\n");
		
		return headerBuilder.toString();
	}

	public String criaDetalheRelatorioBalanco(List<ProdutoDTO> produtos) {
		StringBuilder bodyBuilder = new StringBuilder();
		int itensEstoque = 0; 
		BigDecimal valorTotalEstoque = new BigDecimal("0");
		
		for(ProdutoDTO produto : produtos) {
				String nomeProduto = produto.getNome();
				String precoUnitario = produto.getPrecoUnitario().toString();
				String unidadeMedida = produto.getUnidadeMedida();
				String quantidade = produto.getQuantidadeEstoque().toString();
				BigDecimal precoTotal = produto.getValorTotalEstoque();
				
				bodyBuilder.append(nomeProduto + concatenaEspacos(nomeProduto, limiteColuna1));			
				
				bodyBuilder.append(unidadeMedida + concatenaEspacos(unidadeMedida, limiteColuna2));			
				
				bodyBuilder.append(precoUnitario + concatenaEspacos(precoUnitario, limiteColuna3));
				
				bodyBuilder.append(quantidade + concatenaEspacos(quantidade, limiteColuna4));
				
				bodyBuilder.append(precoTotal + concatenaEspacos(precoTotal.toString(), 0) + "\n");
				
				itensEstoque++;
				valorTotalEstoque = valorTotalEstoque.add(precoTotal);
		}
		
		bodyBuilder.append(criaSeparador(tamanhoSeparador) + "\n");
		
		bodyBuilder.append("Total de itens no estoque : " + itensEstoque + "\n");
		bodyBuilder.append("Valor total do estoque : " + valorTotalEstoque + "\n");
	
		
		return bodyBuilder.toString();
	}

	public void salvaRelatorioBalanco(String path, String relatorio) throws ExceptionHandler {
		path = manipulaPath(path);
		salvaArquivoEmTxt(path, relatorio, "BalancoFisicoFinanceiro.txt");		
	}

}
