package com.hrant.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.regex.Pattern;

public class ConstantsAndMethods {

	public static Pattern REGEX_COORDINATES = Pattern.compile("x=(\\d+),y=(\\d+)");

	public static String detectOS() {

		return System.getProperty("os.name");
	}

	public static void click(int x, int y) throws AWTException {
		Robot bot = new Robot();
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public static void delaySec(int sec){
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
