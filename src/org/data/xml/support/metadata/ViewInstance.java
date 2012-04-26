package org.data.xml.support.metadata;

import java.io.Serializable;

public class ViewInstance extends MetaData implements Serializable {
	
	/**
	 * view which identify this instance
	 */
	protected View _view = null;
	
	/**
	 * screen uses this view instance, a screen could contain several instances
	 */
	protected Screen _screen = null;

	public ViewInstance(long id, String name, String label, String desc) {
		super(id, name, label, desc);
	}

	@Override
	public MetaDataType getType() {
		return MetaDataType.ViewInstance;
	}
	
	public View getView() {
		return _view;
	}
	
	public Screen getScreen() {
		return _screen;
	}

}
