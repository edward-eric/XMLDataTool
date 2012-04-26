package org.data.xml.support.db;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum DatabaseType {
	
	MYSQL ("MYSQL", "", "", false),
	DB2 ("DB2", "com.ibm.db2.jcc.DB2Driver", "jdbc:db2://", false);
	
	DatabaseType(String label, String driver, String prefix, boolean orderby) {
		_label = label;
		_driver = driver;
		_prefix = prefix;
		_orderbyRequired = orderby;
	}
	
	private static final Map<String, DatabaseType> _types = new HashMap<String, DatabaseType>();
	
	static {
		for (DatabaseType type : DatabaseType.values()){
			_types.put(type.toString(), type);
		}
	}
	
	public static DatabaseType getDBType(String dbtype){
		return _types.get(dbtype) != null ? _types.get(dbtype) : null;
	}
	
	private final String _label;
	private final String _driver;
	private final String _prefix;
	private final boolean _orderbyRequired;
	private final Map<String, Catalog> _catalogs = new LinkedHashMap<String, Catalog>();
	
	public String toString() {
		return _label;
	}
	
	public String getDriver() {
		return _driver;
	}
	
	public String getLabel() {
		return _label;
	}
	
	public String getPrefix() {
		return _prefix;
	}
	
	public boolean getOrderByRequired()
	{
		return _orderbyRequired;
	}
	
	public Map<String, Catalog> getCatalogs() {
		return _catalogs;
	}
	
	public Catalog getConnectionInfo(String label){
		return getCatalogs().get(label);
	}
	
	public String getURL(String host, String port, String database){
		String dburl = getPrefix() + host + ":" + port;
		switch(this){
		case DB2:
			dburl += "/"+database;
			break;
		default:
			dburl += "/" + database;
		}
		return dburl;
	}
	
	public String getUser(){
		switch(this) {
		case DB2:
			return "dtuser";
		default:
			return "user";
		}
	}
	
	public String getPassword() {
		switch(this) {
		case DB2:
			return "Welcome1";
		default:
			return "pass";
		}
	}

}
