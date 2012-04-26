package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventHandler implements Exportable, Serializable {
	
	/**
	 * Component instance
	 */
	protected ComponentInstance _componentInstance = null;
	/**
	 * event to handle
	 */
	protected String _eventName = null;
	/**
	 * list of actions to execute upon receiving the event
	 */
	protected List<Action> _actions = new ArrayList<Action>();
	
	/**
	 * whether this handler is application scoped
	 */
	protected boolean _applicationScoped = false;
	
	public EventHandler(ComponentInstance cinst, String eventName, boolean applicationScoped)
	{
		_componentInstance = cinst;
		_eventName = eventName;
		_applicationScoped = applicationScoped;
	}
	
	@Override
	public void toXML(StringBuilder buf) {
		
		//TODO: 
	}
	
	public boolean isApplicationScoped()
	{
		return _applicationScoped;
	}
	
	public String getEventName()
	{
		return _eventName;
	}
	
	public static String getEventHandlerName(ComponentInstance componentInstance, String eventname)
	{
		return componentInstance.getName() + "." + eventname;
	}
	
	public String getEventHandlerName()
	{
		return getEventHandlerName(_componentInstance, _eventName);
	}
	
	public ComponentInstance getWidgetInstance()
	{
		return _componentInstance;
	}
	
	public List<Action> getActions()
	{
		return _actions;
	}
	
	public void addAction(Action action)
	{
		_actions.add(action);
	}

}
