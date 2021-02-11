package com.uem.controle.estoque.exception;

import com.uem.controle.estoque.enumerator.ExceptionEnum;

public class ExceptionHandler extends Exception{

	private static final long serialVersionUID = 1L;

	public ExceptionHandler(ExceptionEnum exception, String variavel) {		
		super(String.format(exception.getCodigo(), variavel));		
	}
}
