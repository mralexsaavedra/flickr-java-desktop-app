package flickrPantailak;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ArgazkiakIgo extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;

    public ArgazkiakIgo() {
        super(new BorderLayout());

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();

        openButton = new JButton("Ireki");
        openButton.addActionListener(this);

        saveButton = new JButton("Gorde");
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(ArgazkiakIgo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Aukeratutakoa: " + file.getName() + "." + newline);
            } else {
                log.append("Irekita kantzelatuta" + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(ArgazkiakIgo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Gordetzen: " + file.getName() + "." + newline);
            } else {
                log.append("Gorde akzioa kantzelatuta" + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ArgazkiakIgo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Ezin da ireki: " + path);
            return null;
        }
    }

    public void eraikiFrame(){
    		JFrame frame = new JFrame("Flickr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ArgazkiakIgo());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    		UIManager.put("swing.boldMetal", Boolean.FALSE); 
         ArgazkiakIgo argazkiakIgo = new ArgazkiakIgo();
         argazkiakIgo.eraikiFrame();
    }
    
    
}
