package com.uem.controle.estoque.view.classeProduto;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
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
public class ClasseProdutoView extends ViewBase{
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	private JLabel btnInclusaoProduto;
	
	public static void run() {
		try {
			ClasseProdutoView window = new ClasseProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClasseProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("CADASTRO DE CLASSE DE PRODUTO"));		
	}
	
	@Override
	protected JLabel montaTituloTela(String titulo) {
		JLabel lblNewLabel = new JLabel(titulo);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel.setBounds(240, 115, 350, 25);
		
		return lblNewLabel;
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
				InclusaoClasseProdutoView inclusaoClasseProdutoView = ApplicationContextProvider.getContext().getBean(InclusaoClasseProdutoView.class);				
				inclusaoClasseProdutoView.frame.setVisible(true);
			}
		});
        this.add(btnInclusaoProduto);                
		
		JLabel btnAlteracaoProduto = new JLabel();
		btnAlteracaoProduto.setText("Alteração");
		btnAlteracaoProduto.setOpaque(true);
		btnAlteracaoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnAlteracaoProduto.setForeground(Color.DARK_GRAY);
		btnAlteracaoProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnAlteracaoProduto.setBackground(Color.WHITE);
		btnAlteracaoProduto.setBounds(121, 249, 458, 31);
		btnAlteracaoProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnAlteracaoProduto);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnAlteracaoProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AlteracaoClasseProdutoView alteracaoClasseProdutoView = ApplicationContextProvider.getContext().getBean(AlteracaoClasseProdutoView.class);				
				alteracaoClasseProdutoView.frame.setVisible(true);
			}
		});
		this.add(btnAlteracaoProduto);
		
		JLabel btnConsultaProduto = new JLabel();
		btnConsultaProduto.setText("Consulta");
		btnConsultaProduto.setOpaque(true);
		btnConsultaProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnConsultaProduto.setForeground(Color.DARK_GRAY);
		btnConsultaProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnConsultaProduto.setBackground(Color.WHITE);
		btnConsultaProduto.setBounds(121, 311, 458, 31);
		btnConsultaProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnConsultaProduto);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnConsultaProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ConsultaClasseProdutoView consultaClasseProdutoView = ApplicationContextProvider.getContext().getBean(ConsultaClasseProdutoView.class);				
				consultaClasseProdutoView.frame.setVisible(true);
			}
		});
		this.add(btnConsultaProduto);
		
		JLabel btnExclusaoProduto = new JLabel();
		btnExclusaoProduto.setText("Exclusão");
		btnExclusaoProduto.setOpaque(true);
		btnExclusaoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnExclusaoProduto.setForeground(Color.DARK_GRAY);
		btnExclusaoProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnExclusaoProduto.setBackground(Color.WHITE);
		btnExclusaoProduto.setBounds(121, 374, 458, 31);
		btnExclusaoProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnExclusaoProduto);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnExclusaoProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ExclusaoClasseProdutoView exclusaoClasseProdutoView = ApplicationContextProvider.getContext().getBean(ExclusaoClasseProdutoView.class);				
				exclusaoClasseProdutoView.frame.setVisible(true);
			}
		});
		this.add(btnExclusaoProduto);
		
		frame.getContentPane().add(this);
	}

}
