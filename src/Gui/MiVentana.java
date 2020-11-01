package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Celda;
import Logica.Sudoku;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.Color;

public class MiVentana extends JFrame {

	private JPanel contentPane;
	private Map<JButton,Celda> miMapeo;
	private Sudoku miSudoku;
	private Reloj reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiVentana frame = new MiVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MiVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setSize(700,700);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));	
		
		
		JPanel subPanel;
		
		InputStream in = MiVentana.class.getClassLoader().getResourceAsStream("archivo.txt");
		InputStreamReader inr =new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		
		miSudoku = new Sudoku(bfr);
		miMapeo = new HashMap<JButton,Celda>();
		
		reloj = new Reloj();
		
		contentPane.add(reloj.getPanel());
		
		
		// Cargo los 9 paneles con los 9 botones
		
		
		
		Celda [] fila;
		
		if(miSudoku.esSolucion()) {		
		
			miSudoku.getTablero().prepararTablero();	
		
		
					for(int k = 0; k < miSudoku.getTablero().getLargo(); k++) {
						fila = miSudoku.getTablero().fila2(k);
						subPanel = new JPanel();
						for(int j = 0;  j < 9; j++) {
							JButton boton = fila[j].getEntidadGrafica().getBoton();
							OyenteBoton oyente = new OyenteBoton();
							boton.addActionListener(oyente);
							subPanel.add(boton);
					
						}
						contentPane.add(subPanel);
					}		
					
				
				
			}else {
			
				JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el juego :(");
				}
		
		
		
		Celda[] arr = miSudoku.celdas();
		
		for(int i = 0; i < arr.length; i++) {
			
			miMapeo.put(arr[i].getEntidadGrafica().getBoton(), arr[i]);
		
			
		}
		
		
		
		
	}
	
	private class OyenteBoton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			int numAJugar; boolean terminar; String respuesta;
			
			respuesta = JOptionPane.showInputDialog("Ingrese el numero a colocar en la celda");
			
			if(respuesta != null && esEntero(respuesta) && (Integer.valueOf(respuesta) >= 1) && (Integer.valueOf(respuesta) <=9)) {		
				numAJugar = Integer.valueOf(respuesta);		
				
                numAJugar = Integer.valueOf(respuesta);
				
				terminar = miSudoku.jugar(miMapeo.get(e.getSource()), numAJugar);
				
				if(terminar) JOptionPane.showMessageDialog(null, "Has ganado el juego");
			}	
			 	else {
			 		JOptionPane.showMessageDialog(null, "Has ingresado un valor incorrecto, vuelve a intentarlo");
			 	} 
			
			
				
			
		}
	 
	}	
	
	public boolean esEntero(String numero){ // Este metodo deberia ir en un paquete extra, por ejemplo,  "Utilidades", pues no hace a la ventana en si
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	
	
}
