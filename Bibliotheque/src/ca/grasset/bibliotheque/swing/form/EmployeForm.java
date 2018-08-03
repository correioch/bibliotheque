package ca.grasset.bibliotheque.swing.form;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import ca.grasset.bibliotheque.controller.EmployeController;
import ca.grasset.bibliotheque.swing.table.EmployeTableModel;
import ca.grasset.bibliotheque.utilisateurs.Employe;
import net.miginfocom.swing.MigLayout;

public class EmployeForm extends JFrame{
	
	private JLabel lbNom, lbPrenom, lbLogin;
	private JTextField txtNom, txtPrenom, txtLogin;
	private JPanel panelAdd, panelTable, panelButton;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
	private JTable table;
	private JScrollPane scrolPane;
	
	private List<Employe>listeEmploye;
	private int idEmploye = 0;
	
	public EmployeForm() throws SQLException {
		super("Formulaire Employe");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Ajouter Employe"));
		panelAdd.setBounds(5, 0, 480, 100);
		
		lbNom = new JLabel("Nom");
		lbPrenom = new JLabel("Prenom");
		lbLogin = new JLabel("Code utilisateur");

		
		txtNom = new JTextField(50);
		txtPrenom = new JTextField(50);
		txtLogin = new JTextField(50);
		
		panelAdd.add(lbNom);
		panelAdd.add(txtNom, "span, growx");
		panelAdd.add(lbPrenom);
		panelAdd.add(txtPrenom, "span, growx");
		panelAdd.add(lbLogin);
		panelAdd.add(txtLogin, "wrap para");
		
		panelButton = new JPanel(new MigLayout());
		panelButton.setBorder(BorderFactory.createEtchedBorder());
		panelButton.setBounds(5, 105, 480, 40);
		
		ClassLoader loader = getClass().getClassLoader();
	    btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
	    btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
	    btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));
	    btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
	    btnCancel = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));

	    panelButton.add(btnNew, "gapleft 90");
	    panelButton.add(btnCancel);
	    panelButton.add(btnSave, "gap unrelated");
	    panelButton.add(btnUpdate, "gap unrelated");
	    panelButton.add(btnRemove);
	    
	    panelTable = new JPanel(new MigLayout());
	    panelTable.setBorder(BorderFactory.createTitledBorder("Liste d'employe"));
	    panelTable.setBounds(5, 150, 480, 240);
	    
	    table = new JTable();
	    
	    scrolPane = new JScrollPane(table);
	    
	    panelTable.add(scrolPane);
	    
	    refreshTable();
	    enableFields(false);
		
		add(panelAdd);
		add(panelButton);
		add(panelTable);	
		setMinimumSize(new Dimension(500, 420));
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
		
		Employe employe = new EmployeTableModel(listeEmploye).get(rowIndex);
		
		idEmploye = employe.getId();
		
		txtNom.setText(employe.getNom());
		txtPrenom.setText(employe.getPrenom());
		txtLogin.setText(employe.getLogin());
		
		enableFields(true);
	}


	private void onRemove() throws SQLException {
		int rowIndex = table.getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Sélectionnez l'employé à supprimer");
			return;
		}
		
		Employe employe = new EmployeTableModel(listeEmploye).get(rowIndex);
		
		int confirme = JOptionPane.showConfirmDialog(this, "confirmer l'exclusion?", "Supprimer Employe", JOptionPane.YES_NO_OPTION);
		
		if (confirme != 0) {
			return;
		}
		
		int resultat = new EmployeController().removeEmploye(employe.getId());
		if(resultat == 1) {
			JOptionPane.showMessageDialog(this, "Employe supprimé avec succès");
			refreshTable();
		}else {
			JOptionPane.showMessageDialog(this, "Employé non supprimé, réessayez");

		}		
	}


	private void onSaveEmploye() throws SQLException {
		Employe employe = new Employe();
		if (txtNom.getText().length()>0 && txtPrenom.getText().length() > 0 && txtLogin.getText().length() > 0) {
			employe.setNom(txtNom.getText());
			employe.setPrenom(txtPrenom.getText());
			employe.setLogin(txtLogin.getText());
		}else {
			JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoire!");
			return;
		}
		
		int result =0;
		if (idEmploye == 0) {
			result = new EmployeController().addEmploye(employe);
			onCancel();
		}else {
			employe.setId(idEmploye);
			result = 	new EmployeController().updateEmploye(employe);
			idEmploye = 0;
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

	private void onCancel() {
		txtNom.setText("");
		txtPrenom.setText("");
		txtLogin.setText("");
		enableFields(false);

	}
	
	private void enableFields(boolean b) {
		txtNom.setEditable(b);
		txtPrenom.setEditable(b);
		txtLogin.setEditable(b);

		
	}
	
	private void refreshTable() throws SQLException {
		listeEmploye = new EmployeController().findEmploye();
		if(listeEmploye!=null) {
			table.setModel(new EmployeTableModel(listeEmploye));
		}
	}
	
	
	
}
