import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class NewLockerWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField conditionField;
	private CeitbaConnection connection;
	private TextField idField;
	
	/** Constructor to setup the GUI */
	public NewLockerWindow(CeitbaConnection con) {
		this.connection = con;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label condition = new Label("Estado: "); 
		add(condition);               
		this.conditionField = new TextField(30);
		add(conditionField);                
		conditionField.addActionListener(this);

		Label idLabel = new Label("ID (Obligatorio): "); // construct Label
		add(idLabel);               // "this" Frame adds Label
		this.idField = new TextField(30); // construct TextField
		add(idField);                // "this" Frame adds TextField
		idField.addActionListener(this);
		
		setSize(290, 300);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
			
		Integer id = 0;
		try{
			id = Integer.parseInt(idField.getText()); 
		}catch(Exception e){
			new ErrorWindow("ID invalido");
			return;
		}
		
		String condition = conditionField.getText().toLowerCase();
						
		if ( ! condition.matches("[a-zA-z0-9\\s]*") ){
			new ErrorWindow("Descripcion invalida: solo puede contener letras y numeros");
			return;			
		}
		if (condition.length() == 0){
			condition = "'ok'";
		}else{
			condition = "'" + condition + "'";
		}
		String[] queryString = new String[1];
		queryString[0] = "INSERT INTO lockers(id, condition) VALUES (" + id + ", " + condition + ");";
		connection.executeInsertQuerys(queryString);
//		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
//		query.run();
		
	};
}
