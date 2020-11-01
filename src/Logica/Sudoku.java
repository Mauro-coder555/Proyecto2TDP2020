package Logica;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import Gui.MiVentana;


public class Sudoku {

	// Atributos
	
	private Tablero tablero;	
	
	// Constructor
	
	public Sudoku(BufferedReader bf){	
			
			tablero = new Tablero();
			
			tablero.cargarTablero(bf);			
			
	}
	
	// Comandos y Consultas
	
	public Tablero getTablero() {
		return tablero;
		
	}
	
	public boolean jugar(Celda celda, int num) {  // Retorna true si luego de jugar se llego a la solucion en el tablero
		
		Celda[] fil,col,cua; boolean victoria;
	
		celda.setNumero(num);		
		
		for(int i = 0; i < this.getTablero().getLargo(); i ++) { // Limpio las marcas de invalido del tablero
			validarTira(tablero.fila2(i)); 
		}
		
		
		for(int i = 0; i < this.getTablero().getLargo(); i ++) {  // Compruebo cada fila ,columna y cuadrante del tablero
			
			
			fil = tablero.fila2(i); 
			col = tablero.columna2(i);
			cua = tablero.cuadrante4(i);
			
			this.chequearLineaSolucion(fil);
			this.chequearLineaSolucion(col);
			this.chequearLineaSolucion(cua);
		
		
		
		}
		
		victoria = this.esSolucion();	
		
		return victoria;
	}
	
	public boolean esSolucion() {  // Utiizo la clase Chequeador, que me dice si una tira de celdas es valida segun las reglas del sudoku
		boolean esSol; int [] fila, columna; Chequeador checkFila,checkCol, checkCuadrante;
		
		esSol = true;
		
		fila = new int [tablero.getLargo()];
		columna = new int [tablero.getLargo()];
		
		
		
		for(int i = 0; i < tablero.getLargo() && esSol; i++) {
			
			fila = tablero.fila(i);  
			columna = tablero.columna(i);			
			
			checkFila = new Chequeador(fila);
			checkCol = new Chequeador(columna);
			checkCuadrante = new Chequeador(tablero.cuadrante(i));		
			
			esSol = checkFila.chequeaTira() && checkCol.chequeaTira() && checkCuadrante.chequeaTira();
			
			
		}
		
		
		return esSol;
	}
	
	public Celda[] celdas() { // Devuelve un arreglo con todas las celdas del sudoku
		Celda [] celdas,fila; int indiceCeldas; 
		
		celdas = new Celda[tablero.getLargo()*tablero.getLargo()]; indiceCeldas = 0;
	
		
		for(int i = 0; i < tablero.getLargo(); i++) {
			fila = tablero.fila2(i);			
			for(int j = 0; j < fila.length;j++) {
			
				celdas[indiceCeldas] = fila[j];
				indiceCeldas++;
				
			}
			
		}
		
		
		
		return celdas;
	}
	
	public void chequearLineaSolucion(Celda [] celdas) { 
		
			Map<Integer,Integer> mapeo2; 
			int apariciones; 
			
			
		
		
			mapeo2 = new HashMap<Integer,Integer>(); // Numero con su cantidad de apariciones
		
			
			
			for(int i = 0; i < celdas.length; i++) { // Inicializo mapeo		
				mapeo2.put(celdas[i].getNumero(),0);		
				
			}
			
		
			
			for(int i = 0; i < celdas.length; i++) {  // Coloco las apariciones que tuvo cada numero en el mapeo
				if(celdas[i].getNumero() != 0)
					mapeo2.put(celdas[i].getNumero(), mapeo2.get(celdas[i].getNumero())+1);				
			}
			
			

			for(int i = 0; i < celdas.length; i++) { // Marco las celdas en las que el numero aparecio mas de una vez
				apariciones = mapeo2.get(celdas[i].getNumero());
				
				if((apariciones > 1)) {
					celdas[i].invalidarCelda();				
					
				}
				
				
				
			}
			
	}	
	
	
	private void validarTira(Celda [] celdas) {  
		
		for(int i = 0; i < celdas.length; i++) 
			celdas [i].validarCelda();		
	
	}
}
