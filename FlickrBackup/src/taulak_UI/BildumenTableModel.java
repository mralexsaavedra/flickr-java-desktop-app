package taulak_UI;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.flickr4java.flickr.photosets.Photoset;

import flickrJava.Bildumak;
import kudeatzaileak.Kudeatzailea;

public class BildumenTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String erabiltzaile;
	private Vector<BildumenLag> data;
	private Vector<String> columnNames = new Vector<String>();
	
	public BildumenTableModel(String email) {
		this.erabiltzaile = email;
		this.elementuakGehitu();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex).getBalioa(columnIndex);
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
		if (col == 1)
			return true;
		else
			return false;
	}
	
	 public void setValueAt(Object value, int row, int col) {
		 data.get(row).insertElementAt(value, col);
     }
	 
	 public void deleteRow(int row){
		 data.remove(row);
		 this.fireTableRowsUpdated(0, this.getRowCount());
		 this.fireTableDataChanged();
	 }
	 
	 public void datuakEguneratu(){
		 Bildumak bildumak;
		try {
			bildumak = new Bildumak(erabiltzaile);
			for (BildumenLag errenkada : data){
				 Photoset bilduma = bildumak.bildumaLortu(errenkada.titulua);
				 Kudeatzailea.getInstantzia().bildumakGorde(errenkada.titulua, errenkada.deskripzioa, bilduma.getPhotoCount(), erabiltzaile);
				List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumaBatenMD5Guztiak(errenkada.titulua);
				for (String[] argazkia : emaitzak){
					 Kudeatzailea.getInstantzia().erlazioakEgin(erabiltzaile, errenkada.titulua, argazkia[0]);
				}
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	 }

	public void elementuakGehitu() {
		this.hasieratuZutabeIzenak();
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumak(erabiltzaile);
		data = new Vector<BildumenLag>(emaitzak.size());
		for (int errenkada = 0; errenkada < emaitzak.size(); errenkada++) {
			data.add(new BildumenLag(emaitzak.get(errenkada)[0], emaitzak.get(errenkada)[1], Integer.parseInt(emaitzak.get(errenkada)[2])));
		}
	}
	
}