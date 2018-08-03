package ca.grasset.bibliotheque.swing;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.swing.adm.EcranAdm;
import ca.grasset.bibliotheque.swing.form.EmployeForm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel ok;
	private JTextField login;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Biblioth\u00E8que Marguerite Bourgeoys");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 285);
		ok = new JPanel();
		ok.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ok);
		
		JLabel lblBienvueABibliothque = new JLabel("Bienvenue a Biblioth\u00E8que Marguerite Bourgeoys");
		lblBienvueABibliothque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCodeUtilisateur = new JLabel("CODE UTILISATEUR");
		
		JLabel lblMotDePasse = new JLabel("MOT DE PASSE");
		
		login = new JTextField();
		login.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		
		
		
		JLabel message = new JLabel(".");
		
		btnOk.addActionListener( ev-> {
			try {
				String strLogin = login.getText();
				String strPassword = password.getText();
				
				if(testeLogin(strLogin, strPassword)) {
					message.setText("login ok");
					EmployeForm  ecranAdm = new EmployeForm();
					ecranAdm.setVisible(true);
					this.dispose();
					
				}else {
					message.setText("fail login");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		GroupLayout gl_ok = new GroupLayout(ok);
		gl_ok.setHorizontalGroup(
			gl_ok.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ok.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_ok.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodeUtilisateur)
						.addComponent(lblMotDePasse))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ok.createParallelGroup(Alignment.LEADING)
						.addComponent(login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_ok.createSequentialGroup()
					.addContainerGap(89, Short.MAX_VALUE)
					.addComponent(lblBienvueABibliothque)
					.addGap(46))
				.addGroup(gl_ok.createSequentialGroup()
					.addGap(165)
					.addComponent(btnOk)
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_ok.createSequentialGroup()
					.addComponent(message, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_ok.setVerticalGroup(
			gl_ok.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ok.createSequentialGroup()
					.addComponent(lblBienvueABibliothque, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ok.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodeUtilisateur)
						.addComponent(login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_ok.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMotDePasse)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOk)
					.addGap(29)
					.addComponent(message)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		ok.setLayout(gl_ok);
	}
	
	private Boolean testeLogin(String login, String password) throws SQLException{
		
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		boolean resultat = statement.execute("select * from employe "
				+ "where code_utilisateur = " +"'"+ login + "'");
		ResultSet resultSet = statement.getResultSet();
		
		
		System.out.println(login + " e " + password);
		
		if(!resultSet.next()) {
			resultat = false;
		}else if(!password.equals(resultSet.getString("mot_cle"))){
			resultat = false;
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		return resultat;
	}
}
