package com.hrant.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.net.URL;
import java.util.regex.Pattern;

public class ConstantsAndMethods {

	public static Pattern REGEX_COORDINATES = Pattern.compile("x=(\\d+),y=(\\d+)");
	public static String macImagesDir = "MacImages/";
	public static String winImagesDir = "WinImages/";

	public static String detectOS() {

		return System.getProperty("os.name");
	}

	public static void click(int x, int y) throws AWTException {
		Robot bot = new Robot();
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void delaySec(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static File getFileFromResources(String filePath) {
		File file = null;
		try {
			URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
			file = new File(resourceUrl.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void createTempFolder(String directory) {
		new File(directory).mkdirs();

	}

	public static void removeTempFolder(String directory) {
		File file = new File(directory);
		file.delete();
	}
	
	

}
