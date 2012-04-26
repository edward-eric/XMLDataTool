package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryDef extends MetaData implements Serializable {

    /**
     * Source table, used for base join for other tables
     */
	protected String _source = null;
	
	/**
	 * default source type for query definition
	 */
	protected QueryType _queryType = QueryType.TABLE;
	
	/**
	 * group of order by columns
	 */
	protected Map<String, Boolean> _orderbys = new LinkedHashMap<String, Boolean>();
	
	/**
	 * Store all fetched columns' names and column Definitions
	 */
	protected Map<String, ColumnDef> _columns = new LinkedHashMap<String, ColumnDef>();
	
	/**
	 * Store Query Definition's Join name, Join Definition
	 */
	protected Map<String, Join> _joins = new LinkedHashMap<String, Join>();
	
	@Override
	public MetaDataType getType() {
		return MetaDataType.QueryDef;
	}
	
	public QueryDef(long id, String name, String label, String desc, String source, String type, String orderby){
		super(id, name, label, desc);
		_source = source;
		_queryType = QueryType.getQueryType(type);
		splitOrderbys(orderby);
	}
	
	private void splitOrderbys(String orderby){
		if(orderby != null && orderby.length() > 0){
			orderby = orderby.trim();
			String[] orderbys = orderby.split(",");
			for(String order : orderbys){
				order = order.trim();
				String[] orders = order.split(" ");
				String trend = orders[orders.length - 1];
				boolean ascSort = !"desc".equalsIgnoreCase(trend);
				int lastIndex = orders.length - ("ASC".equalsIgnoreCase(trend) || "DESC".equalsIgnoreCase(trend) ? 1 : 0);
				if(lastIndex > 0){
					StringBuilder fieldName = new StringBuilder(20);
					for(int i = 0; i < lastIndex; i++){
						fieldName.append(orders[i]);
						if((i+1) < lastIndex){
							fieldName.append(' ');
						}
					}
					_orderbys.put(fieldName.toString(), Boolean.valueOf(ascSort));
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
	
	public Map<String, Boolean> getOrderBys() {
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
