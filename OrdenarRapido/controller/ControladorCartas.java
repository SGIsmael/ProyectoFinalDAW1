package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Baraja;
import model.Carta;
import model.Puntuacion;
import view.TablaDePuntuaciones;

public class ControladorCartas implements ActionListener {
	//En principio usamos un array, por el tipo de juego, puede aumentarse de dificultad
	private ArrayList<JButton> cartas = new ArrayList<JButton>();
	private int[] posiciones = new int[6];
	private Baraja baraja;
	private String nombre;
	private int aciertos = 0;
	private int fallos = 0;
	private int cuantaCarta = 0;
	private int contadorClicks;
	private int tiempoInicial;
	private int tiempoFinal;
	private Puntuacion puntuacion;
	private JFrame pantallaJuego;
	private TablaDePuntuaciones tablaDePuntuaciones;

	//Por cada click colocamos la carta en una posicion y la bloqueamos, a la espera de la ultima. cuando esté se acabará el juego y se dará paso a la puntuación.
	public void actionPerformed(ActionEvent e) {
		contadorClicks++;
		String comparacionS;
		int comparacion;
		for(int i = 0; i<cuantaCarta;i++) {
			if(cartas.get(i).getText().equals(e.getActionCommand())) {
				if(contadorClicks == 1) {
					cartas.get(i).setBounds(32, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[0]) {
						aciertos++;
					}else {
						fallos++;
					}
				}else if(contadorClicks == 2) {
					cartas.get(i).setBounds(182, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[1]) {
						aciertos++;
					}else {
						fallos++;
					}
				}else if(contadorClicks == 3) {
					cartas.get(i).setBounds(320, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[2]) {
						aciertos++;
					}else {
						fallos++;
					}
				}else if(contadorClicks == 4) {
					cartas.get(i).setBounds(463, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[3]) {
						aciertos++;
					}else {
						fallos++;
					}
				}else if(contadorClicks == 5) {
					cartas.get(i).setBounds(604, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[4]) {
						aciertos++;
					}else {
						fallos++;
					}
				}else if(contadorClicks == 6) {
					cartas.get(i).setBounds(758, 32, 89, 124);
					cartas.get(i).setEnabled(false);
					comparacionS = cartas.get(i).getText();
					comparacion = Integer.parseInt(comparacionS);
					if(comparacion == posiciones[5]) {
						aciertos++;
					}else {
						fallos++;
					}
					tiempoFinal = (int)System.currentTimeMillis();
					calcularPuntuacion();
					pantallaJuego.setVisible(false);
					tablaDePuntuaciones.setVisible(true);
					tablaDePuntuaciones.darPuntos(puntuacion);
					tablaDePuntuaciones.rellenarTabla();
				}
			}
		}
	}
	//Añade una nueva carta visual al controlador
	public void addCard(JButton carta) {
		cartas.add(carta);
		cuantaCarta++;
	}
	//Da un valor a cada carta asociada al controlador y guarda las cartas, dado que habrá que usarlas para comparar si hay aciertos y fallos
	private void repartir() {
		for(int i= 0;i<cuantaCarta;i++) {
			Carta c = baraja.darCarta();
			posiciones[i] = c.getNumero();
			cartas.get(i).setText(""+posiciones[i]);
		}
		int i, j, aux;
        for (i = 0; i < posiciones.length - 1; i++) {
            for (j = 0; j < posiciones.length - i - 1; j++) {                                                              
                if (posiciones[j + 1] < posiciones[j]) {
                    aux = posiciones[j + 1];
                    posiciones[j + 1] = posiciones[j];
                    posiciones[j] = aux;
                }
            }
        }
	}
	//Da comienzo el juego, y toma el tiempo inicial, para la puntuacion
	public void jugar() {
		repartir();
		tiempoInicial = (int) System.currentTimeMillis();
	}
	//Hace los calculos en base al tiempo y aciertos.
	private void calcularPuntuacion() {
		int puntos = 0;
		int tiempo;
		tiempo =(tiempoFinal-tiempoInicial)/1000;
		if(tiempo<3) {
			puntos = 1000;
		}else if(tiempo <4) {
			puntos = 800;
		}else if(tiempo <5) {
			puntos = 500;
		}else if(tiempo <6) {
			puntos = 400;
		}else if(tiempo <9) {
			puntos = 300;
		}else if(tiempo <15) {
			puntos = 200;
		}else {
			puntos = 100;
		}
		puntos = puntos +(aciertos*100)-(fallos*300);
		if(puntos <=0) {
			puntos = 0;
		}
		puntuacion = new Puntuacion(puntos, nombre, tiempo);
	}
	//Agregamos una baraja al controlador.
	public void darBaraja(Baraja baraja) {
		this.baraja=baraja;
	}

	//Agregamos el JFrame main.
		public void darPantallaJuego(JFrame pantallaJuego) {
			this.pantallaJuego = pantallaJuego;
		}
	//Agregamos el JFrame que será la tabla de puntuaciones.
	public void darTabla(TablaDePuntuaciones tablaDePuntuaciones) {
		this.tablaDePuntuaciones = tablaDePuntuaciones;
	}
	//Agregamos el nombre del jugador
	public void darNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
