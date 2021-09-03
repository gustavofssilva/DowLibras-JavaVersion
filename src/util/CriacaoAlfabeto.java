package util;
import entities.Letra;
public class CriacaoAlfabeto {
	public static Letra[] alfabetoLibra = new Letra[27];
	public static void criar() {
		for(int i = 0; i < alfabetoLibra.length-1; i++) {
			alfabetoLibra[i] = new Letra(descobrirLetra(i));
		}
		alfabetoLibra[26] = new Letra('ç');
	}
	private static char descobrirLetra(int i) {
		int valor = i + 97;
		char letra = (char) valor;
		return letra;
	}
	public static int encontrar(char letra) {
		int numero = -1;
		for(int i = 0; i < alfabetoLibra.length; i++) {
			if(alfabetoLibra[i].getLetra() == letra) {
				numero = i;
			}
		}
		return numero;
	}
	public static Letra[] stringToLetra(String texto) {
		char[] textoChar = texto.toCharArray();
		Letra[] resultado = new Letra[textoChar.length];
		for(int i = 0; i < textoChar.length; i++) {
			resultado[i] = new Letra(textoChar[i]);
		}
		return resultado;
	}
}
