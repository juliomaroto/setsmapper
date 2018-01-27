package handlers;

import models.PropertiesContext;

public final class PropertiesHandler {
	
	// End of file character
	private final String EOF = "EOF";
	private final String SCAPED_DBL_QUOTED_CHAR = "\"";
	
	private FileHandler filehandler;
	private PropertiesContext properties;
	
	// In temporary memory token pointers to speed up line readings
	private int m_tokenStart;
	private int m_tokenEnd;
	
	public PropertiesHandler(String filename) {
		this.filehandler = new FileHandler(filename);
		this.properties = new PropertiesContext();;
	}
	
	public PropertiesContext getPropertiesContext() {
		String line = filehandler.readLine();	
		
		while (isLineAvailable(line)) {
			String propertyName = getPropertyName(line);
			String propertyValue = getPropertyValue(line);
						
			properties.setProperty(propertyName, propertyValue);
			
			line = filehandler.readLine();
		}
				
		return properties;
	}
		
	private Boolean isLineAvailable(String line) {
		if (false == line.equals(EOF) || -1 != line.indexOf("#"))
			return true;
		else
			return false;
	}
	
	private String getPropertyName(String line) {
		m_tokenStart = 0;
		m_tokenEnd = line.indexOf("=");
		
		line = line.substring(m_tokenStart, m_tokenEnd);
		
		m_tokenStart = m_tokenEnd + 2;
		
		return line;
	}
	
	private String getPropertyValue(String line) {
		m_tokenEnd = line.indexOf(SCAPED_DBL_QUOTED_CHAR, m_tokenStart);

		line = line.substring(m_tokenStart, m_tokenEnd);
		
		return line;
	}
}
