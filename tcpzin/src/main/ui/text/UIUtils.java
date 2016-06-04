package main.ui.text;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UIUtils {


	public static final UIUtils INSTANCE = new UIUtils();
	public static final String PROPERTY_RESOURCE_BUNDLE = "main.resources.globalMessages";
	private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;
	private static ResourceBundle bundle;


	public UIUtils() {
		UIUtils.bundle = ResourceBundle.getBundle(PROPERTY_RESOURCE_BUNDLE);

	}
	public static String getText(String key) {

		String text = null;
		if (text == null) {
			try {
				text = bundle.getString(key);
			} catch (MissingResourceException exc) {
				text = key;
			}
		}
		return text;
	}

	public static Double readDouble(String field) {
		Double value = null;
		while (value == null) {
			try {
				System.out.print(UIUtils.getText(field) + ": ");
				value = new Double(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println(UIUtils
						.getText("exception.formato.double"));

			} catch (Exception e) {

			}
		}
		return value;
	}

	public static Integer readInteger(String field) {
		Integer value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(UIUtils.getText(field) + ": ");
				value = new Integer(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println(UIUtils
						.getText("exception.formato.int"));

			} catch (Exception e) {

			}
		}
		return value;
	}


	public static String readString(String field) {
		String value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(UIUtils.getText(field) + ": ");
				value = reader.readLine();
			} catch (Exception e) {

			}
		}
		return value;
	}

	public static Object chooseFromList(List<? extends Object> list) {
		int numItems = list.size();

		for (int i = 0; i < numItems; i++) {
			String option = String.valueOf(i + 1) + ". " + list.get(i);
			System.out.println(option);
		}

		int chosen = -1;
		do {
			chosen = readInteger("message.choose.option") - 1;
		} while (chosen < 0 || chosen > numItems);

		return list.get(chosen);
	}
}
