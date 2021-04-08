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
import com.uem.controle.estoque.view.relatorio.RelatorioView;
import com.uem.controle.estoque.view.unidade.UnidadeMedidaView;

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
		btnProduto.setBounds(121, 173, 458, 31);
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
		
		JLabel btnMovimentacao = new JLabel();
		btnMovimentacao.setText("Movimentação");
		btnMovimentacao.setOpaque(true);
		btnMovimentacao.setHorizontalAlignment(SwingConstants.CENTER);
		btnMovimentacao.setForeground(Color.DARK_GRAY);
		btnMovimentacao.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnMovimentacao.setBackground(Color.WHITE);
		btnMovimentacao.setBounds(121, 238, 458, 31);
		btnMovimentacao.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnMovimentacao);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnMovimentacao);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				MovimentacaoView movimentacaoView = ApplicationContextProvider.getContext().getBean(MovimentacaoView.class);				
				movimentacaoView.frame.setVisible(true);
				
			}
		});
		this.add(btnMovimentacao);
		
		JLabel btnReajuste = new JLabel();
		btnReajuste.setText("Reajuste de preços");
		btnReajuste.setOpaque(true);
		btnReajuste.setHorizontalAlignment(SwingConstants.CENTER);
		btnReajuste.setForeground(Color.DARK_GRAY);
		btnReajuste.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnReajuste.setBackground(Color.WHITE);
		btnReajuste.setBounds(121, 300, 458, 31);
		btnReajuste.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnReajuste);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnReajuste);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReajustePrecoView reajustePrecoView = ApplicationContextProvider.getContext().getBean(ReajustePrecoView.class);				
				reajustePrecoView.frame.setVisible(true);				
			}
		});
		this.add(btnReajuste);
		
		JLabel btnRelatorio = new JLabel();
		btnRelatorio.setText("Relatórios");
		btnRelatorio.setOpaque(true);
		btnRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		btnRelatorio.setForeground(Color.DARK_GRAY);
		btnRelatorio.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnRelatorio.setBackground(Color.WHITE);
		btnRelatorio.setBounds(121, 426, 458, 31);
		btnRelatorio.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnRelatorio);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnRelatorio);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				RelatorioView relatorioView = ApplicationContextProvider.getContext().getBean(RelatorioView.class);				
				relatorioView.frame.setVisible(true);	
				
			}
		});
		this.add(btnRelatorio);
		
		
		JLabel btnUnidade = new JLabel();
		btnUnidade.setText("Cadastro de Unidade");
		btnUnidade.setOpaque(true);
		btnUnidade.setHorizontalAlignment(SwingConstants.CENTER);
		btnUnidade.setForeground(Color.DARK_GRAY);
		btnUnidade.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnUnidade.setBackground(Color.WHITE);
		btnUnidade.setBounds(121, 364, 458, 31);
		btnUnidade.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnUnidade);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnUnidade);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				UnidadeMedidaView unidadeMedidaView = ApplicationContextProvider.getContext().getBean(UnidadeMedidaView.class);				
				unidadeMedidaView.frame.setVisible(true);	
				
			}
		});
		this.add(btnUnidade);
		
		frame.getContentPane().add(this);
	}
}
