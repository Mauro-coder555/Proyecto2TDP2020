package Logica;


public class Celda {
		
	// Atributos
		
	private int numero;
	private EntidadGrafica entidadGrafica;
	private int fil, col, linea,vertical;

		
	// Consctructor
		
	public Celda() {
		numero = 0;
		entidadGrafica = new EntidadGrafica();
		actualizar(0);

		
		
	}
		
	// Operaciones
	
	private void actualizar(int indice) {		
		if(indice < entidadGrafica.getImagenes().length) {			
			entidadGrafica.actualizarBoton(indice);
		}
		
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
		actualizar(numero);
	}

	
	public void vaciarCelda(){
		setNumero(0);
		
	}

	public int getFil() {
		return fil;
	}

	public void setFil(int fil) {
		this.fil = fil;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public void invalidarCelda() {
		entidadGrafica.invalidar();		
		
	}
	
	public void validarCelda() {
		entidadGrafica.validar();
		
	}

	public EntidadGrafica getEntidadGrafica() {
		return entidadGrafica;
	}

	public void setEntidadGrafica(EntidadGrafica entidadGrafica) {
		this.entidadGrafica = entidadGrafica;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}
	
	
	
	
	
	
		

}


