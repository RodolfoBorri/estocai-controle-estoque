package com.uem.controle.estoque.view.relatorio;

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
import com.uem.controle.estoque.controller.RelatorioController;
import com.uem.controle.estoque.view.MenuPrincipalView;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RelatorioView extends ViewBase {

	@Autowired
	RelatorioController relatorioController;
	
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	private ButtonGroup bg; 
	private JTextField textField;
	private JLabel btnProduto_3;
	private JRadioButton rdbtnListaPrecos;
	private JRadioButton rdbtnBalanco;
	
	public static void run() {
		try {
			RelatorioView window = new RelatorioView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RelatorioView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}

	private void montaCabecalhoCompleto() {

		this.add(montaComecoHeader());

		this.add(montaContinuacaoHeader());

		this.add(montaTituloTela("RELATÓRIOS"));
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
		
		JLabel lblNewLabel_3 = new JLabel("Caminho do sistema:");
		lblNewLabel_3.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(27, 183, 144, 25);
		this.add(lblNewLabel_3);
		
		textField = new JTextField("");
		textField.setCaretColor(new Color(240, 242, 245));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setForeground(new Color(240, 242, 245));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(179, 173, 369, 40);
		textField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textField.setColumns(10);
		this.add(textField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(179, 206, 369, 2);
		this.add(separator);

		btnProduto_3 = new JLabel();
		btnProduto_3.setText("Gerar");
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
				String errors = "";
				
				if(!textField.getText().equals("")) 
					errors = verificaCaminho();
				
				if(errors.equals("")) {
					String erroGeracao = "";
					String pathEscolhido = textField.getText();
					
					if(rdbtnListaPrecos.isSelected()) 						
						erroGeracao = relatorioController.geraRelatorioListaPrecos(pathEscolhido);
					
					else if(rdbtnBalanco.isSelected()) 
						erroGeracao = relatorioController.geraRelatorioBalancoFinanceiro(pathEscolhido);
										
					if(!erroGeracao.equals(""))
						JOptionPane.showMessageDialog(null, erroGeracao, "ERROS", JOptionPane.WARNING_MESSAGE);
				}
				else {
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
				MenuPrincipalView menuPrincipalView = ApplicationContextProvider.getContext().getBean(MenuPrincipalView.class);
				menuPrincipalView .frame.setVisible(true);
			}
		});
		this.add(btnProduto_2);

		frame.getContentPane().add(this);
		
		rdbtnListaPrecos = new JRadioButton("Lista de Preços");
		rdbtnListaPrecos.setSelected(true);
		rdbtnListaPrecos.setOpaque(false);
		rdbtnListaPrecos.setFont((new Font("Leelawadee UI Semilight", Font.PLAIN, 12)));
		rdbtnListaPrecos.setBounds(201, 250, 124, 23);
		rdbtnListaPrecos.setForeground(new Color(240, 242, 245));
		this.add(rdbtnListaPrecos);
		
		rdbtnBalanco = new JRadioButton("Balanço Físico Financeiro");
		rdbtnBalanco.setOpaque(false);
		rdbtnBalanco.setFont((new Font("Leelawadee UI Semilight", Font.PLAIN, 12)));
		rdbtnBalanco.setBounds(327, 250, 155, 23);
		rdbtnBalanco.setForeground(new Color(240, 242, 245));
		this.add(rdbtnBalanco);
		
		bg = new ButtonGroup();
		bg.add(rdbtnListaPrecos);
		bg.add(rdbtnBalanco);
	}

	private String verificaCaminho() {
		String caminho = textField.getText();
		return relatorioController.verificaCaminho(caminho);
	}	
}
