package com.hrant;

import java.io.File;

import org.apache.log4j.Logger;

import com.hrant.scraper.MacScraper;
import com.hrant.scraper.WinScraper;
import com.hrant.utils.ConstantsAndMethods;

public class Start {
	private static final Logger LOGGER = Logger.getLogger(Start.class);
	public String path;

	public void startScraping(String timeZone, String pemPath) {
		 
		String detectOs = ConstantsAndMethods.detectOS();
		if (detectOs.contains("Mac")) {
			try {
				this.path = ConstantsAndMethods.macTempDir;
				if (ConstantsAndMethods.flag) {
					return;
				}
				MacScraper macScraper = new MacScraper();
				macScraper.downloadTextDocs();
			} catch (Exception e) {
				LOGGER.error("error with auto mac ", e);
				;
			}
		}
		
		if (detectOs.contains("Windows")) {
			try {
				this.path = ConstantsAndMethods.winTempDir;
				if (ConstantsAndMethods.flag) {
					return;
				}
				WinScraper winScraper = new WinScraper();
				winScraper.downloadTextDocs();
			} catch (Exception e) {
				LOGGER.error("error with auto win ", e);
			}
		}
		ConnectToDB.ssh(pemPath);
		try {
		
			ReadingFromTxtFile file = new ReadingFromTxtFile(new File(path), timeZone);
			file.writeForAllFiles();
		} catch (Exception e) {
			LOGGER.error("error with after auto... part . ", e);
		} finally {
			ConstantsAndMethods.deleteTempFolder(new File(this.path));
		}

	}

}
