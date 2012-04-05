package org.data.xml.support.metadata;

import java.io.Serializable;

public class JoinSpec extends AbstractJoinSpec implements Serializable {

	
	private static final long serialVersionUID = -677418271251676261L;
	
	private Join _join;
	
	public JoinSpec(String name, String label, String source, String dest) {
		super(name, label, source, dest);
	}
	
	public void setJoin(Join join){
		_join = join;
	}
	
	public Join getJoin() {
		return _join;
	}
}
