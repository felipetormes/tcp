package main.ui.text;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UIUtils {


	public static final UIUtils INSTANCE = new UIUtils();
	public static final String PROPERTY_RESOURCE_BUNDLE = "main.resources.globalMessages";
	
	
	private static ResourceBundle bundle;
	public static String getText(String key) {
		bundle = ResourceBundle.getBundle(PROPERTY_RESOURCE_BUNDLE);
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
	
	

	

}
