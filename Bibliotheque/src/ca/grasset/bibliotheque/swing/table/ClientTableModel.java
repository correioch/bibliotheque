package ca.grasset.bibliotheque.swing.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.grasset.bibliotheque.utilisateurs.Client;

public class ClientTableModel extends AbstractTableModel{
	
	private static final int COL_ID = 0;
	private static final int COL_PRENOM= 1;
	private static final int COL_NOM = 2;
	private static final int COL_LOGIN = 3;
	
	private List<Client> clients;
	
	public ClientTableModel(List <Client> clients ) {
		this.clients = clients;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Client client = clients.get(rowIndex);
		if(columnIndex == COL_ID) {
			return client.getId();
		}else if(columnIndex == COL_PRENOM) {
			return client.getPrenom();
		}else if(columnIndex == COL_NOM) {
			return client.getNom();
		}else if(columnIndex == COL_LOGIN) {
			return client.getLogin();
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
			case COL_LOGIN:
				colonne = "Code_utilisateur";
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

	public Client get(int row) {
		return clients.get(row);
	}
}
