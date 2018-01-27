package SetsMapper.SetsMapper;

import java.io.File;

import handlers.MapReduceHandler;
import handlers.PropertiesHandler;
import models.PropertiesContext;

public class App {
	
	// Constants mapping
	private static final String PROPERTIES_DIR = "conf";
	private static final String OS_SEPARATOR = File.separator;
	private static final String PROPERTIES_FILENAME = "app.conf";
	
	// Properties keys for "dataset files" in app configuration file
	private static final String PROP_DS_FN_1 = "dataset_filename_1";
	private static final String PROP_DS_FN_2 = "dataset_filename_2";
	private static final String PROP_DS_DIR = "data";
	
	// Contains all app properties
	private static PropertiesContext propertiesContext;

    public static void main( String[] args ) { startApp(); }
    
    private static void startApp() {
    		setAppProperties();
    		mapReduceDatasets();
    }
    
    private static void setAppProperties() {
    		propertiesContext = getPropertiesContext();
    }
    
    private static PropertiesContext getPropertiesContext() {
		PropertiesHandler propertiesHandler = new PropertiesHandler(getPropertiesFileName());    		
		
		return propertiesHandler.getPropertiesContext();
    }
    
    private static String getPropertiesFileName() {
    		StringBuilder propertiesFilename = new StringBuilder();
		
		propertiesFilename.append(PROPERTIES_DIR);
		propertiesFilename.append(OS_SEPARATOR);
		propertiesFilename.append(PROPERTIES_FILENAME);
		
    		return propertiesFilename.toString();
    }
    
    private static void mapReduceDatasets() {
	    	String [] datasetFilenameArray = { 	
	    		PROP_DS_DIR + OS_SEPARATOR + propertiesContext.getProperty(PROP_DS_FN_1),
	    		PROP_DS_DIR + OS_SEPARATOR + propertiesContext.getProperty(PROP_DS_FN_2)
	    	};
    			    	
    		MapReduceHandler mrh = new MapReduceHandler(datasetFilenameArray);
    		
    		mrh.mapReduce();
    		mrh.printResult();
    }
}
