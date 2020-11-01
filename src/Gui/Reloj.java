package Gui;


import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Reloj  {
	
	private Timer t;
    private int h, m, s,cs;
    private Map<Integer,ImageIcon> graficos;
    private JPanel panel;
    private String [] direcciones;
    private JLabel [] etiquetas;
  
    public Reloj() {
   
    	 panel = new JPanel();
    	 graficos = new HashMap<Integer,ImageIcon>();
    	 etiquetas = new JLabel [6];
    	 
         t = new Timer(10, acciones);
         
         direcciones = new String[] {"/Gui/IconosReloj/cero.png","/Gui/IconosReloj/uno.png","/Gui/IconosReloj/dos.png","/Gui/IconosReloj/tres.png","/Gui/IconosReloj/cuatro.png","/Gui/IconosReloj/cinco.png","/Gui/IconosReloj/seis.png","/Gui/IconosReloj/siete.png","/Gui/IconosReloj/ocho.png","/Gui/IconosReloj/nueve.png"};
    	
         panel.add(new JLabel(" Tiempo transcurrido "));
         
         for(int i = 0; i < 6; i++) {  // Limitacion, este reloj funciona para mostrar hasta 24 horas transcurridas
        	
        	 JLabel e = new JLabel();
        	 etiquetas[i] = e;
        	 panel.add(e);
         } 
         t.start();
         
         for(int i = 0; i < 10;i++) {
        	 
        	 ImageIcon image = new ImageIcon(this.getClass().getResource(direcciones[i]));	
        	 graficos.put(i,image);
        	 
         }
         
    	
    }
    
	
		
    private ActionListener acciones = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent ae) {
           
            
        	++cs; 
            if(cs==100){
                cs = 0;
                ++s;
            }
            if(s==60) 
            {
                s = 0;
                ++m;
            }
            if(m==60)
            {
                m = 0;
                ++h;
            }
           
            actualizarReloj();
            
        }
        
    };
		
	
    private void actualizarReloj() {
    	
    	
    	etiquetas[1].setIcon(graficos.get(h%10));
    	etiquetas[0].setIcon(graficos.get((h/10)%10));
    	
    	etiquetas[3].setIcon(graficos.get(m%10));
    	etiquetas[2].setIcon(graficos.get((m/10)%10));
    	
    	etiquetas[5].setIcon(graficos.get(s%10));
    	etiquetas[4].setIcon(graficos.get((s/10)%10));
    	
    	
    }


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
    
    
		
		
		
		
	
	
}
