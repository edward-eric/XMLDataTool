package org.data.xml.support.db;

public class Catalog implements Comparable<Catalog> {
	
	private String _database;
	private String _host;
	private String _port;
	private String _label;
	
	public Catalog(String db, String host, String port, String label){
		_database = db;
		_host = host;
		_port = port;
		_label = label;
	}
	
	public Catalog(String db, String host, String port){
		_database = db;
		_host = host;
		_port = port;
	}
	
	public String getDB() {
		return _database;
	}
	
	public String getHost() {
		return _host;
	}
	
	public String getPort() {
		return _port;
	}
	
	public String getLabel() {
		return _label;
	}
	
	public String toString() {
		return _label;
	}

	@Override
	public int compareTo(Catalog o) {
		return getLabel().compareTo(o.getLabel());
	}

}
