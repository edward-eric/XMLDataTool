package org.data.xml.support.metadata;

import java.io.Serializable;

public abstract class AbstractJoinSpec implements Serializable {
	
	protected String _name;
	protected String _label;
	protected String _source;
	protected String _dest;
	
	public AbstractJoinSpec(String name, String label, String source, String dest){
		_name =name;
		_label = label;
		_source = source;
		_dest = dest;
	}

	public String getName() {
		return _name;
	}

	public String getSource() {
		return _source;
	}

	public String getDest() {
		return _dest;
	}	
	
}
