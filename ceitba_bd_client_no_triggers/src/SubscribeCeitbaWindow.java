import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class SubscribeCeitbaWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField legacyField;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public SubscribeCeitbaWindow(CeitbaConnection con) {
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
//		String queryString = "INSERT INTO subscriptions_view(legacy, name) values ("+ legacy + ", 'ceitba');";
		
		String queryString = "INSERT INTO subscriptions(service_id, user_id) VALUES((SELECT id FROM services WHERE services.name = 'ceitba'), (SELECT id FROM users WHERE users.legacy = " + legacy + "));";
		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
		query.run();
		
	};
}