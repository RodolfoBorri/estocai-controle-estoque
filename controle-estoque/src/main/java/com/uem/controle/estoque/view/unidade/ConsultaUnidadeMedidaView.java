package com.uem.controle.estoque.view.unidade;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
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
import com.uem.controle.estoque.controller.UnidadeMedidaController;
import com.uem.controle.estoque.dto.UnidadeMedidaDTO;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultaUnidadeMedidaView extends ViewBase{

	private static final long serialVersionUID = 1L;

	@Autowired
	UnidadeMedidaController unidadeMedidaController;
	
	public JFrame frame;
	private JTextField textFieldDescricao;
	private JTextField textFieldUnidade;
		
	public static void run() {
		try {
			ConsultaUnidadeMedidaView window = new ConsultaUnidadeMedidaView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConsultaUnidadeMedidaView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("CONSULTA DE UNIDADE"));		
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				UnidadeMedidaView unidadeMedidaView = ApplicationContextProvider.getContext().getBean(UnidadeMedidaView.class);
				unidadeMedidaView.frame.setVisible(true);
			}
		});
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
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
				UnidadeMedidaView unidadeMedidaView = ApplicationContextProvider.getContext().getBean(UnidadeMedidaView.class);
				unidadeMedidaView.frame.setVisible(true);
			}
		});
		this.add(btnCancelar);		
				
		JLabel lblNome = new JLabel("Descrição: ");
		lblNome.setForeground(SystemColor.inactiveCaptionBorder);
		lblNome.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNome.setBounds(99, 215, 74, 25);
		add(lblNome);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setCaretColor(new Color(240, 242, 245));
		textFieldDescricao.setBorder(null);
		textFieldDescricao.setOpaque(false);
		textFieldDescricao.setForeground(new Color(240, 242, 245));
		textFieldDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDescricao.setBounds(179, 205, 369, 40);
		textFieldDescricao.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldDescricao.setColumns(10);
		this.add(textFieldDescricao);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(179, 238, 369, 2);
		add(separator);
		
		JLabel lblPreco = new JLabel("Unidade: ");
		lblPreco.setForeground(SystemColor.inactiveCaptionBorder);
		lblPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPreco.setBounds(108, 169, 66, 25);
		add(lblPreco);
		
		textFieldUnidade = new JTextField();
		textFieldUnidade.setCaretColor(new Color(240, 242, 245));
		textFieldUnidade.setBorder(null);
		textFieldUnidade.setOpaque(false);
		textFieldUnidade.setForeground(new Color(240, 242, 245));
		textFieldUnidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldUnidade.setBounds(180, 159, 83, 40);
		textFieldUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldUnidade.setColumns(10);
		this.add(textFieldUnidade);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(180, 192, 83, 2);
		add(separator1);
		
		JLabel btnBuscaUnidade = new JLabel();
		btnBuscaUnidade.setText("Buscar");
		btnBuscaUnidade.setOpaque(true);
		btnBuscaUnidade.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscaUnidade.setForeground(Color.DARK_GRAY);
		btnBuscaUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnBuscaUnidade.setBackground(Color.WHITE);
		btnBuscaUnidade.setBounds(437, 165, 111, 31);
		btnBuscaUnidade.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBuscaUnidade.setBackground(new Color(54, 209, 80));
				btnBuscaUnidade.setForeground(Color.WHITE);
				btnBuscaUnidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBuscaUnidade.setBackground(Color.WHITE);
				btnBuscaUnidade.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaUnidade();			
			}
		});

		add(btnBuscaUnidade);		

		frame.getContentPane().add(this);
	}	
	
	private void buscaUnidade() {
		try {
			String unidade = textFieldUnidade.getText();
			UnidadeMedidaDTO unidadeMedidaDTO = unidadeMedidaController.buscaUnidade(unidade);
			preencheCamposUnidadeTela(unidadeMedidaDTO);
		}
		catch(Exception ex)
		{
			esvaziaCamposTela();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	private void preencheCamposUnidadeTela(UnidadeMedidaDTO unidadeMedidaDTO) {
		textFieldUnidade.setText(unidadeMedidaDTO.getUnidade());
		textFieldDescricao.setText(unidadeMedidaDTO.getDescricao());
	}

	private void esvaziaCamposTela() {
		textFieldUnidade.setText("");
		textFieldDescricao.setText("");
	}
}
