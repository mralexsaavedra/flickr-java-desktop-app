package taulak_UI;

import java.io.IOException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import flickrJava.Bildumak;

public class TaulaModeloa extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Object[][] data = this.elementuakGehitu();
	private String[] columnNames = { "Titulua", "Deskripzioa","Argazkiak"};
	private boolean DEBUG = false;
	
	public Object[][] elementuakGehitu() {
		data = new Object[100][3];
		Bildumak bildumak;
		try {
			bildumak = new Bildumak();
			List<String[]> emaitzak = bildumak.showPhotosets();
			for (String[] bilduma : emaitzak) {
				for (int errenkada = 0; errenkada < data.length; errenkada++) {
					data[errenkada][0] = bilduma[0];
					data[errenkada][1] = bilduma[1];
					data[errenkada][2] = bilduma[2];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
