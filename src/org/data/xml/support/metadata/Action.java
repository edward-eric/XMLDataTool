package org.data.xml.support.metadata;

import java.io.Serializable;


/**
 * Single step in an event handler
 * @author eric.chen
 *
 */

public class Action implements Exportable, Serializable {
	
	/**
	 * address expression of the property
	 */
	protected String _targetProperty = null;
	/**
	 * value expression
	 */
	protected String _value = null;
	
	public Action(String targetProperty)
	{
		_targetProperty = targetProperty;
	}
	
	public String getTargetProperty()
	{
		return _targetProperty;
	}
	
	public String getValueExpression()
	{
		return _value;
	}
	
	public void setValueExpression(String value)
	{
		_value = value;
	}
	
	@Override
	public void toXML(StringBuilder buf) {
		// TODO Auto-generated method stub

	}

}
