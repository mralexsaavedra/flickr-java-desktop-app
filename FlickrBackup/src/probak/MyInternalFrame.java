package probak;

import javax.swing.JInternalFrame;

public class MyInternalFrame extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
    static final int xOffset = 50, yOffset = 50;

    public MyInternalFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        TableFilterDemo newContentPane = new TableFilterDemo();
        newContentPane.setOpaque(true);
        setContentPane(newContentPane);
        pack();
    }
}
