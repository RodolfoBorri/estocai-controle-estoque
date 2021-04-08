package com.uem.controle.estoque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.uem.controle.estoque.dto.UnidadeMedidaDTO;

public class ViewBase extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final String SOMA = "Soma";
	private final String SUBTRACAO = "Subtracao";
	
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
	
	protected UnidadeMedidaDTO converteStringEmUnidadeDTO(String string) {
		String stringSemEspacos = string.replaceAll(" ", "");	//removendo espaços
		int indexSeparador = stringSemEspacos.indexOf("-");
		
		String unidade = stringSemEspacos.substring(0, indexSeparador);
		String descricao = stringSemEspacos.substring(indexSeparador + 1, stringSemEspacos.length());
		
		return new UnidadeMedidaDTO(descricao, unidade);
	}
	
	protected String atualizaQuantidadeTotalMovimentacao(JTextField textFieldQuantidadeAtual, JTextField textFieldQuantidadeEntrada, String operacao) {
		
		int quantidadeEntrada, quantidadeAtual;
		Integer quantidadeTotal = 0;
		
		try {
			
			if(SOMA.equals(operacao)) {
				
				quantidadeAtual = Integer.parseInt(textFieldQuantidadeAtual.getText());
				quantidadeEntrada = Integer.parseInt(textFieldQuantidadeEntrada.getText());
				
				quantidadeTotal = quantidadeAtual + quantidadeEntrada;
				
				return quantidadeTotal.toString();	
			}
			
			if(SUBTRACAO.equals(operacao)) {
				quantidadeAtual = Integer.parseInt(textFieldQuantidadeAtual.getText());
				quantidadeEntrada = Integer.parseInt(textFieldQuantidadeEntrada.getText());
				
				quantidadeTotal = quantidadeAtual - quantidadeEntrada;
			}
			
			return quantidadeTotal.toString();
		}
		catch(Exception e) {
			return "";
		}		
	}
	
	protected String atualizaValorTotal(JTextField textFieldQuantidade, JTextField textFieldPreco) {
		try {
			BigDecimal quantidade = new BigDecimal(textFieldQuantidade.getText());
			BigDecimal preco = new BigDecimal(textFieldPreco.getText());
			
			BigDecimal valorTotal = preco.multiply(quantidade);
			
			return valorTotal.toString();
		}
		catch(Exception ex) {
			return "";
		}	
	}

	protected void mouseEnteredEvent(JLabel button) {
		button.setBackground(new Color(236, 178, 247));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	protected void mouseLeftEvent(JLabel button) {
		button.setBackground(UIManager.getColor("control"));
	}

	protected JLabel montaComecoHeader() {
		JLabel lblNewLabel = new JLabel("ESTOCAÍ COMERCIO DE PRODUTOS LTDA.");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setBounds(155, 25, 388, 25);

		return lblNewLabel;
	}

	protected JLabel montaContinuacaoHeader() {
		JLabel lblNewLabel = new JLabel("SISTEMA DE CONTROLE DE ESTOQUE");
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setBounds(180, 55, 388, 25);
		
		return lblNewLabel;
	}
	
	protected JLabel montaTituloTela(String titulo) {
		JLabel lblNewLabel = new JLabel(titulo);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblNewLabel.setBounds(280, 115, 388, 25);
		
		return lblNewLabel;
	}
}
