package com.uem.controle.estoque.view.movimentacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.MovimentacaoController;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.dto.MovimentacaoDTO;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EntradaMovimentacaoView extends ViewBase {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoController produtoController;
	
	@Autowired
	MovimentacaoController movimentacaoController;

	public JFrame frame;
	private JTextField textFieldProduto;
	private JTextField textFieldQuantidadeAtual;
	private JTextField textFieldQuantidadeSaida;
	private JTextField textFieldQuantidadeFinal;
	private JLabel lblProduto;
	private JLabel lblQuantidadeAtual;
	private JLabel lblQuantidadeSaida;
	private JLabel lblQuantidadeFinal;
	private JLabel btnConfirmar;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	
	public static void run() {
		try {
			EntradaMovimentacaoView window = new EntradaMovimentacaoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EntradaMovimentacaoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
		mudaEdicaoCampos();
	}
	
	private void mudaEdicaoCampos() {	
		textFieldQuantidadeAtual.setEditable(false);
		textFieldQuantidadeFinal.setEditable(false);
	}

	private void montaCabecalhoCompleto() {

		this.add(montaComecoHeader());

		this.add(montaContinuacaoHeader());

		this.add(montaTituloTela("MOVIMENTACAO - ENTRADA DE PRODUTO"));
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				MovimentacaoView movimentacaoView = ApplicationContextProvider.getContext().getBean(MovimentacaoView.class);
				movimentacaoView.frame.setVisible(true);
			}
		});
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);

		btnConfirmar = new JLabel();
		btnConfirmar.setText("Confirmar");
		btnConfirmar.setOpaque(true);
		btnConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmar.setForeground(Color.DARK_GRAY);
		btnConfirmar.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setBounds(58, 430, 256, 31);
		btnConfirmar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnConfirmar.setBackground(new Color(54, 209, 80));
				btnConfirmar.setForeground(Color.WHITE);
				btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnConfirmar.setBackground(Color.WHITE);
				btnConfirmar.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				MovimentacaoDTO movimentacaoDto = converteCamposParaDto();
				if(movimentacaoDto != null) {
					String errors = new String("");				
					errors = validaMovimentacao(movimentacaoDto);
					
					if(errors.equals("")) {
						int result = JOptionPane.showConfirmDialog(frame, "Deseja confirmar a entrada do produto?",
								"Confirmação de entrada do produto", JOptionPane.YES_NO_OPTION);
		
						if (result == JOptionPane.YES_OPTION) {
							movimentacaoController.realizaMovimentacao(movimentacaoDto);
							JOptionPane.showMessageDialog(null, "Movimentacao de entrada realizada com sucesso!", "SUCESSO",
									JOptionPane.INFORMATION_MESSAGE);
						} else if (result == JOptionPane.NO_OPTION)
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					else {
						JOptionPane.showMessageDialog(null, errors, "ERROS", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnConfirmar);

		JLabel btnCancelar = new JLabel();
		btnCancelar.setText("Cancelar");
		btnCancelar.setOpaque(true);
		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(410, 430, 256, 31);
		btnCancelar.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCancelar.setBackground(new Color(224, 63, 63));
				btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnCancelar.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				MovimentacaoView movimentacaoView = ApplicationContextProvider.getContext().getBean(MovimentacaoView.class);
				movimentacaoView.frame.setVisible(true);
			}
		});
		this.add(btnCancelar);

		frame.getContentPane().add(this);

		lblProduto = new JLabel("Produto: ");
		lblProduto.setForeground(SystemColor.inactiveCaptionBorder);
		lblProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblProduto.setBounds(111, 166, 63, 25);
		add(lblProduto);

		textFieldProduto = new JTextField("");
		textFieldProduto.setCaretColor(new Color(240, 242, 245));
		textFieldProduto.setBorder(null);
		textFieldProduto.setOpaque(false);
		textFieldProduto.setForeground(new Color(240, 242, 245));
		textFieldProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldProduto.setBounds(180, 156, 369, 40);
		textFieldProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldProduto.setColumns(10);
		this.add(textFieldProduto);

		JSeparator separator = new JSeparator();
		separator.setBounds(180, 189, 369, 2);
		add(separator);

		lblQuantidadeAtual = new JLabel("Quantidade Atual:");
		lblQuantidadeAtual.setForeground(SystemColor.inactiveCaptionBorder);
		lblQuantidadeAtual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblQuantidadeAtual.setBounds(40, 216, 134, 25);
		add(lblQuantidadeAtual);

		textFieldQuantidadeAtual = new JTextField("");
		textFieldQuantidadeAtual.setCaretColor(new Color(240, 242, 245));
		textFieldQuantidadeAtual.setBorder(null);
		textFieldQuantidadeAtual.setOpaque(false);
		textFieldQuantidadeAtual.setForeground(new Color(240, 242, 245));
		textFieldQuantidadeAtual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldQuantidadeAtual.setBounds(180, 206, 134, 40);
		textFieldQuantidadeAtual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldQuantidadeAtual.setColumns(10);
		this.add(textFieldQuantidadeAtual);

		separator1 = new JSeparator();
		separator1.setBounds(180, 239, 134, 2);
		add(separator1);

		lblQuantidadeSaida = new JLabel("Quantidade Saída:");
		lblQuantidadeSaida.setForeground(SystemColor.inactiveCaptionBorder);
		lblQuantidadeSaida.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblQuantidadeSaida.setBounds(40, 268, 134, 25);
		add(lblQuantidadeSaida);

		textFieldQuantidadeSaida = new JTextField("");
		textFieldQuantidadeSaida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldQuantidadeFinal.setText(atualizaQuantidadeTotalMovimentacao(textFieldQuantidadeAtual, textFieldQuantidadeSaida, "Soma"));
			}
		});
		textFieldQuantidadeSaida.setCaretColor(new Color(240, 242, 245));
		textFieldQuantidadeSaida.setBorder(null);
		textFieldQuantidadeSaida.setOpaque(false);
		textFieldQuantidadeSaida.setForeground(new Color(240, 242, 245));
		textFieldQuantidadeSaida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldQuantidadeSaida.setBounds(180, 258, 134, 40);
		textFieldQuantidadeSaida.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldQuantidadeSaida.setColumns(10);
		this.add(textFieldQuantidadeSaida);

		separator2 = new JSeparator();
		separator2.setBounds(180, 291, 134, 2);
		add(separator2);

		lblQuantidadeFinal = new JLabel("Quantidade Final: ");
		lblQuantidadeFinal.setForeground(SystemColor.inactiveCaptionBorder);
		lblQuantidadeFinal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblQuantidadeFinal.setBounds(40, 320, 134, 25);
		add(lblQuantidadeFinal);

		textFieldQuantidadeFinal = new JTextField("");
		textFieldQuantidadeFinal.setCaretColor(new Color(240, 242, 245));
		textFieldQuantidadeFinal.setBorder(null);
		textFieldQuantidadeFinal.setOpaque(false);
		textFieldQuantidadeFinal.setForeground(new Color(240, 242, 245));
		textFieldQuantidadeFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldQuantidadeFinal.setBounds(180, 310, 134, 40);
		textFieldQuantidadeFinal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldQuantidadeFinal.setColumns(10);
		this.add(textFieldQuantidadeFinal);

		separator3 = new JSeparator();
		separator3.setBounds(180, 343, 134, 2);		
		add(separator3);

		JLabel btnBuscaProduto = new JLabel();
		btnBuscaProduto.setText("Buscar");
		btnBuscaProduto.setOpaque(true);
		btnBuscaProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscaProduto.setForeground(Color.DARK_GRAY);
		btnBuscaProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnBuscaProduto.setBackground(Color.WHITE);
		btnBuscaProduto.setBounds(584, 162, 111, 31);
		btnBuscaProduto.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBuscaProduto.setBackground(new Color(54, 209, 80));
				btnBuscaProduto.setForeground(Color.WHITE);
				btnBuscaProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBuscaProduto.setBackground(Color.WHITE);
				btnBuscaProduto.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaProdutoEPreencheTela();			
			}
		});

		add(btnBuscaProduto);
	}
	
	private String validaMovimentacao(MovimentacaoDTO movimentacaoDto) {
		return movimentacaoController.validaCamposMovimentacao(movimentacaoDto); 
	}

	private MovimentacaoDTO converteCamposParaDto() {
		
		try {	
			Date date = new Date();
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    String sDate = df.format(date);
		    Date finalDate = df.parse(sDate);
		    
		    System.out.println(finalDate);
			String usuarioMovimentacao = "";
			String nomeProduto = textFieldProduto.getText();
			
			ProdutoDTO produtoDto = buscaProduto(nomeProduto);
			
			Integer quantidadeEntrada = Integer.parseInt(textFieldQuantidadeSaida.getText());
			
			String tipoMovimentacao = "E";
			
			return new MovimentacaoDTO(usuarioMovimentacao, finalDate, produtoDto, quantidadeEntrada, tipoMovimentacao);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, ExceptionEnum.CE_9.getCodigo(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}

	private void buscaProdutoEPreencheTela() {
		try {
			String nomeProduto = textFieldProduto.getText();
			ProdutoDTO produtoDto = buscaProduto(nomeProduto);
			preencheCamposTela(produtoDto);
		}
		catch(Exception ex)
		{
			esvaziaCamposProdutoTela();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private ProdutoDTO buscaProduto(String nomeProduto) throws ExceptionHandler {
		return produtoController.buscaProdutoPorNome(nomeProduto);
	}

	private void preencheCamposTela(ProdutoDTO produtoDto) {
		textFieldProduto.setText(produtoDto.getNome());
		textFieldQuantidadeAtual.setText(produtoDto.getQuantidadeEstoque().toString());
		textFieldQuantidadeFinal.setText(produtoDto.getQuantidadeEstoque().toString());
	}

	private void esvaziaCamposProdutoTela() {
		textFieldProduto.setText("");
		textFieldQuantidadeAtual.setText("");
		textFieldQuantidadeSaida.setText("");
		textFieldQuantidadeFinal.setText("");
	}
}
