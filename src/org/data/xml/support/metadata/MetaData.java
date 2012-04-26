package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class MetaData implements Serializable{
	
	protected long _id;
	protected String _name;
	protected String _label;
	protected String _description;
	
	
	protected Map<String, ObjectProperty> _applicationProperties = new HashMap<String, ObjectProperty>();
	
	
	public abstract MetaDataType getType();
	
	public MetaData(String name) {
		_id = -1;
		_name = name;
		_label = getType().getLabel() + " " + name;
	}
	
	public MetaData(long id, String name, String label, String desc){
		_id = id;
		_name = name;
		_label = label;
		_description = desc;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String label) {
		this._label = label;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

}
