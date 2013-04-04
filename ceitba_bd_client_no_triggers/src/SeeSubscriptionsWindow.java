import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class SeeSubscriptionsWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField textField;
	private JFrame window;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public SeeSubscriptionsWindow(JFrame window, CeitbaConnection con) {
		this.connection = con;
		this.window = window;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label fieldLabel = new Label("Numero de legajo: "); // construct Label
		add(fieldLabel);               // "this" Frame adds Label

		this.textField = new TextField(30); // construct TextField
		add(textField);                // "this" Frame adds TextField
		textField.addActionListener(this);

		setSize(350, 120);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		
		Integer legacy = 0;
		try{
			legacy = Integer.parseInt(textField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Numero de legajo invalido!");
			return;
		}

		String[] columnNames = {"id", "name", "legacy", "first_name", "last_name", "price"};
		String queryString = "SELECT id, name, legacy, first_name, last_name, price FROM subscriptions_view WHERE legacy = " + legacy;
		
		Object[][] results = connection.executeSelectQuery(queryString, columnNames);

		TableData output = new TableData(columnNames, results);
		CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
		if (output != null){
			newContentPane = new CeitbaWindow(output);
		}
		newContentPane.setOpaque(true); //content panes must be opaque
		window.setContentPane(newContentPane);
		window.pack();
		window.setVisible(true);		
	};
}
