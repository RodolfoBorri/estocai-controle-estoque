package com.uem.controle.estoque.relatorios;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;

public class RelatorioBase {

	protected String criaCabecalhoRelatorio(String tituloRelatorio, String dataSistema, int tamanhoSeparador) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("ESTOCAÍ COMERCIO DE PRODUTO LTDA.\n");
		stringBuilder.append("SISTEMA DE CONTROLE DE ESTOQUE\n\n");
		stringBuilder.append("DATA: " + dataSistema + "\t\t\t " + tituloRelatorio + "\n");
		stringBuilder.append(criaSeparador(tamanhoSeparador) + "\n");
		
		return stringBuilder.toString();
	}
	
	protected String criaSeparador(int tamanhoSeparador) { //116
		StringBuilder separador = new StringBuilder();
		
		for(int i = 0; i<tamanhoSeparador; i++) {
			separador.append("-");
		}
		return separador.toString();
	}
	
	protected void salvaArquivoEmTxt(String path, String relatorio, String nomeArquivo) throws ExceptionHandler {
		try {
		
			File file = new File(path + nomeArquivo);
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.write(relatorio);
			pw.flush();
			pw.close();
		} 
		catch (Exception e) {
			throw new ExceptionHandler(ExceptionEnum.CE_12, path);
		}		
	}	
	
	protected String concatenaEspacos(String nomeProduto, int limite) {
		StringBuilder espacos = new StringBuilder();
		
		int iteracao = limite - nomeProduto.length();
		
		for(int i = 0; i< iteracao; i++) {
			espacos.append(" ");
		}
		
		return espacos.toString();
	}
	
	protected String manipulaPath(String path) throws ExceptionHandler {
		File dir = null;		
		
		if(path.equals("")) 
			path = "./relatorios/";
		else
			path = path.replaceAll("\\\\", "/");
		
		if(!path.endsWith("/"))		//se o caminho final não possui a barra, é preciso concatenar
			path = path.concat("/");
		
		dir = new File(path);	
		
		if(!dir.exists()) 
			dir.mkdir();
		
		return path;
	}
}
