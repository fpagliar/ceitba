import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class SubscribeLockerWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField idField;
	private TextField legacyField;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public SubscribeLockerWindow(CeitbaConnection con) {
		this.connection = con;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label idLabel = new Label("Locker id (Obligatorio): "); // construct Label
		add(idLabel);               // "this" Frame adds Label
		this.idField = new TextField(30); // construct TextField
		add(idField);                // "this" Frame adds TextField
		idField.addActionListener(this);

		Label legacyLabel = new Label("Legajo (Obligatorio): "); 
		add(legacyLabel);               
		this.legacyField = new TextField(30);
		add(legacyField);                
		legacyField.addActionListener(this);

		setSize(290, 500);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		Integer id = 0;
		try{
			id = Integer.parseInt(idField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Locker id invalido");
			return;
		}
		Integer legacy = 0;
		try{
			legacy = Integer.parseInt(legacyField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Legajo invalido");
			return;
		}
		String[] queryStrings = new String[1];
		queryStrings[0] = "UPDATE lockers SET owner_id = ( SELECT id FROM users WHERE legacy = " + legacy + " ) WHERE id = " + id + ";";
//		String queryString = "INSERT INTO lockers_subscriptions(legacy, id) VALUES(" + legacy + ", " + id + ");";
//		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
//		query.run();
		connection.executeInsertQuerys(queryStrings);
	};
}
