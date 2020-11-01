package Logica;


public class Chequeador {
	
	// Atributos	

	private boolean [] tira;
	
	// Constructor
	
	public Chequeador(int [] arr){		
		
		tira = new boolean[9];
		
		if(arr.length == tira.length) { // Inicializo arreglos
			for(int i = 0; i < tira.length; i++) {
				tira[i] = false;
				
			}
		
			for(int i = 0; i < tira.length; i++) { // El booleano en la posicion i-1 en la tira indicara que el numero i se encuentra en arr. Ej : tira[2] = true indica que el numero 1 esta en arr				
				if(arr[i] != 0) {			
					if(tira[arr[i]-1] != true)
						tira[arr[i]-1] = true;		
					
				}			
			}
		
		} 
	}
	
	// Comandos y Consultas
	


	public boolean[] getTira() {
		return tira;
	}

	public boolean chequeaTira() {
		boolean valido;		
		valido = true;
		
		for(int i = 0; i < tira.length && valido ;i++) { // Una tira es valida si todos los numeros estan marcados con verdadero
			
			valido = tira[i] == true;			
		}	
		
		return valido;
	}		
	
		
}
