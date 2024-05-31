package modelo;


public class Transicion {
	private String siguienteEstado;
    private char escribirSimbolo;
    private Direccion moverDireccion;

    public Transicion(String siguienteEstado, char escribirSimbolo, Direccion moverDireccion) {
        this.siguienteEstado = siguienteEstado;
        this.escribirSimbolo = escribirSimbolo;
        this.moverDireccion = moverDireccion;
    }

	public String getSiguienteEstado() {
		return siguienteEstado;
	}

	public void setSiguienteEstado(String siguienteEstado) {
		this.siguienteEstado = siguienteEstado;
	}

	public char getEscribirSimbolo() {
		return escribirSimbolo;
	}

	public void setEscribirSimbolo(char escribirSimbolo) {
		this.escribirSimbolo = escribirSimbolo;
	}

	public Direccion getMoverDireccion() {
		return moverDireccion;
	}

	public void setMoverDireccion(Direccion moverDireccion) {
		this.moverDireccion = moverDireccion;
	}

	@Override
	public String toString() {
		return "Transicion [siguienteEstado=" + siguienteEstado + ", escribirSimbolo=" + escribirSimbolo
				+ ", moverDireccion=" + moverDireccion + "]";
	}
    
  
}
