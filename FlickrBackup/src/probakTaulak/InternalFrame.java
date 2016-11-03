package probakTaulak;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 6481265986429597754L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

	public InternalFrame(String izenburua) {
		super(izenburua, true, false, true, true);
		this.setLayout(new BorderLayout());
		TaulaPanela taula = new TaulaPanela(izenburua, "");
		this.add(taula, BorderLayout.CENTER);
		this.pack();
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		this.setVisible(true);
	}
}
