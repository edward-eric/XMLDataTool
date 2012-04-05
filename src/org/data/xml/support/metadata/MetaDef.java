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

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_label() {
		return _label;
	}

	public void set_label(String _label) {
		this._label = _label;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

}
