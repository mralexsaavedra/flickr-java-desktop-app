package probakTaulak;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class TaulaPanela extends JPanel implements ListSelectionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZebraJTable gureTaula;
	private JScrollPane gureJScrollPane;
	private AbstractTableModel gureModeloa;

	public TaulaPanela(String mota, String izena) {
		this.setLayout(new BorderLayout());
		gureModeloa = new BildumakTaula(mota);
		gureTaula = new ZebraJTable(gureModeloa);
		gureTaula.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = gureTaula.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		cellSelectionModel.addListSelectionListener(this);
		gureTaula.setRowSorter(new TableRowSorter<>(gureModeloa));
		gureJScrollPane = new JScrollPane(gureTaula);
		gureJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gureJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(gureJScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
