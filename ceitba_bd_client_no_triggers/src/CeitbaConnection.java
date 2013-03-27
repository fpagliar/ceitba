import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CeitbaConnection {

	private String DB_URL = "jdbc:postgresql://veruca.itba.edu.ar/ceitba";
//	private String DB_URL = "jdbc:postgresql://localhost/ceitba";
	private Connection con = null;
	private String username = "";
	private String password = "";

	public CeitbaConnection(String username, String password){
		this.username = username;
		this.password = password;		
	}

	private void setupConnection(String Username, String Password){
		try{
			// Load the Driver class.
			Class.forName("org.postgresql.Driver");
//			System.out.println("Driver loaded");
			//Create the connection using the static getConnection method
			con = DriverManager.getConnection(DB_URL, Username, Password);
		} catch(Exception e){
			new ErrorWindow("Error connecting to the database!");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			con.setAutoCommit(false); //to be able to rollback!
		} catch (SQLException e) {
			new ErrorWindow("Fatal: could not get the settings for the database");			
			try {
				con.close();
			} catch (SQLException e2) {
				new ErrorWindow("Fatal: could not close connection!");
				System.exit(1);
			}
		} 
		return;
	}

	public  Object[][] executeSelectQuery(String queryString, String[] columnNames){
		try{
			setupConnection(username, password);
//			System.out.println("Connection created ok");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryString.toLowerCase());
			//			ResultSet rs = stmt.executeQuery("select column from test");
//			System.out.println("Statement executed ok");
			ArrayList<Object> ans = new ArrayList<Object>();
			Object[] tuple = null;
			int i;
			while (rs.next()){
				tuple = new Object[columnNames.length];
				i = 0;
				for(String column: columnNames){
					tuple[i] = rs.getString(column);
					i++;
				}
				ans.add(tuple);
			}
			return (Object[][]) ans.toArray( new Object[ans.size()][]);

		} catch(Exception e){
			new ErrorWindow("Error retrieving the data: " + queryString);
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				new ErrorWindow("Fatal: could not close connection!");
				System.exit(1);
			}
		}
		return null;
	}

	public void executeInsertQuery(String queryString){
		setupConnection(username, password);
		try {
			Statement stmt = con.createStatement(); // TODO: USE PREPARED STATEMENT
			stmt.executeUpdate(queryString.toLowerCase());
            con.commit();
            new MessageWindow("Data inserted correctly");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				new ErrorWindow("Fatal: could not rollback: query-> " + queryString);
			}
			new ErrorWindow("Error inserting new data: " + queryString);
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				new ErrorWindow("Fatal: could not close connection!");
				System.exit(1);
			}
		}		
	}
}