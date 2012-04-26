package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Join extends AbstractJoin implements Serializable {
	
	private String _tname;
	private List<JoinSpec> _specs = new ArrayList<JoinSpec>();
	
	public Join(String name, String label, String tname, boolean outer) {
		super(name,label, outer);
		_tname = tname;
	}
	
	public String getTableName() {
		return _tname;
	}
	
	public void addJoinSpec(JoinSpec spec){
		spec.setJoin(this);
		_specs.add(spec);
	}
	
	@Override
	public List<? extends AbstractJoinSpec> getSpecs() {
		return _specs;
	}

}
