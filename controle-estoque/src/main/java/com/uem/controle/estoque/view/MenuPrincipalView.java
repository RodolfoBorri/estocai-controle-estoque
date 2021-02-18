package com.uem.controle.estoque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

import com.uem.controle.estoque.ApplicationContextProvider;

@Component
public class MenuPrincipalView extends JPanel{
	
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
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(104,130,252);
        Color color2 = new Color(193,110,253);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(
            0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
	
	private void initialize() {
		frame = new JFrame();
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
        		btnProduto.setBackground(new Color(236, 178, 247));
        		btnProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnProduto.setBackground(UIManager.getColor("control"));
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
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
        		btnProduto_1.setBackground(new Color(236, 178, 247));
        		btnProduto_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnProduto_1.setBackground(UIManager.getColor("control"));
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
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
        		btnProduto_2.setBackground(new Color(236, 178, 247));
        		btnProduto_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnProduto_2.setBackground(UIManager.getColor("control"));
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
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
        		btnProduto_3.setBackground(new Color(236, 178, 247));
        		btnProduto_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnProduto_3.setBackground(UIManager.getColor("control"));
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
			}
		});
		this.add(btnProduto_3);
		
		frame.getContentPane().add(this);
		
		JLabel lblNewLabel = new JLabel("ESTOCAÍ COMERCIO DE PRODUTOS LTDA.");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setBounds(155, 25, 388, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SISTEMA DE CONTROLE DE ESTOQUE");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(180, 55, 388, 25);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MENU PRINCIPAL");
		lblNewLabel_2.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_2.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(280, 115, 388, 25);
		add(lblNewLabel_2);
	}
}
