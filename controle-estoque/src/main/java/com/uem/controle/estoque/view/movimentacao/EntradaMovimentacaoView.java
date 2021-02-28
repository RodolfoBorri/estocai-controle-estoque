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
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel btnProduto_3;
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
		textField1.setEditable(false);
		textField3.setEditable(false);
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

		btnProduto_3 = new JLabel();
		btnProduto_3.setText("Confirmar");
		btnProduto_3.setOpaque(true);
		btnProduto_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_3.setForeground(Color.DARK_GRAY);
		btnProduto_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_3.setBackground(Color.WHITE);
		btnProduto_3.setBounds(58, 430, 256, 31);
		btnProduto_3.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnProduto_3.setBackground(new Color(54, 209, 80));
				btnProduto_3.setForeground(Color.WHITE);
				btnProduto_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnProduto_3.setBackground(Color.WHITE);
				btnProduto_3.setForeground(Color.DARK_GRAY);
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
		this.add(btnProduto_3);

		JLabel btnProduto_2 = new JLabel();
		btnProduto_2.setText("Cancelar");
		btnProduto_2.setOpaque(true);
		btnProduto_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_2.setForeground(Color.DARK_GRAY);
		btnProduto_2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_2.setBackground(Color.WHITE);
		btnProduto_2.setBounds(410, 430, 256, 31);
		btnProduto_2.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnProduto_2.setBackground(new Color(224, 63, 63));
				btnProduto_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnProduto_2.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnProduto_2.setBackground(Color.WHITE);
				btnProduto_2.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				MovimentacaoView movimentacaoView = ApplicationContextProvider.getContext().getBean(MovimentacaoView.class);
				movimentacaoView.frame.setVisible(true);
			}
		});
		this.add(btnProduto_2);

		frame.getContentPane().add(this);

		lblNewLabel_3 = new JLabel("Produto: ");
		lblNewLabel_3.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(111, 166, 63, 25);
		add(lblNewLabel_3);

		textField = new JTextField("");
		textField.setCaretColor(new Color(240, 242, 245));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setForeground(new Color(240, 242, 245));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(180, 156, 369, 40);
		textField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField.setColumns(10);
		this.add(textField);

		JSeparator separator = new JSeparator();
		separator.setBounds(180, 189, 369, 2);
		add(separator);

		lblNewLabel_4 = new JLabel("Quantidade Atual:");
		lblNewLabel_4.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(40, 216, 134, 25);
		add(lblNewLabel_4);

		textField1 = new JTextField("");
		textField1.setCaretColor(new Color(240, 242, 245));
		textField1.setBorder(null);
		textField1.setOpaque(false);
		textField1.setForeground(new Color(240, 242, 245));
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField1.setBounds(180, 206, 134, 40);
		textField1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField1.setColumns(10);
		this.add(textField1);

		separator1 = new JSeparator();
		separator1.setBounds(180, 239, 134, 2);
		add(separator1);

		lblNewLabel_6 = new JLabel("Quantidade Saída:");
		lblNewLabel_6.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_6.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(40, 268, 134, 25);
		add(lblNewLabel_6);

		textField2 = new JTextField("");
		textField2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textField3.setText(atualizaQuantidadeTotalMovimentacao(textField1, textField2, "Soma"));
			}
		});
		textField2.setCaretColor(new Color(240, 242, 245));
		textField2.setBorder(null);
		textField2.setOpaque(false);
		textField2.setForeground(new Color(240, 242, 245));
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField2.setBounds(180, 258, 134, 40);
		textField2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField2.setColumns(10);
		this.add(textField2);

		separator2 = new JSeparator();
		separator2.setBounds(180, 291, 134, 2);
		add(separator2);

		lblNewLabel_7 = new JLabel("Quantidade Final: ");
		lblNewLabel_7.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_7.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(40, 320, 134, 25);
		add(lblNewLabel_7);

		textField3 = new JTextField("");
		textField3.setCaretColor(new Color(240, 242, 245));
		textField3.setBorder(null);
		textField3.setOpaque(false);
		textField3.setForeground(new Color(240, 242, 245));
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField3.setBounds(180, 310, 134, 40);
		textField3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField3.setColumns(10);
		this.add(textField3);

		separator3 = new JSeparator();
		separator3.setBounds(180, 343, 134, 2);		
		add(separator3);

		JLabel btnProduto_4 = new JLabel();
		btnProduto_4.setText("Buscar");
		btnProduto_4.setOpaque(true);
		btnProduto_4.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_4.setForeground(Color.DARK_GRAY);
		btnProduto_4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_4.setBackground(Color.WHITE);
		btnProduto_4.setBounds(584, 162, 111, 31);
		btnProduto_4.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnProduto_4.setBackground(new Color(54, 209, 80));
				btnProduto_4.setForeground(Color.WHITE);
				btnProduto_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnProduto_4.setBackground(Color.WHITE);
				btnProduto_4.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaProdutoEPreencheTela();			
			}
		});

		add(btnProduto_4);
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
			String nomeProduto = textField.getText();
			
			ProdutoDTO produtoDto = buscaProduto(nomeProduto);
			
			Integer quantidadeEntrada = Integer.parseInt(textField2.getText());
			
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
			String nomeProduto = textField.getText();
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
		textField.setText(produtoDto.getNome());
		textField1.setText(produtoDto.getQuantidadeEstoque().toString());
		textField3.setText(produtoDto.getQuantidadeEstoque().toString());
	}

	private void esvaziaCamposProdutoTela() {
		textField.setText("");
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
	}
}
