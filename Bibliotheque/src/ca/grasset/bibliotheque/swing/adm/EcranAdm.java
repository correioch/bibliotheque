package ca.grasset.bibliotheque.swing.adm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.utilisateurs.Employe;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class EcranAdm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	List<Employe> listeEmploye = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAdm frame = new EcranAdm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EcranAdm() throws SQLException {
		setTitle("Ecran administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Inclure Employe");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnChercher = new JButton("chercher");
		
		JLabel lblChercherEmploye = new JLabel("chercher employe");
		
		JComboBox comboBox = new JComboBox();
		chargeListeEmploye();
		
		DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(listeEmploye.toArray());
		comboBox.setModel(defaultComboBox);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnChercher))
						.addComponent(lblChercherEmploye)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(253, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(lblChercherEmploye)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChercher))
					.addGap(45)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(106, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void chargeListeEmploye() throws SQLException {
		
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		boolean resultat = statement.execute("SELECT * FROM `employe` WHERE id_type_employe = 2");
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			
			String prenom = resultSet.getString("prenom");
			String nom = resultSet.getString("nom");
			String code = resultSet.getString("id_type_employe");
			String password = resultSet.getString("mot_cle");
			listeEmploye.add(new Employe(code, nom, prenom, password));
	
		}
		System.out.println(listeEmploye);
		resultSet.close();
		statement.close();
		connection.close();
		
	}
	
}
