package handlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class FileHandler {
	
	// End of file character
	private final String EOF = "EOF";
	
	private String filename;
	private BufferedReader br;
	private String bufferedLine;
	
	public FileHandler(String filename) {
		this.filename = filename;
		this.br = getBufferedReader();
	}
	
	private FileReader getFileReader() {
		FileReader fr = null;
		
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return fr;
	}
	
	private BufferedReader getBufferedReader() {
		FileReader fr = getFileReader(); 
		br = new BufferedReader(fr);
		
		return br;
	}
	
	public String readLine() {
		bufferedLine = EOF;
		
		try {
			bufferedLine = br.readLine();

			if (null == bufferedLine)
				bufferedLine = EOF;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bufferedLine;
	}
	
	public Boolean isLineAvailable() {
		if (false == bufferedLine.equals("EOF"))
			return true;
		else
			return false;
	}
	
	public void closeFile() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
