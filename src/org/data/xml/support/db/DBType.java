package org.data.xml.support.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum DBType {
	
	MYSQL ("mysql"),
	DB2 ("db2");
	
	private final String _label;
	private static final Properties _properties = new Properties();
	
	DBType(String label) {
		_label = label;
	}
	
	private static final String _delimiter = ".";
	private static final String _user = "user";
	private static final String _pass = "pass";
	private static final String _driver = "driver";
	private static final String _prefix = "prefix";
	private static final String _orderby = "orderBy";
	
	private static final Map<String, DBType> _types= new HashMap<String, DBType>();
	
	static {
		for ( DBType type : DBType.values() ){
			_types.put(type.toString(), type);
		}
	}
	
	public String toString() {
		return _label;
	}
	
	private Map<String, Catalog> _catalogs = new HashMap<String, Catalog>();
	
	public static DBType getDBType(String type) {
		return _types.get(type);
	}
	
	public Catalog getConnectionInfo(String label){
		return getCatalogs().get(label);
	}
	
	public Map<String, Catalog> getCatalogs() {
		return _catalogs;
	}
	
	public void parseConnectionInfo(Reader reader) {
		BufferedReader in = new BufferedReader(reader);
		String line = null;
		String host = null;
		String port = null;
		String database = null;
		
		try{
			if(this == DB2){
				while((line = in.readLine())!= null){
					line = line.toUpperCase();
					if(line.indexOf("CATALOG TCPIP") != -1){
						host = line.substring(line.indexOf("REMOTE") + 7, line.indexOf("SERVER") - 1);
						port = line.substring(line.indexOf("SERVER") + 7, line.indexOf("OSTYPE") - 1);
					}else if(line.indexOf(" CATALOG DB") != -1){
						if(host != null){
							database = line.substring(line.indexOf(" CATALOG DB") + 12, line.indexOf(" AT"));
							Catalog catalog = new Catalog(database, host, port);
							_catalogs.put(catalog.toString(), catalog);
						}
					}
				}
			}else if(this == MYSQL){
				Catalog catalog = new Catalog("test", "localhost", "3600");
				_catalogs.put(catalog.toString(), catalog);
			}
		}catch (Exception e) {
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){}
			}
		}
	}
	
	public void removeConnectionInfo(String clabel){
		_catalogs.remove(clabel);
	}
	
	public void loadConnectionUserInfo(String dbfilename){
		try {
			_properties.load(new InputStreamReader(getClass().getResourceAsStream(dbfilename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getProperty(String label){
		return _properties.getProperty(this.toString() + _delimiter + label);
	}
	
	public String getDBUrl(String catalog){
		Catalog _catalog = getConnectionInfo(catalog);
		String dburl = getProperty(_prefix) + _catalog.getHost() + ":" +
		               _catalog.getPort() + "/" + _catalog.getDB();
		return dburl;
	}
	
	public String getDriver() {
		return getProperty(_driver);
	}
	
	public String getUser() {
		return getProperty(_user);
	}
	
	public String getPassword() {
		return getProperty(_pass);
	}
	
	public boolean getOrderBy() {
		return Boolean.valueOf(getProperty(_orderby));
	}

}
