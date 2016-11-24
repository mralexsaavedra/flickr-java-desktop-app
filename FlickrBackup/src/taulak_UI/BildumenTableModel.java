package taulak_UI;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class BildumenTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	Vector<String> columnNames = new Vector<String>();
	
	public BildumenTableModel() {
		this.kargatu();
	}
	
	public void kargatu() {
		this.hasieratuZutabeIzenak();
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	public void hasieratuZutabeIzenak() {
		columnNames.add("Titulua");
		columnNames.add("Deskripzioa");
		columnNames.add("Argazki kopurua");
	}
	
	public String getColumnName(int zut) {
		return columnNames.get(zut);
	}
	
	public Class<?> getColumnClass(int col) {
		Class<?> klaseMota = null;
		switch (col) {
		case 0:
			klaseMota = String.class;
			break;
		case 1:
			klaseMota = String.class;
			break;
		case 2:
			klaseMota = Integer.class;
			break;
		default:
			break;
		}
		
		return klaseMota;
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}