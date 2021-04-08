package com.uem.controle.estoque.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.uem.controle.estoque.builder.UnidadeMedidaBuilder;
import com.uem.controle.estoque.dto.UnidadeMedidaDTO;
import com.uem.controle.estoque.entity.UnidadeMedida;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
	
	private final String FLUXO_INCLUSAO = "Inclusao";
	private final String FLUXO_ALTERACAO = "Alteracao";
	
	@Autowired
	UnidadeMedidaRepository unidadeMedidaRepository;

	public String validaCamposUnidade(UnidadeMedidaDTO unidadeMedidaDTO, String fluxo) {
		StringBuilder errors = new StringBuilder("");	
		
		if(fluxo.equalsIgnoreCase(FLUXO_INCLUSAO.toString())) {
			errors.append(validaCadastroUnidade(unidadeMedidaDTO).equals("") ? "" : validaCadastroUnidade(unidadeMedidaDTO) + "\n");		
			errors.append(validaCadastroDescricaoUnidade(unidadeMedidaDTO).equals("") ? "" : validaCadastroDescricaoUnidade(unidadeMedidaDTO) + "\n");		
		}
		else if(fluxo.equalsIgnoreCase(FLUXO_ALTERACAO.toString())) {
			errors.append(validaCadastroDescricaoUnidade(unidadeMedidaDTO).equals("") ? "" : validaCadastroDescricaoUnidade(unidadeMedidaDTO) + "\n");
		}
		
		return errors.toString();
	}

	private String validaCadastroDescricaoUnidade(UnidadeMedidaDTO unidadeMedidaDTO) {
		String retorno = "";

		try {
			if(unidadeMedidaDTO.getDescricao().equals("")) {
				throw new ExceptionHandler(ExceptionEnum.CE_13, "");
			}	
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	private String validaCadastroUnidade(UnidadeMedidaDTO unidadeMedidaDTO) {
		String retorno = "";

		try {
			if(unidadeMedidaDTO.getUnidade().equals("")) {
				throw new ExceptionHandler(ExceptionEnum.CE_6, "");
			}
			
			if(unidadeMedidaRepository.findByUnidade(unidadeMedidaDTO.getUnidade()).isPresent()) {
				throw new ExceptionHandler(ExceptionEnum.CE_14, unidadeMedidaDTO.getUnidade());
			}	
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	@Transactional
	public void cadastra(UnidadeMedidaDTO unidadeMedidaDTO) {
		try {
			UnidadeMedida unidadeMedida = dtoParaEntidade(unidadeMedidaDTO);
			unidadeMedidaRepository.save(unidadeMedida);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private UnidadeMedida dtoParaEntidade(UnidadeMedidaDTO unidadeMedidaDTO) {
		return new UnidadeMedidaBuilder().setDescricao(unidadeMedidaDTO.getDescricao())
										 .setUnidade(unidadeMedidaDTO.getUnidade())
										 .build();
	}

	public UnidadeMedidaDTO buscaUnidade(String unidade) throws ExceptionHandler {
		UnidadeMedida unidadeEntidade = unidadeMedidaRepository.findByUnidade(unidade)
				.orElseThrow(() -> new ExceptionHandler(ExceptionEnum.CE_15, unidade));
		
		return entidadeParaDto(unidadeEntidade);
	}

	private UnidadeMedidaDTO entidadeParaDto(UnidadeMedida unidadeEntidade) {
		UnidadeMedidaDTO unidadeMedidaDTO = new UnidadeMedidaDTO();
		unidadeMedidaDTO.setDescricao(unidadeEntidade.getDescricao());
		unidadeMedidaDTO.setUnidade(unidadeEntidade.getUnidade());
		
		return unidadeMedidaDTO;
	}

	public List<UnidadeMedidaDTO> buscaTodasUnidades() {
		List<UnidadeMedida> unidades = unidadeMedidaRepository.findAll();
		List<UnidadeMedidaDTO> unidadesDTO = new ArrayList<UnidadeMedidaDTO>();
		
		for(UnidadeMedida unidade : unidades) 
			unidadesDTO.add(entidadeParaDto(unidade));
				
		return unidadesDTO;
	}

	public UnidadeMedida buscaEntidadeUnidade(String unidadeMedida) {
		return unidadeMedidaRepository.findByUnidade(unidadeMedida).orElse(null);		
	}

	public String removeUnidade(String unidade) {
		String erros = "";
		
		try {
			UnidadeMedida unidadeEntidade = buscaEntidadeUnidade(unidade);
			unidadeMedidaRepository.delete(unidadeEntidade);
		}
		catch(DataIntegrityViolationException e) {
			erros = String.format(ExceptionEnum.CE_16 + " - " + ExceptionEnum.CE_16.getCodigo(), unidade);
		}
		
		return erros;
	}

	public void altera(UnidadeMedidaDTO unidadeMedidaDTO) {
		try {
			UnidadeMedida unidadeOrigem = buscaEntidadeUnidade(unidadeMedidaDTO.getUnidade());
			preencheVariaveisAlteracao(unidadeOrigem, unidadeMedidaDTO);
			unidadeMedidaRepository.save(unidadeOrigem);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	private void preencheVariaveisAlteracao(UnidadeMedida unidadeOrigem, UnidadeMedidaDTO unidadeMedidaDTO) {
		unidadeOrigem.setDescricao(unidadeMedidaDTO.getDescricao());
	}
}
