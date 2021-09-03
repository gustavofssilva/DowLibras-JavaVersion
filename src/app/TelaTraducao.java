package app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entities.Letra;
import util.CriacaoAlfabeto;


public class TelaTraducao extends JFrame {
	private static final long serialVersionUID = -2000250762211139836L;
	private JButton last = new JButton("<");
	private JButton next = new JButton(">");
	private JLabel pageNmbr = new JLabel();
	private BufferedImage letra;
	private JLabel letraLabel = new JLabel();
	private InputStream icon = TelaTraducao.class.getResourceAsStream("/icon.png"); //Imagem de ícone
	private int currentPage = 0;
	public TelaTraducao(final Letra[] arrayLetra, int page) {
		super("Tradução do texto - Tradutor para Libras");
	    setSize(400, 700);
	    setLocation(10,1);
	    setLayout (null); 
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
		currentPage = page;
		pageNmbr = new JLabel((currentPage + 1) + "/" + arrayLetra.length);
		String projectDirectory = new File("").getAbsolutePath();
		BufferedImage letraAtual;
		try {
			letraAtual = ImageIO.read(new File(projectDirectory + "\\resources\\" + arrayLetra[currentPage].getLetra() + ".png"));
			Image tmp = letraAtual.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
			letra = new BufferedImage(200, 250, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = letra.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(currentPage > 0) {
				 new TelaTraducao(arrayLetra, currentPage - 1);//Criar o metodo para construir arrays de letras e criar janela com a traducao
				dispose();
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Esse é o primeiro!", "", JOptionPane.ERROR_MESSAGE);
				 }
			}
		});
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(currentPage < arrayLetra.length - 1) {
				 new TelaTraducao(arrayLetra, currentPage + 1);//Criar o metodo para construir arrays de letras e criar janela com a traducao
				dispose();
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Esse é o último!", "", JOptionPane.ERROR_MESSAGE);
				 }
			}
		});
		letraLabel = new JLabel(new ImageIcon(letra));
		letraLabel.setBounds(1, 110, 400, 400);
		last.setBounds(1, 1, 50, 50);
		pageNmbr.setBounds(190, 1, 100, 100);
		next.setBounds(332, 1, 50, 50);
		add(last);
		add(pageNmbr);
		add(next);
		add(letraLabel);
	}
}
