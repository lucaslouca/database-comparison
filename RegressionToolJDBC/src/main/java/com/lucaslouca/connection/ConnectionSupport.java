package com.lucaslouca.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSupport {
	private final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

	private static Properties loadProperties(String propertiesFileName) {
		InputStream input = null;
		Properties result = new Properties();
		try {

			// load db properties file
			input = ConnectionSupport.class.getClassLoader().getResourceAsStream(propertiesFileName);
			result.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * Opens a connection to the database
	 * 
	 * @param propertiesFileName
	 *            String filename to load properties from
	 * 
	 * @return returns a connection on success or NULL if something went wrong.
	 */
	public static Connection openConnection(String propertiesFileName) {
		Connection conn = null;
		try {
			Properties dbProperties = loadProperties(propertiesFileName);
			Class.forName(ORACLE_DRIVER).newInstance();

			if (dbProperties != null) {
				String host = dbProperties.getProperty("host");
				String port = dbProperties.getProperty("port");
				String username = dbProperties.getProperty("username");
				String password = dbProperties.getProperty("password");
				String servicename = dbProperties.getProperty("servicename");
				String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + servicename;

				// Open connection
				conn = DriverManager.getConnection(url, username, password);

			} else {
				throw new RuntimeException("Could not load properties with file name '" + propertiesFileName + "'");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
