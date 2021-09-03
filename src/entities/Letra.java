package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Letra {
	private char letra;
	private BufferedImage imgLetra;
	public Letra(char letra) {
		this.letra = letra;
		InputStream icon = Letra.class.getResourceAsStream("/" + this.letra +".png");
		try {
			imgLetra = ImageIO.read(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public BufferedImage getImgLetra() {
		return imgLetra;
	}
	public void setImgLetra(BufferedImage imgLetra) {
		this.imgLetra = imgLetra;
	}
}
