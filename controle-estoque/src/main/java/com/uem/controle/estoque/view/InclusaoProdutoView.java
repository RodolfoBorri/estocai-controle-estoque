package com.uem.controle.estoque.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.controller.ProdutoController;
import com.uem.controle.estoque.dto.ProdutoDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class InclusaoProdutoView {

	@Autowired
	ProdutoController produtoController;
	
	public JFrame frame;
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;

	/**
	 * Launch the application.
	 */
	
	public static void run() {
		try {
			InclusaoProdutoView window = new InclusaoProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public InclusaoProdutoView() {
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
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);	
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
		
		JLabel lblNewLabel_2 = new JLabel("INCLUSÃO DE PRODUTO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(137, 59, 195, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NOME: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(58, 107, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(103, 105, 281, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PREÇO: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(58, 147, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField1.setBounds(103, 145, 94, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("UNIDADE: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(45, 187, 59, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField2.setBounds(103, 185, 32, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("QUANTIDADE: ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(20, 227, 84, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField3.setBounds(103, 225, 32, 20);
		frame.getContentPane().add(textField3);
		textField3.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("VALOR TOTAL: ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 267, 94, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField4 = new JTextField();
		textField4.setEditable(false);
		textField4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField4.setBounds(103, 265, 94, 20);
		frame.getContentPane().add(textField4);
		textField4.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProdutoDTO produtoDto = converteCamposParaDto();
				String errors = validaInsercao(produtoDto);
				
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
		});
		btnNewButton.setBounds(89, 312, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);
				produtoView.frame.setVisible(true);				
			}
		});
		btnNewButton_1.setBounds(295, 312, 89, 23);
		frame.getContentPane().add(btnNewButton_1);		
		
	}
	
	private String validaInsercao(ProdutoDTO produtoDto) {
		return produtoController.validaInsercao(produtoDto); 
	}	
	
	private ProdutoDTO converteCamposParaDto() {
		String nome, unidade;
		int quantidade;
		BigDecimal preco;
		
		try {
			nome = textField.getText();
			preco = new BigDecimal(textField1.getText());
			unidade = textField2.getText();
			quantidade = Integer.parseInt(textField3.getText());
			
			return new ProdutoDTO(nome, preco, unidade, quantidade);
		}
		catch(Exception e) {			
			return new ProdutoDTO("", new BigDecimal("0"), "", -1);
		}
	}
}
