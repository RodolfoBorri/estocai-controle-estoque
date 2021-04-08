package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.MovimentacaoDTO;
import com.uem.controle.estoque.service.MovimentacaoService;

@Controller
public class MovimentacaoController {

	@Autowired
	MovimentacaoService movimentacaoService;
	
	public String validaCamposMovimentacao(MovimentacaoDTO movimentacaoDto) {
		return movimentacaoService.validaCamposMovimentacao(movimentacaoDto);		
	}

	public void realizaMovimentacao(MovimentacaoDTO movimentacaoDto) {
		movimentacaoService.realizaMovimentacao(movimentacaoDto);
	}

}
