package com.uem.controle.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.ReajustePrecoDTO;
import com.uem.controle.estoque.service.ReajustePrecoService;

@Controller
public class ReajustePrecoController extends ControllerBase{

	@Autowired
	ReajustePrecoService reajustePrecoService;
	
	public String validaCamposReajuste(ReajustePrecoDTO reajusteDto) {
		return reajustePrecoService.validaCamposReajuste(reajusteDto);
	}

	public void realizaReajuste(ReajustePrecoDTO reajusteDto) {
		reajustePrecoService.realizaReajuste(reajusteDto);
	}

}
