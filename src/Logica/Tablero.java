package Logica;
import java.io.*;
import java.util.Random;

public class Tablero {
	
	// Atributos
	
   private Panel [][]paneles;
   private int largo;
   
	// Constructor
	
	public Tablero(){
		
		largo = 9;
		paneles = new Panel[3][3];
		
		for(int i = 0; i < 3; i++) 
			for(int j = 0; j < 3; j++)
				paneles[i][j] = new Panel();
			
	
		
		
	}
	
	// Comandos y consultas
	
	public void cargarTablero(BufferedReader bf){
	    String lineaDeArchivo; 
	    int linea ,i , j, cantFilaSubPanel, cantFilas , numCelda, posCeldai, posCeldaj;

	    
	 
	
	    

	    cantFilas = 9;
	    linea = 0;
	    cantFilaSubPanel = 3;
	    try {
	        lineaDeArchivo = bf.readLine();
	        // verificar que se tiene la cantidad de lineas que se necesitan
	        while (lineaDeArchivo!= null && linea < paneles.length*cantFilaSubPanel) {
	            //quitar todos los espacios
	        	
	            String sinEspacios = lineaDeArchivo.replaceAll(" ","");
	            
	            // verificar que se tiene la cantidad de numeros requeridos 
	           // por fila	    
	            
	            if(sinEspacios.length() == cantFilas){
	            	
	                //cargar los valores de una fila entera
	            	
	                   for (numCelda = 0; numCelda<sinEspacios.length(); numCelda++){                	   
	                	  
	                    // cantFilaSubpanel para este proyecto es 3
	                	   i = (int) (linea / cantFilaSubPanel);
	                	   j = (int) (numCelda / cantFilaSubPanel);
	                	   posCeldai = linea - i*cantFilaSubPanel;
	                	   posCeldaj = numCelda - j*cantFilaSubPanel;
	                	   
	                	  
	                	   paneles[i][j].setCelda(posCeldai, posCeldaj, Character.getNumericValue(sinEspacios.charAt(numCelda)),i,j,linea,numCelda);                	   
	                       
	                   }
	            }
	            
	            linea++;
	          
	            lineaDeArchivo = bf.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public void mostrarTablero() {
		Panel actual;
		
		for(int i = 0; i<3; i++)
			for(int j = 0; j <3 ;j++) { 
				
					actual = paneles[i][j];
					
					for(int k = 0; k < 3; k++)
						for (int l = 0;l < 3;l++)
							System.out.println("Panel: "+ i +" "+j+" Celda: "+k+" "+l +" : "+actual.getCelda(k,l).getNumero());				
				
				}
		     
			}
		
	
	public int[] fila(int numFila) { // Dado el numero de fila (0 a 8), devuelve un arreglo con los enteros de la fila
		int[] arr; int filaEnTablero,filaEnPanel, indiceArr; Panel aux;		
		
		
		
		filaEnTablero = posEnTab(numFila);
		filaEnPanel = numFila % 3;
		
		indiceArr = 0; 
		
		arr = new int[this.largo];	
		
		
		for(int i = 0; i < paneles.length; i++) {
			aux = paneles[filaEnTablero][i];
			for(int j = 0; j < paneles.length; j++){
				arr[indiceArr] = aux.getCelda(filaEnPanel, j).getNumero();
				indiceArr++;		
			}
			
		}
		
		return arr;
		
	}
	
	public int[] columna(int numCol) { // Dado el numero de columna (0 a 8), devuelve un arreglo con los enteros de la columna
	int[] arr; int colEnTablero,colEnPanel, indiceArr; Panel aux;	
		
		
		colEnTablero = posEnTab(numCol);
		colEnPanel = numCol % 3;
		
		indiceArr = 0; 
		
		arr = new int[this.largo];	
		
		
		for(int i = 0; i < paneles.length; i++) {
			aux = paneles[i][colEnTablero];
			for(int j = 0; j < paneles.length; j++){
				arr[indiceArr] = aux.getCelda(j,colEnPanel).getNumero();
				indiceArr++;		
			}
			
		}
		
		return arr;
		
	}
	
	public int[] cuadrante(int numCuadrante) {  // Dado el numero de cuadrante(0 a 8), devuelve un arreglo con los enteros del cuadrante
		int[] arr; int fil, col,indArr; Panel pan;
		
		arr = new int[this.largo];
		fil = -1; col = -1; indArr = 0;
		switch(numCuadrante) {
			
		case 0:
			fil = 0; col = 0;
			break;
			
		case 1:
			fil = 0; col = 1;
			break;
			
		case 2:
			fil = 0; col = 2;
			break;
			
		case 3:
			fil = 1; col = 0;
			break;
			
		case 4:
			fil = 1; col = 1;
			break;
			
		case 5:
			fil = 1; col = 2;
			break;
			
		case 6:
			fil = 2; col = 0;
			break;
			
		case 7:
			fil = 2; col = 1;
			break;
			
		case 8:
			fil = 2; col = 2;
			break;
		
		}
		
		pan = paneles[fil][col];
		
		for(int i = 0; i < this.paneles.length; i++) 
			for(int j = 0; j < this.paneles.length; j++) {
				arr[indArr] = pan.getCelda(i, j).getNumero();
				//System.out.println(" Num en cuadrante "+ arr[indArr]);
				
				indArr++;
			}
		
		
		return arr;
	}
	
	public void prepararTablero() {
		Random rnd;int fil, col,x,z; Celda celda;
		
		rnd = new Random(System.currentTimeMillis());
		
		
		for(int i = 0; i < 73; i++) {  // Como mucho se eliminan 74 elementos del tablero para llegar al estado inicial. Ya que en ese caso quedarian 17 elementos del archivo original
									   // Y se demostro que si hay 17 elementos fijos en un tablero de sudoku, la solucion es unica. De este modo se termina llegando a la solucion del archivo
			fil = Math.abs(rnd.nextInt()%3);
			col = Math.abs(rnd.nextInt()%3);
			x = Math.abs(rnd.nextInt()%3);
			z = Math.abs(rnd.nextInt()%3);
	
			
			celda = paneles[fil][col].getCelda(x, z);
			celda.vaciarCelda();	
			
		}
		
		for(int i = 0; i < paneles.length;i++)
			for(int j = 0; j < paneles.length;j++) 
				for(int k = 0; k < paneles.length;k++)
					for(int l = 0; l < paneles.length;l++){
						if(paneles[i][j].getCelda(k,l).getNumero() != 0)
							paneles[i][j].getCelda(k,l).getEntidadGrafica().bloquear();
				
			}
		
	}
	
	public Celda[] columna2(int numCol) { // Dado el numero de columna(0 a 8), devuelve un arreglo con las celdas de la columna
		Celda[] arr; int colEnTablero,colEnPanel, indiceArr; Panel aux;	
		
		
		colEnTablero = posEnTab(numCol);
		colEnPanel = numCol % 3;
		
		indiceArr = 0; 
		
		arr = new Celda[this.largo];	
		
		
		for(int i = 0; i < paneles.length; i++) {
			aux = paneles[i][colEnTablero];
			for(int j = 0; j < paneles.length; j++){
				arr[indiceArr] = aux.getCelda(j,colEnPanel);
				indiceArr++;		
			}
			
		}
		
		return arr;
		
	}
	

	public Celda[] fila2(int numFila) { //Dado el numero de fila(0 a 8), devuelve un arreglo con las celdas de la columna
		Celda[] arr; int filaEnTablero,filaEnPanel, indiceArr; Panel aux;
		
		
		
		
		filaEnTablero = posEnTab(numFila);
		filaEnPanel = numFila % 3;
		
		indiceArr = 0; 
		
		arr = new Celda[this.largo];	
		
		
		for(int i = 0; i < paneles.length; i++) {
			aux = paneles[filaEnTablero][i];
			for(int j = 0; j < paneles.length; j++){
				arr[indiceArr] = aux.getCelda(filaEnPanel, j);
				indiceArr++;		
			}
			
		}
		
		return arr;
		
	}
	public Celda[] cuadrante2(int fil,int col) { //Dada la posicion en el tablero (3x3), devuelve un arreglo con las celdas del cuadrante
		Celda[] arr;  Panel pan; int indArr;
		
		arr = new Celda[this.largo];
		indArr = 0;
		
		
		pan = paneles[fil][col];
		
		for(int i = 0; i < this.paneles.length; i++) 
			for(int j = 0; j < this.paneles.length; j++) {
				arr[indArr] = pan.getCelda(i, j);
				indArr++;		
				
			}
		
		
		return arr;
	}
	
	
	public int getLargo() {
		return largo;
	}

	public Panel[][] getPaneles() {
		return paneles;
	}

	public void setPaneles(Panel[][] paneles) {
		this.paneles = paneles;
	}
	
	
	public int posEnTab(int i) { // Mapea el numero de un tablero en vista humana (0 a 8) a su ubicacion en el tablero 3x3
		int pos;
		pos = -1;
		switch(i) {
		
		case 0:
			pos = 0; 
			break;
			
		case 1:
			pos = 0; 
			break;
			
		case 2:
			pos = 0; 
			break;
			
		case 3:
			pos = 1; 
			break;
			
		case 4:
			pos = 1; 
			break;
			
		case 5:
			pos = 1; 
			break;
			
		case 6:
			pos = 2; 
			break;
			
		case 7:
			pos = 2; 
			break;
			
		case 8:
			pos = 2; 
			break;
		
		}
		
		return pos;
	}
	
	public Celda[] cuadrante4(int numCuadrante) {
		Celda[] arr; int fil, col,indArr; Panel pan;
		
		arr = new Celda[this.largo];
		fil = -1; col = -1; indArr = 0;
		switch(numCuadrante) {
			
		case 0:
			fil = 0; col = 0;
			break;
			
		case 1:
			fil = 0; col = 1;
			break;
			
		case 2:
			fil = 0; col = 2;
			break;
			
		case 3:
			fil = 1; col = 0;
			break;
			
		case 4:
			fil = 1; col = 1;
			break;
			
		case 5:
			fil = 1; col = 2;
			break;
			
		case 6:
			fil = 2; col = 0;
			break;
			
		case 7:
			fil = 2; col = 1;
			break;
			
		case 8:
			fil = 2; col = 2;
			break;
		
		}
		
		pan = paneles[fil][col];
		
		for(int i = 0; i < this.paneles.length; i++) 
			for(int j = 0; j < this.paneles.length; j++) {
				arr[indArr] = pan.getCelda(i, j);				
				indArr++;
			}
		
		
		return arr;
	}

	
	
}
