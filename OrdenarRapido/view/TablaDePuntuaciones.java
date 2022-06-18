package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Puntuacion;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TablaDePuntuaciones extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Puntuacion> listaPuntos = new ArrayList<Puntuacion>();
	private Puntuacion puntosPartidaActual;
	private JLabel nombreActual;
	private JLabel puntuacionActual;
	private JLabel tiempoActual;
	private JLabel aciertosActual;
	
	public void darPuntos(Puntuacion puntosPartidaActual) {
		this.puntosPartidaActual=puntosPartidaActual;
	}
	public void rellenarTabla() {
		//Se muestran los puntos de la partida actual
		nombreActual.setText(puntosPartidaActual.getNombreJ());
		puntuacionActual.setText(""+puntosPartidaActual.getPuntos());
		tiempoActual.setText(""+puntosPartidaActual.getTiempo());
		aciertosActual.setText(""+puntosPartidaActual.getAciertos());
		boolean esMasAlto = true;
		int longitudArray = listaPuntos.size();
		Puntuacion aux = new Puntuacion(0,"0",0,0);
		Puntuacion aux2;
		//Comprobamos si son mejores que los anteriormente nombrados
		for(int i = 0; i<longitudArray;i++) {
			if(listaPuntos.get(i).getPuntos()<puntosPartidaActual.getPuntos()&& esMasAlto) {
				//Si es mejor, lo ponemos en la posicion que debe ocupar y desplazamos el resto hacia la derecha
				aux = listaPuntos.get(i);
				listaPuntos.set(i, puntosPartidaActual);
				esMasAlto = false;
			}else if(!esMasAlto){
				//hacemos lo siguiente : guardamos el valor a tratar.
				aux2 = listaPuntos.get(i);
				//Cogemos el valor que está en la referencia auxiliar y lo ponemos donde debe estar.
				listaPuntos.set(i, aux);
				//Cambiamos el valor de la referencia auxiliar para poder repetir el proceso.
				aux = aux2;
			}
		}
		//Escribimos los 5 mejores resultados en la tabla.
		try {
			FileWriter w = new FileWriter("./Puntuaciones.txt");
			for(int i = 0; i<5;i++){
				w.write(listaPuntos.get(i).getNombreJ()+"\r\n"+listaPuntos.get(i).getPuntos()+"\r\n"+listaPuntos.get(i).getTiempo()+"\r\n"+listaPuntos.get(i).getAciertos()+"\r\n");
			}
			w.close();
		} catch (IOException e) {
			System.out.println("No existe este fichero.");
		}
		// FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!!
	}
	public TablaDePuntuaciones() {
		File fichero = new File ("./Puntuaciones.txt");
		FileReader r;
		FileWriter w;
		//Si no existe el fichero, lo creamos.
		if(!fichero.exists()) {
			try {
				w = new FileWriter("./Puntuaciones.txt");
				for(int i = 0; i<5;i++){
					w.write("AAAAAA\r\n0000\r\n0000\r\n0000\r\n");
				}
				w.close();
			} catch (IOException e) {
				System.out.println("El fichero no existe");
			}
		}
		//Intenamos leer el fichero y transformar los datos tal y como nosotros queremos
		String nombreJ;
		String palabras;
		int puntosJ;
		int tiempoJ;
		int aciertosJ;
		try {
			r = new FileReader("./Puntuaciones.txt");
			BufferedReader bf = new BufferedReader(r);
			for(int i = 0; i<5;i++) {
				nombreJ = bf.readLine();
				palabras = bf.readLine();
				puntosJ = Integer.parseInt(palabras);
				palabras = bf.readLine();
				tiempoJ = Integer.parseInt(palabras);
				palabras = bf.readLine();
				aciertosJ = Integer.parseInt(palabras);
				//Creamos un objeto Puntuacion por cada 4 datos del fichero
				listaPuntos.add(new Puntuacion(puntosJ, nombreJ, tiempoJ, aciertosJ));
			}
			r.close();
		} catch (FileNotFoundException e) {
			System.out.println("No hay fichero");
		}catch (IOException e) {
			System.out.println("Algo salió mal");
		}

		//Aqui empieza el contenido de la interfaz gráfica, que rellenaremos con los datos del fichero.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15, 106, 48 ));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtNombreJugador = new JLabel("Nombre Jugador ");
		txtNombreJugador.setForeground(Color.WHITE);
		txtNombreJugador.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador.setBounds(57, 2, 189, 50);
		contentPane.add(txtNombreJugador);
		
		JLabel txtPuntuacion = new JLabel("Puntuaci\u00F3n");
		txtPuntuacion.setForeground(Color.WHITE);
		txtPuntuacion.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtPuntuacion.setBounds(288, 2, 189, 50);
		contentPane.add(txtPuntuacion);
		
		JLabel txtTiempo = new JLabel("Tiempo");
		txtTiempo.setForeground(Color.WHITE);
		txtTiempo.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtTiempo.setBounds(626, 2, 189, 50);
		contentPane.add(txtTiempo);
		
		JLabel txtTop1N = new JLabel(listaPuntos.get(0).getNombreJ());
		txtTop1N.setForeground(Color.WHITE);
		txtTop1N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1N.setBounds(95, 60, 189, 44);
		contentPane.add(txtTop1N);
		
		JLabel txtTop2N = new JLabel(listaPuntos.get(1).getNombreJ());
		txtTop2N.setForeground(Color.WHITE);
		txtTop2N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2N.setBounds(95, 120, 189, 44);
		contentPane.add(txtTop2N);
		
		JLabel txtTop3N = new JLabel(listaPuntos.get(2).getNombreJ());
		txtTop3N.setForeground(Color.WHITE);
		txtTop3N.setBackground(Color.WHITE);
		txtTop3N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3N.setBounds(95, 180, 189, 44);
		contentPane.add(txtTop3N);
		
		JLabel txtTop4N = new JLabel(listaPuntos.get(3).getNombreJ());
		txtTop4N.setForeground(Color.WHITE);
		txtTop4N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4N.setBounds(95, 240, 189, 44);
		contentPane.add(txtTop4N);
		
		JLabel txtTop5N = new JLabel(listaPuntos.get(4).getNombreJ());
		txtTop5N.setForeground(Color.WHITE);
		txtTop5N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5N.setBounds(95, 300, 189, 44);
		contentPane.add(txtTop5N);
		
		JLabel txtTop1P = new JLabel(""+listaPuntos.get(0).getPuntos());
		txtTop1P.setForeground(Color.WHITE);
		txtTop1P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1P.setBounds(315, 60, 189, 44);
		contentPane.add(txtTop1P);
		
		JLabel txtTop2P = new JLabel(""+listaPuntos.get(1).getPuntos());
		txtTop2P.setForeground(Color.WHITE);
		txtTop2P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2P.setBounds(315, 120, 189, 44);
		contentPane.add(txtTop2P);
		
		JLabel txtTop3P = new JLabel(""+listaPuntos.get(2).getPuntos());
		txtTop3P.setForeground(Color.WHITE);
		txtTop3P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3P.setBounds(315, 180, 189, 44);
		contentPane.add(txtTop3P);
		
		JLabel txtTop4P = new JLabel(""+listaPuntos.get(3).getPuntos());
		txtTop4P.setForeground(Color.WHITE);
		txtTop4P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4P.setBounds(315, 240, 189, 44);
		contentPane.add(txtTop4P);
		
		JLabel txtTop5P = new JLabel(""+listaPuntos.get(4).getPuntos());
		txtTop5P.setForeground(Color.WHITE);
		txtTop5P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5P.setBounds(315, 300, 189, 44);
		contentPane.add(txtTop5P);
		
		JLabel txtTop1T = new JLabel(""+listaPuntos.get(0).getTiempo());
		txtTop1T.setForeground(Color.WHITE);
		txtTop1T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1T.setBounds(660, 60, 189, 44);
		contentPane.add(txtTop1T);
		
		JLabel txtTop2T = new JLabel(""+listaPuntos.get(1).getTiempo());
		txtTop2T.setForeground(Color.WHITE);
		txtTop2T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2T.setBounds(660, 120, 189, 44);
		contentPane.add(txtTop2T);
		
		JLabel txtTop3T = new JLabel(""+listaPuntos.get(2).getTiempo());
		txtTop3T.setForeground(Color.WHITE);
		txtTop3T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3T.setBounds(660, 180, 189, 44);
		contentPane.add(txtTop3T);
		
		JLabel txtTop4T = new JLabel(""+listaPuntos.get(3).getTiempo());
		txtTop4T.setForeground(Color.WHITE);
		txtTop4T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4T.setBounds(660, 240, 189, 44);
		contentPane.add(txtTop4T);
		
		JLabel txtTop5T = new JLabel(""+listaPuntos.get(4).getTiempo());
		txtTop5T.setForeground(Color.WHITE);
		txtTop5T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5T.setBounds(660, 300, 189, 44);
		contentPane.add(txtTop5T);
		
		JLabel txtAciertos1 = new JLabel(""+listaPuntos.get(0).getAciertos());
		txtAciertos1.setForeground(Color.WHITE);
		txtAciertos1.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtAciertos1.setBounds(494, 63, 189, 44);
		contentPane.add(txtAciertos1);
		
		JLabel txtAciertos2 = new JLabel(""+listaPuntos.get(1).getAciertos());
		txtAciertos2.setForeground(Color.WHITE);
		txtAciertos2.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtAciertos2.setBounds(494, 120, 189, 44);
		contentPane.add(txtAciertos2);
		
		JLabel txtAciertos3 = new JLabel(""+listaPuntos.get(2).getAciertos());
		txtAciertos3.setForeground(Color.WHITE);
		txtAciertos3.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtAciertos3.setBounds(494, 180, 189, 44);
		contentPane.add(txtAciertos3);
		
		JLabel txtAciertos4 = new JLabel(""+listaPuntos.get(3).getAciertos());
		txtAciertos4.setForeground(Color.WHITE);
		txtAciertos4.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtAciertos4.setBounds(494, 240, 189, 44);
		contentPane.add(txtAciertos4);
		
		JLabel txtAciertos5 = new JLabel(""+listaPuntos.get(4).getAciertos());
		txtAciertos5.setForeground(Color.WHITE);
		txtAciertos5.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtAciertos5.setBounds(494, 300, 189, 44);
		contentPane.add(txtAciertos5);
		
		nombreActual = new JLabel("New label");
		nombreActual.setForeground(Color.WHITE);
		nombreActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombreActual.setBounds(76, 448, 170, 29);
		contentPane.add(nombreActual);
		
		puntuacionActual = new JLabel("New label");
		puntuacionActual.setForeground(Color.WHITE);
		puntuacionActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		puntuacionActual.setBounds(279, 448, 170, 29);
		contentPane.add(puntuacionActual);
		
		tiempoActual = new JLabel("New label");
		tiempoActual.setForeground(Color.WHITE);
		tiempoActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		tiempoActual.setBounds(645, 448, 170, 29);
		contentPane.add(tiempoActual);
		
		aciertosActual = new JLabel("New label");
		aciertosActual.setForeground(Color.WHITE);
		aciertosActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		aciertosActual.setBounds(465, 448, 170, 29);
		contentPane.add(aciertosActual);
		
		JLabel txtAciertos = new JLabel("Aciertos");
		txtAciertos.setForeground(Color.WHITE);
		txtAciertos.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtAciertos.setBounds(469, 2, 189, 50);
		contentPane.add(txtAciertos);
		
		JLabel lblPartidaActual = new JLabel("Partida actual");
		lblPartidaActual.setForeground(Color.WHITE);
		lblPartidaActual.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblPartidaActual.setBounds(288, 376, 189, 50);
		contentPane.add(lblPartidaActual);
		
		JLabel txtNombreJugador_1 = new JLabel("1\u00BA");
		txtNombreJugador_1.setForeground(Color.WHITE);
		txtNombreJugador_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador_1.setBounds(10, 56, 34, 50);
		contentPane.add(txtNombreJugador_1);
		
		JLabel txtNombreJugador_1_1 = new JLabel("2\u00BA");
		txtNombreJugador_1_1.setForeground(Color.WHITE);
		txtNombreJugador_1_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador_1_1.setBounds(10, 120, 34, 50);
		contentPane.add(txtNombreJugador_1_1);
		
		JLabel txtNombreJugador_1_2 = new JLabel("3\u00BA");
		txtNombreJugador_1_2.setForeground(Color.WHITE);
		txtNombreJugador_1_2.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador_1_2.setBounds(10, 180, 34, 50);
		contentPane.add(txtNombreJugador_1_2);
		
		JLabel txtNombreJugador_1_3 = new JLabel("4\u00BA");
		txtNombreJugador_1_3.setForeground(Color.WHITE);
		txtNombreJugador_1_3.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador_1_3.setBounds(10, 240, 34, 50);
		contentPane.add(txtNombreJugador_1_3);
		
		JLabel txtNombreJugador_1_4 = new JLabel("5\u00BA");
		txtNombreJugador_1_4.setForeground(Color.WHITE);
		txtNombreJugador_1_4.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador_1_4.setBounds(10, 300, 34, 50);
		contentPane.add(txtNombreJugador_1_4);
		
	}
}
