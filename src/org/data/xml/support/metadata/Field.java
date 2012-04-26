package org.data.xml.support.metadata;

import java.io.Serializable;

public class Field implements Serializable {
	
	private String _label = null;
	private String _name = null;
	private boolean _readOnly = false;
	private int _sequence = 0;
	private boolean _bInactive = false;
	private boolean _bVisible = true;
	private Applet _parentApplet = null;
	
	public Field(String label, String name, String readOnly, String sequence, String inactive, String visible){
		_label = label;
		_name = name;
		_readOnly = Boolean.parseBoolean(readOnly);
		
		try{
			_sequence = Integer.parseInt(sequence);
		}catch (NumberFormatException e) {
		}
		
		_bInactive = Boolean.parseBoolean(inactive);
		_bVisible = Boolean.parseBoolean(visible);
	}
	
	public String getLabel(){
		return _label;
	}
	public String getName() {
		return _name;
	}
	
	public boolean getInactive(){
		return _bInactive;
	}
	
	public boolean getVisible(){
		return _bVisible;
	}
	
	public Applet getApplet(){
		return _parentApplet;
	}
	
	public int getSequence(){
		return _sequence;
	}
	
	public void setApplet(Applet parent){
		_parentApplet = parent;
	}

}
