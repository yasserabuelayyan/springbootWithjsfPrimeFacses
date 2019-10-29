package de.fred4jupiter.spring.boot.jsf.utile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringBootJSF {

	
	public static String dateToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formatedDate = sdf.format(date);

		return formatedDate;

	}
}
