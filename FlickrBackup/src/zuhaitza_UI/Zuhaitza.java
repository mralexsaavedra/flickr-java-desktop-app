package zuhaitza_UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.imgscalr.Scalr;

import kudeatzaileak.Kudeatzailea;
import pantailak_UI.Argazki;

public class Zuhaitza extends JPanel implements TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String erabiltzaile;
	private JPanel argazkiPanela;
	private JTree tree;
	private static boolean DEBUG = false;

	private static boolean playWithLineStyle = false;
	private static String lineStyle = "Horizontal";

	//private static boolean useSystemLookAndFeel = false;

	public Zuhaitza(String email) {
		super(new GridLayout(1, 0));
		this.erabiltzaile = email;
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Flickr");
		createNodes(top);

		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.addTreeSelectionListener(this);

		if (playWithLineStyle) {
			System.out.println("line style = " + lineStyle);
			tree.putClientProperty("JTree.lineStyle", lineStyle);
		}

		JScrollPane treeView = new JScrollPane(tree);

		argazkiPanela = new JPanel();
		JScrollPane argazkiView = new JScrollPane(argazkiPanela);

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

		Object nodeInfo =  node.getUserObject();
		
		System.out.println(nodeInfo);
		
		if (node.isLeaf()) {
			argazkiPanela.removeAll();
			String file = Kudeatzailea.getInstantzia().getArgazkiFile(nodeInfo.toString());
			Argazki argazki = new Argazki(argazkiPanela, file);
			argazkiPanela.add(argazki);
			argazkiPanela.repaint();
		}
		else{
			argazkiPanela.removeAll();
			List<String>  emaitza = Kudeatzailea.getInstantzia().getBildumaFile(nodeInfo.toString(),erabiltzaile);
			for (int i = 0; i<emaitza.size(); i++){
				File f = new File(emaitza.get(i));
				try {
					BufferedImage img = ImageIO.read(f);
					BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED,  Scalr.Mode.FIT_TO_WIDTH, 150, 100, Scalr.OP_ANTIALIAS);
					argazkiPanela.setLayout(new GridLayout(5,5));
					argazkiPanela.add(new JLabel(new ImageIcon(thumbnail)));
					argazkiPanela.validate();
					argazkiPanela.repaint();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (DEBUG) {
			System.out.println(nodeInfo.toString());
		}
	}

	private void createNodes(DefaultMutableTreeNode top) {
		List<String[]> bildumak = Kudeatzailea.getInstantzia().getBildumak(erabiltzaile);
		for (String[] bilduma : bildumak) {
			DefaultMutableTreeNode bildumaTreeNode = new DefaultMutableTreeNode(bilduma[0]);
			top.add(bildumaTreeNode);
			List<String[]> argazkiak = Kudeatzailea.getInstantzia().getBildumaBatenArgazkia(bilduma[0]);
			for (String[] argazkia : argazkiak){
				bildumaTreeNode.add(new DefaultMutableTreeNode(argazkia[0]));
			}
		}
	}
	
}
