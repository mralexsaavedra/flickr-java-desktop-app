package probakTaulak;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import flickrJava.Bildumak;

public class BildumakTaula extends AbstractTableModel {

	private static final long serialVersionUID = 4361943585330570345L;
	private String[] izenak;
	private ArrayList<String[]> datuak;

	public BildumakTaula(String zailtasuna) {
		izenak = new String[] { "Erabiltzailearen izena", "Denbora" };
		try {
			datuak = (ArrayList<String[]>) new Bildumak().showPhotosets();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return this.getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return izenak.length;
	}

	@Override
	public String getColumnName(int i) {
		return izenak[i];
	}

	@Override
	public int getRowCount() {
		return datuak.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return datuak.get(arg0)[arg1];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
