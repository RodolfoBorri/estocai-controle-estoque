package com.uem.controle.estoque.service;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.relatorios.RelatorioBalancoFinanceiro;
import com.uem.controle.estoque.relatorios.RelatorioListaPrecos;

@Service
public class RelatorioService {

	@Autowired
	ProdutoService produtoService;
	
	public String verificaCaminho(String path) {
		String retorno = "";

		try {
			Paths.get(path);

		} catch (InvalidPathException e) {
			retorno = ExceptionEnum.CE_11.getCodigo();
		}

		return retorno;
	}

	public String geraRelatorioListaPrecos(String path) {
		String errors = "";
		
		try {

			RelatorioListaPrecos relatorioPrecos = new RelatorioListaPrecos();
			List<ProdutoDTO> produtos = produtoService.buscaProdutosEOrdenaPorNome();
			
			LocalDateTime dataAtual = LocalDateTime.now();
			String dataFormatada = dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			StringBuilder relatorioFinal = new StringBuilder();
			relatorioFinal.append(relatorioPrecos.criaCabecalhoRelatorioPreco(dataFormatada));
			relatorioFinal.append(relatorioPrecos.criaDetalheRelatorioPreco(produtos));
		
			relatorioPrecos.salvaRelatorioPreco(path, relatorioFinal.toString());
		}
		catch(Exception e) {
			errors = e.getMessage();
		}
		
		return errors;
	}

	public String geraRelatorioBalancoFinanceiro(String path) {
		String errors = "";
		
		try {
			RelatorioBalancoFinanceiro relatorioBalanco = new RelatorioBalancoFinanceiro();			
			List<ProdutoDTO> produtos = produtoService.buscaProdutosEOrdenaPorNome();
			
			LocalDateTime dataAtual = LocalDateTime.now();
			String dataFormatada = dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			StringBuilder relatorioFinal = new StringBuilder();
			relatorioFinal.append(relatorioBalanco.criaCabecalhoRelatorioBalanco(dataFormatada));
			relatorioFinal.append(relatorioBalanco.criaDetalheRelatorioBalanco(produtos));
			
			relatorioBalanco.salvaRelatorioBalanco(path, relatorioFinal.toString());
		}
		catch(Exception e) {
			errors = e.getMessage();
		}
		
		return errors;
	}

}
