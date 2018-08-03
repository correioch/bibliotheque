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

public class LivreForm extends JFrame{
	
	private JLabel lbTittle, lbAnneEdition, lbISBN, lbDateAcquisition, lbTypeLivre, lbSituation, 
	lbStatus, lbAuteur, lbMaisonEdition;
	private JTextField txtTittle, txtAnneEdition, txtISBN, txtDateAcquisition;
	private JPanel panelAdd, panelTable, panelButton;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
	private JTable table;
	private JScrollPane scrolPane;
	JComboBox<String> cbSituation = new JComboBox<String>();
	JComboBox<String> cbTypeLivre = new JComboBox<String>();
	JComboBox<String> cbStatus = new JComboBox<String>();
	JComboBox<String> cbAuteur = new JComboBox<String>();
	JComboBox<String> cbMaisonEdition = new JComboBox<String>();
	
	private List<Client>listeLivro;
	private int idLivro = 0;
	
	public LivreForm() throws SQLException {
		super("Formulaire Livro");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Ajouter Livro"));
		panelAdd.setBounds(5, 0, 680, 180);
		
		lbTittle = new JLabel("Tittle");
		lbAnneEdition = new JLabel("Anne Edition");
		lbISBN = new JLabel("ISBN");
		lbDateAcquisition = new JLabel("Date Acquisition");
		lbTypeLivre = new JLabel("Type du livre");
		lbSituation = new JLabel("Situation");
		lbStatus = new JLabel("Status du livre");
		lbAuteur = new JLabel("Auteurs");
		lbMaisonEdition = new JLabel("Maison d'edition");

		
		txtTittle = new JTextField(50);
		txtAnneEdition = new JTextField(50);
		txtISBN = new JTextField(20);
		txtDateAcquisition = new JTextField(20);
		
		cbSituation.addItem("actif");
		cbSituation.addItem("désactivé");
		cbTypeLivre.addItem("Standard");
		cbTypeLivre.addItem("Rare");
		cbStatus.addItem("actif");
		cbStatus.addItem("désactivé");

		
		panelAdd.add(lbTittle);
		panelAdd.add(txtTittle);
		panelAdd.add(lbAnneEdition, "gap unrelated");
		panelAdd.add(txtAnneEdition, "wrap");
		panelAdd.add(lbISBN);
		panelAdd.add(txtISBN);
		panelAdd.add(lbDateAcquisition, "gap unrelated" );
		panelAdd.add(txtDateAcquisition, "wrap");
		panelAdd.add(lbMaisonEdition);
		panelAdd.add(cbMaisonEdition);
		panelAdd.add(lbTypeLivre);
		panelAdd.add(cbTypeLivre);
		panelAdd.add(lbAuteur, "gap unrelated" );
		panelAdd.add(cbAuteur, "wrap");
		panelAdd.add(lbStatus);
		panelAdd.add(cbStatus);
		panelAdd.add(lbSituation, "gap unrelated" );
		panelAdd.add(cbSituation, "wrap");
		
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
	    panelTable.setBorder(BorderFactory.createTitledBorder("Liste des Livres"));
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
		
		Client client = new ClientTableModel(listeLivro).get(rowIndex);
		
		idLivro = client.getId();
		
		txtTittle.setText(client.getNom());
		txtAnneEdition.setText(client.getPrenom());
		txtISBN.setText(client.getLogin());
		txtDateAcquisition.setText(client.getCourriel());
		
		cbSituation.setSelectedItem(client.getSituation());
		cbTypeLivre.setSelectedItem(client.getAttestationResidentiel());
		
		
		enableFields(true);
	}


	private void onRemove() throws SQLException {
		
		System.out.println("entrou metodo remove");
		int rowIndex = table.getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Sélectionnez l'employé à supprimer");
			return;
		}
		
		Client client = new ClientTableModel(listeLivro).get(rowIndex);
		
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
		if (idLivro == 0) {
			result = new ClientController().add(client);
			
		}else {
			client.setId(idLivro);
			result = 	new ClientController().update(client);
			idLivro = 0;
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
		
		return false;
	}
	
	private void chargeClient(Client client) {
		client.setNom(txtTittle.getText());
		client.setPrenom(txtAnneEdition.getText());
		client.setLogin(txtISBN.getText());
		client.setCourriel(txtDateAcquisition.getText());
		client.setCourriel(txtDateAcquisition.getText());
		client.setSituation(cbSituation.getSelectedItem().toString());
		client.setAttestationResidenciel(cbTypeLivre.getSelectedItem().toString());
		
	}

	private void onCancel() {
		txtTittle.setText("");
		txtAnneEdition.setText("");
		txtISBN.setText("");
		txtAnneEdition.setText("");
		txtDateAcquisition.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtTittle.setEditable(b);
		txtAnneEdition.setEditable(b);
		txtISBN.setEditable(b);
		txtAnneEdition.setEditable(b);
		txtDateAcquisition.setEditable(b);
		cbTypeLivre.setEditable(b);
		cbSituation.setEditable(b);
		cbAuteur.setEditable(b);
	}
	
	private void refreshTable() throws SQLException {
		listeLivro = new ClientController().find();
		if(listeLivro!=null) {
			table.setModel(new ClientTableModel(listeLivro));
		}
	}
	
	
	
}
