package com.uem.controle.estoque.view;

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
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.entity.Produto;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AlteracaoProdutoView extends ViewBase {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoController produtoController;

	public JFrame frame;
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel btnProduto_3;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JSeparator separator4;
	
	public static void run() {
		try {
			AlteracaoProdutoView window = new AlteracaoProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AlteracaoProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
		mudaEnabledCampos(false);
	}

	private void mudaEnabledCampos(boolean podeEditar) {
		separator1.setEnabled(podeEditar);
		separator2.setEnabled(podeEditar);
		separator3.setEnabled(podeEditar);
		separator4.setEnabled(podeEditar);
		textField.setEnabled(!podeEditar);	//Não pode-se alterar o nome
		textField1.setEnabled(podeEditar);
		textField2.setEnabled(podeEditar);
		textField3.setEnabled(podeEditar);
		textField4.setEnabled(false);		//Não pode-se alterar o valorTotal
		lblNewLabel_4.setEnabled(podeEditar);
		lblNewLabel_5.setEnabled(podeEditar);
		lblNewLabel_6.setEnabled(podeEditar);
		lblNewLabel_7.setEnabled(podeEditar);
		lblNewLabel_8.setEnabled(podeEditar);
		lblNewLabel_9.setEnabled(podeEditar);
		btnProduto_3.setEnabled(podeEditar);
	}

	private void montaCabecalhoCompleto() {

		this.add(montaComecoHeader());

		this.add(montaContinuacaoHeader());

		this.add(montaTituloTela("ALTERAÇÃO DE PRODUTO"));
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);
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
				ProdutoDTO produtoDto = converteCamposParaDto();
				String errors = new String(""); 
				errors = validaInsercao(produtoDto);

				if (errors.equals("")) {
					int result = JOptionPane.showConfirmDialog(frame, "Deseja confirmar a alteração ?",
							"Confirmação de alteração", JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						produtoController.alteraProduto(produtoDto);
						JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso!", "SUCESSO",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result == JOptionPane.NO_OPTION)
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, errors, "ERROS", JOptionPane.WARNING_MESSAGE);
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
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);
			}
		});
		this.add(btnProduto_2);

		frame.getContentPane().add(this);

		lblNewLabel_3 = new JLabel("Nome: ");
		lblNewLabel_3.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(115, 166, 59, 25);
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

		lblNewLabel_4 = new JLabel("Preço: ");
		lblNewLabel_4.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(115, 216, 59, 25);
		add(lblNewLabel_4);

		textField1 = new JTextField("");
		textField1.setEnabled(false);
		textField1.setCaretColor(new Color(240, 242, 245));
		textField1.setBorder(null);
		textField1.setOpaque(false);
		textField1.setForeground(new Color(240, 242, 245));
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField1.setBounds(180, 206, 134, 40);
		textField1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField1.setColumns(10);
		textField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textField4.setText(atualizaValorTotal(textField3, textField1));
			}
		});
		this.add(textField1);

		separator1 = new JSeparator();
		separator1.setBounds(180, 239, 134, 2);
		add(separator1);

		lblNewLabel_5 = new JLabel("R$: ");
		lblNewLabel_5.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_5.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(166, 219, 16, 25);
		add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Unidade: ");
		lblNewLabel_6.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_6.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(96, 268, 78, 25);
		add(lblNewLabel_6);

		textField2 = new JTextField("");
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

		lblNewLabel_7 = new JLabel("Quantidade: ");
		lblNewLabel_7.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_7.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(78, 320, 96, 25);
		add(lblNewLabel_7);

		textField3 = new JTextField("");
		textField3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textField4.setText(atualizaValorTotal(textField3, textField1));
			}
		});
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

		lblNewLabel_8 = new JLabel("Valor Total: ");
		lblNewLabel_8.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_8.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(78, 372, 96, 25);
		add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("R$: ");
		lblNewLabel_9.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_9.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel_9.setBounds(166, 375, 16, 25);
		add(lblNewLabel_9);

		textField4 = new JTextField("");
		textField4.setCaretColor(new Color(240, 242, 245));
		textField4.setBorder(null);
		textField4.setOpaque(false);
		textField4.setEditable(false);
		textField4.setForeground(new Color(240, 242, 245));
		textField4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField4.setBounds(180, 362, 134, 31);
		textField4.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField4.setColumns(10);
		this.add(textField4);

		separator4 = new JSeparator();
		separator4.setBounds(180, 395, 134, 2);
		add(separator4);

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
				buscaProduto();			
			}
		});

		add(btnProduto_4);
	}
	
	private void buscaProduto() {
		try {
			String nomeProduto = textField.getText();
			Produto produto = produtoController.buscaProdutoPorNome(nomeProduto);
			preencheCamposProdutoTela(produto);
			mudaEnabledCampos(true);
		}
		catch(Exception ex)
		{
			esvaziaCamposProdutoTela();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			mudaEnabledCampos(false);
		}
	}

	private void esvaziaCamposProdutoTela() {
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
	}

	private void preencheCamposProdutoTela(Produto produto) {
		textField1.setText(produto.getPreco().toString());
		textField2.setText(produto.getUnidadeMedida());
		textField3.setText(produto.getQuantidadeEstoque().toString());
		textField4.setText(produto.getValorTotalEstoque().toString());
	}
	
	private String validaInsercao(ProdutoDTO produtoDto) {
		return produtoController.validaCamposProduto(produtoDto, "Alteracao");
	}

	private ProdutoDTO converteCamposParaDto() {
		String nome, unidade;
		int quantidade;
		BigDecimal preco, valorTotalEstoque;

		try {
			nome = textField.getText();
			preco = new BigDecimal(textField1.getText());
			unidade = textField2.getText();
			quantidade = Integer.parseInt(textField3.getText());
			valorTotalEstoque = preco.multiply(new BigDecimal(quantidade));
			return new ProdutoDTO(nome, preco, unidade, quantidade, valorTotalEstoque);
			
		} catch (Exception e) {
			return new ProdutoDTO("", new BigDecimal("0"), "", -1, new BigDecimal("0"));
		}
	}
}
