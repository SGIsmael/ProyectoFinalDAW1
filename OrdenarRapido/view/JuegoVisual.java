package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControladorCartas;
import controller.ControladorInicio;
import model.Baraja;
import javax.swing.JLabel;
import java.awt.Font;


public class JuegoVisual extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorCartas controladorCartas = new ControladorCartas();
	private Baraja baraja = new Baraja();
	private String nJugador;
	private JLabel nombreJugador;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuegoVisual frame = new JuegoVisual();
					JFrame pantallaInicio = new JFrame("Pantalla inicio");
					pantallaInicio.setDefaultCloseOperation(EXIT_ON_CLOSE);
					pantallaInicio.setVisible(true);
					pantallaInicio.setBounds(10, 10, 1000, 800);
					JPanel c = new JPanel();
					pantallaInicio.setContentPane(c);
					c.setLayout(null);
					c.setBackground(new Color(15, 106, 48 ));
					c.setBorder(new EmptyBorder(5, 5, 5, 5));
					JLabel informacionInicio = new JLabel("¡¡Ordena Rápido!!");
					informacionInicio.setForeground(Color.WHITE);
					informacionInicio.setFont(new Font("Tahoma", Font.BOLD, 18));
					informacionInicio.setBounds(200, 250, 700, 100);
					JTextField textoInicio = new JTextField();
					textoInicio.setFont(new Font("Tahoma", Font.BOLD, 18));
					textoInicio.setBounds(300, 400, 240, 40);
					textoInicio.setVisible(false);
					JButton botonInicio = new JButton("Jugar");
					botonInicio.setFont(new Font("Tahoma", Font.BOLD, 18));
					botonInicio.setBounds(300, 500, 240, 40);
					c.add(informacionInicio);
					c.add(textoInicio);
					c.add(botonInicio);
					ControladorInicio controlI = new ControladorInicio();
					controlI.darPantallas(pantallaInicio, frame);
					controlI.vincularBoton(botonInicio);
					botonInicio.addActionListener(controlI);
					controlI.pasarCuadroNombre(textoInicio);
					controlI.pasarEnunciado(informacionInicio);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void usarControlador(String nombreJugador) {
		nJugador=nombreJugador;
		controladorCartas.jugar();
		controladorCartas.darNombre(nJugador);
	}
	public JuegoVisual() {
		setBackground(Color.WHITE);
		setTitle("Juego de Cartas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1000, 800);
		toFront();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15, 106, 48 ));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton carta1 = new JButton();
		carta1.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta1.setBounds(32, 382, 89, 124);
		contentPane.add(carta1);
		carta1.addActionListener(controladorCartas);
		controladorCartas.addCard(carta1);
		
		JButton carta2 = new JButton();
		carta2.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta2.setBounds(182, 382, 89, 124);
		contentPane.add(carta2);
		carta2.addActionListener(controladorCartas);
		controladorCartas.addCard(carta2);
		
		JButton carta3 = new JButton("");
		carta3.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta3.setBounds(320, 382, 89, 124);
		contentPane.add(carta3);
		controladorCartas.addCard(carta3);
		carta3.addActionListener(controladorCartas);
		
		JButton carta4 = new JButton("");
		carta4.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta4.setBounds(463, 382, 89, 124);
		contentPane.add(carta4);
		controladorCartas.addCard(carta4);
		carta4.addActionListener(controladorCartas);
		
		JButton carta5 = new JButton("");
		carta5.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta5.setBounds(604, 382, 89, 124);
		contentPane.add(carta5);
		controladorCartas.addCard(carta5);
		carta5.addActionListener(controladorCartas);
		
		JButton carta6 = new JButton("");
		carta6.setFont(new Font("Tahoma", Font.BOLD, 18));
		carta6.setBounds(758, 382, 89, 124);
		contentPane.add(carta6);
		controladorCartas.addCard(carta6);
		carta6.addActionListener(controladorCartas);

		
		nombreJugador = new JLabel();
		nombreJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombreJugador.setBounds(273, 221, 203, 47);
		contentPane.add(nombreJugador);
		
		controladorCartas.darBaraja(baraja);
		TablaDePuntuaciones tablaPuntuaciones = new TablaDePuntuaciones();
		controladorCartas.darTabla(tablaPuntuaciones);
	}
}
