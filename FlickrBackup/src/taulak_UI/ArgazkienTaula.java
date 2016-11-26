package taulak_UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ArgazkienTaula extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ArgazkienTableModel model;
	private JTable taula;
	private JScrollPane scrollPanela;
	private String erabiltzaile;

	public ArgazkienTaula(String email) {
		this.erabiltzaile = email;
		model = new ArgazkienTableModel(erabiltzaile);
		taula = new JTable(model);
		scrollPanela = new JScrollPane(taula);
		this.add(scrollPanela);
	}

	
}