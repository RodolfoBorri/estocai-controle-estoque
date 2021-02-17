package com.uem.controle.estoque.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;

@Component
public class ProdutoView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void run() {
		try {
			ProdutoView window = new ProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ProdutoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		frame.setBounds(100, 100, 494, 385);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("XYZ COMERCIO DE PRODUTOS LTDA.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(78, 11, 306, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SISTEMA DE CONTROLE DE ESTOQUE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(78, 26, 333, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CADASTRO DE PRODUTOS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(137, 67, 195, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("INCLUSÃO");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				InclusaoProdutoView inclusaoProduto = ApplicationContextProvider.getContext().getBean(InclusaoProdutoView.class);
				inclusaoProduto.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(127, 103, 195, 31);
		frame.getContentPane().add(btnNewButton);
	}

}
