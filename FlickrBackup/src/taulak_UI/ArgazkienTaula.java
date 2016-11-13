package taulak_UI;

import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import flickrJava.Argazkiak;


public class ArgazkienTaula extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Object[][] data = new Object[30][2];
	//private String[] columnNames = { "Titulua", "Deskripzioa", "Data1","Data2","Data3","Data4","Etiketa"};
	private String[] columnNames = {"Titulua","Bilduma"};
	private JTable taula;
	private JScrollPane scrollPanela;
	
	public ArgazkienTaula(){
		this.elementuakGehitu();
		taula = new JTable(data,columnNames);
		scrollPanela = new JScrollPane(taula);
		this.add(scrollPanela);
	}
	
	public void elementuakGehitu() {
		Argazkiak argazkiak;
		try {
			argazkiak = new Argazkiak();
			List<String[]> emaitzak = argazkiak.showPhotos();
			for (String[] argazkia : emaitzak) {
				for (int errenkada = 0; errenkada < data.length; errenkada++) {
					data[errenkada][0] = argazkia[0];
					data[errenkada][1] = argazkia[1];
					/*data[errenkada][1] = argazkia[1];
					data[errenkada][2] = argazkia[2];
					data[errenkada][3] = argazkia[3];
					data[errenkada][4] = argazkia[4];
					data[errenkada][5] = argazkia[5];
					data[errenkada][6] = argazkia[6];*/
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
