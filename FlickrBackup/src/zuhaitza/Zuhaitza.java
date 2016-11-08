package zuhaitza;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.flickr4java.flickr.photosets.Photoset;

import flickrJava.Bildumak;

public class Zuhaitza extends JPanel implements TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	private JPanel argazkiPanela;
	private JTree tree;
	private static boolean DEBUG = false;

	// Optionally play with line styles. Possible values are
	// "Angled" (the default), "Horizontal", and "None".
	private static boolean playWithLineStyle = false;
	private static String lineStyle = "Horizontal";

	// Optionally set the look and feel.
	private static boolean useSystemLookAndFeel = false;

	public Zuhaitza() {
		super(new GridLayout(1, 0));

		// Create the nodes.
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Flickr");
		createNodes(top);

		// Create a tree that allows one selection at a time.
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		tree.addTreeSelectionListener(this);

		if (playWithLineStyle) {
			System.out.println("line style = " + lineStyle);
			tree.putClientProperty("JTree.lineStyle", lineStyle);
		}

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);

		argazkiPanela = new JPanel();
		JScrollPane argazkiView = new JScrollPane(argazkiPanela);

		// Add the scroll panes to a split pane.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(argazkiView);

		Dimension minimumSize = new Dimension(100, 50);
		argazkiView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(300);
		splitPane.setPreferredSize(new Dimension(500, 300));

		add(splitPane);
	}

	/** Required by TreeSelectionListener interface. */
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		
		System.out.println(nodeInfo);
		
		if (node.isLeaf()) {
			ArgazkiaInfo argazkiInfo = (ArgazkiaInfo) nodeInfo;
			irakurriArgazkia(argazkiInfo);
		} 
		if (DEBUG) {
			System.out.println(nodeInfo.toString());
		}
	}
	
	public void irakurriArgazkia(ArgazkiaInfo argazkiInfo){
		try {
            img = ImageIO.read(new File(argazkiInfo.azgarkiIzena));
        } catch (IOException ex) {
            System.out.println("No se pudo leer la imagen");
        }
	}
	
	 public void paint(Graphics g){
	        g.drawImage(img, 10, 20,getWidth()-10, getHeight()-10, null);
	 }

	private void createNodes(DefaultMutableTreeNode top) {
		Bildumak t;
		try {
			t = new Bildumak();
			List<String[]> bildumak = t.showPhotosets();
			for (String[] bilduma : bildumak) {
				DefaultMutableTreeNode bildumaTreeNode = new DefaultMutableTreeNode(bilduma[0]);
				top.add(bildumaTreeNode);
				Photoset photoset = t.bildumaLortu(bilduma[0]);
				List<String[]> argazkiak = t.bildumaBatenArgazkiakLortu(photoset);
				for (String[] argazkia : argazkiak){
					bildumaTreeNode.add(new DefaultMutableTreeNode(argazkia[0]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void createAndShowGUI() {
		if (useSystemLookAndFeel) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Couldn't use system look and feel.");
			}
		}

		// Create and set up the window.
		JFrame frame = new JFrame("FlickrBackup");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new Zuhaitza());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
