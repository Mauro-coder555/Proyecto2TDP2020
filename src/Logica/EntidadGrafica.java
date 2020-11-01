package Logica;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EntidadGrafica {
	
	private JButton boton;
	private String[] imagenes;
	private boolean bloqueado;
	private boolean valido;
	
	
	public EntidadGrafica() {
		
		bloqueado = false;
		valido = true;
		boton = new JButton();
		boton.setBackground(Color.WHITE);
		boton.setPreferredSize(new Dimension(50,50));
		
		
		imagenes = new String[] {"/NumerosIcon/vacio.png","/NumerosIcon/uno.png","/NumerosIcon/dos.png","/NumerosIcon/tres.png","/NumerosIcon/cuatro.png","/NumerosIcon/cinco.png","/NumerosIcon/seis.png","/NumerosIcon/siete.png","/NumerosIcon/ocho.png","/NumerosIcon/nueve.png"};
		
	}
	
	public void actualizarBoton(int i) {
		
		ImageIcon image = new ImageIcon(this.getClass().getResource(imagenes[i]));	
		boton.setIcon(image);
	}

	public JButton getBoton() {
		return boton;
	}


	public void setBoton(JButton boton) {
		this.boton = boton;
	}


	public String[] getImagenes() {
		return imagenes;
	}


	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	public boolean getValido() {
		return valido;
	}
	public void invalidar() {
		boton.setBackground(Color.RED);
		valido = false;
		
	}
	
	public void validar() {
		boton.setBackground(Color.WHITE);		
		valido = true;
	}
	
	public void bloquear() {
		boton.setEnabled(false);
	}
	
	
}
