package flickrPantailak;

import javax.swing.JInternalFrame;

public class MyInternalFrame extends JInternalFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        setSize(500,400);
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        setVisible(true);
    }
    
}
