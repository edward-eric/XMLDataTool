package org.data.xml.support.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.data.xml.support.metadata.ColumnDef;
import org.data.xml.support.metadata.Join;
import org.data.xml.support.metadata.JoinSpec;
import org.data.xml.support.metadata.MetaDef;
import org.data.xml.support.metadata.MetaType;
import org.data.xml.support.metadata.QueryDef;
import org.data.xml.support.util.StringPair;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {
	
	public static final String _element_module = "module";
	public static final String _element_querydef = "qurydef";
	public static final String _element_columndef = "columndef";
	public static final String _element_join = "join";
	public static final String _element_join_spec = "spec";
	public static final String _element_pickmap = "pickMap";
	public static final String _element_picklist = "pickList";
	
	public static final String _attribute_name = "name";
	public static final String _attribute_column_name = "columnName";
	public static final String _attribute_description = "description";
	public static final String _attribute_label = "label";
	public static final String _attribute_join = "join";
	public static final String _attribute_outer = "outerJoin";
	public static final String _attribute_readonly = "readOnly";
	public static final String _attribute_required = "required";
	public static final String _attribute_source = "source";
	public static final String _attribute_source_filed = "sourceField";
	public static final String _attribute_target_field = "destColumn";
	public static final String _attribute_cons = "constrain";
	public static final String _attribute_table_name = "tableName";
	public static final String _attribute_picklist = "pickList";
	public static final String _attribute_type = "type";
	public static final String _attribute_orderby = "orderBy";
	
	protected Stack<Object> _contextStack = new Stack<Object>();
	protected Map<StringPair, MetaDef> _metas = new HashMap<StringPair, MetaDef>();
	protected List<MetaDef> _metalist = new ArrayList<MetaDef>();
	
	protected Object _topcontext = null;
	
	public DataHandler() {
		//doing nothing.
	}
	
	public void resetMeta() {
		_metas.clear();
		_metalist.clear();
		_contextStack.clear();
		_topcontext = null;
	}
	
	public void newMeta(MetaType type, String name, MetaDef md) {
		StringPair pair = new StringPair(type.getName(), name);
		_metas.put(pair, md);
		_metalist.add(md);
	}
	
	public void startDocument() {}
	
	public void endDocument() {}
	
	public void startElement(String uri, String sname, String qname, Attributes atts) {
		
		Object context = null;
		
		if (!_contextStack.isEmpty()){
			context = _contextStack.peek();
		}
		
		String attrName = atts.getValue(_attribute_name);
		String attrLabel = atts.getValue(_attribute_label);
		String attrDesc = atts.getValue(_attribute_description);
		
		if (qname == _element_module){
			
		}else if (qname == _element_querydef){
			QueryDef queryDef = new QueryDef(-1,
					attrName, 
					attrLabel,
					attrDesc, 
					atts.getValue(_attribute_source),
					atts.getValue(_attribute_type), 
					atts.getValue(_attribute_orderby));
			newMeta(MetaType.QueryDef, attrName, queryDef);
			_contextStack.push(queryDef);
		}else if(qname == _element_columndef){
			ColumnDef columnDef = new ColumnDef(attrName, attrDesc, 
					atts.getValue(_attribute_column_name),
					atts.getValue(_attribute_join), 
					atts.getValue(_attribute_picklist),
					atts.getValue(_attribute_type));
			((QueryDef)context).addColumn(columnDef);
			_contextStack.push(columnDef);
		}else if(qname == _element_join){
			Join join = new Join(attrName, attrLabel, 
					atts.getValue(_attribute_table_name), 
					!"false".equalsIgnoreCase(atts.getValue(_attribute_outer)));
			((QueryDef)context).addJoin(join);
			_contextStack.push(join);
		}else if(qname == _element_join_spec){
			((Join)context).addJoinSpec(new JoinSpec(attrName,
					attrLabel, 
					atts.getValue(_attribute_source_filed), 
					atts.getValue(_attribute_target_field)));
		}else if(qname == _element_picklist){
			
		}else if (qname == _element_pickmap){
			((ColumnDef)context).addPickMap(atts.getValue(_attribute_source_filed),
					atts.getValue(_attribute_target_field), 
					atts.getValue(_attribute_cons));
		}else {
			throw new RuntimeException("invalid tag: " + qname);
		}
		
	}
	
	public void endElement(String uri, String sname, String qname) {
		
		Object context = null;
		
		if (qname == _element_querydef ||
				qname == _element_columndef ||
				qname == _element_join){
			if (_contextStack.size() > 0){
				context = _contextStack.pop();
				_topcontext = null;
			}
		}
	}
	
	public void characters(char buf[], int offset, int length)
	throws SAXException{}

}
