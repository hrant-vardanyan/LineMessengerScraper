package com.hrant.scraper;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.File;

import org.apache.log4j.Logger;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import com.hrant.utils.ConstantsAndMethods;

public class MacScraper {

	private static final Logger LOGGER = Logger.getLogger(MacScraper.class);
	private static final int DELTA = 52;

	private ScreenRegion desktop;
	private Mouse mouse;
	private Keyboard kb;
	private double sec = 0.5;

	public MacScraper() {

		this.desktop = new DesktopScreenRegion();
		this.mouse = new DesktopMouse();
		this.kb = new DesktopKeyboard();
	}

	// public static void main(String[] args) {
	// try {
	// MacScraper macScraper = new MacScraper();
	// macScraper.downloadTextDocs();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// ConstantsAndMethods.deleteTempFolder(new File(
	// ConstantsAndMethods.macTempDir));
	// }
	//
	// // System.out.println(System.getProperty("user.home"));
	// }

	public void downloadTextDocs() {
		ConstantsAndMethods.createTempFolder(new File(ConstantsAndMethods.macTempDir));
//		try {
//			Files.createDirectories(Paths.get(ConstantsAndMethods.macTempDir));
//		} catch (IOException e) {
//			LOGGER.error("Error trying create mac folder", e);
//		}
		Target targetIcon = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir
				+ "icon.PNG"));
		ScreenRegion iconOnTaskbar = desktop.find(targetIcon);
		if (iconOnTaskbar != null) {
			mouse.click(iconOnTaskbar.getCenter());

		}
		// ConstantsAndMethods.delaySec(sec);
		// Target targetMax = new
		// ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin +
		// "maximize.PNG"));
		// ScreenRegion max = desktop.find(targetMax);
		// if (max != null) {
		// mouse.click(max.getUpperRightCorner());
		// }
		ConstantsAndMethods.delaySec(sec);
		Target targetScrollDown = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getCenter());
			kb.keyDown(KeyEvent.VK_CONTROL);
			mouse.wheel(-1, 70);
			kb.keyUp(KeyEvent.VK_CONTROL);
		}
		ConstantsAndMethods.delaySec(sec);
		Target targetScrollDowned = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "scrollDowned.PNG"));
		ScreenRegion scrollDowned = desktop.find(targetScrollDowned);

		int y = scrollDowned.getBounds().y + 25;
		int x = scrollDowned.getBounds().x - 38;
		ConstantsAndMethods.delaySec(sec);
		doScrollAndClick(x, y);

	}

	private void doScrollAndClick(int x, int y) {
		Target targetFriends = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "friends.PNG"));
		clickOnTenFriends(x, y, 10);
		ConstantsAndMethods.delaySec(sec);
		Target targetScrollDown = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getCenter());
		}
		boolean friendsIconFound = false;
		while (true) {
			int i = 0;
			for (; i < 10; i++) {
				System.out.println("mouse up to 1");
				mouse.wheel(1, 5);
				if (desktop.find(targetFriends) != null) {
					friendsIconFound = true;
					break;
				}
			}
			ConstantsAndMethods.delaySec(sec);
			// System.out.println(i);
			clickOnTenFriends(x, y, i);

			if (friendsIconFound) {
				return;
			}
		}

	}

	private void clickOnTenFriends(int x, int y, int scrolCount) {
		y = (int) (y - (((10 - scrolCount) * DELTA) * 0.9));
		try {
			for (int i = 1; i <= scrolCount; i++) {
				downloadTextFileForEachFriend(x, y);
				y = y - DELTA;
				ConstantsAndMethods.delaySec(sec);
			}
		} catch (AWTException e) {
			LOGGER.error("error with clicking on friends ", e);
		}
	}

	private void downloadTextFileForEachFriend(int x, int y) throws AWTException {

		ConstantsAndMethods.click(x, y);
		ConstantsAndMethods.delaySec(1.5);
		Target targetAm = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir
				+ "am.PNG"));
		ScreenRegion am = desktop.find(targetAm);
		Target targetPm = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir
				+ "pm.PNG"));
		ScreenRegion pm = desktop.find(targetPm);
		if (am != null || pm != null) {
			Target targetScrollUp = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "scrollUp.PNG"));
			ScreenRegion scrollUp = desktop.find(targetScrollUp);
			if (scrollUp != null) {
				mouse.click(scrollUp.getUpperLeftCorner());
				kb.keyDown(KeyEvent.VK_CONTROL);
				mouse.wheel(1, 85);
				kb.keyUp(KeyEvent.VK_CONTROL);
			}
			ConstantsAndMethods.delaySec(sec);

			Target targetPreviousChats = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "previousChats.PNG"));
			ScreenRegion previousChats = desktop.find(targetPreviousChats);
			while (previousChats != null) {
				mouse.click(previousChats.getCenter());
				ConstantsAndMethods.delaySec(1.5);
				previousChats = desktop.find(targetPreviousChats);
			}

			ConstantsAndMethods.delaySec(sec);
			Target targetMenu = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "menu.PNG"));
			ScreenRegion menu = desktop.find(targetMenu);
			if (menu != null) {
				mouse.click(menu.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetSaveMessages = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "saveMessages.PNG"));
			ScreenRegion saveMessages = desktop.find(targetSaveMessages);
			if (saveMessages != null) {
				mouse.click(saveMessages.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetOk = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir
					+ "ok.PNG"));
			ScreenRegion ok = desktop.find(targetOk);
			if (ok != null) {
				mouse.click(ok.getCenter());
			}
			ConstantsAndMethods.delaySec(0.8);
			Target targetMaxWin = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "maxWindow.PNG"));
			ScreenRegion maxWin = desktop.find(targetMaxWin);
			if (maxWin == null) {
				Target targetMaxSaveWin = new ImageTarget(
						ConstantsAndMethods
								.getFileFromResources(ConstantsAndMethods.macImagesDir + "maxSaveWindow.PNG"));
				ScreenRegion maxSaveWin = desktop.find(targetMaxSaveWin);
				if (maxSaveWin != null) {
					mouse.click(maxSaveWin.getCenter());
				}
			}
			ConstantsAndMethods.delaySec(sec);
			Target Targetdocuments = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "documents.PNG"));
			ScreenRegion documents = desktop.find(Targetdocuments);
			if (documents != null) {
				mouse.click(documents.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetSearch = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "search.PNG"));
			ScreenRegion search = desktop.find(targetSearch);
			if (search != null) {
				mouse.click(search.getCenter());
				kb.type("LineTemp");
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetTempFolder = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "tempFolder.PNG"));
			ScreenRegion tempFolder = desktop.find(targetTempFolder);
			if (tempFolder != null) {
				mouse.click(tempFolder.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetSave = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir + "save.PNG"));
			ScreenRegion save = desktop.find(targetSave);
			if (save != null) {
				mouse.click(save.getCenter());
			}
		}
		ConstantsAndMethods.delaySec(sec);
		Target targetClose = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.macImagesDir
				+ "close.PNG"));
		ScreenRegion close = desktop.find(targetClose);
		if (close != null) {
			mouse.click(close.getCenter());
		}

	}

}
