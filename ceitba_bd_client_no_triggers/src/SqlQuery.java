
public abstract class SqlQuery {
	protected String query = "";
	protected String[] columnNames = {};
	CeitbaConnection con = null;
	
	public SqlQuery(String query, String[] columnNames, CeitbaConnection con){
		this.query = query;
		this.columnNames = columnNames;
		this.con = con;
	}
	
	public abstract TableData run();
}
