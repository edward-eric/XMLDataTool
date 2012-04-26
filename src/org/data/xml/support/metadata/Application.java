package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Application extends MetaData implements Serializable {

	protected String _defaultScreenInstance = null;
	
	protected Map<String, ScreenInstance> _screenInstances = new LinkedHashMap<String, ScreenInstance>();
	
	public Application(
			long id,
			String name, String label, String description, String defaultScreen)
	{
		super(id, name, label, description);
		_defaultScreenInstance = defaultScreen;
	}
	
	@Override
	public MetaDataType getType() {
		return MetaDataType.Application;
	}
	
	public String getDefaultScreen() {
		return _defaultScreenInstance;
	}
	
	public void addScreen (ScreenInstance instance){
		_screenInstances.put(instance.getName(), instance);
	}
	
	public Collection<ScreenInstance> getScreenInstances()
	{
		return _screenInstances.values();
	}
	
	public ScreenInstance getScreenInstance (int index)
	{
		int i = 0;
		for (ScreenInstance screenInstance : _screenInstances.values()){
			if (i == index){
				return screenInstance;
			}
			++i;
		}
		return null;
	}
	
	public int getScreenIndex(String screenInstanceName){
		if (screenInstanceName != null && !"".equals(screenInstanceName)){
			int i = 0;
			for (ScreenInstance screenInstance : _screenInstances.values()){
				if(screenInstance.getScreen().getName().equals(screenInstanceName)){
					return i;
				}
				++i;
			}
		}
		return -1;
	}
	
	public int getDefaultScreenIndex()
	{
		return getScreenIndex(getDefaultScreen());
	}
	
	public EventHandler getEventhandler(ComponentInstance cinst, String eventName){
		return cinst.getView().getEventhandler(cinst, eventName);
	}
	

}
