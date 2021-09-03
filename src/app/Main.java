package app;
import util.CriacaoAlfabeto;
public class Main {

	public static void main(String[] args) {
		CriacaoAlfabeto.criar();
		for(int i = 0; i < CriacaoAlfabeto.alfabetoLibra.length; i++) {
		System.out.println(CriacaoAlfabeto.alfabetoLibra[i].getLetra());
	}
		System.out.println(CriacaoAlfabeto.encontrar('k'));
		new LogIn();
	}

}
