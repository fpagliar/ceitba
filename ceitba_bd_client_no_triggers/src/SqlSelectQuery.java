
public class SqlSelectQuery extends SqlQuery{

	public SqlSelectQuery(String query, String[] columnNames,
			CeitbaConnection con) {
		super(query, columnNames, con);
		// TODO Auto-generated constructor stub
	}

	public TableData run(){
//		System.out.println("Voy a correr el select");
		Object[][] info = con.executeSelectQuery(this.query, this.columnNames);
//		System.out.println("Corri el select");
		if (info == null){
			return null;
		}
		TableData ans = new TableData(this.columnNames, info);
		return ans;
	}
	
}
