package taulak_UI;

import javax.swing.ImageIcon;

class ArgazkienLag {
	String argazkia;
	String bilduma;
	ImageIcon irudia;
	String deskripzioa;
	String dateAdded;
	String datePosted;
	String dateTaken;
	String geoData;
	String tag;

	public ArgazkienLag(ImageIcon irudia, String argazkia, String bilduma, String desk, String added, String posted, String taken, String geo, String tag) {
		super();
		this.argazkia = argazkia;
		this.bilduma = bilduma;
		this.irudia = irudia;
		this.deskripzioa = desk;
		this.dateAdded = added;
		this.datePosted = posted;
		this.dateTaken = taken;
		this.geoData = geo;
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "ArgazkienLag [argazkia=" + argazkia + ", bilduma=" + bilduma + ", deskripzioa=" + deskripzioa
				+ ", dateAdded=" + dateAdded + ", datePosted=" + datePosted + ", dateTaken=" + dateTaken + ", geoData="
				+ geoData + ", tag=" + tag + "]";
	}

	public Object getBalioa(int i) {
		Object emaitza = null;
		switch (i) {
		case 0:
			emaitza = this.irudia;
			break;
		case 1:
			emaitza = this.argazkia;
			break;
		case 2:
			emaitza = this.bilduma;
			break;
		case 3:
			emaitza = this.deskripzioa;
			break;
		case 4:
			emaitza = this.dateAdded;
			break;
		case 5:
			emaitza =this.datePosted;
			break;
		case 6:
			emaitza = this.dateTaken;
			break;
		case 7:
			emaitza = this.geoData;
			break;
		case 8:
			emaitza = this.tag;
		default:
			break;
		}
		
		return emaitza;
	}

	public void insertElementAt(Object value, int i){
		switch (i) {
		case 0:
			this.irudia = (ImageIcon) value;
			break;
		case 1:
			this.argazkia = (String) value;
			break;
		case 2:
			this.bilduma = (String) value;
			break;
		case 3:
			this.deskripzioa = (String) value;
			break;
		case 4:
			this.dateAdded = (String) value;
			break;
		case 5:
			this.datePosted = (String) value;
			break;
		case 6:
			this.dateTaken = (String) value;
			break;
		case 7:
			this.geoData = (String) value;
			break;
		case 8:
			this.tag = (String) value;
			break;
		default:
			break;
		}
	}
}