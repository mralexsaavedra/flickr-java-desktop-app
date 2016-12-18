package taulak_UI;



import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

}