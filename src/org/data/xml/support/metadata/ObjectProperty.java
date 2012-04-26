package org.data.xml.support.metadata;

import java.io.Serializable;

/**
 * A single property type of a meta data object. The properties are understood
 * by the applications that use them, but not by the application framework per se.
 * 
 * @author eric.chen
 *
 */

public class ObjectProperty implements Exportable, Serializable {
	
	private static final long serialVersionUID = -8839437346147384296L;

	/**
	 * property name
	 */
	protected String _name;
	
	/**
	 * the display label for the property
	 */
	protected String _label;
	
	/**
	 * the type of permissible values
	 */
	protected String _type;
	
	/**
	 * whether the property must have a non-null value for the object
	 */
	protected boolean _isRequired;
	
	/**
	 * the factory default value
	 */
	protected Object _defaultValue;
	
	public ObjectProperty(String name, String label, String type, boolean isRequired)
	{
		_name = name;
		_label = label;
		_type = type;
		_isRequired = isRequired;
	}

	@Override
	public void toXML(StringBuilder buf) {
		
	}
	
	public boolean isReuqired() {
		return _isRequired;
	}
	
	public String getName() {
		return _name;
	}
	
	public Object getDefaultValue() {
		return _defaultValue;
	}
	
	public void setDefaultValue(String value){
		_defaultValue = value;
	}

}
