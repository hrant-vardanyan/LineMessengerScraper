package com.hrant.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hrant.Start;
import com.hrant.utils.ConstantsAndMethods;

import javax.swing.JTextField;

public class LineFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LineFrame.class);

	private JPanel contentPane;
	private JFileChooser fileChooser;

	private ExecutorService singleThreadExecutor;

	private Future<?> future;
	private JLabel totCount;
	private JButton btnStart;
	private JTextField timeZone;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LineFrame() {

		setTitle("LINE Scraper ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 233, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startDownloading();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStart.setBounds(20, 67, 89, 23);
		contentPane.add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopDownloading();
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStop.setBounds(119, 67, 81, 23);
		contentPane.add(btnStop);

		totCount = new JLabel("");
		totCount.setBounds(305, 29, 46, 14);
		contentPane.add(totCount);
		
		JLabel lblTimezoneeG = new JLabel("TimeZone(e. g. GMT-8:00)");
		lblTimezoneeG.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTimezoneeG.setBounds(20, 29, 144, 14);
		contentPane.add(lblTimezoneeG);
		
		timeZone = new JTextField();
		timeZone.setBounds(151, 26, 66, 30);
		contentPane.add(timeZone);
		timeZone.setColumns(10);
		timeZone.setText("GMT-8:00");

		fileChooser = new JFileChooser();
		fileChooser.setBounds(220, 178, 392, 175);
	}
	private Start start;

	public void startDownloading() {
		singleThreadExecutor = Executors.newSingleThreadExecutor();
		future = singleThreadExecutor.submit(new Runnable() {

		

			@Override
			public void run() {

				btnStart.setEnabled(false);

				try {
					start = new Start();
					start.startScraping(timeZone.getText());

				} catch (Exception e) {
					LOGGER.error("error", e);
				}
			}

		});

	}

	public void stopDownloading() {
		
		ConstantsAndMethods.deleteTempFolder(new File(start.path));
		ConstantsAndMethods.flag = true;

	}
}
