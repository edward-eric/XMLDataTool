package org.data.xml.support.metadata;

import java.io.Serializable;

public class PickMap implements Serializable {

	private static final long serialVersionUID = -7481115348980290992L;
	
	private String _source = null;
	private String _target = null;
	private boolean _cons = false;
	
	public PickMap(String source, String dest, String cons) {
		_source = source;
		_target = dest;
		_cons = "true".equalsIgnoreCase(cons);
	}

	public String getSource() {
		return _source;
	}

	public String getTarget() {
		return _target;
	}

	public boolean isCons() {
		return _cons;
	}
}
