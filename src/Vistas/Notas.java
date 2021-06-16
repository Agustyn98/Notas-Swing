package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.ManejadorDB;
import Modelos.Carpeta;
import Modelos.Nota;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Panel;
import javax.swing.BoxLayout;

public class Notas extends JFrame {

	private JFrame frmNotas;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notas frame = new Notas(new Carpeta(-1,"asi no :("));
					frame.setVisible(true);
					frame.getContentPane();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JList listNotas;
	Carpeta carpeta;
	
	private void initialize() {
		
	}
	
	
	public Notas(Carpeta carpeta) {
		setTitle("Notas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Notas.class.getResource("/Resources/post-it.png")));
		setResizable(false);
		this.carpeta = carpeta;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(550, 524));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		listNotas = new JList();
		listNotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listNotas.setFont(new Font("Tahoma", Font.BOLD, 24));
		listNotas.setBounds(36, 80, 467, 341);
		contentPane.add(listNotas);
		
		JPanel panel = new JPanel();
		panel.setBounds(36, 432, 467, 40);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 5, 10, 10));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(Notas.class.getResource("/Resources/left-arrow.png")));
		panel.add(btnVolver);
		btnVolver.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Carpetas();
				dispose();
			}
		});
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Notas.class.getResource("/Resources/plus.png")));
		btnAgregar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnEditar.setIcon(new ImageIcon(Notas.class.getResource("/Resources/pencil.png")));
		btnEditar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(Notas.class.getResource("/Resources/remove.png")));
		btnEliminar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(btnEliminar);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		panel.add(btnSeleccionar);
		btnSeleccionar.setIcon(new ImageIcon(Notas.class.getResource("/Resources/right-arrow.png")));
		btnSeleccionar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listNotas.getSelectedValue() == null)
					return;
				Nota n = (Nota) listNotas.getSelectedValue();
				new VerNota(n, carpeta,false);
				dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 29, 467, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNombreCarpeta = new JLabel(carpeta.nombre);
		panel_1.add(lblNombreCarpeta);
		lblNombreCarpeta.setFont(new Font("Open Sans", Font.BOLD, 20));
		
		JComboBox comboBoxOrdenar = new JComboBox();
		comboBoxOrdenar.setFont(new Font("Open Sans", Font.PLAIN, 11));
		panel_1.add(comboBoxOrdenar);
		comboBoxOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Mas viejos", "Mas nuevas", "Ultima modificacion"}));
		comboBoxOrdenar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				var index = comboBoxOrdenar.getSelectedIndex();
				orden = index +1 ;
				System.out.println(orden);
				actualizarLista();
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listNotas.getSelectedValue() == null)
					return;
				var resultado = JOptionPane.showConfirmDialog(null, "Eliminar s?");
				if(resultado != 0)
					return;
				Nota c = (Nota) listNotas.getSelectedValue();
				var db = ManejadorDB.getInstance();
				db.eliminarNota(c.id);
				actualizarLista();
			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var db = ManejadorDB.getInstance();
				var nuevaNota = new Nota("",LocalDateTime.now().toString(),carpeta.id);
				db.agregarNota(nuevaNota);
				new VerNota(nuevaNota, carpeta, true);
				dispose();

			}
		});
		
		actualizarLista();
	}
	
	int orden = 3;
	public void actualizarLista() {
		var db = ManejadorDB.getInstance();

		var notas = db.seleccionarNotas(carpeta.id, orden);
		listNotas.setListData(notas.toArray());
	}
}
