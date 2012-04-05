package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum MetaType implements Serializable{
	
	None ("none_def", "None Definition", "none", null, 0),
	QueryDef ("query_def", "Query Definition", "qurydef", QueryDef.class, 1);
	
	private final String _name;
	private final String _label;
	private final String _tag;
	private final Class<?> _class;
	private final int _type;
	
	private MetaType(String name, String label, String tag, Class<?> clz, int type) {
		_name = name;
		_label = label;
		_tag = tag;
		_class = clz;
		_type = type;
	}
	
	private static final Map<Integer, MetaType> _metaTypeMap = new HashMap<Integer, MetaType>();
	
	static {
		for (MetaType type : MetaType.values()){
			_metaTypeMap.put(type.getType(), type);
		}
	}

	public String getName() {
		return _name;
	}

	public String getLabel() {
		return _label;
	}

	public String getTag() {
		return _tag;
	}

	public Class<?> getTClass() {
		return _class;
	}

	public int getType() {
		return _type;
	}
	
	

}
