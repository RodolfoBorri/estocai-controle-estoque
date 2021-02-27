package com.uem.controle.estoque.view.produto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.view.MenuPrincipalView;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProdutoView extends ViewBase{
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	private JLabel btnInclusaoProduto;
	
	public static void run() {
		try {
			ProdutoView window = new ProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("CADASTRO DE PRODUTOS"));		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
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
		
		btnInclusaoProduto = new JLabel();
		btnInclusaoProduto.setOpaque(true);
		btnInclusaoProduto.setBounds(121, 184, 458, 31);
		btnInclusaoProduto.setBackground(Color.WHITE);
        btnInclusaoProduto.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18));
        btnInclusaoProduto.setForeground(Color.DARK_GRAY);
        btnInclusaoProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInclusaoProduto.setText("Inclusão");
        btnInclusaoProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnInclusaoProduto);

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnInclusaoProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				InclusaoProdutoView inclusaoProdutoView = ApplicationContextProvider.getContext().getBean(InclusaoProdutoView.class);				
				inclusaoProdutoView.frame.setVisible(true);
			}
		});
        this.add(btnInclusaoProduto);                
		
		JLabel btnProduto_1 = new JLabel();
		btnProduto_1.setText("Alteração");
		btnProduto_1.setOpaque(true);
		btnProduto_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_1.setForeground(Color.DARK_GRAY);
		btnProduto_1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_1.setBackground(Color.WHITE);
		btnProduto_1.setBounds(121, 249, 458, 31);
		btnProduto_1.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnProduto_1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnProduto_1);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AlteracaoProdutoView alteracaoProdutoView = ApplicationContextProvider.getContext().getBean(AlteracaoProdutoView.class);				
				alteracaoProdutoView.frame.setVisible(true);				
			}
		});
		this.add(btnProduto_1);
		
		JLabel btnProduto_2 = new JLabel();
		btnProduto_2.setText("Consulta");
		btnProduto_2.setOpaque(true);
		btnProduto_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_2.setForeground(Color.DARK_GRAY);
		btnProduto_2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_2.setBackground(Color.WHITE);
		btnProduto_2.setBounds(121, 311, 458, 31);
		btnProduto_2.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnProduto_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnProduto_2);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ConsultaProdutoView consultaProdutoView = ApplicationContextProvider.getContext().getBean(ConsultaProdutoView.class);				
				consultaProdutoView .frame.setVisible(true);		
			}
		});
		this.add(btnProduto_2);
		
		JLabel btnProduto_3 = new JLabel();
		btnProduto_3.setText("Exclusão");
		btnProduto_3.setOpaque(true);
		btnProduto_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_3.setForeground(Color.DARK_GRAY);
		btnProduto_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_3.setBackground(Color.WHITE);
		btnProduto_3.setBounds(121, 374, 458, 31);
		btnProduto_3.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnProduto_3);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnProduto_3);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ExclusaoProdutoView exclusaoProdutoView = ApplicationContextProvider.getContext().getBean(ExclusaoProdutoView.class);				
				exclusaoProdutoView.frame.setVisible(true);
			}
		});
		this.add(btnProduto_3);
		
		frame.getContentPane().add(this);
	}

}
