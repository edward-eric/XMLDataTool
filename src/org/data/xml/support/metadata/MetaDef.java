package org.data.xml.support.metadata;

import java.io.Serializable;

public abstract class MetaDef implements Serializable{
	
	private static final long serialVersionUID = 7234583951899394418L;
	
	protected long _id;
	protected String _name;
	protected String _label;
	protected String _description;
	
	
	public abstract MetaType getType();
	
	public MetaDef(String name) {
		_id = -1;
		_name = name;
		_label = getType().getLabel() + " " + name;
	}
	
	public MetaDef(long id, String name, String label, String desc){
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
