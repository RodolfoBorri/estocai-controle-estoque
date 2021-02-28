package com.uem.controle.estoque.view.produto;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.dto.ProdutoDTO;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InclusaoProdutoView extends ViewBase {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoController produtoController;
	
	public JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JTextField textFieldUnidade;
	private JTextField textFieldQualidade;
	private JTextField textFieldValor;
		
	public static void run() {
		try {
			InclusaoProdutoView window = new InclusaoProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InclusaoProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("INCLUSÃO DE PRODUTO"));		
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

		JLabel btnProduto_3 = new JLabel();
		btnProduto_3.setText("Cadastrar");
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
				
				if(produtoDto != null) {

					String errors = new String("");
					errors = validaInsercao(produtoDto);
					
					if(errors.equals("")) {
						int result = JOptionPane.showConfirmDialog(frame,
					            "Deseja confirmar a inclusão ?", "Confirmação de inclusão",
					            JOptionPane.YES_NO_OPTION);
						
					        if (result == JOptionPane.YES_OPTION) {
					          produtoController.cadastraProduto(produtoDto);
					          JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso!", 
					        		  "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					        }
					        else if (result == JOptionPane.NO_OPTION)
					          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					else {
						JOptionPane.showMessageDialog(null, errors, "ERROS", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnProduto_3);
		
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
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);
			}
		});
		this.add(btnCancelar);		
		
		frame.getContentPane().add(this);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setForeground(SystemColor.inactiveCaptionBorder);
		lblNome.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNome.setBounds(115, 166, 59, 25);
		add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setCaretColor(new Color(240, 242, 245));
		textFieldNome.setBorder(null);
		textFieldNome.setOpaque(false);
		textFieldNome.setForeground(new Color(240, 242, 245));
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldNome.setBounds(180, 156, 369, 40);
		textFieldNome.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldNome.setColumns(10);
		this.add(textFieldNome);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(180, 189, 369, 2);
		add(separator);
		
		JLabel lblPreco = new JLabel("Preço: ");
		lblPreco.setForeground(SystemColor.inactiveCaptionBorder);
		lblPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPreco.setBounds(115, 216, 59, 25);
		add(lblPreco);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setCaretColor(new Color(240, 242, 245));
		textFieldPreco.setBorder(null);
		textFieldPreco.setOpaque(false);
		textFieldPreco.setForeground(new Color(240, 242, 245));
		textFieldPreco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldPreco.setBounds(180, 206, 134, 40);
		textFieldPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldPreco.setColumns(10);
		textFieldPreco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldValor.setText(atualizaValorTotal(textFieldQualidade, textFieldPreco));
			}
		});
		this.add(textFieldPreco);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(180, 239, 134, 2);
		add(separator1);
		
		JLabel lblNewLabel_5 = new JLabel("R$: ");
		lblNewLabel_5.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_5.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(166, 219, 16, 25);
		add(lblNewLabel_5);
		
		JLabel lblUnidade = new JLabel("Unidade: ");
		lblUnidade.setForeground(SystemColor.inactiveCaptionBorder);
		lblUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblUnidade.setBounds(96, 268, 78, 25);
		add(lblUnidade);
		
		textFieldUnidade = new JTextField();
		textFieldUnidade.setCaretColor(new Color(240, 242, 245));
		textFieldUnidade.setBorder(null);
		textFieldUnidade.setOpaque(false);
		textFieldUnidade.setForeground(new Color(240, 242, 245));
		textFieldUnidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldUnidade.setBounds(180, 258, 134, 40);
		textFieldUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldUnidade.setColumns(10);
		this.add(textFieldUnidade);		
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(180, 291, 134, 2);
		add(separator2);		
		
		JLabel lblQuantidade = new JLabel("Quantidade: ");
		lblQuantidade.setForeground(SystemColor.inactiveCaptionBorder);
		lblQuantidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblQuantidade.setBounds(78, 320, 96, 25);
		add(lblQuantidade);
		
		textFieldQualidade = new JTextField();
		textFieldQualidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldValor.setText(atualizaValorTotal(textFieldQualidade, textFieldPreco));
			}
		});
		textFieldQualidade.setCaretColor(new Color(240, 242, 245));
		textFieldQualidade.setBorder(null);
		textFieldQualidade.setOpaque(false);
		textFieldQualidade.setForeground(new Color(240, 242, 245));
		textFieldQualidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldQualidade.setBounds(180, 310, 134, 40);
		textFieldQualidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldQualidade.setColumns(10);
		this.add(textFieldQualidade);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(180, 343, 134, 2);
		add(separator3);
		
		JLabel lblValorTotal = new JLabel("Valor Total: ");
		lblValorTotal.setForeground(SystemColor.inactiveCaptionBorder);
		lblValorTotal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblValorTotal.setBounds(78, 372, 96, 25);
		add(lblValorTotal);
		
		JLabel lblNewLabel_9 = new JLabel("R$: ");
		lblNewLabel_9.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_9.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblNewLabel_9.setBounds(166, 375, 16, 25);
		add(lblNewLabel_9);
		
		textFieldValor = new JTextField();
		textFieldValor.setCaretColor(new Color(240, 242, 245));
		textFieldValor.setBorder(null);
		textFieldValor.setOpaque(false);
		textFieldValor.setEditable(false);
		textFieldValor.setForeground(new Color(240, 242, 245));
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldValor.setBounds(180, 362, 134, 40);
		textFieldValor.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldValor.setColumns(10);
		this.add(textFieldValor);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(180, 395, 134, 2);
		add(separator4);
	}	
	
	private String validaInsercao(ProdutoDTO produtoDto) {
		return produtoController.validaCamposProduto(produtoDto, "Inclusao"); 
	}	
	
	private ProdutoDTO converteCamposParaDto() {
		String nome, unidade;
		int quantidade;
		BigDecimal preco, valorTotalEstoque;
		
		try {
			nome = textFieldNome.getText();
			preco = new BigDecimal(textFieldPreco.getText());
			unidade = textFieldUnidade.getText();
			quantidade = Integer.parseInt(textFieldQualidade.getText());
			valorTotalEstoque = preco.multiply(new BigDecimal(quantidade));
			return new ProdutoDTO(nome, preco, unidade, quantidade, valorTotalEstoque);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, ExceptionEnum.CE_9.getCodigo(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}
