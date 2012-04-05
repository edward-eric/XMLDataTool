package org.data.xml.support.util;

public class StringPair {
	
	private String _str_type = null;
	private String _str_name = null;
	
	public StringPair(String type, String name) {
		if (null == type || null == name){
			throw new RuntimeException("null string invloved..");
		}
		
		_str_type = type;
		_str_name = name;
	}
	
	public int hashCode() {
		return (_str_type + "." + _str_name).hashCode();
	}
	
	public boolean equals(Object obj){
		boolean value = false;
		if (obj instanceof StringPair) {
			StringPair pair = (StringPair)obj;
			value = _str_type.equals(pair._str_name) && _str_name.equals(pair._str_name);
		}
		return value;
	}

}
