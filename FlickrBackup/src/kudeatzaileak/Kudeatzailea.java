package kudeatzaileak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Kudeatzailea {

	private static final Kudeatzailea kudeatzaile = new Kudeatzailea();

	public static Kudeatzailea getInstantzia() {
		return kudeatzaile;
	}

	private Kudeatzailea() {
		// Singleton patroia
	}

}
