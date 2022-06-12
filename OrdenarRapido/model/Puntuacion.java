package model;

public class Puntuacion {
	private int puntos;
	private String nombreJ;
	private int tiempo;
	
	public Puntuacion(int puntos, String nombreJ, int tiempo) {
		this.tiempo=tiempo;
		this.nombreJ=nombreJ;
		this.puntos=puntos;
	}
	
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public String getNombreJ() {
		return nombreJ;
	}
	public void setNombreJ(String nombreJ) {
		this.nombreJ = nombreJ;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
}
