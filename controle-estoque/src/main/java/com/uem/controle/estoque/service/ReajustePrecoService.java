package com.uem.controle.estoque.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.controle.estoque.builder.ReajustePrecoBuilder;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.dto.ReajustePrecoDTO;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.entity.ReajustePreco;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.ReajustePrecoRepository;

@Service
public class ReajustePrecoService {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ReajustePrecoRepository reajustePrecoRepository;
	
	private final String REAJUSTE_PRODUTO = "P";
	private final String REAJUSTE_GERAL = "G";

	public String validaCamposReajuste(ReajustePrecoDTO reajusteDto) {
		StringBuilder errors = new StringBuilder("");
	
		errors.append(validaCadastroPercentualReajuste(reajusteDto).equals("") ? "" : validaCadastroPercentualReajuste(reajusteDto) + "\n");
		
		return errors.toString();
	}
	
	private String validaCadastroPercentualReajuste(ReajustePrecoDTO reajusteDto) {
		String retorno = "";
		
		try {
			
			if(reajusteDto.getPercentualReajuste() == 0) {
				throw new ExceptionHandler(ExceptionEnum.CE_10, "");
			}	
			
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	@Transactional
	public void realizaReajuste(ReajustePrecoDTO reajusteDto) {
		try {
			
			BigDecimal porcentagem = new BigDecimal(reajusteDto.getPercentualReajuste()).divide(new BigDecimal("100"));
			
			if(REAJUSTE_PRODUTO.equals(reajusteDto.getTipoReajuste())) {
				ProdutoDTO produtoDto = reajusteDto.getProduto();
				
				BigDecimal precoMultiplicado = produtoDto.getPrecoUnitario().multiply(porcentagem);				
				BigDecimal precoOriginal = produtoDto.getPrecoUnitario();
				BigDecimal precoAtualizado = precoOriginal.add(precoMultiplicado);
				BigDecimal quantidadeEmEstoque = new BigDecimal(produtoDto.getQuantidadeEstoque());
				
				produtoDto.setValorTotalEstoque(precoAtualizado.multiply(quantidadeEmEstoque));
				produtoDto.setPrecoUnitario(precoAtualizado);
				
				produtoService.altera(produtoDto);
				
				Produto produto = produtoService.buscaEntidadeProduto(reajusteDto.getProduto().getNome());
				ReajustePreco reajustePreco = dtoParaEntidade(reajusteDto, produto);
				reajustePrecoRepository.save(reajustePreco);
				
			}
			else if(REAJUSTE_GERAL.equals(reajusteDto.getTipoReajuste())) {
				produtoService.realizaReajuste(porcentagem);
				
				ReajustePreco reajustePreco = dtoParaEntidade(reajusteDto, null);
				reajustePrecoRepository.save(reajustePreco);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	private ReajustePreco dtoParaEntidade(ReajustePrecoDTO reajusteDto, Produto produto) throws ExceptionHandler {
		
		return new ReajustePrecoBuilder().setPercentualReajuste(new BigDecimal(reajusteDto.getPercentualReajuste()))
										 .setTipoReajuste(reajusteDto.getTipoReajuste())
										 .setUsuarioReajuste(reajusteDto.getUsuarioReajuste())
										 .setProduto(produto)
										 .build();
										 
	}	
}
