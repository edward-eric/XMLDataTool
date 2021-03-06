package org.data.xml.support.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColumnDef implements Serializable {
	
	private String _name;
	private String _desc;
	private String _columnName;
	
	/**
	 * Belonging to which join table's
	 */
	private String _join;
	
	/**
	 * Query definition which contains this column
	 */
	private QueryDef _parentQuery;
	private boolean _readOnly = false;
	private boolean _required = false;
	private ColumnType _type = ColumnType.NONE;
	private List<PickMap> _picks = new ArrayList<PickMap>();
	private String _pickList = null;
	
	public ColumnDef(String name, String desc, String columnname, String join, String pickList, String type){
		_name = name;
		_desc = desc;
		_columnName = columnname;
		_join = join;
		_pickList = pickList;
		_type = ColumnType.getType(type);
		
		if("".equals(_join)){
			_join = null;
		}
		_readOnly = "id".equalsIgnoreCase(_columnName);
	}
	
	public QueryDef getQueryDef(){
		return _parentQuery;
	}
	
	public void setQueryDef(QueryDef query){
		_parentQuery = query;
	}
	
	public boolean isReadable() {
		return _readOnly;
	}
	
	public boolean isRequired() {
		return _required;
	}
	
	public String getPickList() {
		return _pickList;
	}
	
	public void addPickMap(String source, String target, String cons){
		_picks.add(new PickMap(source, target, cons));
	}
	
	public List<PickMap> getPickMap() {
		return _picks;
	}
	
	public String getTableName(){
		if(_join == null){
			return getQueryDef().getSource();
		}
		return getQueryDef().getJoin(getJoin()).getTableName();
	}
	
	public String getTableAlias() {
		String join = getJoin();
		if(join != null && !"".equals(join)){
			return "\"" + join + "\"";
		}else {
			return getQueryDef().getSource();
		}
	}
	
	public String getName(){
		return _name;
	}
	
	public String getDescription() {
		return _desc;
	}
	
	public String getColumnName(){
		return _columnName;
	}
	
	public String getQualifiedColumnName() {
		StringBuilder name = new StringBuilder(20);
		if ( getQueryDef().isTableBased() ) {
			name.append(getTableAlias()).append('.');
		}
		name.append(getColumnName());
		return name.toString();
	}
	
	public String getQualifiedColumnName(Map<String, String> tableMap){
		StringBuilder name = new StringBuilder(20);
		boolean bDateType = false;
		
		if ( getQueryDef().isTableBased() ){
			bDateType = getColumnType() == ColumnType.DATE;
			String tableIndex;
			String join = getJoin();
			if( join != null ){
				tableIndex = tableMap.get(join);
				if( tableIndex == null){
					tableIndex = "T" + (tableMap.size() + 1);
				}else {
					tableIndex = "T";
				}
				name.append(tableIndex).append('.');
			}
			name.append(getColumnName());
		}
		
		if (bDateType) {
			name.insert(0, "DATE (").append(")");
		}
		
		return name.toString();
	}
	
	public String getJoin() {
		return _join;
	}
	
	public ColumnType getColumnType() {
		return _type;
	}
	
	
}
