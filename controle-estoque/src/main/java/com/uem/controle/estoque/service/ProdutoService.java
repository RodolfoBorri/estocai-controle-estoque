package com.uem.controle.estoque.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.controle.estoque.builder.ProdutoBuilder;
import com.uem.controle.estoque.dto.ClasseProdutoDTO;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.dto.UnidadeMedidaDTO;
import com.uem.controle.estoque.entity.ClasseProduto;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.entity.UnidadeMedida;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final String FLUXO_INCLUSAO = "Inclusao";
	private final String FLUXO_ALTERACAO = "Alteracao";
	private final String FLUXO_ENTRADA = "E";
	private final String FLUXO_SAIDA = "S";
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	UnidadeMedidaService unidadeMedidaService;
	
	@Autowired
	ClasseProdutoService classeProdutoService;
	
	public void cadastra(ProdutoDTO produtoDto) {
		try {
			Produto produto = dtoParaEntidade(produtoDto);
			produtoRepository.save(produto);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private UnidadeMedida buscaUnidade(String unidade) {
		return unidadeMedidaService.buscaEntidadeUnidade(unidade);
	}
	
	private ClasseProduto buscaClasseProduto(String codigo) {
		return classeProdutoService.buscaEntidadeClasseProduto(Long.parseLong(codigo));
	}

	private Produto dtoParaEntidade(ProdutoDTO produtoDto) {
		UnidadeMedida unidade = buscaUnidade(produtoDto.getUnidadeMedida().getUnidade());
		ClasseProduto classeProduto = buscaClasseProduto(produtoDto.getClasseProduto().getCodigo());
		
		Produto produto = new ProdutoBuilder().setNome(produtoDto.getNome())
											  .setPrecoUnitario(produtoDto.getPrecoUnitario())
											  .setQuantidadeEstoque(produtoDto.getQuantidadeEstoque())
											  .setUnidadeMedida(unidade)
											  .setClasseProduto(classeProduto)
											  .build();
		return produto;
	}
	
	private ProdutoDTO entidadeParaDto(Produto produto) {
		ProdutoDTO produtoDto = new ProdutoDTO();
		
		UnidadeMedidaDTO unidadeMedidaDTO = new UnidadeMedidaDTO(produto.getUnidadeMedida().getDescricao(),
				produto.getUnidadeMedida().getUnidade());
		
		ClasseProdutoDTO classeProdutoDTO = new ClasseProdutoDTO(produto.getClasseProduto().getDescricao(), 
				produto.getClasseProduto().getId().toString()); 
		
		produtoDto.setNome(produto.getNome());
		produtoDto.setPrecoUnitario(produto.getPreco());
		produtoDto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoDto.setUnidadeMedida(unidadeMedidaDTO);
		produtoDto.setValorTotalEstoque(produto.getValorTotalEstoque());
		produtoDto.setClasseProduto(classeProdutoDTO);
		
		return produtoDto;
	}
	
	public List<ProdutoDTO> buscaProdutosEOrdenaPorNome(){
		List<Produto> produtos = produtoRepository.findAllByOrderByNomeAsc();
		
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for(Produto produto : produtos) {
			produtosDTO.add(entidadeParaDto(produto));
		}
		
		return produtosDTO;
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
			if(produtoDto.getUnidadeMedida().getUnidade().equals("")) {
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

	public ProdutoDTO buscaProdutoPorNome(String nome) throws ExceptionHandler {
		Produto produto = produtoRepository.findByNome(nome).orElseThrow(() -> new ExceptionHandler(ExceptionEnum.CE_7, nome));
		return entidadeParaDto(produto);
	}
	
	public Produto buscaEntidadeProduto(String nome) throws ExceptionHandler {
		return produtoRepository.findByNome(nome).orElseThrow(() -> new ExceptionHandler(ExceptionEnum.CE_7, nome)); 
	}

	public void exclui(String nome) {
		try {
			Produto produto = buscaEntidadeProduto(nome);
			produtoRepository.delete(produto);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void altera(ProdutoDTO produtoDto) {
		try {
			Produto produtoOrigem = buscaEntidadeProduto(produtoDto.getNome());
			preencheVariaveisAlteracao(produtoOrigem, produtoDto);
			produtoRepository.save(produtoOrigem);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void preencheVariaveisAlteracao(Produto produtoOrigem, ProdutoDTO produtoDto) {
		produtoOrigem.setPreco(produtoDto.getPrecoUnitario());
		produtoOrigem.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());
		produtoOrigem.setUnidadeMedida(buscaUnidade(produtoDto.getUnidadeMedida().getUnidade()));
		produtoOrigem.setValorTotalEstoque(produtoDto.getValorTotalEstoque());
	}

	public void realizaMovimentacao(ProdutoDTO produtoDto, Integer quantidade, String tipoAlteracao) throws ExceptionHandler {
		
		if(FLUXO_ENTRADA.equals(tipoAlteracao)) 
			produtoDto.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque() + quantidade);			
		
		else if(FLUXO_SAIDA.equals(tipoAlteracao)) 
			produtoDto.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque() - quantidade);
		
		produtoDto.setValorTotalEstoque(produtoDto.getPrecoUnitario().multiply(new BigDecimal(produtoDto.getQuantidadeEstoque())));
		altera(produtoDto);
	}

	public void realizaReajusteGeral(BigDecimal porcentagem) {
		List<Produto> produtos = produtoRepository.findAll();
		
		for(Produto produto : produtos) {
			BigDecimal precoOriginal = produto.getPreco();
			BigDecimal precoMultiplicado = produto.getPreco().multiply(porcentagem);
			BigDecimal precoAtualizado = precoOriginal.add(precoMultiplicado);
			BigDecimal quantidadeEmEstoque = new BigDecimal(produto.getQuantidadeEstoque());

			produto.setValorTotalEstoque(precoAtualizado.multiply(quantidadeEmEstoque));
			produto.setPreco(precoAtualizado);
			
			produtoRepository.save(produto);
		}
	}

	public List<Produto> consultaSaldosPorClasseTotalizandoUnidade() {
		return produtoRepository.consultaSaldosPorClasseTotalizandoUnidade();
	}	
}
