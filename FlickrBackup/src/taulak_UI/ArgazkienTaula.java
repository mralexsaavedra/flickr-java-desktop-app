package taulak_UI;

import java.awt.Dimension;

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
		scrollPanela.setPreferredSize(new Dimension(900,400));
		taula.setRowHeight(80);
		taula.getColumnModel().getColumn(0).setPreferredWidth(100);
		taula.getColumnModel().getColumn(1).setPreferredWidth(150);
		this.add(scrollPanela);
	}

	
}