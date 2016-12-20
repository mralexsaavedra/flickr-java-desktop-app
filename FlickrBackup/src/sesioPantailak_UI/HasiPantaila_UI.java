package sesioPantailak_UI;


import javax.swing.JOptionPane;

import flickrAuth.Auth_UI;

public class HasiPantaila_UI{

	public HasiPantaila_UI(){
		int erantzuna = JOptionPane.showConfirmDialog(null, "setup.properties eginda daukazu?", "Hasi baino lehen...", JOptionPane.YES_NO_OPTION);
		if (erantzuna==0){
			SesioaHasiPantaila sesioa = new SesioaHasiPantaila();
			sesioa.panelaEraikitzen();
		}
		else{
			Auth_UI auth = new Auth_UI();
			auth.eraiki();
		}
	}
	
	public static void main(String[] args) {
		new HasiPantaila_UI();
	}
	
}
