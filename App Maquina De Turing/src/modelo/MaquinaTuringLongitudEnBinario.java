package modelo;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Esta clase toma una cadena de 'a', 'b' o 'c' y te da su longitud en binario
// ejemplo: abc = 11 	- 	'11' en binario es 3 que es la longitud de la cadena 
public class MaquinaTuringLongitudEnBinario {
	private char[] cinta;
	private int puntero;
	private Map<String, Transicion> transiciones;
	
	public MaquinaTuringLongitudEnBinario(String texto) {
		texto += ' ';
		this.cinta = texto.toCharArray();
		this.puntero = 0;
		this.transiciones = new HashMap<>();

		addTransicion("q0", 'a', "q1", 'x', Direccion.L);
		addTransicion("q0", 'b', "q1", 'x', Direccion.L);
		addTransicion("q0", 'c', "q1", 'x', Direccion.L);
		addTransicion("q0", 'x', "q0", 'x', Direccion.R);
		
		addTransicion("q1", ' ', "q0", '1', Direccion.R);
		addTransicion("q1", 'x', "q1", 'x', Direccion.L);
		addTransicion("q1", '0', "q2", '1', Direccion.L);
		addTransicion("q1", '1', "q3", '0', Direccion.L);
		
		addTransicion("q2", '1', "q2", '1', Direccion.L);
		addTransicion("q2", '0', "q2", '0', Direccion.L);
		addTransicion("q2", ' ', "q4", ' ', Direccion.R);
		
		addTransicion("q3", '1', "q3", '0', Direccion.L);
		addTransicion("q3", '0', "q2", '1', Direccion.L);
		addTransicion("q3", ' ', "q4", '1', Direccion.R);
		
		addTransicion("q4", '0', "q4", '0', Direccion.R);
		addTransicion("q4", '1', "q4", '1', Direccion.R);
		addTransicion("q4", 'x', "q0", 'x', Direccion.R);
		
		addTransicion("q0", ' ', "q5", ' ', Direccion.L);

		addTransicion("q5", 'x', "q5", ' ', Direccion.L);
		addTransicion("q5", '0', "H", '0', Direccion.L);
		addTransicion("q5", '1', "H", '1', Direccion.L);
		
		//FINAL
		
//		for (String c : transiciones.keySet()) {
//			System.out.println(c+" "+transiciones.get(c));;
//		}
	}

	public String analizar() {
		String estadoActual = "q0";

		while (!transiciones.get(estadoActual + cinta[puntero]).getSiguienteEstado().equals("H")) {
			Transicion transition = transiciones.get(estadoActual + cinta[puntero]);	
			
			cinta[puntero] = transition.getEscribirSimbolo();

			if (transition.getMoverDireccion() == Direccion.L) {
				puntero--;
			} else {
				puntero++;
			}

			estadoActual = transition.getSiguienteEstado();
			if (puntero == -1) {
				cinta = agregarSimboloAlInicio();
				puntero = 0;
			}
		}
		return eliminarEspacios();
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

	public String eliminarEspacios() {
		String salida = "";
		for (char c : cinta) {
			if (c != ' ') {
				salida += c;
			}
		}
		System.out.println(salida);
		return salida;
	}
	private char[] agregarSimboloAlInicio() {
		char[] cintaNueva = new char[cinta.length + 1];
		cintaNueva[0] = ' ';
		System.arraycopy(cinta, 0, cintaNueva, 1, cinta.length);
		return cintaNueva;
	}
}
