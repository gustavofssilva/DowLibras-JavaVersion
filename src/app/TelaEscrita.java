package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import entities.Letra;
import util.CriacaoAlfabeto;

public class TelaEscrita extends JFrame {
	private static final long serialVersionUID = 1412871901054812203L;
	
	private JTextArea tA = new JTextArea();
	private InputStream icon = TelaEscrita.class.getResourceAsStream("/icon.png"); //Imagem de ícone
	private JButton finish = new JButton("Finalizar");
	private Letra[] letras;
	private boolean validWord = false;
	private boolean validLetter = false;
	public TelaEscrita() {
		super("Escreva o texto a ser traduzido - Tradutor para Libras");
	    setSize(1350, 700);
	    setLocation(10,1);
	    setLayout (null); 
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
		try {
			setIconImage(ImageIO.read(icon));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] alfabetoCompativel = new char[CriacaoAlfabeto.alfabetoLibra.length];
				for(int i = 0; i < CriacaoAlfabeto.alfabetoLibra.length; i++) {
					alfabetoCompativel[i] = CriacaoAlfabeto.alfabetoLibra[i].getLetra();
				}
				int nmrCorretos = 0;
				for(int i = 0; i < tA.getText().toString().length(); i++) {
					for (char c : alfabetoCompativel) {
					    if (c == tA.getText().charAt(i)) {
					    	nmrCorretos++;
					        break;
					    }
					    
					}

					}
				if(nmrCorretos == tA.getText().toString().length()) {
					letras = CriacaoAlfabeto.stringToLetra(tA.getText());
				}
				 
					if(nmrCorretos != tA.getText().toString().length()) {
							JOptionPane.showMessageDialog(null, "Palavra Invalida!", "Remova qualquer letra inválida (acentos ou maiúsculas)", JOptionPane.ERROR_MESSAGE);
						} else {
						if(letras.length > 0) {
				 new TelaTraducao(letras, 0);//Criar o metodo para construir arrays de letras e criar janela com a traducao
				dispose();
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Escreva Algo!", "Tente novamente", JOptionPane.ERROR_MESSAGE);
				 }
			}
			}
		});
		tA.setBounds(150, 1, 1000, 575);
		tA.setEditable(true);
		tA.setFocusable(true);
		tA.setEnabled(true);
		finish.setBounds(500, 600, 300, 50);
		add(tA);
		add(finish);
		
	}


}
