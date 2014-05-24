package com.hrant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFromTxtFile {
	
//	public static void main(String[] args) throws IOException {
//		ReadingFromTxtFile file = new ReadingFromTxtFile();
//		file.getDataFromTxtFile();
//	}
	
	
	public void getDataFromTxtFile() throws IOException{
		
		BufferedReader in = new BufferedReader(new FileReader("C:\\LineTemp\\[LINE]OKpanda カスタマーサポート.txt"));
		String line;
		while((line = in.readLine()) != null)
		{
		    System.out.println(line);
		}
		in.close();
		
		
	}

}
