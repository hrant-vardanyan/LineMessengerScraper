package com.hrant.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.hrant.Start;
import com.hrant.utils.ConstantsAndMethods;

import javax.swing.JTextField;

public class LineFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LineFrame.class);

	private JPanel contentPane;
	private JFileChooser fileChooser;

	private ExecutorService singleThreadExecutor;

	private JLabel totCount;
	private JButton btnStart;
	private JTextField timeZone;
	private Path pemPath;

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
		setBounds(100, 100, 301, 211);
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
		btnStart.setBounds(23, 123, 89, 23);
		contentPane.add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopDownloading();
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStop.setBounds(162, 123, 81, 23);
		contentPane.add(btnStop);

		totCount = new JLabel("");
		totCount.setBounds(305, 29, 46, 14);
		contentPane.add(totCount);
		JButton btnSelectInputFile = new JButton("Select input file (.pem)");
		btnSelectInputFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnSelectInputFile.addActionListener(this);
		btnSelectInputFile.setBounds(23, 29, 155, 23);
		contentPane.add(btnSelectInputFile);

		JLabel lblTimezoneeG = new JLabel("TimeZone  (e. g. GMT-8:00)");
		lblTimezoneeG.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTimezoneeG.setBounds(23, 86, 144, 14);
		contentPane.add(lblTimezoneeG);

		timeZone = new JTextField();
		timeZone.setBounds(177, 78, 89, 30);
		contentPane.add(timeZone);
		timeZone.setColumns(10);
		timeZone.setText("UTC-08:00");

		lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblInfo.setBounds(10, 157, 274, 14);
		contentPane.add(lblInfo);
		
		inputPath = new JLabel("");
		inputPath.setBounds(23, 63, 261, 14);
		contentPane.add(inputPath);
		lblInfo.setVisible(false);

		fileChooser = new JFileChooser();
		fileChooser.setBounds(220, 178, 392, 175);
	}

	private Start start;

	private JLabel lblInfo;

	private JLabel inputPath;

	public void startDownloading() {
		singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		singleThreadExecutor.submit(new Runnable() {

			@Override
			public void run() {
				if(pemPath == null){
					lblInfo.setText("please input .pem file !");
					lblInfo.setVisible(true);
					return;
				}

				btnStart.setEnabled(false);

				try {
					start = new Start();
					lblInfo.setText(" Start writing into DB " + new Date());
					lblInfo.setVisible(true);
					start.startScraping(timeZone.getText(), pemPath.toString());

					lblInfo.setText("Finished ! ! ! " + new Date());
				} catch (Exception e) {
					LOGGER.error("error", e);
				}
			}

		});

	}

	public void stopDownloading() {

		ConstantsAndMethods.flag = true;

	}

	public void actionPerformed(ActionEvent e) {

		int returnVal = fileChooser.showOpenDialog(LineFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.pemPath = fileChooser.getSelectedFile().toPath();
			 String message = "Selected path is " + pemPath;
			 inputPath.setText(message);
			 
			
		}

	}
}
