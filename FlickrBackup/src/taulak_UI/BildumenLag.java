package taulak_UI;

class BildumenLag {
	String titulua;
	String deskripzioa;
	int argazkiKop;

	public BildumenLag(String titulua, String deskripzioa, int argazkiKop) {
		super();
		this.titulua = titulua;
		this.deskripzioa = deskripzioa;
		this.argazkiKop = argazkiKop;
	}

	public String toString() {
		return "Lag [titulua=" + titulua + ", deskripzioa=" + deskripzioa + ", argazkiKop=" + argazkiKop + "]";
	}

	public Object getBalioa(int i) {
		Object emaitza = null;
		switch (i) {
		case 0:
			emaitza = this.titulua;
			break;
		case 1:
			emaitza = this.deskripzioa;
			break;
		case 2:
			emaitza = this.argazkiKop;
			break;
		default:
			break;
		}
		
		return emaitza;
	}

	public void insertElementAt(Object value, int i){
		switch (i) {
		case 0:
			this.titulua = (String) value;
			break;
		case 1:
			this.deskripzioa = (String) value;
			break;
		case 2:
			this.argazkiKop = (Integer) value;
			break;
		default:
			break;
		}
	}
}