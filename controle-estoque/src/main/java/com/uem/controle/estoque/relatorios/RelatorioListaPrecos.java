package com.uem.controle.estoque.relatorios;

import java.util.List;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.exception.ExceptionHandler;

public class RelatorioListaPrecos extends RelatorioBase{

	private int tamanhoSeparador = 94;
	private int limiteColuna1 = 64;
	private int limiteColuna2 = 24;
	private int limiteColuna3 = 8;
	
	public String criaCabecalhoRelatorioPreco(String dataFormatada) {
		StringBuilder headerBuilder = new StringBuilder();
		
		String titulo = "LISTA DE PREÇOS";
		
		headerBuilder.append(criaCabecalhoRelatorio(titulo, dataFormatada, tamanhoSeparador));
		headerBuilder.append("PRODUTO\t\t\t\t\t\t\t\t");
		headerBuilder.append("UND\t\t\t");
		headerBuilder.append("PREÇO\n");
		headerBuilder.append(criaSeparador(tamanhoSeparador) + "\n");
		
		return headerBuilder.toString();
	}

	public String criaDetalheRelatorioPreco(List<ProdutoDTO> produtos) {
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(ProdutoDTO produto : produtos) {			
				String nomeProduto = produto.getNome();
				String precoUnitario = produto.getPrecoUnitario().toString();
				String unidadeMedida = produto.getUnidadeMedida().toString();
				
				bodyBuilder.append(nomeProduto + concatenaEspacos(nomeProduto, limiteColuna1));			
				
				bodyBuilder.append(unidadeMedida + concatenaEspacos(unidadeMedida, limiteColuna2));			
				
				bodyBuilder.append(precoUnitario + concatenaEspacos(precoUnitario, limiteColuna3) + "\n");			
		}
		
		bodyBuilder.append(criaSeparador(tamanhoSeparador) + "\n");
		
		return bodyBuilder.toString();
	}
	
	public void salvaRelatorioPreco(String path, String relatorio) throws ExceptionHandler {
		path = manipulaPath(path);
		salvaArquivoEmTxt(path, relatorio, "ListaDePrecos.txt");
	}
}
