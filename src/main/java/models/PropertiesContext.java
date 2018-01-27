package models;

import java.util.HashMap;
import java.util.Map;

public final class PropertiesContext {
	
	Map<String, String> propertiesMap;
	
	public PropertiesContext() {
		propertiesMap = new HashMap<String, String>();
	}
	
	public String getProperty(String propertyName) {
		return propertiesMap.get(propertyName);
	}
	
	public void setProperty(String propertyName, String propertyValue) {
		propertiesMap.put(propertyName, propertyValue);
	}
}
