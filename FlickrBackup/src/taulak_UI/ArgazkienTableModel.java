package taulak_UI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import org.imgscalr.Scalr;

import kudeatzaileak.Kudeatzailea;

public class ArgazkienTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private String erabiltzaile;
	private Vector<ArgazkienLag> data;
	private Vector<String> columnNames = new Vector<String>();
	
	public ArgazkienTableModel(String email) {
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
		columnNames.add("Irudia");
		columnNames.add("Argazkia");
		columnNames.add("Bilduma");
		columnNames.add("Deskripzioa");
		columnNames.add("DateAdded");
		columnNames.add("DatePosted");
		columnNames.add("DateTaken");
		columnNames.add("GeoData");
		columnNames.add("Tag");
	}
	
	public String getColumnName(int zut) {
		return columnNames.get(zut);
	}
	
	public Class<?> getColumnClass(int col) {
		Class<?> klaseMota = null;
		switch (col) {
		case 0:
			klaseMota = ImageIcon.class;
			break;
		case 1:
			klaseMota = String.class;
			break;
		case 2:
			klaseMota = String.class;
			break;
		case 3:
			klaseMota = String.class;
			break;
		case 4:
			klaseMota = String.class;
			break;
		case 5:
			klaseMota = String.class;
			break;
		case 6:
			klaseMota = String.class;
			break;
		case 7:
			klaseMota = String.class;
			break;
		case 8:
			klaseMota = String.class;
		default:
			break;
		}
		
		return klaseMota;
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col == 1)
			return false;
		else
			return true;
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
		 for (ArgazkienLag errenkada : data){
			 //Kudeatzailea.getInstantzia().argazkiakGorde(md5, izena, file, deskripzioa, dateAdded, datePosted, dateTaken, geoData, tag);
			 //Kudeatzailea.getInstantzia().erlazioakEgin(erabiltzaile, bilduma, argazkia);
		 }
	 }
	
	public void elementuakGehitu() {
		this.hasieratuZutabeIzenak();
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getErlazioak(erabiltzaile);
		data = new Vector<ArgazkienLag>(emaitzak.size());
		for (int errenkada = 0; errenkada < emaitzak.size(); errenkada++) {
			String emaitza = Kudeatzailea.getInstantzia().getArgazkiFile(emaitzak.get(errenkada)[0]);
			File f = new File(emaitza);
			try {
				BufferedImage img = ImageIO.read(f);
				BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED,  Scalr.Mode.FIT_TO_WIDTH, 150, 100, Scalr.OP_ANTIALIAS);
				data.add(new ArgazkienLag(new ImageIcon(thumbnail), emaitzak.get(errenkada)[0], emaitzak.get(errenkada)[1],emaitzak.get(errenkada)[2],emaitzak.get(errenkada)[3],emaitzak.get(errenkada)[4],emaitzak.get(errenkada)[5],emaitzak.get(errenkada)[6],emaitzak.get(errenkada)[7]));
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
	}
	
}