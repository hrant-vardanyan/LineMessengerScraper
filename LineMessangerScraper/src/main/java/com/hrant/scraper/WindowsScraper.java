package com.hrant.scraper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Key;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import com.hrant.utils.ConstantsAndMethods;

public class WindowsScraper {

	public static void main(String[] args) {

		WindowsScraper scraper = new WindowsScraper();
		scraper.downloadingTextDocs();

	}

	public static File getFileFromResources(String filePath){
		File file = null;
		try {
			URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
			file = new File(resourceUrl.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public void downloadingTextDocs() {

		ScreenRegion desktop = new DesktopScreenRegion();
		Target targetIcon = new ImageTarget(getFileFromResources("images/icon.PNG"));
		ScreenRegion iconOnTaskbar = desktop.find(targetIcon);
		Mouse mouse = new DesktopMouse();
		if (iconOnTaskbar != null) {
			mouse.click(iconOnTaskbar.getCenter());

		}
		Target targetMax = new ImageTarget(new File("\\images\\maximize.PNG"));
		ScreenRegion max = desktop.find(targetMax);
		if (max != null) {
			mouse.click(max.getCenter());
		}
		ConstantsAndMethods.delaySec(1);

		Target targetChats = new ImageTarget(new File("images\\chats.PNG"));
		ScreenRegion chats = desktop.find(targetChats);
		if (chats != null) {
			mouse.click(chats.getCenter());
		}
		downloadTextFile("images\\user.PNG");

	}

	private static void downloadTextFile(String userRegion) {
		ScreenRegion desktop = new DesktopScreenRegion();
		Keyboard kb = new DesktopKeyboard();
		Target targetUser = new ImageTarget(new File(userRegion));
		ScreenRegion user = desktop.find(targetUser);
		Mouse mouse = new DesktopMouse();
		if (user != null) {
			mouse.doubleClick(user.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetScrollUp = new ImageTarget(new File("images\\scrollUp.PNG"));
		ScreenRegion scrollUp = desktop.find(targetScrollUp);

		if (scrollUp != null) {
			mouse.click(scrollUp.getCenter());
			mouse.wheel(-1, 15);

		}
		Target targetMenu = new ImageTarget(new File("images\\menu.PNG"));
		ScreenRegion menu = desktop.find(targetMenu);
		if (menu != null) {
			mouse.click(menu.getCenter());
		}
		ConstantsAndMethods.delaySec(2);
		Target targetSaveMessages = new ImageTarget(new File("images\\saveMessages.PNG"));
		ScreenRegion saveMessages = desktop.find(targetSaveMessages);
		if (saveMessages != null) {
			mouse.click(saveMessages.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetOk = new ImageTarget(new File("images\\ok.PNG"));
		ScreenRegion ok = desktop.find(targetOk);
		if (ok != null) {
			mouse.click(ok.getCenter());
		}
		ConstantsAndMethods.delaySec(1);
		Target targetSavePath = new ImageTarget(new File("images\\savePath.PNG"));
		ScreenRegion savePath = desktop.find(targetSavePath);
		if (savePath != null) {
			mouse.click(savePath.getCenter());
			kb.keyDown(Key.SHIFT);
			kb.type("C:\\LineTemp");
		}
		Target targetGoTo = new ImageTarget(new File("images\\goToCurrentPath.PNG"));
		ScreenRegion goTo = desktop.find(targetGoTo);
		if (goTo != null) {
			mouse.click(goTo.getCenter());
		}

		Target targetSave = new ImageTarget(new File("images\\save.PNG"));
		ScreenRegion save = desktop.find(targetSave);
		if (save != null) {
			mouse.click(save.getCenter());

		}
	}

}
