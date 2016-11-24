package taulak_UI;

import javax.swing.ImageIcon;

class ArgazkienLag {
	String argazkia;
	String bilduma;
	ImageIcon irudia;

	public ArgazkienLag(String argazkia, String bilduma, ImageIcon irudia) {
		super();
		this.argazkia = argazkia;
		this.bilduma = bilduma;
		this.irudia = irudia;
	}
	
	public String toString() {
		return "Lag [argazkia=" + argazkia + ", bilduma=" + bilduma + "]";
	}

	public Object getBalioa(int i) {
		Object emaitza = null;
		switch (i) {
		case 0:
			emaitza = this.argazkia;
			break;
		case 1:
			emaitza = this.bilduma;
			break;
		case 2:
			emaitza = this.irudia;
			break;
		default:
			break;
		}
		
		return emaitza;
	}

	public void insertElementAt(Object value, int i){
		switch (i) {
		case 0:
			this.argazkia = (String) value;
			break;
		case 1:
			this.bilduma = (String) value;
			break;
		case 2:
			this.irudia = (ImageIcon) value;
			break;
		default:
			break;
		}
	}
}