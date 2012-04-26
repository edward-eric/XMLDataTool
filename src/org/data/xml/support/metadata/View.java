package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class View extends MetaData implements Serializable{

	public View(long id, String name, String label, String desc) {
		super(id, name, label, desc);
	}

	@Override
	public MetaDataType getType() {
		return MetaDataType.View;
	}
	
	/**
	 * Set of component instances, a view could contain several component instances
	 */
	protected Map<String, ComponentInstance> _componentInstances = new LinkedHashMap<String, ComponentInstance>();
	/**
	 * activity level event handlers
	 */
	protected Map<String, EventHandler> _eventHandlers = new LinkedHashMap<String, EventHandler>();
	
	
	public void addComponentInstance (ComponentInstance cinst)
	{
		_componentInstances.put(cinst.getName(), cinst);
	}
	
	public Map<String, ComponentInstance> getComponentInstances()
	{
		return _componentInstances;
	}
	
	public Map<String, EventHandler> getEventHandlers()
	{
		return _eventHandlers;
	}
	
	public EventHandler getEventhandler(ComponentInstance cinst, String eventName)
	{
		return _eventHandlers.get(EventHandler.getEventHandlerName(cinst, eventName));
	}
	
	public void addEventHandler(EventHandler handler)
	{
		_eventHandlers.put(handler.getEventHandlerName(), handler);
	}

	
}
