package com.hrant.utils;

import java.io.File;
import java.net.URL;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ConstantsAndMethods {

	public static Pattern REGEX_COORDINATES = Pattern.compile("x=(\\d+),y=(\\d+)");
	public static String winTempDir = "C://LineTemp";
	public static String macTempDir = System.getProperty("user.home") + File.separator + "Documents" + File.separator
			+ "LineTemp";
	public static Pattern REGEX_MATCHING_DATE = Pattern.compile("(\\d+/\\d+/\\d+)");
	public static volatile boolean flag = false;
	private static final Logger LOGGER = Logger.getLogger(ConstantsAndMethods.class);
	public static final String RECEIVER = "unreplicable";

	public static String detectOS() {

		return System.getProperty("os.name");
	}

	public static URL getFileFromResources(String filePath) {
		// LOGGER.info(filePath);
		URL resourceUrl = null;
		try {

			resourceUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
			// LOGGER.error("hbghn" + resourceUrl);

		} catch (Exception e) {
			LOGGER.error("error with reading path ", e);
		}
		return resourceUrl;
	}

}
