package org.data.xml.support.metadata;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum FieldType {
	
	NONE("none"),
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
	
	private static final Map<String, FieldType> _types = new HashMap<String, FieldType>();
	
	static {
		for (FieldType type : FieldType.values()){
			_types.put(type.getname(), type);
		}
	}
	
	public static FieldType getType(String type){
		FieldType fType = _types.get(type);
		return fType != null ? fType : NONE;
	}
	
	private final String _name;
	
	FieldType(String name){
		_name = name;
	}
	
	public String getname(){
		return _name;
	}
	
	public Class<?> getTypeClass(){
		switch (this) {
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
	
	public String toString() {
		return _name;
	}

}
