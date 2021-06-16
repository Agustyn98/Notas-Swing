package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
import javax.swing.border.Border;
=======
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Database.ManejadorDB;
import Modelos.Carpeta;
import Modelos.Nota;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Component;
<<<<<<< HEAD

import javax.swing.BorderFactory;
=======
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
<<<<<<< HEAD
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
=======
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791

public class VerNota extends JFrame {

	private JPanel contentPane;

	/**
<<<<<<< HEAD
=======
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//VerNota frame = new VerNota();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
	 * Create the frame.
	 */

	public VerNota(Nota nota, Carpeta carpeta, boolean notaNueva) {
		setTitle("Ver notas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerNota.class.getResource("/Resources/pencil (2).png")));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(550, 524));
		contentPane = new JPanel();
<<<<<<< HEAD
		contentPane.setBackground(Color.WHITE);
=======
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea();
<<<<<<< HEAD
		textArea.setMargin(new Insets(8, 8, 8, 8));


		textArea.setFont(new Font("Dialog", Font.PLAIN, 20));
=======

		textArea.setFont(new Font("Open Sans Light", Font.PLAIN, 18));
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
		textArea.append(nota.texto);
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setBounds(36, 58, 467, 370);
		contentPane.add(textArea);
		
		JPanel panel = new JPanel();
<<<<<<< HEAD
		panel.setBackground(Color.WHITE);
=======
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
		panel.setBounds(36, 439, 466, 40);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 120, 0));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(VerNota.class.getResource("/Resources/left-arrow.png")));
		btnVolver.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Notas(carpeta);
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(VerNota.class.getResource("/Resources/diskette.png")));
		btnGuardar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(btnGuardar);
		
		JPanel panel_1 = new JPanel();
<<<<<<< HEAD
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(36, 11, 295, 36);
=======
		panel_1.setBounds(36, 11, 467, 36);
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Ultima modificacion:");
		panel_1.add(lblNewLabel);
<<<<<<< HEAD
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		String fecha = nota.fechaModificacion.substring(0, 10) + " - " + nota.fechaModificacion.substring(11,16);
		JLabel lblUltimaModificacion = new JLabel(fecha);
		lblUltimaModificacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblUltimaModificacion);
		
		JSlider slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var x = slider.getValue();
				//System.out.print("Cambio " + x);
				textArea.setFont(new Font("Dialog", Font.PLAIN, x));

			}
		});
		slider.setMinorTickSpacing(4);
		slider.setValue(20);
		slider.setMajorTickSpacing(4);
		slider.setMaximum(30);
		slider.setMinimum(10);
		slider.setBounds(341, 11, 162, 36);
		contentPane.add(slider);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(36, 58, 466, 370);
		contentPane.add(scrollPane);
=======
		lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		JLabel lblUltimaModificacion = new JLabel(nota.fechaModificacion);
		panel_1.add(lblUltimaModificacion);
>>>>>>> fe73276d422fd7a469b3b02f969620726b864791
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var db = ManejadorDB.getInstance();
				var notaActualizada = new Nota(nota.id,textArea.getText(),LocalDateTime.now().toString(), carpeta.id);
				if(notaNueva)
					db.actualizarUltimaNota(notaActualizada);
				else
					db.actualizarNota(notaActualizada);
				
			}
		});
		
	}
}
