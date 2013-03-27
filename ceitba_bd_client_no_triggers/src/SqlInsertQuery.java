
public class SqlInsertQuery extends SqlQuery{
	
	public SqlInsertQuery(String query, String[] columnNames,
			CeitbaConnection con) {
		super(query, columnNames, con);
		// TODO Auto-generated constructor stub
	}

	public TableData run(){
		con.executeInsertQuery(this.query);
		return null;
	}
}
