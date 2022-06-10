package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControladorCartas;
import model.Baraja;
import javax.swing.JLabel;
import java.awt.Font;

public class JuegoVisual extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorCartas controladorCartas = new ControladorCartas();
	private Baraja baraja = new Baraja();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuegoVisual frame = new JuegoVisual();
					frame.setVisible(true);
					frame.controladorCartas.jugar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JuegoVisual() {
		setBackground(Color.WHITE);
		setTitle("Juego de Cartas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 556);
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
		
		JLabel reglas = new JLabel("Ordena las cartas de menor a mayor en el menor tiempo posible.");
		reglas.setFont(new Font("Tahoma", Font.BOLD, 16));
		reglas.setBackground(Color.WHITE);
		reglas.setBounds(136, 157, 654, 53);
		contentPane.add(reglas);
		
		JLabel puntuacion = new JLabel("Puntuaci\u00F3n: ");
		puntuacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		puntuacion.setBounds(136, 221, 317, 32);
		contentPane.add(puntuacion);
		
		JLabel aciertos = new JLabel("Aciertos:");
		aciertos.setFont(new Font("Tahoma", Font.BOLD, 14));
		aciertos.setBounds(136, 264, 327, 32);
		contentPane.add(aciertos);
		
		JLabel fallos = new JLabel("Fallos:");
		fallos.setFont(new Font("Tahoma", Font.BOLD, 14));
		fallos.setBounds(136, 307, 317, 32);
		contentPane.add(fallos);
		carta6.addActionListener(controladorCartas);
		
		controladorCartas.darBaraja(baraja);
		controladorCartas.darTextos(puntuacion, aciertos, fallos);
	}
}
