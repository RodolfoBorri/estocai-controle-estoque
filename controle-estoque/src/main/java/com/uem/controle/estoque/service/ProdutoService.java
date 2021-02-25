package com.uem.controle.estoque.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final String FLUXO_INCLUSAO = "Inclusao";
	private final String FLUXO_ALTERACAO = "Alteracao";
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Transactional
	public void cadastra(ProdutoDTO produtoDto) {
		try {
			Produto produto = dtoParaEntidade(produtoDto);
			produtoRepository.save(produto);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Produto dtoParaEntidade(ProdutoDTO produtoDto) {
		return new Produto(produtoDto.getNome(), produtoDto.getPrecoUnitario(), 
				produtoDto.getUnidadeMedida(), produtoDto.getQuantidadeEstoque());
	}

	private String validaCadastroNome(ProdutoDTO produtoDto) {
		String retorno = "";
		
		try {
			
			if(produtoDto.getNome().equals("")) {
				throw new ExceptionHandler(ExceptionEnum.CE_5, produtoDto.getNome());
			}
			
			if(produtoRepository.findByNome(produtoDto.getNome()).isPresent()) {
				throw new ExceptionHandler(ExceptionEnum.CE_1, produtoDto.getNome());
			}		
			
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}

	private String validaCadastroUnidade(ProdutoDTO produtoDto) {
		String retorno = "";

		try {
			if(produtoDto.getUnidadeMedida().equals("")) {
				throw new ExceptionHandler(ExceptionEnum.CE_6, "");
			}
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}
	
	private String validaCadastroPreco(ProdutoDTO produtoDto) {
		String retorno = "";

		try {
			if(produtoDto.getPrecoUnitario().compareTo(new BigDecimal("0")) ==  0 ||
					produtoDto.getPrecoUnitario().compareTo(new BigDecimal("0")) ==  -1) {
				throw new ExceptionHandler(ExceptionEnum.CE_3, "");
			}
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}
	
	private String validaCadastroQuantidade(ProdutoDTO produtoDto) {
		String retorno = "";
		
		try {
			if(produtoDto.getQuantidadeEstoque() < 0) {
				throw new ExceptionHandler(ExceptionEnum.CE_4, "");
			}
		}
		catch(Exception e) {
			retorno = e.getMessage();
		}
		
		return retorno;
	}
	
	public String validaCamposProduto(ProdutoDTO produtoDto, String fluxo) {
		StringBuilder errors = new StringBuilder("");
		
		if(fluxo.equalsIgnoreCase(FLUXO_INCLUSAO.toString())) {
			
			errors.append(validaCadastroNome(produtoDto).equals("") ? "" : validaCadastroNome(produtoDto) + "\n");
			errors.append(validaCadastroPreco(produtoDto).equals("") ? "" : validaCadastroPreco(produtoDto) + "\n");
			errors.append(validaCadastroUnidade(produtoDto).equals("") ? "" : validaCadastroUnidade(produtoDto) + "\n");
			errors.append(validaCadastroQuantidade(produtoDto).equals("") ? "" : validaCadastroQuantidade(produtoDto) + "\n");
		}
		else if(fluxo.equalsIgnoreCase(FLUXO_ALTERACAO.toString())) {
			
			errors.append(validaCadastroPreco(produtoDto).equals("") ? "" : validaCadastroPreco(produtoDto) + "\n");
			errors.append(validaCadastroUnidade(produtoDto).equals("") ? "" : validaCadastroUnidade(produtoDto) + "\n");
			errors.append(validaCadastroQuantidade(produtoDto).equals("") ? "" : validaCadastroQuantidade(produtoDto) + "\n");
		}
		
		return errors.toString();
	}

	public Produto buscaProdutoPorNome(String nome) throws ExceptionHandler {
		return produtoRepository.findByNome(nome).orElseThrow(() -> new ExceptionHandler(ExceptionEnum.CE_7, nome)); 
	}

	public void exclui(ProdutoDTO produtoDto) {
		try {
			Produto produto = produtoRepository.findByNome(produtoDto.getNome()).get();
			produtoRepository.delete(produto);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void altera(ProdutoDTO produtoDto) {
		Produto produtoOrigem = produtoRepository.findByNome(produtoDto.getNome()).get();
		preencheVariaveisAlteracao(produtoOrigem, produtoDto);
		produtoRepository.save(produtoOrigem);
	}

	private void preencheVariaveisAlteracao(Produto produtoOrigem, ProdutoDTO produtoDto) {
		produtoOrigem.setPreco(produtoDto.getPrecoUnitario());
		produtoOrigem.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());
		produtoOrigem.setUnidadeMedida(produtoDto.getUnidadeMedida());
		produtoOrigem.setValorTotalEstoque(produtoDto.getValorTotalEstoque());
	}	
}
