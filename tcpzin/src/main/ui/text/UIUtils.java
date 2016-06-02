package main.ui.text;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UIUtils {


	public static final UIUtils INSTANCE = new UIUtils();
	public static final String PROPERTY_RESOURCE_BUNDLE = "main.resources.globalMessages";
	private final BufferedReader reader;
	private static ResourceBundle bundle;
	
	
	public UIUtils() {
		UIUtils.bundle = ResourceBundle.getBundle(PROPERTY_RESOURCE_BUNDLE);
		this.reader = new BufferedReader(new InputStreamReader(System.in));
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
	
	public Double readDouble(String field) {
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

	public Integer readInteger(String field) {
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

	
	public String readString(String field) {
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
	

	

}
