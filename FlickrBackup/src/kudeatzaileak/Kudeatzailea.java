package kudeatzaileak;

public class Kudeatzailea {

	private static final Kudeatzailea kudeatzaile = new Kudeatzailea();

	public static Kudeatzailea getInstantzia() {
		return kudeatzaile;
	}

	private Kudeatzailea() {
		// Singleton patroia
	}

}
