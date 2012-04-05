package org.data.xml.support.metadata;

public enum QueryType {
	
	SQL ("sql"),
	TABLE ("table"),
	VIRTUAL ("virtual");
	
	private String _name;

	public String getName() {
		return _name;
	}
	
	public void setName(String name){
		_name = name;
	}
	
	private QueryType(String name) {
		_name = name;
	}
	
	public static QueryType getQueryType(String type){
		for ( QueryType qtype : QueryType.values() ){
			if( qtype.getName().equals(type)){
				return qtype;
			}
		}
		return null;
	}

}
