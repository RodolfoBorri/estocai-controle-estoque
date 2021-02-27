package com.uem.controle.estoque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;
import com.uem.controle.estoque.view.movimentacao.MovimentacaoView;
import com.uem.controle.estoque.view.produto.ProdutoView;
import com.uem.controle.estoque.view.reajuste.ReajustePrecoView;

@Component
public class MenuPrincipalView extends ViewBase{
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	private JLabel btnProduto;
		
	public static void run() {
		try {
			MenuPrincipalView window = new MenuPrincipalView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MenuPrincipalView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("MENU PRINCIPAL"));		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		btnProduto = new JLabel();
		btnProduto.setOpaque(true);
		btnProduto.setBounds(121, 184, 458, 31);
		btnProduto.setBackground(Color.WHITE);
        btnProduto.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18));
        btnProduto.setForeground(Color.DARK_GRAY);
        btnProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnProduto.setText("Cadastro de Produtos");
        btnProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnProduto);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ProdutoView produtoView = ApplicationContextProvider.getContext().getBean(ProdutoView.class);				
				produtoView.frame.setVisible(true);
			}
		});
        this.add(btnProduto);                
		
		JLabel btnProduto_1 = new JLabel();
		btnProduto_1.setText("Movimentação");
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
				MovimentacaoView movimentacaoView = ApplicationContextProvider.getContext().getBean(MovimentacaoView.class);				
				movimentacaoView.frame.setVisible(true);
				
			}
		});
		this.add(btnProduto_1);
		
		JLabel btnProduto_2 = new JLabel();
		btnProduto_2.setText("Reajuste de preços");
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
				ReajustePrecoView reajustePrecoView = ApplicationContextProvider.getContext().getBean(ReajustePrecoView.class);				
				reajustePrecoView.frame.setVisible(true);				
			}
		});
		this.add(btnProduto_2);
		
		JLabel btnProduto_3 = new JLabel();
		btnProduto_3.setText("Relatórios");
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
				//TODO
			}
		});
		this.add(btnProduto_3);
		
		frame.getContentPane().add(this);					
	}
}
