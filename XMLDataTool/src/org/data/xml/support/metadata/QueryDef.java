package org.data.xml.support.metadata;

import java.io.Serializable;

public class QueryDef extends MetaDef implements Serializable {

	private static final long serialVersionUID = 8008721891388871652L;

	protected String table = null;
	
	@Override
	public MetaType getType() {
		return MetaType.QueryDef;
	}
	
	public QueryDef(long id, String name, String label, String desc, String tabel, String orderby){
		super(id, name, label, desc);
		
	}

}
