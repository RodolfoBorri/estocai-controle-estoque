package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.service.RelatorioService;

@Controller
public class RelatorioController {

	@Autowired
	RelatorioService relatorioService;
	
	public String verificaCaminho(String path) {
		return relatorioService.verificaCaminho(path);
	}

	public String geraRelatorioListaPrecos(String path) {
		return relatorioService.geraRelatorioListaPrecos(path);
	}

	public String geraRelatorioBalancoFinanceiro(String path) {
		return relatorioService.geraRelatorioBalancoFinanceiro(path);

	}
}
