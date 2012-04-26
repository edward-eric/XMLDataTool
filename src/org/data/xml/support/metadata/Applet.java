package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Applet extends MetaData implements Serializable{
	
	protected QueryDef _queryDef = null;
	
	protected String _format = null;
	
	protected List<Field> _fields = new ArrayList<Field>();
	
	public Applet(long id, String name, QueryDef quryDef, String format){
		super(id, name, name, name);
		_queryDef = quryDef;
		_format = format;
	}

	@Override
	public MetaDataType getType() {
		return MetaDataType.Applet;
	}
	
	public QueryDef getQueryDef(){
		return _queryDef;
	}
	
	public List<Field> getFields(){
		return _fields;
	}
	
	public String getFormat(){
		return _format;
	}
	
	public void addField(Field field){
		if(field!= null){
			field.setApplet(this);
			_fields.add(field);
		}
	}

}
