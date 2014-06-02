package com.hrant.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ConstantsAndMethods {

	public static Pattern REGEX_COORDINATES = Pattern.compile("x=(\\d+),y=(\\d+)");
	public static String macImagesDir = "MacImages/";
	public static String winImagesDir = "WinImages/";
	public static String winTempDir = "C://LineTemp";
	public static String macTempDir = System.getProperty("user.home") + File.separator + "Documents" + File.separator
			+ "LineTemp";
	public static Pattern REGEX_MATCHING_DATE = Pattern.compile("(\\d+/\\d+/\\d+)");
	public static volatile boolean flag = false;
	private static final Logger LOGGER = Logger.getLogger(ConstantsAndMethods.class);

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

	public static void delaySec(double sec) {
		try {
			TimeUnit.MILLISECONDS.sleep((long) (sec * 1000));
		} catch (InterruptedException e) {
			LOGGER.error("error with delaying ", e);
		}
	}

	public static URL getFileFromResources(String filePath) {
		LOGGER.info(filePath);
		URL resourceUrl = null;
		try {
			
			resourceUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
			LOGGER.error("hbghn" + resourceUrl);

		} catch (Exception e) {
			LOGGER.error("error with reading path ", e);
		}
		return resourceUrl;
	}

	public static void createTempFolder(File dir) {
		dir.mkdirs();

	}

	public static void deleteTempFolder(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File aFile : files) {
					deleteTempFolder(aFile);
				}
			}
			dir.delete();
		} else {
			dir.delete();
		}
	}
}
