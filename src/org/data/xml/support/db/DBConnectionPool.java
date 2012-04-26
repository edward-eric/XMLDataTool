package org.data.xml.support.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DBConnectionPool {
	
	private static final String _host = "DB_HOST";
	private static final String _port = "DB_PORT";
	private static final String _database = "DB";
	private static final String _username = "DB_USER";
	private static final String _password = "DB_PASS";
	private static final String _type = "DB_TYPE";
	
	protected static boolean _init = false;
	
	protected static int _capacity = 4;
	
	protected static Set<Connection> _connectionInUse = new HashSet<Connection>();
	protected static Set<Connection> _connectionFree = new HashSet<Connection>();
	
	static {
		Runtime.getRuntime().addShutdownHook(
				new Thread(){
					public void run() {
						DBConnectionPool.destroy();
					}
				}
		);
	}
	
	public synchronized static void destroy() {
		if (_connectionInUse.size() > 0) {
			System.out.println("Pool closed while: " + _connectionInUse.size() + " still alive.");
		}
		
		Set<Connection> connections = new HashSet<Connection>(_connectionInUse);
		connections.addAll(_connectionFree);
		
		for(Connection connection : connections){
			try{
				connection.close();
			}catch(SQLException e){}
		}
		
		_connectionInUse.clear();
		_connectionFree.clear();
	}
	
	public static boolean validateDBProps(String host, String port, String database, String username, String password, DatabaseType dbtype){
		try {
			Connection connection = getConnection(host, port, database, username, password, dbtype);
			connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public synchronized static Connection getConnection(String host, String port, String database, String username, String password, DatabaseType dbtype){
		if (host == null || port == null || database == null || username == null || password == null ){
			throw new RuntimeException("missing database connection paras");
		}
		return getConnection(dbtype.getURL(host, port, database), username, password, dbtype.getDriver());
	}
	
	public synchronized static Connection getConnection(String dburl, String user, String password, String driver){
		if (!_init){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try{
			Connection connection = DriverManager.getConnection(dburl, user, password);
			_init = true;
			return connection;
		}catch(SQLException sqe){
			sqe.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public synchronized static Connection getConnection(DBType type){
		if (!_init){
			try {
				Class.forName(type.getDriver());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try{
			Connection connection = DriverManager.getConnection(
					type.getDBUrl(type.getCatalogs().toString()), 
					type.getUser(), 
					type.getPassword());
			_init = true;
			return connection;
		}catch(SQLException sqe){
			sqe.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public synchronized static void forkConnection() {
		Connection connection = 
			getConnection(DBType.DB2);
		if (isDB2()) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				stmt.execute("SET CURRENT QUERY OPTIMIZATION 7");
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		_connectionFree.add(connection);
	}
	
	public synchronized static Connection getConnection() throws SQLException {
		Connection conn = null;
		while (conn == null){
			if (_connectionFree.size() == 0) {
				if (_connectionInUse.size() < _capacity){
					forkConnection();
				}
			}
			
			conn = _connectionFree.iterator().next();
			_connectionFree.remove(conn);
			
			if (!conn.isValid(0)) {
				conn.close();
				conn = null;
			}
		}
		
		_connectionInUse.add(conn);
		return conn;
	}
	
	private static DatabaseType getDatabaseType() {
		return DatabaseType.DB2;
	}
	
	public static boolean isDB2() {
		return getDatabaseType() == DatabaseType.DB2;
	}
	
	public static boolean isOrderByRequired() {
		return getDatabaseType().getOrderByRequired();
	}
	
	
	public static String getUnrestricedReadClause() {
		if(isDB2()){
			return " with ur";
		}else {
			return "";
		}
	}
	
	
	
	
	
}
