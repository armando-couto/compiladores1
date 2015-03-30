package trabalho;

import java.util.Scanner;
import java.util.Vector;

public class Teste {

	public static void gramatica(String lista) {

		String palavraReservada[] = { "public", "class", "boolean", "int",
				"string", "while", "if", "scanner", "print" };

		String simbolo[] = { "{", "}", "(", ")" };

		String operador[] = { "+", "-", "*", "/" };

		String listaAux[] = lista.split(" ");

		Vector<String> v = new Vector<String>();

		for (int i = 0; i < listaAux.length; i++) {
			v.add(listaAux[i]);
		}

		// System.out.println(v.remove(0));
		// System.out.println(v.remove(0));
		// System.out.println(v.remove(0));
		// System.out.println(v.remove(0));

		for (int i = 0; i < listaAux.length; i++) {
			for (int j = 0; j < palavraReservada.length; j++) {
				if (listaAux[i].equals(palavraReservada[j])) {
					System.out.println(palavraReservada[j]
							+ ": Palavra Reservada");
				}
			}

			for (int j = 0; j < simbolo.length; j++) {
				if (listaAux[i].equals(simbolo[j])) {
					System.out.println(simbolo[j] + ": SÌmbolo");
				}
			}

			for (int j = 0; j < operador.length; j++) {
				if (listaAux[i].equals(operador[j])) {
					System.out.println(operador[j] + ": Operador");
				}
			}

			if (Character.isDigit(listaAux[i].charAt(0))) {
				System.out.println(listaAux[i] + ": N˙mero");
			}

		}

	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String gramatica = scan.nextLine();
		Teste.gramatica(gramatica);
	}

}
