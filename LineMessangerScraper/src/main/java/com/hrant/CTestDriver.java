package com.hrant;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.hrant.utils.ConstantsAndMethods;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class CTestDriver {

	private static final URL PEM_FILE_DIR = ConstantsAndMethods.getFileFromResources("line_scrapper.pem");

	private static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
			String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
		final JSch jsch = new JSch();
		jsch.addIdentity(PEM_FILE_DIR.getFile());
		Session session = jsch.getSession(strSshUser, strSshHost, 22);
		session.setPassword(strSshPassword);

		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	}

	public static void ssh() {
		try {
			System.out.println("start");
			String strSshUser = "ec2-user"; // SSH loging username
			String strSshPassword = "smartbites"; // SSH login password
			String strSshHost = "54.204.144.132"; // hostname or ip or SSH
													// server
			int nSshPort = 22; // remote SSH host port number
			String strRemoteHost = "127.0.0.1"; // hostname or ip of your
												// database server
			int nLocalPort = 3366; // local port number use to bind SSH tunnel
			int nRemotePort = 3306; // remote port number of your database
			String strDbUser = "root"; // database loging username
			String strDbPassword = "smartbites"; // database login password

			CTestDriver.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort,
					nRemotePort);

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + nLocalPort
					+ "/okpanda_teachers_accounts", strDbUser, strDbPassword);
			con.prepareCall("");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// finally
		// {
		// System.exit(0);
		// }
	}
}
