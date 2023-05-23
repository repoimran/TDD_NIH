package util;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

	Properties prop;
	FileInputStream input = null;

	public Configuration() {
		loadProperties();
	}

//	private void loadProperties() {
//		try {
//			properties = new Properties();
//			InputStream iStream = getClass().getClassLoader().getResourceAsStream("configuration.properties");
//			properties.load(iStream);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}

	private void loadProperties() {
		try {
			prop = new Properties();
			input = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/configuration.properties");
			prop.load(input);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public String readProp(String key) {
		return prop.getProperty(key);
	}

	public int readPropNum(String key) {
		return Integer.parseInt(readProp(key));
	}

}
