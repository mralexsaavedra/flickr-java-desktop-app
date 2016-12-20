package taulak_UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.flickr4java.flickr.FlickrException;

public class BildumenTaula extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private BildumenTableModel model;
	private JTable taula;
	private JScrollPane scrollPanela;
	private String erabiltzaile;
	
	public BildumenTaula(String email){
		this.erabiltzaile = email;
		model = new BildumenTableModel(erabiltzaile);
		taula = new JTable(model);
		scrollPanela = new JScrollPane(taula);
		this.add(scrollPanela);
	}
	
	public void eguneratu() throws FlickrException{
		model.datuakEguneratu();
	}
	
	public void ezabatu(){
		if (taula.getSelectedRow()==-1){
			if (model.getRowCount()!=0)
				model.deleteRow(model.getRowCount()-1);
		} else{
			model.deleteRow(taula.getSelectedRow());
		}
	}

}