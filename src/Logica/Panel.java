package Logica;

public class Panel {
	
	// Atributos
	
	private Celda[][] celdas;
	
	
	// Constructor
	
	public Panel(){
		celdas = new Celda [3][3];
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				celdas[i][j] = new Celda();
			
		
		
	}
	
	// Comandos y consultas
	
	public void setCelda(int i,int j ,int numASetear,int posEnTablero_i,int posEnTablero_j,int linea,int vert){
	
		Celda celda = celdas[i][j];		
		celda.setNumero(numASetear);
		celda.setFil(posEnTablero_i);
		celda.setCol(posEnTablero_j);
		celda.setLinea(linea);
		celda.setVertical(vert);
		
	}
	
	public Celda getCelda(int i,int j) {		
		return celdas[i][j];		
		
	}
	
	

}
