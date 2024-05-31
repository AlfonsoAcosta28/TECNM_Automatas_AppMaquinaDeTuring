package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//Esta clase recobe 2 numeros y los transforma a unario y los resta
//ejemplo: 3 - 2 se transforma a 111-11 y el resultado se vuelve en 1
public class MaquinaTuringRestaUnario {
	private char[] cinta;
	private int puntero;
	private Map<String, Transicion> transiciones;
	
	public MaquinaTuringRestaUnario(int numeroUno, int numeroDos) {
		this.cinta = aUnos(numeroUno, numeroDos);
		this.puntero = 0;
		this.transiciones = new HashMap<>();

		addTransicion("q0", '1', "q0", '1', Direccion.R);
		addTransicion("q0", '-', "q0", '-', Direccion.R);

		addTransicion("q0", ' ', "q1", ' ', Direccion.L);

		addTransicion("q1", '1', "q2", 'x', Direccion.L);

		addTransicion("q2", '1', "q2", '1', Direccion.L);

		addTransicion("q2", '-', "q3", '-', Direccion.L);

		addTransicion("q3", 'a', "q3", 'a', Direccion.L);

		addTransicion("q3", '1', "q4", 'a', Direccion.R);

		addTransicion("q4", 'a', "q4", 'a', Direccion.R);
		addTransicion("q4", '1', "q4", '1', Direccion.R);
		addTransicion("q4", '-', "q4", '-', Direccion.R);

		addTransicion("q4", 'x', "q1", 'x', Direccion.L);

		addTransicion("q1", '-', "q5", '-', Direccion.R);

		addTransicion("q5", 'x', "q5", 'x', Direccion.R);

		addTransicion("q5", ' ', "q6", ' ', Direccion.L);

		//Paso final
		addTransicion("q6", '1', "q6", '1', Direccion.L);
		addTransicion("q6", 'x', "q6", ' ', Direccion.L);
		addTransicion("q6", '-', "q6", ' ', Direccion.L);
		addTransicion("q6", 'a', "q6", ' ', Direccion.L);
		//FINAL
		addTransicion("q6", ' ', "H", ' ', Direccion.L);
	}

	public String analizar() {
		String estadoActual = "q0";

		while (!transiciones.get(estadoActual + cinta[puntero]).getSiguienteEstado().equals("H")) {
			Transicion transition = transiciones.get(estadoActual + cinta[puntero]);
			System.out.println(cinta);

			cinta[puntero] = transition.getEscribirSimbolo();
			

			if (transition.getMoverDireccion() == Direccion.L) {
				puntero--;
			} else {
				puntero++;
			}

			estadoActual = transition.getSiguienteEstado();
			if (puntero == -1) {
				cinta = agregarSimboloAlInicio(cinta, ' ');
				puntero = 0;
			}
		}

		System.out.println("SALIDA: " + new String(cinta));
		return eliminarB();
	}


	public void addTransicion(String estadoActual, char simboloActual, String siguienteEstado, char esrcibirSimbolo, Direccion moverDireccion) {
		transiciones.put(estadoActual + simboloActual, new Transicion(siguienteEstado, esrcibirSimbolo, moverDireccion));
	} 

	public char[] aUnos(int numeroUno, int numeroDos) {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < numeroUno; i++) {
			cadena.append("1");
		}
		cadena.append("-");
		for (int i = 0; i < numeroDos; i++) {
			cadena.append("1");
		}

		cadena.append(" ");
		System.out.println(cadena);
		return cadena.toString().toCharArray();
	}

	public String eliminarB() {
		String salida = "";
		for (char c : cinta) {
			if (c != ' ') {
				salida += c;
			}
		}
		return salida;
	}
	private static char[] agregarSimboloAlInicio(char[] cintaOriginal, char simbolo) {
		char[] cintaNueva = new char[cintaOriginal.length + 1];
		cintaNueva[0] = simbolo;
		System.arraycopy(cintaOriginal, 0, cintaNueva, 1, cintaOriginal.length);
		return cintaNueva;
	}
}
