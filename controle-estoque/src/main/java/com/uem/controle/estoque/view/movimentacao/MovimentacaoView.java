package com.uem.controle.estoque.view.movimentacao;

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
public class MovimentacaoView extends ViewBase{
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	private JLabel btnEntradaMovimentacao;
	
	public static void run() {
		try {
			MovimentacaoView window = new MovimentacaoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MovimentacaoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("MOVIMENTAÇÃO"));		
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
		
		btnEntradaMovimentacao = new JLabel();
		btnEntradaMovimentacao.setOpaque(true);
		btnEntradaMovimentacao.setBounds(121, 184, 458, 31);
		btnEntradaMovimentacao.setBackground(Color.WHITE);
        btnEntradaMovimentacao.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18));
        btnEntradaMovimentacao.setForeground(Color.DARK_GRAY);
        btnEntradaMovimentacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEntradaMovimentacao.setText("Entrada");
        btnEntradaMovimentacao.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnEntradaMovimentacao);

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnEntradaMovimentacao);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				EntradaMovimentacaoView entradaMovimentacaoView = ApplicationContextProvider.getContext().getBean(EntradaMovimentacaoView.class);				
				entradaMovimentacaoView.frame.setVisible(true);
			}
		});
        this.add(btnEntradaMovimentacao);                
		
		JLabel btnSaidaMovimentacao = new JLabel();
		btnSaidaMovimentacao.setText("Saída");
		btnSaidaMovimentacao.setOpaque(true);
		btnSaidaMovimentacao.setHorizontalAlignment(SwingConstants.CENTER);
		btnSaidaMovimentacao.setForeground(Color.DARK_GRAY);
		btnSaidaMovimentacao.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnSaidaMovimentacao.setBackground(Color.WHITE);
		btnSaidaMovimentacao.setBounds(121, 249, 458, 31);
		btnSaidaMovimentacao.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnSaidaMovimentacao);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnSaidaMovimentacao);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SaidaMovimentacaoView saidaMovimentacaoView  = ApplicationContextProvider.getContext().getBean(SaidaMovimentacaoView .class);				
				saidaMovimentacaoView .frame.setVisible(true);				
			}
		});
		this.add(btnSaidaMovimentacao);		
		
		frame.getContentPane().add(this);
	}

}
