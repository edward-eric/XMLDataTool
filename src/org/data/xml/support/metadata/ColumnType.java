package org.data.xml.support.metadata;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public enum ColumnType {
	
	NONE ("none"),
	STRING ("string"),
	TEXT ("text"),
	INTEGER ("integer"),
	BOOLEAN ("boolean"),
	NUMERIC ("number"),
	DATE ("date"),
	TIMESTAMP ("timestamp"),
	ID ("id"),
	CURRENCY ("currency"),
	BLOB ("blob");
	
	private static final Map<String, ColumnType> _typeMap = new HashMap<String, ColumnType>();
	
	static {
		for ( ColumnType type : ColumnType.values() ){
			_typeMap.put(type.getName(), type);
		}
	}
	
	private ColumnType(String name) {
		_name = name;
	}
	
	private String _name;

	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String toString() {
		return _name;
	}
	
	public static ColumnType getType(String type){
		return _typeMap.get(type) != null ? _typeMap.get(type) : NONE;
	}
	
	public Class<?> getClassType() {
		switch(this) {
		case NONE:
		case STRING:
		case TEXT:
			return String.class;
		case ID:
			return Long.class;
		case BOOLEAN:
			return Boolean.class;
		case INTEGER:
			return Integer.class;
		case DATE:
			return Date.class;
		case TIMESTAMP:
			return Timestamp.class;
		case NUMERIC:
			return BigDecimal.class;
		case CURRENCY:
			return Currency.class;
		case BLOB:
			return ByteArrayInputStream.class;
	    default:
	    	return String.class;
		}
	}

}
