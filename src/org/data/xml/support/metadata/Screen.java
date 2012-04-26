package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Screen extends MetaData implements Serializable {
	
	protected String _defaultView = null;
	
	protected Map<String, ViewInstance> _viewInstances = new LinkedHashMap<String, ViewInstance>();

	public Screen(long id, String name, String label, String desc) {
		super(id, name, label, desc);
	}

	@Override
	public MetaDataType getType() {
		return MetaDataType.Screen;
	}
	
	public String getDefaultView() {
		return _defaultView;
	}
	
	public void addView(ViewInstance instance){
		_viewInstances.put(instance.getName(), instance);
	}
	
	public Collection<ViewInstance> getViewInstances() {
		return _viewInstances.values();
	}
	
	public int getViewIndex(String viewName){
		if (viewName != null && !"".equals(viewName)){
			int i = 0;
			for (ViewInstance viewInstance : _viewInstances.values()){
				if(viewInstance.getView().getName().equals(viewName)){
					return i;
				}
				++i;
			}
		}
		return -1;
	}
	
	public int getDefaultViewIndex() {
		return getViewIndex(getDefaultView());
	}

}
