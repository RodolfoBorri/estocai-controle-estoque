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
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultaProdutoView extends ViewBase {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoController produtoController;

	public JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JTextField textFieldUnidade;
	private JTextField textFieldQuantidade;
	private JTextField textFieldValorTotal;
	private JLabel lblNome;
	private JLabel lblPreco;
	private JLabel lblCifrao2;
	private JLabel lblUnidade;
	private JLabel lblQuantidade;
	private JLabel lblValorTotal;
	private JLabel lblCifrao;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JSeparator separator4;
	
	public static void run() {
		try {
			ConsultaProdutoView window = new ConsultaProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConsultaProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
		mudaEdicaoCampos(false);
	}

	private void mudaEdicaoCampos(boolean podeEditar) {		//Muda-se a edição do campo conforme for realizar a busca
		textFieldUnidade.setEditable(false);
		textFieldQuantidade.setEditable(false);
		textFieldValorTotal.setEditable(false);
	}

	private void montaCabecalhoCompleto() {

		this.add(montaComecoHeader());

		this.add(montaContinuacaoHeader());

		this.add(montaTituloTela("CONSULTA DE PRODUTO"));
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
				buscaProduto();			
			}
		});

		add(btnBuscaProduto);

		JLabel btnCancelaProduto = new JLabel();
		btnCancelaProduto.setText("Cancelar");
		btnCancelaProduto.setOpaque(true);
		btnCancelaProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelaProduto.setForeground(Color.DARK_GRAY);
		btnCancelaProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnCancelaProduto.setBackground(Color.WHITE);
		btnCancelaProduto.setBounds(410, 430, 256, 31);
		btnCancelaProduto.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCancelaProduto.setBackground(new Color(224, 63, 63));
				btnCancelaProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnCancelaProduto.setForeground(Color.WHITE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCancelaProduto.setBackground(Color.WHITE);
				btnCancelaProduto.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);
			}
		});
		this.add(btnCancelaProduto);

		frame.getContentPane().add(this);

		lblNome = new JLabel("Nome: ");
		lblNome.setForeground(SystemColor.inactiveCaptionBorder);
		lblNome.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNome.setBounds(115, 166, 59, 25);
		add(lblNome);

		textFieldNome = new JTextField("");
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

		lblPreco = new JLabel("Preço: ");
		lblPreco.setForeground(SystemColor.inactiveCaptionBorder);
		lblPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPreco.setBounds(115, 216, 59, 25);
		add(lblPreco);

		textFieldPreco = new JTextField("");
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
				textFieldValorTotal.setText(atualizaValorTotal(textFieldQuantidade, textFieldPreco));
			}
		});
		this.add(textFieldPreco);

		separator1 = new JSeparator();
		separator1.setBounds(180, 239, 134, 2);
		add(separator1);

		lblCifrao2 = new JLabel("R$: ");
		lblCifrao2.setForeground(SystemColor.inactiveCaptionBorder);
		lblCifrao2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblCifrao2.setBounds(166, 219, 16, 25);
		add(lblCifrao2);

		lblUnidade = new JLabel("Unidade: ");
		lblUnidade.setForeground(SystemColor.inactiveCaptionBorder);
		lblUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblUnidade.setBounds(96, 268, 78, 25);
		add(lblUnidade);

		textFieldUnidade = new JTextField("");
		textFieldUnidade.setCaretColor(new Color(240, 242, 245));
		textFieldUnidade.setBorder(null);
		textFieldUnidade.setOpaque(false);
		textFieldUnidade.setForeground(new Color(240, 242, 245));
		textFieldUnidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldUnidade.setBounds(180, 258, 134, 40);
		textFieldUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldUnidade.setColumns(10);
		this.add(textFieldUnidade);

		separator2 = new JSeparator();
		separator2.setBounds(180, 291, 134, 2);
		add(separator2);

		lblQuantidade = new JLabel("Quantidade: ");
		lblQuantidade.setForeground(SystemColor.inactiveCaptionBorder);
		lblQuantidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblQuantidade.setBounds(78, 320, 96, 25);
		add(lblQuantidade);

		textFieldQuantidade = new JTextField("");
		textFieldQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldValorTotal.setText(atualizaValorTotal(textFieldQuantidade, textFieldPreco));
			}
		});
		textFieldQuantidade.setCaretColor(new Color(240, 242, 245));
		textFieldQuantidade.setBorder(null);
		textFieldQuantidade.setOpaque(false);
		textFieldQuantidade.setForeground(new Color(240, 242, 245));
		textFieldQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldQuantidade.setBounds(180, 310, 134, 40);
		textFieldQuantidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldQuantidade.setColumns(10);
		this.add(textFieldQuantidade);

		separator3 = new JSeparator();
		separator3.setBounds(180, 343, 134, 2);		
		add(separator3);

		lblValorTotal = new JLabel("Valor Total: ");
		lblValorTotal.setForeground(SystemColor.inactiveCaptionBorder);
		lblValorTotal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblValorTotal.setBounds(78, 372, 96, 25);
		add(lblValorTotal);
		
		lblCifrao = new JLabel("R$: ");
		lblCifrao.setForeground(SystemColor.inactiveCaptionBorder);
		lblCifrao.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 10));
		lblCifrao.setBounds(166, 375, 16, 25);
		add(lblCifrao);

		textFieldValorTotal = new JTextField("");
		textFieldValorTotal.setCaretColor(new Color(240, 242, 245));
		textFieldValorTotal.setBorder(null);
		textFieldValorTotal.setOpaque(false);
		textFieldValorTotal.setEditable(false);
		textFieldValorTotal.setForeground(new Color(240, 242, 245));
		textFieldValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldValorTotal.setBounds(180, 362, 134, 31);
		textFieldValorTotal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldValorTotal.setColumns(10);
		this.add(textFieldValorTotal);

		separator4 = new JSeparator();
		separator4.setBounds(180, 395, 134, 2);
		add(separator4);		
	}
	
	private void buscaProduto() {
		try {
			String nomeProduto = textFieldNome.getText();
			ProdutoDTO produtoDto = produtoController.buscaProdutoPorNome(nomeProduto);
			preencheCamposProdutoTela(produtoDto);
			mudaEdicaoCampos(true);
		}
		catch(Exception ex)
		{
			esvaziaCamposProdutoTela();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			mudaEdicaoCampos(false);
		}
	}

	private void esvaziaCamposProdutoTela() {
		textFieldPreco.setText("");
		textFieldUnidade.setText("");
		textFieldQuantidade.setText("");
		textFieldValorTotal.setText("");
	}

	private void preencheCamposProdutoTela(ProdutoDTO produto) {
		textFieldPreco.setText(produto.getPrecoUnitario().toString());
		textFieldUnidade.setText(produto.getUnidadeMedida());
		textFieldQuantidade.setText(produto.getQuantidadeEstoque().toString());
		textFieldValorTotal.setText(produto.getValorTotalEstoque().toString());
	}
}
