package com.hrant.scraper;

import java.awt.AWTException;
import java.awt.Rectangle;

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

	public static void main(String[] args) {

		MacScraper scraper = new MacScraper();
		scraper.downloadingTextDocs(ConstantsAndMethods.macImagesDir);
		// ConstantsAndMethods.delaySec(2);
		// downloadTextFileForEachFriend(ConstantsAndMethods.getFileFromResources("images/user1.PNG").toString());

	}

	public void downloadingTextDocs(String macOrWin) {

		ScreenRegion desktop = new DesktopScreenRegion();
		Target targetIcon = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin + "icon.PNG"));
		ScreenRegion iconOnTaskbar = desktop.find(targetIcon);
		Mouse mouse = new DesktopMouse();
		if (iconOnTaskbar != null) {
			mouse.click(iconOnTaskbar.getCenter());

		}
		// ConstantsAndMethods.delaySec(1);
		// Target targetMax = new
		// ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin +
		// "maximize.PNG"));
		// ScreenRegion max = desktop.find(targetMax);
		// if (max != null) {
		// mouse.click(max.getUpperRightCorner());
		// }
		ConstantsAndMethods.delaySec(1);
		Target targetScrollDown = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getCenter());
			mouse.wheel(-1, 70);
		}
		ConstantsAndMethods.delaySec(1);
		Target targetScrollDowned = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "scrollDowned.PNG"));
		ScreenRegion scrollDowned = desktop.find(targetScrollDowned);

		int y = scrollDowned.getBounds().y + 25;
		try {
			for (int i = 1; i <= 8; i++) {
				downloadTextFileForEachFriend(scrollDowned.getBounds().x - 38,
						y, macOrWin);
				y = y - 45;
				ConstantsAndMethods.delaySec(1);

			}
		} catch (AWTException e) {

			LOGGER.error("error with clicking on friends ", e);
		}

	}

	private static void downloadTextFileForEachFriend(int x, int y,
			String macOrWin) throws AWTException {

		ScreenRegion desktop = new DesktopScreenRegion();
		Keyboard kb = new DesktopKeyboard();
		Mouse mouse = new DesktopMouse();
		ConstantsAndMethods.click(x, y);
		ConstantsAndMethods.delaySec(4);
		Target targetScrollUp = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "scrollUp.PNG"));
		ScreenRegion scrollUp = desktop.find(targetScrollUp);
		if (scrollUp != null) {
			mouse.click(scrollUp.getUpperRightCorner());
			mouse.wheel(1, 90);
		}
		ConstantsAndMethods.delaySec(2);

		Target targetPreviousChats = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "previousChats.PNG"));
		ScreenRegion previousChats = desktop.find(targetPreviousChats);
		while (previousChats != null) {
			mouse.click(previousChats.getCenter());
			ConstantsAndMethods.delaySec(2);
			previousChats = desktop.find(targetPreviousChats);
		}

		ConstantsAndMethods.delaySec(1);
		Target targetMenu = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin + "menu.PNG"));
		ScreenRegion menu = desktop.find(targetMenu);
		if (menu != null) {
			mouse.click(menu.getCenter());
		}
		ConstantsAndMethods.delaySec(2);
		Target targetSaveMessages = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "saveMessages.PNG"));
		ScreenRegion saveMessages = desktop.find(targetSaveMessages);
		if (saveMessages != null) {
			mouse.click(saveMessages.getCenter());
		}
		ConstantsAndMethods.delaySec(2);
		Target targetOk = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin + "ok.PNG"));
		ScreenRegion ok = desktop.find(targetOk);
		if (ok != null) {
			mouse.click(ok.getCenter());
		}
		ConstantsAndMethods.delaySec(2);
		Target targetMaxWin = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "maxWindow.PNG"));
		ScreenRegion maxWin = desktop.find(targetMaxWin);
		if (maxWin == null) {
			Target targetMaxSaveWin = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(macOrWin
							+ "maxSaveWindow.PNG"));
			ScreenRegion maxSaveWin = desktop.find(targetMaxSaveWin);
			if (maxSaveWin != null) {
				mouse.click(maxSaveWin.getCenter());
			}
		}
		ConstantsAndMethods.delaySec(1);
		Target Targetdocuments = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "documents.PNG"));
		ScreenRegion documents = desktop.find(Targetdocuments);
		if (documents != null) {
			mouse.click(documents.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSearch = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "search.PNG"));
		ScreenRegion search = desktop.find(targetSearch);
		if (search != null) {
			mouse.click(search.getCenter());
			kb.type("LineTemp");
		}
		ConstantsAndMethods.delaySec(2);
		Target targetTempFolder = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin
						+ "tempFolder.PNG"));
		ScreenRegion tempFolder = desktop.find(targetTempFolder);
		if (tempFolder != null) {
			mouse.click(tempFolder.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSave = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin + "save.PNG"));
		ScreenRegion save = desktop.find(targetSave);
		if (save != null) {
			mouse.click(save.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetClose = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(macOrWin + "close.PNG"));
		ScreenRegion close = desktop.find(targetClose);
		if (close != null) {
			mouse.click(close.getCenter());
		}

	}

}
