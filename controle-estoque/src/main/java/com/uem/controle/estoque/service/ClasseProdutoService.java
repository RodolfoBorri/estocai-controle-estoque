package com.uem.controle.estoque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.uem.controle.estoque.builder.ClasseProdutoBuilder;
import com.uem.controle.estoque.dto.ClasseProdutoDTO;
import com.uem.controle.estoque.entity.ClasseProduto;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.ClasseProdutoRepository;

@Service
public class ClasseProdutoService {

	@Autowired
	ClasseProdutoRepository classeProdutoRepository;
	
	public String validaCamposClasseProduto(ClasseProdutoDTO classeProdutoDTO, String fluxo) {
		StringBuilder errors = new StringBuilder("");			
				
		errors.append(validaCadastroDescricaoClasseProduto(classeProdutoDTO).equals("") ? "" : validaCadastroDescricaoClasseProduto(classeProdutoDTO) + "\n");		
		
		return errors.toString();
	}

	private String validaCadastroDescricaoClasseProduto(ClasseProdutoDTO classeProdutoDTO) {
		String retorno = "";

		try {
			if(classeProdutoDTO.getDescricao().equals("")) {
				throw new ExceptionHandler(ExceptionEnum.CE_19, "");
			}
			
			if(classeProdutoRepository.findByDescricao(classeProdutoDTO.getDescricao()).isPresent()) {
				throw new ExceptionHandler(ExceptionEnum.CE_18, classeProdutoDTO.getDescricao());
			}
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	public String cadastra(ClasseProdutoDTO classeProdutoDTO) {
		String codigo = "";
		
		try {
			ClasseProduto classeProduto = dtoParaEntidade(classeProdutoDTO);
			classeProdutoRepository.save(classeProduto);
			codigo = classeProduto.getId().toString();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return codigo;
	}

	private ClasseProduto dtoParaEntidade(ClasseProdutoDTO classeProdutoDTO) {
		return new ClasseProdutoBuilder().setDescricao(classeProdutoDTO.getDescricao())
										 .build();
	}

	public ClasseProdutoDTO buscaClasseProduto(String codigo) throws ExceptionHandler {
		ClasseProduto classeProdutoEntidade = classeProdutoRepository.findById(Long.parseLong(codigo))
				.orElseThrow(() -> new ExceptionHandler(ExceptionEnum.CE_20, codigo));
		
		return entidadeParaDto(classeProdutoEntidade);
	}

	private ClasseProdutoDTO entidadeParaDto(ClasseProduto classeProdutoEntidade) {
		ClasseProdutoDTO classeProdutoDTO = new ClasseProdutoDTO();
		classeProdutoDTO.setDescricao(classeProdutoEntidade.getDescricao());
		classeProdutoDTO.setCodigo(classeProdutoEntidade.getId().toString());
		
		return classeProdutoDTO;
	}

	public List<ClasseProdutoDTO> buscaTodasClassesProduto() {
		List<ClasseProduto> classesProduto = classeProdutoRepository.findAll();
		List<ClasseProdutoDTO> classesProdutoDTO = new ArrayList<ClasseProdutoDTO>();
		
		for(ClasseProduto classeProduto : classesProduto) 
			classesProdutoDTO.add(entidadeParaDto(classeProduto));
				
		return classesProdutoDTO;
	}

	public ClasseProduto buscaEntidadeClasseProduto(Long codigo) {
		return classeProdutoRepository.findById(codigo).orElse(null);
	}

	public String removeClasseProduto(String codigo) {
		String erros = "";
		
		try {
			ClasseProduto classeProdutoEntidade = buscaEntidadeClasseProduto(Long.parseLong(codigo));
			classeProdutoRepository.delete(classeProdutoEntidade);
		}
		catch(DataIntegrityViolationException e) {
			erros = String.format(ExceptionEnum.CE_16 + " - " + ExceptionEnum.CE_16.getCodigo(), codigo);
		}
		
		return erros;
	}

	public void altera(ClasseProdutoDTO classeProdutoDTO) {
		try {
			ClasseProduto classeProdutoOrigem = buscaEntidadeClasseProduto(Long.parseLong(classeProdutoDTO.getCodigo()));
			preencheVariaveisAlteracao(classeProdutoOrigem, classeProdutoDTO);
			classeProdutoRepository.save(classeProdutoOrigem);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	private void preencheVariaveisAlteracao(ClasseProduto classeProdutoOrigem, ClasseProdutoDTO classeProdutoDTO) {
		classeProdutoOrigem.setDescricao(classeProdutoDTO.getDescricao());
	}
}
