package com.uem.controle.estoque.view.consultas;

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
public class ConsultaView extends ViewBase{
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
		
	public static void run() {
		try {
			ConsultaView window = new ConsultaView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ConsultaView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("MENU CONSULTAS"));		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				MenuPrincipalView menuPrincipalView = ApplicationContextProvider.getContext().getBean(MenuPrincipalView.class);
				menuPrincipalView.frame.setVisible(true);
			}
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		frame.getContentPane().add(this);
		
		JLabel btnConsultaDeProduto = new JLabel();
		btnConsultaDeProduto.setText("Consulta Saldos de Produtos");
		btnConsultaDeProduto.setOpaque(true);
		btnConsultaDeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnConsultaDeProduto.setForeground(Color.DARK_GRAY);
		btnConsultaDeProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnConsultaDeProduto.setBackground(Color.WHITE);
		btnConsultaDeProduto.setBounds(121, 176, 458, 31);
		btnConsultaDeProduto.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		mouseEnteredEvent(btnConsultaDeProduto);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	mouseLeftEvent(btnConsultaDeProduto);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ConsultaSaldosClasseProduto consultaSaldosClasseProduto = ApplicationContextProvider.getContext().getBean(ConsultaSaldosClasseProduto.class);				
				consultaSaldosClasseProduto.frame.setVisible(true);	
				
			}
		});
		this.add(btnConsultaDeProduto);
	}
}
