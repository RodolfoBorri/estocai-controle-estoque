package com.uem.controle.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.UnidadeMedidaDTO;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.service.UnidadeMedidaService;

@Controller
public class UnidadeMedidaController {

	@Autowired
	UnidadeMedidaService unidadeMedidaService;
	
	public String validaCamposUnidade(UnidadeMedidaDTO unidadeMedidaDTO, String fluxo) {
		return unidadeMedidaService.validaCamposUnidade(unidadeMedidaDTO, fluxo);
	}

	public void cadastraUnidade(UnidadeMedidaDTO unidadeMedidaDTO) {
		unidadeMedidaService.cadastra(unidadeMedidaDTO);		
	}

	public UnidadeMedidaDTO buscaUnidade(String unidade) throws ExceptionHandler {
		return unidadeMedidaService.buscaUnidade(unidade);
	}

	public List<UnidadeMedidaDTO> buscaTodasUnidades() {
		return unidadeMedidaService.buscaTodasUnidades();
	}

	public String removeUnidade(String unidade) {
		return unidadeMedidaService.removeUnidade(unidade);		
	}

	public void alteraUnidade(UnidadeMedidaDTO unidadeMedidaDTO) {
		unidadeMedidaService.altera(unidadeMedidaDTO);
	}

}
