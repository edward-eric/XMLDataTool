package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractJoin implements Serializable {
	
	protected String _name;
	protected String _label;
	protected boolean _outer = false;
	protected QueryDef _query = null;
	
	public AbstractJoin(String name, String label, boolean outer){
		_name = name;
		_label = label;
		_outer = outer;
	}
	
	public String getName() {
		return _name;
	}
	
	public boolean isOuter() {
		return _outer;
	}
	
	public QueryDef getQueryDef() {
		return _query;
	}
	
	public void setQueryDef(QueryDef qdef) {
		_query = qdef;
	}
	
	public abstract List<? extends AbstractJoinSpec> getSpecs();
}
