import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class UnsubscribeServiceWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField legacyField;
	private CeitbaConnection connection;
	private TextField nameField;
	
	/** Constructor to setup the GUI */
	public UnsubscribeServiceWindow(CeitbaConnection con) {
		this.connection = con;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label legacyLabel = new Label("Numero de legajo (Obligatorio): "); // construct Label
		add(legacyLabel);               // "this" Frame adds Label
		this.legacyField = new TextField(30); // construct TextField
		add(legacyField);                // "this" Frame adds TextField
		legacyField.addActionListener(this);
		
		Label nameLabel = new Label("Nombre del servicio (Obligatorio): "); // construct Label
		add(nameLabel);               // "this" Frame adds Label
		this.nameField = new TextField(30); // construct TextField
		add(nameField);                // "this" Frame adds TextField
		nameField.addActionListener(this);
		
		setSize(290, 500);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		Integer legacy = 0;
		try{
			legacy = Integer.parseInt(legacyField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Numero de Legajo invalido");
			return;
		}
		
		String name = nameField.getText().toLowerCase();
		
		if ( ! name.matches("[a-z\\s]+") ){
			new ErrorWindow("Nombre invalido");
			return;			
		}
		
		String queryString = "DELETE FROM subscriptions WHERE user_id = ( SELECT id FROM users WHERE legacy = " + legacy + ") AND service_id = ( SELECT id FROM services WHERE name = '" + name + "');";
		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
		query.run();
		
	};
}