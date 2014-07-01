package com.hrant;

import java.io.File;

import org.apache.log4j.Logger;

import com.hrant.utils.ConstantsAndMethods;

public class Start {
	private static final Logger LOGGER = Logger.getLogger(Start.class);
	public String path;

	public void startScraping(String timeZone, String pemPath) {

		String detectOs = ConstantsAndMethods.detectOS();
		if (detectOs.contains("Mac")) {
			this.path = ConstantsAndMethods.macTempDir;
		}

		if (detectOs.contains("Windows")) {
			this.path = ConstantsAndMethods.winTempDir;
		}
		ConnectToDB.ssh(pemPath);
		try {
			
			ReadingFromTxtFile file = new ReadingFromTxtFile(new File(path), timeZone);
			file.writeForAllFiles();
		} catch (Exception e) {
			LOGGER.error("error with after auto... part . ", e);
		}
	}

}
