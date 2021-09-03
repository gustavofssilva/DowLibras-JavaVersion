package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.Usuario;
import util.UsuarioDAO;

public class LogIn extends JFrame {
	private static final long serialVersionUID = 4180310973501726503L;
	private JTextField userField = new JTextField(15); //Espaço para nome de usuário
	private JTextField passField = new JPasswordField(20); //Espaço para Senha
	
	private JLabel userLabel = new JLabel("Nome de Usuário:"); //Texto anunciando Espaço para nome de usuario
	private JLabel passLabel = new JLabel("Senha:"); //Texto anunciando Espaço para senha
	
	private JPanel userPanel = new JPanel(); //Painel para espaço para nome de usuário
	private JPanel passPanel = new JPanel(); //Painel para espaço para senha
	private JPanel buttonPanel = new JPanel(); //Painel para botões
	
	private JButton loginButton = new JButton("Log-in"); //Botão de Log-In
	private JButton registerButton = new JButton("Registre-se"); //Botão de Registro
	
	private UsuarioDAO dao = new UsuarioDAO(); //DAO de Usuario
	
	private InputStream icon = LogIn.class.getResourceAsStream("/icon.png"); //Ícone

	private Color bgColor = new Color(59, 145, 40); //Cores utilizadas
	private Color fontColor = new Color(122, 57, 121);
	
	public LogIn() {
		super("Log-in de Usuario - Tradutor para Libras"); //Configurações da Janela
	    setSize(500, 500);
	    setLocation(350,50);
	    setLayout (null); 
	    setResizable(false);
	    getContentPane().setBackground(bgColor);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(icon));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		userLabel.setBounds(50, 20, 80, 20); //Configurações do espaço para nome
		userLabel.setForeground(fontColor);
		userField.setBounds(70, 30,150, 20);
		userPanel.setBounds(50, 200, 300, 30);
		userPanel.add(userLabel);
		userPanel.add(userField);
		userPanel.setBackground(bgColor);
		
		passLabel.setBounds(50,30,80,20); //Configurações do espaço para senha
		passLabel.setForeground(fontColor);
	    passField.setBounds(70,30,150,20);
	    passPanel.setBounds(90, 250, 300, 30);
	    passPanel.add(passLabel);
	    passPanel.add(passField);
	    passPanel.setBackground(bgColor);
	    
	    loginButton.setBounds(170, 375, 150, 50); //Configurações dos botões
	    
	    loginButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		Usuario usuario = new Usuario();
	    		usuario.setNomeUsuario(userField.getText());
	    		usuario.setSenhaUsuario(passField.getText());
	    		Usuario usuarioSelecionado = new Usuario();
	    		Object[] objetoGerado = dao.lista("nome_usuario = '" + usuario.getNomeUsuario() + "'").toArray();
	    		Usuario[] listaAdmin = Arrays.asList(objetoGerado).toArray(new Usuario[objetoGerado.length]);
	    		if(listaAdmin.length > 0) {
		    		usuarioSelecionado = listaAdmin[0];
	    		if(usuarioSelecionado.getSenhaUsuario().equals(usuario.getSenhaUsuario())) {
	    			JOptionPane.showMessageDialog(null, "Login efetuado com sucesso", "", JOptionPane.PLAIN_MESSAGE);
	    			new TelaEscrita();
	    	        dispose();
	    	}
	    		    else {
	    		    	JOptionPane.showMessageDialog(null, "Senha incorreta!", "Tente novamente", JOptionPane.ERROR_MESSAGE);
	    		    }
	    	}
    		    else {
    		    	JOptionPane.showMessageDialog(null, "Nome de Usuário incorreto!", "Tente novamente", JOptionPane.ERROR_MESSAGE);
    		    }
	    	}
	    	}); 
	    
	    registerButton.setBounds(170, 375, 150, 50);
	    
	    registerButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		Usuario usuario = new Usuario();
	    		usuario.setNomeUsuario(userField.getText());
	    		usuario.setSenhaUsuario(passField.getText());
	    		if(usuario.getSenhaUsuario().isBlank() == false) {
	    		if(dao.insere(usuario)) {
	    			JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso", "", JOptionPane.PLAIN_MESSAGE);
	    			new TelaEscrita();
	    	        dispose();
	    	}
    		    else {
    		    	JOptionPane.showMessageDialog(null, "Nome de usuário já existente!", "Escolha outro nome", JOptionPane.ERROR_MESSAGE);
    		    }
	    		}
	    		    else {
	    		    	JOptionPane.showMessageDialog(null, "Escreva uma senha!", "Senha vazia", JOptionPane.ERROR_MESSAGE);
	    		    }
	    	}
	    	}); 
	    buttonPanel.setBounds(70, 375, 350, 50);
	    buttonPanel.add(registerButton);
	    buttonPanel.add(loginButton);
	    buttonPanel.setBackground(bgColor);
	    
	    add(userPanel);
	    add(passPanel);
	    add(buttonPanel);
	    setVisible(true);
	}
}
