package taulak_UI;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import kudeatzaileak.Kudeatzailea;

public class BildumenTaula extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private BildumenTableModel model;
	private JTable taula;
	private JScrollPane scrollPanela;
	
	public BildumenTaula(){
		this.elementuakGehitu();
		model = new BildumenTableModel();
		taula = new JTable(model);
		scrollPanela = new JScrollPane(taula);
		this.add(scrollPanela);
	}
	
	public void elementuakGehitu() {
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getBildumak();
		model.data = new Object[emaitzak.size()][3];
		for (int errenkada = 0; errenkada < model.data.length; errenkada++) {
			model.data[errenkada][0] = emaitzak.get(errenkada)[0];
			model.data[errenkada][1] = emaitzak.get(errenkada)[1];
			model.data[errenkada][2] = emaitzak.get(errenkada)[2];
		}
	}
}