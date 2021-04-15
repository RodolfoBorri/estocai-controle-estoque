package com.uem.controle.estoque.view.consultas;

import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.entity.Produto;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultaSaldosClasseProduto extends ViewBase{
	
	@Autowired
	ProdutoController produtoController;
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	private TextArea textArea;
	
	public static void run() {
		try {
			ConsultaSaldosClasseProduto window = new ConsultaSaldosClasseProduto();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ConsultaSaldosClasseProduto() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("SALDOS POR CLASSE DE PRODUTO"));
		
	}
	
	@Override
	protected JLabel montaTituloTela(String titulo) {
		JLabel lblNewLabel = new JLabel(titulo);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel.setBounds(240, 115, 350, 25);
		
		return lblNewLabel;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				ConsultaView consultaView = ApplicationContextProvider.getContext().getBean(ConsultaView.class);
				consultaView.frame.setVisible(true);
			}
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(74, 161, 575, 290);
		add(panel);
		panel.setLayout(null);
		
		textArea = new TextArea();
		textArea.setBounds(0, 0, 575, 290);
		textArea.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 12));
		panel.add(textArea);
		
		frame.getContentPane().add(this);
	}
	
	@PostConstruct
	private void criaConsulta() {
		StringBuilder stringBuilder = new StringBuilder(); 
		List<Produto> consulta = produtoController.consultaSaldosPorClasseTotalizandoUnidade();
		BigDecimal quantidadeEstoqueTotal = BigDecimal.ZERO, valorUnidadeTotal = BigDecimal.ZERO, valorFinalTotal = BigDecimal.ZERO, valorTotalGeral = BigDecimal.ZERO;
		BigDecimal quantidadeEstoqueClasse = BigDecimal.ZERO, valorUnidadeClasse = BigDecimal.ZERO, valorFinalClasse = BigDecimal.ZERO;
		Long codigoClasse = 0L;
		String auxiliarClasseProduto = "";
		String auxiliarUnidade = "";
		String nomeClasse = "";
		String unidade = "";
		
		stringBuilder.append("                                     RELATORIO DE ESTOQUE POR CLASSE DE PRODUTO\n\n");
		stringBuilder.append("                                    -------------------------------------\n");
		stringBuilder.append("                                    Codigo - Descricao Classe\n");
		stringBuilder.append("                                    -------------------------------------\n");
		
		auxiliarClasseProduto = consulta.get(0).getClasseProduto().getDescricao();
		auxiliarUnidade = consulta.get(0).getUnidadeMedida().getUnidade();
		codigoClasse = consulta.get(0).getClasseProduto().getId();
		
		stringBuilder.append("                                     " + codigoClasse + " - " + auxiliarClasseProduto);
		
		for(Produto produto : consulta) {
			Long codigoProduto = produto.getId();
			String nomeProduto = produto.getNome();
			
			codigoClasse = produto.getClasseProduto().getId();
			nomeClasse = produto.getClasseProduto().getDescricao();
			unidade = produto.getUnidadeMedida().getUnidade();
			
			if(!auxiliarUnidade.equals(unidade)) {
				stringBuilder.append("\n		            -----------------------------------------------------------------------");
				stringBuilder.append("\n                                            Total Unidade    " + auxiliarUnidade + "  " + quantidadeEstoqueTotal + "       R$    " + valorUnidadeTotal + "       R$   " + valorFinalTotal);
				stringBuilder.append("\n\n");
				quantidadeEstoqueTotal = BigDecimal.ZERO;
				valorUnidadeTotal = BigDecimal.ZERO;
				valorFinalTotal = BigDecimal.ZERO;
			}
			
			if(!auxiliarClasseProduto.equals(nomeClasse)) {
				stringBuilder.append("\n		            -----------------------------------------------------------------------");
				stringBuilder.append("\n                                            Total Unidade    " + auxiliarUnidade + "  " + quantidadeEstoqueTotal + "       R$    " + valorUnidadeTotal + "       R$   " + valorFinalTotal);
				stringBuilder.append("\n		            -----------------------------------------------------------------------");
				stringBuilder.append("\n                                      Total da Classe           " + quantidadeEstoqueClasse + "      R$    " + valorUnidadeClasse + "       R$   " + valorFinalClasse);
				stringBuilder.append("\n\n");	
				
				valorTotalGeral = valorTotalGeral.add(valorFinalClasse);
				
				quantidadeEstoqueTotal = BigDecimal.ZERO;
				valorUnidadeTotal = BigDecimal.ZERO;
				valorFinalTotal = BigDecimal.ZERO;
				quantidadeEstoqueClasse = BigDecimal.ZERO;
				valorUnidadeClasse = BigDecimal.ZERO;
				valorFinalClasse = BigDecimal.ZERO;			
				stringBuilder.append("                                     " + codigoClasse + " - " + nomeClasse); 	
			}
			
			stringBuilder.append("\n                                            " + codigoProduto + " - " + nomeProduto + "      " + unidade + "  " 
					+ produto.getQuantidadeEstoque() + "       R$    " + produto.getPreco() + "       R$    " + produto.getValorTotalEstoque());
			
			
			quantidadeEstoqueTotal = quantidadeEstoqueTotal.add(new BigDecimal(produto.getQuantidadeEstoque()));
			valorUnidadeTotal = valorUnidadeTotal.add(produto.getPreco());
			valorFinalTotal = valorFinalTotal.add(produto.getValorTotalEstoque());
			
			quantidadeEstoqueClasse = quantidadeEstoqueClasse.add(new BigDecimal(produto.getQuantidadeEstoque()));
			valorUnidadeClasse = valorUnidadeClasse.add(produto.getPreco());
			valorFinalClasse = valorFinalClasse.add(produto.getValorTotalEstoque());
			
			auxiliarClasseProduto = nomeClasse;
			auxiliarUnidade = unidade;	 
		}
		
		valorTotalGeral = valorTotalGeral.add(valorFinalClasse);
		stringBuilder.append("\n		            -----------------------------------------------------------------------");
		stringBuilder.append("\n                                            Total Unidade    " + auxiliarUnidade + "  " + quantidadeEstoqueTotal + "       R$    " + valorUnidadeTotal + "       R$   " + valorFinalTotal);
		stringBuilder.append("\n		            -----------------------------------------------------------------------");
		stringBuilder.append("\n                                      Total da Classe           " + quantidadeEstoqueClasse + "      R$    " + valorUnidadeClasse + "       R$   " + valorFinalClasse);
		stringBuilder.append("\n                                   ------------------------------------------------------------------------------");
		stringBuilder.append("\n                                   Total Geral                                                           R$   " + valorTotalGeral);
		
		textArea.setText(stringBuilder.toString());
	}
}
