package ca.grasset.bibliotheque.swing.form;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ca.grasset.bibliotheque.controller.ClientController;
import ca.grasset.bibliotheque.swing.table.ClientTableModel;
import ca.grasset.bibliotheque.utilisateurs.Adresse;
import ca.grasset.bibliotheque.utilisateurs.Client;
import net.miginfocom.swing.MigLayout;

public class ClientForm extends JFrame{
	
	private JLabel lbNom, lbPrenom, lbLogin, lbCourriel, lbTelephone, lbSituation, 
	lbAttestation, lbIdentite, lbAdresse, lbVille, lbProvince, lbCodePostal;
	private JTextField txtNom, txtPrenom, txtLogin, txtCourriel, txtTelephone, 
	txtIdentite, txtAdresse, txtVille, txtProvince, txtCodePostal;
	private JPanel panelAdd, panelTable, panelButton;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
	private JTable table;
	private JScrollPane scrolPane;
	JComboBox<String> cbSituation = new JComboBox<String>();
	JComboBox<String> cbAttestation = new JComboBox<String>();

	 

	
	
	private List<Client>listeClient;
	private int idClient = 0;
	
	public ClientForm() throws SQLException {
		super("Formulaire Client");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Ajouter Client"));
		panelAdd.setBounds(5, 0, 680, 180);
		
		lbNom = new JLabel("Nom");
		lbPrenom = new JLabel("Prenom");
		lbLogin = new JLabel("Code utilisateur");
		lbCourriel = new JLabel("Courriel");
		lbTelephone = new JLabel("Telephone");
		lbSituation = new JLabel("Situation");
		lbAttestation = new JLabel("Attestation Residenciel");
		lbIdentite = new JLabel("Piece Identite");
		lbAdresse = new JLabel("Adresse");
		lbVille = new JLabel("Ville");
		lbProvince = new JLabel("Province");
		lbCodePostal = new JLabel("Code Postal");

		
		txtNom = new JTextField(50);
		txtPrenom = new JTextField(50);
		txtLogin = new JTextField(20);
		txtCourriel = new JTextField(20);
		txtTelephone = new JTextField(20);
		txtIdentite = new JTextField(15);
		txtAdresse = new JTextField(50);
		txtVille = new JTextField(20);
		txtProvince = new JTextField(20);
		txtCodePostal = new JTextField(10);
		
		cbSituation.addItem("actif");
		cbSituation.addItem("désactivé");
		cbAttestation.addItem("vérifié");
		cbAttestation.addItem("pas vérifié");

		
		panelAdd.add(lbNom);
		panelAdd.add(txtNom);
		panelAdd.add(lbPrenom, "gap unrelated");
		panelAdd.add(txtPrenom, "wrap");
		panelAdd.add(lbLogin);
		panelAdd.add(txtLogin);
		panelAdd.add(lbCourriel, "gap unrelated" );
		panelAdd.add(txtCourriel, "wrap");
		panelAdd.add(lbTelephone);
		panelAdd.add(txtTelephone);
		panelAdd.add(lbIdentite, "gap unrelated" );
		panelAdd.add(txtIdentite, "wrap");
		panelAdd.add(lbAdresse);
		panelAdd.add(txtAdresse);
		panelAdd.add(lbVille, "gap unrelated" );
		panelAdd.add(txtVille, "wrap");
		panelAdd.add(lbProvince);
		panelAdd.add(txtProvince);
		panelAdd.add(lbCodePostal, "gap unrelated" );
		panelAdd.add(txtCodePostal, "wrap");
		
		panelAdd.add(lbSituation);
		panelAdd.add(cbSituation);
		panelAdd.add(lbAttestation, "gap unrelated" );
		panelAdd.add(cbAttestation, "wrap");
		
		panelButton = new JPanel(new MigLayout());
		panelButton.setBorder(BorderFactory.createEtchedBorder());
		panelButton.setBounds(5, 185, 680, 40);
		
		ClassLoader loader = getClass().getClassLoader();
	    btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
	    btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
	    btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));
	    btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
	    btnCancel = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));

	    panelButton.add(btnNew, "gapleft 180");
	    panelButton.add(btnCancel);
	    panelButton.add(btnSave, "gap unrelated");
	    panelButton.add(btnUpdate, "gap unrelated");
	    panelButton.add(btnRemove);
	    
	    panelTable = new JPanel(new MigLayout());
	    panelTable.setBorder(BorderFactory.createTitledBorder("Liste des Clients"));
	    panelTable.setBounds(100, 230, 680, 350);
	    
	    table = new JTable();
	    
	    scrolPane = new JScrollPane(table);
	    
	    panelTable.add(scrolPane);
	    
	    refreshTable();
	    enableFields(false);
		
		add(panelAdd);
		add(panelButton);
		add(panelTable);	
		setMinimumSize(new Dimension(700, 620));
		setVisible(true);
		
		btnSave.addActionListener( ev-> {
			try {
				onSaveEmploye();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		btnCancel.addActionListener( ev-> {
			onCancel();
		});
		
		btnNew.addActionListener( ev-> {
			enableFields(true);
		});
		
		btnRemove.addActionListener( ev-> {
			try {
				System.out.println("entrou remove");
				onRemove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btnUpdate.addActionListener( ev-> {
			try {
				onUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	

	private void onUpdate() throws SQLException {
		
		int rowIndex = table.getSelectedRow();
		
		Client client = new ClientTableModel(listeClient).get(rowIndex);
		
		idClient = client.getId();
		
		txtNom.setText(client.getNom());
		txtPrenom.setText(client.getPrenom());
		txtLogin.setText(client.getLogin());
		txtCourriel.setText(client.getCourriel());
		txtTelephone.setText(client.getTelephone());
		txtIdentite.setText(client.getPieceIdentite());
		cbSituation.setSelectedItem(client.getSituation());
		cbAttestation.setSelectedItem(client.getAttestationResidentiel());
		txtAdresse.setText(client.getAdresse().getAdresse());
		txtVille.setText(client.getAdresse().getVille());
		txtProvince.setText(client.getAdresse().getProvince());
		txtCodePostal.setText(client.getAdresse().getCodePostal());		
		
		enableFields(true);
	}


	private void onRemove() throws SQLException {
		
		System.out.println("entrou metodo remove");
		int rowIndex = table.getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Sélectionnez l'employé à supprimer");
			return;
		}
		
		Client client = new ClientTableModel(listeClient).get(rowIndex);
		
		int confirme = JOptionPane.showConfirmDialog(this, "confirmer l'exclusion?", "Supprimer Client", JOptionPane.YES_NO_OPTION);
		
		if (confirme != 0) {
			return;
		}
		
		int resultat = new ClientController().remove(client.getId());
		if(resultat == 1) {
			JOptionPane.showMessageDialog(this, "Employe supprimé avec succès");
			refreshTable();
		}else {
			JOptionPane.showMessageDialog(this, "Employé non supprimé, réessayez");
		}		
	}


	private void onSaveEmploye() throws SQLException {
		
		Client client = new Client();
		if (verieChamps()) {
			chargeClient(client);
		}else {
			JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoire!");
			return;
		}
		
		int result =0;
		if (idClient == 0) {
			result = new ClientController().add(client);
			
		}else {
			client.setId(idClient);
			result = 	new ClientController().update(client);
			idClient = 0;
			onCancel();
		}
		
		if(result == 1) {
			JOptionPane.showMessageDialog(this, "Employe ajouté avec succès");
			onCancel();
			refreshTable();
		}else {
			JOptionPane.showMessageDialog(this, "Employé non ajouté, réessayez");

		}
		
	}
	
	private boolean verieChamps() {
		if (txtNom.getText().length()>0 && txtPrenom.getText().length() > 0 && txtLogin.getText().length() > 0 &&
				txtIdentite.getText().length()>0 && txtCourriel.getText().length()>0 &&
				txtTelephone.getText().length()>0 && txtAdresse.getText().length()>0 &&
				txtVille.getText().length()>0 &&txtProvince.getText().length()>0 &&
				txtCodePostal.getText().length()>0) {
			return true;
		}
		return false;
	}
	
	private void chargeClient(Client client) {
		client.setNom(txtNom.getText());
		client.setPrenom(txtPrenom.getText());
		client.setLogin(txtLogin.getText());
		client.setCourriel(txtCourriel.getText());
		client.setTelephone(txtTelephone.getText());
		client.setCourriel(txtCourriel.getText());
		client.setPieceIdentite(txtIdentite.getText());
		client.setSituation(cbSituation.getSelectedItem().toString());
		client.setAttestationResidenciel(cbAttestation.getSelectedItem().toString());
		client.setAdresse(chargeAdresse());
	}


	private Adresse chargeAdresse() {
		Adresse adresse = new Adresse();
		adresse.setAdresse(txtAdresse.getText());
		adresse.setVille(txtVille.getText());
		adresse.setProvince(txtProvince.getText());
		adresse.setCodePostal(txtCodePostal.getText());
		return adresse;
	}


	private void onCancel() {
		txtNom.setText("");
		txtPrenom.setText("");
		txtLogin.setText("");
		txtIdentite.setText("");
		txtAdresse.setText("");
		txtProvince.setText("");
		txtVille.setText("");
		txtCourriel.setText("");
		txtTelephone.setText("");
		txtCodePostal.setText("");

		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtNom.setEditable(b);
		txtPrenom.setEditable(b);
		txtLogin.setEditable(b);
		txtAdresse.setEditable(b);
		txtVille.setEditable(b);
		txtProvince.setEditable(b);
		txtCodePostal.setEditable(b);
		txtIdentite.setEditable(b);
		txtCourriel.setEditable(b);
		txtTelephone.setEditable(b);
		cbAttestation.setEditable(b);
		cbSituation.setEditable(b);
	}
	
	private void refreshTable() throws SQLException {
		listeClient = new ClientController().find();
		if(listeClient!=null) {
			table.setModel(new ClientTableModel(listeClient));
		}
	}
	
	
	
}
