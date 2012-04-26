package org.data.xml.support.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.data.xml.support.db.DBConnectionPool;
import org.data.xml.support.db.DBType;
import org.data.xml.support.db.DatabaseType;
import org.data.xml.support.handlers.DataHandler;
import org.data.xml.support.util.DBConnectionDialog;
import org.xml.sax.SAXException;

import com.sun.org.apache.bcel.internal.util.ClassPath;

public class Test {
	
	public Test() {
		/*Properties pps = new Properties();
		try {
			pps.load(new InputStreamReader(getClass().getResourceAsStream("/db.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pps.getProperty("db.db2.user"));*/
		
		/*try {
			UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		DBConnectionDialog dialog = new DBConnectionDialog(null, "Open Connection", true);*/
	}
	
	private void analyzeData() throws ParserConfigurationException, SAXException, IOException{
		DataHandler dh = new DataHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(getClass().getResourceAsStream("/bc.xml"), dh);
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		
		Test test = new Test();
		test.analyzeData();
		
		/*boolean s = DBConnectionPool.validateDBProps("localhost", "50000", "dtdev", "dtuser", "Welcome1", DatabaseType.DB2);
		System.out.println(s);*/
		
		/*try {
			DBConnectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*System.out.println(DBType.DB2.getDBUrl());*/
		/*new Test();*/
		/*DBConnectionDialog dialog = new DBConnectionDialog(null, "Open Connection", true);*/
		
		/*System.out.println(System.getenv());*/
		/*Test test = new Test();*/
		
		
	}

}
