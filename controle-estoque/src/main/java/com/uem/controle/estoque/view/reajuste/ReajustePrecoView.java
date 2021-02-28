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
	private JTextField textFieldProduto;
	private JTextField textFieldUnidade;
	private JTextField textFieldPrecoAtual;
	private JTextField textFieldPercentual;
	private JLabel btnBuscaProduto;
	private JLabel lblProduto;
	private JLabel lblUnidade;
	private JLabel lblPrecoAtual;
	private JLabel lblPercentualReajuste;
	private JLabel btnConfirmar;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JRadioButton rdbtnPorProduto;
	private JRadioButton rdbtnGeral;
	private JLabel lblNewLabel;
	private JLabel lblPercentual;
	
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
		lblProduto.setEnabled(podeEditar);
		lblUnidade.setEnabled(podeEditar);
		lblPrecoAtual.setEnabled(podeEditar);
		textFieldProduto.setEnabled(podeEditar);
		textFieldUnidade.setEnabled(podeEditar);
		textFieldPrecoAtual.setEnabled(podeEditar);
		btnBuscaProduto.setEnabled(podeEditar);
		lblNewLabel.setEnabled(podeEditar);
		
		if(podeEditar) {
			btnBuscaProduto.setForeground(Color.DARK_GRAY);
			btnBuscaProduto.setBackground(Color.WHITE);
		}
		else {
			btnBuscaProduto.setBackground(new Color(97,98,98));
			btnBuscaProduto.setForeground(Color.WHITE);
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
				MenuPrincipalView menuPrincipalView = ApplicationContextProvider.getContext().getBean(MenuPrincipalView.class);
				menuPrincipalView .frame.setVisible(true);
			}
		});
		this.add(btnCancelar);

		frame.getContentPane().add(this);

		lblProduto = new JLabel("Produto: ");
		lblProduto.setForeground(SystemColor.inactiveCaptionBorder);
		lblProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblProduto.setBounds(111, 221, 63, 25);
		this.add(lblProduto);

		textFieldProduto = new JTextField("");
		textFieldProduto.setCaretColor(new Color(240, 242, 245));
		textFieldProduto.setBorder(null);
		textFieldProduto.setOpaque(false);
		textFieldProduto.setForeground(new Color(240, 242, 245));
		textFieldProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldProduto.setBounds(180, 211, 369, 40);
		textFieldProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldProduto.setColumns(10);
		this.add(textFieldProduto);

		JSeparator separator = new JSeparator();
		separator.setBounds(180, 244, 369, 2);
		this.add(separator);

		lblUnidade = new JLabel("Unidade:");
		lblUnidade.setForeground(SystemColor.inactiveCaptionBorder);
		lblUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblUnidade.setBounds(111, 271, 63, 25);
		this.add(lblUnidade);

		textFieldUnidade = new JTextField("");
		textFieldUnidade.setCaretColor(new Color(240, 242, 245));
		textFieldUnidade.setBorder(null);
		textFieldUnidade.setOpaque(false);
		textFieldUnidade.setForeground(new Color(240, 242, 245));
		textFieldUnidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldUnidade.setBounds(180, 261, 134, 40);
		textFieldUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldUnidade.setColumns(10);
		this.add(textFieldUnidade);

		separator1 = new JSeparator();
		separator1.setBounds(180, 294, 134, 2);
		this.add(separator1);

		lblPrecoAtual = new JLabel("Preço Atual:");
		lblPrecoAtual.setForeground(SystemColor.inactiveCaptionBorder);
		lblPrecoAtual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPrecoAtual.setBounds(80, 323, 94, 25);
		this.add(lblPrecoAtual);

		textFieldPrecoAtual = new JTextField("");		
		textFieldPrecoAtual.setCaretColor(new Color(240, 242, 245));
		textFieldPrecoAtual.setBorder(null);
		textFieldPrecoAtual.setOpaque(false);
		textFieldPrecoAtual.setForeground(new Color(240, 242, 245));
		textFieldPrecoAtual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldPrecoAtual.setBounds(180, 313, 134, 40);
		textFieldPrecoAtual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldPrecoAtual.setColumns(10);
		this.add(textFieldPrecoAtual);

		separator2 = new JSeparator();
		separator2.setBounds(180, 346, 134, 2);
		this.add(separator2);

		lblPercentualReajuste = new JLabel("Percentual de Reajuste:");
		lblPercentualReajuste.setForeground(SystemColor.inactiveCaptionBorder);
		lblPercentualReajuste.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPercentualReajuste.setBounds(10, 376, 164, 25);
		this.add(lblPercentualReajuste);

		textFieldPercentual = new JTextField("");
		textFieldPercentual.setCaretColor(new Color(240, 242, 245));
		textFieldPercentual.setBorder(null);
		textFieldPercentual.setOpaque(false);
		textFieldPercentual.setForeground(new Color(240, 242, 245));
		textFieldPercentual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldPercentual.setBounds(180, 365, 48, 40);
		textFieldPercentual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldPercentual.setColumns(10);
		this.add(textFieldPercentual);

		separator3 = new JSeparator();
		separator3.setBounds(180, 399, 48, 2);		
		this.add(separator3);
		
		btnBuscaProduto = new JLabel();
		btnBuscaProduto.setText("Buscar");
		btnBuscaProduto.setOpaque(true);
		btnBuscaProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscaProduto.setForeground(Color.DARK_GRAY);
		btnBuscaProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnBuscaProduto.setBackground(Color.WHITE);
		btnBuscaProduto.setBounds(584, 217, 111, 31);
		btnBuscaProduto.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if(rdbtnPorProduto.isSelected()) {
					btnBuscaProduto.setBackground(new Color(54, 209, 80));
					btnBuscaProduto.setForeground(Color.WHITE);
					btnBuscaProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				if(rdbtnPorProduto.isSelected()) {
					btnBuscaProduto.setBackground(Color.WHITE);
					btnBuscaProduto.setForeground(Color.DARK_GRAY);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaProdutoEPreencheTela();			
			}
		});

		this.add(btnBuscaProduto);
		
		rdbtnPorProduto = new JRadioButton("Por Produto");
		rdbtnPorProduto.setSelected(true);
		rdbtnPorProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaEdicaoCampos(true);
			}
		});
		rdbtnPorProduto.setOpaque(false);
		rdbtnPorProduto.setFont((new Font("Leelawadee UI Semilight", Font.PLAIN, 12)));
		rdbtnPorProduto.setBounds(181, 173, 109, 23);
		rdbtnPorProduto.setForeground(new Color(240, 242, 245));
		this.add(rdbtnPorProduto);
		
		JLabel lblTipoReajuste = new JLabel("Tipo de Reajuste:");
		lblTipoReajuste.setForeground(SystemColor.inactiveCaptionBorder);
		lblTipoReajuste.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblTipoReajuste.setBounds(57, 171, 117, 25);
		this.add(lblTipoReajuste);
		
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
		bg.add(rdbtnPorProduto);
		bg.add(rdbtnGeral); 
		
		lblNewLabel = new JLabel("R$: ");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel.setBounds(167, 329, 16, 25);
		add(lblNewLabel);
		
		lblPercentual = new JLabel("%");
		lblPercentual.setForeground(SystemColor.inactiveCaptionBorder);
		lblPercentual.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblPercentual.setBounds(171, 383, 16, 25);
		add(lblPercentual);
	}
	
	private String validaReajuste(ReajustePrecoDTO reajusteDto) {
		return reajustePrecoController.validaCamposReajuste(reajusteDto); 
	}

	private ReajustePrecoDTO converteCamposParaDto() {
		
		try {	
			String usuarioReajuste = "";
			String tipoReajuste = "";
			String nomeProduto = textFieldProduto.getText();
			
			ProdutoDTO produtoDto = new ProdutoDTO();
			
			Integer percentualReajuste = Integer.parseInt(textFieldPercentual.getText());
			
			if(rdbtnPorProduto.isSelected()) {
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
		textFieldUnidade.setText(produtoDto.getUnidadeMedida().toString());
		textFieldPrecoAtual.setText(produtoDto.getPrecoUnitario().toString());
	}

	private void esvaziaCamposProdutoTela() {
		textFieldProduto.setText("");
		textFieldUnidade.setText("");
		textFieldPrecoAtual.setText("");
		textFieldPercentual.setText("");
	}
}
