package taulak_UI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.imgscalr.Scalr;

import kudeatzaileak.Kudeatzailea;

public class ArgazkienTaula extends JPanel {

	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private String[] columnNames = { "Argazkia", "Bilduma","Irudia"};
	private JTable taula;
	private JScrollPane scrollPanela;

	public ArgazkienTaula() {
		this.elementuakGehitu();
		taula = new JTable(data, columnNames);
		scrollPanela = new JScrollPane(taula);
		this.add(scrollPanela);
	}

	public void elementuakGehitu() {
		List<String[]> emaitzak = Kudeatzailea.getInstantzia().getErlazioak();
		data = new Object[emaitzak.size()][3];
		for (int errenkada = 0; errenkada < emaitzak.size(); errenkada++) {
				data[errenkada][0] = emaitzak.get(errenkada)[0];
				data[errenkada][1] = emaitzak.get(errenkada)[1];	
				
				String emaitza = Kudeatzailea.getInstantzia().getArgazkiFile(emaitzak.get(errenkada)[0]);
				File f = new File(emaitza);
					try {
						BufferedImage img = ImageIO.read(f);
						BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED,  Scalr.Mode.FIT_TO_WIDTH, 150, 100, Scalr.OP_ANTIALIAS);
						data[errenkada][2] = new ImageIcon(thumbnail);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		}
	}

}
