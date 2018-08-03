package ca.grasset.bibliotheque.swing.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.grasset.bibliotheque.livre.Auteur;

public class AuteurTableModel extends AbstractTableModel{
	
	private static final int COL_ID = 0;
	private static final int COL_PRENOM= 1;
	private static final int COL_NOM = 2;
	
	private List<Auteur> auteurs;
	
	public AuteurTableModel(List <Auteur> auteurs ) {
		this.auteurs = auteurs;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return auteurs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Auteur auteur = auteurs.get(rowIndex);
		if(columnIndex == COL_ID) {
			return auteur.getId();
		}else if(columnIndex == COL_PRENOM) {
			return auteur.getPrenom();
		}else if(columnIndex == COL_NOM) {
			return auteur.getNom();
		}
		return null;
	}
	
	
	@Override
	public String getColumnName(int column) {
		
		String colonne = "";
		
		switch (column) {
			case COL_ID:
				colonne = "id";
				break;
			case COL_PRENOM:
				colonne = "Prenom";
				break;
			case COL_NOM:
				colonne = "Nom";
				break;
			default:
				throw new IllegalArgumentException("Colonne invalid");
		}
		
		return colonne;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == COL_ID) {
			return int.class;
		}else {
			return String.class;
		}
	}

	public Auteur get(int row) {
		return auteurs.get(row);
	}
}
