package Vistas;

import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;


import Modelos.Carpeta;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Database.ManejadorDB;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;


public class Carpetas {

	private JFrame frmCarpetas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carpetas window = new Carpetas();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Carpetas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JList listCarpetas;
	private void initialize() {
		frmCarpetas = new JFrame();
		frmCarpetas.setIconImage(Toolkit.getDefaultToolkit().getImage(Carpetas.class.getResource("/Resources/FOLDER.png")));
		frmCarpetas.setFont(new Font("Open Sans", Font.PLAIN, 12));
		frmCarpetas.setTitle("Carpetas\r\n");
		frmCarpetas.getContentPane().setFont(new Font("Open Sans", Font.PLAIN, 11));
		frmCarpetas.setResizable(false);
		frmCarpetas.setVisible(true);
		frmCarpetas.setSize(new Dimension(550, 524));
		frmCarpetas.getContentPane().setBackground(Color.WHITE);
		frmCarpetas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarpetas.getContentPane().setLayout(null);
		frmCarpetas.setLocationRelativeTo(null);
		
		JScrollPane scrollPaneCarpetas = new JScrollPane();
		scrollPaneCarpetas.setBounds(36, 55, 467, 370);
		frmCarpetas.getContentPane().add(scrollPaneCarpetas);
		
		listCarpetas = new JList();
		scrollPaneCarpetas.setViewportView(listCarpetas);
		
				listCarpetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listCarpetas.setFont(new Font("Open Sans", Font.BOLD, 22));
				listCarpetas.setFixedCellHeight(50);
                //listCarpetas.setBorder(new EmptyBorder(0,3, 1, 3));

				DefaultListCellRenderer renderer =  (DefaultListCellRenderer)listCarpetas.getCellRenderer();  
				renderer.setHorizontalAlignment(JLabel.CENTER); 
				renderer.setBackground(Color.gray);
				listCarpetas.setCellRenderer(new DefaultListCellRenderer() {
					public Component getListCellRendererComponent(JList list, Object value, int index,
                            boolean isSelected, boolean cellHasFocus) {
                        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        
                        setBorder(BorderFactory.createMatteBorder(
                                0, 0, 1, 0, Color.gray));
								return c;
						
					}
				});

				ImageIcon imageIcon = new ImageIcon("..\\Resources\\FOLDER.png");		
				imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
				
				JPanel bottomPanel = new JPanel();
				bottomPanel.setBackground(Color.WHITE);
				bottomPanel.setBounds(36, 436, 467, 40);
				frmCarpetas.getContentPane().add(bottomPanel);
				bottomPanel.setLayout(new GridLayout(0, 4, 10, 10));
				
				JButton btnAgregar = new JButton("Agregar");
				bottomPanel.add(btnAgregar);
				btnAgregar.setFont(new Font("Open Sans", Font.PLAIN, 12));
				btnAgregar.setIcon(new ImageIcon(Carpetas.class.getResource("/Resources/plus.png")));
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog(null, "Carpeta");
						if( name == null || name.isBlank() )
							return;
						var db = ManejadorDB.getInstance();
						db.agregarCarpeta(new Carpeta(name));
						actualizarListaCarpetas(orden);
						

					}
				});
				
				JButton btnEditar = new JButton("Editar");
				btnEditar.setIcon(new ImageIcon(Carpetas.class.getResource("/Resources/pencil.png")));
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(listCarpetas.getSelectedValue() == null)
							return;
						Carpeta c = (Carpeta) listCarpetas.getSelectedValue();
						var resultado = JOptionPane.showInputDialog(null, "Elige un nuevo nombre");
						if(resultado == null || resultado.isBlank())
							return;
						var db = ManejadorDB.getInstance();
						db.editarCarpeta(c.id, resultado);
						actualizarListaCarpetas(orden);

					}
				});
				btnEditar.setFont(new Font("Open Sans", Font.PLAIN, 12));
				bottomPanel.add(btnEditar);
				
				JButton btnEliminar = new JButton("Eliminar");
				bottomPanel.add(btnEliminar);
				btnEliminar.setFont(new Font("Open Sans", Font.PLAIN, 12));
				btnEliminar.setIcon(new ImageIcon(Carpetas.class.getResource("/Resources/remove.png")));
				
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(listCarpetas.getSelectedValue() == null)
							return;
						Carpeta c = (Carpeta) listCarpetas.getSelectedValue();
						var resultado = JOptionPane.showConfirmDialog(null, "Eliminar '" + c.nombre + "' y todas sus notas?");
						if(resultado != 0)
							return;
						var db = ManejadorDB.getInstance();
						db.eliminarCarpeta(c.id);
						actualizarListaCarpetas(orden);

					}
				});
				
				JButton btnEntrar = new JButton("Entrar");
				bottomPanel.add(btnEntrar);
				btnEntrar.setFont(new Font("Open Sans", Font.PLAIN, 12));
				btnEntrar.setIcon(new ImageIcon(Carpetas.class.getResource("/Resources/right-arrow.png")));
				
				JPanel headerPanel = new JPanel();
				headerPanel.setBackground(Color.WHITE);
				headerPanel.setBounds(151, 11, 352, 40);
				frmCarpetas.getContentPane().add(headerPanel);
				headerPanel.setLayout(new GridLayout(0, 2, 10, 0));
				
				JLabel lblNewLabel = new JLabel("Ordenar por:");
				lblNewLabel.setBackground(Color.WHITE);
				headerPanel.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 15));
				
				JComboBox comboBoxOrdenar = new JComboBox();
				comboBoxOrdenar.setBackground(Color.WHITE);
				headerPanel.add(comboBoxOrdenar);
				comboBoxOrdenar.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						var index = comboBoxOrdenar.getSelectedIndex();
						orden = index +1 ;
						System.out.println(orden);
						actualizarListaCarpetas(orden);
					}
				});
				comboBoxOrdenar.setFont(new Font("Open Sans", Font.PLAIN, 15));
				comboBoxOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Fecha Ascendiente", "Fecha Descendiente", "Nombre"}));
				
				JLabel lblIconoCarpetas = new JLabel("");
				lblIconoCarpetas.setIcon(new ImageIcon(Carpetas.class.getResource("/Resources/FOLDER1.png")));
				lblIconoCarpetas.setBounds(36, 11, 59, 33);
				frmCarpetas.getContentPane().add(lblIconoCarpetas);
				btnEntrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(listCarpetas.getSelectedValue() == null)
							return;
						Carpeta c = (Carpeta) listCarpetas.getSelectedValue();
						new Notas(c);
						frmCarpetas.dispose();
					}
				});
		
		actualizarListaCarpetas(orden);
	}
	
	int orden = 1;
	
	private void actualizarListaCarpetas(int orden) {

		var db = ManejadorDB.getInstance();
		db.crearTablas();
		var db_carpetas = db.seleccionarTodasCarpetas(orden);
		
		listCarpetas.setListData(db_carpetas.toArray());
	}
}
