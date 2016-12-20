package sesioPantailak_UI;


import javax.swing.JOptionPane;

import flickrAuth.Auth_UI;

public class HasiPantaila_UI{

	public HasiPantaila_UI(){
		int erantzuna = JOptionPane.showConfirmDialog(null, "Hasi baino lehen... setup.properties eginda daukazu?");
		if (erantzuna==0){
			SesioaHasiPantaila sesioa = new SesioaHasiPantaila();
			sesioa.panelaEraikitzen();
		}
		else if (erantzuna==1){
			Auth_UI auth = new Auth_UI();
			auth.eraiki();
		}
		else
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new HasiPantaila_UI();
	}
	
}
