package com.uem.controle.estoque.view.reajuste;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.controller.ReajustePrecoController;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.dto.ReajustePrecoDTO;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.exception.ExceptionHandler;
import com.uem.controle.estoque.view.MenuPrincipalView;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReajustePrecoView extends ViewBase {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoController produtoController;
	
	@Autowired
	ReajustePrecoController reajustePrecoController;

	public JFrame frame;
	private ButtonGroup bg; 
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JLabel btnProduto_4;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel btnProduto_3;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnGeral;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public static void run() {
		try {
			ReajustePrecoView window = new ReajustePrecoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReajustePrecoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
		mudaEdicaoCampos(true);
	}
	
	private void mudaEdicaoCampos(boolean podeEditar) {
		lblNewLabel_3.setEnabled(podeEditar);
		lblNewLabel_4.setEnabled(podeEditar);
		lblNewLabel_6.setEnabled(podeEditar);
		textField.setEnabled(podeEditar);
		textField1.setEnabled(podeEditar);
		textField2.setEnabled(podeEditar);
		btnProduto_4.setEnabled(podeEditar);
		lblNewLabel.setEnabled(podeEditar);
		
		if(podeEditar) {
			btnProduto_4.setForeground(Color.DARK_GRAY);
			btnProduto_4.setBackground(Color.WHITE);
		}
		else {
			btnProduto_4.setBackground(new Color(97,98,98));
			btnProduto_4.setForeground(Color.WHITE);
		}

	}

	private void montaCabecalhoCompleto() {

		this.add(montaComecoHeader());

		this.add(montaContinuacaoHeader());

		this.add(montaTituloTela("REAJUSTE DE PREÇOS"));
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				MenuPrincipalView menuPrincipalView = ApplicationContextProvider.getContext().getBean(MenuPrincipalView.class);
				menuPrincipalView.frame.setVisible(true);
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
				ReajustePrecoDTO reajusteDto = converteCamposParaDto();
				if(reajusteDto != null) {
					String errors = new String("");				
					errors = validaReajuste(reajusteDto);
					
					if(errors.equals("")) {
						int result = JOptionPane.showConfirmDialog(frame, "Deseja confirmar o reajuste de preço?",
								"Confirmação de reajuste de preço", JOptionPane.YES_NO_OPTION);
		
						if (result == JOptionPane.YES_OPTION) {
							reajustePrecoController.realizaReajuste(reajusteDto);
							JOptionPane.showMessageDialog(null, "Reajuste de preço realizado com sucesso!", "SUCESSO",
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
				MenuPrincipalView menuPrincipalView = ApplicationContextProvider.getContext().getBean(MenuPrincipalView.class);
				menuPrincipalView .frame.setVisible(true);
			}
		});
		this.add(btnProduto_2);

		frame.getContentPane().add(this);

		lblNewLabel_3 = new JLabel("Produto: ");
		lblNewLabel_3.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(111, 221, 63, 25);
		this.add(lblNewLabel_3);

		textField = new JTextField("");
		textField.setCaretColor(new Color(240, 242, 245));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setForeground(new Color(240, 242, 245));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(180, 211, 369, 40);
		textField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField.setColumns(10);
		this.add(textField);

		JSeparator separator = new JSeparator();
		separator.setBounds(180, 244, 369, 2);
		this.add(separator);

		lblNewLabel_4 = new JLabel("Unidade:");
		lblNewLabel_4.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(111, 271, 63, 25);
		this.add(lblNewLabel_4);

		textField1 = new JTextField("");
		textField1.setCaretColor(new Color(240, 242, 245));
		textField1.setBorder(null);
		textField1.setOpaque(false);
		textField1.setForeground(new Color(240, 242, 245));
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField1.setBounds(180, 261, 134, 40);
		textField1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField1.setColumns(10);
		this.add(textField1);

		separator1 = new JSeparator();
		separator1.setBounds(180, 294, 134, 2);
		this.add(separator1);

		lblNewLabel_6 = new JLabel("Preço Atual:");
		lblNewLabel_6.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_6.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(80, 323, 94, 25);
		this.add(lblNewLabel_6);

		textField2 = new JTextField("");		
		textField2.setCaretColor(new Color(240, 242, 245));
		textField2.setBorder(null);
		textField2.setOpaque(false);
		textField2.setForeground(new Color(240, 242, 245));
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField2.setBounds(180, 313, 134, 40);
		textField2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField2.setColumns(10);
		this.add(textField2);

		separator2 = new JSeparator();
		separator2.setBounds(180, 346, 134, 2);
		this.add(separator2);

		lblNewLabel_7 = new JLabel("Percentual de Reajuste:");
		lblNewLabel_7.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_7.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(10, 376, 164, 25);
		this.add(lblNewLabel_7);

		textField3 = new JTextField("");
		textField3.setCaretColor(new Color(240, 242, 245));
		textField3.setBorder(null);
		textField3.setOpaque(false);
		textField3.setForeground(new Color(240, 242, 245));
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField3.setBounds(180, 365, 48, 40);
		textField3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField3.setColumns(10);
		this.add(textField3);

		separator3 = new JSeparator();
		separator3.setBounds(180, 399, 48, 2);		
		this.add(separator3);
		
		btnProduto_4 = new JLabel();
		btnProduto_4.setText("Buscar");
		btnProduto_4.setOpaque(true);
		btnProduto_4.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_4.setForeground(Color.DARK_GRAY);
		btnProduto_4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_4.setBackground(Color.WHITE);
		btnProduto_4.setBounds(584, 217, 111, 31);
		btnProduto_4.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if(rdbtnNewRadioButton.isSelected()) {
					btnProduto_4.setBackground(new Color(54, 209, 80));
					btnProduto_4.setForeground(Color.WHITE);
					btnProduto_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				if(rdbtnNewRadioButton.isSelected()) {
					btnProduto_4.setBackground(Color.WHITE);
					btnProduto_4.setForeground(Color.DARK_GRAY);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaProdutoEPreencheTela();			
			}
		});

		this.add(btnProduto_4);
		
		rdbtnNewRadioButton = new JRadioButton("Por Produto");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaEdicaoCampos(true);
			}
		});
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setFont((new Font("Leelawadee UI Semilight", Font.PLAIN, 12)));
		rdbtnNewRadioButton.setBounds(181, 173, 109, 23);
		rdbtnNewRadioButton.setForeground(new Color(240, 242, 245));
		this.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_7_1 = new JLabel("Tipo de Reajuste:");
		lblNewLabel_7_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_7_1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_7_1.setBounds(57, 171, 117, 25);
		this.add(lblNewLabel_7_1);
		
		rdbtnGeral = new JRadioButton("Geral");
		rdbtnGeral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaEdicaoCampos(false);
			}
		});
		rdbtnGeral.setOpaque(false);
		rdbtnGeral.setFont((new Font("Leelawadee UI Semilight", Font.PLAIN, 12)));
		rdbtnGeral.setBounds(292, 173, 109, 23);
		rdbtnGeral.setForeground(new Color(240, 242, 245));
		this.add(rdbtnGeral);
		
		bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnGeral); 
		
		lblNewLabel = new JLabel("R$: ");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel.setBounds(167, 329, 16, 25);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(171, 383, 16, 25);
		add(lblNewLabel_1);
	}
	
	private String validaReajuste(ReajustePrecoDTO reajusteDto) {
		return reajustePrecoController.validaCamposReajuste(reajusteDto); 
	}

	private ReajustePrecoDTO converteCamposParaDto() {
		
		try {	
			String usuarioReajuste = "";
			String tipoReajuste = "";
			String nomeProduto = textField.getText();
			
			ProdutoDTO produtoDto = new ProdutoDTO();
			
			Integer percentualReajuste = Integer.parseInt(textField3.getText());
			
			if(rdbtnNewRadioButton.isSelected()) {
				tipoReajuste = "P";
				produtoDto = buscaProduto(nomeProduto);
			}
			else if(rdbtnGeral.isSelected()) {
				tipoReajuste = "G";
				produtoDto = null;
			}
			
			return new ReajustePrecoDTO(usuarioReajuste, tipoReajuste, percentualReajuste, produtoDto);
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
		textField1.setText(produtoDto.getUnidadeMedida().toString());
		textField2.setText(produtoDto.getPrecoUnitario().toString());
	}

	private void esvaziaCamposProdutoTela() {
		textField.setText("");
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
	}
}
