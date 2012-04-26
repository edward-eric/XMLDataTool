package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ComponentInstance extends MetaData implements Serializable{
	
	/**
	 * Component type
	 */
	protected Component _component = null;
	
	/**
	 * Application screen containing the widget
	 * View stands for reports, one view could contain several Component instances
	 */
	protected View _view = null;
	
	/**
	 * Initial properties value
	 */
	protected Map<String, Object> _initPropertyValues = new HashMap<String, Object>();

	@Override
	public MetaDataType getType() {
		return MetaDataType.ComponentInstance;
	}
	
	public ComponentInstance(long id, String name, String label, String description,
			Component component, View view){
		super(id, name, label, description);
		_component = component;
		_view = view;
	}
	
	public HashMap<String, Object> getInitPropertyValues()
	{
		HashMap<String, Object> props = new HashMap<String, Object>();
		for(ObjectProperty prop : _component._applicationProperties.values()){
			if(prop.getDefaultValue() != null){
				props.put(prop.getName(), prop.getDefaultValue());
			}
		}
		return props;
	}
	
	public Component getComponent()
	{
		return _component;
	}
	
	public View getView()
	{
		return _view;
	}
	
	public void setInitPropertyValue(String name, Object value){
		_initPropertyValues.put(name, value);
	}
	
	

}
