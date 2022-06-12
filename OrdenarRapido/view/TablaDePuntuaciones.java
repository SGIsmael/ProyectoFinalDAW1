package view;

import java.awt.Color;
import java.awt.EventQueue;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaDePuntuaciones frame = new TablaDePuntuaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void darPuntos(Puntuacion puntosPartidaActual) {
		this.puntosPartidaActual=puntosPartidaActual;
	}
	public void rellenarTabla() {
		//Se muestran los puntos de la partida actual
		nombreActual.setText(puntosPartidaActual.getNombreJ());
		puntuacionActual.setText(""+puntosPartidaActual.getPuntos());
		tiempoActual.setText(""+puntosPartidaActual.getTiempo());
		boolean esMasAlto = true;
		int longitudArray = listaPuntos.size();
		Puntuacion aux = new Puntuacion(0,"0",0);
		Puntuacion aux2;
		//Comprobamos si son mejores que los anteriormente nombrados
		for(int i = 0; i<longitudArray-1;i++) {
			if(listaPuntos.get(i).getPuntos()<puntosPartidaActual.getPuntos()&& esMasAlto) {
				//Si es mejor, lo ponemos en la posicion que debe ocupar y desplazamos el resto hacia la derecha
				//Mandamos el resultado pero al final del array, y usaremos esa posicion como auxiliar para reordenar el resto.
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
				w.write(listaPuntos.get(i).getNombreJ()+"\r\n"+listaPuntos.get(i).getPuntos()+"\r\n"+listaPuntos.get(i).getTiempo()+"\r\n");
			}
			w.close();
		} catch (IOException e) {
			System.out.println("No existe este fichero.");
		}
		
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
					w.write("AAAAAA\r\n0000\r\n0000\r\n");
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
		try {
			r = new FileReader("./Puntuaciones.txt");
			BufferedReader bf = new BufferedReader(r);
			for(int i = 0; i<5;i++) {
				nombreJ = bf.readLine();
				palabras = bf.readLine();
				puntosJ = Integer.parseInt(palabras);
				palabras = bf.readLine();
				tiempoJ = Integer.parseInt(palabras);
				//Creamos un objeto Puntuacion por cada 3 datos del fichero
				listaPuntos.add(new Puntuacion(puntosJ, nombreJ, tiempoJ));
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
		txtNombreJugador.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtNombreJugador.setBounds(57, 2, 189, 50);
		contentPane.add(txtNombreJugador);
		
		JLabel txtPuntuacion = new JLabel("Puntuaci\u00F3n");
		txtPuntuacion.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtPuntuacion.setBounds(336, 2, 189, 50);
		contentPane.add(txtPuntuacion);
		
		JLabel txtTiempo = new JLabel("Tiempo");
		txtTiempo.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtTiempo.setBounds(560, 2, 189, 50);
		contentPane.add(txtTiempo);
		
		JLabel txtTop1N = new JLabel(listaPuntos.get(0).getNombreJ());
		txtTop1N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1N.setBounds(57, 112, 189, 44);
		contentPane.add(txtTop1N);
		
		JLabel txtTop2N = new JLabel(listaPuntos.get(1).getNombreJ());
		txtTop2N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2N.setBounds(57, 183, 189, 44);
		contentPane.add(txtTop2N);
		
		JLabel txtTop3N = new JLabel(listaPuntos.get(2).getNombreJ());
		txtTop3N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3N.setBounds(57, 262, 189, 44);
		contentPane.add(txtTop3N);
		
		JLabel txtTop4N = new JLabel(listaPuntos.get(3).getNombreJ());
		txtTop4N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4N.setBounds(57, 351, 189, 44);
		contentPane.add(txtTop4N);
		
		JLabel txtTop5N = new JLabel(listaPuntos.get(4).getNombreJ());
		txtTop5N.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5N.setBounds(57, 449, 189, 44);
		contentPane.add(txtTop5N);
		
		JLabel txtTop1P = new JLabel(""+listaPuntos.get(0).getPuntos());
		txtTop1P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1P.setBounds(336, 112, 189, 44);
		contentPane.add(txtTop1P);
		
		JLabel txtTop2P = new JLabel(""+listaPuntos.get(1).getPuntos());
		txtTop2P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2P.setBounds(336, 183, 189, 44);
		contentPane.add(txtTop2P);
		
		JLabel txtTop3P = new JLabel(""+listaPuntos.get(2).getPuntos());
		txtTop3P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3P.setBounds(336, 262, 189, 44);
		contentPane.add(txtTop3P);
		
		JLabel txtTop4P = new JLabel(""+listaPuntos.get(3).getPuntos());
		txtTop4P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4P.setBounds(336, 351, 189, 44);
		contentPane.add(txtTop4P);
		
		JLabel txtTop5P = new JLabel(""+listaPuntos.get(4).getPuntos());
		txtTop5P.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5P.setBounds(336, 449, 189, 44);
		contentPane.add(txtTop5P);
		
		JLabel txtTop1T = new JLabel(""+listaPuntos.get(0).getTiempo());
		txtTop1T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop1T.setBounds(560, 112, 189, 44);
		contentPane.add(txtTop1T);
		
		JLabel txtTop2T = new JLabel(""+listaPuntos.get(1).getTiempo());
		txtTop2T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop2T.setBounds(560, 183, 189, 44);
		contentPane.add(txtTop2T);
		
		JLabel txtTop3T = new JLabel(""+listaPuntos.get(2).getTiempo());
		txtTop3T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop3T.setBounds(560, 262, 189, 44);
		contentPane.add(txtTop3T);
		
		JLabel txtTop4T = new JLabel(""+listaPuntos.get(3).getTiempo());
		txtTop4T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop4T.setBounds(560, 351, 189, 44);
		contentPane.add(txtTop4T);
		
		JLabel txtTop5T = new JLabel(""+listaPuntos.get(4).getTiempo());
		txtTop5T.setFont(new Font("Arial Black", Font.BOLD, 14));
		txtTop5T.setBounds(560, 449, 189, 44);
		contentPane.add(txtTop5T);
		
		nombreActual = new JLabel("New label");
		nombreActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombreActual.setBounds(57, 72, 170, 29);
		contentPane.add(nombreActual);
		
		puntuacionActual = new JLabel("New label");
		puntuacionActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		puntuacionActual.setBounds(336, 72, 170, 29);
		contentPane.add(puntuacionActual);
		
		tiempoActual = new JLabel("New label");
		tiempoActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		tiempoActual.setBounds(560, 72, 170, 29);
		contentPane.add(tiempoActual);
	}
}
