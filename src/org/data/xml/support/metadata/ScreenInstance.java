package org.data.xml.support.metadata;

public class ScreenInstance extends MetaData {
	
	/**
	 * screen type
	 */
	protected Screen _screen = null;
	
	/**
	 * application could contain several screen instances
	 */
	protected Application _application = null;

	public ScreenInstance(long id, String name, String label, String desc, Screen screen, Application application) {
		super(id, name, label, desc);
		_screen = screen;
		_application = application;
	}

	@Override
	public MetaDataType getType() {
		return MetaDataType.ScreenInstance;
	}
	
	public Screen getScreen() {
		return _screen;
	}
	
	public Application getApplication() {
		return _application;
	}
}
