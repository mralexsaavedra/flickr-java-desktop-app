package flickrAuth;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.util.IOUtilities;

import sesioPantailak_UI.SesioaHasiPantaila;


public class Auth_UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel tituluPanela = new JPanel();
	private JLabel ongiEtorriLabel = new JLabel("Ongi etorri");
	private JLabel flickrLabel = new JLabel("FlickrBackup");
	
	private JPanel pausuPanela = new JPanel();
	private JScrollPane scrollPanel;
	private JTextArea textArea;
	
	private JPanel behekoPanela = new JPanel();
	private JButton sortuBotoia = new JButton("setup.properties sortu");
	private JButton kodeaBotoia = new JButton("Kodea");
	private JButton hurrengoaBotoia = new JButton("Hurrengoa");
	
	public Auth_UI(){
		this.tituluaeraiki();
		this.pausuenPanelaEraiki();
		this.behekoPanelaEraiki();
	}
	
	private void tituluaeraiki(){
		tituluPanela.setLayout(new FlowLayout());
		ongiEtorriLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		flickrLabel.setFont(new Font("Calibri", Font.ITALIC, 38));
		tituluPanela.add(ongiEtorriLabel);
		tituluPanela.add(flickrLabel);
		tituluPanela.setBorder(BorderFactory.createEmptyBorder(30, 100, 50, 100));
		this.getContentPane().add(tituluPanela, BorderLayout.NORTH);
	}
	
	private void behekoPanelaEraiki(){
		behekoPanela.add(sortuBotoia);
		behekoPanela.add(kodeaBotoia);
		behekoPanela.add(hurrengoaBotoia);
		this.getContentPane().add(behekoPanela, BorderLayout.SOUTH);
		sortuBotoia.addActionListener(actionListener -> this.fitxategiaSortu());
		kodeaBotoia.addActionListener(actionListener -> {
			try {
				kodeaLortu();
			} catch (IOException | FlickrException e) {
				e.printStackTrace();
			}
		});
		hurrengoaBotoia.addActionListener(actionListener -> this.hurrengoa());
	}
	
	private void pausuenPanelaEraiki(){
		String text = "Jarraitu behar dituzun pausuak hasi baino lehen:\n"
				+ "\n"
				+ "1-. Zure flickr kontura joan\n"
				+ "2-. Ajustes - Compartiendo y extendiendo\n"
				+ "3-. API bat sortu\n"
				+ "4-. Fitxategi bat sortu: setup.properties (behean daukazu)\n"
				+ "5-. Ireki setup.properties eta apiKey, email eta secret ipini \n"
				+ "6-. Kodea botoiari eman\n"
				+ "7-. URL kopiatu nabigatzailean eta kodea kopiatu\n";
			
		textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setPreferredSize(new Dimension(450,200));
		scrollPanel = new JScrollPane(textArea);
		pausuPanela.add(scrollPanel);
		this.getContentPane().add(pausuPanela, BorderLayout.CENTER);
	}
	
	private void fitxategiaSortu(){
		Fitxategia fitxategia = new Fitxategia();
		fitxategia.fitxategiaSortu();
	}
	
	private void kodeaLortu() throws IOException, FlickrException{		
		Properties properties;
        InputStream in = null;
        try {
            in = AuthLortu.class.getResourceAsStream("/setup.properties");
            properties = new Properties();
            properties.load(in);
        } finally {
            IOUtilities.close(in);
        }

        Flickr flickr = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
        Flickr.debugStream = false;
        AuthInterface authInterface = flickr.getAuthInterface();

        //Scanner scanner = new Scanner(System.in);

        Token token = authInterface.getRequestToken();
        System.out.println("token: " + token);

        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
		JOptionPane.showInputDialog("URL", url);
        System.out.println("Follow this URL to authorise yourself on Flickr");
        System.out.println(url);
        System.out.println("Paste in the token it gives you:");
        System.out.print(">>");

        //String tokenKey = scanner.nextLine();
        //scanner.close();
        
		String respuesta = JOptionPane.showInputDialog("Kodea idatzi");
        
        Token requestToken = authInterface.getAccessToken(token, new Verifier(respuesta));
        System.out.println("Authentication success");

        Auth auth = authInterface.checkToken(requestToken);

        // This token can be used until the user revokes it.
        System.out.println("Token: " + requestToken.getToken());
        System.out.println("Token Secret: " + requestToken.getSecret());
        System.out.println("nsid: " + auth.getUser().getId());
        System.out.println("Realname: " + auth.getUser().getRealName());
        System.out.println("Username: " + auth.getUser().getUsername());
        System.out.println("Permission: " + auth.getPermission().getType());
        
        String datuak =  "8-. Pantailan agertzen zaizkizun datuak lehen sortutako setup.properties fitxategian kopiatu\n\n\n" +
        								"Token: " + requestToken.getToken() +
        								"\nToken Secret: " + requestToken.getSecret() + 
        								"\nnsid: " + auth.getUser().getId() +
       								"\nRealname: " + auth.getUser().getRealName() +
       								"\nUsername: " + auth.getUser().getUsername() +
       								"\nPermission: " + auth.getPermission().getType() +
       								"\n\n" +
       								"Dena amaitu ostean, Hurrengoa botoiari eman";
        textArea.setText(datuak);
        scrollPanel.repaint();	
	}
	
	private void hurrengoa(){
		this.dispose();
		SesioaHasiPantaila sesioa = new SesioaHasiPantaila();
		sesioa.panelaEraikitzen();
	}
	
	public void eraiki() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Auth_UI ui = new Auth_UI();
		ui.eraiki();
	}
	
	
}
