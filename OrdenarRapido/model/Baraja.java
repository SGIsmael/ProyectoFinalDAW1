package model;

import java.util.ArrayList;

public class Baraja {
	private ArrayList <Carta> baraja = new ArrayList<Carta>();
	private int contCartas= 0;
	public Baraja() {
		for(int i = 0;i<20;i++) {
			baraja.add(new Carta(i+1)); 
			contCartas++;
		}
	}
	public Carta darCarta(){
		Carta c;
		int random = (int)(Math.random()*contCartas);
		c=baraja.get(random);
		contCartas--;
		baraja.remove(random);
		return c;
	}
}
