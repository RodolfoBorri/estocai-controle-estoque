package com.uem.controle.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uem.controle.estoque.dto.ClasseProdutoDTO;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.service.ClasseProdutoService;

@Controller
public class ClasseProdutoController {
	
	@Autowired
	ClasseProdutoService classeProdutoService;

	public String validaCamposClasseProduto(ClasseProdutoDTO classeProdutoMedidaDTO, String string) {
		return classeProdutoService.validaCamposClasseProduto(classeProdutoMedidaDTO, string);
	}

	public String cadastra(ClasseProdutoDTO classeProdutoMedidaDTO) {
		return classeProdutoService.cadastra(classeProdutoMedidaDTO);
		
	}

	public ClasseProdutoDTO buscaClasseProduto(String codigo) throws ExceptionHandler {
		return classeProdutoService.buscaClasseProduto(codigo);
	}

	public void alteraClasseProduto(ClasseProdutoDTO classeProdutoDTO) {
		classeProdutoService.altera(classeProdutoDTO);		
	}

	public String removeClasseProduto(String codigo) {
		return classeProdutoService.removeClasseProduto(codigo);
	}

	public List<ClasseProdutoDTO> buscaTodasClassesProduto() {
		return classeProdutoService.buscaTodasClassesProduto();
	}

}
