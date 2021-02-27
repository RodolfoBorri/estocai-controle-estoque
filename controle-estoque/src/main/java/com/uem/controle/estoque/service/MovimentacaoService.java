package com.uem.controle.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uem.controle.estoque.builder.MovimentacaoBuilder;
import com.uem.controle.estoque.dto.MovimentacaoDTO;
import com.uem.controle.estoque.entity.Movimentacao;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	ProdutoService produtoService;

	public String validaCamposProduto(MovimentacaoDTO movimentacaoDto) {
		StringBuilder errors = new StringBuilder("");
		
		errors.append(validaCadastroQuantidade(movimentacaoDto).equals("") ? "" : validaCadastroQuantidade(movimentacaoDto) + "\n");

		return errors.toString();
	}

	private String validaCadastroQuantidade(MovimentacaoDTO movimentacaoDto) {
		String retorno = "";
		
		try {
			if(movimentacaoDto.getQuantidade() < 0) {
				throw new ExceptionHandler(ExceptionEnum.CE_8, "");
			}
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	@Transactional
	public void realizaMovimentacao(MovimentacaoDTO movimentacaoDto) {
		try {
			Movimentacao movimentacao = dtoParaEntidade(movimentacaoDto);
			movimentacaoRepository.save(movimentacao);
			
			produtoService.realizaMovimentacao(movimentacaoDto.getProdutoDto(), movimentacaoDto.getQuantidade(),
					movimentacaoDto.getTipoMovimentacao());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private Movimentacao dtoParaEntidade(MovimentacaoDTO movimentacaoDto) throws ExceptionHandler {
		Produto produto = produtoService.buscaEntidadeProduto(movimentacaoDto.getProdutoDto().getNome());
		
		Movimentacao movimentacao = new MovimentacaoBuilder().setUsuarioMovimentacao(movimentacaoDto.getUsuarioMovimentacao())
															 .setProduto(produto)
															 .setDataMovimentacao(movimentacaoDto.getDataMovimentacao())
															 .setQuantidade(movimentacaoDto.getQuantidade())
															 .setTipoMovimentacao(movimentacaoDto.getTipoMovimentacao())
															 .build();
															 
		return movimentacao;
	}

}
