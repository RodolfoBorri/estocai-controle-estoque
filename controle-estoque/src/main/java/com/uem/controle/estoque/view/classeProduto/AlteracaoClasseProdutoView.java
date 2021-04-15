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
public class AlteracaoClasseProdutoView extends ViewBase{

	private static final long serialVersionUID = 1L;

	@Autowired
	ClasseProdutoController classeProdutoController;
	
	public JFrame frame;
	private JTextField textFieldDescricao;
	private JTextField textFieldCodigo;
		
	public static void run() {
		try {
			AlteracaoClasseProdutoView window = new AlteracaoClasseProdutoView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AlteracaoClasseProdutoView() {
		this.setBorder(BorderFactory.createEmptyBorder(32, 32, 600, 1000));
		initialize();
		montaCabecalhoCompleto();
	}
	
	private void montaCabecalhoCompleto() {
		
		this.add(montaComecoHeader());		
		
		this.add(montaContinuacaoHeader());
		
		this.add(montaTituloTela("ALTERAÇÃO DE CLASSE DE PRODUTO"));		
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
		btnProduto_3.setText("Confirmar");
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
				ClasseProdutoDTO classeProdutoDTO = converteCamposParaDto();
				
				if(classeProdutoDTO != null) {

					String errors = new String("");
					errors = validaCamposUnidade(classeProdutoDTO);
					
					if(errors.equals("")) {
						int result = JOptionPane.showConfirmDialog(frame,
					            "Deseja confirmar a alteração ?", "Confirmação de alteração",
					            JOptionPane.YES_NO_OPTION);
						
					        if (result == JOptionPane.YES_OPTION) {
					        	classeProdutoController.alteraClasseProduto(classeProdutoDTO);
					        	
					        	JOptionPane.showMessageDialog(null, "Classe de Produto Alterada com sucesso!", 
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
		
		JLabel lblPreco = new JLabel("Codigo Classe Produto:");
		lblPreco.setForeground(SystemColor.inactiveCaptionBorder);
		lblPreco.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		lblPreco.setBounds(10, 169, 164, 25);
		add(lblPreco);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setCaretColor(new Color(240, 242, 245));
		textFieldCodigo.setBorder(null);
		textFieldCodigo.setOpaque(false);
		textFieldCodigo.setForeground(new Color(240, 242, 245));
		textFieldCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldCodigo.setBounds(180, 159, 99, 40);
		textFieldCodigo.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 16));
		textFieldCodigo.setColumns(10);
		this.add(textFieldCodigo);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(180, 192, 99, 2);
		add(separator1);
		
		JLabel btnBuscaClasseProduto = new JLabel();
		btnBuscaClasseProduto.setText("Buscar");
		btnBuscaClasseProduto.setOpaque(true);
		btnBuscaClasseProduto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscaClasseProduto.setForeground(Color.DARK_GRAY);
		btnBuscaClasseProduto.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));
		btnBuscaClasseProduto.setBackground(Color.WHITE);
		btnBuscaClasseProduto.setBounds(437, 165, 111, 31);
		btnBuscaClasseProduto.addMouseListener(new MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBuscaClasseProduto.setBackground(new Color(54, 209, 80));
				btnBuscaClasseProduto.setForeground(Color.WHITE);
				btnBuscaClasseProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBuscaClasseProduto.setBackground(Color.WHITE);
				btnBuscaClasseProduto.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				buscaClasseProduto();			
			}
		});

		add(btnBuscaClasseProduto);		

		frame.getContentPane().add(this);
	}	
	
	private void buscaClasseProduto() {
		try {
			String classeProduto = textFieldCodigo.getText();
			ClasseProdutoDTO classeProdutoDTO = classeProdutoController.buscaClasseProduto(classeProduto);
			preencheCamposClasseProdutoTela(classeProdutoDTO);
			textFieldCodigo.setEditable(false);
		}
		catch(Exception ex)
		{
			esvaziaCamposTela();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			textFieldCodigo.setEditable(true);
		}		
	}

	private void preencheCamposClasseProdutoTela(ClasseProdutoDTO classeProdutoDTO) {
		textFieldCodigo.setText(classeProdutoDTO.getCodigo());
		textFieldDescricao.setText(classeProdutoDTO.getDescricao());
	}

	private void esvaziaCamposTela() {
		textFieldCodigo.setText("");
		textFieldDescricao.setText("");
	}

	private String validaCamposUnidade(ClasseProdutoDTO classeProdutoDTO) {
		return classeProdutoController.validaCamposClasseProduto(classeProdutoDTO, "Alteracao"); 
	}	
	
	private ClasseProdutoDTO converteCamposParaDto() {
		String descricao, codigo;
		
		try {
			descricao = textFieldDescricao.getText();
			codigo = textFieldCodigo.getText();
			
			return new ClasseProdutoDTO(descricao, codigo);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, ExceptionEnum.CE_9.getCodigo(), "ERRO!", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}
