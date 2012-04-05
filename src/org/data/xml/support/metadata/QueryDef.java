package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryDef extends MetaDef implements Serializable {

	private static final long serialVersionUID = 8008721891388871652L;

	protected String _source = null;
	protected QueryType _queryType = QueryType.TABLE;
	
	protected Map<String, Boolean> _orderbys = new LinkedHashMap<String, Boolean>();
	protected Map<String, ColumnDef> _columns = new LinkedHashMap<String, ColumnDef>();
	protected Map<String, Join> _joins = new LinkedHashMap<String, Join>();
	
	@Override
	public MetaType getType() {
		return MetaType.QueryDef;
	}
	
	public QueryDef(String name){
		super(name);
	}
	
	public QueryDef(long id, String name, String label, String desc, String source, String type, String orderby){
		super(id, name, label, desc);
		_source = source;
		_queryType = QueryType.getQueryType(type);
		if (orderby != null){
			orderby = orderby.trim();
			if ( orderby.length() > 0 ){
				String[] orderbys = orderby.split(",");
				for (String order : orderbys){
					order = order.trim();
					String[] criteria = order.split(" ");
					if( criteria.length == 1){
						_orderbys.put(criteria[0], true);
					}else if(criteria.length == 2){
						_orderbys.put(criteria[0], "true".equalsIgnoreCase(criteria[1]));
					}
				}
			}
		}
	}
	
	public String getSource () {
		return _source;
	}
	
	public QueryType getQueryType() {
		return _queryType;
	}
	
	public boolean isTableBased(){
		return getQueryType() == QueryType.TABLE;
	}
	
	public Map<String, Boolean> getOrderBy() {
		return _orderbys;
	}
	
	public void addColumn(ColumnDef column){
		if( column != null ){
			String defname = column.getName();
			if(defname != null && "".equals(defname)){
				column.setQueryDef(this);
				_columns.put(defname, column);
			}
		}
	}
	
	public ColumnDef getColumn(String defname){
		return _columns.get(defname);
	}
	
	public Collection<ColumnDef> getColumns() {
		return _columns.values();
	}
	
	public boolean containsColumn(String defname){
		return _columns.containsKey(defname);
	}
	
	public void addJoin(Join join){
		if(join!= null){
			String defname = join.getName();
			if(defname!= null && !"".equals(defname)){
				join.setQueryDef(this);
				_joins.put(defname, join);
			}
		}
	}
	
	public Join getJoin(String defname){
		return _joins.get(defname);
	}
	
	public Collection<Join> getJoins() {
		return _joins.values();
	}
	
	public boolean containsJoin(String defname){
		return _joins.containsKey(defname);
	}

}
