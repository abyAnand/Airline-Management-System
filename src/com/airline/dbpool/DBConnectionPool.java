package com.airline.dbpool;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
public class DBConnectionPool {



	private static ComboPooledDataSource datasource;
		  static {
		    try {
		      datasource = new ComboPooledDataSource();
		      Properties properties = new Properties();
		      // Loading properties file
		      InputStream inputStream = new FileInputStream("resources/db.properties");
		      properties.load(inputStream);
		      datasource.setDriverClass(properties.getProperty("DRIVER_CLASS")); //loads the jdbc driver
		      datasource.setJdbcUrl(properties.getProperty("CONNECTION_STRING"));
		      datasource.setUser(properties.getProperty("USERNAME"));
		      datasource.setPassword(properties.getProperty("PASSWORD"));
		      // the settings below are optional
		      // c3p0 can work with defaults
		      datasource.setInitialPoolSize(5);
		      datasource.setMinPoolSize(5);
		      datasource.setAcquireIncrement(5);
		      datasource.setMaxPoolSize(20);
	  }
		    catch(IOException | PropertyVetoException e) {
		      e.printStackTrace();
		    }
		  }
	public static javax.sql.DataSource getDataSource() {
		    return datasource;
		  }
}
