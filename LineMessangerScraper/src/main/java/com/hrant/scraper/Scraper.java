package com.hrant.scraper;

import java.awt.AWTException;

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

public class Scraper {

	private static final Logger LOGGER = Logger.getLogger(Scraper.class);

	public static void main(String[] args) {

		Scraper scraper = new Scraper();
		scraper.downloadingTextDocs(ConstantsAndMethods.winImagesDir);
		// ConstantsAndMethods.delaySec(2);
		// downloadTextFileForEachFriend(ConstantsAndMethods.getFileFromResources("images/user1.PNG").toString());

	}

	public void downloadingTextDocs(String macOrWin) {

		ScreenRegion desktop = new DesktopScreenRegion();
		Target targetIcon = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "icon.PNG"));
		ScreenRegion iconOnTaskbar = desktop.find(targetIcon);
		Mouse mouse = new DesktopMouse();
		if (iconOnTaskbar != null) {
			mouse.click(iconOnTaskbar.getCenter());

		}
		ConstantsAndMethods.delaySec(1);
		Target targetMax = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "maximize.PNG"));
		ScreenRegion max = desktop.find(targetMax);
		if (max != null) {
			mouse.click(max.getCenter());
		}
		// ConstantsAndMethods.delaySec(1);
		// Target targetChats = new
		// ImageTarget(ConstantsAndMethods.getFileFromResources("WinImages/chats.PNG"));
		// ScreenRegion chats = desktop.find(targetChats);
		// if (chats != null) {
		// mouse.click(chats.getCenter());
		// }
		ConstantsAndMethods.delaySec(1);
		Target targetScrollDown = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getUpperLeftCorner());
			mouse.wheel(1, 30);
		}
		ConstantsAndMethods.delaySec(1);
		int y = 679;
		try {
			for (int i = 1; i <= 8; i++) {
				downloadTextFileForEachFriend(706, y, macOrWin);
				y = y - 45;
				ConstantsAndMethods.delaySec(1);

			}
		} catch (AWTException e) {

			LOGGER.error("error with clicking on friends ", e);
		}

	}

	private static void downloadTextFileForEachFriend(int x, int y, String macOrWin) throws AWTException {

		ScreenRegion desktop = new DesktopScreenRegion();
		Keyboard kb = new DesktopKeyboard();
		Mouse mouse = new DesktopMouse();
		ConstantsAndMethods.click(x, y);
		ConstantsAndMethods.delaySec(2);
		Target targetScrollUp = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "scrollUp.PNG"));
		ScreenRegion scrollUp = desktop.find(targetScrollUp);
		if (scrollUp != null) {
			mouse.click(scrollUp.getLowerLeftCorner());
			mouse.wheel(-1, 25);
		}
		ConstantsAndMethods.delaySec(1);

		Target targetPreviousChats = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin
				+ "previousChats.PNG"));
		ScreenRegion previousChats = desktop.find(targetPreviousChats);

		while (previousChats != null) {
			mouse.click(previousChats.getCenter());
			ConstantsAndMethods.delaySec(1);
			previousChats = desktop.find(targetPreviousChats);
		}

		ConstantsAndMethods.delaySec(1);
		Target targetMenu = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "menu.PNG"));
		ScreenRegion menu = desktop.find(targetMenu);
		if (menu != null) {
			mouse.click(menu.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSaveMessages = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin
				+ "saveMessages.PNG"));
		ScreenRegion saveMessages = desktop.find(targetSaveMessages);
		if (saveMessages != null) {
			mouse.click(saveMessages.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetOk = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "ok.PNG"));
		ScreenRegion ok = desktop.find(targetOk);
		if (ok != null) {
			mouse.click(ok.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSavePath = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "savePath.PNG"));
		ScreenRegion savePath = desktop.find(targetSavePath);
		if (savePath != null) {
			mouse.click(savePath.getCenter());
			kb.type("C:\\LineTemp");
		}
		// ConstantsAndMethods.delaySec(1);
		Target targetGoTo = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "goToCurrentPath.PNG"));
		ScreenRegion goTo = desktop.find(targetGoTo);
		if (goTo != null) {
			mouse.click(goTo.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSave = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "save.PNG"));
		ScreenRegion save = desktop.find(targetSave);
		if (save != null) {
			mouse.click(save.getCenter());

		}
		ConstantsAndMethods.delaySec(1);

		Target targetClose = new ImageTarget(ConstantsAndMethods.getFileFromResources(macOrWin + "close.PNG"));
		ScreenRegion close = desktop.find(targetClose);
		if (close != null) {
			mouse.click(close.getUpperLeftCorner());

		}
		ConstantsAndMethods.delaySec(1);
	}

}
