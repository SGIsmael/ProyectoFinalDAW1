package model;

public class Puntuacion {
	private int puntos;
	private String nombreJ;
	private int tiempo;
	private int aciertos;

	public Puntuacion(int puntos, String nombreJ, int tiempo, int aciertos) {
		this.tiempo=tiempo;
		this.nombreJ=nombreJ;
		this.puntos=puntos;
		this.aciertos=aciertos;
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
	public int getAciertos() {
		return aciertos;
	}
	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}
	
}
