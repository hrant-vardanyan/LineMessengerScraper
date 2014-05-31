package com.hrant.scraper;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.io.File;

import org.apache.log4j.Logger;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.KeyModifier;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import com.hrant.utils.ConstantsAndMethods;

public class WinScraper {

	private static final int DELTA = 52;

	private static final Logger LOGGER = Logger.getLogger(WinScraper.class);

	private ScreenRegion desktop;
	private Mouse mouse;
	private Keyboard kb;
	private double sec = 0.5;

	public WinScraper() {

		this.desktop = new DesktopScreenRegion();
		this.mouse = new DesktopMouse();
		this.kb = new DesktopKeyboard();
	}

	// public static void main(String[] args) {
	//
	// try {
	// WinScraper winScraper = new WinScraper();
	// winScraper.downloadTextDocs();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// ConstantsAndMethods.deleteTempFolder(new
	// File(ConstantsAndMethods.winTempDir));
	// }
	//
	// }

	public void downloadTextDocs() {
		ConstantsAndMethods.createTempFolder(new File(ConstantsAndMethods.winTempDir));
		Target targetIcon = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
				+ "icon.PNG"));
		ScreenRegion iconOnTaskbar = desktop.find(targetIcon);
		if (iconOnTaskbar != null) {
			mouse.click(iconOnTaskbar.getCenter());

		}
		ConstantsAndMethods.delaySec(sec);
		Target targetMax = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
				+ "maximize.PNG"));
		ScreenRegion max = desktop.find(targetMax);
		if (max != null) {
			mouse.click(max.getCenter());
		}
		ConstantsAndMethods.delaySec(sec);
		Target targetScrollDown = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getUpperLeftCorner());
			kb.keyDown(KeyEvent.VK_CONTROL);
			mouse.wheel(1, 30);
			kb.keyUp(KeyEvent.VK_CONTROL);
		}
		ConstantsAndMethods.delaySec(sec);
		doScrollAndClick();

	}

	private void doScrollAndClick() {
		if (ConstantsAndMethods.flag) {
			return;
		}
		Target targetFriends = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "friends.PNG"));
		clickOnTenFriends(11);
		ConstantsAndMethods.delaySec(sec);
		Target targetScrollDown = new ImageTarget(
				ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "scrollDown.PNG"));
		ScreenRegion scrollDown = desktop.find(targetScrollDown);
		if (scrollDown != null) {
			mouse.click(scrollDown.getUpperLeftCorner());
		}
		boolean friendsIconFound = false;
		while (true) {
			int i = 0;
			for (; i < 11; i++) {
				// System.out.println("mouse up to 1");
				mouse.wheel(-1, 1);
				if (desktop.find(targetFriends) != null) {
					friendsIconFound = true;
					break;
				}
			}
			ConstantsAndMethods.delaySec(sec);
			System.out.println(i);
			clickOnTenFriends(i + 1);

			if (friendsIconFound) {
				return;
			}
		}

	}

	private void clickOnTenFriends(int scrolCount) {
		int y = (int) (683 - (((11 - scrolCount) * DELTA) * 0.8));
		try {
			if (ConstantsAndMethods.flag) {
				return;
			}
			for (int i = 1; i <= scrolCount; i++) {
				
				downloadTextFileForEachFriend(706, y);
				y = y - DELTA;
				ConstantsAndMethods.delaySec(sec);
			}
		} catch (AWTException e) {
			LOGGER.error("error with clicking on friends ", e);
		}
	}

	private void downloadTextFileForEachFriend(int x, int y) throws AWTException {
		
		ConstantsAndMethods.click(x, y);
		ConstantsAndMethods.delaySec(2);
		Target targetAm = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
				+ "am.PNG"));
		ScreenRegion am = desktop.find(targetAm);
		Target targetPm = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
				+ "pm.PNG"));
		ScreenRegion pm = desktop.find(targetPm);
		if (am != null || pm != null) {
			Target targetScrollUp = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "scrollUp.PNG"));
			ScreenRegion scrollUp = desktop.find(targetScrollUp);
			if (scrollUp != null) {
				mouse.click(scrollUp.getLowerLeftCorner());
				kb.keyDown(KeyEvent.VK_CONTROL);
				mouse.wheel(-1, 25);
				kb.keyUp(KeyEvent.VK_CONTROL);
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetPreviousChats = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "previousChats.PNG"));
			ScreenRegion previousChats = desktop.find(targetPreviousChats);

			while (previousChats != null) {
				mouse.click(previousChats.getCenter());
				ConstantsAndMethods.delaySec(1);
				previousChats = desktop.find(targetPreviousChats);
			}

			ConstantsAndMethods.delaySec(sec);
			Target targetMenu = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "menu.PNG"));
			ScreenRegion menu = desktop.find(targetMenu);
			if (menu != null) {
				mouse.click(menu.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetSaveMessages = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "saveMessages.PNG"));
			ScreenRegion saveMessages = desktop.find(targetSaveMessages);
			if (saveMessages != null) {
				mouse.click(saveMessages.getCenter());
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetOk = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
					+ "ok.PNG"));
			ScreenRegion ok = desktop.find(targetOk);
			if (ok != null) {
				mouse.click(ok.getCenter());
			}
			ConstantsAndMethods.delaySec(1);
			Target targetSavePath = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "savePath.PNG"));
			
			ScreenRegion savePath = desktop.find(targetSavePath);
			if (savePath != null) {
				mouse.click(savePath.getCenter());
				kb.type("C:\\LineTemp");
			}else{
				Target targetSavePathWin7 = new ImageTarget(
						ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "savePathWin7.PNG"));
				
				ScreenRegion savePathWin7 = desktop.find(targetSavePathWin7);
				if (savePathWin7 != null) {
					mouse.click(savePathWin7.getCenter());
					kb.type("C:\\LineTemp");
				}
			}
			// ConstantsAndMethods.delaySec(sec);
			Target targetGoTo = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "goToCurrentPath.PNG"));
			ScreenRegion goTo = desktop.find(targetGoTo);
			if (goTo != null) {
				mouse.click(goTo.getCenter());
			}else{
				Target targetGoToWin7 = new ImageTarget(
						ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "goToCurrentPathWin7.PNG"));
				ScreenRegion goToWin7 = desktop.find(targetGoToWin7);
				if (goToWin7 != null) {
					mouse.click(goToWin7.getCenter());
				}
			}
			ConstantsAndMethods.delaySec(sec);
			Target targetSave = new ImageTarget(
					ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir + "save.PNG"));
			ScreenRegion save = desktop.find(targetSave);
			if (save != null) {
				mouse.click(save.getCenter());

			}
			ConstantsAndMethods.delaySec(sec);
		}
		Target targetClose = new ImageTarget(ConstantsAndMethods.getFileFromResources(ConstantsAndMethods.winImagesDir
				+ "close.PNG"));
		ScreenRegion close = desktop.find(targetClose);
		if (close != null) {
			mouse.click(close.getUpperLeftCorner());

		}
		ConstantsAndMethods.delaySec(sec);
	}
}
