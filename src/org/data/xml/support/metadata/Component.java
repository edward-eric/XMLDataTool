package org.data.xml.support.metadata;

import java.io.Serializable;

public class Component extends MetaData implements Serializable{

	public Component(long id, String name, String label, String description)
	{
		super(id, name, label, description);
	}
	
	public MetaDataType getType()
	{
		return MetaDataType.Component;
	}
}
