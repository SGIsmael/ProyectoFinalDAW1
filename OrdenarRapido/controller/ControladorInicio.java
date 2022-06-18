package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.JuegoVisual;

public class ControladorInicio implements ActionListener{
	private JFrame pantallaInicio;
	private JuegoVisual pantallaJuego;
	private JButton botonContinuar;
	private ArrayList<String> listaFrases = new ArrayList<String>();
	private JTextField txtCoger;
	private JLabel txtMostrar;
	private String nombre;
	private int pasaTextos = 0;
	
	public void actionPerformed(ActionEvent e) {
		if(pasaTextos <=5 ) {
			txtMostrar.setText(listaFrases.get(pasaTextos));
			pasaTextos++;
			if(txtCoger.isVisible()) {
				nombre = txtCoger.getText();
			}
			if(pasaTextos == 2) {
				txtCoger.setVisible(true);
			}else {
				txtCoger.setVisible(false);
			}
			if(pasaTextos == 6) {
				botonContinuar.setText("Empezar el juego");
			}else {
				botonContinuar.setText("Continuar");
			}
		}else {
			pantallaInicio.setVisible(false);
			pantallaJuego.setVisible(true);
			pantallaJuego.usarControlador(nombre);
		}
	}
	public void darPantallas(JFrame pantallaInicio, JuegoVisual pantallaJuego) {
		this.pantallaInicio=pantallaInicio;
		this.pantallaJuego=pantallaJuego;
		rellenar();
	}
	private void rellenar() {
		listaFrases.add(0, "Saludos usuario!");
		listaFrases.add(1, "Introduce tu nombre: ");//Sacamos texto
		listaFrases.add(2, "Gracias! Usaremos tu nombre para añadirlo a la tabla de puntuaciones. ");//Ocultamos el texto
		listaFrases.add(3, "Te voy a explicar las reglas del juego: ");
		listaFrases.add(4, "Debes ordenar las cartas lo más rápido posible.");
		listaFrases.add(5, "En cuanto le des a continuar comenzará el juego. . . ¡YA PUEDES CORRER!"); // Cambiamos el botón de "Continuar a Empezar"
	}
	public void vincularBoton(JButton botonContinuar) {
		this.botonContinuar = botonContinuar;
		
	}	
	public void pasarEnunciado(JLabel txtMostrar) {
		this.txtMostrar = txtMostrar;
	}
	public void pasarCuadroNombre(JTextField txtCoger) {
		this.txtCoger = txtCoger;
	}
}
