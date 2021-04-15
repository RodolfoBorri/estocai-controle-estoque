package com.uem.controle.estoque.view.classeProduto;

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
import com.uem.controle.estoque.controller.ClasseProdutoController;
import com.uem.controle.estoque.dto.ClasseProdutoDTO;
import com.uem.controle.estoque.enumerator.ExceptionEnum;
import com.uem.controle.estoque.view.ViewBase;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InclusaoClasseProdutoView extends ViewBase{

	private static final long serialVersionUID = 1L;

	@Autowired
	ClasseProdutoController classeProdutoController;
	
	public JFrame frame;
	private JLabel lblPreco;
	private JTextField textFieldDescricao;
	private JTextField textFieldCodigoClasseProduto;
		
	public static void run() {
		try {
			InclusaoClasseProdutoView window = new InclusaoClasseProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InclusaoClasseProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("INCLUSÃO DE CLASSE DE PRODUTO"));		
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
			public void windowClosed(WindowEvent arg0) {
				frame.dispose();
				ClasseProdutoView classeProdutoView = ApplicationContextProvider.getContext().getBean(ClasseProdutoView.class);
				classeProdutoView.frame.setVisible(true);
			}
		});
		frame.setResizable(false);
		frame.setTitle("Estocaí - Sistema de controle de estoque");
		frame.setBounds(100, 100, 727, 521);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);		

		JLabel btnProduto_3 = new JLabel();
		btnProduto_3.setText("Cadastrar");
		btnProduto_3.setOpaque(true);
		btnProduto_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnProduto_3.setForeground(Color.DARK_GRAY);
		btnProduto_3.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnProduto_3.setBackground(Color.WHITE);
		btnProduto_3.setBounds(58, 430, 256, 31);
		btnProduto_3.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(java.awt.event.MouseEvent evt) {
        		btnProduto_3.setBackground(new Color(54, 209, 80));
        		btnProduto_3.setForeground(Color.WHITE);
        		btnProduto_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	btnProduto_3.setBackground(Color.WHITE);
        		btnProduto_3.setForeground(Color.DARK_GRAY);
            }
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				ClasseProdutoDTO classeProdutoMedidaDTO = converteCamposParaDto();
				
				if(classeProdutoMedidaDTO != null) {

					String errors = new String("");
					errors = validaCamposClasseProduto(classeProdutoMedidaDTO);
					
					if(errors.equals("")) {
						int result = JOptionPane.showConfirmDialog(frame,
					            "Deseja confirmar a inclusão ?", "Confirmação de inclusão",
					            JOptionPane.YES_NO_OPTION);
						
					        if (result == JOptionPane.YES_OPTION) {
					        	String codigoGerado = classeProdutoController.cadastra(classeProdutoMedidaDTO);
					        	atualizaCamposCodigo(codigoGerado);
					        
					        	JOptionPane.showMessageDialog(null, "Classe de Produto Cadastrada com sucesso!", 
					        		  "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					        }
					        else if (result == JOptionPane.NO_OPTION)
					          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					else {
						JOptionPane.showMessageDialog(null, errors, "ERROS", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnProduto_3);
		
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
				ClasseProdutoView classeProdutoView = ApplicationContextProvider.getContext().getBean(ClasseProdutoView.class);
				classeProdutoView.frame.setVisible(true);
			}
		});
		this.add(btnCancelar);		
		
		frame.getContentPane().add(this);
		
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
		
		lblPreco = new JLabel("Codigo Classe Produto:");
		lblPreco.setEnabled(false);
		lblPreco.setForeground(SystemColor.inactiveCaptionBorder);
		lblPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPreco.setBounds(10, 169, 164, 25);
		add(lblPreco);
		
		textFieldCodigoClasseProduto = new JTextField();
		textFieldCodigoClasseProduto.setEnabled(false);
		textFieldCodigoClasseProduto.setCaretColor(new Color(240, 242, 245));
		textFieldCodigoClasseProduto.setBorder(null);
		textFieldCodigoClasseProduto.setOpaque(false);
		textFieldCodigoClasseProduto.setForeground(new Color(240, 242, 245));
		textFieldCodigoClasseProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldCodigoClasseProduto.setBounds(180, 159, 99, 40);
		textFieldCodigoClasseProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldCodigoClasseProduto.setColumns(10);
		this.add(textFieldCodigoClasseProduto);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(180, 192, 99, 2);
		add(separator1);
	}	
	
	private void atualizaCamposCodigo(String codigoGerado) {
		textFieldCodigoClasseProduto.setText(codigoGerado);
    	textFieldCodigoClasseProduto.setEnabled(true);
    	textFieldCodigoClasseProduto.setEditable(false);
    	lblPreco.setEnabled(true);
	}

	private String validaCamposClasseProduto(ClasseProdutoDTO classeProdutoMedidaDTO) {
		return classeProdutoController.validaCamposClasseProduto(classeProdutoMedidaDTO, "Inclusao"); 
	}	
	
	private ClasseProdutoDTO converteCamposParaDto() {
		String descricao;
		
		try {
			descricao = textFieldDescricao.getText();
			
			return new ClasseProdutoDTO(descricao, "");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, ExceptionEnum.CE_9.getCodigo(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}