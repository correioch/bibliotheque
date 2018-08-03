package ca.grasset.bibliotheque.swing.form;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ca.grasset.bibliotheque.controller.AuteurController;
import ca.grasset.bibliotheque.controller.ClientController;
import ca.grasset.bibliotheque.livre.Auteur;
import ca.grasset.bibliotheque.swing.table.AuteurTableModel;
import ca.grasset.bibliotheque.swing.table.ClientTableModel;
import ca.grasset.bibliotheque.utilisateurs.Client;
import net.miginfocom.swing.MigLayout;

public class AuteurForm extends JFrame{
	
	private JLabel lbNom, lbPrenom;
	private JTextField txtNom, txtPrenom; 
	private JPanel panelAdd, panelTable, panelButton;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
	private JTable table;
	private JScrollPane scrolPane;
	
	private List<Auteur>listeAuteur;
	private int idAuteur = 0;
	
	public AuteurForm() throws SQLException {
		super("Formulaire Auteur");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Ajouter Auteur"));
		panelAdd.setBounds(5, 0, 680, 180);
		
		lbNom = new JLabel("Nom");
		lbPrenom = new JLabel("Prenom");
		
		txtNom = new JTextField(50);
		txtPrenom = new JTextField(50);

		panelAdd.add(lbNom);
		panelAdd.add(txtNom, "wrap");
		panelAdd.add(lbPrenom);
		panelAdd.add(txtPrenom, "wrap");
		
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
	    panelTable.setBorder(BorderFactory.createTitledBorder("Liste des Auteurs"));
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
		
		Auteur auteur = new AuteurTableModel(listeAuteur).get(rowIndex);
		
		idAuteur = auteur.getId();
		
		txtNom.setText(auteur.getNom());
		txtPrenom.setText(auteur.getPrenom());
		
		enableFields(true);
	}


	private void onRemove() throws SQLException {
		
		int rowIndex = table.getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Sélectionnez l'employé à supprimer");
			return;
		}
		
		Auteur auteur = new AuteurTableModel(listeAuteur).get(rowIndex);
		
		int confirme = JOptionPane.showConfirmDialog(this, "confirmer l'exclusion?", "Supprimer Client", JOptionPane.YES_NO_OPTION);
		
		if (confirme != 0) {
			return;
		}
		
		int resultat = new AuteurController().remove(auteur.getId());
		if(resultat == 1) {
			JOptionPane.showMessageDialog(this, "Auteur supprimé avec succès");
			refreshTable();
		}else {
			JOptionPane.showMessageDialog(this, "Auteur non supprimé, réessayez");
		}		
	}


	private void onSaveEmploye() throws SQLException {
		
		Auteur auteur = new Auteur();
		if (verieChamps()) {
			chargeAuteur(auteur);
		}else {
			JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoire!");
			return;
		}
		
		int result =0;
		if (idAuteur == 0) {
			result = new AuteurController().add(auteur);
			
		}else {
			auteur.setId(idAuteur);
			result = 	new AuteurController().update(auteur);
			idAuteur = 0;
			onCancel();
		}
		
		if(result == 1) {
			JOptionPane.showMessageDialog(this, "Auteur ajouté avec succès");
			onCancel();
			refreshTable();
		}else {
			JOptionPane.showMessageDialog(this, "Auteur non ajouté, réessayez");

		}
		
	}
	
	private boolean verieChamps() {
		if(txtNom.getText().length()>0 && txtPrenom.getText().length() > 0) {
			return true;
		}
		return false;
	}
	
	private void chargeAuteur(Auteur auteur) {
		auteur.setNom(txtNom.getText());
		auteur.setPrenom(txtPrenom.getText());
		
	}

	private void onCancel() {
		txtNom.setText("");
		txtPrenom.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtNom.setEditable(b);
		txtPrenom.setEditable(b);
	}
	
	private void refreshTable() throws SQLException {
		listeAuteur = new AuteurController().find();
		if(listeAuteur!=null) {
			table.setModel(new AuteurTableModel(listeAuteur));
		}
	}
	
	
	
}
