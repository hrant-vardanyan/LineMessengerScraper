package com.hrant;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.Logger;

import com.hrant.gui.LineFrame;

public class Program {

	public static LineFrame frame;
	private static final Logger LOGGER = Logger.getLogger(Program.class);

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.info("Exception in main 1 ", e);
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				LOGGER.info("Exception in main 2 ", e);
			}
		}

		frame = new LineFrame();
		frame.setVisible(true);

	}

}
