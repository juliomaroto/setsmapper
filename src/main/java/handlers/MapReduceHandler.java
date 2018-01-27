package handlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class MapReduceHandler{
	private final static String COMMA_CHAR = ",";
	private final static String LINE_FEED_CHAR = "\n";
	private final static String TAB_CHAR = "\t";
	
	private LinkedList<FileHandler> fileHandlerLinkedList;
	private String [] datasetFilenames;
	private Boolean firstLine = true;
	private Map<String, Map<String, Integer>> setMap; 
	
	public MapReduceHandler(String [] datasetFilenames) {
		this.datasetFilenames = datasetFilenames;
		this.setMap = new HashMap<String, Map<String, Integer>>();
		fileHandlerLinkedList = new LinkedList<FileHandler>();
		loadFiles();
	}
	
	private void loadFiles() {
		for (int i = 0; i < datasetFilenames.length; i++) {
			FileHandler fh = new FileHandler(datasetFilenames[i]);
			fileHandlerLinkedList.add(fh);
		}
	}
	
	public void mapReduce() {
		int fileHandlerLinkedListLength = fileHandlerLinkedList.size();
		
		for (int i = 0; i < fileHandlerLinkedListLength; i++) {
			FileHandler fh = fileHandlerLinkedList.get(i);
			
			mapReduceDataset(fh);
		}
	}
	
	private void mapReduceDataset(FileHandler fh) {
		String line = fh.readLine();
			
		while (fh.isLineAvailable()) {
			if (false == firstLine) {
				String county;
				
				int tokenStart = 0;
				int tokenEnd = line.indexOf(COMMA_CHAR);
							
				tokenStart = tokenEnd + 1;
				tokenEnd = line.indexOf(COMMA_CHAR, tokenStart);
								
				county = line.substring(tokenStart, tokenEnd);
				setItemToHashMap(county);	
				
				line = fh.readLine();
			} else {
				firstLine = false;
			}
		}
	}
	
	private void setItemToHashMap(String item) {
		Map<String, Integer> itemKeyVal = setMap.get(item);
		
		if (null != itemKeyVal) {
			
			Map<String, Integer> itemOcurrencies = new HashMap<String, Integer>();
			itemOcurrencies.put(item, (itemKeyVal.get(item) + 1));
			
			setMap.put(item, itemOcurrencies);
		} else {
			Map<String, Integer> itemOcurrencies = new HashMap<String, Integer>();
			itemOcurrencies.put(item, 1);
				
			setMap.put(item, itemOcurrencies);
		}
	}
	
	public void printResult() {
		Iterator<Map.Entry<String, Map<String, Integer>>> entries = 
				setMap.entrySet().iterator();
		
		while(entries.hasNext()) {
			Map.Entry<String, Map<String, Integer>> entry = entries.next();
			
			Map<String, Integer> entrySet = entry.getValue();
						
			System.out.println(
					"County: " + entry.getKey() +
					LINE_FEED_CHAR +
					TAB_CHAR +
					"Ocurrences: " + entrySet.get(entry.getKey())
			);
		}

	}
}
