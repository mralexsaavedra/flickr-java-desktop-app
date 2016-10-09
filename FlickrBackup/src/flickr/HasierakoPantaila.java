package flickr;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class HasierakoPantaila extends JPanel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    JLabel picture;
    int width = 800;
    int height = 800;
    
    Vector <String> elementuak = new Vector<String>();
    JPanel jPanel1 = new JPanel();
    JComboBox jComboBox1;
    
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField2 = new JTextField();

    
    public HasierakoPantaila() {
        super(new BorderLayout());     
        
        this.setLayout(null);
        this.setSize(400, 300);
        
        picture = new JLabel(new ImageIcon(getClass().getResource("images/FlickrLogo.jpg")));
        add(picture);
        picture.setSize(width, height);
        picture.setLocation(900,80);
        
        jPanel1.setBounds(new Rectangle(2350, 0, 333, 170));
        elementuak.add("Euskera");
        elementuak.add("Engilsh");
        elementuak.add("Español");
        jComboBox1 = new JComboBox(elementuak);
        jPanel1.add(jComboBox1, null);
        add(jPanel1, null);
        
        //jLabel2.setLayout(new FlowLayout());
        jLabel2.setText("Email");
        jTextField1.setColumns(30);
        jLabel3.setText("Pasahitza");
        jTextField2.setColumns(30);
        add(jLabel2, null);
        add(jLabel2, BorderLayout.SOUTH);
   
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("FlickrBackup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new HasierakoPantaila();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
    	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
