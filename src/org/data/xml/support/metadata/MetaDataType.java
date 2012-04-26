package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum MetaDataType implements Serializable{
	
	None ("none_def", "None Definition", "none", null, 0),
	QueryDef ("query_def", "Query Definition", "qurydef", QueryDef.class, 1),
	Application ("application_def", "Application Definition", "applicationdef", Application.class, 2),
	Screen ("screen_def", "Screen Definition", "screendef", Screen.class, 3),
	ScreenInstance ("screen_instance_def", "Screen Instance Definition", "screeninstancedef", ScreenInstance.class, 4),
	Component ("component", "Component", "component", Component.class, 5),
	ComponentInstance ("component instance", "Component Instance", "componentInstance", ComponentInstance.class, 6),
	View ("view", "View", "view", View.class, 7),
	ViewInstance ("view instance", "View Instance", "viewInstance", ViewInstance.class, 8),
	Applet ("applet", "Applet", "applet", Applet.class, 9);
	
	private final String _name;
	private final String _label;
	private final String _tag;
	private final Class<?> _class;
	private final int _type;
	
	private MetaDataType(String name, String label, String tag, Class<?> clz, int type) {
		_name = name;
		_label = label;
		_tag = tag;
		_class = clz;
		_type = type;
	}
	
	private static final Map<Integer, MetaDataType> _metaTypeMap = new HashMap<Integer, MetaDataType>();
	
	static {
		for (MetaDataType type : MetaDataType.values()){
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
